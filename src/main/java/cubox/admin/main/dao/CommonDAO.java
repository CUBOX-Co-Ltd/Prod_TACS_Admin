package cubox.admin.main.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cubox.admin.main.service.vo.AuthorVO;
import cubox.admin.main.service.vo.CodeVO;
import cubox.admin.main.service.vo.DateTimeVO;
import cubox.admin.main.service.vo.FloorVO;
import cubox.admin.main.service.vo.LogInfoVO;
import cubox.admin.main.service.vo.LoginVO;
import cubox.admin.main.service.vo.MainStatusVO;
import cubox.admin.main.service.vo.SiteVO;
import cubox.admin.main.service.vo.SysLogVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("commonDAO")
public class CommonDAO extends EgovAbstractMapper {

	private static final String sqlNameSpace = "common.";

	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO actionLogin(LoginVO vo) throws Exception {
    	return (LoginVO)selectOne(sqlNameSpace + "actionLogin", vo);
    }

    /**
	 * actionLogin
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int lastConnect(LoginVO vo) throws Exception {
		return update(sqlNameSpace+"lastConnect", vo);
	}

	/**
	 * 코드가져오기
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<CodeVO> getCodeList(CodeVO vo) throws Exception {
        return selectList(sqlNameSpace+"getCodeList", vo);
    }

	/**
	 * 코드가져오기
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<CodeVO> getCodeList2(CodeVO vo) throws Exception {
        return selectList(sqlNameSpace+"getCodeList2", vo);
    }

	/**
	 * 코드값가져오기 (fvalue)
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public String getCodeValue(CodeVO vo) throws Exception{
		return selectOne(sqlNameSpace+"getCodeValue", vo);
	}

	/**
	 * 코드명가져오기 (fkind3)
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public String getCodeKind3(CodeVO vo) throws Exception{
		return selectOne(sqlNameSpace+"getCodeKind3", vo);
	}

	/**
	 * 센터콤보가져오기
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<SiteVO> getCenterCodeList(SiteVO vo) throws Exception {
        return selectList(sqlNameSpace+"getCenterCodeList", vo);
    }

	/**
	 * sys로그저장
	 * @param SysLogVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public int sysLogSave(SysLogVO vo) throws Exception{
		return insert(sqlNameSpace+"sysLogSave", vo);
	}

	/**
	 * tcsidx테이블 fidx추가
	 * @param
	 * @return int
	 * @throws Exception
	 */
	public int tcsidxSave(String fcode) throws Exception{
		return insert(sqlNameSpace+"tcsidxSave", fcode);
	}

	/**
	 * fsidx가져오기
	 * @param
	 * @return int
	 * @throws Exception
	 */
	public String getFsidx(String fcode) throws Exception{
		return selectOne(sqlNameSpace+"getFsidx", fcode);
	}

	/**
	 * 공통코드 full list
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<CodeVO> getCodeFullList(CodeVO vo) throws Exception {
		return selectList(sqlNameSpace+"getCodeFullList", vo);
	}

	/**
	 * 공통코드 fkind1 list
	 * @param String
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<CodeVO> getCodeFkind1List() throws Exception {
		return selectList(sqlNameSpace+"getCodeFkind1List");
	}

	/**
	 * 공통코드 fkind1 list
	 * @param String
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	public List<CodeVO> getCodeFkind2List() throws Exception {
		return selectList(sqlNameSpace+"getCodeFkind2List");
	}
	
	public List<CodeVO> getCodeFkind2List2(CodeVO vo) throws Exception {
		return selectList(sqlNameSpace+"getCodeFkind2List2", vo);
	}	

	/**
	 * 공통코드 fkind3 중복 체크
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	public int getFkind3Cnt(CodeVO vo) throws Exception {
		return selectOne(sqlNameSpace+"getFkind3Cnt", vo);
	}

	/**
	 * 공통코드 코드 등록
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	public int insertCode(CodeVO vo) throws Exception {
		return insert(sqlNameSpace+"insertCode", vo);
	}

	/**
	 * 공통코드 코드 수정
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	public int updateCode(CodeVO vo) throws Exception {
		return insert(sqlNameSpace+"updateCode", vo);
	}

	/**
	 * 공통코드 코드 사용유무 수정
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	public int updateCodeUseYn(CodeVO vo) throws Exception {
		return insert(sqlNameSpace+"updateCodeUseYn", vo);
	}

	/**
	 * 공통코드 코드 fkind3 최대값 조회
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	public String getMaxCodeFkind3(CodeVO vo) throws Exception {
		return selectOne(sqlNameSpace+"getMaxCodeFkind3", vo);
	}

	/**
	 * 어제 오늘날짜, 현재시간가져오기
	 * @param
	 * @return DateTimeVO
	 * @throws Exception
	 */
	public DateTimeVO getDateTime() throws Exception {
		return selectOne(sqlNameSpace+"getDateTime");
	}

	public List<AuthorVO> getGroupAuthList() {
		return selectList(sqlNameSpace+"getGroupAuthList");
	}

	public int updateGroupAuthUseYn(AuthorVO vo) throws Exception {
		return update(sqlNameSpace+"updateGroupAuthUseYn",vo);
	}

	public int updateAuth(AuthorVO vo) throws Exception {
		return update(sqlNameSpace+"updateAuth",vo);
	}

	public int updateSiteUseYn(SiteVO vo) throws Exception {
		return update(sqlNameSpace+"updateSiteUseYn",vo);
	}

	public int updateSite(SiteVO vo) throws Exception {
		return update(sqlNameSpace+"updateSite",vo);
	}

	public int insertSysMobileLog(HashMap<String, Object> map) throws Exception {
		return insert(sqlNameSpace+"insertSysMobileLog",map);
	}

	public int sysMobileLogUpdate(HashMap<String, Object> map) throws Exception {
		return update(sqlNameSpace+"sysMobileLogUpdate",map);
	}

	public List<CodeVO> selectAuthorList() throws Exception {
		return selectList(sqlNameSpace+"selectAuthorList");
	}

	public List<LogInfoVO> selectMainLogList(Map<String, String> map) throws Exception {
		return selectList(sqlNameSpace+"selectMainLogList", map);
	}

	public int selectMainLogTotCnt(Map<String, String> map) throws Exception {
		return selectOne(sqlNameSpace+"selectMainLogTotCnt", map);
	}

	public int selectMainLogTotUserCnt() throws Exception {
		Object obj = selectOne(sqlNameSpace+"selectMainLogTotUserCnt");
		if(obj != null) return (int) obj;
		else return 1;

	}

	public int selectMainVisitReqCnt(Map<String, String> map) throws Exception {
		return (Integer)selectOne(sqlNameSpace+"selectMainVisitReqCnt", map);
	}
	
	// 메인 출입이력 현황 그래프
	public List<MainStatusVO> selectMainLogGraph(Map<String, String> map) throws Exception {
		return selectList(sqlNameSpace+"selectMainLogGraph", map);
	}

	public List<SiteVO> getSiteCodeList() throws Exception {
		return selectList(sqlNameSpace+"getSiteCodeList");
	}



	/**
	 * 층 위치 가져오기
	 * @param String
	 * @return FloorVO
	 * @throws Exception
	 */
	public List<FloorVO> getFloorList(String siteId) {
		return selectList(sqlNameSpace+"getFloorList",siteId);
	}

}
