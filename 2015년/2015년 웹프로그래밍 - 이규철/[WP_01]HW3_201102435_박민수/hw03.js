var index = 0;			// 각각의 텍스트 박스의 id를 고유한 아이디로 제공해주기 위한 index

function allowDrop(ev) {
    ev.preventDefault();
}
function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}
function drop(ev) {
	if(ev.target.nodeName == "DIV")			//놓으려는 곳에 박스가 존재하면 경고창 띄우고 종료.
	{
			alert("시간표가 중복됩니다.");
			return;
	}
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    ev.target.appendChild(document.getElementById(data));
	var table = document.getElementById("TimeTable").innerHTML;
	localStorage.setItem("Table", table);
}
function deleteItem(ev){					// 휴지통에 삭제하려는 박스가 드랍됬을때 동작하는 함수	
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");					// 드래그된 박스를
	ev.target.appendChild(document.getElementById(data));		// 휴지통에 자식으로 붙인뒤
	ev.target.removeChild(ev.target.childNodes[0]);				// 삭제해버린다.
	var table = document.getElementById("TimeTable").innerHTML;
	localStorage.setItem("Table", table);						// 이후 테이블 상황을 업데이트
}
function addTextBox(){											// 박스를 생성해주는 함수
	var box = document.getElementById("box");
	var color = document.getElementById("Bcolor").value;
	var newText = document.createElement("div");
	index = localStorage.getItem("index");
	newText.contentEditable = true;
	newText.style.width = "100px";
	newText.style.height = "30px";
	newText.style.textAlign = "center";
	newText.style.color = "white";
	newText.style.fontSize = "15px";
	newText.style.backgroundColor = color;
	newText.setAttribute("draggable","true");
	newText.setAttribute("ondragstart","drag(event)");
	newText.setAttribute("oninput","modifyTexbox()");		
	if(index === null)
	{
		newText.id = "Text0";
	}
	else
		newText.id = "Text"+index;
	box.appendChild(newText); 
	index++;
	localStorage.setItem("index", index);
}
function reloadTable(){						// 로컬스토리지에 저장되어있는 테이블을 불러오는 함수.
	if(localStorage.length == 0)			// 로컬스토리지가 비어있으면 경고창을 띄우고 종료
	{
		alert("로컬 스토리지가 비어있습니다.");
		return;
	}
	var loadTable = localStorage.getItem("Table");
	var table = document.getElementById("TimeTable");
	table.innerHTML = loadTable;
}
function modifyTexbox(){					// 텍스트박스가 수정될때 로컬스토리지에 저장되는 테이블을 지속적으로 업데이트
	var table = document.getElementById("TimeTable").innerHTML;
	localStorage.setItem("Table", table);
}
function initStorage(){						// 로컬스토리지를 초기화해주는 함수
	window.localStorage.clear();
}