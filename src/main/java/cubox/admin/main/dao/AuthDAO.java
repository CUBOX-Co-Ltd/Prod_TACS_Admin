package cubox.admin.main.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("authDAO")
public class AuthDAO extends EgovAbstractMapper {

	private static final String sqlNameSpace = "auth.";

	public List<Map<String, Object>> selectAuthCombo() throws Exception {
		return selectList(sqlNameSpace+"selectAuthCombo");
	}
	
	public List<Map<String, Object>> selectAuthList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace+"selectAuthList", param);
	}	
	
	public int selectAuthListCount(Map<String, Object> param) throws Exception {
		return selectOne(sqlNameSpace+"selectAuthListCount", param);
	}
	
	public Map<String, Object> selectAuthInfo(Map<String, Object> param) throws Exception {
		return selectOne(sqlNameSpace+"selectAuthInfo", param);
	}
	
	public String selectNewAuthCd() throws Exception {
		return (String)selectOne(sqlNameSpace+"selectNewAuthCd");
	}
	
	public int insertAuthInfo(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace+"insertAuthInfo", param);
	}
	
	public int updateAuthInfo(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace+"updateAuthInfo", param);
	}
	
	public int updateAuthUseYn(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace+"updateAuthUseYn", param);
	}

	
}
