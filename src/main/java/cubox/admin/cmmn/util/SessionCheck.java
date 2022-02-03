package cubox.admin.cmmn.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cubox.admin.main.service.vo.AuthorVO;
import cubox.admin.main.service.vo.LoginVO;
import cubox.admin.menu.service.MenuService;
import cubox.admin.menu.vo.MenuClVO;
import cubox.admin.menu.vo.MenuDetailVO;

public class SessionCheck extends HandlerInterceptorAdapter {
	static final Logger log = LogManager.getLogger();

	AuthorManager authorManager = AuthorManager.getInstance();

	@Resource
    private MenuService menuService;


	/**
	 * 세션정보 체크
	 *
	 * @param request
	 * @return
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		//로그인없이 볼수 있는 페이지
 		ArrayList<String> freeAccessUrls = new ArrayList<String>();

		//로그인
		freeAccessUrls.add("/login.do");
		freeAccessUrls.add("/common/loginProc.do");


		//권한관계 없이 볼수 있는 페이지
		ArrayList<String> defaultAccessUrls = new ArrayList<String>();
		defaultAccessUrls.add("/main.do");

		String uri = request.getServletPath();

		//System.out.println("uri >>>> "+uri);

		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		//로그인
		if (loginVO != null && loginVO.getFsiteid() != null && !loginVO.getFsiteid().equals("")) {
			//권한 확인
			//if(!authorManager.is()) setAuthorInfo();

			//공통화면 체크
			if(defaultAccessUrls.contains(uri)) { 	//index.do
				request.getSession().setAttribute("uriPath", uri);
				return true;
			} else {
				if(uri != null && (uri.endsWith("login.do") || uri.endsWith("index.do"))) {
					//String strUrlPath = authorManager.getMainRedirect(loginVO.getAuthor_id());
					//request.getSession().setAttribute("uriPath", strUrlPath);
					//response.sendRedirect(strUrlPath);
					request.getSession().setAttribute("uriPath", "/main.do");
					response.sendRedirect("/main.do");
					return false;
				} else {
					request.getSession().setAttribute("uriPath", uri);
					return true;
				}
			}
		}else{
			if(freeAccessUrls.contains(uri)) {
				return true;
			} else if (uri.contains("Popup")) {
				PrintWriter writer = response.getWriter();
				writer.println("<script>parent.location.href='/login.do';</script>");	//Popup 부모창 로그인 이동
				return false;
			} else {
				response.sendRedirect("/login.do");
				return false;
			}
		}
	}


	/* public void setAuthorInfo() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("use_yn", "Y");
    	List<AuthorVO> authorList = menuService.getAuthorList(map);
    	for(AuthorVO avo : authorList) {
    		System.out.println("result >>>> "+avo.getAuthorId());
    		String authorId = avo.getAuthorId();

    		//권한별 대메뉴 정보
        	HashMap<String, Object> sMap = new HashMap<String, Object>();
        	sMap.put("author_id", authorId);
        	List<MenuClVO> urlList = menuService.getAuthorMenuCl(sMap);
        	List<MenuClVO> chkClList = new ArrayList<MenuClVO>();

        	//권한별 상세 url 전체 정보
        	sMap.put("menu_cl_code", "");
        	List<MenuDetailVO> menuDetailList = menuService.getAuthMenuList(sMap);
	    	authorManager.setDetailMenu(authorId, menuDetailList);

    		for(MenuClVO vo : urlList) {
    			String strClCode = vo.getMenu_cl_code();
    			//권한별 sub menu 정보
    			sMap.put("menu_cl_code", strClCode);
    			List<MenuDetailVO> menuList = menuService.getAuthMenuList(sMap);
    	    	//대메뉴 정보
    	    	vo.setList(menuList);
    	    	chkClList.add(vo);
    		}
    		authorManager.setMenuCl(authorId, chkClList);
    	}
		authorManager.complete();
	}*/
}
