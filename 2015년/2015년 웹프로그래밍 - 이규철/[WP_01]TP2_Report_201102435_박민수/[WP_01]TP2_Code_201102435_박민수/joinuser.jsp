<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8" />
<title>My Player</title>
<link type="text/css" rel="stylesheet" href="joinuser.css">
</head>
<header><img id = "banner" src="banner.png"></header><br>
<body>
	<form action="makeUser.jsp" method="POST">
		I D : <input type="text" name="id" required/><br /> 
		P W : <input type="password" name="password" required/><br /> 
		<input type="submit" value="가입" />
	</form>
</body>
<footer>
		<p>&copy; 2015 Wep Programming 201102435 Park Min Soo. All rights reserved.</p>
</footer>
</html>