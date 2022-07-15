package pjrb.cms.accessip.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.accessip.service.CmsAccessLogVO;
import pjrb.cms.accessip.service.CmsAccessLogService;

/**
 * 접근로그 관리
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
public class CmsAccessLogController {

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
    @Resource(name = "cmsAccessLogService")
    protected CmsAccessLogService cmsAccessLogService;
    
	@RequestMapping(value = "/cms/accesslog/accessLogList.do")
    public String accessIPList(@ModelAttribute("searchVO") CmsAccessLogVO aLogVO, HttpServletRequest request, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user == null)
			return "redirect:/cms/login.do?returnValue=2";
			
		PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(aLogVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(aLogVO.getRecordCountPerPage());
        paginationInfo.setPageSize(aLogVO.getPageSize());

        aLogVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        aLogVO.setLastIndex(paginationInfo.getLastRecordIndex());
        aLogVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        paginationInfo.setTotalRecordCount(cmsAccessLogService.selectAccessLogListCnt(aLogVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		List<?> resultList = cmsAccessLogService.selectAccessLogList(aLogVO);
		model.addAttribute("logList",resultList);
		
		cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/accesslog/accessLogList.do", "접속 로그 조회","");
		
		return "/pjrb/cms/accesslog/accessLogList";
    }
	
	@RequestMapping(value = "/cms/accesslog/accessActLogList.do")
    public String accessActLogList(@ModelAttribute("searchVO") CmsAccessLogVO aLogVO, HttpServletRequest request, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user == null)
			return "redirect:/cms/login.do?returnValue=2";
			
		PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(aLogVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(aLogVO.getRecordCountPerPage());
        paginationInfo.setPageSize(aLogVO.getPageSize());

        aLogVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        aLogVO.setLastIndex(paginationInfo.getLastRecordIndex());
        aLogVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        paginationInfo.setTotalRecordCount(cmsAccessLogService.selectAccessActLogListCnt(aLogVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		List<?> resultList = cmsAccessLogService.selectAccessActLogList(aLogVO);
		model.addAttribute("logList",resultList);
		
		cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/accesslog/accessActLogList.do", "접근 로그 조회","");
		
		return "/pjrb/cms/accesslog/accessActLogList";
    }
}
