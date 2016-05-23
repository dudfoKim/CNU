<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.io.*,java.util.*"%>
<% 
	String id = (String)session.getAttribute("id");
	String password = (String)session.getAttribute("password");
	String balance = (String)session.getAttribute("balance");
	int balance_parseInt = Integer.parseInt(balance);
	String movie1 = (String)session.getAttribute("movie1");
	int movie1_parseInt = Integer.parseInt(movie1);
	String movie2 = (String)session.getAttribute("movie2");
	int movie2_parseInt = Integer.parseInt(movie1);

	String filePathId = application.getRealPath("./" + id
			+ ".txt");
	String filePathMv1 = application.getRealPath("./movie1.txt");
	String filePathMv2 = application.getRealPath("./movie2.txt");
	PrintWriter writer = null;

	String mv1_cnt = request.getParameter("mv1_cnt");
	String mv2_cnt = request.getParameter("mv2_cnt");
	int mv1_cnt_parseint = Integer.parseInt(mv1_cnt);
	int mv2_cnt_parseint = Integer.parseInt(mv2_cnt);
	if(mv1_cnt_parseint==0 && mv2_cnt_parseint==0){
		writer =response.getWriter();
		writer.println("<script type='text/javascript'>");
        writer.println("alert('예매 수를 입력하세요.');");
        writer.println("history.back();");
        writer.println("</script>");
        writer.flush();
        return;
	}
	movie1_parseInt += mv1_cnt_parseint;
	movie2_parseInt += mv2_cnt_parseint;
	
	int sum_cnt = 5000*(mv1_cnt_parseint+mv2_cnt_parseint);
	balance_parseInt -= sum_cnt;
	if(balance_parseInt < 0){
		writer =response.getWriter();
		writer.println("<script type='text/javascript'>");
        writer.println("alert('포인트가 부족합니다.');");
        writer.println("history.back();");
        writer.println("</script>");
        writer.flush();
        return;
	}
	
	String sessionMv1_seat = (String)session.getAttribute("mv1_seat");
	int sessionMv1_seat_parseint = Integer.parseInt(sessionMv1_seat);
	sessionMv1_seat_parseint -= mv1_cnt_parseint;
	
	String sessionMv2_seat = (String)session.getAttribute("mv2_seat");
	int sessionMv2_seat_parseint = Integer.parseInt(sessionMv2_seat);
	sessionMv2_seat_parseint -= mv2_cnt_parseint;
	
	if(sessionMv1_seat_parseint < 0 || sessionMv2_seat_parseint < 0){
		writer =response.getWriter();
		writer.println("<script type='text/javascript'>");
        writer.println("alert('좌석이 부족합니다.');");
        writer.println("history.back();");
        writer.println("</script>");
        writer.flush();
        return;
	}
	
	writer = new PrintWriter(filePathMv1);
	writer.println(sessionMv1_seat_parseint);
	writer.close();
	
	writer = new PrintWriter(filePathMv2);
	writer.println(sessionMv2_seat_parseint);
	writer.close();
	
	writer = new PrintWriter(filePathId);
	writer.println(password);
	writer.println(balance_parseInt);
	writer.println(movie1_parseInt);
	writer.println(movie2_parseInt);
	writer.close();
%>

<script type="text/javascript">
	alert('좌석이 예매되었습니다..');
	location.href ="Login.jsp";
</script>
