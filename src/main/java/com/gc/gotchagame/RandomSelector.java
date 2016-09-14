package com.gc.gotchagame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class RandomSelector {

	public static int RandomSelector() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gotchaGame", "gotchaGame",
					"Il0vethi$g@me!");
			String numOfItems = "SELECT COUNT(*) FROM Locations";
			Statement selectStatement = cnn.createStatement();
			ResultSet result = selectStatement.executeQuery(numOfItems);
			result.next();
			int x = result.getInt(0);

			Random randNum = new Random();

			int n = randNum.nextInt(x) + 1;

			return n;
		} catch (Exception e) {
			return -1;
		}
	}
}
