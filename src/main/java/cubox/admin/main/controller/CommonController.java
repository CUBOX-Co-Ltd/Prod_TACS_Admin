package cubox.admin.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springmodules.validation.commons.DefaultBeanValidator;

import cubox.admin.cmmn.util.ApiUtil;
import cubox.admin.cmmn.util.AuthorManager;
import cubox.admin.cmmn.util.CommonUtils;
import cubox.admin.cmmn.util.CuboxProperties;
import cubox.admin.cmmn.util.StringUtil;
import cubox.admin.main.service.CommonService;
import cubox.admin.main.service.vo.LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;


@Controller
public class CommonController {

	/** commonService */
	@Resource(name = "commonService")
	private CommonService commonService;

	@Resource(name = "commonUtils")
	private CommonUtils commonUtils;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	
	
	private static String GLOBAL_API_IP = CuboxProperties.getProperty("Globals.api.ip");
	private static String GLOBAL_API_PORT = CuboxProperties.getProperty("Globals.api.port");
	

	@RequestMapping(value="/login.do")
	public String login(ModelMap model, @RequestParam Map<String, Object> commandMap, RedirectAttributes redirectAttributes) throws Exception {
		
		return "cubox/common/login";
	}

	@RequestMapping(value="/common/loginProc.do")
	public String actionLogin(ModelMap model, @RequestParam Map<String, Object> commandMap, HttpServletRequest request) throws Exception {

		String fsiteid = (String) commandMap.get("fsiteid");
 		String fpasswd = (String) commandMap.get("fpasswd");

		LoginVO loginVO = new LoginVO();
 		loginVO.setFsiteid(fsiteid);
		loginVO.setFpasswd(fpasswd);
		loginVO.setFauthcd("01");
		loginVO.setAuthor_id("00001");
		
		if(fsiteid.equals("admin") && fpasswd.equals("admin")){
			
			request.getSession().setAttribute("loginVO", loginVO);
			return "redirect:/main.do";
		} else {
			model.addAttribute("resultMsg", "loginError");
			return "cubox/common/login";
		}
	}

	@SuppressWarnings("serial")
	@RequestMapping(value="/main.do")
	public String main(ModelMap model, @RequestParam Map<String, Object> commandMap, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {
		//자동새로고침때문에 추가
		String reloadYn = StringUtil.isNullToString(commandMap.get("reloadYn")).matches("Y") ? StringUtil.isNullToString(commandMap.get("reloadYn")) : "N";
		String intervalSecond = StringUtil.isNullToString(commandMap.get("intervalSecond")).matches("(^[0-9]+$)") ? StringUtil.isNullToString(commandMap.get("intervalSecond")) : "5";

		HashMap<String, Object> result = new HashMap<String, Object>();
		
		String deviceUrl = "http://" +GLOBAL_API_IP + ":" + GLOBAL_API_PORT+"/tacsm/v1/admin/device?page=0&pageSize=10";
		
		result = (ApiUtil.getApiReq(deviceUrl));
		
		byte[] imageContent = null;
		List list = new ArrayList<>();
		List imageList = new ArrayList<>();
		String imageString = "";

		HashMap<String, Object> imageHash = new HashMap<>();
		
		
 		if(result.get("data") != null){
 			HashMap<String, Object> hash = (HashMap<String, Object>) result.get("data");
 			list = (List) hash.get("content");
 			for (int i = 0; i<list.size(); i++){
 				HashMap<String, Object> table = new HashMap<String, Object>();
 				table = (HashMap<String, Object>) list.get(i);
 				imageString = "data:image/jpeg;base64,"+table.get("image").toString();
 				imageHash.put(String.valueOf(i), imageString);
 			
 			}
 			imageList = new ArrayList<>(imageHash.values());
		}
 		System.out.print(imageString);
 		
		model.addAttribute("reloadYn", reloadYn);
		model.addAttribute("intervalSecond", intervalSecond);
		model.addAttribute("deviceList", list);
		model.addAttribute("imageString", imageString);
		model.addAttribute("imageList", imageList);
		
		return "cubox/common/main";
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

	/**
	 * 모니터링
	 * @param commandMap 파라메터전달용 commandMap
	 * @return common/index
	 * @throws Exception
	 */
	@RequestMapping(value="/index.do")
	public String index(ModelMap model, @RequestParam Map<String, Object> commandMap,
			HttpServletRequest request) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");

		if (loginVO != null && loginVO.getFsiteid() != null && !loginVO.getFsiteid().equals("")) {
			model.addAttribute("menuPath", "common/index");
			return "cubox/cuboxSubContents";
		}else{
			return "redirect:/login.do";
		}
	}
}
