<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/cubox/common/checkPasswd.jsp" flush="false"/>
<script type="text/javascript">
$(function(){
	$(".title_tx").html("출입자 변경 이력");
	modalPopup("excelDownloadPopup", "엑셀 다운로드", 300, 490);
	modalPopup("logDetailPopup", "변경 이전 내용", 700, 500);
	
	$("input[name=startDate]").change(function(){
		var startDate = $("input[name=startDate]").val().replace('-','').replace('-','');
		var endDate = $("input[name=endDate]").val().replace('-','').replace('-','');
		if(startDate > endDate && endDate != ""){
			alert("완료일보다 뒷 날짜를 선택할 수 없습니다.");
			$("input[name=startDate]").val('');
			$("input[name=startDate]").focus();
		}
	});

	$("input[name=endDate]").change(function(){
		var startDate = $("input[name=startDate]").val().replace('-','').replace('-','');
		var endDate = $("input[name=endDate]").val().replace('-','').replace('-','');
		if(startDate > endDate && startDate != ""){
			alert("시작일보다 앞 날짜를 선택할 수 없습니다.");
			$("input[name=endDate]").val('');
			$("input[name=endDate]").focus();
		}
	});

	$("input[name=checkAll]").click(function() {
		if($(this).prop("checked")){
			$("input[name=excelColumn]").prop("checked", true);
		}else{
			$("input[name=excelColumn]").prop("checked", false);
		}
	});

	$("#checkAll").click(function(){
		if($("#checkAll").prop("checked")){
			$("input[name=excelColumn]").prop("checked", true);
		}else{
			$("input[name=excelColumn]").prop("checked", false);
		}
	});

	$("#excelSelDownload").on("click", function(event){
		$("#excelDownloadPopup").PopupWindow("open");
	});
	
	$(document).on('click','.search_btn2',function(){
		 sysLogSearch();
	});
	
	$("#srchRecPerPage").change(function(){
		sysLogSearch();
	});
	
	$("#fcnntip").keyup(function(e){if(e.keyCode == 13)  sysLogSearch(); });
	
});

function sysLogSearch(){
	f = document.frmSearch;
	$("#srchPage").val("1");

	f.action = "/basicInfo/chgLogList.do"
	f.submit();
}

function pageSearch(page){
	f = document.frmSearch;
	$("#srchPage").val(page);

	f.action = "/basicInfo/chgLogList.do";
	f.submit();
}

function cancel(){
	$("input[name=excelColumn]").prop("checked", false);
	$("input[name=checkbx]").prop("checked", false);
	$("#checkAllLst").prop("checked", false);
	$("#excelDownloadPopup").PopupWindow("close");
}

function excelDownload(){
	f = document.frmSearch;

	var chkValueArray = "";
	var chkTextArray = "";
	$("input[name=excelColumn]:checked").each(function(i){
		var chkValue = $(this).val();
		var chkText = $(this).parent().text();

		if(i == 0){
			chkValueArray = chkValueArray + chkValue + " as CELL" + (i+1);
			chkTextArray = chkTextArray + chkText;
		}else{
			chkValueArray = chkValueArray + "," + chkValue + " as CELL" + (i+1);
			chkTextArray = chkTextArray + "," + chkText;
		}
	});

	if(chkValueArray.length == 0){
		alert("한개 이상 체크를 하셔야 엑셀로 다운로드가 가능합니다.");
		return;
	}else{
		$("#excelDown").attr("data-dismiss","modal");
		$("#excelDown").attr("aria-label","Close");
	}

	$("#chkValueArray").val(chkValueArray);
	$("#chkTextArray").val(chkTextArray.replaceAll(/\s+/g, ""));
	cancel();

	f.action = "/basicInfo/chgLogListExcel.do"
	f.submit();
}


function initDateTime(){
	$("#cntn_ip").val("");
	$("#reg_id").val("");
	$("#reg_nm").val("");
	$("#chg_cl_cd").val("");
	$("#funm").val("");
	$("#fcdno").val("");
		
	$.ajax({
		type:"POST",
		url:"<c:url value='/basicInfo/resetDateTime.do' />",
		dataType:'json',
		success:function(returnData, status){
			if(status == "success") {
				$("input[name=startDate]").val(returnData.dateTimeVO.yesterday);
				$("input[name=endDate]").val(returnData.dateTimeVO.today);
			}else{ alert("ERROR!");return;}
		}
	});
}

