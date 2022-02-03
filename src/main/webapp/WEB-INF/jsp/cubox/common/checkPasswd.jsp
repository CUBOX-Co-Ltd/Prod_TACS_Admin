<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="currentPath" value="${requestScope['javax.servlet.forward.servlet_path']}" />
<c:if test="${not empty sessionScope.loginVO.fsiteid and fn:trim(currentPath) ne '/basicInfo/pwChange.do'}">
 <c:choose>
	<c:when test="${sessionScope.loginVO.fdatediff > 90}">
		<script type="text/javascript">
			alert("비밀번호를 변경하신시 90일이 경과되었습니다.\n비밀번호를 변경하셔야 사이트를 이용할수 있습니다.");
			location.href = "/basicInfo/pwChange.do";
		</script>
	</c:when>
	<c:when test="${sessionScope.loginVO.fpasswdyn == 'N'}">
		<script type="text/javascript">
			alert("비밀번호를 변경하셔야 사이트를 이용할수 있습니다.");
			location.href = "/basicInfo/pwChange.do";
		</script>
	</c:when>
	<c:otherwise/>
</c:choose>
</c:if>