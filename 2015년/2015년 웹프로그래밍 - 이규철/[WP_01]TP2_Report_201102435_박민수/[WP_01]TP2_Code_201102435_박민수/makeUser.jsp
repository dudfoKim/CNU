<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	String result = null;
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	PrintWriter writer = null;
	String DPath = application.getRealPath("/user/" + id);
	File file2 = new File(DPath);
	if( file2.exists() == false )
	{
		file2.mkdirs();
	} 
	
	String DPath2 = application.getRealPath("/user/" + id +"/list");
	File file3 = new File(DPath2);
	if( file3.exists() == false )
	{
		file3.mkdirs();
	} 
	
	String filePath = application.getRealPath("/user/" + id +"/" + id + ".txt");

	File file = new File(filePath);
    if (file.exists())
    	result = "FAIL";
    else{
	
	writer = new PrintWriter(filePath);
	writer.println(password);
	result = "SUCCESS";
	writer.close();
    }
%>

<script type="text/javascript">
	var result = "<%=result%>";
	if(result == "SUCCESS")
	{
		alert("가입완료");
		location.href ="main.jsp";
	}
	else if(result == "FAIL"){
		alert("아이디가 중복됩니다.");
		history.back();
	}

</script>
