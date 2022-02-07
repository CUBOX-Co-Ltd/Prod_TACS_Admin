<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/jsp/cubox/common/checkPasswd.jsp" flush="false"/>
<script type="text/javascript">
$(function() {
	$(".title_tx").html("${menuNm}");
	modalPopup("authAddPopup", "권한 등록", 500, 370);

	$("#addSiteUser").on("click", function(event){
		$(".popupwindow_titlebar_text").html("권한 등록");
		fnClear();
		$("#frmAuthInfo").find("#tdAuthorCd").html("자동생성");
		$("#authAddPopup").PopupWindow("open");
		$("#txtAuthorNm").focus();
	});
	
	$("#srchRecPerPage").change(function(){
		pageSearch("1");
	});
	
	$("#btnReset").click(function(){
		$("#author_nm").val("");
		$("#use_yn").val("");
	});

	$("#author_nm").keyup(function(e){if(e.keyCode == 13) pageSearch("1");});
	
});

function fnSave() {
	if(!fnFormValueCheck("frmAuthInfo")) return;
	
	$.ajax({
		type: "POST",
		url: "<c:url value='/admin/saveAuthInfo.do'/>",
		data: $("#frmAuthInfo").serialize(),
		dataType: 'json',
		success: function(data) {  //status:success
			if(data.result == "success") {
				alert("저장하였습니다.");
			} else {
				if(!fnIsEmpty(data.message)) {
					alert(data.message);
				} else {
					alert("저장 중 오류가 발생했습니다.");
				}	
			}
			$("#menuDetailPopup").PopupWindow("close");
			document.location.reload();
		},
		error: function(jqXHR){
			alert('[' + jqXHR.status + ' ' + jqXHR.statusText + ']\n저장에 실패했습니다.');
			return;
		}
	});
}	

function fnModUseYn(str, str2) {
	var msg = "사용안함으로 변경하시겠습니까?";
	var useyn = "N";		
	if(str2 == "N") {
		msg = "사용중으로 변경하시겠습니까?";
		useyn = "Y";
	}
	if(confirm(msg)) {
		$.ajax({
			type: "POST",
			url: "<c:url value='/admin/modAuthUseYn.do' />",
			data: {
				"author_cd": str,
				"use_yn": useyn
			},
			dataType: 'json',
			success: function(data) {  //status:success
				if(data.result == "success") {
					alert("저장하였습니다.");
				} else {
					if(!fnIsEmpty(data.message)) {
						alert(data.message);
					} else {
						alert("저장 중 오류가 발생했습니다.");
					}
				}
				document.location.reload();
			},
			error: function(jqXHR){
				alert('[' + jqXHR.status + ' ' + jqXHR.statusText + '] 저장에 실패했습니다!');
				return;
			}
		});
	} else {
		return;
	}
}

function fnEditAuth(str) {
	$(".popupwindow_titlebar_text").html("권한 편집");

	$.ajax({
		type: "POST",
		url: "<c:url value='/admin/getAuthInfo.do'/>",
		data: {"author_cd" : str},
		dataType: 'json',
		success: function(data) {
			if(!fnIsEmpty(data.auth)) {
				$("#frmAuthInfo").find("#tdAuthorCd").html(data.auth.author_cd);
				$("#hidAuthorCd").val(data.auth.author_cd);
				$("#txtAuthorNm").val(data.auth.author_nm);
				$("#txtAuthorDesc").val(data.auth.author_desc);
				$("#txtSortOrdr").val(data.auth.sort_ordr);
				$("#authAddPopup").PopupWindow("open");
				$("#txtAuthorNm").focus();				
			} else {
				alert("ERROR!");
			}
		},
		error: function(jqXHR){
			alert('[' + jqXHR.status + ' ' + jqXHR.statusText + '] 조회 오류입니다!');
			return;
		}
	});
}

function fnClear() {
	$("#frmAuthInfo").find("#tdAuthorCd").html("");
	$("#frmAuthInfo").find("#hidAuthorCd").val("");
	$("#frmAuthInfo").find("#txtAuthorNm").val("");
	$("#frmAuthInfo").find("#txtAuthorDesc").val("");
	$("#frmAuthInfo").find("#txtSortOrdr").val("");
}

function fnCancel() {
	fnClear();
	$("#authAddPopup").PopupWindow("close");
}

function pageSearch(page){
	if(!fnIsEmpty(page)) {
		$("#srchPage").val(page);
	} else {
		$("#srchPage").val("1");
	}
	
	frmSearch.action = "${menuUrl}";
	frmSearch.submit();	
}

</script>
<form id="frmSearch" name="frmSearch" method="post">
<input type="hidden" id="srchPage" name="srchPage" value="${pagination.curPage}"/>
<!--//검색박스 -->
<div class="search_box mb_20">
	<div class="search_in">
		<div class="comm_search mr_20">
			<div class="title">권한명</div>
			<input type="text" id="author_nm" name="author_nm" class="w_150px input_com" value="<c:out value='${params.author_nm}'/>" placeholder="권한명을 입력하세요.">
		</div>
		<div class="comm_search mr_10">
			<div class="title">사용여부</div>
			<select name="use_yn" id="use_yn" size="1" class="w_100px input_com">
				<option value="">전체</option>
				<option value="Y" <c:if test="${params.use_yn eq 'Y'}">selected</c:if>>사용중</option>
				<option value="N" <c:if test="${params.use_yn eq 'N'}">selected</c:if>>사용안함</option>
			</select>
		</div>
		<div class="comm_search ml_40">
			<div class="search_btn2" onclick="pageSearch('1')"></div>
		</div>
		<div class="comm_search ml_45">
			<button type="button" class="comm_btn" id="btnReset">초기화</button>
		</div>
	</div>
