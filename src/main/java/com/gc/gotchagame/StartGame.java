package com.gc.gotchagame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class StartGame {

	public static void main(String[] args) {

		AssignmentObject ao1 = new AssignmentObject();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gotchaGame", "gotchaGame",
					"Il0vethi$g@me!");
			
			Statement selectStatement = cnn.createStatement();
			
			String playerQuery = "select userID from PlayerTable1 where PlayerStatus = 'active'";
			
			ArrayList<String> itemsArray = new ArrayList<String>();
			ArrayList<String> locationsArray = new ArrayList<String>();
			ArrayList<String> userIDArray = new ArrayList<String>();
			
			ResultSet playerNames = selectStatement.executeQuery(playerQuery);

			int x = CountOfPlayers.countPlayers();
			
			while (playerNames.next()) {

				String playerName = playerNames.getString("userID");
				userIDArray.add(playerName);

				ArrayList<String> target = new ArrayList<String>(userIDArray);
				Collections.shuffle(target);
			}
			
			String locationsQuery = "select Locations from location order by rand() limit'"+ x +"'";
			
			ResultSet lctn = selectStatement.executeQuery(locationsQuery);
			
			while (lctn.next()) {
				
				String location = lctn.getString("Locations");
				locationsArray.add(location);
			}

			String itemsQuery = "select Items from items order by rand() limit'"+ x +"'";
			
			ResultSet itm = selectStatement.executeQuery(itemsQuery);
			
			while (itm.next()) {
				
				String item = itm.getString("Items");
				itemsArray.add(item);
			}
			
			boolean targetMatchesPlayer = true;
			
			ArrayList<
			

		} catch (Exception e) {

		}
	}
}
