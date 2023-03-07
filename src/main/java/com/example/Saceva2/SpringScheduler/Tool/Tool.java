package com.example.Saceva2.SpringScheduler.Tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import com.example.Saceva2.Bo.Account;
import com.example.Saceva2.Bo.Role;
import com.example.Saceva2.Bo.User;
import com.example.Saceva2.Db.ConnectionDb;
import com.example.Saceva2.Db.ReadFromDb;
import com.example.Saceva2.Dto.Dependent;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class Tool {

	ReadFromXml rfx = null;
	ReadFromJson rfj = null;
	ReadFromCsv rfc = null;

	// Extract data from HashMap
	public String extractDataFromHashMap(HashMap<String, String> config, String keySearch) {
		System.out.println("Start extractDataFromHashMap");
		String result = "";
		if (config.containsKey(keySearch.toUpperCase())) {
			result = config.get(keySearch.toUpperCase());
			System.out.println("Extract data from hashMap with key : " + keySearch + ", data is : " + result);
		} else {
			System.out.println("The key " + keySearch
					+ " isn't present in the array passed as an argument\nreturn String equals \"\"");
		}
		System.out.println("End extractDataFromHashMap");
		return result;
	}

	// read file
	public ArrayList<Dependent> readFile(String pathDirFile)
			throws StreamReadException, DatabindException, IOException {

		System.err.println("start read File");
		String type = "case file ";

		System.out.println("Dir is  : " + pathDirFile);
		ArrayList<String> listFileInDir = listFileInDir(pathDirFile);
		ArrayList<Dependent> dependentList = new ArrayList<Dependent>();
		File dbFile = null;
//		int indexLastPoint = 0;

		try {
			if (listFileInDir != null || listFileInDir.size() != 0 || !listFileInDir.isEmpty()) {
				for (String pathfile : listFileInDir) {
					System.out.println("File is  : " + pathfile);
					if ((pathfile.contains(toDay()) && new File(pathfile).isFile())
							|| !new File(pathfile).isDirectory()) {

						// Pattern pattern = Pattern.compile(pathFile, Pattern.CASE_INSENSITIVE);
						// Matcher matcher = pattern.matcher(".csv");
						// boolean matchFound = matcher.find();
						dbFile = new File(pathfile);

						if (pathfile.contains(".csv")) {
							System.err.println(type + ".csv");
							rfc = new ReadFromCsv();
							dependentList = rfc.getListUser(dbFile);
						} else if (pathfile.contains(".json")) {
							System.err.println(type + ".json");
							rfj = new ReadFromJson();
							dependentList = rfj.getListDipendenti(dbFile);
						} else if (pathfile.contains(".xml")) {
							System.err.println(type + ".xml");
							rfx = new ReadFromXml();
							dependentList = rfx.getListDipendenti(dbFile);
						} else {
							System.err.println(
									"the passed file is not parsable, we can only parse csv, json and xml files\nFile passed : "
											+ pathfile);
						}
					} else {
						System.err.println("the file is not today, or it is a directory");
					}
				}
			} else {
				System.err.println("Sorry the listFileInDir is null or have 0 element");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dependentList;

	}

	public ArrayList<Dependent> arrayToList(Dependent[] arrToList) {
		ArrayList<Dependent> array_list = new ArrayList<Dependent>();
		// Using Collections.addAll() method
		Collections.addAll(array_list, arrToList);
		return array_list;
	}

	public ArrayList<String> arrayToList(String[] arrToList) {
		ArrayList<String> array_list = new ArrayList<String>();
		// Using Collections.addAll() method
		Collections.addAll(array_list, arrToList);
		return array_list;
	}

	public ArrayList<String> listFileInDir(String path) {

		System.out.println("start listFileOfDir");
		ArrayList<String> fileInDirFinal = new ArrayList<String>();
		String[] fileInDir;
		File dir = new File(path);
		File temp = null;
		int indexLastPoint = 0;

		if (dir.isDirectory()) {
			fileInDir = dir.list();
			for (int i = 0; i < fileInDir.length; i++) {

				System.out.println("file is : " + fileInDir[i]);
				temp = new File(path + fileInDir[i]);
				indexLastPoint = fileInDir[i].lastIndexOf('.');

				if (fileInDir[i].contains(toDay()) && !temp.isDirectory()) {
					if (fileInDir[i].subSequence(indexLastPoint, fileInDir[i].length()).equals(".csv")
							|| fileInDir[i].subSequence(indexLastPoint, fileInDir[i].length()).equals(".json")
							|| fileInDir[i].subSequence(indexLastPoint, fileInDir[i].length()).equals(".xml")) {
						fileInDirFinal.add(path + fileInDir[i]);
					} else {
						System.err.println("the file : " + fileInDir[i] + " is not parsable, so it was skipped");
					}
				} else if (temp.isDirectory()) {
					System.err.println("THE FILE : " + fileInDir[i] + " IS DIRECTORY");
				} else {
					System.err.println("THE FILE : " + fileInDir[i] + " IS OBSOLETE");
				}
			}
		}
//		// extract only file target
//		for (int i = 0; i < fileInDir.size(); i++) {
//			if (fileInDir.get(i).contains(".csv") || fileInDir.get(i).contains(".json")
//					|| fileInDir.get(i).contains(".xml")) {
//				if (fileInDir.get(i).contains(toDay())) {
//					System.out.println(fileInDir.get(i));
//					fileInDirFinal.add(path + fileInDir.get(i));
//				}
//			}
//		}
		for (String path1 : fileInDirFinal)
			System.out.println(path1);
		return fileInDirFinal;
	}

	public User dependentToBoEntity(Dependent dip) {

		User u = null;
		Role r = new Role(dip.getRuolo());
		Account a = new Account(dip.getUserName(), dip.getEmail(), dip.getPass(), r);
		u = new User(dip.getName(), dip.getSurname(), dip.getCf(), dip.getEta(), a);
		System.out.println("dipendenteToBoEntity : " + u.toString());
		return u;
	}

	public String toDay() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("to day is : " + String.valueOf(dateFormat.format(new Date())));
		return String.valueOf(dateFormat.format(new Date()));
	}

	public String processingStringToMilliseconds(String dateSchedulingFromDb) {
		String result = "";
		String hours = "";
		String minutes = "";
		String seconds = "";
		int indexOfM;
		String regex = "[0-9]{2}H[0-9]{2}M[0-9]{2}S|[0-9]{2}-[0-9]{2}-[0-9]{2}|[0-9]{2}:[0-9]{2}:[0-9]{2}";
		String dateSchedulingFromDb2 = dateSchedulingFromDb.toUpperCase();
		System.out.println("lenght : " + dateSchedulingFromDb2.length());
		if (dateSchedulingFromDb2.matches(regex)) {

			if (dateSchedulingFromDb2.contains("H")) {
				System.out.println("string date scheduling from db is accetable");
				hours = dateSchedulingFromDb2.substring(0, 2);
				indexOfM = dateSchedulingFromDb2.indexOf("M");
				minutes = dateSchedulingFromDb2.substring(indexOfM - 2, indexOfM);
				seconds = dateSchedulingFromDb2.substring(dateSchedulingFromDb2.length() - 3,
						dateSchedulingFromDb2.length() - 1);
				result = String.valueOf((Integer.parseInt(hours) * 60 * 60 * 1000)
						+ (Integer.parseInt(minutes) * 60 * 1000) + (Integer.parseInt(seconds) * 1000));

				return result;
			} else if (dateSchedulingFromDb2.contains("-")) {
				result = bodyIfOfStringToMilliseconds(dateSchedulingFromDb2, "-");
			} else if (dateSchedulingFromDb2.contains(":")) {
				result = bodyIfOfStringToMilliseconds(dateSchedulingFromDb2, ":");
			}
		} else
			System.err.println(
					"string date scheduling from db is not acceptable , check the format like as this : 00H00m00s or 00-00-00 or 00:00:00\n"
							+ "the regx of String is : " + regex);
		System.out.println("the millisecond of _: " + hours + minutes + seconds + " is " + result);
		return result;
	}

	public String bodyIfOfStringToMilliseconds(String dateSchedulingFromDb2, String charatter) {
		String result = "";

		System.out.println("string date scheduling from db is accetable");
		String hours = dateSchedulingFromDb2.substring(0, 2);
		int indexOfChar = dateSchedulingFromDb2.indexOf(charatter);
		String minutes = dateSchedulingFromDb2.substring(indexOfChar + 1, indexOfChar + 3);
		String seconds = dateSchedulingFromDb2.substring(dateSchedulingFromDb2.length() - 2,
				dateSchedulingFromDb2.length());
		result = String.valueOf((Integer.parseInt(hours) * 60 * 60 * 1000) + (Integer.parseInt(minutes) * 60 * 1000)
				+ (Integer.parseInt(seconds) * 1000));

		return result;
	}

}
