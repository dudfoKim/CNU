<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	String Region = request.getParameter("Region");
	String Temperature = request.getParameter("Temperature");
	String Microdust = request.getParameter("Microdust");
	String Rainfall = request.getParameter("Rainfall");
	String result = null;
	PrintWriter writer = null;
	String filePath = application.getRealPath("./" + Region
				+ ".txt");
	File file = new File(filePath);
    if (file.exists())
    	result = "FAIL";
    else{
	writer = new PrintWriter(filePath);
	writer.println("<p id = area> "+ Region +" weather is </p>");
	writer.println("<p id = temp> "+ Temperature +" temperature </p>");
	writer.println("<p id = dust> "+ Microdust +" microdust </p>");
	writer.println("<p id = rain> "+ Rainfall +" rainful probability </p>");
	result = "SUCCESS";
	writer.close();
    }
%>
<script type="text/javascript">
	var result = "<%=result%>";
	if(result == "SUCCESS")
	{
		alert("save wheather infomation in sever");
		location.href ="final2-2015(skeleton).jsp";
	}
	else if(result == "FAIL"){
		alert("Fail");
		history.back();
	}

</script>