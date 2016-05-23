<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	String id = (String) session.getAttribute("id");
	String password = (String) session.getAttribute("password");
	String balance = (String) session.getAttribute("balance");
	int balance_parseInt = Integer.parseInt(balance);
	String movie1 = (String) session.getAttribute("movie1");
	int movie1_parseInt = Integer.parseInt(movie1);
	String movie2 = (String) session.getAttribute("movie2");
	int movie2_parseInt = Integer.parseInt(movie2);
	PrintWriter writer = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="logout.jsp" method="POST">
		<%=id %>님이 로그인 <input type="submit" value="로그아웃" />
	</form>
	<form action="result_cancel.jsp" method="POST">
	<table border="1px solid" id="table_all" width ="320px">
		<tr>
			<td colspan="5">A 영화관</td>
		</tr>

		<tr>
			<td>상영관</td>
			<td>영화</td>
			<td>상영시간</td>
			<td>예매수</td>
			<td>취소</td>
		</tr>

		<tr>
			<td>1관</td>
			<td>매드맥스</td>
			<td>13:00~15:00</td>
			<td><%=movie1_parseInt %></td>
			<td><input type="checkbox" name="check1"></td>
		</tr>

		<tr>
			<td>2관</td>
			<td>어벤져스2</td>
			<td>17:00~19:30</td>
			<td><%=movie2_parseInt %></td>
			<td><input type="checkbox" name="check2"></td>
		</tr>
	</table>
	<table border="1px solid" id="table1" width ="320px">
		<tr>
			<td colspan="5">A 영화관</td>
		</tr>

		<tr>
			<td>상영관</td>
			<td>영화</td>
			<td>상영시간</td>
			<td>예매수</td>
			<td>취소</td>
		</tr>

		<tr>
			<td>1관</td>
			<td>매드맥스</td>
			<td>13:00~15:00</td>
			<td><%=movie1_parseInt %></td>
			<td><input type="checkbox" name="check3"></td>
		</tr>
	</table>
	<table border="1px solid" id="table2" width ="320px">
		<tr>
			<td colspan="5">A 영화관</td>
		</tr>

		<tr>
			<td>상영관</td>
			<td>영화</td>
			<td>상영시간</td>
			<td>예매수</td>
			<td>취소</td>
		</tr>
		<tr>
			<td>2관</td>
			<td>어벤져스2</td>
			<td>17:00~19:30</td>
			<td><%=movie2_parseInt %></td>
			<td><input type="checkbox" name="check4"></td>
		</tr>
	</table>
	<input type="submit" value="예매취소" />
	</form>
	<form action="Login.jsp" method="POST">
		<input type="submit" value="예매하러가기" />
	</form>
	<script>
	var table_all = document.getElementById("table_all");
	var table1 = document.getElementById("table1");
	var table2 = document.getElementById("table2");
	
	if(<%=movie1_parseInt%>!=0 && <%=movie2_parseInt%>!=0){
		table_all.style.display = "block";
		table1.style.display = "none";
		table2.style.display = "none";
	}
	else if(<%=movie1_parseInt%>!=0 && <%=movie2_parseInt%>==0){
		table_all.style.display = "none";
		table1.style.display = "block";
		table2.style.display = "none";
	}
	else if(<%=movie1_parseInt%>==0 && <%=movie2_parseInt%>!=0){
		table_all.style.display = "none";
		table1.style.display = "none";
		table2.style.display = "block";
	}
	</script>
</body>
</html>