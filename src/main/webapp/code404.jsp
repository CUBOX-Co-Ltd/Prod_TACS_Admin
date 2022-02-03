<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<title>CUBOX 보안관제시스템</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no, user-scalable=no, minimal-ui">
	<!-- base css -->
	<link href="/css/import.css" rel="stylesheet" media="screen, print"/>
	<link href="/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>

<body>
	<div id="wrap">
		<div class="error-wrap">
			<!-- <h1><a href="#"><img src="/images/logo_t.png" alt=""></a></h1> -->
			<div class="error">
				<span class="img"><img src="/images/error_img.png" alt=""></span>
				<h2>요청하신 페이지를 찾을 수가 없습니다.</h2>
				<p>찾으시려는 페이지는 변경되었거나, 현재 사용할 수 없는 페이지입니다. <br>입력하신 주소가 정확한지 다시 한번 확인 해 주시기 바랍니다.</p>
			</div>
			<div class="error-button txt_center">
				<button type="button" class="btn btn-lg btn-primary" onclick="location.href='/index.do'">메인페이지 바로가기</button>
			</div>
		</div>
	</div>
</body>
</html>
