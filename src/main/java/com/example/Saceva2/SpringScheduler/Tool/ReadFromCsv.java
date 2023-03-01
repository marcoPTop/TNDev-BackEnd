package com.example.Saceva2.SpringScheduler.Tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.example.Saceva2.Dto.Dependent;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

public class ReadFromCsv {//work

	public ArrayList<Dependent> getListUser(File pathFile) throws IOException {

		ArrayList<Dependent> userList = null;
		
		MappingIterator<Dependent> personIter = new CsvMapper().readerWithTypedSchemaFor(Dependent.class).readValues(pathFile);
		userList = (ArrayList<Dependent>) personIter.readAll();
		for(Dependent dip:userList)
			System.out.println(dip);
		return userList;
	}
}
