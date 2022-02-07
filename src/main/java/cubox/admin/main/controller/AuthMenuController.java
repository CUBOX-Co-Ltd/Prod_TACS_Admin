package cubox.admin.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cubox.admin.cmmn.util.CommonUtils;
import cubox.admin.cmmn.util.StringUtil;
import cubox.admin.main.service.AuthMenuService;
import cubox.admin.main.service.AuthService;
import cubox.admin.main.service.CodeService;
import cubox.admin.main.service.MenuService;
import cubox.admin.main.service.vo.LoginVO;

@Controller
public class AuthMenuController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthMenuController.class);

	@Resource(name="commonUtils")
	private CommonUtils commonUtils;

	@Resource(name="codeService")
	private CodeService codeService;
	
	@Resource(name="menuService")
	private MenuService menuService;

	@Resource(name="authService")
	private AuthService authService;
	
	@Resource(name="authMenuService")
	private AuthMenuService authMenuService;

	@RequestMapping(value="/admin/authMenuManage.do")
	public String authMenuManage(ModelMap model, HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");

		String menuUrl = request.getServletPath();
		String menuNm = StringUtil.nvl(menuService.selectMenuNm(menuUrl));
		
		LOGGER.debug("###[authMenuMng] param : {}", param);
		
		if (loginVO != null && loginVO.getUserId() != null && !loginVO.getUserId().equals("")) {
			
			List<Map<String, Object>> cboAuth = authService.selectAuthCombo(); // 권한combo

			// 전체 메뉴 목록
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("use_yn", "Y");
			map.put("cl_use_yn", "Y");
			List<Map<String, Object>> totalMenuList = menuService.selectMenuList(map); 
			
			model.addAttribute("menuNm", menuNm); 
			model.addAttribute("menuUrl", menuUrl); 
			model.addAttribute("params", param); 
			model.addAttribute("totalMenuList", totalMenuList);
			model.addAttribute("cboAuth", cboAuth);

			return "cubox/admin/AuthMenuManage";
		} else {
			return "redirect:/login.do";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/getAuthMenuList.do")
	public ModelAndView getAuthMenuList(@RequestParam Map<String, Object> param, HttpServletRequest request) throws Exception {

		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	
    	List<Map<String, Object>> totalMenuList = null;
    	List<Map<String, Object>>  userMenuList = null;
    	
    	if(!StringUtil.nvl(param.get("authorCd")).equals("")) {
    		// 권한에 포함되지 않은 메뉴 목록
    		totalMenuList = authMenuService.selectAuthMenuExclList(param);
    		
    		// 권한에 대한 메뉴 목록
    		userMenuList = authMenuService.selectAuthMenuList(param);
    	} else {
    		// 전체 메뉴 목록
    		Map<String, Object> map = new HashMap<String, Object>();
			map.put("use_yn", "Y");
			map.put("cl_use_yn", "Y");
			totalMenuList = menuService.selectMenuList(map);
    	}
		
		modelAndView.addObject("totalMenuList", totalMenuList);
		modelAndView.addObject("userMenuList", userMenuList);

		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/saveAuthMenu.do")
	public ModelAndView saveAuthMenu(@RequestParam Map<String, Object> param, String[] arrMenu, HttpServletRequest request) throws Exception {

		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	
    	if(StringUtil.nvl(param.get("authorCd")).equals("")) {
    		modelAndView.addObject("result", "fail");
    		modelAndView.addObject("message", "권한을 선택하세요.");
    	} else if(arrMenu == null || arrMenu.length == 0) { 
    		modelAndView.addObject("result", "fail");
    		modelAndView.addObject("message", "메뉴를 1개 이상 선택하세요.");
    	} else {
    		try {
    			param.put("registId", loginVO.getEsntlId());
    			param.put("arrMenu", arrMenu);
    			
    			int cnt = authMenuService.saveAuthMenu(param);
    			LOGGER.debug("###[saveAuthMenu] cnt : {}", cnt);
    			
    			if(cnt > 0) {
    				modelAndView.addObject("result", "success");	
    			} else {
    				modelAndView.addObject("result", "fail");
    				modelAndView.addObject("message", "저장 중 오류가 발생했습니다.");
    			}    			
    		} catch(Exception e) {
    			modelAndView.addObject("result", "fail");
    			modelAndView.addObject("message", e.getMessage());
    		}
    	}
		
		return modelAndView;
	}	

}
