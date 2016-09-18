package com.gc.gotchagame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class TestTargetArray {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gotchagametestdata", "root", "admin");

		Statement selectStatement = cnn.createStatement();

		String playerQuery = "select UserId from playertable1 where PlayerStatus = 'active'";

		ArrayList<String> userIDArray = new ArrayList<String>();
		ArrayList<String> target = new ArrayList<String>();

		ResultSet playerNames = selectStatement.executeQuery(playerQuery);

		int x = CountOfPlayers.countPlayers();

		while (playerNames.next()) {

			String playerName = playerNames.getString("UserId");

			userIDArray.add(playerName);
		}
		System.out.println("UserID 1: " + userIDArray);

		for (int i = 0; i < userIDArray.size(); i++) {
			target.add(userIDArray.get(i));
		}

		boolean arraysAreDifferent = false;
		Collections.shuffle(target);
		System.out.println("Target 1: " + target);

		while (arraysAreDifferent == false) {
			for (int i = 0; i < x; i++) {
				if (userIDArray.get(i).equalsIgnoreCase(target.get(i))) {
					Collections.shuffle(target);
					arraysAreDifferent = false;
				} else {
					arraysAreDifferent = true;
				}
			}
		}
		System.out.println("UserID 2: " + userIDArray);
		System.out.println("Target 2: " + target);
		System.out.println(x);

		if (arraysAreDifferent) {

			for (int i = 0; i < x; i++) {
				String query = "UPDATE playertable1 SET Target=? WHERE UserId = ?";
				java.sql.PreparedStatement addAssignmentToPlayersTable = cnn.prepareStatement(query);
				addAssignmentToPlayersTable.setString(1, target.get(i));
				// addAssignmentToPlayersTable.setString(2, locationsArray.get(i));
				// addAssignmentToPlayersTable.setString(3, itemsArray.get(i));
				addAssignmentToPlayersTable.setString(2, userIDArray.get(i));
				addAssignmentToPlayersTable.executeUpdate();
				System.out.println(addAssignmentToPlayersTable);
			}
		}
	}
}
