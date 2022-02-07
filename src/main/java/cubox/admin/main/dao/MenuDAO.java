package cubox.admin.main.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("menuDAO")
public class MenuDAO extends EgovAbstractMapper {

	private static final String sqlNameSpace = "menu.";

	public String selectMenuNm(String str) throws Exception {
		return (String)selectOne(sqlNameSpace+"selectMenuNm", str);
	}
	
	public List<Map<String, Object>> selectMenuList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace+"selectMenuList", param);
	}
	
	public int selectMenuListCount(Map<String, Object> param) throws Exception {
		return (Integer)selectOne(sqlNameSpace+"selectMenuListCount", param);
	}
	
	public Map<String, Object> selectMenuInfo(Map<String, Object> param) throws Exception {
		return selectOne(sqlNameSpace+"selectMenuInfo", param);
	}
	
	public String selectNewMenuCd() throws Exception {
		return (String)selectOne(sqlNameSpace+"selectNewMenuCd");
	}
	
	public int insertMenuInfo(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace+"insertMenuInfo", param);
	}
	
	public int updateMenuInfo(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace+"updateMenuInfo", param);
	}
	
	public int updateMenuUseYn(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace+"updateMenuUseYn", param);
	}
	
	public List<Map<String, Object>> selectMenuClCombo() throws Exception {
		return selectList(sqlNameSpace+"selectMenuClCombo");
	}
	
	public List<Map<String, Object>> selectMenuClList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace+"selectMenuClList", param);
	}
	
	public Map<String, Object> selectMenuClInfo(Map<String, Object> param) throws Exception {
		return selectOne(sqlNameSpace+"selectMenuClInfo", param);
	}
	
	public String selectNewMenuClCd() throws Exception {
		return (String)selectOne(sqlNameSpace+"selectNewMenuClCd");
	}
	
	public int insertMenuClInfo(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace+"insertMenuClInfo", param);
	}
	
	public int updateMenuClInfo(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace+"updateMenuClInfo", param);
	}
	
	public int updateMenuClUseYn(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace+"updateMenuClUseYn", param);
	}	
	
}
