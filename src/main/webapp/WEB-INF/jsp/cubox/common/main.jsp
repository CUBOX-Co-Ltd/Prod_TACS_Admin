<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/jsp/cubox/common/checkPasswd.jsp" flush="false"/>
<script type="text/javascript">
$(function(){
	$(".title_tx").html("모바일기기 등록 및 이동 현황");
	
	setInterval(function(){ 
		reload();
	 }, 1000);
});

//새로고침
function reload() {
	/* f = document.frmSearch;
	f.action = "/main.do"
	f.submit(); */
	 
	$('#deviceList').load(location.href+' #deviceList');
	<c:forEach items="${spotImageList}" var="list" varStatus="status">
	$('#spotList_${status.index}').load(location.href+' #spotList_${status.index}');
	</c:forEach>
}

</script>
<form id="frmSearch" name="frmSearch" method="post" onsubmit="return false;">
	<div class="mb_5">&nbsp;</div>
	<!--테이블 시작 -->
	<div class="tb_outbox">
	<div id="deviceList">
		<table class="tb_list">
			<tbody>
			<tr>
				<td width="50px" rowspan="2">전체</td>
				<c:forEach items="${deviceList}" var="result">
          		<td><img src="data:image/jpeg;base64,${result.image}" onerror="this.src='/img/photo_01_back.jpg'" style="width: 110px; object-fit: contain;"></td>
       		 	</c:forEach>
       		 	<c:forEach begin="${fn:length(deviceList)}" end="9">
       		 	<td><img src="/img/photo_01_back.jpg" style="width: 110px; object-fit: contain;"></td>
       		 	</c:forEach>
            </tr>
            <tr>
				<c:forEach items="${deviceList}" var="result">
          		<td>${result.deviceName}</td>
       		 	</c:forEach>
       		 	<c:forEach begin="${fn:length(deviceList)}" end="9">
       		 	<td>&nbsp;</td>
       		 	</c:forEach>            
            </tr>
			</tbody>
		</table>
	</div>
	</div>
	
	<c:forEach items="${spotImageList}" var="list" varStatus="status">
	<div>&nbsp;</div>
	<div class="tb_outbox">
	<div id="spotList_${status.index}">
		<table class="tb_list">
			<tbody>
			<tr>
				<td width="50px" style="word-break:break-all;" rowspan="2">${spotNameList[status.index]}</td>
				<c:forEach items="${list}" var="result">
          		<td><img src="data:image/jpeg;base64,${result.image}" onerror="this.src='/img/photo_01_back.jpg'" style="width: 110px; object-fit: contain;"></td>
       		 	</c:forEach>
       		 	<c:forEach begin="${fn:length(list)}" end="9">
       		 	<td><img src="/img/photo_01_back.jpg" style="width: 110px; object-fit: contain;"></td>
       		 	</c:forEach>
            </tr>
            <tr>
				<c:forEach items="${list}" var="result">
          		<td>${result.deviceName}
          		<fmt:parseDate value="${fn:substringBefore(result.updtDt, '+')}" var="dateValue" pattern="yyyy-MM-dd'T'HH:mm:ss.SSS"/>
				<fmt:formatDate value="${dateValue}" pattern="mm:ss"/>
          		
          		</td>
       		 	</c:forEach>
       		 	<c:forEach begin="${fn:length(list)}" end="9">
       		 	<td>&nbsp;</td>
       		 	</c:forEach>            
            </tr>
			</tbody>
		</table>
	</div>
	</div>
	</c:forEach>	

</form>
