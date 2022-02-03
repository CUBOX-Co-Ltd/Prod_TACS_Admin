$(function(){

	// 팝업된 내 창에서 부모창으로 포커싱이 이동한 경우
	/* $(opener).on('focus', function () {
		console.log('event: opener focus');
	}); */
	
	//부모창의 새로고침/닫기/앞으로/뒤로
	$(opener).on('beforeunload', function() {
		 window.close();
	});
	
	$.datetimepicker.setLocale('ko');
	$('#fr_date').datetimepicker({
		format:'Y-m-d H:i'
	});
	$('#to_date').datetimepicker({
		format:'Y-m-d H:i'
	});	
	    
	// 숫자만 입력가능
	$(".onlyNumber").keyup(function(event){
		if (!(event.keyCode >=37 && event.keyCode<=40)) { //방향키
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^0-9]/gi,''));
		}
	});
	
	// 한글만 입력 가능
	$(".onlyHangul").keyup(function(event){
		if (!(event.keyCode >=37 && event.keyCode<=40)) {
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[a-z0-9]/gi,''));
		}
	});
	
	// 한글/영문 입력 가능
	$(".onlyHangulEnglish").keyup(function(event){
		if (!(event.keyCode >=37 && event.keyCode<=40)) {
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[0-9]/gi,''));
		}
	});	
	
	
	$("#fr_date").change(function(){ 
		var srchCfsdt = $("#fr_date").val().replace(/\-/g,'').replace(/\:/g,'').replace(/\s/g,'');
		var srchCfedt = $("#to_date").val().replace(/\-/g,'').replace(/\:/g,'').replace(/\s/g,'');
		if(srchCfedt != "" && srchCfsdt != "" && srchCfsdt > srchCfedt){
			alert("방문종료일보다 뒷 날짜를 선택할 수 없습니다.");
			$("#fr_date").val('');
			$("#fr_date").blur();
		}
	});
	
	$("#to_date").change(function(){
		var srchCfsdt = $("#fr_date").val().replace(/\-/g,'').replace(/\:/g,'').replace(/\s/g,'');
		var srchCfedt = $("#to_date").val().replace(/\-/g,'').replace(/\:/g,'').replace(/\s/g,'');
		if(srchCfedt != "" && srchCfsdt != "" && srchCfsdt > srchCfedt){
			alert("방문시작일보다 앞 날짜를 선택할 수 없습니다.");
			$("#to_date").val('');
			$("#to_date").blur();
		}
	});
	
	$("#fr_time1").focus(function(){
		previous = this.value; 
	}).change(function(){
		if(!checkVisitTime()) {
			$(this).val(previous);
		}
	});
	$("#fr_time2").focus(function(){
		previous = this.value; 
	}).change(function(){
		if(!checkVisitTime()) {
			$(this).val(previous);
		}
	});
	$("#to_time1").focus(function(){
		previous = this.value; 
	}).change(function(){
		if(!checkVisitTime()) {
			$(this).val(previous);
		}
	});
	$("#to_time2").focus(function(){
		previous = this.value; 
	}).change(function(){
		if(!checkVisitTime()) {
			$(this).val(previous);
		}
	});
	
	// img tag에서 미리보기
	$("#customFile").change(function(e){
		$("#imageType").val("file");
		document.querySelector("#myCanvas").style.display = "none";
		document.querySelector("#imgView").style.display = "block";
		//console.log('### e.target.files : '+e.target.files);
		//console.log('### e.target.files[0] : '+e.target.files[0]);
		if(e.target.files && e.target.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#imgView").attr("src", e.target.result);
			}
			reader.readAsDataURL(e.target.files[0]);
		}
	}); 	
	
	$("#btnLoadCamera").click(function(){
		console.log("init attach file!!!");
		$("#customFile").val("");
	});
	
});

