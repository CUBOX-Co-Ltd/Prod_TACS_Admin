package cubox.admin.main.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cubox.admin.cmmn.util.ApiUtil;
import cubox.admin.cmmn.util.AuthorManager;
import cubox.admin.cmmn.util.CommonUtils;
import cubox.admin.cmmn.util.CuboxProperties;
import cubox.admin.cmmn.util.LoginManager;
import cubox.admin.cmmn.util.StringUtil;
import cubox.admin.main.service.CommonService;
import cubox.admin.main.service.vo.LoginVO;
import net.sf.json.JSONObject;

@Controller
public class CommonController {
	
	@Value("#{property['Globals.passwd.errCnt']}")
	private int gvPasswdErrCnt;  //비밀번호 오류 허용수

	@Resource(name = "commonUtils")
	private CommonUtils commonUtils;
	
	@Resource(name = "commonService")
	private CommonService commonService;
	

	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);
	
	private static String GLOBAL_API_IP = CuboxProperties.getProperty("Globals.api.ip");
	private static String GLOBAL_API_PORT = CuboxProperties.getProperty("Globals.api.port");
	
	private static String GLOBAL_API_URL = CuboxProperties.getProperty("Globals.api.url");
	
	private static int page = 0;
	private static int pageSize = 10;
	
	@RequestMapping(value="/login.do")
	public String login(ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		LoginManager loginManager = LoginManager.getInstance();  //LoginManager
        model.addAttribute("userCnt", Integer.valueOf(loginManager.getUserCount()));  //LoginManager		
		
		return "cubox/common/login";
	}
	
	/*@RequestMapping(value="/main.do")
	public String main(ModelMap model, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {
		//return "redirect:/history/list.do";
		
    	List<Map<String, String>> list = commonService.selectMainInoutHist();
    	model.addAttribute("inoutHist", list);
    	
		return "cubox/common/main";
	}*/
	
	@SuppressWarnings("serial")
	@RequestMapping(value="/main.do")
	public String main(ModelMap model, @RequestParam Map<String, Object> commandMap, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {
		//자동새로고침때문에 추가
		String reloadYn = StringUtil.isNullToString(commandMap.get("reloadYn")).matches("Y") ? StringUtil.isNullToString(commandMap.get("reloadYn")) : "N";
		String intervalSecond = StringUtil.isNullToString(commandMap.get("intervalSecond")).matches("(^[0-9]+$)") ? StringUtil.isNullToString(commandMap.get("intervalSecond")) : "5";

		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> spotResult = new HashMap<String, Object>();
		HashMap<String, Object> zoneResult = new HashMap<String, Object>();
 		HashMap<String, Object> spotdResult = new HashMap<String, Object>();
 		
 		List spotImageList = new ArrayList<>();
 		List zoneList = new ArrayList<>();
 		List spotList = new ArrayList<>();
 		List spotNameList = new ArrayList<>();
		List deviceList = new ArrayList<>();
	
		String startDate = getMonthDt();
		String endDate = getTodayDt();

 		String deviceUrl =  GLOBAL_API_URL + "/device?upDtSt="+URLEncoder.encode(startDate+":00", "UTF-8")+"&upDtEd="+URLEncoder.encode(endDate+":59", "UTF-8")+"&page=0&pageSize=10";
		System.out.println("### [main]deviceUrl:"+deviceUrl);
		
		
		String zoneUrl = GLOBAL_API_URL+"/zone?page="+page+"&pageSize="+pageSize;
		String spotUrl = "";
		
		result = (ApiUtil.getApiReq(deviceUrl));		
		zoneResult = (ApiUtil.getApiReq(zoneUrl));

 		if(result.get("data") != null){
 			HashMap<String, Object> hash = (HashMap<String, Object>) result.get("data");
 			deviceList = (List) hash.get("content");
		} // 전체 사진 가져오기
 		
 		String zoneId = "";
 		String zoneHost = "";
 		String spotUuid = "";
 		 
 		if(zoneResult.get("data")!=null){
 			zoneList = (List)((HashMap) zoneResult.get("data")).get("content");
 
 			for (int i =0; i <zoneList.size(); i++){
 				HashMap<String, Object> table = new HashMap<String, Object>();
 				table = (HashMap<String, Object>) zoneList.get(i);
 				zoneId = table.get("id").toString();
 				zoneHost = table.get("zoneHost").toString();
 				spotUrl = GLOBAL_API_URL+"/spot?zoneId="+zoneId+"&page="+page+"&pageSize="+pageSize;
 				spotResult = (ApiUtil.getApiReq(spotUrl));

 				if(spotResult.get("data") != null){
 					HashMap<String, Object> table2 = new HashMap<String, Object>();
 		 			spotList = (List)((HashMap) spotResult.get("data")).get("content");
 		 			table2 = (HashMap<String, Object>) spotList.get(0);
 		 			spotUuid = table2.get("spotUuid").toString();
 		 			spotNameList.add(table2.get("spotName").toString());

	 				String spotdUrl = "http://"+zoneHost+"/tacsz/v1/admin/spot/"+spotUuid+"/device?page="+page+"&pageSize="+pageSize;
	 				
	 				System.out.println("### [main]spotdUrl:"+spotdUrl);
	 				spotdResult= (ApiUtil.getApiReq(spotdUrl));
	 				List spotImageResult = new ArrayList<>();
	 				if(spotdResult.get("data") != null){
	 		 			HashMap<String, Object> hash2 = (HashMap<String, Object>) spotdResult.get("data");
	 		 			List listTemp = (List)hash2.get("content");
	 		 			spotImageResult.addAll(listTemp);
	 				}
	 				spotImageList.add(spotImageResult);
 		 		}
 			}
 		}
 	
 		model.addAttribute("deviceList", deviceList);
 		model.addAttribute("spotNameList", spotNameList);
 		model.addAttribute("spotImageList", spotImageList);
		model.addAttribute("reloadYn", reloadYn);
		model.addAttribute("intervalSecond", intervalSecond);
		model.addAttribute("totalElements", spotImageList.size());

		return "cubox/common/main";
	}	
	
	
	
//	@SuppressWarnings("serial")
//	@RequestMapping(value="/main.do")
//	public String main(ModelMap model, @RequestParam Map<String, Object> commandMap, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {
//		//자동새로고침때문에 추가
//		String reloadYn = StringUtil.isNullToString(commandMap.get("reloadYn")).matches("Y") ? StringUtil.isNullToString(commandMap.get("reloadYn")) : "N";
//		String intervalSecond = StringUtil.isNullToString(commandMap.get("intervalSecond")).matches("(^[0-9]+$)") ? StringUtil.isNullToString(commandMap.get("intervalSecond")) : "5";
//		
//		HashMap<String, Object> result = new HashMap<String, Object>();
//		HashMap<String, Object> spotResult = new HashMap<String, Object>();
//		HashMap<String, Object> zoneResult = new HashMap<String, Object>();
//		
//		
//		HashMap<String, Object> spotdResult = new HashMap<String, Object>();
//		HashMap<String, Object> spotdResult2 = new HashMap<String, Object>();
//		
//		
//		String startDate = getYesterDt().replace(" ", "%20");
//		String endDate = getTodayDt().replace(" ", "%20");
//		
//		
//		String deviceUrl =  GLOBAL_API_URL + "/device?upDtSt="+startDate+"&upDtEd="+endDate+"&page=0&pageSize=10";
//		System.out.println("### [main]deviceUrl:"+deviceUrl);
//		
//		
//		String zoneUrl = GLOBAL_API_URL+"/zone?page=0&pageSize=10";
//		String spotUrl = "";
//		
//		result = (ApiUtil.getApiReq(deviceUrl));
//		
//		zoneResult = (ApiUtil.getApiReq(zoneUrl));
//		
//		
//		List deviceList = new ArrayList<>();
//		if(result.get("data") != null){
//			HashMap<String, Object> hash = (HashMap<String, Object>) result.get("data");
//			deviceList = (List) hash.get("content");
//		} // 전체 사진 가져오기
//		
//		List spotList = new ArrayList<>();
//		List spotImageList = new ArrayList<>();
//		List zoneList = new ArrayList<>();
//		
//		HashMap<String, Object> temp = new HashMap<String, Object>();
//		
//		String spotUuid = "";
//		
//		String zoneId = "";
//		String zoneHost = "";
//		
//		if(zoneResult.get("data")!=null){
//			zoneList = (List)((HashMap) zoneResult.get("data")).get("content");
//			
//			for (int i =0; i <zoneList.size(); i++){
//				HashMap<String, Object> table = new HashMap<String, Object>();
//				table = (HashMap<String, Object>) zoneList.get(i);
//				zoneId = table.get("id").toString();
//				zoneHost = table.get("zoneHost").toString();
//				spotUrl = GLOBAL_API_URL+"/spot?zoneId="+zoneId+"&page=0&pageSize=10";
//				spotResult = (ApiUtil.getApiReq(spotUrl));
//				
//				if(spotResult.get("data") != null){
//					HashMap<String, Object> hash = (HashMap<String, Object>) spotResult.get("data");
//					//spotList = (List) hash.get("content");
//					// for (int i = 0; i<spotList.size(); i++){
//// 		 				HashMap<String, Object> table = new HashMap<String, Object>();
//// 		 				table = (HashMap<String, Object>) spotList.get(i);
//// 		 				spotUuid = table.get("spotUuid").toString(); 
//					
//					String spotdUrl = "http://172.16.150.15:8080/tacsz/v1/admin/spot/9bf5981f-f2b4-4646-9fec-451a61dce3d4/device?page=0&pageSize=10";
//					
//					System.out.println("### [main]spotdUrl:"+spotdUrl);
//					spotdResult= (ApiUtil.getApiReq(spotdUrl));
//					List spotImageResult = new ArrayList<>();
//					if(spotdResult.get("data") != null){
//						HashMap<String, Object> hash2 = (HashMap<String, Object>) spotdResult.get("data");
//						List listTemp = (List)hash2.get("content");
//						
//						spotImageResult.addAll(listTemp);
//					}
//					spotImageList.add(spotImageResult);
//					
//					String spotdUrl2 = "http://172.16.150.16:8080/tacsz/v1/admin/spot/aed7f216-d8d7-470c-aae8-18e60b4ead86/device?page=0&pageSize=10";
//					
//					
//					
//					System.out.println("### [main]spotdUrl:"+spotdUrl2);
//					spotdResult2= (ApiUtil.getApiReq(spotdUrl2));
//					List spotImageResult2 = new ArrayList<>();
//					if(spotdResult.get("data") != null){
//						HashMap<String, Object> hash2 = (HashMap<String, Object>) spotdResult2.get("data");
//						List listTemp = (List)hash2.get("content");
//						
//						spotImageResult2.addAll(listTemp);
//					}
//					spotImageList.add(spotImageResult2);
//					
//					//}
//				}
//				
//				
//			}
//			
//			
//			
//			
//			
//		}
//		
//		
//		
//		
//		model.addAttribute("deviceList", deviceList);
//		model.addAttribute("spotImageList", spotImageList);
//		model.addAttribute("reloadYn", reloadYn);
//		model.addAttribute("intervalSecond", intervalSecond);
//		model.addAttribute("totalElements", spotImageList.size());
//		
//		
//		return "cubox/common/main";
//	}	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/main/selectMainStatInCnt.do")
	public ModelAndView selectMainStatInCnt (@RequestParam Map<String, Object> commandMap, HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	
    	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
    	
    	List<Map<String, String>> list = commonService.selectMainStatInCnt();

    	modelAndView.addObject("list", list);

		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/main/selectMainTodayInCnt.do")
	public ModelAndView selectMainTodayInCnt (@RequestParam Map<String, Object> commandMap, HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	
    	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
    	
    	List<Map<String, String>> list = commonService.selectMainTodayInCnt();

    	modelAndView.addObject("list", list);

		return modelAndView;
	}	

	@ResponseBody
	@RequestMapping(value="/main/selectMainPassRate.do")
	public ModelAndView selectMainPassRate (@RequestParam Map<String, Object> commandMap, HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	
    	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
    	
    	Map<String, String> rate = commonService.selectMainPassRate();

    	modelAndView.addObject("rate", rate);

		return modelAndView;
	}	

	@RequestMapping(value="/common/loginProc.do")
	public String actionLogin(ModelMap model, @RequestParam Map<String, Object> param, HttpServletRequest request) throws Exception {
		LoginManager loginManager = LoginManager.getInstance(); //LoginManager
		HttpSession session = request.getSession(); //LoginManager
        
		LoginVO loginVO = new LoginVO();
		loginVO.setUserId(StringUtil.nvl(param.get("userId")));
		loginVO.setUserPw(StringUtil.nvl(param.get("userPw")));

		LoginVO resultVO = commonService.actionLogin(loginVO);
		
		if(loginManager.isUsing(resultVO.getUserNm()+"_"+resultVO.getUserId())) {
			LOGGER.warn(String.format("[%s]님이 다른 PC에서 로그인 하였습니다.", resultVO.getUserNm()+"_"+resultVO.getUserId()));
			
			/* khlee_dev 중복 로그인 체크 
			model.addAttribute("result", "loginError");
			model.addAttribute("message", "이미 로그인하여 접속 중인 사용자입니다.");
			return "cubox/common/login";
			*/
		} else {
			LOGGER.info(String.format("[%s]님이 로그인할 예정입니다.", resultVO.getUserNm()+"_"+resultVO.getUserId()));
		}

		LOGGER.debug("###[resultVO]:" + resultVO);

		if (resultVO != null && !StringUtil.nvl(resultVO.getUserId()).equals("")) {
			if(resultVO.getUseYn().equals("N")) {
				model.addAttribute("result", "notUseError");
				model.addAttribute("message", "사용안함 처리된 아이디입니다. 관리자에게 문의하세요.");
				return "cubox/common/login";
			} else if(resultVO.getPwFailrCnt() >= gvPasswdErrCnt) {
				model.addAttribute("result", "pwFailError");
				model.addAttribute("message", String.format("패스워드 %d회 오류 입력으로 로그인할 수 없습니다.\\n관리자에게 문의하세요.", gvPasswdErrCnt));
				return "cubox/common/login";
			} else {
				commonService.lastConnect(resultVO); //로그인시 마지막 접속일 변경
				request.getSession().setAttribute("loginVO", resultVO);
				
				loginManager.setSession(session, resultVO.getUserNm()+"_"+resultVO.getUserId());  //LoginManager
				
				return "redirect:/main.do";
			}
		} else {
			commonService.failConnect(loginVO); //비밀번호 실패 횟수 update
			model.addAttribute("result", "loginError");
			model.addAttribute("message", "아이디 또는 비밀번호를 다시 확인하세요.\\n등록되지 않은 아이디이거나 아이디 또는 비밀번호를 잘못 입력하셨습니다.");
			return "cubox/common/login";
		}
	}

	@RequestMapping(value = "/logout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {

		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");
		session.setAttribute("loginVO", null);
		session.invalidate();
		AuthorManager.getInstance().clear();

		return "redirect:/login.do";
	}

	@RequestMapping(value="/index.do")
	public String index(ModelMap model, HttpServletRequest request) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");

		if (loginVO != null && !StringUtil.nvl(loginVO.getUserId()).equals("")) {
			model.addAttribute("menuPath", "common/index");
			return "cubox/cuboxSubContents";
		} else {
			return "redirect:/login.do";
		}
	}
	
	@RequestMapping(value="/common/pwChange.do")
	public String pwChangeView(ModelMap model, @RequestParam Map<String, Object> commandMap,
			HttpServletRequest request) throws Exception {

		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");

		if (loginVO != null && !StringUtil.nvl(loginVO.getUserId()).equals("")) {
			return "cubox/basicInfo/pw_change";
		}else{
			return "redirect:/login.do";
		}
	}
	
    @RequestMapping(value="/alive.do")
    public ResponseEntity<String> sessionAlive(HttpServletRequest request, @RequestParam(value="delUserName", required=false, defaultValue="") String delUserName) throws Exception {
        JSONObject obj = new JSONObject();
        
        LoginManager loginManager = LoginManager.getInstance(); //LoginManager
        
        LOGGER.debug("###[sessionAlive] delUserName : {}", delUserName);
        loginManager.removeSession(delUserName);       
        
        obj.put("I_AM", loginManager.getEsntlID(request.getSession())); //LoginManager
        obj.put("USERS", loginManager.getUsers()); //LoginManager

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/plain; charset=utf-8");
        return new ResponseEntity<String>(obj.toString(), responseHeaders, HttpStatus.CREATED);
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
		Calendar day = Calendar.getInstance();
	    day.add(Calendar.DATE, -1);
	    String yesterday = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(day.getTime());

		return yesterday;
	}
	
	
	private String getMonthDt() {
		// TODO Auto-generated method stub

	    Calendar day = Calendar.getInstance();
	    day.add(Calendar.MONTH, -1);
	    String beforeMonth = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(day.getTime());
	    
	    return beforeMonth;

	}
    
    
}
