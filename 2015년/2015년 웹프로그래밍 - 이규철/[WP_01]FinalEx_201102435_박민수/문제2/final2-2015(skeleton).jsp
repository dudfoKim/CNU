<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="jquery.js"></script>
	<script src="jquery-1.11.3.min.js"></script>
	<script src="ajax.js"></script>
	<title>2015 Web Programming Final Exam Q#2 :skeleton</title>	
</head>
<body>
		<form action="dbWeather.jsp" method="POST">
		Please provide data to save information of region's weather : 
		<br>
		Region's name : <input name ="Region"type="text"><br>
		Temperature  : <input name ="Temperature" type="text"><br>
		Microdust  : <input name ="Microdust"type="text" ><br>
		Rainfall probability : <input name ="Rainfall"type="text" ><br>
		<input type="submit"/>	
		</form>


<br><br>
	<form action="showInfo.jsp" method="POST">
	Please provide the information to retrieve <br>
	Region's name :  <input type="text" name="searchRegion"><br>
	<input type="checkbox" name="checkTemp"> Temperature 
	<input type="checkbox" name="checkDust"> Microdust 
	<input type="checkbox" name="checkRain"> Rainfall probability <br>	
	<input type="button" value ="Search" id = "checkButton"/> 	
	</form>
<br><br>
	<div id = "result">
	</div>
<hr>

 </body>
<html>