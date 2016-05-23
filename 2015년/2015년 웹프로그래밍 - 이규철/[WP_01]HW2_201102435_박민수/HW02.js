function checkTotalCost() {
	var BookName1 = document.getElementById("bookname1").innerText;
	var BookName2 = document.getElementById("bookname2").innerText;
	var BookName3 = document.getElementById("bookname3").innerText;
	var BookName4 = document.getElementById("bookname4").innerText;
	var BookName5 = document.getElementById("bookname5").innerText;
	var BookName6 = document.getElementById("bookname6").innerText;

	var CheckBook1 = document.getElementById("book1");
	var CheckBook2 = document.getElementById("book2");
	var CheckBook3 = document.getElementById("book3");
	var CheckBook4 = document.getElementById("book4");
	var CheckBook5 = document.getElementById("book5");
	var CheckBook6 = document.getElementById("book6");
	
	var VolBook1 = parseInt(document.getElementById("inputbook1").value);
	var VolBook2 = parseInt(document.getElementById("inputbook2").value);
	var VolBook3 = parseInt(document.getElementById("inputbook3").value);
	var VolBook4 = parseInt(document.getElementById("inputbook4").value);
	var VolBook5 = parseInt(document.getElementById("inputbook5").value);
	var VolBook6 = parseInt(document.getElementById("inputbook6").value);
	
	var CostBook1 = parseInt(document.getElementById("book1Price").innerHTML);
	var CostBook2 = parseInt(document.getElementById("book2Price").innerHTML);
	var CostBook3 = parseInt(document.getElementById("book3Price").innerHTML);
	var CostBook4 = parseInt(document.getElementById("book4Price").innerHTML);
	var CostBook5 = parseInt(document.getElementById("book5Price").innerHTML);
	var CostBook6 = parseInt(document.getElementById("book6Price").innerHTML);
	
	var totalCost = 0;
	
	if(CheckBook1.checked && !isNaN(VolBook1)){
		totalCost += VolBook1 * CostBook1;
		console.log(document.getElementById("buyList1").innerHTML = BookName1 + "*"+ VolBook1);	
	}
	else if(!CheckBook1.checked){
		console.log(document.getElementById("buyList1").innerHTML = " ");
	}
	if(CheckBook2.checked && !isNaN(VolBook2))
	{
		totalCost += VolBook2 * CostBook2;
		console.log(document.getElementById("buyList2").innerHTML = BookName2 + "*"+ VolBook2);
	}
	else if(!CheckBook2.checked){
		console.log(document.getElementById("buyList2").innerHTML = " ");
	}
	if(CheckBook3.checked && !isNaN(VolBook3))
	{
		totalCost += VolBook3 * CostBook3;
		console.log(document.getElementById("buyList3").innerHTML = BookName3 + "*"+ VolBook3);
	}
	else if(!CheckBook3.checked){
		console.log(document.getElementById("buyList3").innerHTML = " ");
	}
	if(CheckBook4.checked && !isNaN(VolBook4))
	{
		totalCost += VolBook4 * CostBook4;
		console.log(document.getElementById("buyList4").innerHTML = BookName4 + "*"+ VolBook4);
	}
	else if(!CheckBook4.checked){
		console.log(document.getElementById("buyList4").innerHTML = " ");
	}
	if(CheckBook5.checked && !isNaN(VolBook5))
	{
		totalCost += VolBook5 * CostBook5;
		console.log(document.getElementById("buyList5").innerHTML = BookName5 + "*"+ VolBook5);
	}
	else if(!CheckBook5.checked){
		console.log(document.getElementById("buyList5").innerHTML = " ");
	}
	if(CheckBook6.checked && !isNaN(VolBook6))
	{
		totalCost += VolBook6 * CostBook6;
		console.log(document.getElementById("buyList6").innerHTML = BookName6 + "*"+ VolBook6);
	}
	else if(!CheckBook6.checked){
		console.log(document.getElementById("buyList6").innerHTML = " ");
	}
	if(totalCost >= 10000){
		console.log(document.getElementById("delivery").innerHTML = 0);
		console.log(document.getElementById("totalCost").innerHTML = totalCost);
	}
	else if(totalCost != 0){
		console.log(document.getElementById("delivery").innerHTML = 2000);
		totalCost += 2000;
		console.log(document.getElementById("totalCost").innerHTML = totalCost);
	}
	if(totalCost === 0){
		console.log(document.getElementById("delivery").innerHTML = "");
		console.log(document.getElementById("totalCost").innerHTML = "");
	}
	
}

function chekSubmit() {
	var CheckBook1 = document.getElementById("book1");
	var CheckBook2 = document.getElementById("book2");
	var CheckBook3 = document.getElementById("book3");
	var CheckBook4 = document.getElementById("book4");
	var CheckBook5 = document.getElementById("book5");
	var CheckBook6 = document.getElementById("book6");
	
	var Name = document.getElementById("cunsumerName").value;
	var DsAddress = document.getElementById("Address").value;
	var Select = document.getElementById("CardSelect").selectedIndex;
	var CardNum = document.getElementById("cardNumTB").value;
	var patternCardnum = /^\d{4}-\d{4}-\d{4}-\d{4}$/;
	var patternName = /[가-힣a-zA-z]/;
	
	if(!CheckBook6.checked && !CheckBook5.checked && !CheckBook4.checked && !CheckBook3.checked && !CheckBook2.checked && !CheckBook1.checked ){
		alert("Select any book!");
	}
	else if(!Name)
	{
		alert("Input your name !");
	}
	else if(!patternName.test(Name)) {
		alert("Cunsumer name input only STRING !");
	}
	else if(!DsAddress)
	{
		alert("Input your Address !");
	}
	else if(Select === 0){
		alert("Select Card Corporation !");
	}
	else if(!CardNum) {
		alert("Input your Card Number !");
	}
	else if (!patternCardnum.test(CardNum)){
		alert("Cardnumber pattern is 0000-0000-0000-0000");
	}
	else{
		alert("Thak you!");
	}
	
}

