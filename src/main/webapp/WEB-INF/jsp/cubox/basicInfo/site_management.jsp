<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:eval expression="@property['Globals.formatList']" var="formatList" /> 
<body>
	<script type="text/javascript">
		$(function() {
			$(".title_tx").html("사이트 관리");
			modalPopup ("siteEditPopup", "사이트 편집", 800, 320);
			modalPopup ("siteAddPopup", "사이트 등록", 500, 320);
			modalPopup ("siteDrawEditPopup", "도면 관리", 1000, 620);
			
			$("#addSiteUser").on("click", function(event){
				$("#txtSiteNm").val("");
				$("#txtSiteDesc").val("");
				$("#txtSortOrdr").val("");
				
		        $("#siteAddPopup").PopupWindow("open");
		        $("#txtSiteNm").focus();
		    });
			
			$("#btnAddClose").click(function(){
				$("#siteAddPopup").PopupWindow("close");	
			});
			
			$("#btnEditClose").click(function(){
				$("#txtEditSiteNm").val("");
				$("#txtEditSiteDesc").val("");
				$("#txtEditSortOrdr").val("");
				$("#txtEditSiteId").val("");
				
				$("#siteEditPopup").PopupWindow("close");	
			});
			
			var index = $("#hidIndex").val();
			$(index).MultiFile({
				accept: '<c:forEach var="format" items="${formatList}" varStatus="status">${format}<c:if test="${!status.last}">|</c:if></c:forEach>',
				max: Number('${option.posblAtchFileNumber}'),
				list: '#fileList',
				STRING: {
					remove: "<img src='/img/icon_x.png' id='btnRemove'>",
					selected: 'Selecionado: $file',
					denied: '$ext 는(은) 업로드 할수 없는 파일확장자 입니다.',
					duplicate: '$file 는(은) 이미 추가된 파일입니다.'
				}
			});
		});
		
		function fnSiteAddSave(){
			var   siteNm = $("#txtSiteNm").val();
			var siteDesc = $("#txtSiteDesc").val();
			var sortOrdr = $("#txtSortOrdr").val();
			
			if(fnIsEmpty(siteNm)) {
				alert("사이트명을 입력하세요.");
				$("#txtSiteNm").focus();
				return;
			}
			
			$.ajax({
				url: "<c:url value='/basicInfo/siteAddSave.do' />",
				data: {
					"siteNm": siteNm,
					"siteDesc":siteDesc,
					"sortOrdr":sortOrdr
					
				},
				dataType:'json',
				traditional:true,
				type: "POST",
				success:function(returnData, status){
					if(status == "success") {
						location.reload();
						//$("#siteAddPopup").PopupWindow("close");
					}else{ alert("ERROR!");return;}
				}
			});
		}
		
		
	//	
	function fnSiteUpdate(siteId, siteNm, siteDesc, sortOrdr){	
		$("#txtEditSiteNm").val(siteNm);
		$("#txtEditSiteDesc").val(siteDesc);
		$("#txtEditSortOrdr").val(sortOrdr);
		$("#txtEditSiteId").val(siteId);

		$("#siteEditPopup").PopupWindow("open");
	}
	
	//도면관리
	 function fnDrawUpdate(siteId, siteNm, siteDesc, sortOrdr){	
		$("#txtEditSiteNm").val(siteNm);
		$("#txtEditSiteDesc").val(siteDesc);
		$("#txtEditSortOrdr").val(sortOrdr);
		$("#txtEditSiteId").val(siteId);
		
		$.ajax({
			url: "<c:url value='/basicInfo/getFloorList.do'/>",
			data: {
				"siteId": siteId
			},
			dataType:'json',
			traditional:true,
			type: "POST",
			success:function(returnData, status){
				if(status == "success") {
					var floorList = "";
					for(var i = 0; i < returnData.floorList.length; i++){
						var test = "";
						floorList = floorList
									+"<input type='hidden' id='idx"+ parseInt(i+1) +"' value='"
									+ returnData.floorList[i].idx
									+"'/>"
									+ "<tr><td>"
									+ parseInt(i+1)
									+ "</td><td>"
									+ returnData.floorList[i].floor.replace("-","")
									+ "</td><td>"
									+ "<input type='checkbox' name='chkUnder"+parseInt(i+1)+"' value='chkUnder'";
									if(returnData.floorList[i].floor.indexOf("-")!== -1){
										test = "checked"
									}
									floorList += test + "></td><td>"
									+ "<input type='text' id='txtEditFloorNm' name='txtEditFloorNm'  class='w_100p input_com' value='"
									+ returnData.floorList[i].floorNm
									+ "'></td>"
									+ "<td><iframe id='ifrFil' src='<c:url value='/file/floorFileListIframe.do'/>?ifr_id=ifrFile&atchFileId="+returnData.floorList[i].atchFileId+"' scrolling='no' frameborder='0' width='100%' height='20px'></iframe></td>"
									+ "<td><input type='file' id='file"+parseInt(i+1)+"' name='file"+parseInt(i+1)+"' style='padding-left: 10px;'></td>"
									+ "<td><button type='button' class='btn_small color_basic mr_5' onClick='floorUpload(this)'>저장</button>"
									+ "<button type='button' class='btn_small color_gray' onClick='fnDeleteTr(this)'>삭제</button></td></tr>";
							}
						
							$('#floorBody').html(floorList);
						} else {
							alert("ERROR!");
							return;
						}
					}
				});

			$("#siteDrawEditPopup").PopupWindow("open");
		} 
		
		function fnAddFloor(){
			var lastTd = $('#floorBody tr:last').children();
			var index = Number(lastTd.eq(0).text())+1;

			var innerHtml = "";
				innerHtml += "<tr>";
				innerHtml += "<td>"+index+"</td>";
				innerHtml += "<td><input type='text' class='input_com w_100p' id='newFloor' name='newFloor' maxlength='2' onkeyup='fnvalichk(event)'/></td>";
				innerHtml += "<td><input type='checkbox' name='chkUnder"+index+"' id='chkUnder'></td>";
				innerHtml += "<td><input type='text' class='input_com w_80p' id='newEditFloorNm' name='newEditFloorNm' maxlength='15'/></td>";
				innerHtml += "<td><input type='input' value='파일'></td>"
				innerHtml += "<td><input type='file' id='file"+index+"' name='file"+index+"' style='padding-left: 10px;'></td>"
				innerHtml += "<td><button type='button' class='btn_small color_basic mr_5' onClick='floorUpload(this)'>저장</button>"
				innerHtml += "<button type='button' class='btn_small color_gray' onClick='fnDeleteTr(this)'>삭제</button></td>"
				innerHtml += "</tr>";
			
			$('#floorBody').append(innerHtml);
			
		}
		
		function fnDeleteTr(obj){
			var tr = $(obj).parent().parent();
			
			var td = tr.children();
			var ordr = td.eq(0).text();
			var floor = td.eq(1).text();		
			
			var index = $("#idx"+ordr).val();
			
			$.ajax({
				 url : "<c:url value='/basicInfo/floorDelete.do' />",
				data : {
					    "idx" : index,
					    "floor" : floor
				       },
				dataType:'json',
				traditional:true,
				type: "POST",
				success:function(returnData, status){
					if(status == "success") {
						if(returnData.result == 1){
							alert("삭제되었습니다.");
							tr.remove();
							location.reload();
						}else if(returnData.result == -1){
							alert("사용하는 층이 있어 삭제할 수 없습니다.");
						}
						
						$("#siteEditPopup").PopupWindow("reload");
					}else{ alert("ERROR!");return;}
				}
			});
			
		}

		
		
		function floorUpload(obj){
			var tr = $(obj).parent().parent();
			var td = tr.children();
			
			var line = td.children();
			var floorNm = line.eq(1).val();
			var floor = td.eq(1).text();	
			var ordr = td.eq(0).text();
			
			var newFloor = "";
			if(floor==''){
				newFloor =  $("#newFloor").val();
				floorNm = $("#newEditFloorNm").val();
			}
			
			var index = $("#idx"+ordr).val();
			
			var chkUnder = "";
			if($('input:checkbox[name="chkUnder'+ordr+'"]').is(":checked")== true){
				chkUnder = "Y";
			}else{
				chkUnder = "N";
			}
			
			$("#hidChkUnder").val(chkUnder); // 지하체크
			$("#hidIndex").val(index); // idx
			$("#hidFloor").val(floor); // 층
			$("#hidNewFloor").val(newFloor); // 새로 등록하는 층
			$("#hidFloorNm").val(floorNm); // 층이름
			$("#hidOrdr").val(ordr); // 순번
			
			var formData = new FormData($("#drawForm")[0]);
			
			$.ajax({
				url: "<c:url value='/basicInfo/floorUpload.do'/>",
				type: "POST",
				enctype: "multipart/form-data",
				processData: false,
				contentType: false,
				data : formData,
				success: function(returnData,status){
					if(returnData.result == 1){
						alert("저장되었습니다.");
						location.reload();
					}else if(returnData.result == -2) {
						if(!fnIsEmpty(returnData.message)) {
							alert(returnData.message);
							
						} 
					}else if(returnData.result == -3){
						if(!fnIsEmpty(returnData.message)) {
							alert(returnData.message);
							location.reload();
						} 
					}
				},
					
				error: function (jqXHR){
					alert("저장에 실패했습니다."); //alert(jqXHR.responseText);
					return;
				}

				});
			
		}
		

		function fnSiteEditSave() {
			var txtEditSiteNm = $("#txtEditSiteNm").val();
			var txtEditSiteDesc = $("#txtEditSiteDesc").val();
			var txtEditSortOrdr = $("#txtEditSortOrdr").val();
			var txtEditSiteId = $("#txtEditSiteId").val();

			if (fnIsEmpty(txtEditSiteNm)) {
				alert("사이트명을 입력하세요.");
				$("#txtEditSiteNm").focus();
				return;
			}
			
			if (confirm("저장하시겠습니까?")) {
				$.ajax({
					type : "POST",
					url : "<c:url value='/basicInfo/siteEditSave.do' />",
					data : {
						"txtEditSiteNm" : txtEditSiteNm,
						"txtEditSiteDesc" : txtEditSiteDesc,
						"txtEditSortOrdr" : txtEditSortOrdr,
						"txtEditSiteId" : txtEditSiteId
					},
					dataType : 'json',
					//timeout:(1000*30),
					success : function(returnData, status) {
						if (status == "success") {
							location.reload();
							//$("#siteAddPopup").PopupWindow("close");
						} else {
							alert("ERROR!");
							return;
						}
					}
				});
			}
		}

		function fnSiteUseynChangeSave(siteId, useyn) {

			if (useyn == 'Y') {
				var confirmTxt = "사용안함으로 변경하시겠습니까?";
				var useyn2 = "N";
			} else {
				var confirmTxt = "사용중으로 변경하시겠습니까?";
				var useyn2 = "Y";
			}

			if (confirm(confirmTxt)) {
				$.ajax({
						type : "POST",
						url : "<c:url value='/basicInfo/siteUseynChangeSave.do' />",
						data : {
							"siteId" : siteId,
							"useyn" : useyn2
						},
						dataType : 'json',
						//timeout:(1000*30),
						success : function(returnData, status) {
							if (returnData.siteCnt > 0) {
								location.reload();
							} else {
								alert("ERROR!");
								return;
							}
						}
					});
				}

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
	</script>
	<form id="frmSearch" name="frmSearch" method="post">
	
	<input type="hidden" id="txtEditSiteId" name="txtEditSiteId" maxlength="50" size="10" />
	<!--//검색박스 -->
	<!--------- 목록--------->
	<div class="com_box ">
		<div class="totalbox" style="width: 50%;">
			<div class="r_btnbox  mb_10">
				<!-- <button type="button" class="btn_middle color_color1" 	onclick="fnGateEvent('CMD_TERMINAL_KOPEN',1)">KeepOpen</button>
				<button type="button" class="btn_middle color_basic" 	onclick="fnGateEvent('CMD_TERMINAL_KOPEN',0)">Open</button>
				<button type="button" class="btn_middle color_gray"		onclick="fnGateEvent('CMD_TERMINAL_KOPEN',2)">Close</button> -->
			</div>
		</div>
		<!--버튼 -->
		<div class="r_btnbox  mb_10">
			<button type="button" class="btn_middle color_basic" id="addSiteUser">추가</button>
		</div>

		<!--//버튼  -->
		<!--테이블 시작 -->
		<div class="tb_outbox">
			<table class="tb_list">
				<colgroup>
					<col style="width: 6%">
	                <col style="width: 12%">
					<col style="width: 20%">
					<col style="width: 10%">
					<col style="width: 15%">
					<col style="width: 15%">
					<col style="width: 8%">
					<col style="width: 8%">
	             </colgroup>
				<thead>
					<tr>
						<th>순서</th>
						<th>사이트명</th>
						<th>사이트설명</th>
						<th>등록자</th>						
						<th>등록일</th>
						<th>수정일</th>
						<th>사용유무</th>
						<th>편집</th>
						<th>도면관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${siteList}" var="sList" varStatus="status">
						<tr>
							<td><c:out value="${sList.sortOrdr}" /></td>
							<td><c:out value="${sList.siteNm}" /></td>
							<td><c:out value="${sList.siteDesc}" /></td>
							<td><c:out value="${sList.registId}" /></td>
							<td><c:out value="${sList.registDt}" /></td>
							<td><c:out value="${sList.modifyDt}" /></td>
							<td>
	                       		<c:if test="${sList.useyn eq 'Y'}"><button type="button" class="btn_small color_basic" onclick="fnSiteUseynChangeSave('<c:out value="${sList.siteId}"/>','<c:out value="${sList.useyn}"/>');">사용중</button></c:if>
	                       		<c:if test="${sList.useyn eq 'N'}"><button type="button" class="btn_small color_gray" onclick="fnSiteUseynChangeSave('<c:out value="${sList.siteId}"/>','<c:out value="${sList.useyn}"/>');">사용안함</button></c:if>
	                       	</td>
							<td><button type="button" class="btn_small color_basic" data-toggle="modal" onclick="fnSiteUpdate('${sList.siteId}',  '${sList.siteNm}','${sList.siteDesc}',  '${sList.sortOrdr}');">편집</button></td>
							<td><button type="button" class="btn_small color_basic" data-toggle="modal" onclick="fnDrawUpdate('${sList.siteId}',  '${sList.siteNm}','${sList.siteDesc}',  '${sList.sortOrdr}');">추가/수정</button></td> 
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
	<div id="siteAddPopup" class="example_content">
		<div class="popup_box">
			<!--테이블 시작 -->
			<div class="tb_outbox mb_20">
				<table class="tb_write_02 tb_write_p1">
					<col width="25%" />
					<col width="75%" />
					<tbody>
						<tr>
							<th>사이트명  <span class="font-color_H">*</span></th>
							<td>
								<input type="text" id="txtSiteNm" name="txtSiteNm" maxlength="50" class="w_100p input_com"/>
							</td>
						</tr>
						<tr>
							<th>사이트설명</th>
							<td>
								<input type="text" id="txtSiteDesc" name="txtSiteDesc" maxlength="50" class="w_100p input_com" />
							</td>
						</tr>
						<tr>
							<th>정렬순서</th>
							<td>
								<input type="text" id="txtSortOrdr" name="txtSortOrdr" maxlength="3" class="w_70px input_com" onkeyup="fnvalichk(event)" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	
	<!-- modal : 편집 -->
	<div id="siteEditPopup" class="example_content">
		<!--버튼 -->
	   	<div class="r_btnbox">
			<div style="display: inline-block;">
				<button type="button" class="comm_btn mr_5" onclick="fnSiteAddSave();">저장</button>
				<button type="button" class="bk_color comm_btn mr_5" id="btnAddClose">취소</button>
			</div>
		</div>
		<!--//버튼  -->
		<form id="addForm" name="addForm" method="post" enctype="multipart/form-data">
			<div class="popup_box" style="padding-top: 5px;">
				<!--테이블 시작 -->
				<div class="tb_outbox mb_20">
					<table class="tb_write_02 tb_write_p1">
						<col width="25%" />
						<col width="75%" />
						<tbody>
							<tr>
								<th>사이트명 <span class="font-color_H">*</span></th>
								<td>
									<input type="text" id="txtEditSiteNm" name="txtEditSiteNm" maxlength="50" class="w_100p input_com"/>
								</td>
							</tr>
							<tr>
								<th>사이트설명</th>
								<td>
									<input type="text" id="txtEditSiteDesc" name="txtEditSiteDesc" maxlength="50" class="w_100p input_com" />
								</td>
							</tr>
							<tr>
								<th>정렬순서</th>
								<td>
									<input type="text" id="txtEditSortOrdr" name="txtEditSortOrdr" maxlength="3" class="w_70px input_com" onkeyup="fnvalichk(event)" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</div>
	
	<!-- modal : 도면 관리 -->
	<div id="siteDrawEditPopup" class="example_content">
		<form id="drawForm" name="drawForm" method="post" enctype="multipart/form-data">
			<input type="hidden" id="hidIndex" name="hidIndex"/>
			<input type="hidden" id="hidOrdr" name="hidOrdr"/>
			<input type="hidden" id="hidFloor" name="hidFloor"/>
			<input type="hidden" id="hidFloorNm" name="hidFloorNm"/>
			<input type="hidden" id="hidNewFloor" name="hidNewFloor"/>
			<input type="hidden" id="hidChkUnder" name="hidChkUnder"/>
			<div class="popup_box" style="padding-top: 5px;">
				<div class="r_btnbox">
					<div style="display: inline-block;">
						<button type="button" class="comm_btn ml_5" onclick="fnAddFloor();" style="margin-bottom: 3px;">층 추가</button>
					</div>
				</div>
				<div class="tb_outbox">
					<table class="tb_list">
						<colgroup>
							<col style="width: 5%">
			                <col style="width: 5%">
							<col style="width : 8%">
							<col style="width : 24%">
							<col style="width : 24%">
							<col style="width : 20%">
							<col style="width : 14%">
			             </colgroup>
						<thead>
							<tr>
								<th>순번</th>
								<th>층</th>
								<th>지하 여부</th>
								<th>설명</th>
								<th>도면 파일</th>
								<th>도면 업로드</th>						
								<th>관리</th>						
							</tr>
						</thead>
						<tbody id="floorBody">
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</div>

</body>
