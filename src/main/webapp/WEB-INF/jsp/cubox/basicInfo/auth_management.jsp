<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/jsp/cubox/common/checkPasswd.jsp" flush="false"/>
<script type="text/javascript">
	$(function() {
		$(".title_tx").html("권한 관리");
		modalPopup("authAddPopup", "권한 등록", 500, 370);

		$("#addSiteUser").on("click", function(event){
			$(".popupwindow_titlebar_text").html("권한 등록");
			fnClear();
			$("#frmAuthInfo").find("#tdAuthorId").html("자동발번");
			$("#authAddPopup").PopupWindow("open");
			$("#txtAuthorNm").focus();
		});
	});
	
	function fnSave() {
		if(!fnFormValueCheck("frmAuthInfo")) return;
		
		var formData = $("form[id=frmAuthInfo]").serialize();
		
		if(fnIsEmpty($("#hidAuthorId").val())) {
			$.ajax({
				url: "<c:url value='/basicInfo/authAddSave.do'/>",
				type: "POST",
				data: formData,
				dataType: "json",
				success: function(returnData){
					if(returnData.authSaveCnt > 0) {
						location.reload();
					} else {alert("ERROR!");return;}
				},
				error: function(jqXHR){
					alert("저장에 실패했습니다.");
					return;
				}
			});
		} else {
			$.ajax({
				url: "<c:url value='/basicInfo/authEditSave.do'/>",
				type: "POST",
				data: formData,
				dataType: "json",
				success: function(returnData){
					if(returnData.authEditCnt > 0) {
						location.reload();
					} else {alert("ERROR!");return;}
				},
				error: function(jqXHR){
					alert("저장에 실패했습니다.");
					return;
				}
			});
		}
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
				type:"POST",
				url:"<c:url value='/basicInfo/authUseynChangeSave.do' />",
				data:{
					"authId": str,
					"useYn": useyn
				},
				dataType:'json',
				//timeout:(1000*30),
				success:function(returnData, status){
					if(returnData.groupAuthCnt > 0) {
						location.reload();
					} else {alert("ERROR!");return;}
				}
			});
		} else {
			return;
		}
	}

	function fnEditPop(sId, sNm, sDesc, sOrdr, sUseYn) {
		$(".popupwindow_titlebar_text").html("권한 편집");
		$("#frmAuthInfo").find("#tdAuthorId").html(sId);
		$("#hidAuthorId").val(sId);
		$("#txtAuthorNm").val(sNm);
		$("#txtAuthorDesc").val(sDesc);
		$("#txtSortOrdr").val(sOrdr);
		$("#selUseYn").val(sUseYn);
		$("#authAddPopup").PopupWindow("open");
		$("#txtAuthorNm").focus();		
	}
	
	function fnClear() {
		$("#frmAuthInfo").find("#tdAuthorId").html("");
		$("#frmAuthInfo").find("#hidAuthorId").val("");
		$("#frmAuthInfo").find("#txtAuthorNm").val("");
		$("#frmAuthInfo").find("#txtAuthorDesc").val("");
		$("#frmAuthInfo").find("#txtSortOrdr").val("");
		$("#frmAuthInfo").find("#selUseYn").val("Y");		
	}
	
	function fnCancel() {
		fnClear();
		$("#authAddPopup").PopupWindow("close");
	}


</script>
<!--//검색박스 -->
<!--------- 목록--------->
<div class="com_box ">
	<div class="r_btnbox  mb_10">
		<button type="button" class="btn_middle color_basic" id="addSiteUser">추가</button>
	</div>
	<!--버튼 -->
	<!--//버튼  -->
	<!--테이블 시작 -->
	<div class="tb_outbox">
		<table class="tb_list">
			<colgroup>
				<col width="10%">
				<col width="15%">
				<col width="20%">
				<col width="5%">
				<col width="17%">
				<col width="17%">
				<col width="8%">
				<col width="8%">
			</colgroup>
			<thead>
				<tr>
					<th>권한 ID</th>
					<th>권한 명</th>
					<th>권한 설명</th>
					<th>순서</th>
					<th>등록일</th>
					<th>수정일</th>
					<th>사용유무</th>
					<th>편집</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${authList}" var="aList" varStatus="status">
				<tr>
					<td><c:out value="${aList.authorId}"/></td>
					<td><c:out value="${aList.authorNm}"/></td>
					<td><c:out value="${aList.authorDesc}"/></td>
					<td><c:out value="${aList.sortOrdr}"/></td>
					<td><c:out value="${aList.registDt}"/></td>
					<td><c:out value="${aList.modifyDt}"/></td>
					<td><c:if test="${aList.useYn eq 'Y'}"><button type="button" class="btn_small color_basic" onclick="fnModUseYn('<c:out value="${aList.authorId}"/>','<c:out value="${aList.useYn}"/>');">사용중</button></c:if>
						<c:if test="${aList.useYn eq 'N'}"><button type="button" class="btn_small color_gray" onclick="fnModUseYn('<c:out value="${aList.authorId}"/>','<c:out value="${aList.useYn}"/>');">사용안함</button></c:if>
					</td>
					<td><button type="button" class="btn_small color_basic" data-toggle="modal" onclick="fnEditPop('<c:out value="${aList.authorId}"/>', '<c:out value="${aList.authorNm}"/>', '<c:out value="${aList.authorDesc}"/>', '<c:out value="${aList.sortOrdr}"/>', '<c:out value="${aList.useYn}"/>');">편집</button></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!--------- //목록--------->
</div>

<!-- modal : 등록 -->
<div id="authAddPopup" class="example_content">
<form id="frmAuthInfo" name="frmAuthInfo" method="post">
	<input type="hidden" id="hidAuthorId" name="hidAuthorId" />
	<input type="hidden" id="selUseYn" name="selUseYn" value="Y" />
	<div class="popup_box">
		<!--테이블 시작 -->
		<div class="tb_outbox mb_20">
			<table class="tb_write_02 tb_write_p1">
				<col width="25%" />
				<col width="75%" />
				<tbody>
					<tr>
						<th>권한 ID</th>
						<td id="tdAuthorId"></td>
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
