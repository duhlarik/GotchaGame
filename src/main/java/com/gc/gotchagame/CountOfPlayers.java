package com.gc.gotchagame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CountOfPlayers {

	public static int countOfPlayers() {

		try {
			return countPlayers();
		} catch (Exception e) {
			return -1;
		} 
	}

	public static int countPlayers() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gotchagametestdata", "root",
				"admin");
		String numOfPlayers = "SELECT COUNT(*) FROM playertable1 WHERE PlayerStatus = 'active'";
		Statement selectStatement = cnn.createStatement();
		ResultSet result = selectStatement.executeQuery(numOfPlayers);
		result.next();
		int x = result.getInt(1);
		return x;
	}
}
