<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="cubox/frame/sub/head.jsp" />
	</head>
	<body id="test">
		<!--상단영역 공통  -->
		<jsp:include page="cubox/frame/sub/top_com.jsp"/>
		<!--//상단영역 공통  -->
		<main>
			<div class="sub_box">
				<!--타이틀 공통 -->
				<div class="title_box">
					<div class="title_tx" ></div>
				  	<div class="route">
				  </div>
				</div>
				<!--//타이틀 공통 -->
				<decorator:body />
			</div>
		</main>
		<jsp:include page="cubox/frame/sub/footer.jsp" />
    </body>
</html>
