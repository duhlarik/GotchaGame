package com.gc.gotchagame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestTargetArray {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gotchagametestdata", "root",
				"admin");

		Statement selectStatement = cnn.createStatement();

		String playerQuery = "select userID from playertable1 where PlayerStatus = 'active'";

		ArrayList<String> userIDArray = new ArrayList<String>();
		ArrayList<String> target = new ArrayList<String>();

		ResultSet playerNames = selectStatement.executeQuery(playerQuery);

		int x = CountOfPlayers.countPlayers();

		while (playerNames.next()) {

			String playerName = playerNames.getString("userID");
			userIDArray.add(playerName);

			target = userIDArray;
			
		}
		System.out.println(target);
	}

}
