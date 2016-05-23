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
	
	if(movie1_parseInt == 0 && movie2_parseInt ==0){
		writer =response.getWriter();
		writer.println("<script type='text/javascript'>");
        writer.println("alert('예매내역이 없습니다.');");
        writer.println("history.back();");
        writer.println("</script>");
        writer.flush();
        return;
	}
	
%>




<script>
		location.href ="cancel.jsp";
</script>
