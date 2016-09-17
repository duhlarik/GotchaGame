<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<html>



<head>
   <title>Gotcha Game</title>

    <meta name="Gotcha Game" content="An interactive game where players eliminate other players by getting them to hold a specified item at a specified location.  Users will be able to get their spy on and take out targets.  Instagram integration is used to create a running record of the games events.  Are you ready to get or be got?">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

	<style>

body
{background-image: url("http://www.recettes-de-cuisines.com/wp-content/plugins/wp-o-matic/cache/7b51d_photodune-2487584-white-wood-background-m.jpg")


}

header, footer {exam

    padding: .1em;
    color: black;
    background-color:  white;
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
</style>
</head>
<body>


<header>

<h1>Gotcha!</h1>
<h3>Get or Be Got</h3>


</header>
<ul>
      <li><a href="GotchaGameDashboard.html">Game Dashboard</a></li>
  <li><a href="GotchaGameOverview.html">Game Overview</a></li>
  <li><a href="GotchaGameGames.HTML">Games</a></li>
   <li><a href="GotchaGamesCreateGame.Html">Create Game</a></li> 


</ul>
<p>
Last Updated: September 2016


GotchaGame Privacy Policy

GotchaGame collects, stores and uses your personal information to provide and develop GotchaGame’s products, mobile applications, services and websites (together “Services”) according to this privacy policy (“Privacy Policy”).

For questions and information about our Privacy Policy and data subject's rights, please contact us at privacy@gotchagame.com.

For information about our privacy practices related to data from children, please see “Special Note About Children’s Privacy” below.

All GotchaGame Services are governed by this Privacy Policy and by using or accessing a Service, you give consent to the processing, use and disclosure of your data. Please do not install or use the Services if you do not agree to this Privacy Policy. 

GotchaGame reserves the right to modify this Privacy Policy. Your continued use of Services will signify your acceptance of the changes to this Privacy Policy.


1. How We Collect Your Data

Likely situations when you make personal data available to GotchaGame include, but are not limited to: (i) by using GotchaGame’s mobile apps or visiting our websites, (ii) registration for Services, contests and special events; (iii) accessing Services using a third party ID, such as social networking sites or gaming services; (iv) using “tell a friend,” "email this page," or other similar features; (v) requesting technical support; and (vi) otherwise through use of GotchaGame Services where personal data is required for use and/or participation.


2. Type Of Data We Collect

The data we process on you may include, but is not limited to: your Instagram user ID, email address, device ID, IP-address and your Instagram media as identified by the hashtag identifier for a single GotchaGame. This media may be visible to other GotchaGame players from your single GotchaGame for up to 72 hours from the end date of the game.


3. Location Data

To the extent GotchaGame makes location enabled Services available and you use such Services, GotchaGame may collect and process your location data for internal reporting purposes. Location data is processed and stored only for the duration that is required for the provision of the location related Services.

GotchaGame may use, depending on the service (1)IP-based location based on the IP address presented by the end-user, (2) fine geo-location data based on coordinates obtained from a mobile device’s GPS radio, or (3) coarse, network-based geo-location data based on proximity of network towers or the location of WiFi networks. 

Your fine, GPS-based geo-location is not accessed without your consent. Notwithstanding GotchaGame’s partners who are providing location related parts of the Service, GotchaGame will not share your GPS geo-location information.


4. Analytics

GotchaGame uses your information to perform its own analytics and to enable analytics provided by third parties.  We use analytical information for supporting business analysis and operations, business intelligence, product development, improving products and services and personalizing content. 


5. Friends

If you choose to use GotchaGame’s "tell a friend" or a similar service about GotchaGame Services GotchaGame may store your friend's name and contact details that you provide to GotchaGame.

Please note that certain features of the Services may be able to connect to your social networking sites to obtain additional information about you. In such cases, GotchaGame may be able to collect certain information from your social networking profile when your social networking site permits it, and when you consent to allow your social networking site to make that information available to GotchaGame. This information may include, but is not limited to, your name, profile picture, gender, user ID, email address, your country, your language, your time zone, the organizations and links on your profile page, the names and profile pictures of your social networking site “friends” and other information you have included in your social networking site profile. GotchaGame may associate and/or combine as well as use information collected by GotchaGame and/or obtained through such social networking sites.


6. Data Retention and Correctness

GotchaGame retains the collected data for the period necessary to fulfill the purposes outlined in this Privacy Policy unless a longer retention period is required or permitted by law. Thereafter GotchaGame deletes all aforementioned data in its possession within a reasonable timeframe. GotchaGame does not verify the correctness of personal data.

Please note that some data may be further retained if necessary to resolve disputes, enforce GotchaGame user agreements, and comply with technical and legal requirements and constraints related to the security, integrity and operation of Services.


7. Third Party Terms and Conditions

Please note that your access to and use of the Services may be subject to certain third party terms and conditions and GotchaGame is not liable for any such third parties’ use of your personal data.

Certain Services that GotchaGame may offer, such as multiplayer gaming, social networking and gaming console services, may use third party services to provide authentication for the Services with a gaming console ID, social networking ID, or gaming network account. In connection with such use, certain personally identifiable user and/or membership data may be transferred automatically to and from GotchaGame. 


8. Safeguards

GotchaGame follows generally accepted industry standards and maintains reasonable safeguards to attempt to ensure the security, integrity and privacy of the information in GotchaGame’s possession.


9. Other

You may choose to disclose data about yourself in the course of contributing user generated content to the Services.

Any data that you disclose in any of these forums, blogs, chats or the like will become public information, and there is no expectation of privacy or confidentiality with respect to that data.


10. Special Note about Children’s Privacy

GotchaGame does not knowingly collect personal information from children under 13 years of age. If GotchaGame learns that GotchaGame has inadvertently gathered personal data from children under 13 years of age, GotchaGame will take reasonable measures to promptly delete such personal data from GotchaGame’s records.   

GotchaGame understands the need to provide extra privacy protections to users who are children. To that end, features and services such as social networking plug-ins and collection of data for the purpose of building user profiles are not available to users under 13. 

GotchaGame and its partners do collect non-personally identifiable data from children. This includes, for example, collection of IP-based geolocation data to ensure that the product, service or features served comply with applicable laws of that nation.

GotchaGame and its partners also collect data to assess general information about the use of products, such as the number of visitors to our sites and services and which sections of a site or service are popular. GotchaGame also uses this data to allow users to personalize their experience on the site or service (for example, to save game statistics). 

If you have additional questions about GotchaGame’s Privacy Practices related to children under the age of 13, please contact us at privacy@GotchaGame.com.


11. Further information

GotchaGame makes good faith efforts to enable you to review, update or correct your personal data in GotchaGame’s possession. 

GotchaGame will need sufficient information from you to establish your identity and to verify your access request, and also to assist us in handling your request.

By clicking “Submit” I agree that:

I have read and accepted the User Agreement and Privacy Policy.
I am at least 18 years old.


</p>


</body>

</html>