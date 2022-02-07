package cubox.admin.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cubox.admin.main.service.vo.AuthorVO;
import cubox.admin.main.service.vo.LoginVO;
import cubox.admin.main.service.vo.MenuClVO;
import cubox.admin.main.service.vo.MenuVO;

public interface CommonService {

	/**
	 * 로그인시 사용자 정보 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	LoginVO actionLogin(LoginVO vo) throws Exception;

	/**
	 * 마지막 접속 일시 update
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int lastConnect(LoginVO vo) throws Exception;
	
	/**
	 * 비밀번호 오류 횟수 update
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int failConnect(LoginVO vo) throws Exception;	
	
	public List<AuthorVO> selectAuthorList(HashMap<String, Object> map) throws Exception;
	public List<MenuClVO> selectMenuClListByAuthor(HashMap<String, Object> map) throws Exception;
	public List<MenuVO> selectAuthorMenuList(HashMap<String, Object> map) throws Exception;
		
	public List<Map<String, String>> selectMainTodayInCnt() throws Exception;
	public Map<String, String> selectMainPassRate() throws Exception;
	public List<Map<String, String>> selectMainStatInCnt() throws Exception;
	public List<Map<String, String>> selectMainInoutHist() throws Exception;
}
