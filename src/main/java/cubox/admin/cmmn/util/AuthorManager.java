package cubox.admin.cmmn.util;

import java.util.*;

import cubox.admin.main.service.vo.MenuClVO;
import cubox.admin.main.service.vo.MenuVO;

public class AuthorManager{

	private static AuthorManager authorManager = null;
	private static LinkedHashMap<String, List<MenuVO>> MENU_INFO = new LinkedHashMap<String, List<MenuVO>>();
	private static LinkedHashMap<String, List<MenuClVO>> MENU_CL_INFO = new LinkedHashMap<String, List<MenuClVO>>();
	private static LinkedHashMap<String, List<MenuVO>> MENU_DETAIL_INFO = new LinkedHashMap<String, List<MenuVO>>();
	private static boolean AUTHOR_AT = false;

	public static synchronized AuthorManager getInstance(){
		if(authorManager == null){
			authorManager = new AuthorManager();
		}
		return authorManager;
	}

	public synchronized List<MenuClVO> getMenuCl(String author){
		return MENU_CL_INFO.get(author);
	}

	public synchronized void setMenuCl(String author, List<MenuClVO> vo){
		MENU_CL_INFO.put(author, vo);
	}


	public synchronized List<MenuVO> getMenu(String author, String menuClCd){
		return MENU_INFO.get(author+"_"+menuClCd);
	}

	public synchronized void setMenu(String author, String menuClCd, List<MenuVO> menuList){
		MENU_INFO.put(author+"_"+menuClCd, menuList);
	}

	public synchronized void complete() {
		AUTHOR_AT = true;
	}

	public synchronized boolean is() {
		return AUTHOR_AT;
	}

	public synchronized void clear() {
		MENU_INFO = new LinkedHashMap<String, List<MenuVO>>();
		MENU_CL_INFO = new LinkedHashMap<String, List<MenuClVO>>();
		AUTHOR_AT = false;
	}

	public void setDetailMenu(String authorCd, List<MenuVO> menuList) {
		MENU_DETAIL_INFO.put(authorCd, menuList);
	}

	public List<MenuVO> getDetailMenu(String authorCd) {
		return MENU_DETAIL_INFO.get(authorCd);
	}

	/*
	public String getMainRedirect(String author) {
		String strMain = "/userInfo/userMngmt.do";
		String returnUrl = "";
		int i = 0;
		List<MenuDetailVO> dlist = getDetailMenu (author);
		if(dlist != null && dlist.size() > 0) {
			for(MenuDetailVO mv : dlist) {
				if(mv.getMenu_url()!=null) {
					if(mv.getMenu_url().endsWith(strMain)) {
						returnUrl = strMain;
						break;
					}
					if(i==0) {
						returnUrl = mv.getMenu_url();
						i++;
					}
				}
			}
		}
		if(returnUrl == null || returnUrl.trim().equals("")) {
			returnUrl = "/login.do";
		}
		return returnUrl;
	}*/
}
