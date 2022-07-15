package pjrb.cms.grade.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.accessip.service.CmsAccessLogService;
import pjrb.cms.grade.service.CmsGradeInfoVO;
import pjrb.cms.grade.service.CmsGradeService;
import pjrb.cms.grade.service.CmsGradeVO;

/**
 * 권한관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.16
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.16  권대성          최초 생성 
 *  
 */

@Controller
public class CmsGradeController {
		
	@Resource(name = "ajaxMainView")
	private MappingJackson2JsonView ajaxMainView;
	
    @Resource(name = "cmsGradeService")
    private CmsGradeService cmsGradeService;
    
    @Resource(name = "cmsAccessLogService")
    protected CmsAccessLogService cmsAccessLogService;
    
    @RequestMapping("/cms/grade/gradeList.do")
	public String list(@ModelAttribute("searchVO") CmsGradeVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		if(user == null){
			return "redirect:/cms/login.do?returnValue=2";
		}else{
			
			model.addAttribute("resultList", cmsGradeService.gradeList(vo));
			
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/grade/gradeList.do", "권한 관리 조회", "");

			return "/pjrb/cms/grade/gradeList";
		}
	}
	
	@RequestMapping("/cms/grade/gradeView.do")
	public String view(CmsGradeVO vo,CmsGradeInfoVO infoVo, HttpServletRequest request, ModelMap model) throws Exception {
				
		model.addAttribute("authDetail", cmsGradeService.gradeView(vo));
		model.addAttribute("detail", cmsGradeService.gradeInfoList(infoVo));
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/grade/gradeView.do", "권한 상세 조회", "");
		}
		
		return "/pjrb/cms/grade/gradeDetail";
	}
	
	@RequestMapping("/cms/grade/gradeUpdate.do")
	public String update(CmsGradeVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		
		cmsGradeService.gradeUpdate(vo);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/grade/gradeUpdate.do", "권한 수정", "");
		}
		
		return "redirect:/cms/grade/gradeList.do?grade="+vo.getGrade();
	}

	@RequestMapping("/cms/grade/gradeInfo.do")
	public String gradeInfoList(@ModelAttribute("searchVO") CmsGradeInfoVO vo, HttpServletRequest request, ModelMap model) throws Exception{
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		model.addAttribute("resultList", cmsGradeService.gradeInfoList(vo));
		
		cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/grade/gradeInfoList.do", "권한 설정 조회", "");
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
        paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
        paginationInfo.setPageSize(vo.getPageSize());

        vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
        vo.setLastIndex(paginationInfo.getLastRecordIndex());
        vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        int totCnt = cmsGradeService.gradeInfoListCnt(vo);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/pjrb/cms/grade/gradeInfoList";
		
	}
	
	@RequestMapping(value = "/cms/grade/gradeInfoInsert.do")
    public String gradeInfoInsert(CmsGradeInfoVO vo, ModelMap model, RedirectAttributes redirectAttrs) throws Exception {
		
		cmsGradeService.gradeInfoInsert(vo);
		redirectAttrs.addFlashAttribute("resultMsg","저장되었습니다");
		
    	return "redirect:/cms/grade/gradeInfo.do";
	}

	@RequestMapping(value = "/cms/grade/gradeInfoUpdate.do")
    public String gradeInfoUpdate(CmsGradeInfoVO vo, ModelMap model, RedirectAttributes redirectAttrs) throws Exception {
		
		cmsGradeService.gradeInfoUpdate(vo);
		redirectAttrs.addFlashAttribute("resultMsg","수정되었습니다");
		
		return "redirect:/cms/grade/gradeInfo.do";
	}
	
	
	@RequestMapping(value = "/cms/grade/gradeInfoDelete.do")
    public String gradeInfoDelete(CmsGradeInfoVO vo, ModelMap model, RedirectAttributes redirectAttrs) throws Exception {
		
		
		cmsGradeService.gradeInfoDelete(vo);
		redirectAttrs.addFlashAttribute("resultMsg","삭제되었습니다");
		
		return "redirect:/cms/grade/gradeInfo.do";
	}
}