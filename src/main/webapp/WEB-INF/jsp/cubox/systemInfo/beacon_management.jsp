<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">
$(function() {
	$(".title_tx").html("이동현황");
});

function pageSearch(page){
	$("#srchPage").val(page);
	f = document.frmSearch;
	f.action = "/systemInfo/deviceMngmt.do";
	f.submit();
}

function fnGetSpot(obj) {
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

function beaconSearch(){
	if(fnIsEmpty($("#srchSpot").val())){
		alert('Spot 을 선택해 주세요');
		return;
	}	
	
	$("#srchPage").val("1");
	frmSearch.action = "/systemInfo/beaconMngmt.do";
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
			<div class="ch_box  mr_20">
				<label for="srchSpotHost" class="ml_10"> zone</label>
			</div>
			<div class="comm_search mr_20">
				<select name="srchZone" id="srchZone" size="1" class="w_100px input_com" onchange="fnGetSpot(this);">
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
				<label for="srchSpotHost" class="ml_10"> spot</label>
			</div>
			<div class="comm_search mr_20">
				<select name="srchSpot" id="srchSpot" size="1" class="w_100px input_com">
				<option value=''>선택</option>
					<c:forEach items="${spotCombo}" var="sCombo" varStatus="status">
                      	<option value='<c:out value="${sCombo.id}"/>' 
                      		<c:if test="${sCombo.id eq spotId}">selected</c:if>>
                      		<c:out value="${sCombo.spotName}"/>
                      	</option>
	                </c:forEach>
				</select>
			</div>
			
			<div class="ch_box  mr_20">
				<label for="srchSpotName" class="ml_10"> 비콘 MAJOR</label>
			</div>
			<div class="comm_search mr_20">
				<input type="text" class="w_200px input_com" id="srchBeacon" name="srchBeacon" value="${srchBeacon}"/>
			</div>
			
			<div class="comm_search ml_60">
				<div class="search_btn2" title="검색" onclick="beaconSearch()"></div>
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
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<thead>
				<tr>
					<th>일련번호</th>
					<th>SPOT</th>
					<th>비콘 MAJOR</th>
					<th>기록일시</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${beaconList == null || fn:length(beaconList) == 0}">
						<tr>
							<td class="h_35px" colspan="11">조회 목록이 없습니다.</td>
						</tr>
					</c:when> 
					<c:otherwise>
						<c:forEach items="${beaconList}" var="result" varStatus="status">
							<tr>
								<td> ${result.id}</td>
								<td> ${spotId}</td>
								<td> ${result.majorNo}</td>
								<td> ${result.registDt}</td>
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
