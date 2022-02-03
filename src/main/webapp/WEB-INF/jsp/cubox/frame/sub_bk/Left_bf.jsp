<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 			uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" 	uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 			uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="cubox.admin.main.service.vo.LoginVO" %>
<c:set var="currentURI" value="${requestScope['javax.servlet.forward.servlet_path']}" />

                        <ul id="js-nav-menu" class="nav-menu">
							<c:if test="${sessionScope.loginVO.fkind3 == '10'}">
							<li>
								<a href="#">
									<i class="fa fa-folder"></i>
									<span class="nav-link-text">기본 정보</span>
								</a>
								<ul>
									<li class="li-a"><a href="/basicInfo/centerMngmt.do">센터 관리</a></li>
									<li class="li-a"><a href="/basicInfo/accountMngmt.do">계정 관리</a></li>
									<li class="li-a"><a href="/basicInfo/commcodeMngmt.do">코드 관리</a></li>
								</ul>
							</li>
							</c:if>
							<li>
								<a href="#">
									<i class="fa fa-folder"></i>
									<span class="nav-link-text">단말기 제어 관리</span>
								</a>
								<ul>
									<li class="li-a"><a href="/terminalInfo/terminalScheduleMngmt.do">단말기 관리</a></li>
								</ul>
							</li>
							<!-- 
							<li>
								<a href="#">
									<i class="fa fa-folder"></i>
									<span class="nav-link-text">출입 권한 관리</span>
								</a>
								<ul>
									<li class="li-a"><a href="/authInfo/groupAuthMngmt.do">그룹 권한</a></li>
									<li class="li-a"><a href="/authInfo/userAuthMngmt.do">출입자 권한</a></li>
								</ul>
							</li>
							 -->
							<li>
								<a href="#">
									<i class="fa fa-folder"></i>
									<span class="nav-link-text">출입자 관리</span>
								</a>
								<ul>
									<li class="li-a"><a href="/userInfo/userMngmt.do">출입자 관리</a></li>
									<li class="li-a"><a href="/userInfo/userExpireMngmt.do">출입자 만료 관리</a></li>
								</ul>
							</li>
                            <li>
								<a href="#">
									<i class="fa fa-folder"></i>
									<span class="nav-link-text">보고서</span>
								</a>
								<ul>
									<li class="li-a"><a href="/logInfo/logMngmt.do">출입이력</a></li>
									<li class="li-a"><a href="/logInfo/logFailMngmt.do">출입실패이력</a></li>
									<!--
								 	<li class="li-a"><a href="/gateInfo/gateUserMngmt.do">단말기별 출입자</a></li>
								 	<li class="li-a"><a href="/authInfo/authUserMngmt.do">권한그룹별 출입자</a></li>
								 	<li class="li-a"><a href="/terminalInfo/terminalLogMngmt.do">단말기제어 로그</a></li>
									-->
								 	<li class="li-a"><a href="/basicInfo/sysLogMngmt.do">시스템 로그</a></li>
									<li class="li-a"><a href="/logInfo/syncLogMngmt.do">동기화 이력</a></li>
								</ul>
							</li>
                            <li>
								<a href="#">
									<i class="fa fa-folder"></i>
									<span class="nav-link-text">서버인증</span>
								</a>
								<ul>
									<li class="li-a"><a href="/apiInfo/compareMonitor.do">서버인증 모니터링</a></li>
									<li class="li-a"><a href="/apiInfo/apiInfoMngmt.do">서버인증 이력</a></li>
								</ul>
							</li>
                        </ul>
                        <div class="filter-message js-filter-message bg-success-600"></div>

                        <script type="text/javascript">
                        var currentURI = '<c:out value="${currentURI}"/>';
                        $('.li-a > a').each(function(i){
                        	//console.log(currentURI.indexOf($('.li-a > a').eq(i).attr('href')));
                        	if(currentURI.indexOf($('.li-a > a').eq(i).attr('href')) > -1){
                        		$('.li-a > a').eq(i).parent().addClass('active');
                        		$('.li-a > a').eq(i).parent().parent().parent().addClass('active open');
                        	}
                        });
						</script>