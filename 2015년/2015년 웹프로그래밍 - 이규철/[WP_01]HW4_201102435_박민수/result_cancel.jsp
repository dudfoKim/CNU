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
	
	String filePathMv1 = application.getRealPath("./movie1.txt");	
	String filePathMv2 = application.getRealPath("./movie2.txt");
	String filePathId = application.getRealPath("./" + id
			+ ".txt");
	PrintWriter writer = null;
	
	FileReader fr;
	fr = new FileReader(filePathMv1);
	BufferedReader br = new BufferedReader(fr);
	String mv1_seat= br.readLine();
	int mv1_seat_parseInt = Integer.parseInt(mv1_seat);
	
	FileReader fr2;
	fr2 = new FileReader(filePathMv2);
	BufferedReader br2 = new BufferedReader(fr2);
	String mv2_seat= br2.readLine();
	int mv2_seat_parseInt = Integer.parseInt(mv2_seat);
	
	String check1 = (String) request.getParameter("check1");
	String check2 = (String) request.getParameter("check2");
	String check3 = (String) request.getParameter("check3");
	String check4 = (String) request.getParameter("check4");
	
	if(check1 != null){
		mv1_seat_parseInt += movie1_parseInt;
		balance_parseInt += movie1_parseInt*5000;
		movie1_parseInt = 0;
	}
	if(check2 != null){
		mv2_seat_parseInt += movie2_parseInt;
		balance_parseInt += movie2_parseInt*5000;
		movie2_parseInt = 0;
	}
	if(check3 != null){
		mv1_seat_parseInt += movie1_parseInt;
		balance_parseInt += movie1_parseInt*5000;
		movie2_parseInt = 0;
	}
	if(check4 != null){
		mv2_seat_parseInt += movie2_parseInt;
		balance_parseInt += movie2_parseInt*5000;
		movie2_parseInt = 0;
	}
	
	writer = new PrintWriter(filePathMv1);
	writer.println(mv1_seat_parseInt);
	writer.close();
	
	writer = new PrintWriter(filePathMv2);
	writer.println(mv2_seat_parseInt);
	writer.close();
	
	writer = new PrintWriter(filePathId);
	writer.println(password);
	writer.println(balance_parseInt);
	writer.println(movie1_parseInt);
	writer.println(movie2_parseInt);
	writer.close();
	
%>

<script>
	alert('좌석이 취소되었습니다..');
	location.href ="Login.jsp";
</script>
