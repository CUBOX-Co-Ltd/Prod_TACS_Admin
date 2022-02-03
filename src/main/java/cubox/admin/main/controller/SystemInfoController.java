package cubox.admin.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import cubox.admin.cmmn.util.ApiUtil;
import cubox.admin.cmmn.util.CommonUtils;
import cubox.admin.cmmn.util.CuboxProperties;
import cubox.admin.cmmn.util.StringUtil;
import cubox.admin.main.service.CommonService;
import cubox.admin.main.service.vo.PaginationVO;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class SystemInfoController {

	/** memberService */

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/** commonService */
	@Resource(name = "commonService")
	private CommonService commonService;

	@Resource(name = "commonUtils")
	protected CommonUtils commonUtils;

	private static String GLOBAL_API_IP = CuboxProperties.getProperty("Globals.api.ip");
	private static String GLOBAL_API_PORT = CuboxProperties.getProperty("Globals.api.port");
	
	private int srchCnt     = 10; //조회할 페이지 수
	private int curPageUnit = 10; //한번에 표시할 페이지 번호 개수

	
	
	@RequestMapping(value="/systemInfo/zoneMngmt.do")
	public String zoneMngmt(ModelMap model,  HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		int srchPage = String.valueOf(request.getParameter("srchPage")).matches("(^[0-9]*$)") ? Integer.valueOf(request.getParameter("srchPage")) : 1;
		String srchRecPerPage = StringUtil.nvl(param.get("srchRecPerPage"), String.valueOf(srchCnt));
		String srchCondWord= StringUtil.nvl(param.get("srchCondWord"));
	
		PaginationVO pageVO = new PaginationVO();
		pageVO.setCurPage(srchPage);
		pageVO.setRecPerPage(Integer.parseInt(srchRecPerPage));
		
		pageVO.calcRecordIndex();
		
		int page = pageVO.getCurPage()-1;
		int pageSize = pageVO.getRecPerPage();
		
 		String zoneUrl = "http://" +GLOBAL_API_IP + ":" + GLOBAL_API_PORT+"/tacsm/v1/admin/zone?page="+page+"&pageSize="+pageSize;
		if(srchCondWord!=null){
			zoneUrl+="&zoneName="+srchCondWord;
		}
		
		System.out.println("zoneUrl >>>> "+zoneUrl);

		//페이지에서 조회할 레코드 인덱스 생성
		pageVO.calcRecordIndex();
	
		HashMap<String, Object> result = new HashMap<String, Object>();

		result = ApiUtil.getApiReq(zoneUrl);
		
		int totalElements = 0;
		List list = new ArrayList<>();
		if(result.get("data") != null){
			totalElements = (int) ((HashMap) result.get("data")).get("totalElements");
			list = (List) ((HashMap) result.get("data")).get("content");
		}

		pageVO.setTotRecord(totalElements);
		pageVO.setUnitPage(curPageUnit);
		pageVO.calcPageList();
		
		model.addAttribute("pagination", pageVO);
		model.addAttribute("zoneList", list);
		model.addAttribute("srchCondWord", srchCondWord);
		
		
		return "cubox/systemInfo/zone_management";
	}
	
	
	
	
	@RequestMapping(value="/systemInfo/spotMngmt.do")
	public String spotMngmt(ModelMap model,  HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		int srchPage = String.valueOf(request.getParameter("srchPage")).matches("(^[0-9]*$)") ? Integer.valueOf(request.getParameter("srchPage")) : 1;
		String srchRecPerPage = StringUtil.nvl(param.get("srchRecPerPage"), String.valueOf(srchCnt));
		
		PaginationVO pageVO = new PaginationVO();
		List list = new ArrayList<>();
		
		String zoneId = "";
		String zoneUrl = "http://" +GLOBAL_API_IP + ":" + GLOBAL_API_PORT + "/tacsm/v1/admin/zone?page=0&pageSize=20";
		HashMap<String, Object> zoneResult = new HashMap<String, Object>();
		List zoneCombo = new ArrayList<>();
		
		zoneResult = ApiUtil.getApiReq(zoneUrl);
		
		if(zoneResult.get("data") != null){
			zoneCombo = (List) ((HashMap) zoneResult.get("data")).get("content");
		}
		

		pageVO.setCurPage(srchPage);
		pageVO.setRecPerPage(Integer.parseInt(srchRecPerPage));

		//페이지에서 조회할 레코드 인덱스 생성
		pageVO.calcRecordIndex();
		
		int page = pageVO.getCurPage()-1;
		int pageSize = pageVO.getRecPerPage();
		
 		if(!CommonUtils.empty(param)) {


	 		String spotUrl = "http://" +GLOBAL_API_IP + ":" + GLOBAL_API_PORT + "/tacsm/v1/admin/spot?page="+page+"&pageSize="+pageSize;
	 			   spotUrl += "&zoneId="+param.get("srchZone")+"&spotName="+param.get("srchCondName")+"&spotHost="+param.get("srchCondHost");
			System.out.println("spotUrl >>>> "+spotUrl);
			
			//페이지에서 조회할 레코드 인덱스 생성
			pageVO.calcRecordIndex();
		
			HashMap<String, Object> result = new HashMap<String, Object>();
			result = ApiUtil.getApiReq(spotUrl);
			
			int totalElements = 0;
			
			if(result.get("data") != null){
				totalElements = (int) ((HashMap) result.get("data")).get("totalElements");
				list = (List) ((HashMap) result.get("data")).get("content");
				
			}
			pageVO.setTotRecord(totalElements);
			pageVO.setUnitPage(curPageUnit);
			pageVO.calcPageList();
		
		}
		model.addAttribute("zoneCombo",zoneCombo);
		model.addAttribute("pagination", pageVO);
		model.addAttribute("spotList", list);
		model.addAttribute("zoneId", param.get("srchZone"));
		model.addAttribute("srchCondName", param.get("srchCondName"));
		model.addAttribute("srchCondHost", param.get("srchCondHost"));
		
		return "cubox/systemInfo/spot_management";
	}
	
	
	@RequestMapping(value="/systemInfo/deviceMngmt.do")
	public String deviceMngmt(ModelMap model, @RequestParam Map<String, Object> commandMap, HttpServletRequest request) throws Exception {


		return "cubox/systemInfo/device_management";
	}
	


}