function fnDetail(str) {
	fnInitTd();
	$.ajax({
		type: "POST",
		url: "<c:url value='/basicInfo/getChgLogInfo.do' />",
		data: {
			"chg_seq": str
		},
		dataType: 'json',
		success: function(returnData, status){ //status:"success"
			if(!fnIsEmpty(returnData.chgLogInfo.chg_cnts)) {
				//$("#divDetail").html(returnData.chgLogInfo.chg_cnts);
				var chgCnts = JSON.parse(returnData.chgLogInfo.chg_cnts);
				
				if(chgCnts.chg_cl_cd == "U") {					
					$("#tblUserChgInfo").css("display", "block");
					$("#tblCardChgInfo").css("display", "none");
					$("#tblAuthChgInfo").css("display", "none");
					$("#tblAllChgInfo").css("display", "none");
					
					$("#tdUserFuid").html(chgCnts.fuid);
					$("#tdUserFunm").html(chgCnts.funm);
					$("#tdUserFutypenm").html(chgCnts.futypenm);
					$("#tdUserFustatusnm").html(chgCnts.fustatusnm);
					$("#tdUserFpartnm1").html(chgCnts.fpartnm1);
					$("#tdUserFpartnm2").html(chgCnts.fpartnm2);
					$("#tdUserFpartnm3").html(chgCnts.fpartnm3);
					$("#tdUserFcdno").html(chgCnts.fcdno);
					$("#tdUserFetc1").html(chgCnts.fetc1);
					$("#tdUserFetc2").html(chgCnts.fetc2);	
					$("#tdBioInfo").html(chgCnts.bio_info);	
				} else if(chgCnts.chg_cl_cd == "C") {
					$("#tblUserChgInfo").css("display", "none");
					$("#tblCardChgInfo").css("display", "block");
					$("#tblAuthChgInfo").css("display", "none");
					$("#tblAllChgInfo").css("display", "none");
					
					$("#tdCardFuid").html(chgCnts.fuid);
					$("#tdCardFunm").html(chgCnts.funm);
					$("#tdCardFcdno").html(chgCnts.fcdno);
					$("#tdCardFstatusnm").html(chgCnts.fstatusnm);
					$("#tdCardFsdt").html(chgCnts.fsdt);
					$("#tdCardFedt").html(chgCnts.fedt);
				} else if(chgCnts.chg_cl_cd == "A") {
					$("#tblUserChgInfo").css("display", "none");
					$("#tblCardChgInfo").css("display", "none");
					$("#tblAuthChgInfo").css("display", "block");
					$("#tblAllChgInfo").css("display", "none");
					
					$("#tdAuthFuid").html(chgCnts.fuid);
					$("#tdAuthFunm").html(chgCnts.funm);
					$("#tdAuthFcdno").html(chgCnts.fcdno);
					$("#tdAuthFstatusnm").html(chgCnts.fstatusnm);
					$("#tdAuthFsdt").html(chgCnts.fsdt);
					$("#tdAuthFedt").html(chgCnts.fedt);
					$("#tdAuthList").html(chgCnts.author_group_list);
				} else {
					$("#tblUserChgInfo").css("display", "none");
					$("#tblCardChgInfo").css("display", "none");
					$("#tblAuthChgInfo").css("display", "none");
					$("#tblAllChgInfo").css("display", "block");
					
					$("#tdAllFuid").html(chgCnts.fuid);
					$("#tdAllFunm").html(chgCnts.funm);
					$("#tdAllFutypenm").html(chgCnts.futypenm);
					$("#tdAllFustatusnm").html(chgCnts.fustatusnm);
					$("#tdAllFpartnm1").html(chgCnts.fpartnm1);
					$("#tdAllFpartnm2").html(chgCnts.fpartnm2);
					$("#tdAllFpartnm3").html(chgCnts.fpartnm3);
					$("#tdAllFetc1").html(chgCnts.fetc1);
					$("#tdAllFetc2").html(chgCnts.fetc2);
					$("#tdAllFcdno").html(chgCnts.fcdno);
					$("#tdAllFstatusnm").html(chgCnts.fstatusnm);
					$("#tdAllFsdt").html(chgCnts.fsdt);
					$("#tdAllFedt").html(chgCnts.fedt);
					$("#tdAllList").html(chgCnts.author_group_list);
				}
				$("#logDetailPopup").PopupWindow("open");
			} else {
				alert('조회 실패!');
			}
		}
	});    
}

