package cubox.admin.menu.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cubox.admin.main.service.vo.AuthorVO;
import cubox.admin.menu.service.MenuService;
import cubox.admin.menu.vo.MenuClVO;
import cubox.admin.menu.vo.MenuDetailVO;

@Service("MenuService")
public class MenuServiceImpl implements MenuService {

	@Resource(name = "menuDAO")
	private MenuDAO menuDAO;

	@Override
	public List<MenuClVO> getAuthorMenuCl(HashMap<String, Object> map) throws Exception {
		return menuDAO.selectAuthorMenuCl(map);
	}

	@Override
	public List<AuthorVO> getAuthorList(HashMap<String, Object> map) throws Exception {
		return menuDAO.selectAuthorList(map);
	}

	@Override
	public List<MenuDetailVO> getAuthMenuList(HashMap<String, Object> map) throws Exception {
		return menuDAO.selectAuthMenuList(map);
	}
	@Override
	public List<MenuDetailVO> getMenuList(HashMap<String, Object> map) throws Exception {
		return menuDAO.selectMenuList(map);
	}

	@Override
	public List<MenuDetailVO> getTotalMenuList( HashMap map ) throws Exception {
		return menuDAO.selectMenuList(map);
	}

	@Override
	public List<MenuDetailVO> getUserMenuList( HashMap map ) throws Exception {
		return menuDAO.selectUserMenuList(map);
	}

	@Override
	public void saveAuthMenuGroup(HashMap pMap) throws Exception {
		String[] menu = (String[]) pMap.get("menuArray");

		System.out.println(menu.length);

		menuDAO.deleteAuthorMenu(pMap);

		for(int i=0; i< menu.length; i++) {
			pMap.put("menu_code", menu[i].toString());
			menuDAO.insertAuthorMenu(pMap);
		}
	}
}
