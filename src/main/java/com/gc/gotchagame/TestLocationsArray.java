package com.gc.gotchagame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestLocationsArray {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gotchagametestdata", "root",
				"admin");

		Statement selectStatement = cnn.createStatement();

		ArrayList<String> locationsArray = new ArrayList<String>();

		int x = CountOfPlayers.countPlayers();
		
		String locationsQuery = "select Locations from location order by rand() limit'" + x + "'";

		ResultSet locationSet = selectStatement.executeQuery(locationsQuery);

		while (locationSet.next()) {

			String location = locationSet.getString("Locations");
			locationsArray.add(location);
		}
		System.out.println(locationsArray);
	}

}
