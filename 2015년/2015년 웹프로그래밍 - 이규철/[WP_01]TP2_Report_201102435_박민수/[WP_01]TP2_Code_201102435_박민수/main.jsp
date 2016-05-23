<%@page import="java.io.FileNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.io.*" %>
<% request.setCharacterEncoding("euc-kr");%>	

<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8" />
<title>My Player</title>
<link type="text/css" rel="stylesheet" href="main.css">
</head>
<body>
	<header><img id = "banner" src="banner.png"></header><br>
	<form action="Login.jsp" method="POST">
		I D : <input type="text" name="id" required/><br /> 
		P W : <input type="password" name="password" required><br> 
		<input type="submit" value="로그인">
	</form>
	
	<form action="joinuser.jsp" method="POST">
		<input type="submit" value="회원가입">
	</form>
	<footer>
		<p>&copy; 2015 Wep Programming 201102435 Park Min Soo. All rights reserved.</p>
	</footer>
</body>
</html>