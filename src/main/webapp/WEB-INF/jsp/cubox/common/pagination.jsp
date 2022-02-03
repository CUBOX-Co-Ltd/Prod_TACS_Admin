<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="pagebox">
	<c:choose>
		<c:when test="${pagination.totPage ge 0}">
			<!-- 이전 페이지 -->
			<c:choose>
				<c:when test="${pagination.curPage ne 1 }">
					<span class="ar1" onclick="pageSearch('1');">
						<img src="/img/al_icon3.png" alt="" />
					</span>
				</c:when>
				<c:otherwise>
					<span class="ar1">
						<img src="/img/al_icon3.png" alt="" />
					</span>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${pagination.startPage ge pagination.unitPage }">
					<span class="ar" onclick="pageSearch('<c:out value="${pagination.curPage - pagination.unitPage < 0 ? 1 : pagination.curPage - pagination.unitPage }"/>');">
						<img src="/img/al_icon2.png" alt="" />
					</span>
				</c:when>
				<c:otherwise>
					<span class="ar">
						<img src="/img/al_icon2.png" alt="" />
					</span>
				</c:otherwise>
			</c:choose>

			<c:forEach begin="${pagination.startPage }" end="${pagination.endPage }" var="page">
				<c:choose>
					<c:when test="${pagination.curPage eq page }">
						<span class="um on"> ${page} </span>
					</c:when>
					<c:otherwise>
						<span class="um" onclick="pageSearch('<c:out value="${page}"/>');"> ${page} </span>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<!-- 다음 페이지 -->
			<c:choose>
				<c:when test="${pagination.endPage lt pagination.totPage }">
					<span class="ar">
						<img src="/img/ar_icon2.png" alt="" onclick="pageSearch('<c:out value="${pagination.endPage + 1 }"/>');"/>
					</span>
				</c:when>
				<c:otherwise>
					<span class="ar"><img src="/img/ar_icon2.png" alt="" /></span>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${pagination.curPage ne pagination.totPage }">
					<span class="ar1">
						<img src="/img/ar_icon3.png" alt="" onclick="pageSearch('<c:out value="${ pagination.totPage }"/>');"/>
					</span>
				</c:when>
				<c:otherwise>
					<span class="ar1">
						<img src="/img/ar_icon3.png" alt="" />
					</span>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<span class="ar1"><img src="/img/al_icon3.png" alt="" /></span>
			<span class="ar"><img src="/img/al_icon2.png" alt="" /></span>
			<span class="um"> 1 </span>
			<span class="ar"><img src="/img/ar_icon2.png" alt="" /></span>
			<span class="ar1"><img src="/img/ar_icon3.png" alt="" /></span>
		</c:otherwise>
	</c:choose>
</div>


