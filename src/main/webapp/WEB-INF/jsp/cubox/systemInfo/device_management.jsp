<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">
$(function() {
	$(".title_tx").html("등록현황");
	


});


</script>
<form id="frmSearch" name="frmSearch" method="post" onsubmit="return false;">
	<div class="search_box mb_20">
		<div class="search_in_bline">
			<div class="comm_search  mr_5">
				<label for="search-from-date" class="title">등록일</label>
				<input type="text" class="input_datepicker w_200px fl" name="srchStartDate" id="startDatetimepicker" placeholder="날짜">
				<div class="sp_tx fl">~</div>
				<label for="search-to-date"></label>
				<input type="text" class="input_datepicker w_200px fl" name="srchExpireDate" id="endDatetimepicker" placeholder="날짜">
			</div>
			
			<div class="comm_search ml_60">
				<div class="search_btn2" title="검색" onclick="userSearch()"></div>
			</div>
			<div class="comm_search ml_65">
				<button type="button" class="comm_btn" id="reset">초기화</button>
			</div>
		</div>
		
		
	</div>
	<div class="totalbox">
		<div class="txbox">
			<b class="fl mr_10">전체 : <c:out value="${pagination.totRecord}"/>건</b>
			<!-- 건수 -->
	          	<select name="srchRecPerPage" id="srchRecPerPage" class="input_com w_80px">
	              	<option value="30"  <c:if test="${userInfoVO.srchCnt eq '30'}">selected</c:if>>30</option>
	                	<option value="50"  <c:if test="${userInfoVO.srchCnt eq '50'}">selected</c:if>>50</option>
	                	<option value="100"  <c:if test="${userInfoVO.srchCnt eq '100'}">selected</c:if>>100</option>
	                	<option value="300"  <c:if test="${userInfoVO.srchCnt eq '300'}">selected</c:if>>300</option>
	                	<option value="500"  <c:if test="${userInfoVO.srchCnt eq '500'}">selected</c:if>>500</option>
	                	<option value="1000"  <c:if test="${userInfoVO.srchCnt eq '1000'}">selected</c:if>>1000</option>
	                	<option value="1500" <c:if test="${userInfoVO.srchCnt eq '1500'}">selected</c:if>>1500</option>
	                	<option value="2000" <c:if test="${userInfoVO.srchCnt eq '2000'}">selected</c:if>>2000</option>
	                	<option value="2500" <c:if test="${userInfoVO.srchCnt eq '2500'}">selected</c:if>>2500</option>
	                	<option value="3000" <c:if test="${userInfoVO.srchCnt eq '3000'}">selected</c:if>>3000</option>
	          	</select>
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
					<th>UUID</th>
					<th>이미지</th>
					<th>등록일</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
	</div>
	<!--------- //목록--------->
	<!-- 페이징 -->
	<jsp:include page="/WEB-INF/jsp/cubox/common/pagination.jsp" flush="false"/>
	<!-- /페이징 -->
</div>
