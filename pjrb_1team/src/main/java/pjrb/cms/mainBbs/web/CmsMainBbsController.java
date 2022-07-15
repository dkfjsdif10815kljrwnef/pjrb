package pjrb.cms.mainBbs.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.accessip.service.CmsAccessLogService;
import pjrb.cms.bbs.service.CmsBbsVO;
import pjrb.cms.mainBbs.service.CmsMainBbsService;
import pjrb.cms.mainBbs.service.CmsMainBbsVO;

/**
 * 메인 게시판 관리
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
public class CmsMainBbsController {
		
	@Resource(name = "ajaxMainView")
	private MappingJackson2JsonView ajaxMainView;
	
    @Resource(name = "cmsMainBbsService")
    private CmsMainBbsService cmsMainBbsService;
    
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    @Resource(name = "cmsAccessLogService")
    protected CmsAccessLogService cmsAccessLogService;
	
	@RequestMapping("/cms/mainBbs/mainBbsList.do")
    public String list(@ModelAttribute("searchVO") CmsMainBbsVO vo, HttpServletRequest request, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user == null){
			return "redirect:/cms/login.do?returnValue=2";
		}else{
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(vo.getPageIndex());
			paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
			paginationInfo.setPageSize(vo.getPageSize());

			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			int totCnt = cmsMainBbsService.mainBbsListCnt(vo);
			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", cmsMainBbsService.mainBbsList(vo));
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("paginationInfo", paginationInfo);

			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/mainBbs/mainBbsList.do", "메인 게시판 관리 조회", "");
		
			return "/pjrb/cms/mainBbs/mainBbsList";
		}
    }
	
	@RequestMapping("/cms/mainBbs/mainBbsInsert.do")
	public String insert(@ModelAttribute("searchVO") CmsMainBbsVO vo, HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttrs) throws Exception {
	    
		cmsMainBbsService.mainBbsInsert(vo);
		redirectAttrs.addFlashAttribute("resultMsg","저장되었습니다");
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/mainBbs/mainBbsInsert.do", "메인 게시판 등록", "");
		}
		
		return "redirect:/cms/mainBbs/mainBbsList.do?searchType="+vo.getSearchType();
	}
	
	@RequestMapping("/cms/mainBbs/mainBbsUpdate.do")
	public String update(@ModelAttribute("searchVO") CmsMainBbsVO vo, HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttrs) throws Exception {
				
		cmsMainBbsService.mainBbsUpdate(vo);
		redirectAttrs.addFlashAttribute("resultMsg","저장되었습니다");
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/mainBbs/mainBbsUpdate.do", "메인 게시판 수정", "");
		}
		
		return "redirect:/cms/mainBbs/mainBbsList.do?searchType="+vo.getSearchType();
	}
	
	@RequestMapping("/cms/mainBbs/mainBbsDelete.do")
	public String delete(@ModelAttribute("searchVO") CmsMainBbsVO vo, HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttrs) throws Exception {
				
		cmsMainBbsService.mainBbsDelete(vo);
		redirectAttrs.addFlashAttribute("resultMsg","삭제되었습니다.");
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/mainBbs/mainBbsDelete.do", "메인 게시판 삭제", "");
		}
		
		return "redirect:/cms/mainBbs/mainBbsList.do?searchType="+vo.getSearchType();
	}
	
	@RequestMapping("/cms/mainBbs/mainBbsListPopup.do")
	public String mainBbsListPopup(@ModelAttribute("searchVO") CmsBbsVO vo, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null)
			return "redirect:/cms/login.do?returnValue=2";

		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		paginationInfo.setPageSize(vo.getPageSize());

		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = 0;
		if(vo.getSearchFlag()!=null && !vo.getSearchFlag().isEmpty()) {
			if(vo.getSearchFlag().equals("1")) {
				totCnt = cmsMainBbsService.mainBbsModalListCnt(vo);
				model.addAttribute("resultList", cmsMainBbsService.mainBbsModalList(vo));
			}else {
				totCnt = cmsMainBbsService.mainBbsContentsModalListCnt(vo);
				model.addAttribute("resultList", cmsMainBbsService.mainBbsContentsModalList(vo));
			}
		}

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "/pjrb/cms/mainBbs/mainBbsListModalView";
	}
	
}