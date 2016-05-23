var count =0;			//게시물의 item 갯수를 알기위한 count.
var index;				//입력받은 index값을 받아오는 변수. init에서 초기값을 -1로 주어준다.(index를 안받아오면 -1)

//드롭다운메뉴 보이기
function showMenu(){
	document.getElementById("hiddenMenu").style.display="block";
}
//드롭다운메뉴 숨기기
function hideMenu(){
	document.getElementById("hiddenMenu").style.display="none";
}
//WritingForm & index 초기화 
function init(){				
	document.getElementById("subject").value ="";
	document.getElementById("image").src ="";	
	document.getElementById("inputimage").value ="";
	document.getElementById("textarea").value = "";
	index = -1;
}
//미들바에 추가버튼이 눌려졌을때 WritingForm 띄우기
function addContents(){
	init();
	document.getElementById("submitButton").style.display = "inline";
	document.getElementById("inputimage").style.display = "block";
	document.getElementById("WirtingForm").style.display = "block";
}
//미들바에 Index 지정 추가버튼이 눌려졌을때 WritingForm 띄우기
function IndexAdd(){
	init();
	index = prompt("인덱스 값을 입력하세요(1이상)");

	if(index == 0){
		alert("1이상의 값을 입력하세요.");
		return;
	}
	else if(index-1>count)
	{
		alert("인덱스가 현재 아이템보다 많습니다.");
		return;
	}
	else if(index === "")
	{
		alert("값을 입력해주세요.");
		return;
	}
	else if (index == null){
		return;
	}
	else if(isNaN(index)){
		alert("숫자값을 입력해주세요.");
		return;
	}
	index--;
	document.getElementById("submitButton").style.display = "inline";
	document.getElementById("inputimage").style.display = "block";
	document.getElementById("WirtingForm").style.display = "block";
}
//WritingForm에 등록버튼이 눌렸을때 Contents등록
function submit(){
	var currentNow = new Date();
	var theYear = currentNow.getFullYear();
	var theMonth = currentNow.getMonth()+1;
	var theDate = currentNow.getDate();
	var theHour = currentNow.getHours();
	var theMinutes = currentNow.getMinutes();
	var theSeconds = currentNow.getSeconds();
	
	if(document.getElementById("subject").value === "" ){		//null값이 들어 올 경우 예외처리
		alert("제목을 입력하세요!!");
	}
	else if(document.getElementById('inputimage').files[0] === undefined){	//입력이 들어 오지 않았을 경우
		alert("이미지를 입력하세요!!");
	}
	else if(document.getElementById('textarea').value === "")	//내용이 들어오지 않았을경우
	{
		alert("내용을 입력하세요!!");
	}
	else if (index === -1){				//index값이 입력되지 않았을때.(추가버튼으로 삽입)
		var item = document.getElementById("content");
		var item2 = document.getElementById("sideBar");
		var postWrapper = document.createElement("div");
			postWrapper.id = "item"+count;
			postWrapper.style.border = "thick solid black";
			postWrapper.style.margin = "25px";
		
		//이미지
		var file = document.getElementById("inputimage");
		var cnt=0;

		//제목
		var postSubject = document.createElement("p");
			postSubject.innerHTML = document.getElementById("subject").value;
			postSubject.setAttribute("class","contentsSubject");
		//날짜
		var postDate = document.createElement("p");
			postDate.innerHTML = theYear+"년" +theMonth+"월"+theDate+"일 "+theHour+":"+theMinutes+":"+theSeconds;
		//내용
		var postText = document.createElement("p");
			postText.innerHTML = document.getElementById("textarea").value;
			postText.setAttribute("class","contentsText");
		//삭제버튼
		var deletebutton = document.createElement("button");
			deletebutton.onclick = function (){
				deleteContents(postWrapper,sideBar);
			}
		var deletename = document.createTextNode("게시물 삭제");
			deletebutton.appendChild(deletename);
		//사이드바에 추가.	
		var sideBar = document.createElement("li");
		var link = document.createElement("a");
			link.setAttribute("href","#"+postWrapper.id);
			link.innerHTML = document.getElementById("subject").value;
			sideBar.appendChild(link);
			item2.insertBefore(sideBar,item2.childNodes[count]);
		//textarea(댓글)
		//구역
		var postTextarea = document.createElement("div");
			postTextarea.setAttribute("class","contentsTextarea");
		//textbox
		var postTextbox1 = document.createElement("input");
		var postTextbox2 = document.createElement("textarea");
			postTextbox2.style.margin="1px 1px -5px 1px";
			
		//등록버튼
		var replyButton = document.createElement("button");
			replyButton.onclick = function (){
				addreply(postTextarea,postTextbox1,postTextbox2);
			}
		var replyName = document.createTextNode("댓글 등록");
			replyButton.appendChild(replyName);
		
			postWrapper.setAttribute("class","contents");
			postWrapper.appendChild(postSubject);
			postWrapper.appendChild(postDate);
			while(file.files[cnt])
			{
				var postImg = document.createElement("img");
				postImg.setAttribute("class","contentsImage");
				postImg.src = window.URL.createObjectURL(file.files[cnt]);
				postImg.height="300";
				postImg.width="300";
				postWrapper.appendChild(postImg);
				cnt++;
			}
			postWrapper.appendChild(postText);
			postWrapper.appendChild(postTextarea);
			postWrapper.appendChild(postTextbox1);
			postWrapper.appendChild(postTextbox2);
			postWrapper.appendChild(replyButton);
			postWrapper.appendChild(deletebutton);
			item.insertBefore(postWrapper, item.childNodes[count]);
		count++;
		closeContents();
		init();
	}	
	else {								//index값이 입력되었을때(index지정추가버튼으로 삽입)
		var item = document.getElementById("content");
		var item2 = document.getElementById("sideBar");
		var postWrapper = document.createElement("div");
			postWrapper.id = "item"+count;
			postWrapper.style.border = "thick solid black";
			postWrapper.style.margin = "25px";
		
		//이미지
		var file = document.getElementById("inputimage");
		var cnt=0;

		//제목
		var postSubject = document.createElement("p");
			postSubject.innerHTML = document.getElementById("subject").value;
			postSubject.setAttribute("class","contentsSubject");
		//날짜
		var postDate = document.createElement("p");
			postDate.innerHTML = theYear+"년" +theMonth+"월"+theDate+"일 "+theHour+":"+theMinutes+":"+theSeconds;
		//내용
		var postText = document.createElement("p");
			postText.innerHTML = document.getElementById("textarea").value;
			postText.setAttribute("class","contentsText");
		//삭제버튼
		var deletebutton = document.createElement("button");
			deletebutton.onclick = function (){
				deleteContents(postWrapper,sideBar);
			}
		var deletename = document.createTextNode("게시물 삭제");
			deletebutton.appendChild(deletename);
		//사이드바에 추가.	
		var sideBar = document.createElement("li");
			var link = document.createElement("a");
			link.setAttribute("href","#"+postWrapper.id);
			link.innerHTML = document.getElementById("subject").value;
			sideBar.appendChild(link);
			item2.insertBefore(sideBar,item2.childNodes[index]);
		//textarea(댓글)
		//구역
		var postTextarea = document.createElement("div");
			postTextarea.setAttribute("class","contentsTextarea");
		//textbox
		var postTextbox1 = document.createElement("input");
		var postTextbox2 = document.createElement("textarea");
			postTextbox2.style.margin="1px 1px -5px 1px";
		//등록버튼
		var replyButton = document.createElement("button");
			replyButton.onclick = function (){
				addreply(postTextarea,postTextbox1,postTextbox2);
			}
		var replyName = document.createTextNode("댓글 등록");
			replyButton.appendChild(replyName);
		
			postWrapper.setAttribute("class","contents");
			postWrapper.appendChild(postSubject);
			postWrapper.appendChild(postDate);
			while(file.files[cnt])
			{
				var postImg = document.createElement("img");
				postImg.setAttribute("class","contentsImage");
				postImg.src = window.URL.createObjectURL(file.files[cnt]);
				postImg.height="300";
				postImg.width="300";
				postWrapper.appendChild(postImg);
				cnt++;
			}
			postWrapper.appendChild(postText);
			postWrapper.appendChild(postTextarea);
			postWrapper.appendChild(postTextbox1);
			postWrapper.appendChild(postTextbox2);
			postWrapper.appendChild(replyButton);
			postWrapper.appendChild(deletebutton);
			item.insertBefore(postWrapper, item.childNodes[index]);
		count++;
		closeContents();
		init();
	}
}

