package com.example.Saceva2.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.Saceva2.Db.ConnectionDb;

public class ConnectionDb {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/springsaceva2db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	static ConnectionDb conn = null;
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	}
	
	public static ConnectionDb getInstance() {//single tone 
		
		if(conn == null) 
			return conn = new ConnectionDb();
		else
			return conn;
		
	}

}
