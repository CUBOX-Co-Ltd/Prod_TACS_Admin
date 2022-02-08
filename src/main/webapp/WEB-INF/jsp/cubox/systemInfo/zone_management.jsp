<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
$(function() {
	$(".title_tx").html("Zone 목록");
	

	$("#btnAddZone").on("click", function(event){
        $("#add-zone-modal").PopupWindow("open");
    });
	
	modalPopup ("add-zone-modal", "Zone 추가", 550, 300);
	modalPopup ("edit-zone-modal", "Zone 상세보기", 550, 330);
	
	
	
});

function fnZoneAdd () {
	if(!fnFormValueCheck("frmAdd")) return;
	
	var txtName = $("#txtName").val();
	var txtHost01 = $("#txtHost01").val();
	var txtHost02 = $("#txtHost02").val();
	var txtHost03 = $("#txtHost03").val();
	var txtHost04 = $("#txtHost04").val();
	
	var txtHost = txtHost01 + "." + txtHost02 + "." + txtHost03 + "." + txtHost04;
	
	
	showLoading();
	$.ajax({
		url: "<spring:eval expression="@property['Globals.api.url']"/>/zone",
		data: JSON.stringify({
			"zoneName": txtName,
			"zoneHost": txtHost
		}),
		dataType:'json',
		type: "POST",
		contentType: "application/json",
		
		success:function(result){
			if(result.status == "200"){
				location.reload();
			} else {
				alert(returnData.message);
				hideLoading();
			}
		}
	});
}

function fnEditPop(id, uuid, name, host) {
	$("#edit-zone-modal").PopupWindow("open");
	$("#hiddenId").val(id);
	$("#editUuid").val(uuid);
	$("#editName").val(name);
	
	if(!fnIsEmpty(host)) {
		var sHost = host.split('.');
		$("#editHost01").val(sHost[0]);
		$("#editHost02").val(sHost[1]);
		$("#editHost03").val(sHost[2]);
		$("#editHost04").val(sHost[3]);
	}
}

function fnZoneEdit () {
	if(!fnFormValueCheck("frmEdit")) return;
	
	var editUuid = $("#editUuid").val();
	var editName = $("#editName").val();
	
	
	var editHost01 = $("#editHost01").val();
	var editHost02 = $("#editHost02").val();
	var editHost03 = $("#editHost03").val();
	var editHost04 = $("#editHost04").val();
	
	var editHost = editHost01 + "." + editHost02 + "." + editHost03 + "." + editHost04;
	
	var zoneId = $("#hiddenId").val();
	
	var send = "id="+zoneId+"&zoneUuid="+editUuid+"&zoneName="+editName+"&zoneHost="+editHost;
	
	if(fnIsEmpty(editName)) {
		alert("이름을 입력하세요.");
		$("#editName").focus();
		return;
	}



	showLoading();
	$.ajax({
		url: "<spring:eval expression="@property['Globals.api.url']"/>/zone/"+zoneId,
		data: JSON.stringify({
			"id" : zoneId,
			"zoneUuid": editUuid,
			"zoneName": editName,
			"zoneHost": editHost
		}),
		dataType:'json',
		type: "POST",
		contentType: "application/json",
		success:function(result){
			if(result.status == "200"){
				location.reload();
			} else {
				alert(returnData.message);
				hideLoading();
			}
		}
	});
}


function fnZoneDelete () {
//	if(!fnFormValueCheck("frmAdd")) return;
	
	var zoneId = $("#hiddenId").val();

	showLoading();
	$.ajax({
		url: "<spring:eval expression="@property['Globals.api.url']"/>/zone/"+zoneId,
		type: "DELETE",
		success:function(result){
			if(result.status == "200"){
				location.reload();
			} else {
				alert(returnData.message);
				hideLoading();
			}
		}
	});
}

function fnEditCancel(){
	$("#edit-zone-modal").PopupWindow("close");
}

function fnAddCancel(){
	$("#add-zone-modal").PopupWindow("close");
}

function resetSearch(){
	 $("#srchCondWord").val("");
}

function zoneSearch() {
	if(!fnIsEmpty($("#srchFromDt").val()) && !fnIsEmpty($("#srchToDt").val())) {
		if($("#srchFromDt").val() > $("#srchToDt").val()){
			alert('시작일이 만료일보다 큽니다. 다시 입력해 주세요');
			$("input[name=srchFromDt]").focus();
			return;
		}	
	}	
	
	$("#srchPage").val("1");
	frmSearch.action = "/systemInfo/zoneMngmt.do";
	frmSearch.submit();
}

