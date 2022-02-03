<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 			uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" 	uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 			uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="cubox.admin.main.service.vo.LoginVO" %>
<%
	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
%>
<c:if test="${sessionScope.loginVO.femergency != 'E' && sessionScope.loginVO.femergency != 'C' }">
<jsp:forward page="/userInfo/userMngmt.do"/>
</c:if>
<c:if test="${sessionScope.loginVO.femergency == 'E' }">
<jsp:forward page="/emergency/emergencyMngmt.do"/>
</c:if>
<c:if test="${sessionScope.loginVO.femergency == 'C' }">
<jsp:forward page="/emergency/certMngmt.do"/>
</c:if>



						<div class="subheader">
							<div class="title-bar float_wrap">
								<h1>모니터링 </h1>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div id="panel-1" class="panel">
                                    <div class="panel-hdr">
                                        <h2>본관1층</h2>
                                        <div class="panel-toolbar">
                                            <button class="btn btn-panel " data-action="panel-collapse" data-toggle="tooltip" data-offset="0,10" data-original-title="축소"></button>
                                            <button class="btn btn-panel " data-action="panel-fullscreen" data-toggle="tooltip" data-offset="0,10" data-original-title="화면확대"></button>
                                        </div>
                                    </div>
                                    <div class="panel-container collapse show" style="">
                                        <div class="panel-content">
                                            <div class="state_list">
                                                <ul>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_triangle color-red"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_triangle color-blue"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_triangle color-green"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_triangle color-orange"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_triangle color-violet"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_bell color-red"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_bell color-blue"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_bell color-green"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_bell color-orange"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_bell color-violet"></span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
							</div>
						</div>
                        <div class="row">
							<div class="col-md-12">
								<div id="panel-1" class="panel">
                                    <div class="panel-hdr">
                                        <h2>본관2층</h2>
                                        <div class="panel-toolbar">
                                            <button class="btn btn-panel " data-action="panel-collapse" data-toggle="tooltip" data-offset="0,10" data-original-title="축소"></button>
                                            <button class="btn btn-panel " data-action="panel-fullscreen" data-toggle="tooltip" data-offset="0,10" data-original-title="화면확대"></button>
                                        </div>
                                    </div>
                                    <div class="panel-container collapse show" style="">
                                        <div class="panel-content">
                                            <div class="state_list">
                                                <ul>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_triangle bgc-red"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_triangle bgc-blue"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_triangle bgc-green"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_triangle bgc-orange"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_triangle bgc-violet"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_bell bgc-red"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_bell bgc-blue"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_bell bgc-green"></span>
                                                    </li> 
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_bell bgc-orange"></span>
                                                    </li>
                                                    <li>
                                                        <span class="gate_num">61(이름 : 홍길동)</span>
                                                        <span class="gate_icon_bell bgc-violet"></span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
							</div>
						</div>