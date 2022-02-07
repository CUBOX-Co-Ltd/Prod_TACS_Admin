package cubox.admin.main.service;

import java.util.Map;
import java.util.List;

public interface CodeService {

	public List<Map<String, Object>> selectCdCombo(String str) throws Exception;
	public List<Map<String, Object>> selectCdList(Map<String, Object> param) throws Exception;

}
