<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="cubox.admin.main.service.vo.LoginVO" %>
<%@ page import="cubox.admin.cmmn.util.AuthorManager" %>
<%@ page import="cubox.admin.menu.vo.MenuClVO" %>
<%-- <%@ page import="cubox.admin.menu.vo.MenuDetailVO" %> --%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일");
%>
<%-- 
	AuthorManager authorManager = AuthorManager.getInstance();
	LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
	String authorId = "";
	if(loginVO!= null) {
		authorId = loginVO.getAuthor_id ();
	}

	//대메뉴 조회
	List<MenuClVO> menuClList = null;
	if(authorManager != null) {
		menuClList = authorManager.getMenuCl (authorId);
	}
	pageContext.setAttribute("menuClList", menuClList);

	//String strUriPath = (String) session.getAttribute("uriPath");
--%>
<body>
	<!--상단영역 공통  -->
	<header class="main">
		<div class="logo" style="width: 250px;">
			<a href="/main.do">
				<img id="topLeftLogo" src="/img/logo/logo_<spring:eval expression="@property['Globals.site.main.id']" />.png" alt="" style="max-width: 170px; <c:if test="${sessionScope.loginVO.author_id ne '00008'}">display: none;</c:if>"/>
			</a>
		</div>
		<div class="title_box">
			<div class="title_time" style="margin-top: 24px;"><em style="font-size: 16px;color: #000;font-weight: 700; font-family: 'Noto Sans KR', sans-serif; margin-right: 5px; line-height: 35px;"><%= sf.format(nowTime) %></em> Recent Updates </div>
		</div>
		<div class="search_topbox" style="display: none;">
			<div class="input_box">
				<div class="line"></div>
				<input type="text" class="input_stin" placeholder="search">
				<button type="button" class="search_btn"></button>
			</div>
		</div>
		<div class="login_in_iconbox">
			
			<button type="button" class="logout" onclick="fnLogout();">로그아웃</button>
		</div>
	</header>
	<!--//상단영역 공통  -->
	<input type="checkbox" id="menu_state" checked>
	<nav>
		<label for="menu_state"><i class="fa"></i></label>
		<div class="left_title">
			<a href="/main.do">
				<img src="/img/logo/logo_<spring:eval expression="@property['Globals.site.main.id']" />_w.png" alt="" style="max-width: 170px;"/>
			</a>
		</div>

		<ul class="nav" id="demo1">
			<li>
				<a>지역 관리</a>
					<ul class="menu2">
						<li class="li-a"><a href="/systemInfo/zoneMngmt.do">Zone 관리</a></li>
						<li class="li-a"><a href="/systemInfo/spotMngmt.do">Spot 관리</a></li>
					</ul>
			</li>
			<li>
				<a>기기 관리</a>
					<ul class="menu2">
						<li class="li-a"><a href="/systemInfo/deviceMngmt.do">등록 현황</a></li>
						<li class="li-a"><a href="/systemInfo/spotMngmt.do">이동 현황</a></li>
					</ul>
			</li>
		</ul> 
		
		<div style="position: absolute; left: 10; bottom: 0; "></div>
	</nav>

	<script type="text/javascript">
		function fnLogout () {
			location.href = "/logout.do";
		}
		function fnPassChange () {
			location.href = "/basicInfo/pwChange.do";
		}
		$(function() {
			$(".nav > li").removeClass("open");
			$(".menu2").hide();

			$(".fa").click(function(){
				if($("#topLeftLogo").css("display") == "none") {
					$("#topLeftLogo").show();
				} else {
					$("#topLeftLogo").hide();
				}
			});

			var currentURI = "${sessionScope.uriPath}";
			$('.li-a > a').each(function(i) {
				var strHref = $('.li-a > a').eq(i).attr('href');
				if (strHref != null && $.trim(strHref) != "" && currentURI.indexOf(strHref) > -1) {
					$('.li-a > a').eq(i).parent().parent().parent().addClass('open');
					$('.li-a > a').eq(i).parent().parent().show();
				}
			});
		});
	</script>

</body>