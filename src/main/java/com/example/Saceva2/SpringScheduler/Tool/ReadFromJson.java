package com.example.Saceva2.SpringScheduler.Tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.example.Saceva2.SpringScheduler.Dto.Dipendente;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class ReadFromJson {
	
	Tool tool = new Tool();
	
	public ArrayList<Dipendente> getListDipendenti(File pathFile) throws FileNotFoundException {
//		Type listType = new TypeToken<ArrayList<Dipendente>>(){}.getType();
        JsonReader reader = new JsonReader(new FileReader(pathFile));
        Dipendente[] arrDip = new Gson().fromJson(reader, Dipendente[].class);
//      listDip.setListDipendenti( );
        System.out.println(arrDip[0] + ", " + arrDip[1]);
        return tool.arrayToList(arrDip);
	}
	
	
}
