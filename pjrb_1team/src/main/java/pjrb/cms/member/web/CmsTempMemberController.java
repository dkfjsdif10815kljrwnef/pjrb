package pjrb.cms.member.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.member.service.CmsMemberService;
import pjrb.cms.member.service.CmsMemberVO;
/**
 * 회원관리 클론
 * 김주련
 * 
 */
@Controller
public class CmsTempMemberController {
	
	@Resource(name = "cmsTempMemberService")
	private CmsMemberService tempService;
	
	@RequestMapping("/cms/member/tempMemberList.do")
	public String list(@ModelAttribute("searchVO") CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user == null){
			return "redirect:/cms/login.do?returnValue=2";
		}else{
			/// pagination 설정
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(vo.getPageIndex());
			paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
			paginationInfo.setPageSize(vo.getPageSize());
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			paginationInfo.setTotalRecordCount(tempService.memberListCnt(vo));
			
			
			/// 게시판 리스트 호출
			
			model.addAttribute("resultList",tempService.memberList(vo));
			model.addAttribute("resultCnt",paginationInfo.getTotalRecordCount());
			model.addAttribute("paginationInfo",paginationInfo);
			
			return "/pjrb/cms/member/tempMember";
		}
	}

}
