package cubox.admin.main.service;

import java.util.List;
import java.util.Map;

public interface MenuService {

	public String selectMenuNm(String str) throws Exception;
	public List<Map<String, Object>> selectMenuList(Map<String, Object> param) throws Exception;
	public int selectMenuListCount(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> selectMenuInfo(Map<String, Object> param) throws Exception;	
	public int insertMenuInfo(Map<String, Object> param) throws Exception;	
	public int updateMenuInfo(Map<String, Object> param) throws Exception;	
	public int updateMenuUseYn(Map<String, Object> param) throws Exception;	
	
	public List<Map<String, Object>> selectMenuClCombo() throws Exception;
	public List<Map<String, Object>> selectMenuClList(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> selectMenuClInfo(Map<String, Object> param) throws Exception;
	public int insertMenuClInfo(Map<String, Object> param) throws Exception;	
	public int updateMenuClInfo(Map<String, Object> param) throws Exception;	
	public int updateMenuClUseYn(Map<String, Object> param) throws Exception;
	
		

}