function fnInitTd() {
	$('#tblUserChgInfo').find('td').each(function(){
		if(fnIsEmpty(this.id)) $(this).html('');
	});
	$('#tblCardChgInfo').find('td').each(function(){
		if(fnIsEmpty(this.id)) $(this).html('');
	});
	$('#tblAuthChgInfo').find('td').each(function(){
		if(fnIsEmpty(this.id)) $(this).html('');
	});
	$('#tblAllChgInfo').find('td').each(function(){
		if(fnIsEmpty(this.id)) $(this).html('');
	});
}

</script>
<form id="frmSearch" name="frmSearch" method="post" onsubmit="return false;">
<input type="hidden" id="srchPage" name="srchPage" value="${params.srchPage}"/>
<input type="hidden" id="chkValueArray" name="chkValueArray" value="" >
<input type="hidden" id="chkTextArray" name="chkTextArray" value="" >
<div class="search_box mb_20">
	<div class="search_in_bline">
		<div class="comm_search mr_20">
		<label  for="search-from-date" class="title">검색기간 (시작일 ~ 종료일)</label>
			<input type="text" class="input_datepicker w_160px  fl" name="startDate" id="startDate" value="${params.startDate}" placeholder="시작일자" autocomplete="off">
			<div class="sp_tx fl">~</div>
			<label  for="search-to-date" ></label>
			<input type="text" class="input_datepicker w_170px  fl" name="endDate" id="endDate" value="${params.endDate}" placeholder="종료일자" autocomplete="off">
		</div>
		<div class="comm_search  mr_5">
			<div class="title">접속IP</div>
			<input type="text" class="w_150px input_com r_radius_no" id="cntn_ip" name="cntn_ip" value="${params.cntn_ip}" placeholder="127.0.0.1">
		</div>
		<div class="comm_search ml_40">
			<div class="search_btn2" onclick=""></div>
		</div>    
		<div class="comm_search ml_45">
			<button type="button" class="comm_btn" onClick="initDateTime()">초기화</button>	
		</div>
	</div>
	<div class="search_in_bline">
		<div class="comm_search  mr_20">
			<div class="title">작업자ID</div>
			<input type="text" class="w_150px input_com r_radius_no" id="reg_id" name="reg_id" value="${params.reg_id}" >
		</div>
		<div class="comm_search  mr_20">
			<div class="title">작업자</div>
			<input type="text" class="w_150px input_com r_radius_no" id="reg_nm" name="reg_nm" value="${params.reg_nm}" >
		</div>
		<div class="comm_search  mr_20">
			<div class="title">작업구분</div>
			<select id="chg_cl_cd" name="chg_cl_cd" class="w_150px input_com r_radius_no">
				<option value="">전체</option>
				<c:forEach items="${cmnChgClCd}" var="code" varStatus="status">
				<option value='<c:out value="${code.fkind3}"/>' <c:if test="${code.fkind3 eq param.chg_cl_cd}">selected</c:if>><c:out value="${code.fvalue}"/></option>
			</c:forEach>
			</select>
		</div>
		<div class="comm_search  mr_20">
			<div class="title">출입자</div>
			<input type="text" class="w_150px input_com r_radius_no" id="funm" name="funm" value="${params.funm}" >
		</div>
		<div class="comm_search  mr_20">
			<div class="title">카드번호</div>
			<input type="text" class="w_150px input_com r_radius_no" id="fcdno" name="fcdno" value="${params.fcdno}" >
		</div>
	</div>
