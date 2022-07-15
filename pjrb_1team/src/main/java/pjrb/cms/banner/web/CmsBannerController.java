package pjrb.cms.banner.web;

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
import pjrb.cms.banner.service.CmsBannerService;
import pjrb.cms.banner.service.CmsBannerVO;
import pjrb.cms.excel.excelModule;

/**
 * 배너 및 관련사이트 관리
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
public class CmsBannerController {
		
	@Resource(name = "ajaxMainView")
	private MappingJackson2JsonView ajaxMainView;
	
    @Resource(name = "cmsBannerService")
    private CmsBannerService cmsBannerService;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
    
    @Resource(name = "cmsAccessLogService")
    protected CmsAccessLogService cmsAccessLogService;
    
	@RequestMapping("/cms/banner/bannerList.do")
	public String list(@ModelAttribute("searchVO") CmsBannerVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
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

			int totCnt = cmsBannerService.bannerListCnt(vo);
			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", cmsBannerService.bannerList(vo));
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			
			if(vo.getPurpose().equals("1"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerList.do", "배너 관리 조회","");
			else if(vo.getPurpose().equals("2"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerList.do", "관련사이트 관리 조회","");
			
			return "/pjrb/cms/banner/bannerList";
		}
	}
	
	@RequestMapping("/cms/banner/bannerWrite.do")
	public String write(@ModelAttribute("searchVO") CmsBannerVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		if(user != null){
			if(vo.getPurpose().equals("1"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerWrite.do", "배너 등록 화면","");
			else if(vo.getPurpose().equals("2"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerWrite.do", "관련사이트 등록 화면","");
		}
		
		return "/pjrb/cms/banner/bannerWrite";
	}
	
	@RequestMapping("/cms/banner/bannerInsert.do")
	public ModelAndView insert(@ModelAttribute("searchVO") CmsBannerVO vo, final MultipartHttpServletRequest multiRequest, HttpServletRequest request, ModelMap model) throws Exception {
			
		List<FileVO> result = null;
	    String atchFileId = "";
	    
	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "BANNER_", 0, "", "BANNER");
			atchFileId = fileMngService.insertFileInfs(result);
	    }
	    vo.setAtchFileId(atchFileId);
	    
	    cmsBannerService.bannerInsert(vo);
	    
	    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		if(user != null){
			if(vo.getPurpose().equals("1"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerInsert.do", "배너 등록",atchFileId);
			else if(vo.getPurpose().equals("2"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerInsert.do", "관련사이트 등록",atchFileId);
		}
		
	    return new ModelAndView(ajaxMainView);
	}
	
	@RequestMapping("/cms/banner/bannerModify.do")
	public String modify(@ModelAttribute("searchVO") CmsBannerVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
		model.addAttribute("result", cmsBannerService.bannerView(vo));
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		if(user != null){
			if(vo.getPurpose().equals("1"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerModify.do", "배너 상세 조회", "");
			else if(vo.getPurpose().equals("2"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerModify.do", "관련사이트 상세 조회", "");
		}
		
		return "/pjrb/cms/banner/bannerWrite";
	}
	
	@RequestMapping("/cms/banner/bannerUpdate.do")
	public ModelAndView update(@ModelAttribute("searchVO") CmsBannerVO vo, final MultipartHttpServletRequest multiRequest, HttpServletRequest request, ModelMap model) throws Exception {
				
		String atchFileId = vo.getAtchFileId();
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if (!files.isEmpty()) {
			if ("".equals(atchFileId)) {
			    List<FileVO> result = fileUtil.parseFileInf(files, "BANNER_", 0, atchFileId, "BANNER");
			    atchFileId = fileMngService.insertFileInfs(result);
			    vo.setAtchFileId(atchFileId);
			} else {
			    FileVO fvo = new FileVO();
			    fvo.setAtchFileId(atchFileId);
			    int cnt = fileMngService.getMaxFileSN(fvo);
			    List<FileVO> _result = fileUtil.parseFileInf(files, "BANNER_", cnt, atchFileId, "BANNER");
			    fileMngService.updateFileInfs(_result);
			}
	    }
	    
	    cmsBannerService.bannerUpdate(vo);
	    
	    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		if(user != null){
			if(vo.getPurpose().equals("1"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerUpdate.do", "배너 수정", atchFileId);
			else if(vo.getPurpose().equals("2"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerUpdate.do", "관련사이트 수정", atchFileId);
		}
		
	    return new ModelAndView(ajaxMainView);
	}
	
	@RequestMapping("/cms/banner/bannerDelete.do")
	public String delete(@ModelAttribute("searchVO") CmsBannerVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
		cmsBannerService.bannerDelete(vo);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		if(user != null){
			if(vo.getPurpose().equals("1"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerDelete.do", "배너 삭제", "");
			else if(vo.getPurpose().equals("2"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerDelete.do", "관련사이트 삭제", "");
		}
		
		return "redirect:/cms/banner/bannerList.do";
	}
	
	@RequestMapping("/cms/banner/bannerChkDel.do")
    public ModelAndView bannerChkDel(CmsBannerVO vo, HttpServletRequest request, ModelMap model) throws Exception {
	
		String[] seqList = (vo.getSeqList()).split(",");
		
		for(int i=0; i<seqList.length; i++){
			CmsBannerVO bannerVO = new CmsBannerVO();
			bannerVO.setSeq(seqList[i]);
			
			cmsBannerService.bannerDelete(bannerVO);
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		if(user != null){
			if(vo.getPurpose().equals("1"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerChkDel.do", "배너 선택 삭제", "");
			else if(vo.getPurpose().equals("2"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerChkDel.do", "관련사이트 선택 삭제", "");
		}
		
		return new ModelAndView(ajaxMainView);
    }
	
	@RequestMapping("/cms/banner/bannerStatusUpdate.do")
    public ModelAndView bannerStatusUpdate(CmsBannerVO vo, HttpServletRequest request, ModelMap model) throws Exception {
	
		cmsBannerService.bannerStatusUpdate(vo);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		if(user != null){
			if(vo.getPurpose().equals("1"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerStatusUpdate.do", "배너 상태 수정", "");
			else if(vo.getPurpose().equals("2"))
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/banner/bannerStatusUpdate.do", "관련사이트 상태 수정", "");
		}
		
		return new ModelAndView(ajaxMainView);
    }
	/*
	@RequestMapping("/cms/banner/excelDown.do")
	public ModelAndView bannerExcelDown(PjrbDefaultVO defaultVO, HttpServletRequest request, Model model)throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}
			
		Map<String,Object> map = new HashMap<String,Object>();
		
		String excelFlag = defaultVO.getExcelFlag();
		String filename = "";
		String listMapping = "";
		
		if(excelFlag != null && !excelFlag.isEmpty()){
			
			CmsBannerVO vo = new CmsBannerVO();
			vo.setRecordCountPerPage(-1);
			String nm = "배너";
			vo.setPurpose("1");
			if(defaultVO.getExcelType().equals("2")){
				vo.setPurpose("2");
				nm = "관련사이트";
			}
			String excelType = defaultVO.getExcelType();
			
			map.put("excelType", excelType);
			map.put("resultList", cmsBannerService.bannerList(vo));
			
			filename = nm+" 목록";
			listMapping = "bannerListMapping";
			
			CmsMainController cmsMC = new CmsMainController();
			
		 	filename = cmsMC.FileName(filename, request);
		 	
		 	request.setAttribute("filename", filename);	
			return new ModelAndView(listMapping,"resultMap",map);
			
		}else{
			return new ModelAndView("","","");
		}
		
	}
	*/
	@RequestMapping("/cms/banner/excelDown.do")
	public ModelAndView excelDown(CmsBannerVO vo, HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}
		
		String excelFlag = vo.getExcelFlag();
		String excelType = vo.getExcelType();
		String filename = "";
		
		if(excelFlag != null && !excelFlag.isEmpty()){
			vo.setRecordCountPerPage(-1);
			
			if(excelType.equals("1")){
				vo.setPurpose("1");
				
				List<?> list = cmsBannerService.bannerList(vo);
				String[] cellHeader = {"기관명", "설명", "주소", "사용유무", "등록일"};
				String[] row = {"title", "bannerDetail", "detail", "status", "regDate"};
				String[] columnWidth = {"5000", "10000", "10000", "5000", "5000"};
				
				filename = "배너 목록";
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("excelFlag", excelFlag);
				map.put("excelType", excelType);
				map.put("excelList", list);
				map.put("excelHead", cellHeader);
				map.put("excelRow", row);
				map.put("excelWidth", columnWidth);
				map.put("filename", filename);
				
				excelModule.excel(map, request, response);
				
			}else if(excelType.equals("2")) {
				vo.setPurpose("2");
				
				List<?> list = cmsBannerService.bannerList(vo);
				String[] cellHeader = {"기관명", "주소", "언어 구분", "사용유무", "등록일"};
				String[] row = {"title", "detail", "engYn", "status", "regDate"};
				String[] columnWidth = {"5000", "10000", "5000", "5000", "5000"};
				
				filename = "관련사이트 목록";
				
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