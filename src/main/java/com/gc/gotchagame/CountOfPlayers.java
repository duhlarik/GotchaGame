package com.gc.gotchagame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

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
		Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gotchaGame", "gotchaGame",
				"Il0vethi$g@me!");
		String numOfItems = "SELECT COUNT(*) FROM PlayerTable1";
		Statement selectStatement = cnn.createStatement();
		ResultSet result = selectStatement.executeQuery(numOfItems);
		result.next();
		int x = result.getInt(0);
		return x;
	}
}