//삭제버튼이 눌렸을때 Contents닫기
function deleteContents(item,sideBar) {
	item.remove();
	sideBar.remove();
	count--;
}

//WritingForm에 닫기버튼이 눌렸을때 WritingForm 닫기
function closeContents(){
	document.getElementById("WirtingForm").style.display = "none";
}
//File선택 버튼이 눌려서 파일이 선택되면 파일주소가 image src에 연결되는 함수.
function fileSelected() {
	var file = document.getElementById('inputimage').files[0];
	var image = document.getElementById('image');
	image.src = window.URL.createObjectURL(file);
	image.style.display = "none";
}
//댓글 등록 버튼이 눌렀을때 댓글을 등록하는 함수
function addreply(postTextarea,postTextbox1,postTextbox2){
		var currentNow = new Date();
		var theYear = currentNow.getFullYear();
		var theMonth = currentNow.getMonth()+1;
		var theDate = currentNow.getDate();
		var theHour = currentNow.getHours();
		var theMinutes = currentNow.getMinutes();
		var theSeconds = currentNow.getSeconds();
		
		if(postTextbox1.value === "" ){		//null값이 들어 올 경우 예외처리
			alert("아이디를 입력하세요!!");
			return;
		}
		else if(postTextbox2.value === "" ){		//null값이 들어 올 경우 예외처리
			alert("댓글 내용을 입력하세요!!");
			return;
		}
		var reply = document.createElement("div");
			reply.setAttribute("class","reply");
		var reply1 = document.createElement("p");
			reply1.setAttribute("class","reply");
			reply1.innerHTML = "ID: "+postTextbox1.value+" ("+theYear+"/"+theMonth+"/"+theDate+" "+theHour+":"+theMinutes+":"+theSeconds+")";
		var reply2 = document.createElement("p");
			reply2.setAttribute("class","reply");
			reply2.innerHTML = postTextbox2.value;
		var reDeleteButton = document.createElement("button");
			reDeleteButton.onclick = function (){
				deletereply(postTextarea,reply);
			}
		var reDeleteName = document.createTextNode("댓글 삭제");
			reDeleteButton.appendChild(reDeleteName);
			
		reply.appendChild(reply1);
		reply.appendChild(reply2);
		reply.appendChild(reDeleteButton);
		postTextarea.appendChild(reply);
		//박스초기화
		postTextbox1.value = "";
		postTextbox2.value = "";
}
//댓글을 지우는 함수.
function deletereply(item1,item2){
	item1.removeChild(item2);
}