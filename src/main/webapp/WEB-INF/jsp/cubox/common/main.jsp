<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/jsp/cubox/common/checkPasswd.jsp" flush="false"/>

<script type="text/javascript">

$(function(){
	
	$(".title_tx").html("기기등록 및 이동현황");
	
	setInterval(function(){ 
		reload();
	 }, 4000);

	
});

//새로고침
function reload() {
	/*f = document.frmSearch;
	f.action = "/main.do"
	f.submit();*/
	
	$('#deviceList').load(location.href+' #deviceList');
	$('#spotList').load(location.href+' #spotList');
}
	


</script>
<form id="frmSearch" name="frmSearch" method="post" onsubmit="return false;">
<div class="main_a">

<div class="com_box ">
	<div class="totalbox" style="width: 50%;">
		<div class="r_btnbox  mb_10">
		</div>
	</div>
	<c:set var="i" value="0" />
	<c:set var="j" value="10" />
	<!--테이블 시작 -->
	<div class="tb_outbox" id="deviceList">
		<table class="tb_list">
			<tbody>
			<tr>
				<td width="50px">전체</td>
				<c:if test="${fn:length(deviceList) == 0}">
					<c:forEach begin="1" end="10">
		       		 	<td><img width="100px" src="/img/photo_01_back.jpg"></td>
		       		 </c:forEach>
				</c:if>
				<c:forEach items="${deviceList}" var="result" varStatus="status">
                 		<td><img width="100px" src="data:image/jpeg;base64,${result.image}" onerror="this.src='/img/photo_01_back.jpg'"><br>${result.deviceName}</td>
            		<c:if test="${i%j == j-1 }">
                		</tr>
           		 	</c:if>
           		 <c:set var="i" value="${i+1}" />
       		 	</c:forEach>
       		  <c:if test="${i > 0}">
		       		 <c:forEach begin="1" end="${10-i}">
		       		 	<td><img width="100px" src="/img/photo_01_back.jpg"></td>
		       		 </c:forEach>
	       		 </c:if>
			</tbody>
		</table>
	</div>
		
	<div class="tb_outbox" id="spotList">
		<c:forEach items="${spotImageList}" var="list" varStatus="status2">
		<c:set var="a" value="0" />
		<c:set var="b" value="10" />
			<table class="tb_list">
				<tbody>
					<tr>
					<c:if test="${status2.index == 0}">
						<td width="50px">쇼룸</td>
					</c:if>
					<c:if test="${status2.index == 1}">
						<td width="50px">휴게실</td>
					</c:if>
					<c:if test="${fn:length(list) == 0}">
					<c:forEach begin="1" end="10">
		       		 	<td><img width="100px" src="/img/photo_01_back.jpg"></td>
		       		 </c:forEach>
					</c:if>
					<c:forEach items="${list}" var="result" varStatus="status">	
	                 	<td><img width="100px" src="data:image/jpeg;base64,${result.image}" onerror="this.src='/img/photo_01_back.jpg'"><br>${result.deviceName}</td>
	           		 <c:set var="a" value="${a+1}" />
	       		 	</c:forEach>
	       		 <c:if test="${a > 0}">
		       		 <c:forEach begin="1" end="${10-a}">
		       		 	<td><img width="100px" src="/img/photo_01_back.jpg"></td>
		       		 </c:forEach>
	       		 </c:if>
	       		 </tr>
				</tbody>
			</table>
		</c:forEach>
		</div>
	</div>
</div>
</form>
