<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 			uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" 	uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 			uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/jsp/cubox/common/checkPasswd.jsp" flush="false"/>
<script type="text/javascript">
$(function(){
	$(".title_tx").html("시스템 로그");
	modalPopup ("excelAllDownloadPopup", "전체 엑셀 다운로드", 300, 490);
	modalPopup ("logDetailPopup", "로그 상세 내용", 400, 400);
	
	$("input[name=startDateTime]").change(function(){
		var startDate = $("input[name=startDateTime]").val().replace('-','').replace('-','');
		var endDate = $("input[name=endDateTime]").val().replace('-','').replace('-','');
		if(startDate > endDate && endDate != ""){
			alert("완료일보다 뒷 날짜를 선택할 수 없습니다.");
			$("input[name=startDateTime]").val('');
			$("input[name=startDateTime]").focus();
		}
	});

	$("input[name=endDateTime]").change(function(){
		var startDate = $("input[name=startDateTime]").val().replace('-','').replace('-','');
		var endDate = $("input[name=endDateTime]").val().replace('-','').replace('-','');
		if(startDate > endDate && startDate != ""){
			alert("시작일보다 앞 날짜를 선택할 수 없습니다.");
			$("input[name=endDateTime]").val('');
			$("input[name=endDateTime]").focus();
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

	$("#fetcAdd").click(function(){
		var sysLogDept1Value = $("#sysLogDept1").val();
		var sysLogDept1Text = $("#sysLogDept1 option:selected").text();
		var sysLogDept2Value = $("#sysLogDept2").val();
		var sysLogDept2Text = $("#sysLogDept2 option:selected").text();
		var sysLogDept3Value = $("#sysLogDept3").val();
		var sysLogDept3Text = $("#sysLogDept3 option:selected").text();

		var tagText = "";
		var tagValue = "";

		if(sysLogDept1Value == ""){
			alert("구분1을 선택하세요.");
			$("#sysLogDept1").focus();
			return;
		}

		if(sysLogDept1Value != "" && sysLogDept2Value == "" && sysLogDept3Value == ""){
			tagText = sysLogDept1Text;
			tagValue = sysLogDept1Value;
		}

		if(sysLogDept1Value != "" && sysLogDept2Value != "" && sysLogDept3Value == ""){
			tagText = sysLogDept2Text;
			tagValue = sysLogDept2Value;
		}

		if(sysLogDept1Value != "" && sysLogDept2Value != "" && sysLogDept3Value != ""){
			tagText = sysLogDept3Text;
			tagValue = sysLogDept3Value;
		}

		var tag = "<div style='float:left;'><span style='margin-left: 3px;width: 50px;'code='" + tagValue + "'>" + tagText + "</span><button type='button' class='btn_del_small color_gray fetc-remove'></button></div>"
		$("#srch-result").append(tag);

	});


	$("#excelSelDownload").on("click", function(event){
		
           $("#excelAllDownloadPopup").PopupWindow("open");
       });
	
	$(document).on('click','.fetc-remove',function(){
		$(this).closest("div").remove();
	});
	  
	$(document).on('click','.search_btn2',function(){
		 sysLogSearch();
	});
	
	$("#srchRecPerPage").change(function(){
		sysLogSearch();
	});
	
	$("#fcnntip").keyup(function(e){if(e.keyCode == 13)  sysLogSearch(); });
	
	if("${syslogVO.syslogdept1}" != "") {
		$('#sysLogDept1').val("${syslogVO.syslogdept1}").prop("selected", true);
		sysLogDept2List("${syslogVO.syslogdept1}", "1");
	}

});

function sysLogSearch(){
	f = document.frmSearch;

	var sysLogDeptValue = "";
	var sysLogDeptParam = "";
	$(".form-control4").each(function(i){
		if(i==0){
			sysLogDeptValue = sysLogDeptValue + "^" + $(this).attr("code");
			sysLogDeptParam = sysLogDeptParam + $(this).attr("code");
		}else{
			sysLogDeptValue = sysLogDeptValue + "|^" + $(this).attr("code");
			sysLogDeptParam = sysLogDeptParam + "," + $(this).attr("code");
		}
	});

	$("#sysLogDeptValue").val(sysLogDeptValue);
	$("#sysLogDeptParam").val(sysLogDeptParam);

	f.action = "/basicInfo/sysLogMngmt.do"
	f.submit();
}

function pageSearch(page){
	f = document.frmSearch;

	var sysLogDeptValue = "";
	var sysLogDeptParam = "";
	$(".form-control4").each(function(i){
		if(i==0){
			sysLogDeptValue = sysLogDeptValue + "^" + $(this).attr("code");
			sysLogDeptParam = sysLogDeptParam + $(this).attr("code");
		}else{
			sysLogDeptValue = sysLogDeptValue + "|^" + $(this).attr("code");
			sysLogDeptParam = sysLogDeptParam + "," + $(this).attr("code");
		}
	});

	$("#sysLogDeptValue").val(sysLogDeptValue);
	$("#sysLogDeptParam").val(sysLogDeptParam);

	f.action = "/basicInfo/sysLogMngmt.do?srchPage=" + page;
	f.submit();
}

function cancel(){
	$("input[name=excelColumn]").prop("checked", false);
	$("input[name=checkbx]").prop("checked", false);
	$("#checkAllLst").prop("checked", false);
	$("#excelAllDownloadPopup").PopupWindow("close");
}

/* 
function excelFormDown(){
	location.href = "/basicInfo/excelFormDown.do";
} */

function excelDownload(){
	f = document.frmSearch;

	var sysLogDeptValue = "";
	$(".form-control4").each(function(i){
		if(i==0){
			sysLogDeptValue = sysLogDeptValue + "^" + $(this).attr("code");
		}else{
			sysLogDeptValue = sysLogDeptValue + "|^" + $(this).attr("code");
		}
	});

	$("#sysLogDeptValue").val(sysLogDeptValue);

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

	f.action = "/basicInfo/excelDownload.do"
	f.submit();
}

function sysLogDept2List(fkind3, gb){
	$.ajax({
		type:"POST",
		url:"<c:url value='/basicInfo/sysLogDept2List.do' />",
		data:{
			"fkind3": fkind3
		},
		dataType:'json',
		//timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				var sysLogDept2 = "<option value=''>구분2</option>";
				//console.log(returnData.totalAuthGroup[0]);
				for(var i=0; i < returnData.sysLogDept2.length; i++ ){
					var selText = "";
					if("${syslogVO.syslogdept2}" == returnData.sysLogDept2[i].fkind3) selText = "selected";
					sysLogDept2 = sysLogDept2 + "<option value='" + returnData.sysLogDept2[i].fkind3 + "' " + selText + ">" + returnData.sysLogDept2[i].fvalue + "</option>";
				}
				$("#sysLogDept2").html(sysLogDept2);
				
				if(fnIsEmpty(gb)) {
					$("#sysLogDept3").html("<option value=''>구분3</option>");
				} else {
					if(fnIsEmpty("${syslogVO.syslogdept2}")) {
						$("#sysLogDept3").html("<option value=''>구분3</option>");
					} else {
						sysLogDept3List("${syslogVO.syslogdept2}");
					}					
				}
			}else{ alert("ERROR!");return;}
		}
	});
}

function sysLogDept3List(fkind3){
	$.ajax({
		type:"POST",
		url:"<c:url value='/basicInfo/sysLogDept3List.do' />",
		data:{
			"fkind3": fkind3
		},
		dataType:'json',
		//timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				var sysLogDept3 = "<option value=''>구분3</option>";
				//console.log(returnData.totalAuthGroup[0]);
				for(var i=0; i < returnData.sysLogDept3.length; i++ ){
					var selText = "";
					if("${syslogVO.syslogdept3}" == returnData.sysLogDept3[i].fkind3) selText = "selected";
					sysLogDept3 = sysLogDept3 + "<option value='" + returnData.sysLogDept3[i].fkind3 + "' " + selText + ">" + returnData.sysLogDept3[i].fvalue + "</option>";
				}
				$("#sysLogDept3").html(sysLogDept3);
			}else{ alert("ERROR!");return;}
		}
	});
}

