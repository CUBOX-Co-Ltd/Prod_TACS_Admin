<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--검색박스 -->
<script type="text/javascript">
	$(function() {
		$(".title_tx").html("비밀번호 변경");
		$("#currentpasswd").focus();
	});
	//비밀번호 변경
	function fnPasswdChangeSave() {
		var currentpasswd = $("input[name=currentpasswd]").val();
		var fpasswd = $("input[name=fpasswd]").val();
		var fpasswdcon = $("input[name=fpasswdcon]").val();

		if(fnIsEmpty(currentpasswd)){ alert ("현재 비밀번호를 입력하세요."); $("input[name=currentpasswd]").focus(); return;}
		if(fnIsEmpty(fpasswd)){alert ("변경 비밀번호를 입력하세요."); $("input[name=fpasswd]").focus(); return;}
		if(fnIsEmpty(fpasswdcon)){alert ("변경 비밀번호 확인을 입력하세요."); $("input[name=fpasswdcon]").focus(); return;}

		var pattern1 = /[0-9]/;
		var pattern2 = /[a-zA-Z]/;
		var pattern3 = /[~!@\#$%<>^&*]/;     // 원하는 특수문자 추가 제거
		var i = 0;

		if(pattern1.test(fpasswd)) {
			i++;
		}
		if(pattern2.test(fpasswd)) {
			i++;
		}
		if(pattern3.test(fpasswd)) {
			i++;
		}

		if(fpasswd.length < 8 || fpasswd.length > 20){
			alert("변경 비밀번호를 8~20자 이내 다시 입력하세요.");
			$("input[name=fpasswd]").focus();
			$("input[name=fpasswd]").val("");
			$("input[name=fpasswdcon]").val("");
			return;
		}
		if(i < 2){
			alert("변경 비밀번호를 2종류 이상 조합하여 다시 입력하세요.");
			$("input[name=fpasswd]").focus();
			$("input[name=fpasswd]").val("");
			$("input[name=fpasswdcon]").val("");
			return;
		}
		if(fpasswd != fpasswdcon){
			alert("변경 비밀번호와 변경 비밀번호 확인이 일치하지 않습니다.")
			$("input[name=fpasswd]").focus();
			$("input[name=fpasswd]").val("");
			$("input[name=fpasswdcon]").val("");
			return;
		}
		if(currentpasswd == fpasswd) {
			alert("현재 비밀번호와 변경 비밀번호가 동일합니다.\n변경 비밀번호를 다시 입력하십시오.")
			$("input[name=fpasswd]").focus();
			$("input[name=fpasswd]").val("");
			$("input[name=fpasswdcon]").val("");
			return;
		}

		if(confirm("비밀번호를 변경하시겠습니까?")){
			$.ajax({
				type:"POST",
				url:"<c:url value='/basicInfo/passwdChangeSave.do' />",
				data:{
					"currentpasswd": currentpasswd,
					"fpasswd": fpasswd
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
							$("input[name=currentpasswd]").focus();
							$("input[name=currentpasswd]").val("");
						}
					}else{ alert("ERROR!");return;}
				}
			});
		}
	}
</script>
<form id="frmSearch" name="frmSearch" method="post">
<input type="hidden" id="chkValueArray" name="chkValueArray" value=""/>
<input type="hidden" id="chkTextArray" name="chkTextArray" value="" >
<input type="hidden" id="fdownresn" name="fdownresn" value=""/>
<div class="search_box mb_20">
	<div class="search_in" style="width: 600px;">
		<div class="comm_search mb_20 mt_10">
			<div class="w_150px fl" style="line-height: 30px"><em>현재 비밀번호</em></div>
			<input type="password" id="currentpasswd" name="currentpasswd" class="w_250px input_com l_radius_no" placeholder="현재 비밀번호" maxlength="20" style="border:1px solid #ccc;"/>
		</div>
		<div class="comm_search w_100p mb_20" style="line-height: 30px">
			<div class="w_150px fl"><em>변경 비밀번호</em></div>
			<input type="password" id="fpasswd" name="fpasswd" class="w_250px input_com l_radius_no" placeholder="변경 비밀번호" maxlength="20" style="border:1px solid #ccc;"/>
		</div>
		<div class="comm_search w_100p mb_20" style="line-height: 30px">
			<div class="w_150px fl"><em>변경 비밀번호 확인</em></div>
			<input type="password" id="fpasswdcon" name="fpasswdcon"  class="w_250px input_com l_radius_no" placeholder="변경 비밀번호 확인" maxlength="20" style="border:1px solid #ccc;"/>
			<button type="button" class="btn_middle color_basic ml_5" onclick="fnPasswdChangeSave()">저장</button>
		</div>
	</div>
</div>
<!--//검색박스 -->
</form>