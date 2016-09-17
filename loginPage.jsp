<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="566268484169-3ghc3irrnbon5o1kabg2i8c07lvvmnrj.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
  </head>
  <body>
    <div class="g-signin2" data-onsuccess="onSignIn"></div>
    <script>
    
    //source: https://developers.google.com/identity/sign-in/web/sign-in
      function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        console.log("UserName: " + profile.getUserName()); // Don't send this directly to your server!
        console.log('FirstName: ' + profile.getName());
        console.log('LastName: ' + profile.getLastName());
        //console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());

        // The ID token to pass to back end:
        var id_token = googleUser.getAuthResponse().id_token;
        console.log("ID Token: " + id_token);
        
     //auth2 is initialized with gapi.auth2.init() and a user is signed in.
        if (auth2.isSignedIn.get()) {
          var profile = auth2.currentUser.get().getBasicProfile();
          console.log('UserName: ' + profile.getUserName());
          cconsole.log('FirstName: ' + profile.getName());
          console.log('LastName: ' + profile.getLastName());
          //console.log('Image URL: ' + profile.getImageUrl());
          console.log('Email: ' + profile.getEmail());
        }
      };
    </script>
  </body>
</html>