function checkValidValue() {
	// 비밀번호
	if(!fnIsEmpty($("input[name='fpasswd']").val())) {
		//alert($("input[name='fpasswd']").val().search(/\s/));
		if($("input[name='fpasswd']").val().search(/\s/) != -1){
			alert("비밀번호는 공백 없이 입력해주세요.");
			$(this).focus();
			return false;
		}
		if($("input[name='fpasswd']").val().length < 4) {
			alert("비밀번호는 4자리 이상 입력하세요.");
			$(this).focus();
			return false;
		}
	}
	// 생년월일 체크
	if(!isDate($("input[name='fbirth']").val())) {
		alert("생년월일이 날짜 형식에 맞지 않습니다.");
		$(this).focus();
		return false;
	}
	// 전화번호 체크
	var str = $("input[name='fphone_1']").val()+"-"+$("input[name='fphone_2']").val()+"-"+$("input[name='fphone_3']").val();
	if(!isCelluar(str) && !isCall(str)) {
		alert("전화번호 형식에 맞지 않습니다.");
		$(this).focus();
		return false;
	}
	// 이메일 체크
	if(!fnIsEmpty($("input[name='femail']").val()) && !isEmail($("input[name='femail']").val())) {
		alert("이메일 형식에 맞지 않습니다.");
		$(this).focus();
		return false;
	}
	// 차량번호 체크
	/* if(!fnIsEmpty($("input[name='fcarno']").val()) && !isCarNo($("input[name='fcarno']").val())) {
		alert("차량번호 형식에 맞지 않습니다.");
		$(this).focus();
		return false;
	} */
	return true;
}

function checkVisitTime() {
	var str1 = $("#fr_time1").val() + $("#fr_time2").val();
	var str2 = $("#to_time1").val() + $("#to_time2").val();
	
	if($("#fr_date").val() == $("#to_date").val() && str1 > str2) {
		alert("방문시각을 다시 선택하세요.");
		return false;
	}
	return true;
}

function getVisitorInfo(str) {
	$.ajax({
		url: "/visitInfo/getVisitInfo.do",
		type: "POST",
		data: {
			"fvisitno": str
		},
		dataType: "json",
		success: function(data, status){
			//console.log(data);
			//console.log(status);
			if(status == "success"){
				$("input[name='fnm']").val(data.result.fnm);
				$("input[name='fcomnm']").val(data.result.fcomnm);
				$("input[name='frspofc']").val(data.result.frspofc);
				$("input[name='fbirth']").val(data.result.fbirth);
				$("input:radio[name=fsexcd]:input[value="+data.result.fsexcd+"]").prop("checked", true);
				$("input:radio[name=fsexcd]:input[value!="+data.result.fsexcd+"]").prop("checked", false);
				$("input[name='femail']").val(data.result.femail);
				$("input[name='fcarno']").val(data.result.fcarno);
				$("input[name='fnotebook']").val(data.result.fnotebook);
				$("input[name='fphone_1']").val(data.result.fphone_1);
				$("input[name='fphone_2']").val(data.result.fphone_2);
				$("input[name='fphone_3']").val(data.result.fphone_3);
			}else{alert("ERROR!");return;}
		}
	});
}

function keyInVisitor() {
	$("input[name='fnm']").focus();
}

function fnVisitPopup() {
	openPopup('/visitInfo/visitListPopup.do','visitListPopup', 1000, 600);
}

function fnUserPopup() {
	openPopup('/visitInfo/userListPopup.do','userListPopup', 900, 600);
}

function fnDetectCall() {
	showLoading ();
	$.ajax({
		url: "/userInfo/detectCallImg.do",
		data: "",
		processData: false,
		contentType: false,
		type: "POST",
		success: function(response) {
			hideLoading();
			//$("#imgView").attr("src",response);
			if(!fnIsEmpty(response) && !fnIsEmpty(response.rst) && response.rst == 1) {
				$("#myCanvas").show();
				$("#imgView").hide();
				$("#imageType").val("canvas");
				var image = new Image();
				image.onload = function() {
					//alert(image.width + "," + image.height);
					var canvas = document.getElementById('myCanvas');
					canvas.setAttribute('width', 640);
					canvas.setAttribute('height', 576);
					var scale = Math.max(canvas.width / image.width, canvas.height / image.height);
					//var scale = Math.min(canvas.width / image.width, canvas.height / image.height);
					var x = (canvas.width / 2) - (image.width / 2) * scale;
				    var y = (canvas.height / 2) - (image.height / 2) * scale;
					var ctx =  canvas.getContext('2d');
					ctx.drawImage(image, x, y, image.width * scale, image.height * scale);
				};
				image.src = response.imageString;
			} else {
				alert("사진이 확인되지 않습니다. 다시 시도하여 주십시오.");
			}
		},
		error: function (jqXHR){
			alert("사진촬영에 실패했습니다. 다시 시도하여 주십시오.");
		}
	});
}