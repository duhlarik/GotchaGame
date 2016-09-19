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
				model.addAttribute("username", userNameSession);
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
	
				query = "SELECT Target FROM PlayerTable1 WHERE UserId=?";
				
				java.sql.PreparedStatement ps4 = conn.prepareStatement(query);
			    ps4.setString(1, userNameSession);
			    System.out.println(ps4);
			    
			    // process the results
			    ResultSet rs4 = ps4.executeQuery();
			   
			    String target= " ";
			   
			    while ( rs4.next() )
			    {
			    	 target = rs4.getString(1);

			    }
			  
			    model.addAttribute("target", "Target: " + target);
			    System.out.println(target);
				
			    query = "SELECT Item FROM PlayerTable1 WHERE UserId=?";
				
				java.sql.PreparedStatement ps5 = conn.prepareStatement(query);
			    ps5.setString(1, userNameSession);
			    System.out.println(ps5);
			    
			    // process the results
			    ResultSet rs5 = ps5.executeQuery();
			   
			    String item= " ";
			   
			    while ( rs5.next() )
			    {
			    	 item = rs5.getString(1);

			    }
			  
			    model.addAttribute("item", "Item: " + item);
			    System.out.println(item);
			    
			    query = "SELECT Location FROM PlayerTable1 WHERE UserId=?";
				
				java.sql.PreparedStatement ps6 = conn.prepareStatement(query);
			    ps6.setString(1, userNameSession);
			    System.out.println(ps6);
			    
			    // process the results
			    ResultSet rs6 = ps6.executeQuery();
			   
			    String location = " ";
			   
			    while ( rs6.next() )
			    {
			    	 location = rs6.getString(1);

			    }
			  
			    model.addAttribute("location", "Location: " + location);
			    System.out.println(location);
			    
			/*query = "SELECT Target, Item, Location FROM PlayerTable1 WHERE UserId=?";
				
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
					
					*/
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

	@RequestMapping(value = "GotchaShuffleAssignments", method = RequestMethod.GET)
	public String playerClicksonGotchaButton(HttpServletRequest request, HttpServletResponse response, Model model) {
		// when player clicks on gotcha button on active page, this will go to gotcha page
		// multiple steps need to happen
		// Step 1: We will know the username, so we need to extract their target 
		// Step 2: We will set the userid that matches the target to inactive status and ally of usernamesession
		// Step 3: We will check the number of active players
		// Step 4: If 1, winner message, if more than 1, reshuffle
		
		{					  
			try
			{//Step 1: target associated with userNameSession
				HttpSession session1 = request.getSession();
				String userNameSession = (String) session1.getAttribute("userNameSession");
				model.addAttribute("username", userNameSession);
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
						"admin");
				String query = "SELECT Target FROM PlayerTable1 WHERE UserId=?";
			
				java.sql.PreparedStatement ps = conn.prepareStatement(query);
			    ps.setString(1, userNameSession);
			    System.out.println(ps);
			    
			    // process the results
			    ResultSet rs = ps.executeQuery();
			   
			    String targetOfGotcha= " ";
			   
			    while ( rs.next() )
			    {
			    	 targetOfGotcha = rs.getString(1);

			    }
			  
			    model.addAttribute("targetremoved", targetOfGotcha + " has been removed from the game!");
			  
			    System.out.println(targetOfGotcha);
				
			    //Step 2 change playerstatus of targetofgotcha to inactive
			  

				query = "UPDATE playertable1 SET PlayerStatus='inactive' WHERE UserId=?";

				java.sql.PreparedStatement updatePlayerStatus = conn
						.prepareStatement(query);

				updatePlayerStatus.setString(1, targetOfGotcha);

				updatePlayerStatus.execute();
			    System.out.println(updatePlayerStatus);
			    
			    //Step 3: Get GameName
				query = "SELECT GameName FROM PlayerTable1 WHERE UserId=?";
				
				java.sql.PreparedStatement ps2 = conn.prepareStatement(query);
			    ps2.setString(1, userNameSession);
			    System.out.println(ps2);
			    
			    // process the results
			    ResultSet rs2 = ps2.executeQuery();
			   
			    String gameName= " ";
			   
			    while ( rs2.next() )
			    {
			    	 gameName = rs2.getString(1);

			    }
			  
			    model.addAttribute("gameName", gameName);
			    
			    
			    //Step 4: Check to see how many activeplayers are in the game 
			 
			    
			    query = "SELECT COUNT(*) FROM PlayerTable1 WHERE PlayerStatus=? AND GameName=?";
				
				java.sql.PreparedStatement ps3 = conn.prepareStatement(query);
			    ps3.setString(1, "active");
			    ps3.setString(2, gameName);
			    System.out.println(ps3);
			    
			    // process the results
			    ResultSet rs3 = ps3.executeQuery();
			   
			    int numberOfActivePlayers;
			    rs3.next();
			    int x = rs3.getInt(1);
			   
			    System.out.println("Active Players Left in Game" + x );
				
			    if (x>1)
			    {
			    	//We need to reshuffle assignments
			    	 model.addAttribute("messagenewassignments", "Stay Tuned For New Assignments!");
			    	
			    }
			    
			    else
			    {
			    	//If there is only 1 active player less, the person who pressed gotcha is a winner
			    	 model.addAttribute("winner", "We have a winner!");
			    	
			    }
			    
			
			}

			 catch (Exception e) {
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
			}

			return "Gotcha";
		}
		}

	
	
	@RequestMapping(value = "GameOverview", method = RequestMethod.GET)
	public String processGameOverviewClick(HttpServletRequest request,
			Model model) {
		// navigation bar click on Game Overview and go to game overview page
		return "GameOverview";
	}


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
					"jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
					"admin");

			String query1 = "INSERT INTO gametable1"
					+ "(GameName,StartDate,EndDate,GameMaker, GameStatus) VALUES"
					+ "(?,?,?,?,?)";

			java.sql.PreparedStatement updateGame = conn
					.prepareStatement(query1);
			updateGame.setString(1, gamename);
			updateGame.setString(2, startDate);
			updateGame.setString(3, endDate);
			updateGame.setString(4, userNameSession);
			updateGame.setString(5, "inactive");
			updateGame.execute();

			query1 = "INSERT INTO playertable1"
					+ "(PlayerNumber,UserId,GameName,PlayerStatus) VALUES" + "(?,?,?,?)";

			java.sql.PreparedStatement addPlayerToPlayersTable = conn
					.prepareStatement(query1);
			addPlayerToPlayersTable.setInt(1, 1);
			addPlayerToPlayersTable.setString(2, userNameSession);
			addPlayerToPlayersTable.setString(3, gamename);
			addPlayerToPlayersTable.setString(4, "active");
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
			String userNameSession = (String) session.getAttribute("userNameSession");
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
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
					"jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
					"admin");

			String query1 = "INSERT INTO playertable1"
					+ "(PlayerNumber,UserId,GameName,PlayerStatus) VALUES" + "(?,?,?,?)";

			for (int i = 0; i < ar.length; i++) {
				java.sql.PreparedStatement addPlayerToPlayersTable = conn
						.prepareStatement(query1);
				addPlayerToPlayersTable.setInt(1, i + 2);
				addPlayerToPlayersTable.setString(2, ar[i]);
				addPlayerToPlayersTable.setString(3, gamename);
				addPlayerToPlayersTable.setString(4, "inactive");
				addPlayerToPlayersTable.execute();
			}
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}

		return "StartGamePage";
	}
	
	@RequestMapping(value = "StartGamePage", method = RequestMethod.GET)
	public String playerClicksonStartGame(HttpServletRequest request, HttpServletResponse response, Model model) {
	//when player clicks on StartGamePage, this page will show a start Game option ONLY IF
	//the session username is equal to a gamemaker name
	//AND the gamestatus is inactive
	//1. we will first track down the gameName associated with the player
	//2. we will then see who the game maker is and if the game status is active

		{					  
				try
				{//Step 1: game name associated with userNameSession
					
					HttpSession session = request.getSession();
					String userNameSession = (String) session.getAttribute("userNameSession");
					Class.forName("com.mysql.jdbc.Driver");

					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
							"admin");

					
					
					String query = "SELECT GameName FROM PlayerTable1 WHERE UserId = ?";
				
					
					
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
				  
				    model.addAttribute("gametostart", gameName);
				  
				    
				   
			  //we will then see who the game maker is
				   String query1 = "SELECT GameMaker, GameStatus FROM GameTable1 WHERE GameName = ?";
				    java.sql.PreparedStatement ps1 = conn.prepareStatement(query1);
				    ps1.setString(1, gameName);
				    System.out.println(ps1);
				    
				    // process the results
				    ResultSet rs1 = ps1.executeQuery();
				    String gameMaker= " ";
				    String gameStatus= "";
				    //ArrayList <String> test = new ArrayList <> ();  
				    if(rs1.next())
				    {
				    	gameMaker = rs1.getString(1);
				    	gameStatus = rs1.getString(2);
				
				
				    }
				   
				    model.addAttribute("message2", gameMaker);
				    model.addAttribute("message3", gameStatus);
				 //check to see if userID = game maker id and if game is not yet active
				  if (userNameSession.equalsIgnoreCase(gameMaker))
				  {
					  
					  model.addAttribute("StartGame", "Hi Game Maker!  Go Ahead and push the button to start the game!");
				   
				  }	  
				
				}
				catch (Exception e) {
					System.err.println("Got an exception!");
					System.err.println(e.getMessage());
				}
				
					return "StartGamePage";		
						
			
				}
			
	}	  		
				
		
	@RequestMapping(value = "StartGamePageTest", method = RequestMethod.GET)
	public String playerClicksonStartGameInNavigationBar(HttpServletRequest request, HttpServletResponse response, Model model) {
	//when player clicks on StartGamePage, this page will show a start Game option ONLY IF
	//the session username is equal to a gamemaker name
	//AND the gamestatus is inactive
	//1. we will first track down the gameName associated with the player
	//2. we will then see who the game maker is and if the game status is active

		{		String option = "";			  
				try
				{//Step 1: game name associated with userNameSession
					
					HttpSession session = request.getSession();
					String userNameSession = (String) session.getAttribute("userNameSession");
					Class.forName("com.mysql.jdbc.Driver");

					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
							"admin");

					
					
					String query = "SELECT GameName FROM PlayerTable1 WHERE UserId = ?";
				
					
					
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
				  
				    model.addAttribute("gametostart", gameName);
				  
				    
				   
			  //we will then see who the game maker is
				   String query1 = "SELECT GameMaker, GameStatus FROM GameTable1 WHERE GameName = ?";
				    java.sql.PreparedStatement ps1 = conn.prepareStatement(query1);
				    ps1.setString(1, gameName);
				    System.out.println(ps1);
				    
				    // process the results
				    ResultSet rs1 = ps1.executeQuery();
				    String gameMaker= " ";
				    String gameStatus= "";
				    //ArrayList <String> test = new ArrayList <> ();  
				    if(rs1.next())
				    {
				    	gameMaker = rs1.getString(1);
				    	gameStatus = rs1.getString(2);
				
				
				    }
				   
				    model.addAttribute("message2", gameMaker);
				    model.addAttribute("message3", gameStatus);
				 //check to see if userID = game maker id and if game is not yet active
				  if ((userNameSession.equalsIgnoreCase(gameMaker)) && !(gameStatus.equalsIgnoreCase("active")))
				  {
					  
					  model.addAttribute("StartGame", "Hi Game Maker!  Go Ahead and push the button to start the game!");
					 option = "true";
				  }	  
				  
				  else
				  {
					  option = "false";
				  }
				
				}
				catch (Exception e) {
					System.err.println("Got an exception!");
					System.err.println(e.getMessage());
				}
				
					if (option.equalsIgnoreCase("true"))
					{
						return "StartGameOption";
					}
						
					else
					{
					return "NoStartGameOption";	
					}
				}
			
	}	  		
				
		
	@RequestMapping(value = "GameInvitationPage", method = RequestMethod.GET)
	public String playerClicksonGameInvitationPageInNavigationBar(HttpServletRequest request, HttpServletResponse response, Model model) {
	//This method will pull up an accept game option if the player has any game invites and they click on game invitations page
		{		String option = "";			  
				try
				{//Step 1: game name associated with userNameSession
					
					HttpSession session = request.getSession();
					String userNameSession = (String) session.getAttribute("userNameSession");
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println(userNameSession);
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
							"admin");

					
					//Step 2: Get Game Name Associated with user and Player Status
					String query = "SELECT GameName, PlayerStatus FROM PlayerTable1 WHERE UserId = ?";
				
					
					
				    java.sql.PreparedStatement ps = conn.prepareStatement(query);
				    ps.setString(1, userNameSession);
				    System.out.println(ps);
				    
				    // process the results
				    ResultSet rs = ps.executeQuery();
				   
				    String gameName= " ";
				    String playerStatus = " ";
				    while ( rs.next() )
				    {
				    	 gameName = rs.getString(1);
				    	 playerStatus = rs.getString(2);
				    }
				  
				    model.addAttribute("gameName", gameName);
				    model.addAttribute("playerStatus", playerStatus);
				    System.out.println(gameName + "is the gamename associated with this player" );
				    System.out.println(playerStatus + "is the playerStatus");
			  //we will then see what gamestatus is
				   String query1 = "SELECT GameStatus FROM GameTable1 WHERE GameName = ?";
				    java.sql.PreparedStatement ps1 = conn.prepareStatement(query1);
				    ps1.setString(1, gameName);
				    System.out.println(ps1);
				    
				    // process the results
				    ResultSet rs1 = ps1.executeQuery();
				  
				    String gameStatus= "";
				  
				    if(rs1.next())
				    {
				    
				    	gameStatus = rs1.getString(1);
				
				
				    }
				   
				    model.addAttribute("message2", gameStatus);
				    System.out.println("the game status is " + gameStatus);
				 //check to see if userID = game maker id and if game is not yet active
				  if (!(playerStatus.equalsIgnoreCase("active")) && !(gameStatus.equalsIgnoreCase("active")))
				  {
					
					  
					  model.addAttribute("AcceptGame",   "Push the Accept Game Button to Accept Your Invitation to " + gameName + " Game!");
					 option = "true";
				  }	  
				  
				  else
				  {
					  option = "false";
				  }
				
				}
				catch (Exception e) {
					System.err.println("Got an exception!");
					System.err.println(e.getMessage());
				}
				
					if (option.equalsIgnoreCase("true"))
					{
						return "AcceptInviteOption";
					}
						
					else
					{
					return "NoInvitationOption";	
					}
				}
			
	}	  		

	@RequestMapping(value = "StartGameButton", method = RequestMethod.GET)
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
