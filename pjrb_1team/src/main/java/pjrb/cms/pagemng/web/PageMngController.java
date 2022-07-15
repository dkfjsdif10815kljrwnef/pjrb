package pjrb.cms.pagemng.web;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.accessip.service.CmsAccessLogService;
import pjrb.cms.excel.excelModule;
import pjrb.cms.pagemng.service.PageMngService;
import pjrb.cms.pagemng.service.PageMngVO;
import pjrb.cms.popup.service.CmsPopupVO;

@Controller
public class PageMngController {
	
	@Resource(name = "ajaxMainView")
	private MappingJackson2JsonView ajaxMainView;

	@Resource(name="pageMngService")
	private PageMngService pageMngService;
	
	@Resource(name = "cmsAccessLogService")
    protected CmsAccessLogService cmsAccessLogService;
	
	@RequestMapping("/cms/pageMng/pageMngList.do")
	public String pageMngList(@ModelAttribute("searchVO") PageMngVO  searchVO, HttpServletRequest request, Model  model) throws Exception{
		//PaginationInfo에 필수 정보를 넣어 준다.
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = pageMngService.selectPageMngListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt); //전체 게시물 건 수
 
		//페이징 관련 정보가 있는 PaginationInfo 객체를 모델에 반드시 넣어준다.
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("resultList", pageMngService.selectPageMngList(searchVO));
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/pageMng/pageMngList.do", "페이지 관리 조회", "");
		}
		
		return "pjrb/cms/pageMng/pageMngList";
	}
	
	@RequestMapping("/cms/pageMng/pageMngForm.do")
	public String pageMngForm(@ModelAttribute("searchVO") PageMngVO  pageMngVO, HttpServletRequest request, Model  model) throws Exception{
		
		if(pageMngVO != null && pageMngVO.getPageId() != null && !pageMngVO.getPageId().isEmpty()){
			model.addAttribute("result", pageMngService.selectPageMngDetail(pageMngVO));
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/pageMng/pageMngList.do", "페이지 관리 상세조회", "");
		}
		
		return "pjrb/cms/pageMng/pageMngForm";
	}
	
	@RequestMapping("/cms/pageMng/pageMngSave.do")
	public String pageMngSave(@ModelAttribute("searchVO") PageMngVO  pageMngVO, HttpServletRequest request, Model  model) throws Exception{
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		if(pageMngVO != null && pageMngVO.getPageId() != null && !pageMngVO.getPageId().isEmpty()){
			pageMngVO.setMname(user.getUserNm());
			pageMngService.updatePageMng(pageMngVO);
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/pageMng/pageMngSave.do", "페이지 관리 수정", "");
			}
		}else{
			pageMngVO.setCname(user.getUserNm());
			pageMngService.insertPageMng(pageMngVO);
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/pageMng/pageMngSave.do", "페이지 관리 등록", "");
			}
		}
		
		return "redirect:/cms/pageMng/pageMngList.do";
	}
	
	@RequestMapping("/cms/pageMng/pageMngDelete.do")
	public String pageMngDelete(@ModelAttribute("searchVO") PageMngVO  pageMngVO, HttpServletRequest request, Model  model) throws Exception{
		
		pageMngService.deletePageMng(pageMngVO);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/pageMng/pageMngDelete.do", "페이지 관리 삭제", "");
		}
		
		return "redirect:/cms/pageMng/pageMngList.do";
	}
	
	
	@RequestMapping("/cms/pageMng/pageMngUpdateUseYn.do")
	public ModelAndView pageMngUpdateUseYn(@ModelAttribute("searchVO") PageMngVO  pageMngVO, HttpServletRequest request, Model  model) throws Exception{
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		pageMngVO.setMname(user.getUserNm());
		pageMngService.updatePageMngUseYn(pageMngVO);
		
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/pageMng/pageMngUpdateUseYn.do", "페이지 관리 상태 수정", "");
		}
		
		return new ModelAndView("ajaxMainView");
	}
	
	/*
	@RequestMapping("/cms/pageMng/pageMngExcelDown.do")
	public ModelAndView pageMngExcelDown(@ModelAttribute("searchVO") PageMngVO  pageMngVO, HttpServletRequest request, Model  model) throws Exception{
		
		pageMngVO.setPageUnit(9999999);
		pageMngVO.setPageIndex(1);
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(pageMngVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(pageMngVO.getPageUnit());
		paginationInfo.setPageSize(pageMngVO.getPageSize());

		pageMngVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pageMngVO.setLastIndex(paginationInfo.getLastRecordIndex());
		pageMngVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = pageMngService.selectPageMngListCnt(pageMngVO);
		paginationInfo.setTotalRecordCount(totCnt); //전체 게시물 건 수

		String filename = "페이지관리";
		Date date = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
		String now = dt.format(date);
		String header = request.getHeader( "User-Agent" );
		filename += "_"+now;
		
		if ( header.indexOf( "MSIE" ) > -1 ) {
			filename = URLEncoder.encode( filename, "UTF-8" ).replaceAll( "\\+", "%20" );
		}else if ( header.indexOf( "Trident" ) > -1 ) { 
			filename = URLEncoder.encode( filename, "UTF-8" ).replaceAll( "\\+", "%20" );
		}else if ( header.indexOf( "Chrome" ) > -1 ) {
			StringBuffer sb = new StringBuffer();
			for ( int i = 0; i < filename.length(); i++ ) {
				char c = filename.charAt( i );
				if ( c > '~' ) {
					sb.append( URLEncoder.encode( "" + c, "UTF-8" ) );
				}else {
					sb.append( c );
				}
			}
			filename = sb.toString();
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("resultList", pageMngService.selectPageMngList(pageMngVO));
		resultMap.put("paginationInfo", paginationInfo);
		request.setAttribute("filename", filename); 
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/pageMng/pageMngExcelDown.do", "페이지 관리 엑셀다운", "");
		}
		
		return new ModelAndView("pageMngExcelDown","resultMap",resultMap);
	}*/
	
	@RequestMapping("/cms/pageMng/excelDown.do")
	public ModelAndView excelDown(PageMngVO vo, HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}
		
		String excelFlag = vo.getExcelFlag();
		String excelType = vo.getExcelType();
		String filename = "";
		
		if(excelFlag != null && !excelFlag.isEmpty()){
			vo.setRecordCountPerPage(9999999);
			vo.setFirstIndex(0);
			
			List<?> list = pageMngService.selectPageMngList(vo);
			String[] cellHeader = {"번호", "페이지명", "생성일"};
			String[] row = {"@", "pageNm", "cdate"};
			String[] columnWidth = {"4000", "10000", "5000"};
			
			filename = "페이지 관리";
			
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
