package cubox.admin.main.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cubox.admin.main.dao.AuthDAO;
import cubox.admin.main.service.AuthService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("authService")
public class AuthServiceImpl extends EgovAbstractServiceImpl implements AuthService {

	@Resource(name="authDAO")
	private AuthDAO authDAO;

	@Override
	public List<Map<String, Object>> selectAuthCombo() throws Exception {
		return authDAO.selectAuthCombo();
	}
	
	@Override
	public List<Map<String, Object>> selectAuthList(Map<String, Object> param) throws Exception {
		return authDAO.selectAuthList(param);
	}

	@Override
	public int selectAuthListCount(Map<String, Object> param) throws Exception {
		return authDAO.selectAuthListCount(param);
	}

	@Override
	public Map<String, Object> selectAuthInfo(Map<String, Object> param) throws Exception {
		return authDAO.selectAuthInfo(param);
	}	
	
	@Override
	public int insertAuthInfo(Map<String, Object> param) throws Exception {
		param.put("author_cd", authDAO.selectNewAuthCd());
		return authDAO.insertAuthInfo(param);
	}

	@Override
	public int updateAuthInfo(Map<String, Object> param) throws Exception {
		return authDAO.updateAuthInfo(param);
	}

	@Override
	public int updateAuthUseYn(Map<String, Object> param) throws Exception {
		return authDAO.updateAuthUseYn(param);
	}
	
}
