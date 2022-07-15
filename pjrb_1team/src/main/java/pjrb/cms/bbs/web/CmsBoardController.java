package pjrb.cms.bbs.web;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.accessip.service.CmsAccessLogService;
import pjrb.cms.bbs.service.CmsBbsCommentVO;
import pjrb.cms.bbs.service.CmsBbsMstVO;
import pjrb.cms.bbs.service.CmsBbsVO;
import pjrb.cms.bbs.service.CmsBoardService;
import pjrb.cms.excel.excelModule;
import pjrb.cms.login.service.LoginService;

@Controller
public class CmsBoardController {
	
	@Resource(name = "ajaxMainView")
	private MappingJackson2JsonView ajaxMainView;
		
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    @Resource(name = "cmsBoardService")
    private CmsBoardService cmsBoardService;
    
    @Resource(name = "cmsAccessLogService")
    protected CmsAccessLogService cmsAccessLogService;

	@RequestMapping("/cms/bbsmng/mngList.do")
	public String list(@ModelAttribute("searchVO") CmsBbsMstVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return "redirect:/cms/login.do?returnValue=2";
		}else{
			vo.setRegId(user.getEmplyrId());

			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(vo.getPageIndex());
			paginationInfo.setRecordCountPerPage(vo.getPageUnit());
			paginationInfo.setPageSize(vo.getPageSize());

			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			int totCnt = cmsBoardService.mngListCnt(vo);
			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", cmsBoardService.mngList(vo));
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("paginationInfo", paginationInfo);

			String userIP = request.getRemoteAddr();
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbsmng/mngList.do", "게시판 관리 조회", "");
			}
			
