<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	
	PrintWriter writer = null;
	String id = (String)session.getAttribute("id");
	String password = (String)session.getAttribute("password");
	String balance = (String)session.getAttribute("balance");
	String movie1 = (String)session.getAttribute("movie1");
	String movie2 = (String)session.getAttribute("movie2");
	int b = Integer.parseInt(balance);
	int addPoint = Integer.parseInt(request.getParameter("Point"));
	String filePath = application.getRealPath("./" + id
			+ ".txt");

	b += addPoint;
	
	
	writer = new PrintWriter(filePath);
	writer.println(password);
	writer.println(b);
	writer.println(movie1);
	writer.println(movie2);
	writer.close();
%>

<script type="text/javascript">
	alert('포인트가 충전되었습니다..');
	location.href ="Login.jsp";
</script>
