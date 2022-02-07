package cubox.admin.main.service;

import java.util.Map;
import java.util.List;

public interface UserService {

	public int checkPasswd(Map<String, Object> param) throws Exception;
	public int updatePasswd(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> selectUserInfo(Map<String, Object> param) throws Exception;
	public List<Map<String, Object>> selectUserList(Map<String, Object> param) throws Exception;
	public int selectUserListCount(Map<String, Object> param) throws Exception;
	
	public int selectUserIdDplct(Map<String, Object> param) throws Exception;

	public int insertUserInfo(Map<String, Object> param) throws Exception;
	public int updateUserInfo(Map<String, Object> param) throws Exception;
	public int updateUserUseYn(Map<String, Object> param) throws Exception;
	public int updateUserPasswdReset(Map<String, Object> param) throws Exception;
}
