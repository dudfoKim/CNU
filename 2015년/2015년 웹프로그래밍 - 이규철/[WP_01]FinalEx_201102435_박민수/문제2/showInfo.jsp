<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.io.FileNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.*"%>

<% request.setCharacterEncoding("euc-kr");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ajax실습</title>
</head>
<body>
	<%
	String user = request.getParameter("userCheck");
	String userSex = "";
	BufferedReader reader = null;
	String directory = "./";
	File dirFile = new File(directory);//폴더
	File []fileList=dirFile.listFiles();//폴더 리스트 뽑기
	JSONArray returndata = new JSONArray();
	JSONObject json = null;
	PrintWriter pw = response.getWriter();
 	try{
 		for(File tempFile : fileList) {
			if(tempFile.isFile() &&  tempFile.getName().endsWith(".txt")){//확장자 확인.
				String tempFileName = tempFile.getName();
				json = new JSONObject();
			    reader = new BufferedReader(new FileReader(directory+"/"+tempFileName));
			    
			    //전 과제 계속해서 함.
			    String area = reader.readLine();
			 	String temp = reader.readLine();
			 	String dust = reader.readLine();
			 	String rain = reader.readLine();
			 	
			 	//전 과제 계속해서 함.
			 	//json에 key, value로 셋팅
			 	json.put("area", area);
			 	json.put("temp",temp);
			 	json.put("dust",dust);
			 	json.put("rain", rain);
				//여기까지가 끝인가보오
			 	
			 	returndata.add(json);//json array에 넣기
		  	}
		}
		pw.print(returndata);
		pw.flush();
		pw.close();
 	} catch( Exception e ){
 		e.printStackTrace();
 	} finally{
 		try{
 			if( reader != null ) reader.close();
 		}catch( Exception e1 ){
 			e1.printStackTrace();
 		}
 	}
		
%>

</body>
</html>
