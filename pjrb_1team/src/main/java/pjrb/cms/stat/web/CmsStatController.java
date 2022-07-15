package pjrb.cms.stat.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.accessip.service.CmsAccessLogService;
import pjrb.cms.excel.excelModule;
import pjrb.cms.main.web.CmsMainController;
import pjrb.cms.pagemng.service.PageMngVO;
import pjrb.cms.stat.service.CmsConstatVO;
import pjrb.cms.stat.service.CmsPagestatVO;
import pjrb.cms.stat.service.CmsStatService;
import pjrb.user.service.PjrbDefaultVO;

/**
 * 접속,페이지통계
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성 
 *  
 */

@Controller
public class CmsStatController {
		
	@Resource(name = "ajaxMainView")
	private MappingJackson2JsonView ajaxMainView;

    @Resource(name = "cmsStatService")
    private CmsStatService cmsStatService;
    
    @Resource(name = "cmsAccessLogService")
    protected CmsAccessLogService cmsAccessLogService;
    
	@RequestMapping(value = "/cms/stats/conStatList.do")
	public String constatList(@ModelAttribute("searchVO") CmsConstatVO constatVO, HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user == null){
			return "redirect:/cms/login.do?returnValue=2";
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(constatVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(constatVO.getRecordCountPerPage());
        paginationInfo.setPageSize(constatVO.getPageSize());

        constatVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        constatVO.setLastIndex(paginationInfo.getLastRecordIndex());
        constatVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        paginationInfo.setTotalRecordCount(cmsStatService.constatListCnt(constatVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		constatVO.setSearchOrder("chart");
		model.addAttribute("resultChart", cmsStatService.constatList(constatVO));
		constatVO.setSearchOrder("list");
		model.addAttribute("resultList", cmsStatService.constatList(constatVO));
		
		cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/stats/conStatList.do", "접속통계 조회", "");
		
		return "/pjrb/cms/stats/constatList";
	}
	
	@RequestMapping(value = "/cms/stats/pageStatList.do")
	public String pageStatList(@ModelAttribute("searchVO") CmsPagestatVO pagestatVO, HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user == null){
			return "redirect:/cms/login.do?returnValue=2";
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(pagestatVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(pagestatVO.getRecordCountPerPage());
        paginationInfo.setPageSize(pagestatVO.getPageSize());

        pagestatVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        pagestatVO.setLastIndex(paginationInfo.getLastRecordIndex());
        pagestatVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        paginationInfo.setTotalRecordCount(cmsStatService.pagestatListCnt(pagestatVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("resultList", cmsStatService.pagestatList(pagestatVO));
		
		cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/stats/pageStatList.do", "페이지통계 조회", "");
		
		return "/pjrb/cms/stats/pagestatList";
	}
	/*
	@RequestMapping("/cms/stats/excelDown.do")
	public ModelAndView statsExcelDown(PjrbDefaultVO defaultVO, HttpServletRequest request, Model model)throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}
			
		Map<String,Object> map = new HashMap<String,Object>();
		
		String filename = "";
		String listMapping = "";
		
		if(defaultVO.getExcelType() != null ){
			if(defaultVO.getExcelType().equals("1")){
				CmsConstatVO constatVO = new CmsConstatVO();
				constatVO.setRecordCountPerPage(-1);
				map.put("resultList", cmsStatService.constatList(constatVO));
				filename = "접속 통계";
				listMapping = "constatListMapping";
				
			}else if(defaultVO.getExcelType().equals("2")){
				CmsPagestatVO pagestatVO = new CmsPagestatVO();
				pagestatVO.setRecordCountPerPage(-1);
				map.put("resultList", cmsStatService.pagestatList(pagestatVO));
				filename = "페이지 통계";
				listMapping = "pagestatListMapping";
			}
			
			CmsMainController cmsMC = new CmsMainController();
			
		 	filename = cmsMC.FileName(filename, request);
		 	
		 	request.setAttribute("filename", filename);	
			return new ModelAndView(listMapping,"resultMap",map);
			
		}else{
			return new ModelAndView("","","");
		}
		
	}
	*/
	@RequestMapping("/cms/stats/excelDown.do")
	public ModelAndView excelDown(PjrbDefaultVO defaultVO, HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}
		
		String excelFlag = defaultVO.getExcelFlag();
		String excelType = defaultVO.getExcelType();
		String filename = "";
		
		if(excelFlag != null && !excelFlag.isEmpty()){
			if(excelType.equals("1")){
				
				CmsConstatVO vo = new CmsConstatVO();
				vo.setRecordCountPerPage(-1);
				vo.setSearchCnd(defaultVO.getSearchCnd());
				vo.setEngYn(defaultVO.getSearchEngYn());
				vo.setSearchDate(defaultVO.getSearchDate());
				vo.setStartDate(defaultVO.getSearchStartDate());
				vo.setEndDate(defaultVO.getSearchEndDate());
				
				List<?> list = cmsStatService.constatList(vo);
				String[] cellHeader = {"접속일시", "PC", "Mobile", "Total"};
				String[] row = {"parseDate", "pc", "mobile", "total"};
				String[] columnWidth = {"10000", "5000", "5000", "5000"};
				
				filename = "접속 통계";
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("excelFlag", excelFlag);
				map.put("excelType", excelType);
				map.put("excelList", list);
				map.put("excelHead", cellHeader);
				map.put("excelRow", row);
				map.put("excelWidth", columnWidth);
				map.put("filename", filename);
				
				excelModule.excel(map, request, response);
				
			}else if(excelType.equals("2")){
				
				CmsPagestatVO vo = new CmsPagestatVO();
				vo.setRecordCountPerPage(-1);
				vo.setEngYn(defaultVO.getSearchEngYn());
				vo.setSearchDate(defaultVO.getSearchDate());
				vo.setStartDate(defaultVO.getSearchStartDate());
				vo.setEndDate(defaultVO.getSearchEndDate());
				
				List<?> list = cmsStatService.pagestatList(vo);
				String[] cellHeader = {"메뉴명", "URL", "페이지뷰", "비율", "언어 구분"};
				String[] row = {"menuTitle", "url", "viewCount", "viewCount", "engYn"};
				String[] columnWidth = {"15000", "15000", "5000", "5000", "5000"};
				
				filename = "페이지 통계";
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("excelFlag", excelFlag);
				map.put("excelType", excelType);
				map.put("excelList", list);
				map.put("excelHead", cellHeader);
				map.put("excelRow", row);
				map.put("excelWidth", columnWidth);
				map.put("filename", filename);
				
				excelModule.excel(map, request, response);
				
			}
			
		 	
			return new ModelAndView(ajaxMainView);
		}else{
			return new ModelAndView("","","");
		}
		
	}
	
}