package cubox.admin.main.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cubox.admin.cmmn.util.DataScrty;
import cubox.admin.cmmn.util.StringUtil;
import cubox.admin.main.dao.UserDAO;
import cubox.admin.main.service.UserService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("userService")
public class UserServiceImpl extends EgovAbstractServiceImpl implements UserService {

	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Override
	public int checkPasswd(Map<String, Object> param) throws Exception {
		String curPasswdEnc = DataScrty.encryptPassword(StringUtil.nvl(param.get("curPasswd")), StringUtil.nvl(param.get("userId")));
		param.put("curPasswdEnc", curPasswdEnc);

		return userDAO.checkPasswd(param);
	}

	@Override
	public int updatePasswd(Map<String, Object> param) throws Exception {
		String newPasswdEnc = DataScrty.encryptPassword(StringUtil.nvl(param.get("newPasswd")), StringUtil.nvl(param.get("userId")));
		param.put("newPasswdEnc", newPasswdEnc);

		return userDAO.updatePasswd(param);
	}

	@Override
	public Map<String, Object> selectUserInfo(Map<String, Object> param) throws Exception {
		return userDAO.selectUserInfo(param);
	}
	
	@Override
	public List<Map<String, Object>> selectUserList(Map<String, Object> param) throws Exception {
		return userDAO.selectUserList(param);
	}
	
	@Override
	public int selectUserListCount(Map<String, Object> param) throws Exception {
		return userDAO.selectUserListCount(param);
	}
	
	@Override
	public int selectUserIdDplct(Map<String, Object> param) throws Exception {
		return userDAO.selectUserIdDplct(param);
	}
	
	@Override
	public int insertUserInfo(Map<String, Object> param) throws Exception {
		return userDAO.insertUserInfo(param);
	}
	
	@Override
	public int updateUserInfo(Map<String, Object> param) throws Exception {
		return userDAO.updateUserInfo(param);
	}

	@Override
	public int updateUserUseYn(Map<String, Object> param) throws Exception {
		return userDAO.updateUserUseYn(param);
	}
	
	@Override
	public int updateUserPasswdReset(Map<String, Object> param) throws Exception {
		return userDAO.updateUserPasswdReset(param);
	}
}
