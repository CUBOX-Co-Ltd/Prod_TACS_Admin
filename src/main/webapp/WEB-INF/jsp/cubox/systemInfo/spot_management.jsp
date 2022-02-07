<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">

var globalIp = '172.16.150.14';
var globalPort = '8080';

$(function() {
	$(".title_tx").html("Spot 목록");
	
	$("#btnAddSpot").on("click", function(event){
        $("#add-spot-modal").PopupWindow("open");
    });
	
	$("#btnEditSpot").on("click", function(event){
		$("#edit-spot-modal").PopupWindow("open");
    });
	
	$("#btnAddBeacon").on("click", function(event){
		$("#add-beacon-modal").PopupWindow("open");
    });
	
	$("#btnEditClose").on("click", function(event){
		$("#edit-spot-modal").PopupWindow("close");
    });
	
	$("#btnBeaconClose").on("click", function(event){
		$("#add-beacon-modal").PopupWindow("close");
    });
	
	modalPopup ("add-spot-modal", "Spot 추가", 550, 450);
	modalPopup ("edit-spot-modal", "Spot 상세보기", 550, 600);
	modalPopup ("add-beacon-modal", "Beacon 추가", 550, 400);

});

function fnSpotAddSave(){
	var id = $("#srchZone").val();
	
	var txtName = $("#txtName").val();
	var txtHost01 = $("#txtHost01").val();
	var txtHost02 = $("#txtHost02").val();
	var txtHost03 = $("#txtHost03").val();
	var txtHost04 = $("#txtHost04").val();
	
	var txtHost = txtHost01 + "." + txtHost02 + "." + txtHost03 + "." + txtHost04;
	
	
	showLoading();
	$.ajax({
		url: 'http://' + globalIp + ':' + globalPort + '/tacsm/v1/admin/spot',
		data: JSON.stringify({
			"zoneId" : id,
			"spotName": txtName,
			"frsHost": txtHost
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

function fnEditPop(id) {
	$("#edit-spot-modal").PopupWindow("open");
	
	$.ajax({
		url: 'http://' + globalIp + ':' + globalPort + '/tacsm/v1/admin/spot/'+id,
		dataType:'json',
		type: "GET",
		contentType: "application/json",
		
		success:function(result){
			if(result.status == "200"){
				$("#editZone").val(result.data.msZone.id);
				$("#editUuid").val(result.data.spotUuid);
				$("#editName").val(result.data.spotName);
				$("#editExpiredTime").val(result.data.msZone.updtDt);
				$("#editRegistTime").val(result.data.msZone.registDt);
				$("#hidSpotId").val(id);
				var host = result.data.frsHost;
				if(!fnIsEmpty(host)) {
					var sHost = host.split('.');
					$("#editHost01").val(sHost[0]);
					$("#editHost02").val(sHost[1]);
					$("#editHost03").val(sHost[2]);
					$("#editHost04").val(sHost[3]);
				}
			} else {
				alert(returnData.message);
				hideLoading();
			}
		}
	});
	
	$.ajax({
		url: 'http://' + globalIp + ':' + globalPort + '/tacsm/v1/admin/spot/'+id+'/beacon?page=0&pageSize=20',
		dataType:'json',
		type: "GET",
		contentType: "application/json",
		success:function(result){
			if(result.status == "200"){
				var content = "";
				innerHtml = "";
				for(var i = 0; i<result.data.content.length; i++){
					content = result.data.content[i];
					innerHtml+= "<tr>";
					innerHtml+= "<td><input type='text' class='w_200px input_com' id='editBeaconMajor' name='editBeaconMajor' value='"+content.majorNo+"'/></td>";
					innerHtml+= "<td><input type='text' class='w_200px input_com' id='editBeaconMemo' name='editBeaconMemo' value='"+content.beaconMemo+"'/></td>";
					innerHtml+= "<td><button type='button' class='comm_btn' onClick='fnBeaconSave("+content.id+","+id+")'>저장</button></td>";
					innerHtml+= "<td><button type='button' class='comm_btn' onClick='fnBeaconDelete("+content.id+","+id+")'>삭제</button></td>";
					innerHtml+= "</tr>";
				}
				$("#beconList").html(innerHtml);
				
			} else {
				alert(returnData.message);
				hideLoading();
			}
		}
	});
}

function fnSpotEditSave(){
	var spotId = $("#hidSpotId").val();
	
	var editZone = $("#editZone").val();
	var editUuid = $("#editUuid").val();
	var editName = $("#editName").val();
	
	var editHost01 = $("#editHost01").val();
	var editHost02 = $("#editHost02").val();
	var editHost03 = $("#editHost03").val();
	var editHost04 = $("#editHost04").val();
	
	var editHost = editHost01 + "." + editHost02 + "." + editHost03 + "." + editHost04;
	
	$.ajax({
		url: 'http://' + globalIp + ':' + globalPort + '/tacsm/v1/admin/spot/'+spotId,
		dataType:'json',
		type: "POST",
		data: JSON.stringify({
			"id" : spotId,
			"zoneId": editZone,
			"spotName": editName,
			"frsHost": editHost
		}),
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

function fnSpotDelete(){
	var spotId = $("#hidSpotId").val();
	showLoading();
	$.ajax({
		url: 'http://' + globalIp + ':' + globalPort + '/tacsm/v1/admin/spot/'+spotId,
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

function fnBeaconSave(beaconId,spotId){
	var beaconMemo = $("#editBeaconMemo").val();
	var beaconMajor = $("#editBeaconMajor").val();

	showLoading();
	$.ajax({
		url: 'http://' + globalIp + ':' + globalPort + '/tacsm/v1/admin/spot/'+spotId+'/beacon/'+beaconId,
		dataType:'json',
		type: "POST",
		data: JSON.stringify({
			"majorNo" : beaconMajor,
			"beaconMemo" : beaconMemo
		}),
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

function fnBeaconAddSave(){
	var beaconMemo = $("#txtbeaconMemo").val();
	var majorNo = $("#txtMajorNo").val();
	var spotId = $("#hidSpotId").val();

	showLoading();
	$.ajax({
		url: 'http://' + globalIp + ':' + globalPort + '/tacsm/v1/admin/spot/'+spotId+'/beacon',
		data: JSON.stringify({
			"majorNo" : majorNo,
			"beaconMemo" : beaconMemo,
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

function fnBeaconDelete(beaconId,spotId){
	$.ajax({
		url: 'http://' + globalIp + ':' + globalPort + '/tacsm/v1/admin/spot/'+spotId+'/beacon/'+beaconId,
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

function spotSearch() {
	if(!fnIsEmpty($("#srchFromDt").val()) && !fnIsEmpty($("#srchToDt").val())) {
		if($("#srchFromDt").val() > $("#srchToDt").val()){
			alert('시작일이 만료일보다 큽니다. 다시 입력해 주세요');
			$("input[name=srchFromDt]").focus();
			return;
		}	
	}	
	
	$("#srchPage").val("1");
	frmSearch.action = "/systemInfo/spotMngmt.do";
	frmSearch.submit();
}

function pageSearch(page){
	$("#srchPage").val(page);
	f = document.frmSearch;
	f.action = "/systemInfo/spotMngmt.do";
	f.submit();
}

function fnvalichk(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if (keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39)
		return;
	else {
		var tVal = event.target.value;
		var regx = /^[0-9]{0,3}$/g;
		if (!fnIsEmpty(tVal) && !regx.test(tVal)) {
			tVal = tVal.replace(/[^0-9]/g, "");
			event.target.value = fnIsEmpty(tVal) ? "" : tVal.substring(
					0, 3);
		}
	}
}


function resetSearch(){
	 $("#srchCondName").val("");
	 $("#srchCondHost").val("");
	 $("#srchZone").val("");
}


</script>
<form id="frmSearch" name="frmSearch" method="post" onsubmit="return false;">
<input type="hidden" id="hidZoneId" name="hidZoneId" value="${zoneId}"/>
<input type="hidden" id="srchPage" name="srchPage" value="${pagination.curPage}"/>
	<div class="search_box mb_20">
		<div class="search_in_bline">
			<!-- <div class="comm_search  mr_5">
				<label for="search-from-date" class="title">등록일</label>
				<input type="text" class="input_datepicker w_200px fl" name="srchStartDate" id="startDatetimepicker" placeholder="날짜">
				<div class="sp_tx fl">~</div>
				<label for="search-to-date"></label>
				<input type="text" class="input_datepicker w_200px fl" name="srchExpireDate" id="endDatetimepicker" placeholder="날짜">
			</div> -->
			<div class="ch_box  mr_20">
				<label for="srchSpotName" class="ml_10"> 이름</label>
			</div>
			<div class="comm_search mr_20">
				<input type="text" class="w_200px input_com" id="srchCondName" name="srchCondName" value="${srchCondName}"/>
			</div>
			<div class="ch_box  mr_20">
				<label for="srchSpotHost" class="ml_10"> host</label>
			</div>
			<div class="comm_search mr_20">
				<input type="text" class="w_200px input_com" id="srchCondHost" name="srchCondHost" value="${srchCondHost}" placeholder="127.0.0.1"/>
			</div>
			<div class="ch_box  mr_20">
				<label for="srchSpotHost" class="ml_10"> zone</label>
			</div>
			<div class="comm_search mr_20">
				<select name="srchZone" id="srchZone" size="1" class="w_100px input_com">
					<c:forEach items="${zoneCombo}" var="zCombo" varStatus="status">
	                      	<option value='<c:out value="${zCombo.id}"/>' 
	                      		<c:if test="${zCombo.id eq zoneId}">selected</c:if>>
	                      		<c:out value="${zCombo.zoneName}"/>
	                      	</option>
	                </c:forEach>
				</select>
			</div>
			<div class="comm_search ml_60">
				<div class="search_btn2" title="검색" onclick="spotSearch()"></div>
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
		<button type="button" class="btn_middle color_basic" id="btnAddSpot">추가</button>
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
					<th>ZONE</th>
					<th>UUID</th>
					<th>이름</th>
					<th>FRS_HOST</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${spotList == null || fn:length(spotList) == 0}">
						<tr>
							<td class="h_35px" colspan="11">조회 목록이 없습니다.</td>
						</tr>
					</c:when> 
					<c:otherwise>
						<c:forEach items="${spotList}" var="result" varStatus="status">
							<tr>
								<td> ${result.id}</td>
								<td> ${zoneId}</td>
								<td> <a class="nav-link" onclick="fnEditPop('${result.id}')">${result.spotUuid}</a></td>
								<td> ${result.spotName}</td>
								<td> ${result.frsHost}</td>
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
<div id="add-spot-modal" class="example_content">
<form id="frmAdd" name="frmAdd" method="post">
	<div class="popup_box">
		<!--테이블 시작 -->
		<div class="tb_outbox mb_20">
			<table class="tb_write_02 tb_write_p1">
				<col width="30%" />
				<col width="70%" />
				<tbody>
					<tr>
						<th>ZONE</th>
						<td>
							<select name="srchZone" id="srchZone" size="1" class="w_100px input_com">
								<c:forEach items="${zoneCombo}" var="zCombo" varStatus="status">
	                      			<option value='<c:out value="${zCombo.id}"/>' 
	                      				<c:if test="${zCombo.zoneName eq zCombo.zoneName}">selected</c:if>>
	                      				<c:out value="${zCombo.zoneName}"/>
	                      			</option>
	                		</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>UUID</th>
						<td>
							<input type="text" id="txtUuid" name="txtUuid" maxlength="20" class="w_190px input_com" check="text" checkName="UUID" readOnly="readOnly"/>
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" id="txtName" name="txtName" maxlength="20" class="w_190px input_com" check="text" checkName="이름"/>
						</td>
					</tr>
					<tr>
						<th>FRS_HOST</th>
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
				<button type="button" class="comm_btn mr_5" onclick="fnSpotAddSave();">저장</button>
				<button type="button" class="bk_color comm_btn mr_5" id="btnAddClose">취소</button>
			</div>
		</div>
		<!--//버튼  -->
	</div>
</form>
</div>


<!-- modal : 상세보기 -->
<div id="edit-spot-modal" class="example_content">
<form id="frmEdit" name="frmEdit" method="post">
<input type="hidden" id="hidSpotId" name="hidSpotId"/>
	<div class="popup_box">
		<!--테이블 시작 -->
		<div class="tb_outbox mb_20">
			<table class="tb_write_02 tb_write_p1">
				<col width="30%" />
				<col width="70%" />
				<tbody>
					<tr>
						<th>ZONE</th>
						<td>
							<input type="text" id="editZone" name="editZone" maxlength="20" class="w_190px input_com" check="text" checkName="" readOnly="readOnly"/>
						</td>
					</tr>
					<tr>
						<th>UUID</th>
						<td>
							<input type="text" id="editUuid" name="editUuid" maxlength="20" class="w_190px input_com" check="text" checkName="" readOnly="readOnly"/>
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" id="editName" name="editName" maxlength="20" class="w_190px input_com" check="text" checkName="이름"/>
						</td>
					</tr>
					<tr>
						<th>FRS_HOST</th>
						<td>
							<input type="text" id="editHost01" name="editHost01" maxlength="3" class="w_70px input_com fl" onkeyup="fnvalichk(event)" check="text" checkName="Host"/>
							<input type="text" id="editHost02" name="editHost02" maxlength="3" class="w_70px input_com fl ml_5" onkeyup="fnvalichk(event)" check="text" checkName="Host"/>
							<input type="text" id="editHost03" name="editHost03" maxlength="3" class="w_70px input_com fl ml_5" onkeyup="fnvalichk(event)" check="text" checkName="Host"/>
							<input type="text" id="editHost04" name="editHost04" maxlength="3" class="w_70px input_com fl ml_5" onkeyup="fnvalichk(event)" check="text" checkName="Host"/>
						</td>
					</tr>
					<tr>
						<th>등록일</th>
						<td>
							<input type="text" id="editRegistTime" name="editRegistTime" maxlength="20" class="w_190px input_com" check="text" checkName="" readOnly="readOnly"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--버튼 -->
	   	<div class="r_btnbox">
			<div style="display: inline-block;">
				<button type="button" class="comm_btn mr_5" onclick="fnSpotEditSave();">수정</button>
				<button type="button" class="comm_btn mr_5" onclick="fnSpotDelete();">삭제</button>
				<button type="button" class="bk_color comm_btn mr_5" id="btnEditClose">취소</button>
				
			</div>
		</div>
		
		<div class="r_btnbox mb_10">
			<button type="button" class="comm_btn mr_5" id="btnAddBeacon">추가</button>
		</div>
		
		
		<!--//버튼  -->
		<!--테이블 시작 -->
		<div class="tb_outbox mt_12">
		<table class="tb_list">
			<col width="40%" />
			<col width="40%" />
			<col width="" />
			<col width="" />

			<thead>
				<tr>
					<th>비콘MAJOR</th>
					<th>비콘Memo</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody id="beconList">
			</tbody>
		</table>
	</div>
	</div>
</form>
</div>




<!-- modal : beacon 등록 -->
<div id="add-beacon-modal" class="example_content">
<form id="frmBeaconAdd" name="frmBeaconAdd" method="post">
	<div class="popup_box">
		<!--테이블 시작 -->
		<div class="tb_outbox mb_20">
			<table class="tb_write_02 tb_write_p1">
				<col width="30%" />
				<col width="70%" />
				<tbody>
					<tr>
						<th>비콘_Major</th>
						<td>
							<input type="text" id="txtMajorNo" name="txtMajorNo" maxlength="20" class="w_190px input_com" check="text" checkName="txtMajorNo" />
						</td>
					</tr>
					<tr>
						<th>비콘_Memo</th>
						<td>
							<input type="text" id="txtbeaconMemo" name="txtbeaconMemo" maxlength="20" class="w_190px input_com" check="text" checkName="비콘Major" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--버튼 -->
	   	<div class="r_btnbox">
			<div style="display: inline-block;">
				<button type="button" class="comm_btn mr_5" onclick="fnBeaconAddSave();">저장</button>
				<button type="button" class="bk_color comm_btn mr_5" id="btnBeaconClose">취소</button>
			</div>
		</div>
		<!--//버튼  -->
	</div>
</form>
</div>

