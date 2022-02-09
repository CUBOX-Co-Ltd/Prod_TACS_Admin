package cubox.admin.main.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
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
		String srchZoneId = StringUtil.nvl(param.get("srchZone"));
		String srchSpotName = StringUtil.nvl(param.get("srchCondName"));
		String srchSpotHost = StringUtil.nvl(param.get("srchCondHost"));
		
		
		
		PaginationVO pageVO = new PaginationVO();
		List list = new ArrayList<>();
		
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
		
 		
 		String spotUrl = "http://" +GLOBAL_API_IP + ":" + GLOBAL_API_PORT + "/tacsm/v1/admin/spot?page="+page+"&pageSize="+pageSize;
 			   spotUrl += "&zoneId=" + srchZoneId + "&spotName=" + srchSpotName + "&spotHost=" + srchSpotHost;
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
		
		
		model.addAttribute("zoneCombo",zoneCombo);
		model.addAttribute("pagination", pageVO);
		model.addAttribute("spotList", list);
		model.addAttribute("zoneId", srchZoneId);
		model.addAttribute("srchCondName", srchSpotName);
		model.addAttribute("srchCondHost", srchSpotHost);
		
		return "cubox/systemInfo/spot_management";
	}
	
	
	@RequestMapping(value="/systemInfo/deviceMngmt.do")
	public String deviceMngmt(ModelMap model, HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {
		
		int srchPage = String.valueOf(request.getParameter("srchPage")).matches("(^[0-9]*$)") ? Integer.valueOf(request.getParameter("srchPage")) : 1;
		String srchRecPerPage = StringUtil.nvl(param.get("srchRecPerPage"), String.valueOf(srchCnt));
		
		String startDate = StringUtil.isNullToString(request.getParameter("startDate"));
		String endDate = StringUtil.isNullToString(request.getParameter("expireDate"));

		if(startDate.equals("")) startDate =  getYesterDt();
		if(endDate.equals("")) endDate = getTodayDt();
		
		PaginationVO pageVO = new PaginationVO();
		List list = new ArrayList<>();
	
		pageVO.setCurPage(srchPage);
		pageVO.setRecPerPage(Integer.parseInt(srchRecPerPage));

		//페이지에서 조회할 레코드 인덱스 생성
		pageVO.calcRecordIndex();
		
		int page = pageVO.getCurPage()-1;
		int pageSize = pageVO.getRecPerPage();

 		String deviceUrl = "http://"+GLOBAL_API_IP+":"+GLOBAL_API_PORT+"/tacsm/v1/admin/device?registDtStart="+startDate.replace(" ", "%20")+"&registDtEnd="+endDate.replace(" ", "%20")+"&page="+page+"&pageSize="+pageSize;
 			   
		System.out.println("deviceUrl >>>> "+deviceUrl);
		
		//페이지에서 조회할 레코드 인덱스 생성
		pageVO.calcRecordIndex();
	
		HashMap<String, Object> result = new HashMap<String, Object>();
		result = ApiUtil.getApiReq(deviceUrl);
		
		int totalElements = 0;
		List imageList = new ArrayList<>();
		String imageString = "";
		
		HashMap<String, Object> imageHash = new HashMap<>();
		
		if(result.get("data") != null){
			totalElements = (int) ((HashMap) result.get("data")).get("totalElements");
			list = (List) ((HashMap) result.get("data")).get("content");
			for (int i = 0; i<list.size(); i++){
 				HashMap<String, Object> table = new HashMap<String, Object>();
 				table = (HashMap<String, Object>) list.get(i);
 				imageString = "data:image/jpeg;base64,"+table.get("image").toString();
 				imageHash.put(String.valueOf(i), imageString);
 			
 			}
			imageList = new ArrayList<>(imageHash.values());
		}
		pageVO.setTotRecord(totalElements);
		pageVO.setUnitPage(curPageUnit);
		pageVO.calcPageList();
		

		model.addAttribute("pagination", pageVO);
		model.addAttribute("deviceList", list);
		model.addAttribute("imageList", imageList);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		return "cubox/systemInfo/device_management";
	}
	
	
	@RequestMapping(value="/systemInfo/deviceLocMngmt.do")
	public String deviceLocMngmt(ModelMap model, HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {
		
		int srchPage = String.valueOf(request.getParameter("srchPage")).matches("(^[0-9]*$)") ? Integer.valueOf(request.getParameter("srchPage")) : 1;
		String srchRecPerPage = StringUtil.nvl(param.get("srchRecPerPage"), String.valueOf(srchCnt));
		
		String startDate = StringUtil.isNullToString(request.getParameter("startDate"));
		String endDate = StringUtil.isNullToString(request.getParameter("expireDate"));

		
		if(startDate.equals("")) startDate = getYesterDt();
		if(endDate.equals("")) endDate = getTodayDt();
		
		PaginationVO pageVO = new PaginationVO();
		List list = new ArrayList<>();
		
		

		pageVO.setCurPage(srchPage);
		pageVO.setRecPerPage(Integer.parseInt(srchRecPerPage));

		//페이지에서 조회할 레코드 인덱스 생성
		pageVO.calcRecordIndex();
		
		int page = pageVO.getCurPage()-1;
		int pageSize = pageVO.getRecPerPage();
		
	
		String zoneUrl = "http://" +GLOBAL_API_IP + ":" + GLOBAL_API_PORT + "/tacsm/v1/admin/zone?page=0&pageSize=20";
		
		HashMap<String, Object> zoneResult = new HashMap<String, Object>();
		HashMap<String, Object> spotResult = new HashMap<String, Object>();
		List zoneCombo = new ArrayList<>();
		List spotCombo = new ArrayList<>();
		
		zoneResult = ApiUtil.getApiReq(zoneUrl);
		
		
		if(zoneResult.get("data") != null){
			zoneCombo = (List) ((HashMap) zoneResult.get("data")).get("content");
		}
	
		if(!CommonUtils.empty(param)) {
			String spotUrl = "http://" +GLOBAL_API_IP + ":" + GLOBAL_API_PORT + "/tacsm/v1/admin/spot?page=0&pageSize=20";
			spotResult = ApiUtil.getApiReq(spotUrl);
			System.out.println("spotUrl >>>> "+spotUrl);
			if(spotResult.get("data") != null){
				spotCombo = (List) ((HashMap) spotResult.get("data")).get("content");
			}
			
	 		String deviceLocUrl = "http://" +GLOBAL_API_IP + ":" + GLOBAL_API_PORT + "/tacsm/v1/admin/deviceLoc?";
	 			deviceLocUrl += "zoneId="+param.get("srchZone")+"&spotId="+param.get("srchSpot")+"&page="+page+"&pageSize="+pageSize;
	 			   
			System.out.println("beaconUrl >>>> "+deviceLocUrl);
			
			//페이지에서 조회할 레코드 인덱스 생성
			pageVO.calcRecordIndex();
		
			HashMap<String, Object> result = new HashMap<String, Object>();
			result = ApiUtil.getApiReq(deviceLocUrl);
			
			int totalElements = 0;
			
			if(result.get("data") != null){
				totalElements = (int) ((HashMap) result.get("data")).get("totalElements");
				list = (List) ((HashMap) result.get("data")).get("content");
				
			}
			pageVO.setTotRecord(totalElements);
			pageVO.setUnitPage(curPageUnit);
			pageVO.calcPageList();
		
		}
		model.addAttribute("pagination", pageVO);
		model.addAttribute("deviceLocList", list);
		model.addAttribute("spotId", param.get("srchSpot"));
		model.addAttribute("zoneId", param.get("srchZone"));
		model.addAttribute("zoneCombo", zoneCombo);
		model.addAttribute("spotCombo", spotCombo);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		
		return "cubox/systemInfo/deviceLoc_management";
	}
	
	
	private String getTodayDt() {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = sdf.format(date);

		return today;
	}




	private String getYesterDt() {
		// TODO Auto-generated method stub
		Date dDate = new Date();
		dDate = new Date(dDate.getTime()+(1000*60*60*24*-1));
		SimpleDateFormat dSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String yesterday = dSdf.format(dDate);

		return yesterday;
	}




	@ResponseBody
	@RequestMapping(value = "/systemInfo/getSpotCombo.do")
	public ModelAndView getSpotCombo(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		String spotUrl = "http://" + GLOBAL_API_IP + ":" + GLOBAL_API_PORT + "/tacsm/v1/admin/spot?page=0&pageSize=20&zoneId="+param.get("srchZone");
		   
		System.out.println("spotUrl >>>> "+spotUrl);

		HashMap<String, Object> spotResult = new HashMap<String, Object>();
		List spotCombo = new ArrayList<>();
		
		spotResult = ApiUtil.getApiReq(spotUrl);
		
		if(spotResult.get("data") != null){
			spotCombo = (List) ((HashMap) spotResult.get("data")).get("content");
		}

		modelAndView.addObject("spotCombo", spotCombo);

		return modelAndView;
	}	
	


}
