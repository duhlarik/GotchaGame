package com.gc.gotchagame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value = "privacy policy", method = RequestMethod.GET)
	public String processPrivacyPolicy(HttpServletRequest request, Model model) {
		
		return "privacypolicy";

	}	
	
	@RequestMapping(value = "redirectPage", method = RequestMethod.GET)
	public String processRedirectPage(HttpServletRequest request, Model model) {
		
		return "redirectPage";

	}	
	
	@RequestMapping(value = "playerdashboard", method = RequestMethod.GET)
	public String processSuccessfulLogin(HttpServletRequest request, Model model) {
		
		return "playerdashboard";

	}	
	
	@RequestMapping(value = "GameInvitations", method = RequestMethod.GET)
	public String processGameInvitationsClick(HttpServletRequest request, Model model) {
		
		return "InvitedPlayers";

	}	
	
	
	@RequestMapping(value = "GameOverview", method = RequestMethod.GET)
	public String processGameOverviewClick(HttpServletRequest request, Model model) {
		
		return "GameOverview";

	}	
	
	@RequestMapping(value = "Assignments", method = RequestMethod.GET)
	public String processActiveGameClick(HttpServletRequest request, Model model) {
		
		return "ActiveGames";

	}	
	
	@RequestMapping(value = "StartGameGameMakerPage", method = RequestMethod.GET)
	public String processStartGameClick(HttpServletRequest request, Model model) {
		
		return "StartGame";

	}	
	
	
	
	
	@RequestMapping(value = "GotchaGamesCreateGame", method = RequestMethod.GET)
	public String CreateGamePage(HttpServletRequest request, Model model) {
	
		
		return "GotchaGamesCreateGame";

	}	
	
	@RequestMapping(value = "gamecreation", method = RequestMethod.GET)
	public String processAssignment(HttpServletRequest request, Model model)
	
	{
		try {
		String gameName = request.getParameter("gamename");
		String startDate = request.getParameter("startdate");
		String endDate = request.getParameter("enddate");
		String player1 = request.getParameter("player1");
		String player2 = request.getParameter("player2");
		
		String[] ar = {player1, player2};
		model.addAttribute("invitedPlayers", ar);
		model.addAttribute("gameName", gameName);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		Class.forName("com.mysql.jdbc.Driver"); //the connection is an example of the factory design pattern
		
		Connection conn = DriverManager.getConnection
		("jdbc:mysql://localhost:3306/GameTestPlayerName", "root", "Farfel83!");
		
		String query1 = "INSERT INTO GameTable"
				+ "(GameName, StartDate,EndDate) VALUES"
				+ "(?, ?, ?)";
		
		java.sql.PreparedStatement updateGame = conn.prepareStatement(query1);
		updateGame.setString (1, gameName);
		updateGame.setString (2, startDate);
		updateGame.setString (3, endDate);

		updateGame.execute();
		
		String query = "INSERT INTO PlayerTable1"
				+ "(PlayerNumber, UserId, GameName) VALUES"
				+ "(?,?,?)";
				
		for (int i = 0; i< ar.length; i++)
		{
		java.sql.PreparedStatement updateInvitedPlayer = conn.prepareStatement(query);
		updateInvitedPlayer.setInt (1,  i+1);
		updateInvitedPlayer.setString (2,  ar[i]);
		updateInvitedPlayer.setString (3,  gameName);
		
		      updateInvitedPlayer.execute();
		} 
		}
		catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
	
		return "InvitedPlayers";
	
	}
	
	@RequestMapping(value = "addPlayer", method = RequestMethod.GET)
	public String playersAccepted(HttpServletRequest request, Model model)
	
	{
		try {
		
		String userName1 = request.getParameter("player1");
		String userName2 = request.getParameter("player2");
		
		String[] ar = {userName1, userName2};
		model.addAttribute("message", ar);
		
		
		Class.forName("com.mysql.jdbc.Driver"); //the connection is an example of the factory design pattern
		
		Connection conn = DriverManager.getConnection
		("jdbc:mysql://localhost:3306/GameTestPlayerName", "root", "Farfel83!");
		
		
		String query1 = "UPDATE PlayerTable1 SET PlayerStatus='active' WHERE PlayerNumber=?";

		
		for (int i = 0; i< ar.length; i++)
		{
	
		
		
		java.sql.PreparedStatement updatePlayerStatus = conn.prepareStatement(query1);
		
		updatePlayerStatus.setInt (1, i+1);
		//updatePlayerStatus.setInt(2, i+1);
		
	      updatePlayerStatus.execute();
		
		
		}
		
		}
		catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
	
		return "StartGame";
	
	}
	
	@RequestMapping(value = "StartGame", method = RequestMethod.GET)
	public String StartGameAssignments(HttpServletRequest request, Model model)
	
	{
		/*try {
		
		String userName1 = request.getParameter("player1");
		String userName2 = request.getParameter("player2");
		
		String[] ar = {userName1, userName2};
		model.addAttribute("message", ar);
		
		
		Class.forName("com.mysql.jdbc.Driver"); //the connection is an example of the factory design pattern
		
		Connection conn = DriverManager.getConnection
		("jdbc:mysql://localhost:3306/GameTestPlayerName", "root", "Farfel83!");
		
		
		String query1 = "UPDATE PlayerTable1 SET PlayerStatus='active' WHERE PlayerNumber=?";

		
		for (int i = 0; i< ar.length; i++)
		{
	
		
		
		java.sql.PreparedStatement updatePlayerStatus = conn.prepareStatement(query1);
		
		updatePlayerStatus.setInt (1, i+1);
		//updatePlayerStatus.setInt(2, i+1);
		
	      updatePlayerStatus.execute();
		
		
		}
		
		}
		catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
	*/
		return "Gotcha";
	
	}
	
}
