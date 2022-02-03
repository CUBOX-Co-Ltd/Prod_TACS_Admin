<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cubox.admin.main.service.vo.LoginVO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<title>CUBOX 보안관제시스템</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no, user-scalable=no, minimal-ui">
	<!-- base css -->
	<link rel="stylesheet" media="screen, print" href="/css/import.css">
	<link href="/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
	<script src="/js/vendors.js"></script>
	<script src="/js/layout.js"></script>
	<script src="/lib/drag_drop/Sortable.js"></script>
</head>
<body class="mod-bg-1 ">

<%
LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
if( loginVO != null ){
	if(loginVO.getFemergency().equals("Y")){
%>
<script type="text/javascript">
	location.href = "/emergency/emergencyMngmt.do"; 
</script>
<%
	}else{
%>
<script type="text/javascript">
	location.href = "/userInfo/userMngmt.do"; 
</script>
<%
	}
}
%>
					 
<c:if test="${resultMsg eq 'loginError'}">
<script type="text/javascript">
	alert("아이디 또는 비밀번호를 다시 확인하세요. \n등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.");
	window.location.href = "/login.do";
</script>
</c:if>
 
<script type="text/javascript">
	$(function(){ 
		$("#fsiteid").focus();
	});
	
	function loginProc() {
		f = document.frmLogin;

		var fsiteid = $("input[name=fsiteid]").val();
		var fpasswd = $("input[name=fpasswd]").val();
			
		if(fsiteid == ""){alert ("아이디를 입력하세요."); $("input[name=fsiteid]").focus(); return;}
		if(fpasswd == ""){alert ("비밀번호를 입력하세요."); $("input[name=fpasswd]").focus(); return;}
				
		f.action = "/common/loginProc.do"
		f.submit();
	}
	
	function caps_lock(e){
		var keyCode = 0;
		var shiftKey = false; 
		keyCode = e.keyCode;
		shiftKey = e.shiftKey;
		
		if(((keyCode >= 65 && keyCode <= 90) && !shiftKey) || ((keyCode >= 97 && keyCode <= 122) && shiftKey)){
			show_caps_lock();
			setTimeout("hide_caps_lock()", 500);
		}else{
			hide_caps_lock();
		}
	}
	
	function show_caps_lock(){
		$("#capslock").show(); 
	}
	
	function hide_caps_lock(){
		$("#capslock").hide();
	}
	
</script>

	<!-- Page Wrapper-->
	<div id="wrap">
		<div class="login_wrap">
			<!-- h1><img src="/images/logo_t.png" alt=""></h1-->
			<form id="frmLogin" name="frmLogin" method="post" onsubmit="return false;">
			<div class="login_box">
				<strong>로그인 해 주세요.</strong> 
				<div class="form_area">
					<fieldset>
						<legend>로그인</legend> 
						<div class="inq_input inp_id"> 
							<label for="email">아이디</label>
							<div class="input_wrap"> 
								<input type="text" id="fsiteid" name="fsiteid" placeholder="아이디" title="아이디 입력" onkeyup="this.value=this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|/`~!@#$%^&*()_=+|<>?:{}-]/g,'')" />
							</div>
						</div>
						<div class="inq_input inp_pw">  
							<label for="loginPwd">비밀번호</label>
							<div class="input_wrap"> 
								<input type="password"  id="fpasswd" name="fpasswd" placeholder="비밀번호" title="비밀번호 입력" onkeypress="caps_lock(event);"/>		
								<span id="capslock" style="display:none;"><b>CapsLock</b>키가 눌려있습니다.</span>					
							</div>
						</div>
						<button class="btn_login btn btn-sm btn-primary" onclick="loginProc();">로그인</button>
					</fieldset>
				</div>
				<ul class="list_st1">
					<li class="float_wrap">· 아이디/비밀번호는 대소문자 구별합니다. 입력 시 유의하세요</li>
					<li class="float_wrap mt10">· 보안을 위해 이용 후 반드시 로그아웃 해주시기 바랍니다.</li>
				</ul>
			</div>
			</form>
		</div>
	</div>
	<!-- // Page Wrapper -->
</body>
</html>
