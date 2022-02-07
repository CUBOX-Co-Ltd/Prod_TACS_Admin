<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/jsp/cubox/common/checkPasswd.jsp" flush="false"/>
<script type="text/javascript">
$(function() {
	$(".title_tx").html("${menuNm}");
	$(".onlyNumber").keyup(function(event){
		if (!(event.keyCode >=37 && event.keyCode<=40)) {
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^0-9]/gi,''));
		}
	});
	
	modalPopup("addUserPopup", "사용자 등록", 500, 350);
	modalPopup("editUserPopup", "사용자 편집", 500, 350);

	$("#btnAddUser").on("click", function(event){
		fnClear();
		$(".popupwindow_titlebar_text").html("사용자 등록");
		$("#addUserPopup").PopupWindow("open");
		$("#txtUserId").focus();
	});
	
	$("#btnClose").click(function(){
		$("#addUserPopup").PopupWindow("close");
	});
	
	$("#srchRecPerPage").change(function(){
		pageSearch("1");
	});

	$("#btnReset").click(function(){
		$("#srchUserId").val("");
		$("#srchUserNm").val("");
		$("#srchAuthorCd").val("");
		$("#srchUseYn").val("");
	});
	
	$("#srchUserId").keyup(function(e){if(e.keyCode == 13) pageSearch("1");});
	$("#srchUserNm").keyup(function(e){if(e.keyCode == 13) pageSearch("1");});
});

function fnClear(){
	$("#hidEsntlId").val("");
	$("#txtUserId").val("");
	$("#txtUserId").attr("readonly", false);
	$("#txtUserNm").val("");
	$("#selAuthorCd").val("");
	$("#idCheck").val("");
	$("#idConfirm").html("※ 아이디를 입력 후 중복확인을 클릭하세요.");	
	$("#btnCheckId").show();
}

//아이디 체크
function fnCheckId(){
	var str = $("#txtUserId").val();
	var isID = /^[a-z0-9][a-z0-9_\-]{4,19}$/;
	if (!isID.test(str)) {
		alert("5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
		$("#txtUserId").focus();
		return;
	}
	if(!fnIsEmpty(str)) {
		$.ajax({
			type: "POST",
			url: "<c:url value='/admin/checkIdDplct.do' />",
			data: {"checkId": str},
			dataType:'json',
			success: function(data){
				if(data.result == "success") {
					if(data.dupcnt == 0 ){
						$("#idConfirm").html("<font color='#009fc0'> ※ 사용가능한 아이디입니다.</font>");
						$("#txtUserId").attr("readonly", true);
						$("#idCheck").val("Y");
					} else {
						$("#idConfirm").html("<font color='#ff5a00'> ※ 사용할 수 없는 아이디입니다.</font>");
					}
				} else {
					if(!fnIsEmpty(data.message)) {
						alert(data.message);
					} else {
						alert("ERROR!");
					}
				}
			}
		});
	}
}

function fnSaveUser() {
	if(fnIsEmpty($("#hidEsntlId").val())) {
		fnAddUser();
	} else {
		fnModUser();
	}
}

//계정추가
function fnAddUser() {
	if(fnIsEmpty($("#txtUserId").val())) {alert("아이디를 입력하세요."); $("#txtUserId").focus(); return;}
	if(fnIsEmpty($("#txtUserNm").val())) {alert("이름을 입력하세요."); $("#txtUserNm").focus(); return;}
	if(fnIsEmpty($("#selAuthorCd").val())) {alert("권한을 선택하세요."); $("#selAuthorCd").focus(); return;}
	if(fnIsEmpty($("#idCheck").val()) || $("#idCheck").val() != "Y") {alert("아이디 중복확인을 하세요."); return;}

	if(confirm("비밀번호는 아이디 + <spring:eval expression="@property['Globals.passwd.post']" />입니다. \n저장하시겠습니까?")){
		$.ajax({
			url: "<c:url value='/admin/addUserInfo.do' />",
			type: "POST",
			data: { 
				"user_id" : $("#txtUserId").val(),
				"user_nm" : $("#txtUserNm").val(),
				"author_cd" : $("#selAuthorCd").val()
			},
			dataType: 'json',
			success: function(data){
				if(data.result == "success") {
					alert("저장하였습니다.");
					pageSearch("1");
				} else {
					if(!fnIsEmpty(data.message)) {
						alert(data.message);
					} else {
						alert("저장 중 오류가 발생했습니다.");
					}
				}
			},
			error: function(jqXHR){
				alert('[' + jqXHR.status + ' ' + jqXHR.statusText + '] 저장 실패!');
				return;
			}
		});
	}
}

function fnEditUser(str){
	$(".popupwindow_titlebar_text").html("사용자 편집");

	$.ajax({
		type: "POST",
		url: "<c:url value='/admin/getUserInfo.do'/>",
		data: {"esntl_id" : str},
		dataType: 'json',
		success: function(data) {
			if(!fnIsEmpty(data.user)) {
				$("#hidEsntlId").val(data.user.esntl_id);
				$("#txtUserId").val(data.user.user_id);
				$("#txtUserId").attr("readonly", true);
				$("#txtUserNm").val(data.user.user_nm);
				$("#selAuthorCd").val(data.user.author_cd);
				$("#idCheck").val("");
				$("#idConfirm").html("");
				$("#btnCheckId").hide();
				$("#addUserPopup").PopupWindow("open");
			} else {
				if(!fnIsEmpty(data.message)) {
					alert(data.message);
				} else {
					alert("조회 중 오류가 발생했습니다.");
				}
			}
		},
		error: function(jqXHR){
			alert('[' + jqXHR.status + ' ' + jqXHR.statusText + '] 조회 실패!');
			return;
		}
	});
}

//계정편집
function fnModUser() {
	if(fnIsEmpty($("#txtUserNm").val())) {alert("이름을 입력하세요."); $("#txtUserNm").focus(); return;}
	if(fnIsEmpty($("#selAuthorCd").val())) {alert("권한을 선택하세요."); $("#selAuthorCd").focus(); return;}

	if(confirm("저장하시겠습니까?")){
		$.ajax({
			type: "POST",
			url: "<c:url value='/admin/modUserInfo.do' />",
			data: {
				"esntl_id" : $("#hidEsntlId").val(),
				"user_nm" : $("#txtUserNm").val(),
				"author_cd" : $("#selAuthorCd").val()
			},
			dataType: 'json',
			success: function(data, status){
				if(data.result == "success") {
					alert("저장하였습니다.");
					pageSearch("1");
				} else {
					if(!fnIsEmpty(data.message)) {
						alert(data.message);
					} else {
						alert("저장 중 오류가 발생했습니다.");
					}
				}
			},
			error: function(jqXHR){
				alert('[' + jqXHR.status + ' ' + jqXHR.statusText + '] 저장 실패!');
				return;
			}
		});
	}
}

//계정사용여부변경
function fnModUseYn(str, useYn) {
	var sMsg = "사용안함으로 변경하시겠습니까? ";
	if(useYn == "Y") {
		sMsg = "사용중으로 변경하시겠습니까? ";
	}

	if(confirm(sMsg)){
		$.ajax({
			type:"POST",
			url:"<c:url value='/admin/modUserUseYn.do' />",
			data:{
				"esntl_id" : str,
				"use_yn": useYn
			},
			dataType:'json',
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
				alert('[' + jqXHR.status + ' ' + jqXHR.statusText + '] 저장 실패!');
				return;
			}
		});
	}
}