			return "pjrb/cms/bbsmng/mngList";
		}
	}
	/*
	@RequestMapping("/cms/bbsmng/mngListExcelDown.do")
	public ModelAndView mngListExcelDown(@ModelAttribute("searchVO") CmsBbsMstVO vo,HttpServletRequest request, ModelMap model) throws Exception {
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}else{
			vo.setRegId(user.getEmplyrId());

			vo.setPageUnit(99999999);
			vo.setPageIndex(1);
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(vo.getPageIndex());
			paginationInfo.setRecordCountPerPage(vo.getPageUnit());
			paginationInfo.setPageSize(vo.getPageSize());

			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			int totCnt = cmsBoardService.mngListCnt(vo);
			paginationInfo.setTotalRecordCount(totCnt);

			String filename = "게시판관리";
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
			resultMap.put("paginationInfo", paginationInfo);
			resultMap.put("resultList", cmsBoardService.mngList(vo));
			request.setAttribute("filename", filename); 
			
			String userIP = request.getRemoteAddr();
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbsmng/mngListExcelDown.do", "게시판 관리 엑셀 다운", "");
			}
			
			return new ModelAndView("bbsMngExcelDown","resultMap",resultMap);
		}
	}
	*/
	@RequestMapping("/cms/bbsmng/excelDown.do")
	public ModelAndView bbsmngExcelDown(CmsBbsMstVO vo, HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}
		
		String excelFlag = vo.getExcelFlag();
		String excelType = vo.getExcelType();
		String filename = "";
		
		if(excelFlag != null && !excelFlag.isEmpty()){
			vo.setRecordCountPerPage(-1);
			
			List<?> list = cmsBoardService.mngList(vo);
			String[] cellHeader = {"번호", "게시판명", "게시판유형","생성일"};
			String[] row = {"@", "bbsNm", "bbsTp", "regDate"};
			String[] columnWidth = {"4000", "10000", "10000", "5000"};
			
			filename = "게시판 관리";
			
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
	
	@RequestMapping("/cms/bbsmng/mngWrite.do")
	public String write(@ModelAttribute("searchVO") CmsBbsMstVO vo, HttpServletRequest request, ModelMap model) throws Exception {
			
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbsmng/mngWrite.do", "게시판 관리 등록 화면", "");
		}
		
		return "pjrb/cms/bbsmng/mngWrite";
	}
	
	@RequestMapping("/cms/bbsmng/mngInsert.do")
	public String insert(@ModelAttribute("searchVO") CmsBbsMstVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setRegId(user.getEmplyrId());
		cmsBoardService.insertBbsMaster(vo);
		
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbsmng/mngInsert.do", "게시판 관리 등록", "");
		}
		
		return "redirect:/cms/bbsmng/mngList.do";
	}
	
	@RequestMapping("/cms/bbsmng/mngModify.do")
	public String modify(@ModelAttribute("searchVO") CmsBbsMstVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		
		model.addAttribute("result", cmsBoardService.mngView(vo));
		model.addAttribute("tableHead", cmsBoardService.selectBbsTableHeadList(vo));
		model.addAttribute("addFieldList", cmsBoardService.selectBbsAddFieldConfList(vo));
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbsmng/mngModify.do", "게시판 관리 상세 조회", "");
		}
		
		return "pjrb/cms/bbsmng/mngWrite";
	}
	
	@RequestMapping("/cms/bbsmng/mngUpdate.do")
	public String update(@ModelAttribute("searchVO") CmsBbsMstVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setModId(user.getEmplyrId());
		cmsBoardService.updateBbsMaster(vo);
		/*cmsBoardService.insertBbsAddFieldConf(vo);
		cmsBoardService.insertBbsTableHead(vo);*/
		
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbsmng/mngUpdate.do", "게시판 관리 수정", "");
		}
		
		return "redirect:/cms/bbsmng/mngList.do";
	}
	
	@RequestMapping("/cms/bbsmng/mngDelete.do")
	public String delete(@ModelAttribute("searchVO") CmsBbsMstVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
		cmsBoardService.mngDelete(vo);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbsmng/mngDelete.do", "게시판 관리 삭제", "");
		}
		
		return "redirect:/cms/bbsmng/mngList.do";
	}
	
	@RequestMapping("/cms/bbs/bbsList.do")
	public String bbsList(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, ModelMap model) throws Exception {
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return "redirect:/cms/login.do?returnValue=2";
		}else{
			CmsBbsMstVO vo = new CmsBbsMstVO();
			vo.setBbsId(CmsBbsVO.getBbsId());
			
			EgovMap mResult = cmsBoardService.mngView(vo);
			
			String bbsTp = (String) mResult.get("bbsTp");
			String url = "";
			
			int totCnt = 0;
			String userIP = request.getRemoteAddr();
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(CmsBbsVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(CmsBbsVO.getRecordCountPerPage());
			paginationInfo.setPageSize(CmsBbsVO.getPageSize());
		
			CmsBbsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			CmsBbsVO.setLastIndex(paginationInfo.getLastRecordIndex());
			CmsBbsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			model.addAttribute("mResult", mResult);

			if(bbsTp.equals("2")||bbsTp.equals("3")){
				totCnt = cmsBoardService.bbsContentsListCnt(CmsBbsVO);

				model.addAttribute("resultList", cmsBoardService.bbsContentsList(CmsBbsVO)); 
				
				url = "pjrb/cms/bbs/bbsContentsList";
				
				if(user != null){
					cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsContentsList.do", "게시물 관리 조회", "");
				}
				
			}else{
				totCnt = cmsBoardService.bbsListCnt(CmsBbsVO);
				model.addAttribute("resultList", cmsBoardService.bbsList(CmsBbsVO));
				model.addAttribute("tableHead", cmsBoardService.selectBbsTableHeadList(vo));
				model.addAttribute("addFieldList", cmsBoardService.selectBbsAddFieldList_forList(CmsBbsVO));
				model.addAttribute("selectBoxList", cmsBoardService.selectBbsSelectBoxSearch(CmsBbsVO));
				
				if(user != null){
					cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsList.do", "게시물 관리 조회", "");
				}
				
				url =  "pjrb/cms/bbs/bbsList";
			}
			
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("CmsBbsVO", CmsBbsVO);
			model.addAttribute("paginationInfo", paginationInfo);
			
			return url;
		}
	}
	
	@RequestMapping("/cms/bbs/bbsListExcelDown.do")
	public ModelAndView bbsListExcelDown(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, ModelMap model) throws Exception {
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}else{
			CmsBbsMstVO vo = new CmsBbsMstVO();
			vo.setBbsId(CmsBbsVO.getBbsId());
			
			CmsBbsVO.setPageIndex(1);
			CmsBbsVO.setPageUnit(99999999);
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(CmsBbsVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(CmsBbsVO.getRecordCountPerPage());
			paginationInfo.setPageSize(CmsBbsVO.getPageSize());
		
			CmsBbsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			CmsBbsVO.setLastIndex(paginationInfo.getLastRecordIndex());
			CmsBbsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
			int totCnt = cmsBoardService.bbsListCnt(CmsBbsVO);
			
			paginationInfo.setTotalRecordCount(totCnt);
			EgovMap bbsMng = cmsBoardService.mngView(vo);
			String filename = "게시판";
			if(bbsMng != null && bbsMng.get("bbsNm") != null && bbsMng.get("bbsNm").toString() != null){
				filename = bbsMng.get("bbsNm").toString();
			}
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
			resultMap.put("resultList", cmsBoardService.bbsList(CmsBbsVO));
			resultMap.put("paginationInfo", paginationInfo);
			resultMap.put("tableHead", cmsBoardService.selectBbsTableHeadList(vo));
			resultMap.put("addFieldList", cmsBoardService.selectBbsAddFieldList_forList(CmsBbsVO));
			resultMap.put("mResult", bbsMng);
			request.setAttribute("filename", filename); 
			
			String userIP = request.getRemoteAddr();
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsListExcelDown.do", "게시물 관리 엑셀다운", "");
			}
			
			return new ModelAndView("bbsExcelDown","resultMap",resultMap);

		}
	}

	@RequestMapping("/cms/bbs/excelDown.do")
	public ModelAndView bbsExcelDown(CmsBbsVO vo, HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}
		
		String excelFlag = vo.getExcelFlag();
		String excelType = vo.getExcelType();
		String filename = "";
		
		if(excelFlag != null && !excelFlag.isEmpty()){
			vo.setRecordCountPerPage(-1);
			
			CmsBbsMstVO MstVO = new CmsBbsMstVO();
			MstVO.setBbsId(vo.getBbsId());
			List<?> tableHead = cmsBoardService.selectBbsTableHeadList(MstVO);
			
			List<?> list = cmsBoardService.bbsList(vo);
			String[] cellHeader = {"번호", "조회수","작성일"};
			String[] row = {"ntcYn", "rdcnt", "regDate"};
			String[] columnWidth = {"4000", "10000", "5000"};
			
			List<?> addFieldList = cmsBoardService.selectBbsAddFieldList_forList(vo);
			
			for (int i = 0; i < tableHead.size(); i++) { // 여분 필드 헤드 추가
				EgovMap tmp = (EgovMap) tableHead.get(i);
				if(tmp.get("fieldNm") != null && !tmp.get("fieldNm").toString().isEmpty() && !tmp.get("fieldNm").toString().replace(" ", "").isEmpty()) {
					String newValue = tmp.get("fieldNm").toString();
					int position = i+1;
					List<String> addHead = new ArrayList<>(Arrays.asList(cellHeader));
					addHead.add(position, newValue);
					cellHeader = addHead.toArray(cellHeader);
					
					List<String> addRow = new ArrayList<>(Arrays.asList(row));
					addRow.add(position, "field");
					row = addRow.toArray(row);
					
					List<String> addWidth = new ArrayList<>(Arrays.asList(columnWidth));
					addWidth.add(position, "10000");
					columnWidth = addWidth.toArray(columnWidth);
				}
			}
			
			EgovMap bbsMng = cmsBoardService.mngView(MstVO);
			if(bbsMng != null && bbsMng.get("bbsNm") != null && bbsMng.get("bbsNm").toString() != null){
				filename = bbsMng.get("bbsNm").toString();
			}else {
				filename = "게시판  리스트";
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("excelFlag", excelFlag);
			map.put("excelType", excelType);
			map.put("excelList", list);
			map.put("excelHead", cellHeader);
			map.put("excelRow", row);
			map.put("excelWidth", columnWidth);
			map.put("filename", filename);
			
			map.put("mResult", bbsMng);
			map.put("addFieldList", addFieldList);
			
			excelModule.excel(map, request, response);
		 	
			return new ModelAndView(ajaxMainView);
		}else{
			return new ModelAndView("","","");
		}
		
	}
	
	@RequestMapping("/cms/bbs/bbsWrite.do")
    public String bbsWrite(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String url = "";
		
		if(user == null){
			return "redirect:/admin/login.do?returnValue=2";
		}else{
			
			CmsBbsMstVO vo = new CmsBbsMstVO();
			vo.setBbsId(CmsBbsVO.getBbsId());
			
			EgovMap mResult = cmsBoardService.mngView(vo);
			model.addAttribute("mResult", mResult);
			String bbsTp = (String) mResult.get("bbsTp");
			
			model.addAttribute("addFieldValue", cmsBoardService.selectBbsAddFieldList(CmsBbsVO));
			String userIP = request.getRemoteAddr();
			
			if(bbsTp.equals("2")||bbsTp.equals("3")){
				url = "pjrb/cms/bbs/bbsContentsWrite";
			}else{
				url = "pjrb/cms/bbs/bbsWrite";
			}
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsListExcelDown.do", "게시물 관리 등록 화면", "");
			}
		}
	
		return url;
    }
	
	@RequestMapping("/cms/bbs/bbsInsert.do")
    public ModelAndView bbsInsert(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = true;
	
		if (isAuthenticated) {
		    List<FileVO> result = null;
		    String atchFileId = "";
		    
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(files, "BBS_", 0, atchFileId,"");
				atchFileId = fileMngService.insertFileInfs(result);
		    }
		    CmsBbsVO.setAtchFileId(atchFileId);
		    CmsBbsVO.setRegId(user.getEmplyrId());
		 
		    CmsBbsVO.setNttCn(unscript(CmsBbsVO.getNttCn()));	// XSS 방지
		    
		    cmsBoardService.bbsInsert(CmsBbsVO);
		    
		    String userIP = request.getRemoteAddr();
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsInsert.do", "게시물 관리 등록", atchFileId);
			}
		}
	
		model.addAttribute("bbsId", CmsBbsVO.getBbsId());

		return new ModelAndView("ajaxMainView");
    }
	
	@RequestMapping("/cms/bbs/bbsModify.do")
    public String bbsModify(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String url = "";
		
		if(user == null){
			return "redirect:/admin/login.do?returnValue=2";
		}else{
			
			CmsBbsMstVO vo = new CmsBbsMstVO();
			vo.setBbsId(CmsBbsVO.getBbsId());
			
			EgovMap mResult = cmsBoardService.mngView(vo);
			model.addAttribute("mResult", mResult);
			String bbsTp = (String) mResult.get("bbsTp");
			
			if(bbsTp.equals("2")||bbsTp.equals("3")){
				EgovMap board = cmsBoardService.bbsContentsView(CmsBbsVO);
				model.addAttribute("result", board);
				
				url = "pjrb/cms/bbs/bbsContentsWrite";
			}else{
				EgovMap board = cmsBoardService.bbsView(CmsBbsVO);
				model.addAttribute("result", board);
				model.addAttribute("addFieldValue", cmsBoardService.selectBbsAddFieldList(CmsBbsVO));
				
				url = "pjrb/cms/bbs/bbsWrite";
			}
			
			String userIP = request.getRemoteAddr();
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsModify.do", "게시물 관리 상세조회", "");
			}
		}
		
		return url;
    }
	
	@RequestMapping("/cms/bbs/bbsView.do")
    public String bbsView(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
		if(user == null){
			return "redirect:/admin/login.do?returnValue=2";
		}else{
			CmsBbsMstVO vo = new CmsBbsMstVO();
			vo.setBbsId(CmsBbsVO.getBbsId());
			model.addAttribute("mResult", cmsBoardService.mngView(vo));
			
			EgovMap board = cmsBoardService.bbsView(CmsBbsVO);
			model.addAttribute("result", board);
			
			/*FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(board.get("atchFileId").toString());
			List<FileVO> result = fileMngService.selectFileInfs(fileVO);
			model.addAttribute("fileList", result);
			model.addAttribute("fileListCnt", result.size());*/
			
			String userIP = request.getRemoteAddr();
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsView.do", "게시물 관리 상세조회", "");
			}
		}
	
		return "pjrb/cms/bbs/bbsView";
    }
	
	@RequestMapping("/cms/bbs/bbsUpdate.do")
    public ModelAndView bbsUpdate(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		String atchFileId = CmsBbsVO.getAtchFileId();
	
		if (isAuthenticated) {
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    if (!files.isEmpty()) {
				if ("".equals(atchFileId)) {
				    List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, atchFileId,"");
				    atchFileId = fileMngService.insertFileInfs(result);
				    CmsBbsVO.setAtchFileId(atchFileId);
				} else {
				    FileVO fvo = new FileVO();
				    fvo.setAtchFileId(atchFileId);
				    int cnt = fileMngService.getMaxFileSN(fvo);
				    List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "");
				    fileMngService.updateFileInfs(_result);
				}
		    }
	
		    CmsBbsVO.setModId(user.getEmplyrId());
		    
		    CmsBbsVO.setNttCn(unscript(CmsBbsVO.getNttCn()));	// XSS 방지
		    CmsBbsVO.setReplyCn(unscript(CmsBbsVO.getReplyCn()));	// XSS 방지
		    
		    cmsBoardService.bbsUpdate(CmsBbsVO);
		    
		    String userIP = request.getRemoteAddr();
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsUpdate.do", "게시물 관리 수정", atchFileId);
			}
		}
		
		model.addAttribute("bbsId", CmsBbsVO.getBbsId());

		return new ModelAndView("ajaxMainView");
    }
	
	@RequestMapping("/cms/bbs/bbsDelete.do")
    public ModelAndView bbsDelete(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, ModelMap model) throws Exception {
	
		cmsBoardService.bbsDelete(CmsBbsVO);
		
		model.addAttribute("bbsId", CmsBbsVO.getBbsId());
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsDelete.do", "게시물 관리 삭제", "");
		}
		
		return new ModelAndView("ajaxMainView");
    }
	
	@RequestMapping("/cms/bbs/bbsUseAtUpdate.do")
    public ModelAndView bbsUseAtUpdate(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, ModelMap model) throws Exception {
	
		cmsBoardService.bbsUseAtUpdate(CmsBbsVO);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsUseAtUpdate.do", "게시물 관리 상태 수정", "");
		}
		
		return new ModelAndView("ajaxMainView");
    }
	
	@RequestMapping("/cms/bbs/bbsChkDel.do")
    public ModelAndView bbsChkDel(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, ModelMap model) throws Exception {
	
		String[] nttIdList = (CmsBbsVO.getNttNo()).split(",");
		
		for(int i=0; i<nttIdList.length; i++){
			CmsBbsVO vo = new CmsBbsVO();
			vo.setNttNo(nttIdList[i]);
			vo.setBbsId(CmsBbsVO.getBbsId());
			
			cmsBoardService.bbsDelete(vo);
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsChkDel.do", "게시물 관리 선택 삭제", "");
		}
		
		model.addAttribute("bbsId", CmsBbsVO.getBbsId());
		return new ModelAndView("ajaxMainView");
    }
	
	protected String unscript(String data) {
        if (data == null || data.trim().equals("")) {
            return "";
        }
        
        String ret = data;
        
        ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
        
        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
        
        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
        
        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        
        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

        return ret;
    }
	
	@RequestMapping("/cms/bbs/bbsContentsInsert.do")
    public ModelAndView bbsContentsInsert(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = true;
	
		if (isAuthenticated) {
		    List<FileVO> result = null;
		    List<FileVO> thumbnailVO = null;
		    List<FileVO> videoVO = null;
		    
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    Map<String, MultipartFile> thumbnailMap = new HashMap<String, MultipartFile>();
		    Map<String, MultipartFile> filesMap = new HashMap<String, MultipartFile>();
		    Map<String, MultipartFile> videofilesMap = new HashMap<String, MultipartFile>();
		    
		    String thumbnailId = "";
		    String atchFileId = "";
		    String videoFileId = "";
		    
		    Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();	
		    
		    //파일 가져오기 (jsp name)
		    while (itr.hasNext()) {
		    	Entry<String, MultipartFile> entry = itr.next();
		    	String key = entry.getKey();
		    	
		    	if(key.indexOf("fileThumbnail") != -1){
		    		thumbnailMap.put(key, entry.getValue());
		    	}else if(key.indexOf("videoFile") != -1){
		    		videofilesMap.put(key, entry.getValue());
		    	}else{
		    		filesMap.put(key, entry.getValue());
		    	}
		    }
		    
		    //파일 정보 세팅
		    if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(filesMap, "BBS_", 0, atchFileId,"");
				thumbnailVO = fileUtil.parseFileInf(thumbnailMap, "THUMB_", 0, thumbnailId, "");
				videoVO = fileUtil.parseFileInf(videofilesMap, "VIDEO_",0,videoFileId, "");
				
				thumbnailId = fileMngService.insertFileInfs(thumbnailVO);
				atchFileId = fileMngService.insertFileInfs(result);
				videoFileId = fileMngService.insertFileInfs(videoVO);
		    }
		    
		    CmsBbsVO.setAtchFileId(atchFileId);
		    CmsBbsVO.setThumbAtchFileId(thumbnailId);
		    CmsBbsVO.setVideoFileId(videoFileId);
		    
		    CmsBbsVO.setRegId(user.getEmplyrId());
		    CmsBbsVO.setNttCn(unscript(CmsBbsVO.getNttCn()));	// XSS 방지
		    
		    cmsBoardService.bbsContentsInsert(CmsBbsVO);
		    
		    String userIP = request.getRemoteAddr();
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsContentsInsert.do", "게시물 관리 등록", atchFileId);
			}
		}
	
		model.addAttribute("bbsId", CmsBbsVO.getBbsId());

		return new ModelAndView("ajaxMainView");
    }
	
	@RequestMapping("/cms/bbs/bbsContentsUpdate.do")
    public ModelAndView bbsContentsUpdate(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		String atchFileId = CmsBbsVO.getAtchFileId();
		String thumbId = CmsBbsVO.getThumbAtchFileId();
	    String videoFileId = CmsBbsVO.getVideoFileId();
	    
		if (isAuthenticated) {
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    Map<String, MultipartFile> thumbnailMap = new HashMap<String, MultipartFile>();
		    Map<String, MultipartFile> filesMap = new HashMap<String, MultipartFile>();
		    Map<String, MultipartFile> videofilesMap = new HashMap<String, MultipartFile>();
		    
		    Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();	
		    
		    while (itr.hasNext()) {
		    	Entry<String, MultipartFile> entry = itr.next();
		    	String key = entry.getKey();
		    	
		    	if(key.indexOf("fileThumbnail") != -1){
		    		thumbnailMap.put(key, entry.getValue());
		    	}else if(key.indexOf("videoFile") != -1){
		    		videofilesMap.put(key, entry.getValue());
		    	}else{
		    		filesMap.put(key, entry.getValue());
		    	}
		    }
		    //각각의 파일이 빈값이 아니면 정보를 세팅	
		    if (!filesMap.isEmpty()) {
				if("".equals(atchFileId)) {
					
				    List<FileVO> result = fileUtil.parseFileInf(filesMap, "BBS_", 0, atchFileId,"");
				    atchFileId = fileMngService.insertFileInfs(result);
				    CmsBbsVO.setAtchFileId(atchFileId);
				}else{
				    FileVO fvo = new FileVO();
				    fvo.setAtchFileId(atchFileId);
				    int cnt = fileMngService.getMaxFileSN(fvo);
				    List<FileVO> _result = fileUtil.parseFileInf(filesMap, "BBS_", cnt, atchFileId, "");
				    fileMngService.updateFileInfs(_result);
				}
	    	}
	    		
	    	if(!thumbnailMap.isEmpty()){
				if("".equals(thumbId)){
					 List<FileVO> thumbFileVO = fileUtil.parseFileInf(thumbnailMap, "THUMB_", 0, thumbId, "");
					 thumbId = fileMngService.insertFileInfs(thumbFileVO);
					 CmsBbsVO.setThumbAtchFileId(thumbId);
				}else{
					 List<FileVO> thumbFileVO = fileUtil.parseFileInf(thumbnailMap, "THUMB_", 0, thumbId, "");
					 fileMngService.updateFileInfs(thumbFileVO);
				}
	    	}
	    	
	    	if(!videofilesMap.isEmpty()){
	    		if("".equals(videoFileId)){
	    			List<FileVO> videoFileVO = fileUtil.parseFileInf(videofilesMap, "VIDEO_", 0, videoFileId, "");
	    			videoFileId = fileMngService.insertFileInfs(videoFileVO);
	    			CmsBbsVO.setVideoFileId(videoFileId);
	    		}else{
	    			List<FileVO> videoFileVO = fileUtil.parseFileInf(videofilesMap, "VIDEO_", 0, videoFileId, "");
	    			fileMngService.updateFileInfs(videoFileVO);
	    		}
	    	}
		 		  
		  CmsBbsVO.setModId(user.getEmplyrId());
		  CmsBbsVO.setNttCn(unscript(CmsBbsVO.getNttCn()));	// XSS 방지
		  CmsBbsVO.setReplyCn(unscript(CmsBbsVO.getReplyCn()));	// XSS 방지
		    
		  cmsBoardService.bbsContentsUpdate(CmsBbsVO);
		 } 
		 String userIP = request.getRemoteAddr();
			
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsContentsUpdate.do", "게시물 관리 수정", atchFileId);
		}
		
		model.addAttribute("bbsId", CmsBbsVO.getBbsId());

		return new ModelAndView("ajaxMainView");
    }
	

	@RequestMapping("/cms/bbs/bbsContentsDelete.do")
    public ModelAndView bbsContentsDelete(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, ModelMap model) throws Exception {
	
		cmsBoardService.bbsContentsDelete(CmsBbsVO);
		
		model.addAttribute("bbsId", CmsBbsVO.getBbsId());
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsContentsDelete.do", "게시물 관리 삭제", "");
		}
		
		return new ModelAndView("ajaxMainView");
    }
	
	@RequestMapping("/cms/bbs/bbsContentsChkDel.do")
    public ModelAndView bbsContentsChkDel(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, ModelMap model) throws Exception {
	
		String[] nttIdList = (CmsBbsVO.getNttNo()).split(",");
		
		for(int i=0; i<nttIdList.length; i++){
			CmsBbsVO vo = new CmsBbsVO();
			vo.setNttNo(nttIdList[i]);
			vo.setBbsId(CmsBbsVO.getBbsId());
			
			cmsBoardService.bbsContentsDelete(vo);
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsContentsChkDel.do", "게시물 관리 선택 삭제", "");
		}
		
		model.addAttribute("bbsId", CmsBbsVO.getBbsId());
		return new ModelAndView("ajaxMainView");
    }
	
	@RequestMapping("/cms/bbs/bbsContentsListExcelDown.do")
	public ModelAndView bbsContentsListExcelDown(@ModelAttribute("searchVO") CmsBbsVO CmsBbsVO, HttpServletRequest request, ModelMap model) throws Exception {
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}else{
			CmsBbsMstVO vo = new CmsBbsMstVO();
			vo.setBbsId(CmsBbsVO.getBbsId());
			
			CmsBbsVO.setPageIndex(1);
			CmsBbsVO.setPageUnit(99999999);
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(CmsBbsVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(CmsBbsVO.getRecordCountPerPage());
			paginationInfo.setPageSize(CmsBbsVO.getPageSize());
		
			CmsBbsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			CmsBbsVO.setLastIndex(paginationInfo.getLastRecordIndex());
			CmsBbsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
			int totCnt = cmsBoardService.bbsContentsListCnt(CmsBbsVO);
			
			paginationInfo.setTotalRecordCount(totCnt);
			EgovMap bbsMng = cmsBoardService.mngView(vo);
			String filename = "게시판";
			if(bbsMng != null && bbsMng.get("bbsNm") != null && bbsMng.get("bbsNm").toString() != null){
				filename = bbsMng.get("bbsNm").toString();
			}
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
			resultMap.put("resultList", cmsBoardService.bbsContentsList(CmsBbsVO));
			resultMap.put("paginationInfo", paginationInfo);
			resultMap.put("mResult", bbsMng);
			request.setAttribute("filename", filename); 
			
			String userIP = request.getRemoteAddr();
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsListExcelDown.do", "게시물 관리 엑셀다운", "");
			}
			
			return new ModelAndView("bbsContentsExcelDown","resultMap",resultMap);

		}
	}
	
	@RequestMapping("/cms/bbsCon/excelDown.do")
	public ModelAndView bbsConExcelDown(CmsBbsVO vo, HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}
		
		String excelFlag = vo.getExcelFlag();
		String excelType = vo.getExcelType();
		String filename = "";
		
		if(excelFlag != null && !excelFlag.isEmpty()){
			vo.setRecordCountPerPage(-1);
			
			CmsBbsMstVO MstVO = new CmsBbsMstVO();
			MstVO.setBbsId(vo.getBbsId());
			
			List<?> list = cmsBoardService.bbsContentsList(vo);
			String[] cellHeader = {"번호", "제목", "조회수", "작성일"};
			String[] row = {"ntcYn", "nttSj", "rdcnt", "regDate"};
			String[] columnWidth = {"4000", "10000", "4000", "5000"};
			
			EgovMap bbsMng = cmsBoardService.mngView(MstVO);
			if(bbsMng != null && bbsMng.get("bbsNm") != null && bbsMng.get("bbsNm").toString() != null){
				filename = bbsMng.get("bbsNm").toString();
			}else {
				filename = "게시판  리스트";
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("excelFlag", excelFlag);
			map.put("excelType", excelType);
			map.put("excelList", list);
			map.put("excelHead", cellHeader);
			map.put("excelRow", row);
			map.put("excelWidth", columnWidth);
			map.put("filename", filename);
			
			map.put("mResult", bbsMng);
			
			excelModule.excel(map, request, response);
		 	
			return new ModelAndView(ajaxMainView);
		}else{
			return new ModelAndView("","","");
		}
		
	}
	
	@RequestMapping(value = "/cms/bbs/bbsCommnetList.do")
	public String bbsCommentList(@ModelAttribute("searchVO") CmsBbsCommentVO vo, @RequestParam Map<String, Object> commandMap,ModelMap model) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());

		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = cmsBoardService.bbsCommentListCnt(vo);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("cmtBbsId", vo.getBbsId());
		model.addAttribute("cmtNttNo", vo.getNttNo());
		model.addAttribute("commentCnt", totCnt);
		model.addAttribute("commentList", cmsBoardService.bbsCommentList(vo));
		//답글리스트
		vo.setSearchCnd("notNull");
		model.addAttribute("commentList2", cmsBoardService.bbsCommentList(vo));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "pjrb/cms/bbs/bbsComment";
	} 
	
	@RequestMapping("/cms/bbs/bbsCommentInsert.do")
    public ModelAndView bbsCommentInsert(@ModelAttribute("searchVO") CmsBbsCommentVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = true;
	
		if (isAuthenticated) {
		    
		    vo.setRegId(user.getEmplyrId());
		    cmsBoardService.bbsCommentInsert(vo);
		    
		    String userIP = request.getRemoteAddr();
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsCommentInsert.do", "댓글 등록","");
			}
		}
	
		return new ModelAndView("ajaxMainView");
    }
	
	@RequestMapping("/cms/bbs/bbsCommentDelete.do")
    public ModelAndView bbsCommentDelete(@ModelAttribute("searchVO") CmsBbsCommentVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = true;
	
		if (isAuthenticated) {
		    
		    cmsBoardService.bbsCommentDelete(vo);		    
		    String userIP = request.getRemoteAddr();
			
			if(user != null){
				cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsCommentDelete.do", "댓글 삭제","");
			}
		}
		
		return new ModelAndView("ajaxMainView");
    }
	
	@RequestMapping("/cms/bbs/bbsCommentUpdate.do")
    public ModelAndView bbsUCommentUpdate(@ModelAttribute("searchVO") CmsBbsCommentVO vo, HttpServletRequest request, ModelMap model) throws Exception {
	
		
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		vo.setModId(user.getEmplyrId());
		cmsBoardService.bbsCommentUpdate(vo);
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/bbs/bbsUseAtUpdate.do", "댓글 수정", "");
		}
		
		return new ModelAndView("ajaxMainView");
    }
	
}