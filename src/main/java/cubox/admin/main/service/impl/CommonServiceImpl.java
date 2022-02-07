package cubox.admin.main.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cubox.admin.cmmn.util.DataScrty;
import cubox.admin.cmmn.util.StringUtil;
import cubox.admin.main.dao.CommonDAO;
import cubox.admin.main.service.CommonService;
import cubox.admin.main.service.vo.AuthorVO;
import cubox.admin.main.service.vo.LoginVO;
import cubox.admin.main.service.vo.MenuClVO;
import cubox.admin.main.service.vo.MenuVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("commonService")
public class CommonServiceImpl extends EgovAbstractServiceImpl implements CommonService {

	@Resource(name="commonDAO")
	private CommonDAO commonDAO;

	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	@Override
	public LoginVO actionLogin(LoginVO vo) throws Exception {

		// 1. 입력한 비밀번호를 암호화한다.
		String fpasswd = DataScrty.encryptPassword(vo.getUserPw(), vo.getUserId());
		vo.setUserPw(fpasswd);

		// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
		LoginVO loginVO = commonDAO.actionLogin(vo);

		// 3. 결과를 리턴한다.
		if(loginVO != null && !StringUtil.nvl(loginVO.getUserId()).equals("")) {
			return loginVO;
		} else {
			loginVO = new LoginVO();
		}

		return loginVO;
	}

	@Override
	public int lastConnect(LoginVO vo) throws Exception {
		return commonDAO.lastConnect(vo);
	}
	
	@Override
	public int failConnect(LoginVO vo) throws Exception {
		return commonDAO.failConnect(vo);
	}

	@Override
	public List<AuthorVO> selectAuthorList(HashMap<String, Object> map) throws Exception {
		return commonDAO.selectAuthorList(map);
	}
	
	@Override
	public List<MenuClVO> selectMenuClListByAuthor(HashMap<String, Object> map) throws Exception {
		return commonDAO.selectMenuClListByAuthor(map);
	}	
	
	@Override
	public List<MenuVO> selectAuthorMenuList(HashMap<String, Object> map) throws Exception {
		return commonDAO.selectAuthorMenuList(map);
	}
	
	@Override
	public List<Map<String, String>> selectMainTodayInCnt() throws Exception {
		return commonDAO.selectMainTodayInCnt();
	}
	
	@Override
	public Map<String, String> selectMainPassRate() throws Exception {
		return commonDAO.selectMainPassRate();
	}
		
	@Override
	public List<Map<String, String>> selectMainStatInCnt() throws Exception {
		return commonDAO.selectMainStatInCnt();
	}	
	
	@Override
	public List<Map<String, String>> selectMainInoutHist() throws Exception {
		return commonDAO.selectMainInoutHist();
	}
	
}
