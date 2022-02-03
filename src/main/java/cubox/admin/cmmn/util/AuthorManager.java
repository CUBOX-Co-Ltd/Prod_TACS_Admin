package cubox.admin.cmmn.util;

import java.util.*;

import cubox.admin.menu.vo.MenuClVO;
import cubox.admin.menu.vo.MenuDetailVO;

public class AuthorManager{

	private static AuthorManager authorManager = null;
	private static LinkedHashMap<String, List<MenuDetailVO>> MENU_INFO = new LinkedHashMap<String, List<MenuDetailVO>>();
	private static LinkedHashMap<String, List<MenuClVO>> MENU_CL_INFO = new LinkedHashMap<String, List<MenuClVO>>();
	private static LinkedHashMap<String, List<MenuDetailVO>> MENU_DETAIL_INFO = new LinkedHashMap<String, List<MenuDetailVO>>();
	private static boolean AUTHOR_AT = false;

	public static synchronized AuthorManager getInstance(){
		if(authorManager == null){
			authorManager = new AuthorManager();
		}
		return authorManager;
	}

//	public synchronized List<MenuClVO> getMenuCl(String author){
//		return MENU_CL_INFO.get(author);
//	}
//
//	public synchronized void setMenuCl(String author, List<MenuClVO> vo){
//		MENU_CL_INFO.put(author, vo);
//	}
//
//
//	public synchronized List<MenuDetailVO> getMenu(String author, String menuClCode){
//		return MENU_INFO.get(author+"_"+menuClCode);
//	}
//
//	public synchronized void setMenu(String author, String menuClCode, List<MenuDetailVO> menuList){
//		MENU_INFO.put(author+"_"+menuClCode, menuList);
//	}
//
//	public synchronized void complete() {
//		AUTHOR_AT = true;
//	}

//	public synchronized boolean is() {
//		return AUTHOR_AT;
//	}

	public synchronized void clear() {
		MENU_INFO = new LinkedHashMap<String, List<MenuDetailVO>>();
		MENU_CL_INFO = new LinkedHashMap<String, List<MenuClVO>>();
		AUTHOR_AT = false;
	}

	

	
}