function initDateTime(){
	$("#fcnntip").val("");
	$("#fcnntid").val("");
	$("#sysLogDept1").val("");
	$("#sysLogDept2").html("<option value=''>구분2</option>");
	$("#sysLogDept3").html("<option value=''>구분3</option>");
	
	$.ajax({
		type:"POST",
		url:"<c:url value='/basicInfo/resetDateTime.do' />",
		data:{
			"fkind3": ""
		},
		dataType:'json',
		//timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				$("input[name=startDateTime]").val(returnData.dateTimeVO.yesterday);
				$("input[name=endDateTime]").val(returnData.dateTimeVO.today);
				$("#fcnntip").val('');
				$(".fetc-remove").closest("div").remove();
			}else{ alert("ERROR!");return;}
		}
	});
}

function fnDetail(str1, str2, str3) {
    $.ajax({
        type: "POST",
        url: "<c:url value='/basicInfo/getSysLogInfo.do' />",
        data: {
            "fevttm": str1,
            "fsiteid": str2,
            "fsyscode": str3
        },
        dataType: 'json',
        //timeout:(1000*30),
        success: function(returnData, status){
            if(status == "success") {
                if(!fnIsEmpty(returnData.sysLogInfo.fdetail)) {
                    $("#divDetail").html(returnData.sysLogInfo.fdetail);
                    $("#logDetailPopup").PopupWindow("open");
                }
            }
        }
    });    
}

