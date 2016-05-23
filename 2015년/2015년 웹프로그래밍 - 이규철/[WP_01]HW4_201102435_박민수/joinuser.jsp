<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8" />
<title>회원 가입</title>
</head>
<body>
	<form action="makeUser.jsp" method="POST">
		I D : <input type="text" name="id" required/><br /> 
		P W : <input type="password" name="password" required/><br /> 
		<input type="submit" value="가입" />
	</form>
</body>
</html>