<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<script type="text/javascript">
$(function() {
	$(".title_tx").html("모바일기기 등록 현황");
	
	$('#startDate').datetimepicker({
		format:'Y-m-d H:i'
	});
	
	$('#endDate').datetimepicker({
		format:'Y-m-d H:i'
	});
});

function pageSearch(page){
	$("#srchPage").val(page);
	f = document.frmSearch;
	f.action = "/systemInfo/deviceMngmt.do";
	f.submit();
}
function deviceSearch(){
	$("#srchPage").val("1");
	f = document.frmSearch;
	f.action = "/systemInfo/deviceMngmt.do";
	f.submit();
}

</script>
<form id="frmSearch" name="frmSearch" method="post" onsubmit="return false;">
<input type="hidden" id="srchPage" name="srchPage" value="${pagination.curPage}"/>
	<div class="search_box mb_20">
		<div class="search_in_bline">
			<div class="comm_search  mr_20">
				<label for="search-from-date" class="title">수정일시</label>
				<input type="text" class="input_datepicker w_200px  fl" autocomplete="off" name="startDate" id="startDate" value="${startDate}" placeholder="날짜,시간">
				<div class="sp_tx fl">~</div>
				<label for="search-to-date"></label>
				<input type="text" class="input_datepicker w_200px fl" name="endDate" id="endDate" value="${endDate}" placeholder="날짜">
			</div>
			<div class="comm_search mr_20">
				<div class="title">모바일기기 이름</div>
				<input type="text" class="w_200px input_com" id="srchCondWord" name="srchCondWord" value='<c:out value="${srchCondWord}"/>' />
			</div>
			<div class="comm_search ml_60">
				<div class="search_btn2" title="검색" onclick="deviceSearch()"></div>
			</div>
			<div class="comm_search ml_65">
				<button type="button" class="comm_btn" id="reset">초기화</button>
			</div>
		</div>
	</div>
</form>
<!--//검색박스 -->
<!--------- 목록--------->
<div class="com_box ">
	<div class="totalbox" style="width: 50%;">
		<div class="r_btnbox  mb_10">
		</div>
	</div>
	<!--테이블 시작 -->
	<div class="tb_outbox">
		<table class="tb_list">
			<col width="7%" />
			<col width="26%" />
			<col width="15%" />
			<col width="15%" />
			<col width="15%" />
			<col width="15%" />
			<col width="7%" />
			<thead>
				<tr>
					<th>순번</th>
					<th>모바일기기 UUID</th>
					<th>등록 이미지</th>
					<th>모바일기기 이름</th>
					<th>등록일시</th>
					<th>수정일시</th>
					<th>삭제여부</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${deviceList == null || fn:length(deviceList) == 0}">
						<tr>
							<td class="h_35px" colspan="7">조회 목록이 없습니다.</td>
						</tr>
					</c:when> 
					<c:otherwise>
						<c:forEach items="${deviceList}" var="result" varStatus="status">
							<tr>
								<%-- <td> ${result.id}</td> --%>
								<td>${(pagination.totRecord - (pagination.totRecord-status.index)+1)  + ( (pagination.curPage - 1)  *  pagination.recPerPage ) }</td>
								<td> ${result.deviceUuid}</td>
								<td><img src="data:image/jpeg;base64,${result.image}" onerror="this.src='/img/photo_01_back.jpg'" style="width: 110px; object-fit: contain;"></td>
								<td> ${result.deviceName}</td>
								<td> 
									<fmt:parseDate value="${fn:substringBefore(result.registDt, '+')}" var="dateValue" pattern="yyyy-MM-dd'T'HH:mm:ss.SSS"/>
									<fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td> 
									<fmt:parseDate value="${fn:substringBefore(result.updtDt, '+')}" var="dateValue" pattern="yyyy-MM-dd'T'HH:mm:ss.SSS"/>
									<fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td> ${result.deleteYn}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	<!--------- //목록--------->
	<!-- 페이징 -->
	<jsp:include page="/WEB-INF/jsp/cubox/common/pagination.jsp" flush="false"/>
	<!-- /페이징 -->
</div>
