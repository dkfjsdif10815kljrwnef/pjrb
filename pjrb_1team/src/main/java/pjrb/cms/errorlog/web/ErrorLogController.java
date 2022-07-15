package pjrb.cms.errorlog.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.errorlog.service.ErrorLogService;
import pjrb.cms.errorlog.service.ErrorLogVO;
import pjrb.user.service.PjrbDefaultVO;

/**
 * 에러로그 관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.18
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.18  권대성          최초 생성 
 *  
 */

@Controller
public class ErrorLogController {

	@Resource(name="errorLogService")
	private ErrorLogService errorLogService;
	
	@RequestMapping("/cms/errorlog/errorList.do")
	public String pjrbErrorLogList(@ModelAttribute("searchVO") PjrbDefaultVO vo, Model model) throws Exception{
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		paginationInfo.setPageSize(vo.getPageSize());

		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = errorLogService.selectPjrbErrorLogListCnt(vo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("resultList", errorLogService.selectPjrbErrorLogList(vo));
		model.addAttribute("paginationInfo", paginationInfo);
		return "/pjrb/cms/errorlog/errorList";
	}
	
	@RequestMapping("/cms/errorlog/errorDetail.do")
	public String pjrbErrorLogDetail(ErrorLogVO vo, Model model) throws Exception{
		
		
		model.addAttribute("result", errorLogService.selectPjrbErrorLogDetail(vo));
		return "/pjrb/cms/errorlog/errorDetail";
	}
	
	@RequestMapping("/cms/errorlog/errorDelete.do")
	public String pjrbErrorLogDelete(Model model) throws Exception{
		
		errorLogService.deletePjrbErrorLog();
		return "/pjrb/errorlog/errorList";
	}
}
