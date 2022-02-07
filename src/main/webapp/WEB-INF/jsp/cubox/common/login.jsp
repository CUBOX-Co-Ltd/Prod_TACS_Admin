<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0">
<title><spring:message code="site.title"/></title>
<link rel="stylesheet" type="text/css" href="/css/all.css" media="all">
<script type="text/javascript" src="/js/jquery-2.2.4.min.js"></script>
<c:if test="${result ne '' && result ne null}">
<script type="text/javascript">
	alert("<c:out value='${message}'/>");
	window.location.href = "/login.do";
</script>
</c:if>
<script type="text/javascript">
$(function(){
	$("input[name=userId]").focus();

	var sUserId = getCookie("cookieLoginUserId");
	if(!fnIsEmpty(sUserId)){
		$("input[name=userId]").val(sUserId);
		$("#chkIdSave").attr("checked", true);
	}
});

function fnLoginProc () {
	var userId = $("input[name=userId]").val();
	var userPw = $("input[name=userPw]").val();

	if(fnIsEmpty(userId)){alert ("아이디를 입력하세요."); $("input[name=userId]").focus(); return;}
	if(fnIsEmpty(userPw)){alert ("비밀번호를 입력하세요."); $("input[name=userPw]").focus(); return;}

	if($("#chkIdSave").is(":checked")) {
		var sUserId = $("input[name=userId]").val();
		setCookie("cookieLoginUserId", sUserId, 365);
	}else{
		deleteCookie("cookieLoginUserId");
	}

	f = document.frmLogin;
	f.action = "/common/loginProc.do"
	f.submit();
}

function caps_lock(e) {
	var keyCode = 0;
	var shiftKey = false;
	keyCode = e.keyCode;
	shiftKey = e.shiftKey;

	if( ((keyCode >= 65 && keyCode <= 90) && !shiftKey) || ((keyCode >= 97 && keyCode <= 122) && shiftKey) ){
		show_caps_lock();
		setTimeout("hide_caps_lock()", 500);
	} else if( keyCode == 13 ){
		fnLoginProc();
	} else {
		hide_caps_lock();
	}
}

function show_caps_lock() {
	$("#capslock").show();
}

function hide_caps_lock() {
	$("#capslock").hide();
}

function setCookie(cookieName, value, exdays){
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	document.cookie = cookieName + "=" + cookieValue;
}

function getCookie(cookieName) {
	cookieName = cookieName + '=';
	var cookieData = document.cookie;
	var start = cookieData.indexOf(cookieName);
	var cookieValue = '';
	if(start != -1){
		start += cookieName.length;
		var end = cookieData.indexOf(';', start);
		if(end == -1)end = cookieData.length;
		cookieValue = cookieData.substring(start, end);
	}
	return unescape(cookieValue);
}

function deleteCookie(cookieName) {
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate() - 1); //어제날짜를 쿠키 소멸날짜로 설정
	document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}

//null check
function fnIsEmpty(inVal) {
	if (new String(inVal).valueOf() == "undefined") return true;
	if (inVal == null) return true;
	if (inVal == 'null') return true;
	var v_ChkStr = new String(inVal);
	if (v_ChkStr == null) return true;
	if (v_ChkStr.toString().length == 0) return true;
	return false;
}

function fnGoVisit() {
	var iWidth = 1000;
	var iHeight = 820;
	var iLeft = (screen.availWidth - iWidth)/2;
	var iTop = (screen.availHeight - iHeight)/2;
	window.open('/visitInfo/visitorPopup.do', 'visitorPopup', "scrollbars=yes, toolbar=no, menubar=no, resizable=no, status=no, location=no, width="+iWidth+", height="+iHeight+", left="+iLeft+", top="+iTop);
}
</script>
</head>
<body>
<div id="login_wrap">
	<form id="frmLogin" name="frmLogin" method="post">
		<div id="inloginbox">
			<div class="loginbox">
				<div class="topimg">
					<img src="/img/logo/logo_<spring:eval expression="@property['Globals.site.main.id']" />.png" alt="" />
				</div>
				<div class="title">
					<em>Member login</em><spring:message code="site.title"/>
				</div>
				<div class="fl_box">
					<div class="tx_box">아이디</div>
					<div class="input_inbox">
						<div class="icon">
							<img src="/img/login/login_icon1.png" alt="" />
						</div>
						<input name="userId" type="text" placeholder="Username" autocomplete="off" onkeyup="this.value=this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|/`~!@#$%^&*()_=+|<>?:{}-]/g,'')"/>
					</div>
					<div class="tx_box">비밀번호</div>
					<div class="input_inbox">
						<div class="icon">
							<img src="/img/login/login_icon2.png" alt="" />
						</div>
						<input type="password" name="userPw" placeholder="Password" onkeypress="caps_lock(event);" />
					</div>
					<button type="button" class="login_btn mt_30 mr_20" onClick="fnLoginProc()">로그인</button>
					<%-- <button type="button" class="login_btn mt_30" onClick="fnGoVisit()">방문신청</button> --%>
					<div class="com_box">
						<input type="checkbox" id="chkIdSave" class="checkbox mr_5" value="Y">
						<label for="chkIdSave">아이디를 저장하기</label>
					</div>
				</div>
				<div class="join_box">
					<div class="txbox">아이디/비밀번호는 대소문자 구별합니다. 입력 시 유의하세요. 보안을 위해 이용 후 반드시 로그아웃 해주시기 바랍니다.</div>
				</div>
			</div>
		</div>
	</form>
</div>
</body>