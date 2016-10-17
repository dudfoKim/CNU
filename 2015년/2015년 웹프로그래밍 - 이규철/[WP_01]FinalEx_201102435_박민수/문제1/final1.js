var cnt = 0;
function Submit(){
	var we = document.getElementById("we");
	var kyung = document.getElementById("kyung");
	var subject = document.getElementById("subject");
	var closeWindow = document.getElementById("a");
	var googleMap = document.getElementById("googleMap");
	var List = document.getElementById("List");
	var div = document.createElement("DIV");
		div.style.width = "800px";
		div.style.height = "380px";
		div.id = "map"+cnt;
		div.setAttribute("class","map" );
	var li = document.createElement("LI");
	var link = document.createElement("a");
		link.setAttribute("href","#"+div.id );
		link.setAttribute("onclick","showOne(event)");
		link.id = cnt;
		link.innerHTML = subject.value;
		li.appendChild(link); 
	var mapProp = {
    center:new google.maps.LatLng(we.value,kyung.value),
    zoom:14,
    mapTypeId:google.maps.MapTypeId.ROADMAP
	
  };
  var map=new google.maps.Map(div, mapProp);
  google.maps.event.addListener(map, 'click', function(event) {
    placeMarker(event.latLng);
  });
  googleMap.appendChild(div);
  List.appendChild(li);
  function placeMarker(location) {
  var marker = new google.maps.Circle({
    center:location,
	radius:200,
	strokeColor:"#0000FF",
	strokeOpacity:0.8,
	strokeWeight:2,
	fillColor:"#0000FF",
	fillOpacity:0.4
  });
 
  marker.setMap(map);
	}
  closeWindow.style.display = "none";
  cnt ++;
}
function showOne(ev){
	var classMap =  document.getElementsByClassName("map");
	var index = Number(ev.target.id);
	
	for(i=0; i<classMap.length; i++){
		classMap[i].style.display = "none";
		if(i == index)
			classMap[i].style.display = "block";
	}
}
function showAll(){
	var classMap =  document.getElementsByClassName("map");
	for(i=0; i<classMap.length; i++){
		classMap[i].style.display = "block";
	}
}
function Close(){
	var closeWindow = document.getElementById("a");
	
	closeWindow.style.display = "none";
}
function Add(){
	var openWindow = document.getElementById("a");
	
	openWindow.style.display = "block";
}

function Delete(){
	var googleMap = document.getElementById("googleMap");
	var List = document.getElementById("List");
	var person = prompt("삭제할 번호를 입력하세요.");

	
	googleMap.removeChild(googleMap.childNodes[person]);
	List.removeChild(List.childNodes[person]);
}