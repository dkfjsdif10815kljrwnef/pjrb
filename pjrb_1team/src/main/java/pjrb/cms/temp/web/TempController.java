package pjrb.cms.temp.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.popup.service.CmsPopupVO;
import pjrb.cms.temp.service.TempPopupService;

@Controller
public class TempController {
	
	@Resource(name="TempPopupService")
	private TempPopupService service;
	
	@RequestMapping("/temp/index.do")
	public String tempIndex() {
		return "/pjrb/temp/index";
	}
	@RequestMapping("/temp/popupList.do")
	public String tempPopupList(@ModelAttribute("searchVO") CmsPopupVO vo, ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		paginationInfo.setPageSize(vo.getPageSize());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/// 게시판 total cnt
		paginationInfo.setTotalRecordCount( service.popupListCnt(vo) );
		
		/// addAttribute
		model.addAttribute("resultList", service.popupList(vo) );
		model.addAttribute("resultCnt", paginationInfo.getTotalRecordCount() );
		model.addAttribute("paginationInfo", paginationInfo);
		
		
		return "/pjrb/temp/file/popupList";
	}
	
	@RequestMapping("/temp/popupWrite.do")
	public String tempPopupWrite(@ModelAttribute("searchVO") CmsPopupVO cmsMemberVO, ModelMap model) {
		return "/pjrb/temp/file/popupWrite";
	}
	
	@RequestMapping("/temp/popupModify.do")
	public String tempPopupModify(@ModelAttribute("searchVO") CmsPopupVO vo, ModelMap model) throws Exception {
		model.addAttribute("result", service.popupView(vo) );
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		return "/pjrb/temp/file/popupWrite";
	}
	@RequestMapping("/temp/popupChkDel.do")
	public String tempPopupChkDel(@ModelAttribute("searchVO") CmsPopupVO vo, ModelMap model) throws Exception {
		model.addAttribute("result", service.popupView(vo) );
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		return "/pjrb/temp/file/popupWrite";
	}

	@RequestMapping("/temp/download/bbsSelectFile.do")
	public String tempFileupload() {
		return "/pjrb/temp/file/fileupload";
	}


	
	
}
