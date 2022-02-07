package cubox.admin.main.service;

import java.util.List;
import java.util.Map;

public interface AuthMenuService {

	public List<Map<String, Object>> selectAuthMenuList(Map<String, Object> param) throws Exception;
	public List<Map<String, Object>> selectAuthMenuExclList(Map<String, Object> param) throws Exception;
	public int saveAuthMenu(Map<String, Object> param) throws Exception;

	
}
