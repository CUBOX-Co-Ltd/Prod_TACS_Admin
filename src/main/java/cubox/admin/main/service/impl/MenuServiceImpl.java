package cubox.admin.main.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cubox.admin.main.dao.MenuDAO;
import cubox.admin.main.service.MenuService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("menuService")
public class MenuServiceImpl extends EgovAbstractServiceImpl implements MenuService {

	@Resource(name="menuDAO")
	private MenuDAO menuDAO;
	
	@Override
	public String selectMenuNm(String str) throws Exception {
		return menuDAO.selectMenuNm(str);
	}

	@Override
	public List<Map<String, Object>> selectMenuList(Map<String, Object> param) throws Exception {
		return menuDAO.selectMenuList(param);
	}
	
	@Override
	public int selectMenuListCount(Map<String, Object> param) throws Exception {
		return menuDAO.selectMenuListCount(param);
	}
	
	@Override
	public Map<String, Object> selectMenuInfo(Map<String, Object> param) throws Exception {
		return menuDAO.selectMenuInfo(param);
	}	
	
	@Override
	public int insertMenuInfo(Map<String, Object> param) throws Exception {
		param.put("menu_cd", menuDAO.selectNewMenuCd());
		return menuDAO.insertMenuInfo(param);
	}

	@Override
	public int updateMenuInfo(Map<String, Object> param) throws Exception {
		return menuDAO.updateMenuInfo(param);
	}

	@Override
	public int updateMenuUseYn(Map<String, Object> param) throws Exception {
		return menuDAO.updateMenuUseYn(param);
	}

	@Override
	public List<Map<String, Object>> selectMenuClCombo() throws Exception {
		return menuDAO.selectMenuClCombo();
	}
	
	@Override
	public List<Map<String, Object>> selectMenuClList(Map<String, Object> param) throws Exception {
		return menuDAO.selectMenuClList(param);
	}
	
	@Override
	public Map<String, Object> selectMenuClInfo(Map<String, Object> param) throws Exception {
		return menuDAO.selectMenuClInfo(param);
	}
	
	@Override
	public int insertMenuClInfo(Map<String, Object> param) throws Exception {
		param.put("menu_cl_cd", menuDAO.selectNewMenuClCd());
		return menuDAO.insertMenuClInfo(param);
	}

	@Override
	public int updateMenuClInfo(Map<String, Object> param) throws Exception {
		return menuDAO.updateMenuClInfo(param);
	}

	@Override
	public int updateMenuClUseYn(Map<String, Object> param) throws Exception {
		return menuDAO.updateMenuClUseYn(param);
	}	

}
