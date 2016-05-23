<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.*,java.util.*"%>

<%
	request.setCharacterEncoding("UTF-8");
	String AddCate = request.getParameter("AddCate");
	String sessionId = (String)session.getAttribute("id");
	PrintWriter writer = null;
	String Path = application.getRealPath("/user/" + sessionId +"/list/"+ AddCate);
	File file = new File(Path);
	writer = new PrintWriter(Path);
	writer.close();
%>
<script>
	alert("Add the Category");
	location.href ="Login.jsp";
</script>