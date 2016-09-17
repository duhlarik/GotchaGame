package com.gc.gotchagame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
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
//confirmed working, this returns privacy policy
	return "privacypolicy";

	}

@RequestMapping(value = "redirectPage", method = RequestMethod.GET)
public String processRedirectPage(HttpServletRequest request, Model model) {

	return "redirectPage";
	}





@RequestMapping(value = "playerdashboard", method = RequestMethod.GET)
public String processSuccessfulLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
//user will go to login, enter username, and password and press login (with playerdashboard value).  if successful, we will go to playerdashboard
//we will dump username into UserNameTable as UserName
	try {
	String userNameSession = request.getParameter("username");
			model.addAttribute("username", userNameSession);
	
	HttpSession session = request.getSession();
	session.setAttribute("userNameSession", userNameSession);
	
	Class.forName("com.mysql.jdbc.Driver"); // the connection is an
												// example of the factory
													// design pattern

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
					"Farfel83!");

			String query1 = "INSERT INTO UserNameTable" + "(UserName) VALUES"
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

//	@RequestMapping(value = "PlayerInviteComplete", method = RequestMethod.GET)
//	public String processFinishedInvitingPlayersClick(
//			HttpServletRequest request, Model model) {
//
//		return "StartGame";
//
//	}
//
	@RequestMapping(value = "GameOverview", method = RequestMethod.GET)
	public String processGameOverviewClick(HttpServletRequest request,
			Model model) {
//navigation bar click on Game Overview and go to game overview page
		return "GameOverview";

	}
//
//	@RequestMapping(value = "Assignments", method = RequestMethod.GET)
//	public String processActiveGameClick(HttpServletRequest request, Model model) {
//
//		return "ActiveGames";
//
//	}
//
//	@RequestMapping(value = "StartGameGameMakerPage", method = RequestMethod.GET)
//	public String processStartGameClick(HttpServletRequest request, Model model) {
//
//		return "StartGame";
//
//	}
//
@RequestMapping(value = "GotchaGamesCreateGame", method = RequestMethod.GET)
	public String CreateGamePage(HttpServletRequest request, Model model) {
//	navigation bar, brings to CreateGamePage
		return "GotchaGamesCreateGame";

	}

@RequestMapping(value = "gamecreation", method = RequestMethod.GET)
public String processAssignment(HttpServletRequest request, HttpServletResponse response, Model model)
{//This is our game creation page.  Once the user clicks create game, enters gamename and date information.  This will get stored in gametable.
	//we also want to add the gamemaker into the player table at this point, but code needs work.  Once submitted, user will go to inviteplayerpage.
	try {
		HttpSession session = request.getSession();
		String gamename = request.getParameter("gamename");
		session.setAttribute("gamename", gamename);
		String startDate = request.getParameter("startdate");
		String endDate = request.getParameter("enddate");
		
		String userNameSession = (String) session.getAttribute("userNameSession");
		//String gameMakerUserName = request.getParameter("gamecreatedby");
		//Object value = request.getSession().getAttribute("username");
		//String gameMakerUserName = (String) value;
		model.addAttribute("gamename", gamename);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
	
	Class.forName("com.mysql.jdbc.Driver"); // the connection is an/													// example of the factory
													// design pattern

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
					"Farfel83!");

		String query1 = "INSERT INTO GameTable1"
					+ "(GameName,StartDate,EndDate,GameMakerUserName) VALUES"
					+ "(?,?,?,?)";

		java.sql.PreparedStatement updateGame = conn
					.prepareStatement(query1);
			updateGame.setString(1, gamename);
			updateGame.setString(2, startDate);
			updateGame.setString(3, endDate);
			updateGame.setString(4, userNameSession);

			updateGame.execute();
			
			
			query1 = "INSERT INTO PlayerTable1"
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
public String playersAccepted(HttpServletRequest request, HttpServletResponse response, Model model)

	{
		try {

			HttpSession session = request.getSession();
			String userNameSession = (String) session.getAttribute("userNameSession");
			Class.forName("com.mysql.jdbc.Driver");
												

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
					"Farfel83!");

			String query1 = "UPDATE PlayerTable1 SET PlayerStatus='active' WHERE UserId=?";



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
public String AddPlayersToTable(HttpServletRequest request, HttpServletResponse response, Model model) { 
	//The gamemaker has created a new game.  They will now invite players	
	//successfully added to players table
	try {

String player1 = request.getParameter("player1");
String player2 = request.getParameter("player2");
HttpSession session1 =request.getSession();
String gamename = (String)session1.getAttribute("gamename");
model.addAttribute("gamename", gamename);
String[] ar = { player1, player2 };
model.addAttribute("invitedPlayers", ar);

		Class.forName("com.mysql.jdbc.Driver"); 

		Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
					"Farfel83!");

			String query1 = "INSERT INTO PlayerTable1"
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
public String StartGameAssignments(HttpServletRequest request, HttpServletResponse response, Model model)
//Game maker will press the start game button and this will trigger assignments.
{	
	
	try {

		HttpSession session1 =request.getSession();
		String gamename = (String)session1.getAttribute("gamename");
			Class.forName("com.mysql.jdbc.Driver"); 
													

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/GameTestPlayerName", "root",
					"Farfel83!");

			String query1 = "UPDATE GameTable1 SET GameStatus='active' WHERE GameName=?";

	

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
