package com.example.Saceva2.SpringScheduler.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.slf4j.Logger;

public class ReadFromDb {

	public HashMap<String, String> readFromConfig(Connection connection, Logger log) throws SQLException {

		HashMap<String, String> conf = new HashMap<String, String>();

		String query = "SELECT * FROM TAB_CONFIG";
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		try {
			selectStatement = connection.prepareStatement(query);
			rs = selectStatement.executeQuery();
			while (rs.next()) {// error not if but while che è meglio xD
				conf.put(rs.getString("chiave".toUpperCase()), rs.getString("valore".toUpperCase()));// populate the array with the data found in the tab
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (rs != null)
				rs.close();
			if (selectStatement != null)
				selectStatement.close();
		}
		System.out.println("Data extratc from tab_config :\n" + conf);
		return conf;
	}

}