</div>
<!--//검색박스 -->
<!--------- 목록--------->
<div class="com_box ">
	<div class="totalbox">
		<div class="txbox">
			<b class="fl mr_10">전체 : <c:out value="${pagination.totRecord}" />건</b>
			<!-- 건수 -->
			<select name="srchRecPerPage" id="srchRecPerPage" class="input_com w_80px">
			<c:forEach items="${cmnCntPerPage}" var="code" varStatus="status">
				<option value='<c:out value="${code.fvalue}"/>' <c:if test="${code.fvalue eq pagination.recPerPage}">selected</c:if>><c:out value="${code.fkind3}"/></option>
			</c:forEach>
			</select>
		</div>
		<div class="r_btnbox  mb_5">
			<button type="button" class="btn_excel" data-toggle="modal" id="excelSelDownload">엑셀 다운로드</button>
		</div>
	</div>
	<!--버튼 -->
	<!--//버튼  -->
	<!--테이블 시작 -->
	<div class="tb_outbox">
	<table class="tb_list" style="word-break:break-all;">
		<col width="30px"/>
		<col width="120px"/>
		<col width="80px"/>
		<col width="80px"/>
		<col width="100px"/>
		<col width="80px"/>
		<col width="80px"/>
		<col width="180px"/>
		<col width="80px"/>
		<thead>
			<tr>
				<th>순번</th>
				<th>작업일시</th>
				<th>작업자ID</th>
				<th>작업자</th>
				<th>작업구분</th>
				<th>출입자</th>
				<th>카드번호</th>
				<th>변경사유</th>
				<th>접속IP</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${chgLogList}" var="list" varStatus="status">
			<tr>
				<td>${(pagination.totRecord - (pagination.totRecord-status.index)+1)  + ( (pagination.curPage - 1)  *  pagination.recPerPage ) } </td>
				<td><c:out value="${list.reg_dt}"/></td>
				<td><c:out value="${list.reg_id}"/></td>
				<td><c:out value="${list.reg_nm}"/></td>
				<td><c:out value="${list.chg_cl_nm}"/></td>
				<td><c:out value="${list.funm}"/></td>
				<td><c:out value="${list.fcdno}"/></td>
				<td style="padding:5px 5px 5px 10px; text-align:left;"><a href="javascript:;" onclick="fnDetail('${list.chg_seq}')"><c:out value="${list.chg_resn}"/></a></td>
				<td><c:out value="${list.cntn_ip}"/></td>
			</tr>
			</c:forEach>
		</tbody>
	  </table>
	</div>
</div>
<!--------- //목록--------->
<!-- 페이징 -->
<jsp:include page="/WEB-INF/jsp/cubox/common/pagination.jsp" flush="false"/>
<!-- /페이징 -->
<!--//본문시작 -->
</form>

<!-- modal : 전체 이미지 다운로드 -->
<div id="excelDownloadPopup" class="example_content">
	<div class="popup_box">
		<div class="search_box_popup" style="border-bottom: 0px">
			<div class="search_in">
				<input type="checkbox" id="checkAllA" name="checkAll" class="checkbox">
				<label for="checkAllA" class="ml_10"> 전체</label>
			</div>
		</div>
		<div class="search_box_popup mb_20">
			<div class="search_in">
				<div class="mr_m3">
					<input type="checkbox" name="excelColumn" id="xls_reg_dt" value="reg_dt" class="checkbox">
					<label for="xls_reg_dt" class="ml_10">작업일시</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="xls_reg_id" value="reg_id" class="checkbox">
					<label for="xls_reg_id" class="ml_10">작업자ID</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="xls_reg_nm" value="reg_nm" class="checkbox">
					<label for="xls_reg_nm" class="ml_10">작업자</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="xls_chg_cl_nm" value="chg_cl_nm" class="checkbox">
					<label for="xls_chg_cl_nm" class="ml_10">작업구분</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="xls_funm" value="funm" class="checkbox">
					<label for="xls_funm" class="ml_10">출입자</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="xls_fcdno" value="fcdno" class="checkbox">
					<label for="xls_fcdno" class="ml_10">카드번호</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="xls_chg_resn" value="chg_resn" class="checkbox">
					<label for="xls_chg_resn" class="ml_10">변경사유</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="xls_cntn_ip" value="cntn_ip" class="checkbox">
					<label for="xls_cntn_ip" class="ml_10">접속IP</label>
				</div>
			</div>
		</div>
		<div class="c_btnbox">
			<div style="display: inline-block;">
				<button type="button" class="comm_btn mr_5" id="excelDownS" onclick="excelDownload();">다운로드</button>
				<button type="button" class="bk_color comm_btn" title="취소" onclick="cancel();">취소</button>
			</div>
		</div>
	</div>
</div>

