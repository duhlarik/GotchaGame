package com.gc.gotchagame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class TestItemsArray {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gotchagametestdata", "root",
				"admin");

		Statement selectStatement = cnn.createStatement();

		ArrayList<String> itemsArray = new ArrayList<String>();
		
		int x = CountOfPlayers.countPlayers();
		
		String itemsQuery = "select Items from items;";
		ResultSet itemSet = selectStatement.executeQuery(itemsQuery);

		ArrayList<String> itm = new ArrayList<String>();
		while (itemSet.next()) {

			String item = itemSet.getString("Items");
			itm.add(item);
		}
		Collections.shuffle(itm);
		
		for (int i = 0; i < x; i++) {
			itemsArray.add(itm.get(i));
		}
		System.out.println(itemsArray);
	}
}
