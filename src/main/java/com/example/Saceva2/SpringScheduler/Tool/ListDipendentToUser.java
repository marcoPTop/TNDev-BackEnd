package com.example.Saceva2.SpringScheduler.Tool;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.Saceva2.Bo.User;
import com.example.Saceva2.Db.ConnectionDb;
import com.example.Saceva2.Db.ReadFromDb;
import com.example.Saceva2.Dto.Dependent;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class ListDipendentToUser {

	private final Logger log = LoggerFactory.getLogger(ListDipendentToUser.class);

	Tool tool = new Tool();

	private String stringDateTemp = "";

	private ArrayList<Dependent> listOfDependent = null;

	private ArrayList<User> listOfUser = new ArrayList<User>();

	public ArrayList<User> listOfUsers(HashMap<String, String> dataFromTabConfig, ReadFromDb readFromDb)
			throws StreamReadException, DatabindException, IOException {

		System.out.println("run listOfUsers");
		SimpleDateFormat dateFormatDb = null;
		SimpleDateFormat formatYears = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDb = null;
		String format = "";
		Date toDay = new Date();
		String nowDay = formatYears.format(toDay);
		String stringDate = "";
		String dateScheduling = "";

//		try {// re-loadTab-config in hashmap, error reload in previous class every 15 second on lunch scheduling
//			dataFromTabConfig = readFromDb.readFromConfig(ConnectionDb.getInstance().getConnection(), log);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		dateScheduling = tool.extractDataFromHashMap(dataFromTabConfig, "DATE_SCHEDULING");

		if (dateScheduling.contains(":")) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if (dateScheduling.contains("_")) {
			format = "yyyy-MM-dd HH_mm_ss";
		} else if (dateScheduling.contains("-")) {
			format = "yyyy-MM-dd HH-mm-ss";
		}

		stringDate = nowDay + " " + dateScheduling;
		System.out.println("all : " + stringDate);
		try {
			dateFormatDb = new SimpleDateFormat(format);
			dateDb = dateFormatDb.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.err.println("date a confronto db e macchina : " + dateDb + " , " + toDay);
		if (!stringDateTemp.equals(stringDate) || stringDateTemp.equals("")) {// work
			if (dateDb.before(toDay)) {
				System.out.println("date checked try to start");
				System.out.println("variabile stringDateTemp : " + stringDateTemp + "\nStringDate : " + stringDate);
				System.out.println("start read dir from : " + dataFromTabConfig.get("PATH_FILE"));

				listOfDependent = tool.readFile(dataFromTabConfig.get("PATH_FILE"));

				if (listOfDependent != null || listOfDependent.size() != 0) {

					for (Dependent dep : listOfDependent) {

						User u = tool.dependentToBoEntity(dep);

						if (listOfUser != null)
							listOfUser.add(u);
					}
					stringDateTemp = stringDate;

					System.out.println("fine convert obj dependent");

				} else {
					System.err.println("your list is empity");
					
				}
			}
		} else {
			System.err.println("You updated Db at hours : " + stringDateTemp);
			return new ArrayList<User>();
		}
		return listOfUser;
	}

}
