<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 			uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" 	uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 			uri="http://java.sun.com/jsp/jstl/functions" %>
<nav>
	<ul class="pagination justify-content-center pagination-sm">
		<!--a class="prev">이전페이지</a-->
		<c:if test="${pagination.startPage ge pagination.unitPage }">
		<li class="page-item"><a class="page-link" href='#' onclick="pageSearch('<c:out value="${pagination.curPage - pagination.unitPage < 0 ? 1 : pagination.curPage - pagination.unitPage }"/>');" aria-label="Previous"><span aria-hidden="true"><i class="fa fa-angle-left"></i></span></a></li>
		</c:if>
		<c:if test="${pagination.startPage lt pagination.unitPage }">
		<li class="page-item"><a class="page-link" href="#none" aria-label="Previous"><span aria-hidden="true"><i class="fa fa-angle-left"></i></span></a></li>
		</c:if>

		<c:forEach begin="${pagination.startPage }" end="${pagination.endPage }" var="page">
		<c:if test="${pagination.curPage eq page }">
		<li class="page-item active"><span><c:out value="${page}"/></span></li>
		</c:if>
		<c:if test="${pagination.curPage ne page }">
		<li class="page-item"><a class="page-link" href='#' onclick="pageSearch('<c:out value="${page}"/>');"><c:out value="${page}"/></a></li>
		</c:if>
		</c:forEach>

		<!--a class="next on" href="#">다음페이지</a-->
		<c:if test="${pagination.endPage lt pagination.totPage }">
		<li class="page-item"><a class="page-link" href='#' onclick="pageSearch('<c:out value="${pagination.endPage + 1 }"/>');" aria-label="Next"><span aria-hidden="true"><i class="fa fa-angle-right"></i></span></a></li>
		</c:if>
		<c:if test="${pagination.endPage ge pagination.totPage }">
		<li class="page-item"><a class="page-link" href="#none" aria-label="Next"><span aria-hidden="true"><i class="fa fa-angle-right"></i></span></a></li>
		</c:if>
	</ul>
</nav>