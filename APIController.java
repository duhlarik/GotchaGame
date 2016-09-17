package com.gc.APIController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class APIController {

	//private static final Logger logger = LoggerFactory.getLogger(APIController.class);

	// @RequestMapping(value ={"home", "/"}, method = RequestMethod.GET)
	// public String home(Locale locale, Model model) {
	// logger.info("Welcome home! The client locale is {}.", locale);
	//
	// Date date = new Date();
	// DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
	// DateFormat.LONG, locale);
	//
	// String formattedDate = dateFormat.format(date);
	//
	// model.addAttribute("serverTime", formattedDate);
	//
	// return "home";
	// }

	@RequestMapping(value = "about", method = RequestMethod.GET)
	public String about(Model model) {

		return "about";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Model model) {

		return "logout";

	}

	@RequestMapping(value = "loginPage", method = RequestMethod.GET)
	public String loginPage(Model model) {

		return "loginPage";

	}
}

/*
 * //gather user login information and send to mySQL Database
 * 
 * @RequestMapping(value = "loginInfo", method = RequestMethod.GET) public
 * String processLogin(HttpServletRequest request, Model model) { try { String
 * UserName = request.getParameter("UserName"); String FirstName =
 * request.getParameter("FirstName"); String LastName =
 * request.getParameter("LastName"); String Email =
 * request.getParameter("Email");
 * 
 * String[] playerData; model.addAttribute("UserName", UserName);
 * model.addAttribute("FirstName", FirstName); model.addAttribute("LastName",
 * LastName); model.addAttribute("Email", Email);
 * 
 * Class.forName("com.mysql.jdbc.Driver"); //the connection is an example of the
 * factory design pattern
 * 
 * //initiate connection to mySQL database Connection conn =
 * DriverManager.getConnection
 * ("jdbc:mysql://localhost:3306/GameTestPlayerName", "root", "Farfel83!");
 * 
 * String query1 = "INSERT INTO userData" +
 * "(UserName, FirstName, LastName) VALUES" + "(?, ?, ?)";
 * 
 * java.sql.PreparedStatement updatePlayerData = conn.prepareStatement(query1);
 * updatePlayerData.setString (1, UserName); updatePlayerData.setString (2,
 * FirstName); updatePlayerData.setString (3, LastName);
 * 
 * updatePlayerData.execute();
 * 
 * String query = "INSERT INTO PlayerData" +
 * "(PlayerNumber, UserId, GameName) VALUES" + "(?,?,?)"; } catch (Exception e)
 * { System.err.println("Got an exception!");
 * System.err.println(e.getMessage()); }
 * 
 * return "loginPage";
 * 
 * } }
 */