function pageSearch(page){
	$("#srchPage").val(page);
	f = document.frmSearch;
	f.action = "/systemInfo/zoneMngmt.do";
	f.submit();
}
</script>
<form id="frmSearch" name="frmSearch" method="post" onsubmit="return false;">
<input type="hidden" id="srchPage" name="srchPage" value="${pagination.curPage}"/>
	<div class="search_box mb_20">
		<div class="search_in_bline">
			<!-- <div class="comm_search  mr_5">
				<label for="search-from-date" class="title">등록일</label>
				<input type="text" class="input_datepicker w_200px fl" name="srchStartDate" id="startDatetimepicker" value="${logInfoVO.srchStartDate}" placeholder="날짜,시간">
				<div class="sp_tx fl">~</div>
				<label for="search-to-date"></label>
				<input type="text" class="input_datepicker w_200px fl" name="srchExpireDate" id="endDatetimepicker" value="${logInfoVO.srchExpireDate}" placeholder="날짜,시간">
			</div> -->
			<div class="ch_box  mr_20">
				<label for="srchFunm" class="ml_10"> 이름</label>
			</div>
			<div class="comm_search mr_20">
				<input type="text" class="w_200px input_com" id="srchCondWord" name="srchCondWord" value='<c:out value="${srchCondWord}"/>' />
			</div>
			<div class="comm_search ml_60">
				<div class="search_btn2" title="검색" onclick="zoneSearch()"></div>
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
	<div class="r_btnbox  mb_10">
		<button type="button" class="btn_middle color_basic" id="btnAddZone">추가</button>
	</div>
	<!--버튼 -->

	<!--//버튼  -->
	<!--테이블 시작 -->
	<div class="tb_outbox">
		<table class="tb_list">
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			
			<thead>
				<tr>
					<th>일련번호</th>
					<th>UUID</th>
					<th>이름</th>
					<th>HOST</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${zoneList == null || fn:length(zoneList) == 0}">
						<tr>
							<td class="h_35px" colspan="11">조회 목록이 없습니다.</td>
						</tr>
					</c:when> 
					<c:otherwise>
						<c:forEach items="${zoneList}" var="result" varStatus="status">
							<tr>
								<td> ${result.id}</td>
								<td> <a class="nav-link" onclick="fnEditPop('${result.id}','${result.zoneUuid}','${result.zoneName}','${result.zoneHost}')">${result.zoneUuid}</a></td>
								<td> ${result.zoneName}</td>
								<td> ${result.zoneHost}</td>
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



<!-- modal : 등록 -->
<div id="add-zone-modal" class="example_content">
<form id="frmAdd" name="frmAdd" method="post">

	<div class="popup_box">
		<!--테이블 시작 -->
		<div class="tb_outbox mb_20">
			<table class="tb_write_02 tb_write_p1">
				<col width="30%" />
				<col width="70%" />
				<tbody>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" id="txtName" name="txtName" maxlength="20" class="w_190px input_com" check="text" checkName="이름"/>
						</td>
					</tr>
					<tr>
						<th>HOST</th>
						<td>
							<input type="text" id="txtHost01" name="txtHost01" maxlength="3" class="w_70px input_com fl" onkeyup="fnvalichk(event)" check="text" checkName="Host"/>
							<input type="text" id="txtHost02" name="txtHost02" maxlength="3" class="w_70px input_com fl ml_5" onkeyup="fnvalichk(event)" check="text" checkName="Host"/>
							<input type="text" id="txtHost03" name="txtHost03" maxlength="3" class="w_70px input_com fl ml_5" onkeyup="fnvalichk(event)" check="text" checkName="Host"/>
							<input type="text" id="txtHost04" name="txtHost04" maxlength="3" class="w_70px input_com fl ml_5" onkeyup="fnvalichk(event)" check="text" checkName="Host"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--버튼 -->
		
	   	<div class="r_btnbox">
			<div style="display: inline-block;">
				<button type="button" class="comm_btn mr_5" onclick="fnZoneAdd();">추가</button>
				<button type="button" class="bk_color comm_btn mr_5" onclick="fnAddCancel();">취소</button>
			</div>
		</div>
		<!--//버튼  -->
	</div>
</form>
</div>



<!-- modal : 수정 -->
<div id="edit-zone-modal" class="example_content">
<form id="frmEdit" name="frmEdit" method="post">
<input type="hidden" id="hiddenId" name="hiddenId"/>
	<div class="popup_box">
		<!--테이블 시작 -->
		<div class="tb_outbox mb_20">
			<table class="tb_write_02 tb_write_p1">
				<col width="30%" />
				<col width="70%" />
				<tbody>
					<tr>
						<th>UUID</th>
						<td>
							<input type="text" id="editUuid" name="editUuid" maxlength="20" class="w_190px input_com" check="text" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" id="editName" name="editName" maxlength="20" class="w_190px input_com" check="text" checkName="이름"/>
						</td>
					</tr>
					<tr>
						<th>HOST</th>
						<td>
							<input type="text" id="editHost01" name="editHost01" maxlength="3" class="w_70px input_com fl" onkeyup="fnvalichk(event)" check="text" checkName="Host"/>
							<input type="text" id="editHost02" name="editHost02" maxlength="3" class="w_70px input_com fl ml_5" onkeyup="fnvalichk(event)" check="text" checkName="Host"/>
							<input type="text" id="editHost03" name="editHost03" maxlength="3" class="w_70px input_com fl ml_5" onkeyup="fnvalichk(event)" check="text" checkName="Host"/>
							<input type="text" id="editHost04" name="editHost04" maxlength="3" class="w_70px input_com fl ml_5" onkeyup="fnvalichk(event)" check="text" checkName="Host"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--버튼 -->
		
	   	<div class="r_btnbox ">
			<div style="display: inline-block;">
				<button type="button" class="comm_btn mr_5" onclick="fnZoneEdit();">수정</button>
				<button type="button" class="comm_btn mr_5" onclick="fnZoneDelete();">삭제</button>
				<button type="button" class="bk_color comm_btn mr_5" onclick="fnEditCancel();">취소</button>
			</div>
		</div>
		<!--//버튼  -->
	</div>
</form>
</div>


