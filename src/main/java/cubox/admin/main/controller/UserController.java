package cubox.admin.main.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cubox.admin.cmmn.util.CommonUtils;
import cubox.admin.cmmn.util.DataScrty;
import cubox.admin.cmmn.util.StringUtil;
import cubox.admin.main.service.AuthService;
import cubox.admin.main.service.CodeService;
import cubox.admin.main.service.MenuService;
import cubox.admin.main.service.UserService;
import cubox.admin.main.service.vo.LoginVO;
import cubox.admin.main.service.vo.PaginationVO;

@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Value("#{property['Globals.paging.recPerPage']}")
	private int gvRecPerPage;  //조회할 페이지 수
	
	@Value("#{property['Globals.paging.curPageUnit']}")
	private int gvCurPageUnit;  //한번에 표시할 페이지 번호 개수
	
	@Value("#{property['Globals.passwd.post']}")
	private String gvPasswdPost;  //비밀번호 암호화 단어Globals.passwd.errCnt
	
	@Resource(name="commonUtils")
	private CommonUtils commonUtils;

	@Resource(name="codeService")
	private CodeService codeService;
	
	@Resource(name="menuService")
	private MenuService menuService;
	
	@Resource(name="authService")
	private AuthService authService;

	@Resource(name="userService")
	private UserService userService;

	@RequestMapping(value="/user/passwdChange.do")
	public String passwdChange(ModelMap model, HttpServletRequest request) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		
		if (loginVO != null && !StringUtil.nvl(loginVO.getUserId()).equals("")) {
			return "cubox/admin/UserPwChange";
		}else{
			return "redirect:/login.do";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/user/modPasswd.do")
	public ModelAndView modPasswd(@RequestParam Map<String, Object> param, HttpServletRequest request) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");

		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");

		if (loginVO != null && !StringUtil.nvl(loginVO.getUserId()).equals("")) {

			param.put("userId", loginVO.getUserId());
			param.put("esntlId", loginVO.getEsntlId());

			int cnt = userService.checkPasswd(param);

			if(cnt > 0){
				int passwdCnt = userService.updatePasswd(param);
				modelAndView.addObject("checkPwdError", "N");
			} else {
				modelAndView.addObject("checkPwdError", "Y");
			}
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/userManage.do")
	public String userManage(ModelMap model, HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");

		String menuUrl = request.getServletPath();
		String menuNm = StringUtil.nvl(menuService.selectMenuNm(menuUrl));
		
		LOGGER.debug("###[userMng] param : {}", param);
		
		if (loginVO != null && loginVO.getUserId() != null && !loginVO.getUserId().equals("")) {
		
			List<Map<String, Object>> cboCntPerPage = codeService.selectCdCombo("00001"); //페이지당건수
			List<Map<String, Object>> cboAuth = authService.selectAuthCombo(); //메뉴분류
			
			int srchCnt = gvRecPerPage;
			String sRecPerPage = StringUtil.nvl(param.get("srchRecPerPage"));
			if(!sRecPerPage.equals("")) {
				srchCnt = Integer.parseInt(sRecPerPage);
			}

			// paging
			int srchPage = Integer.parseInt(StringUtil.nvl(param.get("srchPage"), "1")); //조회할 페이지 번호 기본 1페이지
			param.put("offset", commonUtils.getOffset(srchPage, srchCnt));
			param.put("srchCnt", srchCnt);
			
			// select
			List<Map<String, Object>> list = userService.selectUserList(param);
			int count = userService.selectUserListCount(param);

			// paging
			PaginationVO pageVO = new PaginationVO();
			pageVO.setCurPage(srchPage);
			pageVO.setRecPerPage(srchCnt);
			pageVO.setTotRecord(count);
			pageVO.setUnitPage(gvCurPageUnit);
			pageVO.calcPageList();
			
			model.addAttribute("menuNm", menuNm); 
			model.addAttribute("menuUrl", menuUrl); 
			model.addAttribute("params", param); 
			model.addAttribute("list", list);
			model.addAttribute("pagination", pageVO);
			model.addAttribute("cboCntPerPage", cboCntPerPage);
			model.addAttribute("cboAuth", cboAuth);

			return "cubox/admin/UserManage";
		} else {
			return "redirect:/login.do";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/checkIdDplct.do")
	public ModelAndView checkIdDplct(@RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");

		try {
			int cnt = userService.selectUserIdDplct(param);
			modelAndView.addObject("dupcnt", cnt);
			modelAndView.addObject("result", "success");
		} catch(Exception e){
			modelAndView.addObject("result", "fail");
			modelAndView.addObject("message", e.getMessage());
		}
		
		return modelAndView;
	}	
	
	@ResponseBody
	@RequestMapping(value="/admin/getUserInfo.do")
	public ModelAndView getUserInfo(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		try { 
			Map<String, Object> user = userService.selectUserInfo(param);
			if(user != null) {
				modelAndView.addObject("user", user);	
			} else {
				modelAndView.addObject("message", "해당 자료가 없습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
			modelAndView.addObject("message", e.getMessage());
		}

		return modelAndView;
	}	
	
	@ResponseBody
	@RequestMapping(value="/admin/addUserInfo.do")
	public ModelAndView addUserInfo(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		param.put("regist_id", loginVO.getEsntlId());
		
		String userId = StringUtil.nvl(param.get("user_id"));
		String userPw = DataScrty.encryptPassword(userId + gvPasswdPost, userId);
		param.put("user_pw", userPw);
		
		try {
			int	cnt = userService.insertUserInfo(param);
			if(cnt > 0) {
				modelAndView.addObject("result", "success");
			} else {
				modelAndView.addObject("result", "fail");
			}
		} catch(Exception e) {
			e.printStackTrace();
			modelAndView.addObject("result", "fail");
			modelAndView.addObject("message", e.getMessage());
		}

		return modelAndView;
	}	
	
	@ResponseBody
	@RequestMapping(value="/admin/modUserInfo.do")
	public ModelAndView modUserInfo(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		param.put("updt_id", loginVO.getEsntlId());
		
		try {
			if(StringUtil.nvl(param.get("esntl_id")).equals("")) {
				modelAndView.addObject("result", "fail");
				modelAndView.addObject("message", "사용자 정보가 잘못 되었습니다.");
			} else {
				int	cnt = userService.updateUserInfo(param);
				if(cnt > 0) {
					modelAndView.addObject("result", "success");
				} else {
					modelAndView.addObject("result", "fail");
				}	
			}
		} catch(Exception e) {
			e.printStackTrace();
			modelAndView.addObject("result", "fail");
			modelAndView.addObject("message", e.getMessage());
		}

		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/modUserUseYn.do")
	public ModelAndView modUserUseYn(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		param.put("updt_id", loginVO.getEsntlId());
		
		try {
			int cnt = userService.updateUserUseYn(param);	
			
			if(cnt > 0) {
				modelAndView.addObject("result", "success");
			} else {
				modelAndView.addObject("result", "fail");
			}
		} catch(Exception e) {
			e.printStackTrace();
			modelAndView.addObject("result", "fail");
			modelAndView.addObject("message", e.getMessage());
		}

		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/resetUserPw.do")
	public ModelAndView resetUserPw(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		param.put("updt_id", loginVO.getEsntlId());
		
		String userId = StringUtil.nvl(param.get("user_id"));
		String userPw = DataScrty.encryptPassword(userId + gvPasswdPost, userId);
		param.put("user_pw", userPw);
		
		try {
			int cnt = userService.updateUserPasswdReset(param);	
			
			if(cnt > 0) {
				modelAndView.addObject("result", "success");
			} else {
				modelAndView.addObject("result", "fail");
			}
		} catch(Exception e) {
			e.printStackTrace();
			modelAndView.addObject("result", "fail");
			modelAndView.addObject("message", e.getMessage());
		}

		return modelAndView;
	}	
}