<!-- 변경 전 내용 상세보기 팝업 -->
<div id="logDetailPopup" class="example_content">
	<div class="popup_box">
		<div class="com_box">
			<div class="tb_outbox" id="tblUserChgInfo">
				<table class="tb_write_02 tb_write_p1" >
					<col width="14%" />
					<col width="36%" />
					<col width="14%" />
					<col width="36%" />
					<tbody>
						<tr>
							<th>FUID</th>
							<td id="tdUserFuid"></td>
							<th>이름</th>
							<td id="tdUserFunm"></td>
						</tr>
						<tr>
							<th>출입자유형</th>
							<td id="tdUserFutypenm"></td>
							<th>출입자상태</th>
							<td id="tdUserFustatusnm"></td>
						</tr>
						<tr>
							<th>회사</th>
							<td id="tdUserFpartnm1"></td>
							<th>부서</th>
							<td id="tdUserFpartnm2"></td>
						</tr>
						<tr>
							<th>직책</th>
							<td id="tdUserFpartnm3"></td>
							<th>카드번호</th>
							<td id="tdUserFcdno"></td>
						</tr>
						<tr>
							<th>기타1</th>
							<td id="tdUserFetc1"></td>
							<th>기타2</th>
							<td id="tdUserFetc2"></td>
						</tr>
						<tr>
							<th>사진수정여부</th>
							<td colspan="3" id="tdBioInfo"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="tb_outbox" id="tblCardChgInfo">
				<table class="tb_write_02 tb_write_p1">
					<col width="14%" />
					<col width="36%" />
					<col width="14%" />
					<col width="36%" />
					<tbody>
						<tr>
							<th>FUID</th>
							<td id="tdCardFuid"></td>
							<th>이름</th>
							<td id="tdCardFunm"></td>
						</tr>
						<tr>
							<th>카드번호</th>
							<td id="tdCardFcdno"></td>
							<th>카드상태</th>
							<td id="tdCardFstatusnm"></td>
						</tr>
						<tr>
							<th>시작일자</th>
							<td id="tdCardFsdt"></td>
							<th>종료일자</th>
							<td id="tdCardFedt"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="tb_outbox" id="tblAuthChgInfo">
				<table class="tb_write_02 tb_write_p1" >
					<col width="14%" />
					<col width="36%" />
					<col width="14%" />
					<col width="36%" />
					<tbody>
						<tr>
							<th>FUID</th>
							<td id="tdAuthFuid"></td>
							<th>이름</th>
							<td id="tdAuthFunm"></td>
						</tr>
						<tr>
							<th>카드번호</th>
							<td id="tdAuthFcdno"></td>
							<th>카드상태</th>
							<td id="tdAuthFstatusnm"></td>
						</tr>
						<tr>
							<th>시작일자</th>
							<td id="tdAuthFsdt"></td>
							<th>종료일자</th>
							<td id="tdAuthFedt"></td>
						</tr>
						<tr>
							<th>권한그룹</th>
							<td id="tdAuthList" colspan="3"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="tb_outbox" id="tblAllChgInfo">
				<table class="tb_write_02 tb_write_p1" >
					<col width="14%" />
					<col width="36%" />
					<col width="14%" />
					<col width="36%" />
					<tbody>
						<tr>
							<th>FUID</th>
							<td id="tdAllFuid"></td>
							<th>이름</th>
							<td id="tdAllFunm"></td>
						</tr>
						<tr>
							<th>출입자유형</th>
							<td id="tdAllFutypenm"></td>
							<th>출입자상태</th>
							<td id="tdAllFustatusnm"></td>
						</tr>
						<tr>
							<th>카드번호</th>
							<td id="tdAllFcdno"></td>
							<th>카드상태</th>
							<td id="tdAllFstatusnm"></td>
						</tr>
						<tr>
							<th>시작일자</th>
							<td id="tdAllFsdt"></td>
							<th>종료일자</th>
							<td id="tdAllFedt"></td>
						</tr>
						<tr>
							<th>회사</th>
							<td id="tdAllFpartnm1"></td>
							<th>부서</th>
							<td id="tdAllFpartnm2"></td>
						</tr>
						<tr>
							<th>직책</th>
							<td id="tdAllFpartnm3"></td>
							<th></th>
							<td id=""></td>
						</tr>
						<tr>
							<th>기타1</th>
							<td id="tdAllFetc1"></td>
							<th>기타2</th>
							<td id="tdAllFetc2"></td>
						</tr>
						<tr>
							<th>권한그룹</th>
							<td id="tdAllList" colspan="3"></td>
						</tr>
					</tbody>
				</table>			
			</div>
		</div>
	</div>
</div>	