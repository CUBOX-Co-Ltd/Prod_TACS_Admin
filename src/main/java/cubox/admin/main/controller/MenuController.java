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
import cubox.admin.cmmn.util.StringUtil;
import cubox.admin.main.service.CodeService;
import cubox.admin.main.service.MenuService;
import cubox.admin.main.service.vo.LoginVO;
import cubox.admin.main.service.vo.PaginationVO;

@Controller
public class MenuController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

	@Value("#{property['Globals.paging.recPerPage']}")
	private int gvRecPerPage;  //조회할 페이지 수
	
	@Value("#{property['Globals.paging.curPageUnit']}")
	private int gvCurPageUnit;  //한번에 표시할 페이지 번호 개수

	@Resource(name="commonUtils")
	private CommonUtils commonUtils;

	@Resource(name="codeService")
	private CodeService codeService;
	
	@Resource(name="menuService")
	private MenuService menuService;

	@RequestMapping(value="/admin/menuManage.do")
	public String menuManage(ModelMap model, HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");

		String menuUrl = request.getServletPath();
		String menuNm = StringUtil.nvl(menuService.selectMenuNm(menuUrl));
		
		LOGGER.debug("###[menuMng] param : {}", param);
		
		if (loginVO != null && loginVO.getUserId() != null && !loginVO.getUserId().equals("")) {
		
			List<Map<String, Object>> cboCntPerPage = codeService.selectCdCombo("00001"); //페이지당건수
			List<Map<String, Object>> cboMenuCl = menuService.selectMenuClCombo(); //메뉴분류
			
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
			List<Map<String, Object>> list = menuService.selectMenuList(param);
			int count = menuService.selectMenuListCount(param);

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
			model.addAttribute("cboMenuCl", cboMenuCl);

			return "cubox/admin/MenuManage";
		} else {
			return "redirect:/login.do";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/getMenuInfo.do")
	public ModelAndView getMenuInfo(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		try { 
			Map<String, Object> detail = menuService.selectMenuInfo(param);
			if(detail != null) {
				modelAndView.addObject("detail", detail);	
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
	@RequestMapping(value="/admin/saveMenuInfo.do")
	public ModelAndView saveMenuInfo(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		param.put("regist_id", loginVO.getEsntlId());
		param.put("updt_id", loginVO.getEsntlId());
		
		param.put("menu_cd", StringUtil.nvl(param.get("hidMenuCode")));
		param.put("menu_cl_cd", StringUtil.nvl(param.get("selMenuCl")));
		param.put("menu_nm", StringUtil.nvl(param.get("txtMenuNm")));
		param.put("menu_url", StringUtil.nvl(param.get("txtMenuUrl")));
		param.put("sort_ordr", StringUtil.nvl(param.get("txtSortOrdr"), "1"));
		
		int cnt = 0;
		try {
			if(StringUtil.nvl(param.get("hidMenuCode")).equals("")) {
				cnt = menuService.insertMenuInfo(param);	
			} else {
				cnt = menuService.updateMenuInfo(param);
			}
			
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
	@RequestMapping(value="/admin/modMenuUseYn.do")
	public ModelAndView modMenuUseYn(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		param.put("updt_id", loginVO.getEsntlId());
		
		try {
			int cnt = menuService.updateMenuUseYn(param);	
			
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
	@RequestMapping(value="/admin/getMenuClList.do")
	public ModelAndView getMenuClList(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		try {
			List<Map<String, Object>> list = menuService.selectMenuClList(param);
			
			modelAndView.addObject("list", list);
			
		} catch(Exception e) {
			e.printStackTrace();
			modelAndView.addObject("message", e.getMessage());
		}

		return modelAndView;
	}	

	@ResponseBody
	@RequestMapping(value="/admin/getMenuClInfo.do")
	public ModelAndView getMenuClInfo(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		try { 
			Map<String, Object> detail = menuService.selectMenuClInfo(param);
			if(detail != null) {
				modelAndView.addObject("detail", detail);	
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
	@RequestMapping(value="/admin/saveMenuClInfo.do")
	public ModelAndView saveMenuClInfo(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		param.put("regist_id", loginVO.getEsntlId());
		param.put("updt_id", loginVO.getEsntlId());
		
		param.put("menu_cl_cd", StringUtil.nvl(param.get("hidMenuClCode")));
		param.put("menu_cl_nm", StringUtil.nvl(param.get("txtMenuClNm")));
		param.put("icon_image", StringUtil.nvl(param.get("txtIconImg"), "left_icon1.png"));
		param.put("sort_ordr", StringUtil.nvl(param.get("txtClSortOrdr"), "1"));
		
		int cnt = 0;
		try {
			if(StringUtil.nvl(param.get("hidMenuClCode")).equals("")) {
				cnt = menuService.insertMenuClInfo(param);	
			} else {
				cnt = menuService.updateMenuClInfo(param);
			}
			
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
	@RequestMapping(value="/admin/modMenuClUseYn.do")
	public ModelAndView modMenuClUseYn(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		param.put("updt_id", loginVO.getEsntlId());
		
		try {
			int cnt = menuService.updateMenuClUseYn(param);	
			
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
