package cubox.admin.main.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("userDAO")
public class UserDAO extends EgovAbstractMapper {

	private static final String sqlNameSpace = "user.";

	public int checkPasswd(Map<String, Object> param) throws Exception {
		return (Integer)selectOne(sqlNameSpace+"checkPasswd", param);
	}
	
	public int updatePasswd(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace+"updatePasswd", param);
	}

	public Map<String, Object> selectUserInfo(Map<String, Object> param) throws Exception {
		return selectOne(sqlNameSpace+"selectUserInfo", param);
	}
	
	public List<Map<String, Object>> selectUserList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace+"selectUserList", param);
	}
	
	public int selectUserListCount(Map<String, Object> param) throws Exception {
		return (Integer)selectOne(sqlNameSpace+"selectUserListCount", param);
	}
	
	public int selectUserIdDplct(Map<String, Object> param) throws Exception {
		return (Integer)selectOne(sqlNameSpace+"selectUserIdDplct", param);
	}
	
	public int insertUserInfo(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace+"insertUserInfo", param);
	}
	
	public int updateUserInfo(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace+"updateUserInfo", param);
	}
	
	public int updateUserUseYn(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace+"updateUserUseYn", param);
	}
	
	public int updateUserPasswdReset(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace+"updateUserPasswdReset", param);
	}
}
