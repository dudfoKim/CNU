$(document).ready(function(){
	$("#checkButton").click(function(){
		$.ajax({
			type : "POST",
			url : "./showInfo.jsp",
			dataType : "json",
			contenType : "application/son; charset=utf-8",
			beforeSend : function(){
				$("body").css("cursor","wait");
			},
			success : function( data ) {
				var div = $("#result");
					var area = data.area;
					var temp = data.temp;
					var dust = data.dust;
					var rain = data.rain;
					div.append(area);
					div.append(temp);
					div.append(dust);
					div.append(rain);
				
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n"
						+ "message:" + request.responseText
						+ "\n" + "error:" + error);
			},
			complete: function(){
				$("body").css("cursor","default");
			}
		});
	});
	
});