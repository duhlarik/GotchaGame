<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>



<head>
<title>Gotcha Game</title>

<meta name="Gotcha Game"
	content="An interactive game where players eliminate other players by getting them to hold a specified item at a specified location.  Users will be able to get their spy on and take out targets.  Instagram integration is used to create a running record of the games events.  Are you ready to get or be got?">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<style>
body {
	background-image:
		url("http://www.recettes-de-cuisines.com/wp-content/plugins/wp-o-matic/cache/7b51d_photodune-2487584-white-wood-background-m.jpg")
}

header,footer {
	exam padding: .1em;
	color: black;
	background-color: white;
	clear: left;
	text-align: left;
	font-family: "courier new";
	font-size: 25px;
}

p {
	color: black;
	text-align: center;
	font-family: "courier new";
	font-size: 30px;
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-family: "courier new";
}

li a:hover {
	background-color: #111;
}

form {
	text-align: center;
}
</style>
</head>
<body>


	<header>

		<h1>Gotcha!</h1>
		<h3>Get or Be Got</h3>


	</header>


	<br>
	<br>
	<br>
	<br>
	<p>
		<b>Gotcha: Got or be Got.</b> <br> Players are assigned a target
		(another player), an item, and a location. <br> Get points by
		getting your target to hold the specified item at the specified
		location and posting the picture. <br> Win the game by being the
		last player standing. <br>

	</p>
	<p>
	<form action="playerdashboard" method="get">
		<br> username <input type="text" align="center" name="username">
		<br> password <input type="text" align="center" name="password">
		<br> <input type="submit" align="center" value="login">

	</form>
	<p>
		 <b>New User? Click below to create an
			account.</b>
			
			<form action="newuser copy" method="get">
		
		<br><input type="submit" align="center" value="New User">
</p>
	</form>	
	<p>
	 <b> Click below to read our privacy policy.</b>
			
			<form action="privacy policy" method="get">
		
		<br><input type="submit" align="center" value="Privacy Policy">
</p>
	</form>	
		</form>	
	<p>
	 <b> If you do not agree to Instagram's Login Policy, please click below.</b>
			
			<form action="redirectPage" method="get">
		
		<br><input type="submit" align="center" value="Login Policy">
</p>
	</form>	
	


	</p>


</body>

</html>