
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	request.setCharacterEncoding("EUC-KR");
	String ct = "0";
	if(request.getParameter("id") != null){
		String id = request.getParameter("id");
		session.setAttribute("id",id);
	}
	String sessionId = (String)session.getAttribute("id");
	
	if(request.getParameter("password") != null){
		String password = request.getParameter("password");
		session.setAttribute("password",password);
	}
	String sessionPWD = (String)session.getAttribute("password");
	
	PrintWriter writer = response.getWriter();
	String filePath = application.getRealPath("/user/" + sessionId +"/" + sessionId
			+ ".txt");
	session.setAttribute("cnt",ct);
	String count = (String)session.getAttribute("cnt");
	int parse_count = Integer.parseInt(count);
	
	
	File file = new File(filePath);
    if (!file.exists()) // 아이디가 존재하는지 체크하여 존재하지않으면 alert창을 띄워주고 이전 화면으로 돌아간다.
    {
    	writer.println("<script type='text/javascript'>");
        writer.println("alert('해당 아이디가 존재하지 않습니다.');");
        writer.println("history.back();");
        writer.println("</script>");
        writer.flush();
        return;
    }
    else{
    	FileReader fr;
    	fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		String source= br.readLine();
		
		if(!source.equals(sessionPWD))//파일속에 있는 비밀번호와 체크해서 일치하지않으면 alert창을 띄워주고 이전화면으로 돌아간다.
		{
			writer.println("<script type='text/javascript'>");
	        writer.println("alert('비밀번호가 틀렸습니다.');");
	        writer.println("history.back();");
	        writer.println("</script>");
	        writer.flush();
	        return;
		}
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>My Player</title>
<link type="text/css" rel="stylesheet" href="login.css">
<script>
	function allowDrop(ev) {
    ev.preventDefault();
	}
	function drag(ev) {
		ev.dataTransfer.setData("text", ev.target.id);
	}
	function drop(ev) {
		ev.preventDefault();
		// palySection에 드랍되면 iframe태크를 생성하여 드래그해온 주소를 셋해준다.
		
		var data = ev.dataTransfer.getData("text");
		var src = document.getElementById(data);
		var subject = src.innerHTML;
		var subjectSection = document.getElementById("Subject");
		var subjectP = document.createElement("P");
		subjectP.innerHTML = subject;
		
		if(ev.target.childElementCount == 1)			//놓으려는 곳에 동영상 존재하면 드래그해온 리스트의 동영상주소로 바꿔줌.
		{
			ev.target.childNodes[3].src = src.id+"?rel=0&autoplay=1";
			subjectSection.childNodes[1].innerHTML = subject;
			return;
		}
		var video = document.createElement("iframe");
			video.style.width = "560px";
			video.style.height = "315px";
			video.style.frameborder = "0";
			video.setAttribute("allowfullscreen","true");
		var data = ev.dataTransfer.getData("text");
		var src = document.getElementById(data);
			video.src = src.id+"?rel=0&autoplay=1";
			video.setAttribute("ondrop","drop(event)");
			video.setAttribute("ondragover","allowDrop(event)");
		ev.target.appendChild(video);
		subjectSection.appendChild(subjectP);
	}
	//리스트에 추가하는 함수
	function AddList(){
		var listSection = document.getElementById("ListSection");
		var saveList = document.getElementById("saveList");
		
		var VideoName = document.getElementById("VideoName");
		var VideoSrc = document.getElementById("Src");
		var AddListElements = document.createElement("li");
		
		if(VideoName.value == "")
		{
			alert("제목을 입력해주세요.");
			return;
		}
		if(VideoSrc.value == "")
		{
			alert("주소를 입력해주세요.");
			return;
		}
		AddListElements.innerHTML = VideoName.value
		AddListElements.id = VideoSrc.value;
		AddListElements.setAttribute("draggable","true");
		AddListElements.setAttribute("ondragstart","drag(event)");
		listSection.appendChild(AddListElements);
		VideoName.value= " ";
		VideoSrc.value= " ";
		saveList.value = listSection.innerHTML;
	}
	//리스트에서 선택한 리스트를 제거하는 함수. 추후 구현할 예정.
	function DeleteList(ev){
		ev.preventDefault();
		var data = ev.dataTransfer.getData("text");					// 드래그된 박스를
		ev.target.appendChild(document.getElementById(data));		// 휴지통에 자식으로 붙인뒤
		ev.target.removeChild(ev.target.childNodes[0]);				// 삭제해버린다.
		var ListSection = document.getElementById("ListSection");
		var saveList = document.getElementById("saveList");
		saveList.value = ListSection.innerHTML;
	}
	
	window.onload = function MyList(){
		<%
			String loadList = application.getRealPath("/user/" + sessionId +"/list");
			File LoList = new File(loadList);
			String Lists[] = LoList.list();
		%>
		var myList = document.getElementById("MyLists");
		<%
		for(int i = 0; i<Lists.length ; i++){
		%>
			var newP = document.createElement("li");
			newP.innerHTML = "<%=Lists[i]%>";
			newP.id = <%=i%>;
			myList.appendChild(newP);
		<%
		}
		%>
	}
	function LoadList(){
		
		var VideoInfo = document.getElementById("VideoInfo");
		var ListSection = document.getElementById("ListSection");
		var HiddenList = document.getElementById("SelectList");
		var saveList = document.getElementById("saveList");
		var SelectList = document.getElementById("List");
		var myList = document.getElementById("MyLists");
		var saveBu = document.getElementById("saveBu");
		var z = "null";
		if(SelectList.value == ""){
			alert("불러올 리스트를 입력하세요.");
			return;
		}
		<%
		for(int i = 0; i<Lists.length ; i++){
		%>
			if(SelectList.value == "<%=Lists[i]%>"){
				<%
					String selList = application.getRealPath("/user/" + sessionId +"/list/"+Lists[i]);
					FileReader selfr;
					selfr = new FileReader(selList);
					BufferedReader selbr = new BufferedReader(selfr);
					String sel_innerHTML = selbr.readLine();
				%>
				if(z != '<%=sel_innerHTML%>'){
					ListSection.innerHTML = '<%=sel_innerHTML%>';
					
				}
				else{
					ListSection.innerHTML = "";
				}
				HiddenList.value = SelectList.value;
				saveList.value = ListSection.innerHTML;
			}
		<%
		}
		%>
		
		VideoInfo.style.display = "block";
		ListSection.style.display = "block";
		saveBu.style.display = "block";
		SelectList.disabled  = true;
	}
	function anotherList(){
		var SelectList = document.getElementById("List");
		var VideoInfo = document.getElementById("VideoInfo");
		var ListSection = document.getElementById("ListSection");
		var saveBu = document.getElementById("saveBu");
		SelectList.disabled  = false;
		VideoInfo.style.display = "none";
		ListSection.style.display = "none";
		saveBu.style.display = "none";
	}
	function nextVideo(){
		var ListSection = document.getElementById("ListSection");
		var subjectSection = document.getElementById("Subject").childNodes[1];
		var playSection = document.getElementById("playSection").childNodes[3];
		for (var i =0 ; i<ListSection.childNodes.length; i++)
		{
			if(ListSection.childNodes[i].innerHTML == subjectSection.innerHTML)
			{
				if(i != ListSection.childNodes.length-1){
					playSection.src = ListSection.childNodes[i+1].id+"?rel=0&autoplay=1";
					subjectSection.innerHTML = ListSection.childNodes[i+1].innerHTML
					break;
				}
				else{
					alert("리스트의 마지막 입니다.");
					return;
				}
				
			}
		}
	}
	function preVideo(){
		var ListSection = document.getElementById("ListSection");
		var subjectSection = document.getElementById("Subject").childNodes[1];
		var playSection = document.getElementById("playSection").childNodes[3];
		for (var i =ListSection.childNodes.length-1 ; i>=0; i--)
		{
			if(ListSection.childNodes[i].innerHTML == subjectSection.innerHTML)
			{
				if(i != 0){
					playSection.src = ListSection.childNodes[i-1].id+"?rel=0&autoplay=1";
					subjectSection.innerHTML = ListSection.childNodes[i-1].innerHTML
					break;
				}
				else
				{
					alert("리스트의 처음 입니다.");
					return;
				}
			}
		}
	}
</script>
</head>
<body>
	<header><img id = "banner" src="banner.png"></header><br>
	<form id="logout"action="logout.jsp" method="POST">
		<p id="text1"><%=sessionId %> is Login <input type="submit" value="LogOut" /></p>
	</form>
	<br><br>
	<section id = "frame">
		<section id ="playSection" ondrop="drop(event)" ondragover="allowDrop(event)">
			<!-- 이 구역에 유튜브 동영상이 재생된다.-->
		</section>
		<section id = "Subject">
		</section>
		<img id = "next" src="preList.jpg" onclick ="preVideo()">
		<img id = "next" src="nextList.png" onclick ="nextVideo()">
		<img id = "trash" src="delete-300x300.jpg" ondrop="DeleteList(event)" ondragover="allowDrop(event)">
	</section>
	<section id= "TheList">
	<!-- 이 구역에 유튜브 동영상주소를 적어서 추가하면 리스트가 추가된다.-->
	
	<div id="LoadList">
	<h1>My Lists</h1> <br><p id = "MyLists"></P>
	<form action ="AddCategory.jsp" method="POST">
	<span class="span">Select List</span> <input type="text" name = "AddCate"id="List"  required> <input type="button" value ="LoadList" onclick="LoadList()"> <input type="submit" value ="AddList">
	</form>
	</div>
	<div id="VideoInfo">
	<span class="span">Subjcet</span> <input type="text" id="VideoName" required> <br>
	<span class="span">Address</span> <input type="text" id="Src" required> <br>	
	<input type="button" value ="Add" onclick="AddList()"> 
	<input type="button" value ="SelectAnotherList" onclick="anotherList()"> 
	</div>
	<div id= "ListSection">

	</div >
		<form action="SaveList.jsp" method="POST">
		<input type ="textarea" name="saveList" id="saveList">
		<input type ="textarea" name="SelectList" id="SelectList">
		<input id ="saveBu"type="submit" value ="SaveList"> 
	</form>
	</section>
	<footer>
		<p>&copy; 2015 Wep Programming 201102435 Park Min Soo. All rights reserved.</p>
	</footer>
</body>
</html>
