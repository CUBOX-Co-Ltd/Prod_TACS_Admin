<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../frame/sub/head.jsp" />
<script>
	$(document).ready(function(){
		$("#popupNm").html("출입자 목록");
		$("#TableID1").chromatable({
		width: "100%", // specify 100%, auto, or a fixed pixel amount
			height: "315px",
			scrolling: "yes"
		});
		$("#srchFid").focus();
	});

	//form submit
	function pageSearch(page) {
		var f = document.frmSearch;
		f.action = "/userInfo/tuserListPopup.do?srchPage=" + page;
		f.submit();
	}

	//검색
	function usrLogSearch(){
		var f = document.frmSearch;
		f.action = "/userInfo/tuserListPopup.do"
		f.submit();
	}

	$(function(){
		//초기화
		$("#reset").click(function(){
			$("#srchFid").val('');
			$("#srchFnm").val('');
			$("#srchCdno").val('');
		});
	});

	function fnSend(fid, fmn) {
		$("input[name=fuid]", opener.document).val(fid);
		$("input[name=funm]", opener.document).val(fmn);
		window.close();
	}
</script>
<body>
<jsp:include page="../frame/sub/popup_top.jsp" />
<form id="frmSearch"  name="frmSearch" method="post" onsubmit="return false;" >
<div class="popup_box">
	<!--검색박스 -->
	<div class="search_box mb_20">
		<div class="search_in">
			<div class="comm_search mr_20">
				<div class="title">ID</div>
				<input type="text" id="srchFid" name="srchFid" class="w_150px input_com" value="${usrInfoVO.fuid}"/>
			</div>
			<div class="comm_search mr_20">
				<div class="title">이름</div>
				<input type="text" id="srchFnm" name="srchFnm" class="w_150px input_com" value="${usrInfoVO.funm}"/>
			</div>
			<div class="comm_search mr_50">
				<div class="title">카드번호</div>
				<input type="text" id="srchCdno" name="srchCdno" class="w_150px input_com mr_40" value="${usrInfoVO.fcdno}"/>
				<div class="search_btn2" onclick="usrLogSearch()"></div>
			</div>
		</div>
	</div>
	<!--//검색박스 -->
	<div class="mb_10">
		<b class="mr_10">전체 : <c:out value="${pagination.totRecord}"/>건</b>
	</div>	
	<div class="com_box">
		<!--테이블 시작 -->
		<div class="tb_outbox">
			<table class="tb_list tb_write_p1" id="TableID1">
				<thead>
					<tr>
						<th>ID</th>
						<th>이름</th>
						<th>카드번호</th>
						<th>회사명</th>
						<th>부서</th>
						<th>직급</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userInfo}" var="userlnfo" varStatus="status">
					<tr>
						<td><c:out value="${userlnfo.fuid}"/></td>
						<td><a href="javascript:fnSend('${userlnfo.fuid}','${userlnfo.funm}')"><c:out value="${userlnfo.funm}"/></a></td>
						<td><c:out value="${userlnfo.cfcdno}"/></td>
						<td><c:out value="${userlnfo.fpartnm1}"/></td>
						<td><c:out value="${userlnfo.fpartnm2}"/></td>
						<td><c:out value="${userlnfo.fpartnm3}"/></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--------- //목록--------->
	<jsp:include page="/WEB-INF/jsp/cubox/common/pagination.jsp" flush="false" />
</div>
</form>