package com.gc.gotchagame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class StartGame {

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gotchagametestdata", "root",
					"admin");

			Statement selectStatement = cnn.createStatement();

			ArrayList<String> itemsArray = new ArrayList<String>();
			ArrayList<String> locationsArray = new ArrayList<String>();
			ArrayList<String> userIDArray = new ArrayList<String>();
			ArrayList<String> target = new ArrayList<String>();

			int x = CountOfPlayers.countPlayers();
			int i = 0;

			String locationsQuery = "select Locations from location;";
			ResultSet locationSet = selectStatement.executeQuery(locationsQuery);

			ArrayList<String> lctn = new ArrayList<String>();
			while (locationSet.next()) {

				String location = locationSet.getString("Locations");
				lctn.add(location);
			}
			Collections.shuffle(lctn);

			for (i = 0; i < x; i++) {
				locationsArray.add(lctn.get(i));
			}

			String itemsQuery = "select Items from items;";
			ResultSet itemSet = selectStatement.executeQuery(itemsQuery);

			ArrayList<String> itm = new ArrayList<String>();
			while (itemSet.next()) {

				String item = itemSet.getString("Items");
				itm.add(item);
			}
			Collections.shuffle(itm);

			for (i = 0; i < x; i++) {
				itemsArray.add(itm.get(i));
			}

			String playerQuery = "select UserId from playertable1 where PlayerStatus = 'active'";
			ResultSet playerNames = selectStatement.executeQuery(playerQuery);

			while (playerNames.next()) {

				String playerName = playerNames.getString("UserId");
				userIDArray.add(playerName);
			}

			for (i = 0; i < x; i++) {
				target.add(userIDArray.get(i));
			}

			boolean arraysAreDifferent = false;

			Collections.shuffle(target);
			
			while (arraysAreDifferent == false) {
				for (i = 0; i < x; i++) {
					if (userIDArray.get(i).equalsIgnoreCase(target.get(i))) {
						Collections.shuffle(target);
						break;
					}
				}
				if (i == x)
					arraysAreDifferent = true;
			}
			while (arraysAreDifferent = true) {

				for (i = 0; i < x; i++) {
					String query = "UPDATE playertable1 SET Target=?, Location=?, Item = ? WHERE UserId=?";
					java.sql.PreparedStatement addAssignmentToPlayersTable = cnn.prepareStatement(query);
					addAssignmentToPlayersTable.setString(1, target.get(i));
					addAssignmentToPlayersTable.setString(2, locationsArray.get(i));
					addAssignmentToPlayersTable.setString(3, itemsArray.get(i));
					addAssignmentToPlayersTable.setString(4, userIDArray.get(i));
					addAssignmentToPlayersTable.execute();
				}
			}
		} catch (Exception e) {
			
		}
	}
}
