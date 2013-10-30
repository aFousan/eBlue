<%-- 
    Document   : join
    Created on : Oct 29, 2013, 11:56:30 PM
    Author     : aFousan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="aFousan">
        <link rel="shortcut icon" href="static/images/ico/favicon.png">
        <title>Join Page</title>
        <link rel="stylesheet" href="static/css/bootstrap.css">
    </head>
    <body>
        <div class="container">

            <form class="form-signin" action="join" method="POST">
                <h2 class="form-signin-heading">Register</h2>
                <input type="text" name="name" class="form-control" placeholder="User Name" autofocus>
                <input type="text" name="pass1" class="form-control" placeholder="Email" >
                <input type="text" name="pass2" class="form-control" placeholder="Email Verify" >
                <input type="password" name="pass" class="form-control" placeholder="Password" >
                <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
                Already have an Account <a href="login">Sign in</a>
            </form>

        </div> 
    </body>
</html>
