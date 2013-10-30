<%-- 
    Document   : login
    Created on : Oct 29, 2013, 5:04:00 PM
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
        <title>Sign In</title>
        <link rel="stylesheet" href="static/css/bootstrap.css">
    </head>
    <body>
        <div class="container">

            <form action="login" class="form-signin">
                <h2 class="form-signin-heading">Sign in</h2>
                <input type="text" name="user" class="form-control" placeholder="Email address" autofocus>
                <input type="password" name="pass" class="form-control" placeholder="Password">
                <label class="checkbox">
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                Don't have an Account <a href="join">Register</a>
            </form>

        </div>
    </body>
</html>
