package cubox.admin.main.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("codeDAO")
public class CodeDAO extends EgovAbstractMapper {

	private static final String sqlNameSpace = "code.";

	public List<Map<String, Object>> selectCdCombo(String str) throws Exception {
		return selectList(sqlNameSpace+"selectCdCombo", str);
	}
	
	public List<Map<String, Object>> selectCdList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace+"selectCdList", param);
	}	

}
