<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*"%>
<%@ page import="java.io.File.*"%>
<%@ page errorPage="/WEB-INF/jsp/cubox/error/error.jsp" %>
<%@ page import="cubox.admin.main.service.vo.LoginVO" %>
<%
	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
	String containerMenuName = "";
	if(cubox.admin.cmmn.util.StringUtil.nvl(loginVO.getFemergency()).equals("Y")){
		containerMenuName = "/WEB-INF/jsp/cubox/emergency/emergency_management.jsp";
	}else{
		containerMenuName = "/WEB-INF/jsp/cubox/userInfo/user_management.jsp";
	}
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
	<body class="mod-bg-1 ">
        <!-- Page Wrapper-->
        <div class="page-wrapper">
            <div class="page-inner">
                <!-- page-sidebar -->
                <aside class="page-sidebar">
                    <div class="page-logo">
                    	<c:if test="${sessionScope.loginVO.femergency != 'EG' && sessionScope.loginVO.femergency != 'CG' && sessionScope.loginVO.femergency != 'EA' && sessionScope.loginVO.femergency != 'CA'}">
                        <a href="/userInfo/userMngmt.do" class="page-logo-link press-scale-down d-flex align-items-center position-relative"><strong>CUBOX</strong>보안관제시스템</a>
                        </c:if>
                        <c:if test="${sessionScope.loginVO.femergency == 'EG' || sessionScope.loginVO.femergency == 'EA' }">
                        <a href="/emergency/emergencyMngmt.do" class="page-logo-link press-scale-down d-flex align-items-center position-relative"><strong>CUBOX</strong>보안관제시스템</a>
                        </c:if>
                        <c:if test="${sessionScope.loginVO.femergency == 'CG' || sessionScope.loginVO.femergency == 'CA'}">
                        <a href="/emergency/certMngmt.do" class="page-logo-link press-scale-down d-flex align-items-center position-relative"><strong>CUBOX</strong>보안관제시스템</a>
                        </c:if>
                    </div>
                    <!-- BEGIN PRIMARY NAVIGATION -->
                    <nav id="js-primary-nav" class="primary-nav" role="navigation">
                		<jsp:include page="/WEB-INF/jsp/cubox/frame/sub_bk/Left.jsp" flush="false"/>
                	</nav>
                    <!-- END PRIMARY NAVIGATION -->
                </aside>
                <!-- //page-sidebar -->
                <div class="page-content-wrapper">
                    <!-- 오른쪽 상단 로고 등 -->
                    <header class="page-header" role="banner">
                    	<jsp:include page="/WEB-INF/jsp/cubox/frame/sub_bk/Header.jsp" flush="false"/>
                    </header>
                    <!-- //오른쪽 상단 로고 등 -->
                    <!-- Content -->
                    <main id="js-page-content" role="main" class="page-content">
                    	<jsp:include page="<%= containerMenuName %>" flush="false"/>
                    </main>
                    <div class="page-content-overlay" data-action="toggle" data-class="mobile-nav-on"></div>
					<!-- //Content -->
                    <!-- Footer -->
                    <footer class="page-footer" role="contentinfo">
                        <jsp:include page="/WEB-INF/jsp/cubox/frame/sub_bk/Footer.jsp" flush="false"/>
                    </footer>
                    <!--// Footer -->
				</div>
			</div>
		</div>
		<script type="text/javascript" src="/js/layout.js"></script>
		<script type="text/javascript" src="/js/bootstrap.datapicker.min.js"></script>
        <script type="text/javascript" src="/js/bootstrap.datapicker.kr.js"></script>
        <script type="text/javascript" src="/lib/timepicker/jquery.timepicker.min.js"></script>
        <script type="text/javascript" src="/js/common.js?vesion=1.2"></script>
        <script type="text/javascript" src="/lib/drag_drop/Sortable.js"></script>
        <script type="text/javascript" src="/js/jquery.blockUI.js"></script>
        <script type="text/javascript" src="/js/jquery.form.js"></script>
	</body>
</html>
