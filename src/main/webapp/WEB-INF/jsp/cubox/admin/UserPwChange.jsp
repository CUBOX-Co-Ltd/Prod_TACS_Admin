<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
$(function() {
	$(".title_tx").html("비밀번호 변경");
	$("#curPasswd").focus();
});

function fnChangePasswd() {
	var curPasswd = $("input[name=curPasswd]").val();
	var newPasswd = $("input[name=newPasswd]").val();
	var newPasswdConfirm = $("input[name=newPasswdConfirm]").val();

	if(fnIsEmpty(curPasswd)){ alert ("현재 비밀번호를 입력하세요."); $("input[name=curPasswd]").focus(); return;}
	if(fnIsEmpty(newPasswd)){alert ("변경 비밀번호를 입력하세요."); $("input[name=newPasswd]").focus(); return;}
	if(fnIsEmpty(newPasswdConfirm)){alert ("변경 비밀번호 확인을 입력하세요."); $("input[name=newPasswdConfirm]").focus(); return;}

	var pattern1 = /[0-9]/;
	var pattern2 = /[a-zA-Z]/;
	var pattern3 = /[~!@\#$%<>^&*]/;     // 원하는 특수문자 추가 제거
	var i = 0;

	if(pattern1.test(newPasswd)) {
		i++;
	}
	if(pattern2.test(newPasswd)) {
		i++;
	}
	if(pattern3.test(newPasswd)) {
		i++;
	}

	if(newPasswd.length < 8 || newPasswd.length > 20){
		alert("변경 비밀번호를 8~20자 이내 다시 입력하세요.");
		$("input[name=newPasswd]").focus();
		$("input[name=newPasswd]").val("");
		$("input[name=newPasswdConfirm]").val("");
		return;
	}
	if(i < 2){
		alert("변경 비밀번호를 2종류 이상 조합하여 다시 입력하세요.");
		$("input[name=newPasswd]").focus();
		$("input[name=newPasswd]").val("");
		$("input[name=newPasswdConfirm]").val("");
		return;
	}
	if(newPasswd != newPasswdConfirm){
		alert("변경 비밀번호와 변경 비밀번호 확인이 일치하지 않습니다.")
		$("input[name=newPasswd]").focus();
		$("input[name=newPasswd]").val("");
		$("input[name=newPasswdConfirm]").val("");
		return;
	}
	if(curPasswd == newPasswd) {
		alert("현재 비밀번호와 변경 비밀번호가 동일합니다.\n변경 비밀번호를 다시 입력하십시오.")
		$("input[name=newPasswd]").focus();
		$("input[name=newPasswd]").val("");
		$("input[name=newPasswdConfirm]").val("");
		return;
	}

	if(confirm("비밀번호를 변경하시겠습니까?")){
		$.ajax({
			type:"POST",
			url:"<c:url value='/user/modPasswd.do' />",
			data:{
				"curPasswd": curPasswd,
				"newPasswd": newPasswd
			},
			dataType:'json',
			//timeout:(1000*30),
			success:function(returnData, status){
				if(status == "success") {
					//location.reload();
					console.log(returnData);
					if(returnData.checkPwdError != "Y"){
						alert("비밀번호가 변경되었습니다. \n비밀번호 변경시 자동 로그아웃됩니다. \n재로그인 하십시요.");
						location.href='/logout.do';
					}else{
						alert("현재 비밀번호가 맞지 않습니다.");
						$("input[name=curPasswd]").focus();
						$("input[name=curPasswd]").val("");
					}
				}else{ alert("ERROR!");return;}
			}
		});
	}
}
</script>
<form id="frmSearch" name="frmSearch" method="post">
<div class="search_box mb_20">
	<div class="search_in" style="width: 600px;">
		<div class="comm_search mb_20 mt_10">
			<div class="w_150px fl" style="line-height: 30px"><em>현재 비밀번호</em></div>
			<input type="password" id="curPasswd" name="curPasswd" class="w_250px input_com l_radius_no" placeholder="현재 비밀번호" maxlength="20" style="border:1px solid #ccc;"/>
		</div>
		<div class="comm_search w_100p mb_20" style="line-height: 30px">
			<div class="w_150px fl"><em>변경 비밀번호</em></div>
			<input type="password" id="newPasswd" name="newPasswd" class="w_250px input_com l_radius_no" placeholder="변경 비밀번호" maxlength="20" style="border:1px solid #ccc;"/>
		</div>
		<div class="comm_search w_100p mb_20" style="line-height: 30px">
			<div class="w_150px fl"><em>변경 비밀번호 확인</em></div>
			<input type="password" id="newPasswdConfirm" name="newPasswdConfirm"  class="w_250px input_com l_radius_no" placeholder="변경 비밀번호 확인" maxlength="20" style="border:1px solid #ccc;"/>
			<button type="button" class="btn_middle color_basic ml_5" onclick="fnChangePasswd()">저장</button>
		</div>
	</div>
</div>
</form>