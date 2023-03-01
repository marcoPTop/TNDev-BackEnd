package com.example.Saceva2.SpringScheduler.Tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.example.Saceva2.Dto.Dependent;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class ReadFromJson {//work
	
	Tool tool = new Tool();
	
	public ArrayList<Dependent> getListDipendenti(File pathFile) throws FileNotFoundException {
//		Type listType = new TypeToken<ArrayList<Dipendente>>(){}.getType();
        JsonReader reader = new JsonReader(new FileReader(pathFile));
        Dependent[] arrDip = new Gson().fromJson(reader, Dependent[].class);
//      listDip.setListDipendenti( );
        System.out.println(arrDip[0] + ", " + arrDip[1]);
        return tool.arrayToList(arrDip);
	}
	
	
}
