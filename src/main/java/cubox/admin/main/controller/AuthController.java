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
import cubox.admin.main.service.AuthService;
import cubox.admin.main.service.CodeService;
import cubox.admin.main.service.MenuService;
import cubox.admin.main.service.vo.LoginVO;
import cubox.admin.main.service.vo.PaginationVO;

@Controller
public class AuthController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

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
	
	@Resource(name="authService")
	private AuthService authService;

	@RequestMapping(value="/admin/authManage.do")
	public String authManage(ModelMap model, HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");

		String menuUrl = request.getServletPath();
		String menuNm = StringUtil.nvl(menuService.selectMenuNm(menuUrl));
		
		LOGGER.debug("###[authMng] param : {}", param);
		
		if (loginVO != null && loginVO.getUserId() != null && !loginVO.getUserId().equals("")) {
		
			List<Map<String, Object>> cboCntPerPage = codeService.selectCdCombo("00001"); //페이지당건수
			
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
			List<Map<String, Object>> list = authService.selectAuthList(param);
			int count = authService.selectAuthListCount(param);

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

			return "cubox/admin/AuthManage";
		} else {
			return "redirect:/login.do";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/getAuthInfo.do")
	public ModelAndView getAuthInfo(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		try { 
			Map<String, Object> auth = authService.selectAuthInfo(param);
			if(auth != null) {
				modelAndView.addObject("auth", auth);	
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
	@RequestMapping(value="/admin/saveAuthInfo.do")
	public ModelAndView saveAuthInfo(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		param.put("regist_id", loginVO.getEsntlId());
		param.put("updt_id", loginVO.getEsntlId());
		
		param.put("author_cd", StringUtil.nvl(param.get("hidAuthorCd")));
		param.put("author_nm", StringUtil.nvl(param.get("txtAuthorNm")));
		param.put("author_desc", StringUtil.nvl(param.get("txtAuthorDesc")));
		param.put("sort_ordr", StringUtil.nvl(param.get("txtSortOrdr"), "1"));
		
		int cnt = 0;
		try {
			if(StringUtil.nvl(param.get("hidAuthorCd")).equals("")) {
				cnt = authService.insertAuthInfo(param);	
			} else {
				cnt = authService.updateAuthInfo(param);
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
	@RequestMapping(value="/admin/modAuthUseYn.do")
	public ModelAndView modAuthUseYn(HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		param.put("updt_id", loginVO.getEsntlId());
		
		try {
			int cnt = authService.updateAuthUseYn(param);	
			
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
