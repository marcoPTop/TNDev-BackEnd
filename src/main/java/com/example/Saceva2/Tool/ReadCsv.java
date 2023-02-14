package com.example.Saceva2.Tool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadCsv {

	private static final String CSV_FILE = "C:\\Users\\Admin\\Desktop\\provisioning_dipendenti.csv";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/springrelazionale";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String CSV_SPLIT_BY = ",";

	public static boolean inserUpdateDb() {

		try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {
				count++;
				if(count == 1)
					continue;
				System.out.println("start read csv");
				String[] csvArr = line.split(CSV_SPLIT_BY);
				for (String user : csvArr) {
					System.out.println(user);
				}
				String cf = csvArr[3];
				String nome = csvArr[1];
				String cognome = csvArr[2];
				String ruolo = csvArr[4];
				String pass = csvArr[5];
				String eta = csvArr[6];

				String selectSQL = "SELECT cf FROM Utenti WHERE cf = ?";
				PreparedStatement selectStatement = connection.prepareStatement(selectSQL);
				selectStatement.setString(1, cf);
				ResultSet resultSet = selectStatement.executeQuery();

				if (!resultSet.next()) {
					System.out.println("new cf, insert user");
					try {
						// update

						// insert
						String insertSQL = "INSERT INTO Utenti (nome, cognome, cf, eta, id) VALUES ( ?, ?, ?, ?, (SELECT NEXT_VAL FROM UTENTI_SEQ));";
						String updateSqc = "UPDATE UTENTI_SEQ SET NEXT_VAL=LAST_INSERT_ID(NEXT_VAL+1);";
//								+ "INSERT INTO ACCOUNTS ( PASSWORD, RUOLO) VALUES( ?, ?)";
						PreparedStatement updateStatmantSqc = connection.prepareStatement(updateSqc);
						PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
						insertStatement.setString(1, nome);
						insertStatement.setString(2, cognome);
						insertStatement.setString(3, cf);
						insertStatement.setString(4, eta);
//						insertStatement.setString(5, ruolo);
//						insertStatement.setString(6, cf);
//						insertStatement.setString(7, cf);
//						insertStatement.setString(8, cf);
						insertStatement.executeUpdate();
						updateStatmantSqc.executeUpdate();
					} catch (SQLException e) {
						System.err.println("sql exception");
						e.printStackTrace();
					} finally {
						resultSet.close();
						selectStatement.close();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		inserUpdateDb();
	}
}
