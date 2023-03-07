package com.example.Saceva2.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Saceva2.Bo.User;
import com.example.Saceva2.Db.ConnectionDb;
import com.example.Saceva2.Db.ReadFromDb;
import com.example.Saceva2.Dto.Dependent;
import com.example.Saceva2.Repository.IRepoAccount;
import com.example.Saceva2.Repository.IRepoRole;
import com.example.Saceva2.Repository.IRepoUser;
import com.example.Saceva2.SpringScheduler.Tool.ListDipendentToUser;
import com.example.Saceva2.SpringScheduler.Tool.Tool;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

@Component
public class Scheduler implements ApplicationListener<ApplicationReadyEvent> {

	private final Logger log = LoggerFactory.getLogger(Scheduler.class);

	@Autowired
	IRepoUser iUserScheduler;
	@Autowired
	IRepoAccount iAccountScheduler;
	@Autowired
	IRepoRole iRoleScheduler;

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private ReadFromDb readFromDb = new ReadFromDb();
	private Tool tool = new Tool();
	private HashMap<String, String> dataFromTabConfig = null;
	private ArrayList<Dependent> listDip = null;
	private ArrayList<User> listOfUsers = null;
	private ListDipendentToUser updateUsers = new ListDipendentToUser();
	private String dateScheduling, cryptoPass, dateSchedulingTemp = "";

	@Scheduled(fixedDelay = 15000)
	public void upRunning()
			throws StreamReadException, DatabindException, IOException, SQLException, InterruptedException {
		log.info("lunch action evrey 15sec. {}", dateFormat.format(new Date()));
		listOfUsers = new ArrayList<User>();
		dataFromTabConfig = readFromDb.readFromConfig(ConnectionDb.getInstance().getConnection(), log);
		dateScheduling = tool.extractDataFromHashMap(dataFromTabConfig, "DATE_SCHEDULING");

		if (!dateScheduling.isEmpty()) {
			System.err.println("listOfUsers is not empty".toUpperCase());
			listOfUsers = updateUsers.listOfUsers(dataFromTabConfig, readFromDb);
		}
		log.error("devi implemenatre update qui ");

		BCryptPasswordEncoder cryptoPs = new BCryptPasswordEncoder();
//		if (!dateScheduling.equals(dateSchedulingTemp)) {
			if (!listOfUsers.isEmpty() || listOfUsers.size() != 0) {
				for (User u : listOfUsers) {
					u.getAccount().getRole()
							.setId(iRoleScheduler.findByRole(u.getAccount().getRole().getRuolo()).getId());
					System.out.println("Test encrypt password on update db");
					cryptoPass = cryptoPs.encode(u.getAccount().getPass());
					u.getAccount().setPass(cryptoPass);

					if (iUserScheduler.findByTaxCode(u.getTaxCode()) != null) {
						u.setIdUser(iUserScheduler.findByTaxCode(u.getTaxCode()).getIdUser());
						u.getAccount()
								.setIdAccount(iUserScheduler.findByTaxCode(u.getTaxCode()).getAccount().getIdAccount());
						log.info("Try update on user : " + u.getTaxCode());
					}
//				Account a = new Account(u.getAccount().getuName(), u.getAccount().getEmail(), u.getAccount().getPass(), null, u);
//				a.setRole(iRole.findByRole(u.getAccount().getRole().getRuolo()));
//				u.setAccount(a);
//				u.getAccount().setRole(iRole.findByRole(u.getAccount().getRole().getRuolo()));
					System.out.println("User : " + u.toString());
					iUserScheduler.save(u);
					Thread.sleep(275);
				}
			} else {
				System.err.println("list for updae is null");
			}
			
			dateSchedulingTemp = dateScheduling;
			
//		}
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		log.info("I am first action on start up the application :P");
	}

}
