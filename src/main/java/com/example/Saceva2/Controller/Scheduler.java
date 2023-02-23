package com.example.Saceva2.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.Saceva2.SpringScheduler.Db.ConnectionDb;
import com.example.Saceva2.SpringScheduler.Db.ReadFromDb;
import com.example.Saceva2.SpringScheduler.Dto.Dipendente;
import com.example.Saceva2.SpringScheduler.Tool.Tool;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

@Component
public class Scheduler implements ApplicationListener<ApplicationReadyEvent> {

	private final Logger log = LoggerFactory.getLogger(Scheduler.class);

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private ReadFromDb readFromDb = new ReadFromDb();
	private Tool tool = new Tool();
	private HashMap<String, String> dataFromTabConfig = null;
	private ArrayList<Dipendente> listDip = null;

	@Autowired
	private TaskScheduler scheduler;

//	@Scheduled(fixedDelay = 5000)
//	public void reportCurrentTime() {
//		log.info("lunch action evrey 5sec. {}", dateFormat.format(new Date()));
//	}

	@Scheduled(cron = "${cron.expression}") // retrive data from tab_config
	public void chedulingOnceADay() throws StreamReadException, DatabindException, IOException {
		try {
			dataFromTabConfig = readFromDb.readFromConfig(ConnectionDb.getInstance().getConnection(), log);
			String path = tool.extractDataFromHashMap(dataFromTabConfig, "PATH_fiLE");
			listDip = tool.readFile(path);
			if (listDip != null) {
				for (Dipendente dip : listDip)
					tool.dipendenteToBoEntity(dip);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {// on start app do someting
		try {
			dataFromTabConfig = readFromDb.readFromConfig(ConnectionDb.getInstance().getConnection(), log);
			String dateScheduling = tool.extractDataFromHashMap(dataFromTabConfig, "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void scheduleNewCall(Date dateTime) {
		scheduler.schedule(this::scheduledMethod, dateTime);
	}

	public void scheduledMethod() {
		System.out.println("ciao");
	}

}
