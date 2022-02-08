<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/jsp/cubox/common/checkPasswd.jsp" flush="false"/>

<script type="text/javascript">

$(function(){
	
	$(".title_tx").html("등록기기 목록");
	
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
				<c:forEach items="${deviceList}" var="result" varStatus="status">
					<c:if test="${i%j == 0 }">
						<tr>
            		</c:if>
                 		<td><img width="100px" src="data:image/jpeg;base64,${result.image}" onerror="this.src='/img/photo_01_back.jpg'"><br>${result.deviceName}</td>
            		<c:if test="${i%j == j-1 }">
                		</tr>
           		 	</c:if>
           		 <c:set var="i" value="${i+1}" />
       		 </c:forEach>
			</tbody>
		</table>
	</div>
	<!--------- //목록--------->

</div>

</div>
</form>
