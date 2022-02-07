package cubox.admin.main.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("authMenuDAO")
public class AuthMenuDAO extends EgovAbstractMapper {

	private static final String sqlNameSpace = "authMenu.";

	public List<Map<String, Object>> selectAuthMenuList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace+"selectAuthMenuList", param);
	}
	
	public List<Map<String, Object>> selectAuthMenuExclList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace+"selectAuthMenuExclList", param);
	}
	
	public int insertAuthMenu(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace+"insertAuthMenu", param);
	}
	
	public int deleteAuthMenu(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace+"deleteAuthMenu", param);
	}
	
}
