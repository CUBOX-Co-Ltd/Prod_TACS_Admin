<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/jsp/cubox/common/checkPasswd.jsp" flush="false"/>
<script type="text/javascript">
$(function() {
	$(".title_tx").html("계정 관리");
	modalPopup ("siteEditPopup", "계정 편집", 650, 460);
	modalPopup ("siteAddPopup", "계정 등록", 650, 485);

	$("#addSiteUser").on("click", function(event){
		fnClear();
		$("#siteAddPopup").PopupWindow("open");
		$("#fsiteid").focus();
	});
	
	$("#btnAddClose").click(function(){
		$("#siteAddPopup").PopupWindow("close");
	});
	
	$("#btnEditClose").click(function(){
		fnClear();
		$("#siteEditPopup").PopupWindow("close");
	});
	$(".onlyNumber").keyup(function(event){
		if (!(event.keyCode >=37 && event.keyCode<=40)) {
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^0-9]/gi,''));
		}
	});
	
	$("#srchRecPerPage").change(function(){
		pageSearch("1");
	});

	$("#btnReset").click(function(){
		$("#srchCond").val("");
		$("#srchFsiteid").val("");
		$("#srchFname").val("");
		$("#srchAuthorId").val("");
	});
	
	$("#srchFsiteid").keyup(function(e){if(e.keyCode == 13) pageSearch("1");});
	$("#srchFname").keyup(function(e){if(e.keyCode == 13) pageSearch("1");});
});

function fnClear(){
	$("#fsiteid").attr("readonly", false);
	$("#idCheck").val("");
	$("#idConfirm").html("");
	$("input[name=fsiteid]").val("");
	$("input[name=fname]").val("");
	$("input[name=fphone01]").val("");
	$("input[name=fphone02]").val("");
	$("input[name=fphone03]").val("");
	$("input[name=fuid]").val("");
	$("input[name=funm]").val("");
	$("#siteId2 option:eq(0)").prop('selected', 'selected');
	$("#authorId2 option:eq(0)").prop('selected', 'selected');
	$("#siteId option:eq(0)").prop('selected', 'selected');
	$("#authorId option:eq(0)").prop('selected', 'selected');
	$("#fsiteidTxt").html("");
}