//계정비밀번호 초기화
function fnResetPw(str1, str2){
	if(confirm(str2 + " 비밀번호를 초기화 하시겠습니까? \n초기화시 비밀번호는 아이디 + <spring:eval expression="@property['Globals.passwd.post']" />로 변경이 됩니다.")){
		$.ajax({
			type:"POST",
			url:"<c:url value='/admin/resetUserPw.do' />",
			data:{
				"esntl_id": str1,
				"user_id": str2,
			},
			dataType:'json',
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
				alert('[' + jqXHR.status + ' ' + jqXHR.statusText + '] 저장 실패!');
				return;
			}
		});
	}
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
		<div class="comm_search  mr_20">
			<div class="title">아이디</div>
			<input type="text" class="w_150px input_com" id="srchUserId" name="srchUserId" value="${params.srchUserId}" placeholder="아이디를 입력하세요.">
		</div>
		<div class="comm_search  mr_20">
			<div class="title">이름</div>
			<input type="text" class="w_150px input_com" id="srchUserNm" name="srchUserNm" value="${params.srchUserNm}" placeholder="이름을 입력하세요.">
		</div>
		<div class="comm_search mr_20">
			<div class="title">권한</div>
			<select name="srchAuthorCd" id="srchAuthorCd" size="1" class="w_150px input_com">
				<option value="">권한을 선택하세요.</option>
				<c:forEach items="${cboAuth}" var="auth" varStatus="status">
				<option value='<c:out value="${auth.author_cd}"/>' <c:if test="${auth.author_cd eq params.srchAuthorCd}">selected</c:if>><c:out value="${auth.author_nm}"/></option>
				</c:forEach>
			</select>
		</div>
		<div class="comm_search mr_10">
			<div class="title">사용여부</div>
			<select name="srchUseYn" id="srchUseYn" size="1" class="w_100px input_com">
				<option value="">전체</option>
				<option value="Y" <c:if test="${params.srchUseYn eq 'Y'}">selected</c:if>>사용중</option>
				<option value="N" <c:if test="${params.srchUseYn eq 'N'}">selected</c:if>>사용안함</option>
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
	<div class="totalbox">
		<div class="txbox">
			<b class="fl mr_10">전체 : <fmt:formatNumber value="${pagination.totRecord}" type="number"/>건</b>
			<!-- 건수 -->
			<select name="srchRecPerPage" id="srchRecPerPage" class="input_com w_80px">
				<c:forEach items="${cboCntPerPage}" var="cntPerPage" varStatus="status">
				<option value='<c:out value="${cntPerPage.cd_nm}"/>' <c:if test="${cntPerPage.cd_nm eq pagination.recPerPage}">selected</c:if>><c:out value="${cntPerPage.cd_nm}"/></option>
				</c:forEach>
			</select>
		</div>	<!--버튼 -->
		<div class="r_btnbox  mb_10">
			<button type="button" class="btn_middle color_basic" id="btnAddUser">추가</button>
		</div>
		<!--//버튼  -->
	</div>
	<!--테이블 시작 -->
	<div class="tb_outbox">
		<table class="tb_list">
			<colgroup>
				<col width="4%" />
				<col width="8%" />
				<col width="8%" />
				<col width="9%" />
				<col width="14%" />
				<col width="8%" />
				<col width="8%" />
				<col width="10%" />
				<col width="9%" />
				<col width="7%" />
				<col width="7%" />
				<col width="7%" />
			</colgroup>
			<thead>
				<tr>
					<th>순번</th>
					<th>아이디</th>
					<th>이름</th>
					<th>권한</th>
					<th>마지막접속일시</th>
					<th>암호오류횟수</th>
					<th>암호변경여부</th>
					<th>암호변경경과일수</th>
					<th>등록일자</th>
					<th>사용여부</th>
					<th>편집</th>
					<th>비밀번호</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${list == null || fn:length(list) == 0}">
				<tr>
					<td class="h_35px" colspan="12">조회 목록이 없습니다.</td>
				</tr>
				</c:if>
				<c:forEach items="${list}" var="list" varStatus="status">
				<tr>
					<td>${(pagination.totRecord - (pagination.totRecord-status.index)+1)  + ( (pagination.curPage - 1)  *  pagination.recPerPage ) }</td>
					<td><c:out value="${list.user_id}"/></td>
					<td><c:out value="${list.user_nm}"/></td>
					<td><c:out value="${list.author_nm}"/></td>
					<td><c:out value="${list.last_conect_dt}"/></td>
					<td><c:out value="${list.pw_failr_cnt}"/></td>
					<td><c:out value="${list.pw_updt_yn}"/></td>
					<td><c:out value="${list.pw_diff_days}"/></td>
					<td><c:out value="${list.regist_de}"/></td>
					<c:if test="${list.use_yn eq 'Y'}">
					<td><button type="button" class="btn_small color_basic" onclick="fnModUseYn('<c:out value="${list.esntl_id}"/>','N');">사용중</button></td>
					<td><button type="button" class="btn_small color_basic" data-toggle="modal" onclick="fnEditUser('<c:out value="${list.esntl_id}"/>');">편집</button></td>
					<td><button type="button" class="btn_small color_color1" onclick="fnResetPw('<c:out value="${list.esntl_id}"/>', '<c:out value="${list.user_id}"/>');">초기화</button></td>
					</c:if><c:if test="${list.use_yn eq 'N'}">
					<td><button type="button" class="btn_small color_gray" onclick="fnModUseYn('<c:out value="${list.esntl_id}"/>','Y');">사용안함</button></td>
					<td></td>
					<td></td>
					</c:if>
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
<form id="frmAddUser" name="frmAddUser" method="post">
<input type="hidden" id="hidEsntlId" name="hidEsntlId">
<div id="addUserPopup" class="example_content">
	<div class="popup_box">
		<!--테이블 시작 -->
		<div class="tb_outbox mb_20">
			<table class="tb_write_02 tb_write_p1">
				<col width="25%" />
				<col width="75%" />
				<tbody>
					<tr>
						<th>아이디 <span class="font-color_H">*</span></th>
						<td>
							<div class="w_100p_center fl">
								<input type="text" id="txtUserId" name="txtUserId" maxlength="20" class="w_200px input_com fl"/>
								<button type="button" class="btn_small color_basic fl ml_5" style="height: 30px" id="btnCheckId" onclick="fnCheckId();">중복확인</button>
								<input type="hidden" id="idCheck" name="idCheck" />
							</div><br>
							<div class="w_100p_center mt_5 fl">
								<span id="idConfirm" class="">※ 아이디를 입력 후 중복확인을 클릭하세요.</span>
							</div>
						</td>
					</tr>
					<tr>
						<th>이름 <span class="font-color_H">*</span></th>
						<td>
							<input type="text" id="txtUserNm" name="txtUserNm" maxlength="30" class="w_200px input_com"/>
						</td>
					</tr>
					<tr>
						<th>권한 <span class="font-color_H">*</span></th>
						<td>
							<select name="selAuthorCd" id="selAuthorCd" class="input_com w_200px">
								<option value=''>선택</option>
								<c:forEach items="${cboAuth}" var="auth" varStatus="status">
								<option value='<c:out value="${auth.author_cd}"/>'><c:out value="${auth.author_nm}"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--버튼 -->
		<div class="r_btnbox">
			<div style="display: inline-block;">
				<button type="button" class="comm_btn mr_5" onclick="fnSaveUser();">저장</button>
				<button type="button" class="bk_color comm_btn mr_5" id="btnClose">취소</button>
			</div>
		</div>
		<!--//버튼  -->
	</div>
</div>
</form>