</div>
<!--------- 목록--------->
<div class="com_box ">
	<div class="r_btnbox  mb_10">
		
	</div>
	<!--버튼 -->
	<div class="totalbox">
		<div class="txbox">
			<b class="fl mr_10">전체 : <fmt:formatNumber value="${pagination.totRecord}" type="number"/>건</b>
			<!-- 건수 -->
			<select name="srchRecPerPage" id="srchRecPerPage" class="input_com w_80px">
				<c:forEach items="${cboCntPerPage}" var="cntPerPage" varStatus="status">
				<option value='<c:out value="${cntPerPage.cd_nm}"/>' <c:if test="${cntPerPage.cd_nm eq pagination.recPerPage}">selected</c:if>><c:out value="${cntPerPage.cd_nm}"/></option>
				</c:forEach>
			</select>
		</div>
		<!--버튼 -->
		<div class="r_btnbox mb_10">
			<button type="button" class="btn_middle color_basic" id="addSiteUser">추가</button>
		</div>
		<!--//버튼  -->	
	</div>	
	<!--//버튼  -->
	<!--테이블 시작 -->
	<div class="tb_outbox">
		<table class="tb_list">
			<colgroup>
				<col width="5%">
				<col width="10%">
				<col width="15%">
				<col width="15%">
				<col width="5%">
				<col width="17%">
				<col width="17%">
				<col width="8%">
				<col width="8%">
			</colgroup>
			<thead>
				<tr>
					<th>순번</th>
					<th>권한코드</th>
					<th>권한명</th>
					<th>권한설명</th>
					<th>순서</th>
					<th>등록일</th>
					<th>수정일</th>
					<th>사용여부</th>
					<th>편집</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${list == null || fn:length(list) == 0}">
				<tr>
					<td class="h_35px" colspan="9">조회 목록이 없습니다.</td>
				</tr>
				</c:if>
				<c:forEach items="${list}" var="list" varStatus="status">
				<tr>
					<td>${(pagination.totRecord - (pagination.totRecord-status.index)+1)  + ( (pagination.curPage - 1)  *  pagination.recPerPage ) }</td>
					<td><c:out value="${list.author_cd}"/></td>
					<td><c:out value="${list.author_nm}"/></td>
					<td><c:out value="${list.author_desc}"/></td>
					<td><c:out value="${list.sort_ordr}"/></td>
					<td><c:out value="${list.regist_dt}"/></td>
					<td><c:out value="${list.updt_dt}"/></td>
					<td><c:if test="${list.use_yn eq 'Y'}"><button type="button" class="btn_small color_basic" onclick="fnModUseYn('<c:out value="${list.author_cd}"/>','<c:out value="${list.use_yn}"/>');">사용중</button></c:if>
						<c:if test="${list.use_yn eq 'N'}"><button type="button" class="btn_small color_gray" onclick="fnModUseYn('<c:out value="${list.author_cd}"/>','<c:out value="${list.use_yn}"/>');">사용안함</button></c:if>
					</td>
					<td><c:if test="${list.use_yn eq 'Y'}"><button type="button" class="btn_small color_basic" data-toggle="modal" onclick="fnEditAuth('<c:out value="${list.author_cd}"/>');">편집</button></c:if></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!--------- //목록--------->
	<!-- 페이징 -->
	<jsp:include page="/WEB-INF/jsp/cubox/common/pagination.jsp" flush="false"/>
	<!-- /페이징 -->	
</div>
</form>

<!-- modal : 등록 -->
<div id="authAddPopup" class="example_content">
<form id="frmAuthInfo" name="frmAuthInfo" method="post">
<input type="hidden" id="hidAuthorCd" name="hidAuthorCd" />
	<div class="popup_box">
		<!--테이블 시작 -->
		<div class="tb_outbox mb_20">
			<table class="tb_write_02 tb_write_p1">
				<col width="25%" />
				<col width="75%" />
				<tbody>
					<tr>
						<th>권한 ID</th>
						<td id="tdAuthorCd"></td>
					</tr>
					<tr>
						<th>권한 명 <span class="font-color_H">*</span></th>
						<td>
							<input type="text" id="txtAuthorNm" name="txtAuthorNm" class="w_100p input_com" maxlength="20" check="text" checkName="권한 명"/>
						</td>
					</tr>
					<tr>
						<th>권한 설명</th>
						<td>
							<input type="text" id="txtAuthorDesc" name="txtAuthorDesc" class="w_100p input_com" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th>순서</th>
						<td>
							<input type="text" id="txtSortOrdr" name="txtSortOrdr" class="w_80px input_com" maxlength="5" onkeyup="fnRemoveChar(event)"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--버튼 -->
		<div class="r_btnbox">
			<div style="display: inline-block;">
				<button type="button" class="comm_btn mr_5" onclick="fnSave();">저장</button>
				<button type="button" class="bk_color comm_btn mr_5" onclick="fnCancel();">취소</button>
			</div>
		</div>
		<!--//버튼  -->
	</div>
</form>
</div>
