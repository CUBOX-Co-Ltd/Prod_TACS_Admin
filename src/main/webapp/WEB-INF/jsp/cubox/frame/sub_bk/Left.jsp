<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 			uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" 	uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 			uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="cubox.admin.main.service.vo.LoginVO" %>
<%@ page import="cubox.admin.cmmn.util.AuthorManager" %>
<%@ page import="cubox.admin.menu.vo.MenuClVO" %>
<%@ page import="cubox.admin.menu.vo.MenuDetailVO" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	AuthorManager authorManager = AuthorManager.getInstance();
	LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
	String authorId = loginVO.getAuthor_id ();

	//대메뉴 조회
	List<MenuClVO> menuClList = authorManager.getMenuCl (authorId);
	pageContext.setAttribute("menuClList", menuClList);
%>
<c:set var="currentURI" value="${requestScope['javax.servlet.forward.servlet_path']}" />
<ul id="js-nav-menu" class="nav-menu">
	<!-- 메뉴 자동 -->
	<c:forEach var="result" items="${menuClList}" varStatus="status">
		<li>
			<a href="#">
				<i class="fa fa-folder"></i>
				<span class="nav-link-text">${result.menu_cl_nm}</span>
			</a>
			<c:if test="${result.list != null && fn:length(result.list) > 0 }">
				<ul>
					<c:forEach var="menuList" items="${result.list}" varStatus="dStatus">
						<li class="li-a"><a href="${menuList.menu_url}">${menuList.menu_nm}</a></li>
					</c:forEach>
				</ul>
			</c:if>
		</li>
	</c:forEach>
</ul>
<div class="filter-message js-filter-message bg-success-600"></div>
<script type="text/javascript">
	var currentURI = '<c:out value="${currentURI}"/>';
	$('.li-a > a').each(function(i) {
		var strHref = $('.li-a > a').eq(i).attr('href');
		if (strHref != null && $.trim(strHref) != "" && currentURI.indexOf(strHref) > -1) {
			$('.li-a > a').eq(i).parent().addClass('active');
			$('.li-a > a').eq(i).parent().parent().parent().addClass('active open');
		}
	});
</script>