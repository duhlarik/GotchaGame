package com.gc.gotchagame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.SessionScope;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "privacy policy", method = RequestMethod.GET)
	public String processPrivacyPolicy(HttpServletRequest request, Model model) {
		// confirmed working, this returns privacy policy
		return "privacypolicy";
	}

	@RequestMapping(value = "redirectPage", method = RequestMethod.GET)
	public String processRedirectPage(HttpServletRequest request, Model model) {

		return "redirectPage";
	}

	@RequestMapping(value = "playerdashboard", method = RequestMethod.GET)
	public String processSuccessfulLogin(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// user will go to login, enter username, and password and press login
		// (with playerdashboard value). if successful, we will go to
		// playerdashboard
		// we will dump username into UserNameTable as UserName
		try {
			String userNameSession = request.getParameter("username");
			model.addAttribute("username", userNameSession);

			HttpSession session = request.getSession();
			session.setAttribute("userNameSession", userNameSession);

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
					"admin");

			String query1 = "INSERT INTO usernametable" + "(UserName) VALUES"
					+ "(?)";

			java.sql.PreparedStatement updateGame = conn
					.prepareStatement(query1);
			updateGame.setString(1, userNameSession);
			updateGame.execute();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}

		return "playerdashboard";
	}

	@RequestMapping(value = "GameMakerInvitePlayers", method = RequestMethod.GET)
	public String InvitePlayersClick(HttpServletRequest request, Model model) {

		return "GameMakerInvitePlayers";
	}

	@RequestMapping(value = "GameInvitations", method = RequestMethod.GET)
	public String processGameInvitationsClick(HttpServletRequest request,
			Model model) {

		return "GameInvitations";
	}

	@RequestMapping(value = "ActiveGameShowAssignment", method = RequestMethod.GET)
	public String playerClicksonActiveGames(HttpServletRequest request, HttpServletResponse response, Model model) {
		// when player clicks on active game, this page will show an assignment
		// for the specific user if the PlayerStatus is active, GameStatus is active.
		
		{					  
			try
			{//Step 1: game name associated with userNameSession
				HttpSession session1 = request.getSession();
				String userNameSession = (String) session1.getAttribute("userNameSession");
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
						"admin");
				String query = "SELECT GameName FROM PlayerTable1 WHERE UserId=?";
			
				java.sql.PreparedStatement ps = conn.prepareStatement(query);
			    ps.setString(1, userNameSession);
			    System.out.println(ps);
			    
			    // process the results
			    ResultSet rs = ps.executeQuery();
			   
			    String gameName= " ";
			   
			    while ( rs.next() )
			    {
			    	 gameName = rs.getString(1);

			    }
			  
			    model.addAttribute("gameName", gameName);
				
				
				//Step 2: game status of game name associated with userNameSession
			
			
				query = "SELECT GameStatus FROM GameTable1 WHERE GameName=?";
			
				java.sql.PreparedStatement ps2 = conn.prepareStatement(query);
			    ps2.setString(1, gameName);
			    System.out.println(ps2);
			    
			    // process the results
			    ResultSet rs2 = ps2.executeQuery();
			   
			    String gameStatus= " ";
			   
			    while ( rs2.next() )
			    {
			    	 gameStatus = rs2.getString(1);			    
			
			    }
			    System.out.println(gameStatus);
			    model.addAttribute("gameStatus", gameStatus);
			  
			    query = "SELECT PlayerStatus FROM PlayerTable1 WHERE UserId=?";
				
				java.sql.PreparedStatement ps3 = conn.prepareStatement(query);
			    ps3.setString(1, userNameSession);
			    System.out.println(ps3);
			    
			    // process the results
			    ResultSet rs3 = ps3.executeQuery();
			   
			    String playerStatus= " ";
			    
			    while ( rs3.next() )
			    {
			    	 playerStatus = rs3.getString(1);
			
			    	  
			    }
			  
			    System.out.println(playerStatus);
				 
			    model.addAttribute("playerStatus", playerStatus);
			  
		
			
			if ((gameStatus.equalsIgnoreCase("active")) && (playerStatus.equalsIgnoreCase("active")))
					{
				// Target, Item, Location
			query = "SELECT* FROM PlayerTable1 WHERE UserId=?";
		
			java.sql.PreparedStatement ps4 = conn.prepareStatement(query);
			ps4.setString(1, userNameSession);
		    System.out.println(ps4);
		   
					ResultSet rs4 = ps4.executeQuery(query);
					String target = "";
					String location = "";
					String item = "";
					while (rs4.next()) {
			
						target = rs4.getString("Target");
						location = rs4.getString("Location");
						item = rs4.getString("Item");
					}

						model.addAttribute("target", target);
						model.addAttribute("location", location);
						model.addAttribute("item", item);
					
		
					System.out.println(target);
					
					
					}

				else {
					String messageNoAssignment = "You have no current games";
					model.addAttribute("nullmessage", messageNoAssignment);
					
				}
			}

			 catch (Exception e) {
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
			}

			return "ActiveGames";
		}
		}

	@RequestMapping(value = "GameOverview", method = RequestMethod.GET)
	public String processGameOverviewClick(HttpServletRequest request,
			Model model) {
		// navigation bar click on Game Overview and go to game overview page
		return "GameOverview";
	}

	// @RequestMapping(value = "StartGameGameMakerPage", method =
	// RequestMethod.GET)
	// public String processStartGameClick(HttpServletRequest request, Model
	// model) {

	// return "StartGame";
	// }

	@RequestMapping(value = "GotchaGamesCreateGame", method = RequestMethod.GET)
	public String CreateGamePage(HttpServletRequest request, Model model) {
		// navigation bar, brings to CreateGamePage
		return "GotchaGamesCreateGame";
	}

	@RequestMapping(value = "gamecreation", method = RequestMethod.GET)
	public String processAssignment(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		/*
		 * This is our game creation page. Once the user clicks create game,
		 * enters gamename and date information. This will get stored in
		 * gametable. We also want to add the gamemaker into the player table at
		 * this point, but code needs work. Once submitted, user will go to
		 * inviteplayerpage.
		 */
		try {
			HttpSession session = request.getSession();
			String gamename = request.getParameter("gamename");
			session.setAttribute("gamename", gamename);
			String startDate = request.getParameter("startdate");
			String endDate = request.getParameter("enddate");

			String userNameSession = (String) session
					.getAttribute("userNameSession");
			// String gameMakerUserName = request.getParameter("gamecreatedby");
			// Object value = request.getSession().getAttribute("username");
			// String gameMakerUserName = (String) value;
			model.addAttribute("gamename", gamename);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);

			Class.forName("com.mysql.jdbc.Driver");
			// the connection is an example of the factory design pattern

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gotchagametestdata", "root",
					"admin");

			String query1 = "INSERT INTO gametable1"
					+ "(GameName,StartDate,EndDate,GameMakerUserName) VALUES"
					+ "(?,?,?,?)";

			java.sql.PreparedStatement updateGame = conn
					.prepareStatement(query1);
			updateGame.setString(1, gamename);
			updateGame.setString(2, startDate);
			updateGame.setString(3, endDate);
			updateGame.setString(4, userNameSession);

			updateGame.execute();

			query1 = "INSERT INTO playertable1"
					+ "(PlayerNumber,UserId,GameName) VALUES" + "(?,?,?)";

			java.sql.PreparedStatement addPlayerToPlayersTable = conn
					.prepareStatement(query1);
			addPlayerToPlayersTable.setInt(1, 1);
			addPlayerToPlayersTable.setString(2, userNameSession);
			addPlayerToPlayersTable.setString(3, gamename);
			addPlayerToPlayersTable.execute();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}

		return "InvitePlayersPage";
	}

	@RequestMapping(value = "AcceptGame", method = RequestMethod.GET)
	public String playersAccepted(HttpServletRequest request,
			HttpServletResponse response, Model model)

	{
		try {

			HttpSession session = request.getSession();
			String userNameSession = (String) session
					.getAttribute("userNameSession");
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gotchagametestdata", "root",
					"admin");

			String query1 = "UPDATE playertable1 SET PlayerStatus='active' WHERE UserId=?";

			java.sql.PreparedStatement updatePlayerStatus = conn
					.prepareStatement(query1);

			updatePlayerStatus.setString(1, userNameSession);

			updatePlayerStatus.execute();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}

		return "playerdashboard";
	}

	@RequestMapping(value = "AddPlayerToPlayersTable", method = RequestMethod.GET)
	public String AddPlayersToTable(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		/*
		 * The gamemaker has created a new game. They will now invite players
		 * successfully added to players table
		 */
		try {

			String player1 = request.getParameter("player1");
			String player2 = request.getParameter("player2");
			HttpSession session1 = request.getSession();
			String gamename = (String) session1.getAttribute("gamename");
			model.addAttribute("gamename", gamename);
			String[] ar = { player1, player2 };
			model.addAttribute("invitedPlayers", ar);

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gotchagametestdata", "root",
					"admin");

			String query1 = "INSERT INTO playertable1"
					+ "(PlayerNumber,UserId,GameName) VALUES" + "(?,?,?)";

			for (int i = 0; i < ar.length; i++) {
				java.sql.PreparedStatement addPlayerToPlayersTable = conn
						.prepareStatement(query1);
				addPlayerToPlayersTable.setInt(1, i + 2);
				addPlayerToPlayersTable.setString(2, ar[i]);
				addPlayerToPlayersTable.setString(3, gamename);
				addPlayerToPlayersTable.execute();
			}
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}

		return "StartGame";
	}

	@RequestMapping(value = "StartGame", method = RequestMethod.GET)
	public String StartGameAssignments(HttpServletRequest request,
			HttpServletResponse response, Model model)
	/*
	 * Game maker will press the start game button and this will trigger
	 * assignments. Need to add the logic to trigger assignments for players
	 * that are attached to game in active status
	 */
	{

		try {

			HttpSession session1 = request.getSession();
			String gamename = (String) session1.getAttribute("gamename");
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
					"Farfel83!");

			String query1 = "UPDATE gametable1 SET GameStatus='active' WHERE GameName=?";

			java.sql.PreparedStatement updateGameStatus = conn
					.prepareStatement(query1);

			updateGameStatus.setString(1, gamename);

			updateGameStatus.execute();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}

		return "playerdashboard";
	}
}