//아이디 체크
function fnIdCheck(){
	var fsiteid = $("#fsiteid").val();
	var isID = /^[a-z0-9][a-z0-9_\-]{4,19}$/;
	if (!isID.test(fsiteid)) {
		alert("5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
		$("#fsiteid").focus();
		return;
	}
	if(!fnIsEmpty(fsiteid)) {
		$.ajax({
			type:"POST",
			url:"<c:url value='/basicInfo/idDplctCnfirm.do' />",
			data:{
				"checkId": fsiteid
			},
			dataType:'json',
			//timeout:(1000*30),
			success:function(returnData, status){
				if(status == "success") {
					if(returnData.usedCnt > 0 ){
						//사용할수 없는 아이디입니다.
						$("#idConfirm").html("<font color='#ff5a00'>※  사용할 수 없는 아이디 입니다.</font>");
					}else{
						//사용가능한 아이디입니다.
						$("#idConfirm").html("<font color='#009fc0'>※  사용가능한 아이디 입니다.</font>");
						$("#fsiteid").attr("readonly", true);
						$("#idCheck").val("Y");
					}
				}else{ alert("ERROR!");return;}
			}
		});
	}
}

//계정추가
function fnSiteUserAddSave() {
	var fsiteid = $("#fsiteid").val();
	var idCheck = $("#idCheck").val();
	var fname = $("#fname").val();
	var fuid = $("#fuide").val();
	var siteId = $("#siteId").val();
	var authorId = $("#authorId").val();
	var fphone01 = $("#fphone01").val();
	var fphone02 = $("#fphone02").val();
	var fphone03 = $("#fphone03").val();

	if(fnIsEmpty(fsiteid)){alert ("아이디를 입력하세요."); $("#fsiteid").focus(); return;}
	if(fnIsEmpty(idCheck) || idCheck != "Y"){alert ("아이디 중복체크를 확인하세요."); return;}
	if(fnIsEmpty(fname)){alert ("담당자를 입력하세요."); $("#fname").focus(); return;}
	if(fnIsEmpty(siteId)){alert ("사이트를 선택하세요."); $("#siteId").focus(); return;}
	if(fnIsEmpty(authorId)){alert ("사이트 권한을 선택하세요."); $("#authorId").focus(); return;}

	if(confirm("비밀번호는 아이디 + 1234 입니다. \n저장하시겠습니까?")){
		$.ajax({
			type:"POST",
			url:"<c:url value='/basicInfo/siteUserAddSave.do' />",
			data:{
				"fsiteid": fsiteid,
				"fname": fname,
				"fuid":fuid,
				"siteId":siteId,
				"authorId":authorId,
				"fphone01": fphone01,
				"fphone02": fphone02,
				"fphone03": fphone03
			},
			dataType:'json',
			//timeout:(1000*30),
			success:function(returnData, status){
				if(status == "success") {
					//location.reload();
					pageSearch("1");
				}else{ alert("ERROR!");return;}
			}
		});
	}
}

function fnSiteUserInfoChange(fsiteid, fkind3, femergency, fname, fphone, fauthcd, fuid, funm, siteId, authorId){
	$("#fsiteidTxt").html(fsiteid);
	$("#fsiteid2").val(fsiteid);
	$("#fname2").val(fname);
	$("#fuide2").val(fuid);
	$("#funme2").val(funm);
	$("#siteId2").val(siteId);
	$("#authorId2").val(authorId);

	if(!fnIsEmpty(fphone)) {
		var fphone2 = fphone.split('-');
		$("#fphone012").val(fphone2[0]);
		$("#fphone022").val(fphone2[1]);
		$("#fphone032").val(fphone2[2]);
	}
	//계정편집 open
	$("#siteEditPopup").PopupWindow("open");
	$("#fname2").focus();
}

//계정편집
function fnSiteUserInfoChangeSave() {
	var fsiteid = $("#fsiteid2").val();
	var fname = $("#fname2").val();
	var fuid = $("#fuide2").val();
	var siteId = $("#siteId2").val();
	var authorId = $("#authorId2").val();
	var fphone01 = $("#fphone012").val();
	var fphone02 = $("#fphone022").val();
	var fphone03 = $("#fphone032").val();

	if(fnIsEmpty(fname)){alert ("담당자를 입력하세요."); $("#fname2").focus(); return;}
	if(fnIsEmpty(siteId)){alert ("사이트를 선택하세요."); $("#siteId2").focus(); return;}
	if(fnIsEmpty(authorId)){alert ("사이트 권한을 선택하세요."); $("#authorId2").focus(); return;}

	if(confirm("저장하시겠습니까?")){
		$.ajax({
			type:"POST",
			url:"<c:url value='/basicInfo/siteUserInfoChangeSave.do' />",
			data:{
				"fsiteid": fsiteid,
				"fname": fname,
				"fuid": fuid,
				"siteId": siteId,
				"authorId": authorId,
				"fphone01": fphone01,
				"fphone02": fphone02,
				"fphone03": fphone03
			},
			dataType:'json',
			//timeout:(1000*30),
			success:function(returnData, status){
				if(status == "success") {
					//location.reload();
					//$("#siteAddPopup").PopupWindow("close");
					pageSearch("1");
				}else{ alert("ERROR!");return;}
			}
		});
	}
}

//계정사용유무변경
function fnSiteUserFuseynChangeSave(fsiteid, fuseyn) {
	var fuseyn2 = "";
	var confirmTxt = "";
	if(!fnIsEmpty(fuseyn) && fuseyn == 'Y'){
		confirmTxt = "사용안함으로 변경하시겠습니까?";
		fuseyn2 = "N";
	}else{
		confirmTxt = "사용중으로 변경하시겠습니까?";
		fuseyn2 = "Y";
	}

	if(confirm(confirmTxt)){
		$.ajax({
			type:"POST",
			url:"<c:url value='/basicInfo/siteUserFuseynChangeSave.do' />",
			data:{
				"fsiteid": fsiteid,
				"fuseyn": fuseyn2
			},
			dataType:'json',
			//timeout:(1000*30),
			success:function(returnData, status){
				if(status == "success") {
					location.reload();
				}else{ alert("ERROR!");return;}
			}
		});
	}
}

//계정비밀번호 초기화
function fnSiteUserPasswdReset(fsiteid){
	if(confirm(fsiteid + " 비밀번호를 초기화 하시겠습니까? \n초기화시 비밀번호는 아이디 + 1234로 변경이 됩니다.")){
		$.ajax({
			type:"POST",
			url:"<c:url value='/basicInfo/siteUserPasswdReset.do' />",
			data:{
				"fsiteid": fsiteid
			},
			dataType:'json',
			//timeout:(1000*30),
			success:function(returnData, status){
				if(status == "success") {
					location.reload();
				}else{ alert("ERROR!");return;}
			}
		});
	}
}

function pageSearch(page){
	f = document.frmSearch;
	
	$("#srchPage").val(page);
	
	f.action = "/basicInfo/accountMngmt.do";
	f.submit();
}

</script>
<form id="frmSearch" name="frmSearch" method="post">
<input type="hidden" id="srchPage" name="srchPage" value="${pagination.curPage}"/>
<!--//검색박스 -->
<div class="search_box mb_20">
	<div class="search_in">
		<div class="comm_search mr_10">
			<select name="srchCond" id="srchCond" size="1" class="w_150px input_com">
				<option value="">센터를 선택하세요.</option>
				<c:forEach items="${centerList}" var="site" varStatus="status">
				<option value='<c:out value="${site.siteId}"/>' <c:if test="${site.siteId eq siteUserVO.site_id}">selected</c:if>><c:out value="${site.siteNm}"/></option>
				</c:forEach>
			</select>
		</div>  
		<div class="comm_search  mr_10">
			<input type="text" class="w_150px input_com" id="srchFsiteid" name="srchFsiteid" value="${siteUserVO.fsiteid}" placeholder="아이디를 입력하세요.">
		</div>
		<div class="comm_search  mr_10">
			<input type="text" class="w_150px input_com" id="srchFname" name="srchFname" value="${siteUserVO.fname}" placeholder="담당자를 입력하세요.">
		</div>
		<div class="comm_search mr_10">
			<select name="srchAuthorId" id="srchAuthorId" size="1" class="w_190px input_com">
				<option value="">사이트권한을 선택하세요.</option>
				<c:forEach items="${authorList}" var="author" varStatus="status">
				<option value='<c:out value="${author.authorId}"/>' <c:if test="${author.authorId eq siteUserVO.author_id}">selected</c:if>><c:out value="${author.authorNm}"/></option>
				</c:forEach>
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
			<b class="fl mr_10">전체 : <c:out value="${pagination.totRecord}"/>건</b>
			<!-- 건수 -->
			<select name="srchRecPerPage" id="srchRecPerPage" class="input_com w_80px">
				<c:forEach items="${cntPerPage}" var="cntPerPage" varStatus="status">
				<option value='<c:out value="${cntPerPage.fvalue}"/>' <c:if test="${cntPerPage.fvalue eq pagination.recPerPage}">selected</c:if>><c:out value="${cntPerPage.fkind3}"/></option>
				</c:forEach>
			</select>
		</div>	<!--버튼 -->
		<div class="r_btnbox  mb_10">
			<button type="button" class="btn_middle color_basic" id="addSiteUser">추가</button>
		</div>
		<!--//버튼  -->
	</div>
	<!--테이블 시작 -->
	<div class="tb_outbox">
		<table class="tb_list">
			<colgroup>
				<col width="50px" />
				<col width="9%"/>
				<col width="9%" />
				<col width="9%" />
				<col width="9%" />
				<col width="9%" />
				<col width="9%" />
				<col width="9%" />
				<col width="5%" />
				<col width="90px" />
				<col width="80px" />
				<col width="70px" />
				<col width="80px" />
			</colgroup>
			<thead>
				<tr>
					<th>순번</th>
					<th>아이디</th>
					<th>담당자</th>
					<th>연락처</th>
					<th>출입자ID</th>
					<th>출입자명</th>
					<th>센터</th>
					<th>사이트권한</th>
					<th>암호변경여부</th>
					<th>등록일자</th>
					<th>사용유무</th>
					<th>편집</th>
					<th>비밀번호</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${siteUserList}" var="sList" varStatus="status">
				<tr>
					<td>${(pagination.totRecord - (pagination.totRecord-status.index)+1)  + ( (pagination.curPage - 1)  *  pagination.recPerPage ) }</td>
					<td><c:out value="${sList.fsiteid}"/></td>
					<td><c:out value="${sList.fname}"/></td>
					<td><c:out value="${sList.fphone}"/></td>
					<td><c:out value="${sList.fuid}"/></td>
					<td><c:out value="${sList.funm}"/></td>
					<td><c:out value="${sList.site_nm}"/></td>
					<td><c:out value="${sList.author_nm}"/></td>
					<td><c:out value="${sList.fpasswdyn}"/></td>
					<td><c:out value="${sList.fregdt2}"/></td>
					<td>
					<c:if test="${sList.fuseyn eq 'Y'}"><button type="button" class="btn_small color_basic" onclick="fnSiteUserFuseynChangeSave('<c:out value="${sList.fsiteid}"/>','<c:out value="${sList.fuseyn}"/>');">사용중</button></c:if>
					<c:if test="${sList.fuseyn eq 'N'}"><button type="button" class="btn_small color_gray" onclick="fnSiteUserFuseynChangeSave('<c:out value="${sList.fsiteid}"/>','<c:out value="${sList.fuseyn}"/>');">사용안함</button></c:if>
					</td>
					<td><button type="button" class="btn_small color_basic" data-toggle="modal" onclick="fnSiteUserInfoChange('<c:out value="${sList.fsiteid}"/>','<c:out value="${sList.fkind3}"/>','<c:out value="${sList.femergency}"/>','<c:out value="${sList.fname}"/>','<c:out value="${sList.fphone}"/>','<c:out value="${sList.fauthcd}"/>','<c:out value="${sList.fuid}"/>','<c:out value="${sList.funm}"/>','<c:out value="${sList.site_id}"/>','<c:out value="${sList.author_id}"/>');">편집</button></td>
					<td><button type="button" class="btn_small color_color1" onclick="fnSiteUserPasswdReset('<c:out value="${sList.fsiteid}"/>');">초기화</button></td>
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

<!-- modal : 편집 -->
<div id="siteEditPopup" class="example_content">
	<div class="popup_box">
		<!--테이블 시작 -->
		<div class="tb_outbox mb_20">
			<input type="hidden" id="fsiteid2" name="fsiteid" />
			<table class="tb_write_02 tb_write_p1">
				<col width="25%" />
				<col width="75%" />
				<tbody>
					<tr>
						<th>아이디</th>
						<td id="fsiteidTxt"></td>
					</tr>
					<tr>
						<th>담당자 <span class="font-color_H">*</span></th>
						<td>
							<input type="text" id="fname2" name="fname" maxlength="30" class="w_190px input_com" />
						</td>
					</tr>
					<tr>
						<th>연락처</th>
						<td>
							<input type="text" id="fphone012" name="fphone01" maxlength="3" class="w_70px input_com fl onlyNumber" /><span class="ml_5 mr_5 fl">-</span>
							<input type="text" id="fphone022" name="fphone02" maxlength="4" class="w_70px input_com fl onlyNumber ml_5" /><span class="ml_5 mr_5 fl">-</span>
							<input type="text" id="fphone032" name="fphone03" maxlength="4" class="w_70px input_com fl onlyNumber ml_5" />
						</td>
					</tr>
					<tr>
						<th>출입자 연결</th>
						<td>
							<input type="text" id="fuide2" name="fuid" class="input_com fl" maxlength="15" placeholder="출입자아이디" readonly="readonly" style="width:calc(30% - 5px)">
							<input type="text" id="funme2" name="funm" class="input_com fl w_30p_center ml_5" maxlength="15" placeholder="출입자명" readonly="readonly">
							<button type="button" class="btn_small color_color1 fl ml_5" style="height: 30px" onclick="openPopup('/userInfo/tuserListPopup.do','출입자확자', 850, 620)">출입자조회</button>
						</td>
					</tr>
					<tr>
						<th>센터 <span class="font-color_H">*</span></th>
						<td>
							<select name="siteId" id="siteId2" class="form-control w_190px">
								<c:forEach items="${centerList}" var="site" varStatus="status">
								<option value='<c:out value="${site.siteId}"/>'><c:out value="${site.siteNm}"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>사이트권한 <span class="font-color_H">*</span></th>
						<td>
							<select name="authorId" id="authorId2" class="form-control w_190px">
								<c:forEach items="${authorList}" var="author" varStatus="status">
								<option value='<c:out value="${author.authorId}"/>'><c:out value="${author.authorNm}"/></option>
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
				<button type="button" class="comm_btn mr_5" onclick="fnSiteUserInfoChangeSave();">저장</button>
				<button type="button" class="bk_color comm_btn mr_5" id="btnEditClose">취소</button>
			</div>
		</div>
		<!--//버튼  -->
	</div>
</div>

<!-- modal : 등록 -->
<div id="siteAddPopup" class="example_content">
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
								<input type="text" id="fsiteid" name="fsiteid" maxlength="20" class="w_190px input_com fl"/>
								<button type="button" class="btn_small color_basic fl ml_5" style="height: 30px" onclick="fnIdCheck();">중복체크</button>
								<input type="hidden" id="idCheck" name="idCheck" />
							</div>
							<div class="w_100p_center mt_5 fl">
								<span id="idConfirm" class="">※ 아이디를 입력 후 중복확인을 클릭하세요.</span>
							</div>
						</td>
					</tr>
					<tr>
						<th>담당자 <span class="font-color_H">*</span></th>
						<td>
							<input type="text" id="fname" name="fname" maxlength="30" class="w_190px input_com" />
						</td>
					</tr>
					<tr>
						<th>연락처</th>
						<td>
							<input type="text" id="fphone01" name="fphone01" maxlength="3" class="w_70px input_com fl onlyNumber" /><span class="ml_5 mr_5 fl">-</span>
							<input type="text" id="fphone02" name="fphone02" maxlength="4" class="w_70px input_com fl onlyNumber ml_5" /><span class="ml_5 mr_5 fl">-</span>
							<input type="text" id="fphone03" name="fphone03" maxlength="4" class="w_70px input_com fl onlyNumber ml_5" />
						</td>
					</tr>
					<tr>
						<th>출입자 연결</th>
						<td>
							<input type="text" id="fuide" name="fuid" class="input_com fl" maxlength="15" placeholder="출입자아이디" readonly="readonly" style="width:calc(30% - 5px)">
							<input type="text" id="funme" name="funm" class="input_com fl w_30p_center ml_5" maxlength="15" placeholder="출입자명" readonly="readonly">
							<button type="button" class="btn_small color_color1 fl ml_5" style="height: 30px" onclick="openPopup('/userInfo/tuserListPopup.do','출입자조회', 850, 620)">출입자조회</button>
						</td>
					</tr>
					<tr>
						<th>센터 <span class="font-color_H">*</span></th>
						<td>
							<select name="siteId" id="siteId" class="form-control w_190px">
								<c:forEach items="${centerList}" var="site" varStatus="status">
								<option value='<c:out value="${site.siteId}"/>'><c:out value="${site.siteNm}"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>사이트권한 <span class="font-color_H">*</span></th>
						<td>
							<select name="authorId" id="authorId" class="form-control w_190px">
								<c:forEach items="${authorList}" var="author" varStatus="status">
								<option value='<c:out value="${author.authorId}"/>'><c:out value="${author.authorNm}"/></option>
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
				<button type="button" class="comm_btn mr_5" onclick="fnSiteUserAddSave();">저장</button>
				<button type="button" class="bk_color comm_btn mr_5" id="btnAddClose">취소</button>
			</div>
		</div>
		<!--//버튼  -->
	</div>
</div>

