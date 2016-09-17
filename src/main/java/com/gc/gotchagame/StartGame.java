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

			String playerQuery = "select userID from playertable1 where PlayerStatus = 'active'";

			ArrayList<String> itemsArray = new ArrayList<String>();
			ArrayList<String> locationsArray = new ArrayList<String>();
			ArrayList<String> userIDArray = new ArrayList<String>();
			ArrayList<String> target = new ArrayList<String>();

			ResultSet playerNames = selectStatement.executeQuery(playerQuery);

			int x = CountOfPlayers.countPlayers();

			while (playerNames.next()) {

				String playerName = playerNames.getString("userID");
				userIDArray.add(playerName);

				target = userIDArray;
			}

			String locationsQuery = "select Locations from location order by rand() limit'" + x + "'";

			ResultSet locationSet = selectStatement.executeQuery(locationsQuery);

			while (locationSet.next()) {

				String location = locationSet.getString("Locations");
				locationsArray.add(location);
			}

			String itemsQuery = "select Items from items order by rand() limit'" + x + "'";

			ResultSet itemSet = selectStatement.executeQuery(itemsQuery);

			while (itemSet.next()) {

				String item = itemSet.getString("Items");
				itemsArray.add(item);
			}

			boolean targetMatchesPlayer = true;

			while (targetMatchesPlayer = true) {
				Collections.shuffle(target);
				
				for (int i = 0; i < x; i++) {
					if (userIDArray.get(i).equalsIgnoreCase(target.get(i))) {
						targetMatchesPlayer = true;
						Collections.shuffle(target);
					} else {
						targetMatchesPlayer = false;
					}
				}
			}
			while (targetMatchesPlayer = false) {

				for (int i = 0; i < x; i++) {
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
