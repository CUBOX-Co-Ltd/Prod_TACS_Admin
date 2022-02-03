package cubox.admin.main.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cubox.admin.main.service.vo.AuthorVO;
import cubox.admin.main.service.vo.CodeVO;
import cubox.admin.main.service.vo.DateTimeVO;
import cubox.admin.main.service.vo.FloorVO;
import cubox.admin.main.service.vo.LogInfoVO;
import cubox.admin.main.service.vo.LoginVO;
import cubox.admin.main.service.vo.MainStatusVO;
import cubox.admin.main.service.vo.SiteVO;
import cubox.admin.main.service.vo.SysLogVO;
import cubox.admin.main.dao.CommonDAO;
import cubox.admin.main.service.CommonService;
import cubox.admin.cmmn.util.DataScrty;
import cubox.admin.cmmn.util.NumberUtil;
import cubox.admin.cmmn.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

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
		String fpasswd = DataScrty.encryptPassword(vo.getFpasswd(), vo.getFsiteid());
    	vo.setFpasswd(fpasswd);

    	// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
    	LoginVO loginVO = commonDAO.actionLogin(vo);

    	// 3. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getFsiteid().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}

    	return loginVO;
    }

    /**
   	 * 로그인시 마지막 접속일 변경
   	 * @param LoginVO
   	 * @return
   	 * @throws Exception
   	 */
    @Override
	public int lastConnect(LoginVO vo) throws Exception {
    	return commonDAO.lastConnect(vo);
    }

    /**
	 * 코드가져오기
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	@Override
	public List<CodeVO> getCodeList(String fkind1, String fkind2) throws Exception {
		CodeVO codeVO = new CodeVO();
		codeVO.setFkind1(fkind1);
		codeVO.setFkind2(fkind2);

		return commonDAO.getCodeList(codeVO);
	}

	/**
	 * 코드가져오기
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	@Override
	public List<CodeVO> getCodeList2(String fkind1, String fkind2) throws Exception {
		CodeVO codeVO = new CodeVO();
		codeVO.setFkind1(fkind1);
		codeVO.setFkind2(fkind2);

		return commonDAO.getCodeList2(codeVO);
	}

	/**
	 * 코드값가져오기 (fvalue)
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	@Override
	public String getCodeValue(String fkind1, String fkind2, String fkind3) throws Exception{

		CodeVO vo = new CodeVO();
		vo.setFkind1(fkind1);
		vo.setFkind2(fkind2);
		vo.setFkind3(fkind3);

		return commonDAO.getCodeValue(vo);

	}

	/**
	 * 코드명가져오기 (fkind3)
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	@Override
	public String getCodeKind3(String fkind1, String fkind2, String fvalue) throws Exception{

		CodeVO vo = new CodeVO();
		vo.setFkind1(fkind1);
		vo.setFkind2(fkind2);
		vo.setFvalue(fvalue);

		return commonDAO.getCodeKind3(vo);
	}

	/**
	 * 센터콤보가져오기
	 * @param CodeVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	@Override
	public List<SiteVO> getCenterCodeList(String siteId) throws Exception{

		SiteVO vo = new SiteVO();
		vo.setSiteId(siteId);

		return commonDAO.getCenterCodeList(vo);
	}

	/**
	 * sys로그저장
	 * @param SysLogVO
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	@Override
	public int sysLogSave(String fsiteid, String fsyscode, String fdetail, String fcnntip) throws Exception{

		String fdetail2 = "";
		String fdetail3 = "";

		if(!StringUtil.isEmpty(fdetail)){
			//System.out.println("sysLog.getFdetail() : " + fdetail);
			String[] fdetailArray = fdetail.replace("]","").trim().split(",");
			for(String array : fdetailArray){
				int arrayIndex = array.trim().indexOf("=");
				int arrayLength = array.trim().length()-1;
				//System.out.println("array index : " + arrayIndex);
				//System.out.println("array length : " + arrayLength);
				if(!array.contains("null") && arrayIndex != arrayLength){
					fdetail2 = fdetail2 + array.replace("[","|").replaceAll("(.*?)[|]", "").trim() + ",";
				}
			}
			fdetail3 = fdetail2.substring(0, fdetail2.length()-1);
			//System.out.println("fdetail2 : " + fdetail2);
		}

		SysLogVO vo = new SysLogVO();
		vo.setFsiteid(fsiteid);
		vo.setFsyscode(fsyscode);
		vo.setFdetail(fdetail3);
		vo.setFcnntip(fcnntip);

		return commonDAO.sysLogSave(vo);
	}

	/**
	 * tcsidx테이블 fidx추가
	 * @param
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int tcsidxSave(String fcode) throws Exception{
		return commonDAO.tcsidxSave(fcode);
	}

	/**
	 * fsidx가져오기
	 * @param
	 * @return int
	 * @throws Exception
	 */
	@Override
	public String getFsidx(String fcode) throws Exception{
		return commonDAO.getFsidx(fcode);
	}

	/**
	 * 공통코드 full list
	 * @param String
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	@Override
	public List<CodeVO> getCodeFullList(CodeVO vo) throws Exception {
		return commonDAO.getCodeFullList(vo);
	}

	/**
	 * 공통코드 fkind1 list
	 * @param String
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	@Override
	public List<CodeVO> getCodeFkind1List() throws Exception {
		return commonDAO.getCodeFkind1List();
	}

	/**
	 * 공통코드 fkind2 list
	 * @param String
	 * @return List<CodeVO>
	 * @throws Exception
	 */
	@Override
	public List<CodeVO> getCodeFkind2List() throws Exception {
		return commonDAO.getCodeFkind2List();
	}
	
	@Override
	public List<CodeVO> getCodeFkind2List2(CodeVO vo) throws Exception {
		return commonDAO.getCodeFkind2List2(vo);
	}

	/**
	 * 공통코드 fkind3 중복 체크
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getFkind3Cnt(CodeVO vo) throws Exception {
		return commonDAO.getFkind3Cnt(vo);
	}

	/**
	 * 공통코드 코드 등록
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int insertCode(CodeVO vo) throws Exception {
		return commonDAO.insertCode(vo);
	}

	/**
	 * 공통코드 코드 수정
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateCode(CodeVO vo) throws Exception {
		return commonDAO.updateCode(vo);
	}

	/**
	 * 공통코드 코드 사용유무 수정
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateCodeUseYn(CodeVO vo) throws Exception {
		return commonDAO.updateCodeUseYn(vo);
	}

	/**
	 * 공통코드 코드 fkind3 최대값 조회
	 * @param CodeVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public String getMaxCodeFkind3(CodeVO vo) throws Exception {
		return commonDAO.getMaxCodeFkind3(vo);
	}

	/**
	 * 어제 오늘날짜, 현재시간가져오기
	 * @param
	 * @return DateTimeVO
	 * @throws Exception
	 */
	@Override
	public DateTimeVO getDateTime() throws Exception{
		return commonDAO.getDateTime();
	}


	@Override
	public int updateGroupAuthUseYn(AuthorVO vo) throws Exception {
		return commonDAO.updateGroupAuthUseYn(vo);
	}

	@Override
	public int updateAuth(AuthorVO vo) throws Exception {
		return commonDAO.updateAuth(vo);
	}

	@Override
	public int updateSiteUseYn(SiteVO vo) throws Exception {
		return commonDAO.updateSiteUseYn(vo);
	}

	@Override
	public int updateSite(SiteVO vo) throws Exception {
		return commonDAO.updateSite(vo);
	}

	@Override
	public int sysMobileLogSave(String face_id, String face_img) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("face_id", face_id);
		map.put("face_img", face_img);
		int logRst = commonDAO.insertSysMobileLog (map);
		int rst = map.get("log_id")!=null?Integer.parseInt(map.get("log_id").toString()):0;
		return rst;
	}

	@Override
	public int sysMobileLogUpdate(int rst, String string) throws Exception {
		int saverst = 0;
		if(rst != 0) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("log_id", rst);
			map.put("download_yn", string);
			saverst = commonDAO.sysMobileLogUpdate (map);
		}
		return saverst;
	}

	@Override
	public List<CodeVO> getAuthorList() throws Exception {
		return commonDAO.selectAuthorList();
	}

	@Override
	public List<LogInfoVO> getMainLogList(Map<String, String> map) throws Exception {
		return commonDAO.selectMainLogList(map);
	}
	
	@Override
	public int getMainLogTotCnt(Map<String, String> map) throws Exception {
		return commonDAO.selectMainLogTotCnt(map);
	}

	@Override
	public int getMainLogTotUserCnt() throws Exception {
		return commonDAO.selectMainLogTotUserCnt();
	}

	@Override
	public int getMainVisitReqCnt(Map<String, String> map) throws Exception {
		return commonDAO.selectMainVisitReqCnt(map);
	}

	@Override
	public List<MainStatusVO> selectMainLogGraph(Map<String, String> map) throws Exception {
		return commonDAO.selectMainLogGraph(map);
	}

	@Override
	public List<SiteVO> getSiteCodeList() throws Exception {
		// TODO Auto-generated method stub
		return commonDAO.getSiteCodeList();
	}

	@Override
	public int getInmateCnt() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FloorVO> getFloorList(String siteId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
