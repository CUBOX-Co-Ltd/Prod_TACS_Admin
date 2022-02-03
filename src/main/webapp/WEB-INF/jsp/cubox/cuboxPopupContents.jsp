<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*"%>
<%@ page import="java.io.File.*"%>
<%@ page errorPage="/WEB-INF/jsp/cubox/error/error.jsp" %>
<%
	String containerMenuName = "/WEB-INF/jsp/cubox/common/index.jsp";
	Enumeration<String> enums = request.getAttributeNames();


	// System.out.println("---------------requestAttributes--------------");
	while(enums.hasMoreElements()){
		String key = enums.nextElement();
		if(key.indexOf(".")<0){
			// System.out.println(key + " - typeof:["+request.getAttribute(key).getClass()+"]" + request.getAttribute(key));
			if(key.equals("menuPath")) containerMenuName = "/WEB-INF/jsp/cubox/"+String.valueOf(request.getAttribute(key))+".jsp";
		}
	}
	// System.out.println("---------------requestAttributes--------------");


	String menuInfo = request.getParameter("menuInfo");
	if(!StringUtils.isEmpty(menuInfo)){
		containerMenuName = "/WEB-INF/jsp/cubox/"+menuInfo+".jsp";
	}

%>
<!DOCTYPE html>
<html  lang="ko">
	<head>
		<jsp:include page="/WEB-INF/jsp/cubox/frame/sub_bk/Head.jsp" flush="false"/>
	</head>
	<body>
		<div class="popupLayer">
    	<jsp:include page="<%= containerMenuName %>" flush="false"/>
		</div>
        <script type="text/javascript" src="/js/vendors.js"></script>
        <script type="text/javascript" src="/js/layout.js"></script>
		<script type="text/javascript" src="/js/bootstrap.datapicker.min.js"></script>
        <script type="text/javascript" src="/js/bootstrap.datapicker.kr.js"></script>
        <script type="text/javascript" src="/lib/timepicker/jquery.timepicker.min.js"></script>
        <script type="text/javascript" src="/js/common.js"></script>
        <script type="text/javascript" src="/lib/drag_drop/Sortable.js"></script>
        <script type="text/javascript" src="/js/jquery.blockUI.js"></script>
	</body>
</html>
