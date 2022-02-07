<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../frame/sub/head.jsp" />
<style>
	.tb_file td .file_box{ width: 100%; float: left; font-size: 12px; color: #999; line-height: 20px}
	.tb_file td .file_box em{ float: left; font-size: 12px; color: #666; margin-right: 5px}

	a.btn_file{  border: 1px solid #c7c7c7; line-height: 22px; color: #333; font-size: 10px!important;   font-weight: 400; border-bottom: 1px solid #727272;  border-right: 1px solid #727272 ; cursor: pointer; position: relative; transition: all 0.5s;
	-moz-transition: all 0.5s; /* Firefox 4 */
	-webkit-transition: all 0.5s; /* Safari and Chrome */
	-o-transition: all 0.5s; /* Opera */  background-color: #fff; display: inline-block}
	a.btn_file:hover{   border: 1px solid #4464a3; line-height: 22px; color: #5384d0; font-size: 10px!important;  background-color: #fff;  font-weight: 400; border-bottom: 1px solid #1f3d78;  border-right: 1px solid #1f3d78; cursor: pointer;; position: relative; z-index: 9}
	.icon_n { padding: 0px 15px 0px 15px; }
</style>
<script type="text/javascript">

	$(document).ready(function(){
		<c:if test="${fn:length(ifr_id) > 0}">
		    var iframeHeight = document.body.scrollHeight;
			parent.document.getElementById("${ifr_id}").height = iframeHeight;
		</c:if>
	});

	function fnDownload(sFileID, sFileSn){
		location.href = "<c:url value='/file/getFileBinary.do'/>?atchFileId=" + sFileID + "&fileSn=" + sFileSn;
	}


<c:if test="${owner eq 'Y'}">
	function fnDelete(sFileID, sFileSn){
		var queryString =  "atchFileId=" + sFileID + "&fileSn=" + sFileSn;
		
		$.ajax({
			type : "post",
			url  : "<c:url value='/file/deleteAjax.do'/>",
			data : queryString,
			dataType :'json', 
			success:function(returnData, status){
				if(returnData.ajaxData.result == "1"){
					alert("삭제되었습니다.");
					location.reload();
				}else{
					alert("삭제 진행중 오류가 발생하였습니다.");
				}
			}
		});
	}
</c:if>

</script>

<div>
	<table class="tb_file">
		<tr>
			<td>
			<c:if test="${fn:length(result) == 0}">
				<div class="file_box"></div>
			</c:if>
			<c:forEach var="list" items="${result}" varStatus="status">
				<div class="file_box">
					<a href="javascript:fnDownload('${list.atchFileId}','${list.fileSn}');">${list.orignlFileNm}</a> (<fmt:formatNumber value="${list.fileSize / 1024 / 1024}" pattern="0.00"/> mbyte)
					<c:if test="${owner eq 'Y'}"><a href="javascript:fnDelete('${list.atchFileId}','${list.fileSn}');" class="btn_file icon_n mr5">삭제</a></c:if>
				</div>
			</c:forEach>
			</td>
		</tr>
	</table>
</div>



