package com.gc.gotchagame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class TestLocationsArray {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gotchagametestdata", "root", "admin");

		Statement selectStatement = cnn.createStatement();

		ArrayList<String> locationsArray = new ArrayList<String>();

		String locationsQuery = "select Locations from location;";
		ResultSet locationSet = selectStatement.executeQuery(locationsQuery);

		ArrayList<String> lctn = new ArrayList<String>();
		while (locationSet.next()) {

			String location = locationSet.getString("Locations");
			lctn.add(location);
		}
		Collections.shuffle(lctn);
		int x = CountOfPlayers.countPlayers();
		for (int i = 0; i < x; i++) {
			locationsArray.add(lctn.get(i));
		}
		
		for (int i = 0; i < x; i++) {
			String query = "UPDATE playertable1 SET Location=?;";
			java.sql.PreparedStatement addAssignmentToPlayersTable = cnn.prepareStatement(query);
			//addAssignmentToPlayersTable.setString(1, target.get(i));
			addAssignmentToPlayersTable.setString(1, locationsArray.get(i));
			//addAssignmentToPlayersTable.setString(3, itemsArray.get(i));
			//addAssignmentToPlayersTable.setString(2, userIDArray.get(i));
			addAssignmentToPlayersTable.executeUpdate();
			System.out.println(addAssignmentToPlayersTable);
		}
	}
}
