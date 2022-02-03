<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/jsp/cubox/common/checkPasswd.jsp" flush="false"/>
<script type="text/javascript">
	$(function() {
		$(".title_tx").html("시스템 정보");
	});
</script>
<!--//검색박스 -->
<!--------- 목록--------->
<div class="com_box ">
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
