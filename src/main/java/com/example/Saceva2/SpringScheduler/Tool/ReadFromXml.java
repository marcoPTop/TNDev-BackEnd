package com.example.Saceva2.SpringScheduler.Tool;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.example.Saceva2.SpringScheduler.Dto.Dipendente;

public class ReadFromXml
{
	public ArrayList<Dipendente> getListDipendenti(File pathFile)
	{
		System.out.println("method read xml");
//		System.out.println("Reading file: " + filePath);
		Dipendente dipObj = null;
//		Class<? extends Dipendente> bo = dipObj.getClass();
		String rootTag = "DIPENDENTE";
		
		ArrayList<Dipendente> arrayEmployees = new ArrayList<Dipendente>();

		JAXBContext jc = null;
		Scanner myReader = null;
		Unmarshaller unmarshaller = null;
//		ArrayList<String> allCellsDuplicated = new ArrayList<String>();

		try
		{
//			dipObj = new Dipendente();
			jc = JAXBContext.newInstance(Dipendente.class);
			unmarshaller = jc.createUnmarshaller();
//			File xml = new File(filePath);
			myReader = new Scanner(pathFile);
			boolean startDipedente = false;
			boolean endDipendente = false;
			String dipendenteString = "";

			while (myReader.hasNextLine())
			{
				String data = myReader.nextLine().trim();

				if (data.equalsIgnoreCase("<" + rootTag + ">"))
				{
					startDipedente = true;
					endDipendente = false;
				}

				if (startDipedente)
				{
					dipendenteString += data;
				}

				if (data.equalsIgnoreCase("</" + rootTag + ">"))
				{
					startDipedente = false;
					endDipendente = true;
				}

				if (endDipendente)
				{
					InputStream is = new ByteArrayInputStream(dipendenteString.getBytes(StandardCharsets.UTF_8));

					dipObj = (Dipendente) unmarshaller.unmarshal(is);
					
					arrayEmployees.add(dipObj);//cf unique remeber it Strunzù

					dipendenteString = "";
					endDipendente = false;
				}
			}
		}
		catch (JAXBException e)
		{
			System.out.println("JAXBException : " + e);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("FileNotFoundException : " + e);
		}
		finally
		{
			if (myReader != null)
				myReader.close();
			else
				System.out.println("myReader is null");
		}

		System.out.println("Reading employees complete.");
		System.out.println("employees number: " + arrayEmployees.size());

		return arrayEmployees;
	}

}
