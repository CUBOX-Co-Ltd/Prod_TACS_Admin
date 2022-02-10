<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
$(function() {
	$(".title_tx").html("이동 현황");
	
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
	f.action = "/systemInfo/deviceLocMngmt.do";
	f.submit();
}

function fnGetSpot(obj) {
	if(!obj.value) {
		$("#srchSpot").find("option").remove();
		$("#srchSpot").append("<option value=''>선택</option>");
		return;
	}
	
	$.ajax({
		type:"post",
		url:"<c:url value='/systemInfo/getSpotCombo.do' />",
		data:{
			"srchZone": obj.value
		},
		dataType:"json",
		success:function(result){
			$("#srchSpot").find("option").remove();
			$("#srchSpot").append("<option value=''>선택</option>");
			
			if(result != null){
				$.each(result.spotCombo, function(i){
					$("#srchSpot").append("<option value='" + result.spotCombo[i].id + "'>" 
							+ result.spotCombo[i].spotName + "</option>");
				});
			}
		}
	});
}

function deviceLocSearch(){
	if(fnIsEmpty($("#srchZone").val())){
		alert('Zone을 선택해 주세요');
		return;
	}
	
	if(fnIsEmpty($("#srchSpot").val())){
		alert('Spot을 선택해 주세요');
		return;
	}
	
	$("#srchPage").val("1");
	frmSearch.action = "/systemInfo/deviceLocMngmt.do";
	frmSearch.submit();
}

function resetSearch(){
	$("#srchZone").val("");
	$("#srchSpot").val("");
	$("#srchBeacon").val("");
}

</script>
<form id="frmSearch" name="frmSearch" method="post" onsubmit="return false;">
<input type="hidden" id="hidZoneId" name="hidZoneId" value="${zoneId}"/>
<input type="hidden" id="srchPage" name="srchPage" value="${pagination.curPage}"/>
	<div class="search_box mb_20">
		<div class="search_in_bline">
			<div class="comm_search  mr_5">
				<label for="search-from-date" class="title">기록일시</label>
				<input type="text" class="input_datepicker w_200px fl" name="startDate" id="startDate" value="${startDate}" placeholder="날짜">
				<div class="sp_tx fl">~</div>
				<label for="search-to-date"></label>
				<input type="text" class="input_datepicker w_200px fl" name="endDate" id="endDate" value="${endDate}" placeholder="날짜">
			</div>
			<div class="ch_box  mr_20">
				<label for="srchSpotHost" class="ml_20">Zone</label>
			</div>
			<div class="comm_search mr_20">
				<select name="srchZone" id="srchZone" size="1" class="w_150px input_com" onchange="fnGetSpot(this);">
				<option value=''>선택</option>
					<c:forEach items="${zoneCombo}" var="zCombo" varStatus="status">
	                      	<option value='<c:out value="${zCombo.id}"/>' 
	                      		<c:if test="${zCombo.id eq zoneId}">selected</c:if>>
	                      		<c:out value="${zCombo.zoneName}"/>
	                      	</option>
	                </c:forEach>
				</select>
			</div>
			<div class="ch_box  mr_20">
				<label for="srchSpotHost" class="ml_20">Spot</label>
			</div>
			<div class="comm_search mr_20">
				<select name="srchSpot" id="srchSpot" size="1" class="w_150px input_com">
				<option value=''>선택</option>
					<c:forEach items="${spotCombo}" var="sCombo" varStatus="status">
                      	<option value='<c:out value="${sCombo.id}"/>' 
                      		<c:if test="${sCombo.id eq spotId}">selected</c:if>>
                      		<c:out value="${sCombo.spotName}"/>
                      	</option>
	                </c:forEach>
				</select>
			</div>
			
			<div class="comm_search ml_60">
				<div class="search_btn2" title="검색" onclick="deviceLocSearch()"></div>
			</div>
			<div class="comm_search ml_65">
				<button type="button" class="comm_btn" id="reset" onclick="resetSearch();">초기화</button>
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
			<col width="5%" />
			<col width="10%" />
			<col width="10%" />
			<col width="20%" />
			<col width="15%" />
			<col width="10%" />
			<col width="15%" />
			<thead>
				<tr>
					<th>순번</th>
					<th>Zone</th>
					<th>Spot</th>
					<th>UUID</th>
					<th>이미지</th>
					<th>이름</th>
					<th>기록일시</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${deviceLocList == null || fn:length(deviceLocList) == 0}">
						<tr>
							<td class="h_35px" colspan="7">조회 목록이 없습니다.</td>
						</tr>
					</c:when> 
					<c:otherwise>
						<c:forEach items="${deviceLocList}" var="result" varStatus="status">
							<tr>
								<td>${(pagination.totRecord - (pagination.totRecord-status.index)+1)  + ( (pagination.curPage - 1)  *  pagination.recPerPage ) }</td>
								<td>${result.msSpot.msZone.zoneName}</td>
								<td>${result.msSpot.spotName}</td>
								<td>${result.msDevice.deviceUuid}</td>
								<td><img src="data:image/jpeg;base64,${result.msDevice.image}" alt="" onerror="this.src='/images/empty_photo.png'" style="width: 100px; height: 125px; object-fit: contain; "></td>
								<td>${result.msDevice.deviceName}</td>
								<td> 
									<fmt:parseDate value="${fn:substringBefore(result.updtDt, '+')}" var="dateValue" pattern="yyyy-MM-dd'T'HH:mm:ss.SSS"/>
									<fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
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
