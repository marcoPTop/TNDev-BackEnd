package com.example.Saceva2.SpringScheduler.Tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.example.Saceva2.SpringScheduler.Dto.Dipendente;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

public class ReadFromCsv {

	public ArrayList<Dipendente> getListDipendneti(File pathFile) throws IOException {

		ArrayList<Dipendente> dipList = null;
		
		MappingIterator<Dipendente> personIter = new CsvMapper().readerWithTypedSchemaFor(Dipendente.class).readValues(pathFile);
		dipList = (ArrayList<Dipendente>) personIter.readAll();
		for(Dipendente dip:dipList)
			System.out.println(dip);
		return dipList;
	}
}
