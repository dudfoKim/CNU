<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.*,java.util.*"%>

<%
	request.setCharacterEncoding("EUC-KR");
	String saveList = request.getParameter("saveList");
	String SelectList = request.getParameter("SelectList");
	String sessionId = (String)session.getAttribute("id");
	PrintWriter writer = null;
	String Path = application.getRealPath("/user/" + sessionId +"/list/"+ SelectList);
	File file = new File(Path);
	writer = new PrintWriter(Path);
	writer.println(saveList);
	writer.close();
%>
<script>
	alert("Save the list");
	location.href ="Login.jsp";
</script>