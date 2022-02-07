package cubox.admin.main.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cubox.admin.main.dao.CodeDAO;
import cubox.admin.main.service.CodeService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("codeService")
public class CodeServiceImpl extends EgovAbstractServiceImpl implements CodeService {

	@Resource(name="codeDAO")
	private CodeDAO codeDAO;
	
	@Override
	public List<Map<String, Object>> selectCdCombo(String str) throws Exception {
		return codeDAO.selectCdCombo(str);
	}
	
	@Override
	public List<Map<String, Object>> selectCdList(Map<String, Object> param) throws Exception {
		return codeDAO.selectCdList(param);
	}


}