</script>
<form id="frmSearch" name="frmSearch" method="post" onsubmit="return false;">
	<input type="hidden" id="chkValueArray" name="chkValueArray" value=""/>
	<input type="hidden" id="chkTextArray" name="chkTextArray" value="" >
	<input type="hidden" id="sysLogDeptValue" name="sysLogDeptValue" value="" >
	<input type="hidden" id="sysLogDeptParam" name="sysLogDeptParam" value="" >

<div class="search_box mb_20">
  <div class="search_in_bline">
    <div class="comm_search mr_20">
     <label  for="search-from-date" class="title">기간 (시작일 ~ 완료일)</label>
          <input type="text" class="input_datepicker w_160px  fl" name="startDateTime" id="startDate" value="${syslogVO.startDateTime}" placeholder="날짜,시간">
		<div class="sp_tx fl">~</div>
		  <label  for="search-to-date" ></label>
          <input type="text" class="input_datepicker w_170px  fl" name="endDateTime" id="endDate" value="${syslogVO.endDateTime}" placeholder="날짜,시간">
    </div>
    <div class="comm_search  mr_5">
	    <div class="title">IP</div>
		<input type="text" class="w_150px input_com r_radius_no" id="fcnntip" name="fcnntip" value="${syslogVO.fcnntip}" placeholder="127.0.0.1">
    </div>
    <div class="comm_search ml_40">
        <div class="search_btn2" onclick=""></div>
    </div>    
    <div class="comm_search ml_45">
        <button type="button" class="comm_btn" onClick="initDateTime()">초기화</button>	
    </div>
  </div>

  <div class="search_in_bline">
    <div class="comm_search mr_20">
        <div class="title">구분값</div>
            <select name="sysLogDept1" id="sysLogDept1" class="w_150px input_com  mr_5" onchange="sysLogDept2List(this.value);" size="1" >
                <option value=''>구분1</option>
	            <c:forEach items="${sysLogDept1List}" var="slDept1" varStatus="status">
                <option value='<c:out value="${slDept1.fkind3}"/>' ><c:out value="${slDept1.fvalue}"/></option>
	            </c:forEach>
	        </select>
	        <select name="sysLogDept2" id="sysLogDept2" class="w_150px input_com  mr_5" onchange="sysLogDept3List(this.value);">
		        <option value=''>구분2</option>
	        </select>
            <select name="sysLogDept3" id="sysLogDept3" class="w_150px input_com">
		        <option value=''>구분3</option>
	        </select>
    </div>
	<c:choose>
	<c:when test="${syslogVO.fkind3 ne '10' && syslogVO.authorId ne '00001'}">
		<input type="hidden" id="fcnntid" name="fcnntid">
	</c:when>
	<c:otherwise>
		<div class="comm_search  mr_5">
			<div class="title">ID</div>
			<input type="text" class="w_150px input_com r_radius_no" id="fcnntid" name="fcnntid" value="${syslogVO.fcnntid}" >
		</div>
	</c:otherwise>
	</c:choose>    
    <%-- 2021-01-15 주석처리
    <div class="comm_search">
      <button type="button" class="comm_btn" id="fetcAdd">추가 </button>
    </div>
    <div>
	    <div id="srch-result">
            <c:forEach items="${sysLogParam}" var="slParam" varStatus="status">
                <div class='d-flex align-items-center'>
             	    <span class="form-control4" code='<c:out value="${slParam.fkind3}"/>'><c:out value="${slParam.fvalue}"/></span>
                     <a href="javascript:void(0);" class="btn btn-secondary btn-xs btn-icon mr20">
                         <i class="fa fa-times fetc-remove"></i>
                     </a>
                </div>
         	</c:forEach>
          </div>
      </div> --%>
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
           	<c:forEach items="${cntPerPage}" var="cntPerPage" varStatus="status">
				<option value='<c:out value="${cntPerPage.fvalue}"/>' <c:if test="${cntPerPage.fvalue eq syslogVO.srchCnt}">selected</c:if>><c:out value="${cntPerPage.fkind3}"/></option>
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
      <table class="tb_list">
        <col width="5%"/>
        <col width="16%"/>
        <col width="10%"/>
        <col width="9%"/>
        <col width="10%"/>
        <col width="12%"/>
        <col width="12%"/>
        <col width="14%"/>
        <col width="12%"/>
        <thead>
          <tr>
            <th>순번</th>
            <th>작업일시</th>
            <th>아이디</th>
            <th>이름</th>
            <th>구분1</th>
            <th>구분2</th>
            <th>구분3</th>
            <th>상세로그</th>
            <th>접속IP</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${sysLogList}" var="slList" varStatus="status">
			<tr>
				<td>${(totalCnt - (totalCnt-status.index)+1)  + ( (currentPage - 1)  *  displayNum ) } </td>
				<td><c:out value="${slList.fevttm}"/></td>
				<td><c:out value="${slList.fsiteid}"/></td>
				<td><c:out value="${slList.fname}"/></td>
				<td><c:out value="${slList.syslogdeptnm1}"/></td>
				<td><c:out value="${slList.syslogdeptnm2}"/></td>
				<td><c:out value="${slList.syslogdeptnm3}"/></td>
				<td><c:if test="${!empty slList.fdetail}"><a href="#none" onclick="fnDetail('<c:out value="${slList.fevttm}"/>', '<c:out value="${slList.fsiteid}"/>', '<c:out value="${slList.fsyscode}"/>');"><c:out value="${slList.fsyscodenm}"/></a></c:if>
					<c:if test="${empty slList.fdetail}"><c:out value="${slList.fsyscodenm}"/></c:if>
				</td>
				<td><c:out value="${slList.fcnntip}"/></td>
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
<div id="excelAllDownloadPopup" class="example_content">
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
					<input type="checkbox" name="excelColumn" id="fevttm" value="fevttm" class="checkbox">
					<label for="fevttm" class="ml_10">날짜</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="fsiteid" value="fsiteid" class="checkbox">
					<label for="fsiteid" class="ml_10">아이디</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="fname" value="fname" class="checkbox">
					<label for="fsiteid" class="ml_10">이름</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="syslogdeptnm1" value="syslogdeptnm1" class="checkbox">
					<label for="syslogdeptnm1" class="ml_10">구분1</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="syslogdeptnm2" value="syslogdeptnm2" class="checkbox">
					<label for="syslogdeptnm2" class="ml_10">구분2</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="syslogdeptnm3" value="syslogdeptnm3" class="checkbox">
					<label for="syslogdeptnm2" class="ml_10">구분3</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="fsyscodenm" value="fsyscodenm" class="checkbox">
					<label for="fsyscodenm" class="ml_10">상세로그</label>
				</div>
				<div class="mr_m3 mt_15">
					<input type="checkbox" name="excelColumn" id="fcnntip" value="fcnntip" class="checkbox">
					<label for="fcnntip" class="ml_10">접속IP</label>
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

<div id="logDetailPopup" class="example_content">
	<div class="popup_box" id="divDetail" style="width:100%; word-break:break-all;word-wrap:break-word;">
	</div>
</div>
