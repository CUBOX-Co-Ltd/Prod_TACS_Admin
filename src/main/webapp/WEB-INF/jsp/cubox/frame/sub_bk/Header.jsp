<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 			uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" 	uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 			uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="cubox.admin.main.service.vo.LoginVO" %>

						<c:set var="currentPath" value="${requestScope['javax.servlet.forward.servlet_path']}" /> 
						
						<c:if test="${empty sessionScope.loginVO.fsiteid}">
							<script type="text/javascript">
							alert("로그인 하십시요.");
							location.href = "/login.do";
							</script>
						</c:if>
						
						<c:if test="${not empty sessionScope.loginVO.fsiteid}">						
							<c:if test="${sessionScope.loginVO.fpasswdyn == 'N'}">
							<c:if test="${fn:trim(currentPath) ne '/basicInfo/pwChange.do'}">
							<script type="text/javascript">
							alert("비밀번호를 변경하셔야 사이트를 이용할수 있습니다.");
							location.href = "/basicInfo/pwChange.do";
							</script>
							</c:if>
							</c:if> 
						</c:if>
						
						<c:if test="${not empty sessionScope.loginVO.fsiteid}">						
							<c:if test="${sessionScope.loginVO.fdatediff > 90}">
							<c:if test="${fn:trim(currentPath) ne '/basicInfo/pwChange.do'}">
							<script type="text/javascript">
							alert("비밀번호를 변경하신시 90일이 경과되었습니다.\n비밀번호를 변경하셔야 사이트를 이용할수 있습니다.");
							location.href = "/basicInfo/pwChange.do";
							</script>
							</c:if>
							</c:if> 
						</c:if>
												
						<div class="page-logo">
                            <a href="#" class="page-logo-link press-scale-down d-flex align-items-center position-relative">
                                <img src="/images/top-logo.png" alt="CUBOX">
                                <span class="page-logo-text mr-1">CUBOX</span>
                                <span class="position-absolute text-white opacity-50 small pos-top pos-right mr-2 mt-n2"></span>
                                <i class="fa fa-angle-down d-inline-block ml-1 fs-lg color-primary-300"></i>
                            </a> 
                        </div>
                        <!-- 햄버거 버튼 pc -->
                        <div class="hidden-md-down dropdown-icon-menu position-relative">
                            <a href="#" class="header-btn btn js-waves-off" data-action="toggle" data-class="nav-function-hidden" title="메뉴 닫기">
                                <i class="fa fa-bars"></i>
                            </a>
                        </div>
                        <!-- 햄버거 버튼 mobile -->
                        <div class="hidden-lg-up">
                            <a href="#" class="header-btn btn press-scale-down" data-action="toggle" data-class="mobile-nav-on">
                                <i class="fa fa-bars"></i>
                            </a>
                        </div> 
                        <div class="ml-auto d-flex">
                            <!-- login user -->
                            <span class="header-text">[<c:out value="${sessionScope.loginVO.fvalue}"/>] <c:out value="${sessionScope.loginVO.fname}"/> (<c:out value="${sessionScope.loginVO.fsiteid}"/>)</span>
							<!-- logout -->
							<a href="/basicInfo/pwChange.do" class="header-text-a">비밀번호 변경</a>
                            <a href="/logout.do" class="header-icon" title="로그아웃">
								<i class="fa fa-power-off"></i>
							</a>
                        </div>