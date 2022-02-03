package cubox.admin.menu.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import cubox.admin.main.service.vo.AuthorVO;
import cubox.admin.menu.vo.MenuClVO;
import cubox.admin.menu.vo.MenuDetailVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository
public class MenuDAO extends EgovAbstractMapper {

	public List<MenuClVO> selectAuthorMenuCl(HashMap<String, Object> map) throws Exception {
		return selectList ("admin.selectAuthorMenuCl", map);
	}

	public List<AuthorVO> selectAuthorList(HashMap<String, Object> map) throws Exception {
		return selectList ("admin.selectAuthorList", map);
	}

	public List<MenuDetailVO> selectAuthMenuList(HashMap<String, Object> map) throws Exception {
		return selectList ("menu.selectAuthMenuList", map);
	}
	public List<MenuDetailVO> selectMenuList(HashMap<String, Object> map) throws Exception {
		return selectList ("admin.selectMenuList", map);
	}
	public List<MenuDetailVO> selectUserMenuList(HashMap<String, Object> map) throws Exception {
		return selectList ("admin.selectUserMenuList", map);
	}

	public void insertAuthorMenu(HashMap pMap) throws Exception{
		insert( "admin.insertAuthorMenu", pMap );

	}
	public void deleteAuthorMenu(HashMap pMap)throws Exception {
		delete( "admin.deleteAuthorMenu", pMap );
	}
}
