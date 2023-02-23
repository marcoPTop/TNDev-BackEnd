package com.example.Saceva2.SpringScheduler.Tool;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import com.example.Saceva2.Bo.Account;
import com.example.Saceva2.Bo.Permesso;
import com.example.Saceva2.Bo.Utente;
import com.example.Saceva2.SpringScheduler.Dto.Dipendente;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class Tool {

	ReadFromXml rfx = null;
	ReadFromJson rfj = null;
	ReadFromCsv rfc = null;

	// Extract data from HashMap
	public String extractDataFromHashMap(HashMap<String, String> config, String keySearch) {
		String result = "";
		if (config.containsKey(keySearch.toUpperCase())) {
			result = config.get(keySearch.toUpperCase());
			System.out.println("Extract data from hashMap with key : " + keySearch + ", data is : " + result);
		} else {
			System.out.println("The key " + keySearch
					+ " isn't present in the array passed as an argument\nreturn String equals \"\"");
		}

		return result;
	}

	// read file
	public ArrayList<Dipendente> readFile(String pathFile) throws StreamReadException, DatabindException, IOException {

		System.out.println("start read File");
		String type = "case file ";

		System.out.println("File is  : " + pathFile);
		listFileOfDir(pathFile);
//		if (pathFile.contains(dateFormat.format(new Date()))) {

		ArrayList<Dipendente> dipendentiList = new ArrayList<Dipendente>();

//		    Pattern pattern = Pattern.compile(pathFile, Pattern.CASE_INSENSITIVE);
//		    Matcher matcher = pattern.matcher(".csv");
//		    boolean matchFound = matcher.find();
		File dipFile = new File(pathFile);

		if (pathFile.contains(".csv")) {
			System.out.println(type + ".csv");
			rfc = new ReadFromCsv();
			dipendentiList = rfc.getListDipendneti(dipFile);
		} else if (pathFile.contains(".json")) {
			System.out.println(type + ".json");
			rfj = new ReadFromJson();
			dipendentiList = rfj.getListDipendenti(dipFile);
		} else if (pathFile.contains(".xml")) {
			System.out.println(type + ".xml");
			rfx = new ReadFromXml();
			dipendentiList = rfx.getListDipendenti(dipFile);
		} else {
			System.err.println("il file passato non è parsabbile , possiamo parsare solo file di tipo csv, json e xml");
			return null;
		}

		return dipendentiList;
}

	public ArrayList<Dipendente> arrayToList(Dipendente[] arrToList) {
		ArrayList<Dipendente> array_list = new ArrayList<Dipendente>();
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

	public ArrayList<String> listFileOfDir(String path) {
		System.out.println("listFileOfDir");
		ArrayList<String> fileInDir = new ArrayList<String>();
		ArrayList<String> fileInDirFinal = new ArrayList<String>();
		String[] temp;
		File dir = new File(path);
		if(dir.isDirectory()) {
			temp = dir.list();
			for(String file:temp) {
				fileInDir.add(path+file);
			}
		}
		//extract only file target
		for(int i = 0; i<fileInDir.size(); i++) {
			if(fileInDir.get(i).contains(".csv") || fileInDir.get(i).contains(".json") ||
					fileInDir.get(i).contains(toDay()) || fileInDir.get(i).contains(".xml")) {
				System.out.println(fileInDir.get(i));
				fileInDirFinal.add(path+fileInDir.get(i));
			}
		}
		System.out.println("print ciaooooooo");
		for(String path1:fileInDirFinal)
			System.out.println(path1);
		return fileInDir;
	}

	public void dipendenteToBoEntity(Dipendente dip) {

		Utente u = null;
		Permesso p = new Permesso(dip.getRuolo());
		Account a = new Account(dip.getUserName(), dip.getEmail(), dip.getPass(), p, u);
		u = new Utente(dip.getName(), dip.getSurname(), dip.getCf(), dip.getEta(), a);
		System.out.println("dipendenteToBoEntity : " + u.toString());
	}

	public String toDay() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("to day is : " + String.valueOf(dateFormat.format(new Date())));
		return String.valueOf(dateFormat.format(new Date()));
	}

}
