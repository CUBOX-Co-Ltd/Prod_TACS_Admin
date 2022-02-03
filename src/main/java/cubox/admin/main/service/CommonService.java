package cubox.admin.main.service;

import java.util.List;
import java.util.Map;

import cubox.admin.main.service.vo.AuthorVO;
import cubox.admin.main.service.vo.CodeVO;
import cubox.admin.main.service.vo.DateTimeVO;
import cubox.admin.main.service.vo.FloorVO;
import cubox.admin.main.service.vo.LogInfoVO;
import cubox.admin.main.service.vo.LoginVO;
import cubox.admin.main.service.vo.MainStatusVO;
import cubox.admin.main.service.vo.SiteVO;

public interface CommonService {

	/**
	 * 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    LoginVO actionLogin(LoginVO vo) throws Exception;

    /**
	 * 로그인시 마지막 접속일 변경
	 * @param LoginVO
	 * @return
	 * @throws Exception
	 */
	int lastConnect(LoginVO vo) throws Exception;

	/**
	 * 코드가져오기
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<CodeVO> getCodeList(String fkind1, String fkind2) throws Exception;

	/**
	 * 코드가져오기
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<CodeVO> getCodeList2(String fkind1, String fkind2) throws Exception;

	/**
	 * 코드값가져오기 (fvalue)
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public String getCodeValue(String fkind1, String fkind2, String fkind3) throws Exception;

	/**
	 * 코드명가져오기 (fkind3)
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public String getCodeKind3(String fkind1, String fkind2, String fvalue) throws Exception;

	/**
	 * 센터콤보가져오기
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<SiteVO> getCenterCodeList(String siteId) throws Exception;

	/**
	 * sys로그저장
	 * @param SysLogVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public int sysLogSave(String fsiteid, String fsyscode, String fdetail, String fcnntip) throws Exception;

	/**
	 * tcsidx테이블 fidx추가
	 * @param String
	 * @return int
	 * @throws Exception
	 */
	public int tcsidxSave(String fcode) throws Exception;

	/**
	 * fsidx가져오기
	 * @param String
	 * @return String
	 * @throws Exception
	 */
	public String getFsidx(String fcode) throws Exception;

	/**
	 * 공통코드 full 가져오기
	 * @param String
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<CodeVO> getCodeFullList(CodeVO vo) throws Exception;

	/**
	 * 공통코드 fkind1 가져오기
	 * @param String
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<CodeVO> getCodeFkind1List() throws Exception;

	/**
	 * 공통코드 fkind2 가져오기
	 * @param String
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<CodeVO> getCodeFkind2List() throws Exception;
	
	public List<CodeVO> getCodeFkind2List2(CodeVO vo) throws Exception;

	/**
	 * 공통코드 fkind3 중복 체크
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	public int getFkind3Cnt(CodeVO vo) throws Exception;

	/**
	 * 공통코드 코드 등록
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	public int insertCode(CodeVO vo) throws Exception;

	/**
	 * 공통코드 코드 수정
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	public int updateCode(CodeVO vo) throws Exception;

	/**
	 * 공통코드 코드 사용 유무 수정
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	public int updateCodeUseYn(CodeVO vo) throws Exception;

	/**
	 * 공통코드 코드 fkind3 최대값 조회
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	public String getMaxCodeFkind3(CodeVO vo) throws Exception;

	/**
	 * 어제 오늘날짜, 현재시간가져오기
	 * @param
	 * @return DateTimeVO
	 * @throws Exception
	 */
	public DateTimeVO getDateTime() throws Exception;



	/**
	 *
	 * @param String
	 * @return List<AuthorVO>
	 * @throws Exception
	 */
	int updateGroupAuthUseYn(AuthorVO vo) throws Exception;

	/**
	 *
	 * @param String
	 * @return List<AuthorVO>
	 * @throws Exception
	 */
	int updateAuth(AuthorVO vo) throws Exception;



	/**
	 *
	 * @param String
	 * @return List<AuthorVO>
	 * @throws Exception
	 */
	int updateSiteUseYn(SiteVO vo) throws Exception;



	/**
	 *
	 * @param String
	 * @return List<AuthorVO>
	 * @throws Exception
	 */
	int updateSite(SiteVO vo) throws Exception;

	/**
	 *
	 * @param String, String
	 * @return int
	 * @throws Exception
	 */
	int sysMobileLogSave(String face_id, String face_img) throws Exception;

	/**
	 *
	 * @param int, String
	 * @return int
	 * @throws Exception
	 */
	int sysMobileLogUpdate(int rst, String string) throws Exception;

	/**
	 *
	 * @param
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	List<CodeVO> getAuthorList() throws Exception;

	/**
	 * 메인 화면 로그 목록
	 * @param
	 * @return List<LogInfoVO>
	 * @throws Exception
	 */
	List<LogInfoVO> getMainLogList(Map<String, String> map) throws Exception;

	/**
	 * 메인 화면 일일출입현황
	 * @param
	 * @return int
	 * @throws Exception
	 */
	int getMainLogTotCnt(Map<String, String> map) throws Exception;

	/**
	 * 메인 화면 일일출입인원
	 * @param
	 * @return int
	 * @throws Exception
	 */
	int getMainLogTotUserCnt() throws Exception;

	/**
	 * 메인 화면 방문객 신청 현황
	 * @param
	 * @return int
	 * @throws Exception
	 */
	int getMainVisitReqCnt(Map<String, String> map) throws Exception;

	/**
	 * 메인 화면 현황 그래프
	 * @param
	 * @return int
	 * @throws Exception
	 */
	List<MainStatusVO> selectMainLogGraph(Map<String, String> map) throws Exception;

	List<SiteVO> getSiteCodeList() throws Exception;

	
	public int getInmateCnt() throws Exception;

	/**
	 * 층 위치 가져오기
	 * @param String
	 * @return FloorVO
	 * @throws Exception
	 */
	List<FloorVO> getFloorList(String siteId) throws Exception;
}
