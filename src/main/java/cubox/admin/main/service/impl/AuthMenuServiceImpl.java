package cubox.admin.main.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cubox.admin.main.controller.AuthMenuController;
import cubox.admin.main.dao.AuthMenuDAO;
import cubox.admin.main.service.AuthMenuService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("authMenuService")
public class AuthMenuServiceImpl extends EgovAbstractServiceImpl implements AuthMenuService {

	@Resource(name="authMenuDAO")
	private AuthMenuDAO authMenuDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthMenuService.class);

	@Override
	public List<Map<String, Object>> selectAuthMenuList(Map<String, Object> param) throws Exception {
		return authMenuDAO.selectAuthMenuList(param);
	}

	@Override
	public List<Map<String, Object>> selectAuthMenuExclList(Map<String, Object> param) throws Exception {
		return authMenuDAO.selectAuthMenuExclList(param);
	}

	@Override
	public int saveAuthMenu(Map<String, Object> param) throws Exception {
		
		int cnt1 = authMenuDAO.deleteAuthMenu(param);
		int cnt2 = authMenuDAO.insertAuthMenu(param);
		
		LOGGER.debug("###[saveAuthMenu] delete cnt : {}", cnt1);
		LOGGER.debug("###[saveAuthMenu] insert cnt : {}", cnt2);

		return cnt2;
	}


}
