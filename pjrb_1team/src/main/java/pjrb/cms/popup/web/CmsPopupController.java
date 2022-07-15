package pjrb.cms.popup.web;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.accessip.service.CmsAccessLogService;
import pjrb.cms.excel.excelModule;
import pjrb.cms.popup.service.CmsPopupService;
import pjrb.cms.popup.service.CmsPopupVO;

/**
 * 팝업관리
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
public class CmsPopupController {
		
	@Resource(name = "ajaxMainView")
	private MappingJackson2JsonView ajaxMainView;
	
    @Resource(name = "cmsPopupService")
    private CmsPopupService cmsPopupService;
    
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    @Resource(name = "cmsAccessLogService")
    protected CmsAccessLogService cmsAccessLogService;
    
	@RequestMapping("/cms/popup/popupList.do")
	public String list(@ModelAttribute("searchVO") CmsPopupVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
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

			int totCnt = cmsPopupService.popupListCnt(vo);
			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", cmsPopupService.popupList(vo));
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("paginationInfo", paginationInfo);

			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/popup/popupList.do", "팝업 관리 조회", "");
			
			return "/pjrb/cms/popup/popupList";
		}
	}
	
	@RequestMapping("/cms/popup/popupWrite.do")
	public String write(@ModelAttribute("searchVO") CmsPopupVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/popup/popupWrite.do", "팝업 등록 화면", "");
		}
		
		return "/pjrb/cms/popup/popupWrite";
	}
	
	@RequestMapping("/cms/popup/popupInsert.do")
	public ModelAndView insert(@ModelAttribute("searchVO") CmsPopupVO vo, final MultipartHttpServletRequest multiRequest, HttpServletRequest request, ModelMap model) throws Exception {
			
		List<FileVO> result = null;
	    String atchFileId = "";
	    
	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "POPUP_", 0, "", "POPUP");
			atchFileId = fileMngService.insertFileInfs(result);
	    }
	    vo.setAtchFileId(atchFileId);
	    
		cmsPopupService.popupInsert(vo);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/popup/popupInsert.do", "팝업 등록", atchFileId);
		}
		
		return new ModelAndView(ajaxMainView);
	}
	
	@RequestMapping("/cms/popup/popupModify.do")
	public String modify(@ModelAttribute("searchVO") CmsPopupVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
		model.addAttribute("result", cmsPopupService.popupView(vo));
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/popup/popupModify.do", "팝업 상세 조회", "");
		}
		
		return "/pjrb/cms/popup/popupWrite";
	}
	
	@RequestMapping("/cms/popup/popupUpdate.do")
	public ModelAndView update(@ModelAttribute("searchVO") CmsPopupVO vo, final MultipartHttpServletRequest multiRequest, HttpServletRequest request, ModelMap model) throws Exception {
				
		String atchFileId = vo.getAtchFileId();
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if (!files.isEmpty()) {
			if ("".equals(atchFileId)) {
			    List<FileVO> result = fileUtil.parseFileInf(files, "POPUP_", 0, atchFileId, "POPUP");
			    atchFileId = fileMngService.insertFileInfs(result);
			    vo.setAtchFileId(atchFileId);
			} else {
			    FileVO fvo = new FileVO();
			    fvo.setAtchFileId(atchFileId);
			    int cnt = fileMngService.getMaxFileSN(fvo);
			    List<FileVO> _result = fileUtil.parseFileInf(files, "POPUP_", cnt, atchFileId, "POPUP");
			    fileMngService.updateFileInfs(_result);
			}
	    }
	    
		cmsPopupService.popupUpdate(vo);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/popup/popupUpdate.do", "팝업 수정", atchFileId);
		}
		
		return new ModelAndView(ajaxMainView);
		
	}
	
	@RequestMapping("/cms/popup/popupDelete.do")
	public String delete(@ModelAttribute("searchVO") CmsPopupVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
		cmsPopupService.popupDelete(vo);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/popup/popupDelete.do", "팝업 삭제", "");
		}
		
		return "redirect:/cms/popup/popupList.do";
	}
	
	@RequestMapping("/cms/popup/popupChkDel.do")
    public ModelAndView popupChkDel(CmsPopupVO vo, HttpServletRequest request, ModelMap model) throws Exception {
	
		String[] seqList = (vo.getSeqList()).split(",");
		
		for(int i=0; i<seqList.length; i++){
			CmsPopupVO popupVO = new CmsPopupVO();
			popupVO.setSeq(seqList[i]);
			
			cmsPopupService.popupDelete(popupVO);
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/popup/popupChkDel.do", "팝업 선택 삭제", "");
		}
		
		return new ModelAndView(ajaxMainView);
    }
	
	@RequestMapping("/cms/popup/popupStatusUpdate.do")
    public ModelAndView popupStatusUpdate(CmsPopupVO vo, HttpServletRequest request, ModelMap model) throws Exception {
	
		cmsPopupService.popupStatusUpdate(vo);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/popup/popupStatusUpdate.do", "팝업 상태 수정", "");
		}
		
		return new ModelAndView(ajaxMainView);
    }
	/*
	@RequestMapping("/cms/popup/excelDown.do")
	public ModelAndView popupExcelDown(PjrbDefaultVO defaultVO, HttpServletRequest request, Model model)throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}
			
		Map<String,Object> map = new HashMap<String,Object>();
		
		String excelFlag = defaultVO.getExcelFlag();
		String filename = "";
		String listMapping = "";
		
		if(excelFlag != null && !excelFlag.isEmpty()){
			CmsPopupVO vo = new CmsPopupVO();
			vo.setRecordCountPerPage(-1);
			map.put("resultList", cmsPopupService.popupList(vo));
			filename = "팝업 목록";
			listMapping = "popupListMapping";
			
			CmsMainController cmsMC = new CmsMainController();
			
		 	filename = cmsMC.FileName(filename, request);
		 	
		 	request.setAttribute("filename", filename);	
			return new ModelAndView(listMapping,"resultMap",map);
			
		}else{
			return new ModelAndView("","","");
		}
		
	}*/
	@RequestMapping("/cms/popup/excelDown.do")
	public ModelAndView excelDown(CmsPopupVO vo, HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}
		
		String excelFlag = vo.getExcelFlag();
		String excelType = vo.getExcelType();
		String filename = "";
		
		if(excelFlag != null && !excelFlag.isEmpty()){
			vo.setRecordCountPerPage(-1);
			
			List<?> list = cmsPopupService.popupList(vo);
			String[] cellHeader = {"팝업 제목", "링크 주소", "기간", "사용유무", "등록일"};
			String[] row = {"title", "address", "startDate", "status", "regDate"};
			String[] columnWidth = {"5000", "10000", "10000", "5000", "5000"};
			
			filename = "팝업 목록";

			Map<String,Object> map = new HashMap<String,Object>();
			map.put("excelFlag", excelFlag);
			map.put("excelType", excelType);
			map.put("excelList", list);
			map.put("excelHead", cellHeader);
			map.put("excelRow", row);
			map.put("excelWidth", columnWidth);
			map.put("filename", filename);
			
			excelModule.excel(map, request, response);
		 	
			return new ModelAndView(ajaxMainView);
		}else{
			return new ModelAndView("","","");
		}
		
	}
	
}