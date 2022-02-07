package cubox.admin.main.service;

import java.util.List;
import java.util.Map;

public interface AuthService {

	public List<Map<String, Object>> selectAuthCombo() throws Exception;
	public List<Map<String, Object>> selectAuthList(Map<String, Object> param) throws Exception;
	public int selectAuthListCount(Map<String, Object> param) throws Exception;

	public Map<String, Object> selectAuthInfo(Map<String, Object> param) throws Exception;	
	public int insertAuthInfo(Map<String, Object> param) throws Exception;	
	public int updateAuthInfo(Map<String, Object> param) throws Exception;	
	public int updateAuthUseYn(Map<String, Object> param) throws Exception;	

}
