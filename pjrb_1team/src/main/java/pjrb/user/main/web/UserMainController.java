package pjrb.user.main.web;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.banner.service.CmsBannerService;
import pjrb.cms.banner.service.CmsBannerVO;
import pjrb.cms.bbs.service.CmsBoardService;
import pjrb.cms.pagemng.service.PageMngVO;
import pjrb.cms.popup.service.CmsPopupService;
import pjrb.cms.popup.service.CmsPopupVO;
import pjrb.user.main.service.DefaultVO;
import pjrb.user.main.service.UserMainService;
import pjrb.user.service.PjrbDefaultVO;
import pjrb.user.sns.web.SnsController;

/**
 * 사용자 페이지(헤더,푸터,일반페이지 등), 파일 리스트, csrf, xss
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
public class UserMainController {
	
	@Resource(name="userMainService")
	private UserMainService userMainService;
	
    @Resource(name = "cmsBoardService")
    private CmsBoardService cmsBoardService;
	
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
	
	@Resource(name = "cmsBannerService")
    private CmsBannerService cmsBannerService;

	@Resource(name = "cmsPopupService")
    private CmsPopupService cmsPopupService;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
    
	@RequestMapping(value = {"/main.do", "/pjrbEng/main.do"})
	public String PjrbMain(@ModelAttribute("searchAllVO") PjrbDefaultVO searchVO, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception{
		String isTrue = csrf(request, session);
		if(isTrue.equals("F")){
			return "redirect:/main.do";
		}
		
		String requestUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		
		if(requestUrl.equals("/main.do")){
			
			/*// 공지사항
			BoardVO boardVO = new BoardVO();
			boardVO.setRecordCountPerPage(-1);
			boardVO.setSearchUseAt("Y");
			boardVO.setBbsId("BBSMSTR_000000000001");
			model.addAttribute("newCnt01", cmsBoardService.bbsNewCnt(boardVO));
			EgovMap bbs01 = cmsBoardService.bbsMainList(boardVO);
			if(bbs01 != null)	noImage(bbs01);
			model.addAttribute("bbs01", bbs01);
			
			//선정과제
			boardVO.setBbsId("BBSMSTR_000000000019");
			model.addAttribute("newCnt19", cmsBoardService.bbsNewCnt(boardVO));
			List<EgovMap> bbsList19 = (List<EgovMap>) cmsBoardService.bbsList(boardVO);
			if(bbsList19 != null)	noImageList(bbsList19,"nttCn");
			model.addAttribute("bbsList19", bbsList19);
			
			//제안서양식
			boardVO.setBbsId("BBSMSTR_000000000021");
			model.addAttribute("newCnt21", cmsBoardService.bbsNewCnt(boardVO));
					
			//홍보영상
			boardVO.setBbsId("BBSMSTR_000000000022");
			model.addAttribute("newCnt22", cmsBoardService.bbsNewCnt(boardVO));
			List<EgovMap> bbsList22 = (List<EgovMap>) cmsBoardService.bbsList(boardVO);
			if(bbsList22 != null)	noImageList(bbsList22,"nttCn");
			model.addAttribute("bbsList22", bbsList22);
			
			//최신뉴스
			boardVO.setBbsId("BBSMSTR_000000000023");
			model.addAttribute("newCnt23", cmsBoardService.bbsNewCnt(boardVO));
			EgovMap bbs23 = cmsBoardService.bbsMainList(boardVO);
			if(bbs23 != null)	noImage(bbs23);
			model.addAttribute("bbs23", bbs23);*/
					
			//팝업
			CmsPopupVO popVO = new CmsPopupVO();
			popVO.setRecordCountPerPage(-1);
			popVO.setSearchCnd("main");
			popVO.setSearchStatus("Y");
			model.addAttribute("popupList", cmsPopupService.popupList(popVO));
	
			//배너
			CmsBannerVO bannerVO = new CmsBannerVO();
			bannerVO.setPurpose("1"); //배너
			bannerVO.setSearchStatus("Y");
			bannerVO.setRecordCountPerPage(-1);
			model.addAttribute("bannerList", cmsBannerService.bannerList(bannerVO));
			
			//SNS 글 가져오기
			model.addAttribute("youtubeHtml", SnsController.getYoutube());
			model.addAttribute("facebookHtml", SnsController.getFacebook());
			model.addAttribute("naverHtml", SnsController.getNaver());
			
			return "pjrb/user/main";
		}else{
			/*// 공지사항01
			BoardVO boardVO = new BoardVO();
			boardVO.setRecordCountPerPage(-1);
			boardVO.setSearchUseAt("Y");
			boardVO.setBbsId("BBSMSTR_000000000050");
			model.addAttribute("newCnt50", cmsBoardService.bbsNewCnt(boardVO));
			EgovMap bbs50 = cmsBoardService.bbsMainList(boardVO);
			if(bbs50 != null)	noImage(bbs50);
			model.addAttribute("bbs50", bbs50);
			
			//선정과제19
			boardVO.setBbsId("BBSMSTR_000000000051");
			model.addAttribute("newCnt51", cmsBoardService.bbsNewCnt(boardVO));
			List<EgovMap> bbsList51 = (List<EgovMap>) cmsBoardService.bbsList(boardVO);
			if(bbsList51 != null)	noImageList(bbsList51,"nttCn");
			model.addAttribute("bbsList51", bbsList51);
	
			//제안서양식21
			boardVO.setBbsId("BBSMSTR_000000000048");
			model.addAttribute("newCnt48", cmsBoardService.bbsNewCnt(boardVO));
					
			//홍보영상22
			boardVO.setBbsId("BBSMSTR_000000000052");
			model.addAttribute("newCnt52", cmsBoardService.bbsNewCnt(boardVO));
			List<EgovMap> bbsList52 = (List<EgovMap>) cmsBoardService.bbsList(boardVO);
			if(bbsList52 != null)	noImageList(bbsList52,"nttCn");
			model.addAttribute("bbsList52", bbsList52);
			
			//최신뉴스23
			boardVO.setBbsId("BBSMSTR_000000000053");
			model.addAttribute("newCnt53", cmsBoardService.bbsNewCnt(boardVO));
			EgovMap bbs53 = cmsBoardService.bbsMainList(boardVO);
			if(bbs53 != null)	noImage(bbs53);
			model.addAttribute("bbs53", bbs53);*/
					
			//팝업
			CmsPopupVO popVO = new CmsPopupVO();
			popVO.setRecordCountPerPage(-1);
			popVO.setSearchCnd("main");
			popVO.setSearchStatus("Y");
			model.addAttribute("popupList", cmsPopupService.popupList(popVO));
	
			//배너
			CmsBannerVO bannerVO = new CmsBannerVO();
			bannerVO.setPurpose("1"); //배너
			bannerVO.setSearchStatus("Y");
			bannerVO.setRecordCountPerPage(-1);
			model.addAttribute("bannerList", cmsBannerService.bannerList(bannerVO));
			
			return "pjrbEng/user/main";
		}
	}
	
	public List<EgovMap> noImageList(List<EgovMap> egovMapList, String flag){
		
		for(int i=0; i<egovMapList.size(); i++){
			EgovMap egovMap = egovMapList.get(i);
			if(egovMap.get(flag) != null && !egovMap.get(flag).equals("")){
				String nttCn = egovMap.get(flag).toString();
				String pattern1 = "\\< ?img(.*?)\\>";
				String pattern2 = "<[^>]*>";
				String pattern3 = "/(<([^>]+)>)/ig";
				if(nttCn.indexOf("<script>")!=-1){
					nttCn = nttCn.substring(nttCn.indexOf("</script>")+10, nttCn.length());
				}
				nttCn = nttCn.replaceAll(pattern1, "");
				nttCn = nttCn.replaceAll(pattern2, "");
				nttCn = nttCn.replaceAll(pattern3, "");
				egovMap.put(flag, nttCn);
				egovMapList.set(i, egovMap);
			}
		}
		
		return egovMapList;
	}
	
	public EgovMap noImage(EgovMap egovMap){
		
		if(egovMap.get("nttCn") != null && !egovMap.get("nttCn").equals("")){
			String nttCn = egovMap.get("nttCn").toString();
			String pattern1 = "\\< ?img(.*?)\\>";
			String pattern2 = "<[^>]*>";
			String pattern3 = "/(<([^>]+)>)/ig";
			if(nttCn.indexOf("<script>")!=-1){
				nttCn = nttCn.substring(nttCn.indexOf("</script>")+10, nttCn.length());
			}
			nttCn = nttCn.replaceAll(pattern1, "");
			nttCn = nttCn.replaceAll(pattern2, "");
			nttCn = nttCn.replaceAll(pattern3, "");
			egovMap.put("nttCn", nttCn);
		}
		
		return egovMap;
	}
	
	
	/*@RequestMapping(value = {"/user/inc/header.do", "/pjrbEng/user/inc/header.do"})
	public String PjrbHeader(Model model, HttpServletRequest request) throws Exception{
		
		String requestUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		
		String bbsId  = request.getParameter("bbsId");
		UrlPathHelper urlPathHelper = new UrlPathHelper();
	    String oriUrl = "";
	    String url = urlPathHelper.getOriginatingRequestUri(request);
	    String query = urlPathHelper.getOriginatingQueryString(request);

        if (query != null) {	           	            
        		String [] splitQuery = query.split("&");
        		oriUrl = url+"?"+splitQuery[0];	            		               	           
        } else {
        	if(requestUrl.equals("/user/inc/header.do")){
	        	if(url.indexOf("/pjrb/board") > -1){
	        		oriUrl = "/pjrb/board.do?bbsId="+bbsId;
	        	}else{
	        		if(!"/".equals(url)){
	        			
	        			oriUrl = url;
	        		}
	        	}
        	}else {
        		if(url.indexOf("/pjrbEng/board") > -1){
	        		oriUrl = "/pjrbEng/board.do?bbsId="+bbsId;
	        	}else{
	        		if(!"/".equals(url)){
	        			
	        			oriUrl = url;
	        		}
	        	}
        	}
        }
	    
	    model.addAttribute("menuInfo", mainService.selectMenuInfo(oriUrl));
		model.addAttribute("menuList", mainService.selectMainMenuList());
		
		if(requestUrl.equals("/user/inc/header.do"))
			return "/pjrb/user/inc/header";
		else
			return "/pjrbEng/user/inc/header";
		
	}
	
	@RequestMapping(value = {"/user/inc/footer.do", "/pjrbEng/user/inc/footer.do"})
	public String PjrbFooter(Model model, HttpServletRequest request) throws Exception{
		CmsBannerVO vo = new CmsBannerVO();

		String requestUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		
		if(requestUrl.equals("/user/inc/footer.do"))
			vo.setSearchEngYn("N"); 
		else
			vo.setSearchEngYn("Y"); 
		
		vo.setPurpose("2"); //관련사이트
		vo.setRecordCountPerPage(-1);
		vo.setSearchStatus("Y");
		model.addAttribute("siteList", cmsBannerService.bannerList(vo));
		
		if(requestUrl.equals("/user/inc/footer.do"))
			return "/pjrb/user/inc/footer";
		else
			return "/pjrbEng/user/inc/footer";
	}

	@RequestMapping(value = {"/pjrb/privacy.do", "/pjrbEng/privacy.do"})
	public String privacy(Model model, HttpServletRequest request) throws Exception{
		
		String requestUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		
		if(requestUrl.equals("/pjrb/privacy.do"))
			return "/pjrb/user/etc/privacy";
		else
			return "/pjrbEng/user/etc/privacy";
	}

	@RequestMapping(value = {"/pjrb/email.do", "/pjrbEng/email.do"})
	public String email(Model model, HttpServletRequest request) throws Exception{
		
		String requestUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		
		if(requestUrl.equals("/pjrb/email.do"))
			return "/pjrb/user/etc/email";
		else
			return "/pjrbEng/user/etc/email";
	}
	
	@RequestMapping(value = {"/pjrb/media.do", "/pjrbEng/media.do"})
	public String media(Model model, HttpServletRequest request) throws Exception{
		
		String requestUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		
		if(requestUrl.equals("/pjrb/media.do"))
			return "/pjrb/user/etc/media";
		else
			return "/pjrbEng/user/etc/media";
	}
	
	@RequestMapping(value = {"/user/inc/subMenu.do", "/pjrbEng/user/inc/subMenu.do"})
	public String PjrbSubMenu(HttpServletRequest request, HttpSession session, Model model) throws Exception{

		String requestUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		
		if(requestUrl.equals("/user/inc/subMenu.do"))
			return "/pjrb/user/inc/subMenu";
		else
			return "/pjrbEng/user/inc/subMenu";
		
	}
	
	@RequestMapping("/user/inc/satis.do")
	public String PjrbSatis(Model model) throws Exception{
		return "/pjrb/user/inc/satis";
	}
	
	*/
	
	
	/*@RequestMapping(value = {"/pjrb/content.do", "/pjrbEng/content.do"})
	public String PjrbContent(@RequestParam(name="pageId" , required = false) String pageId , HttpServletRequest request, HttpSession session, Model model) throws Exception{
		
		String isTrue = csrf(request, session);
		if(isTrue.equals("F")){
			return "redirect:/main.do";
		}
		
		if(pageId == null || pageId.isEmpty()){
			return "redirect:/main.do";
		}
		
		pageId = xss(pageId);
		PageMngVO vo = new PageMngVO();
		vo.setPageId(pageId);
		EgovMap result = mainService.selectContentDetail(vo);
		if(result == null || result.get("bbsContent") == null){
			return "redirect:/main.do";
		}
		
		model.addAttribute("result", result);
		
		String requestUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		
		if(requestUrl.equals("/pjrb/content.do"))
			return "/pjrb/user/content";
		else
			return "/pjrbEng/user/content";
	}*/
	
	@RequestMapping("/common/bbsSelectImage.do")
    public String bbsSelectImage(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String atchFileId = (String)commandMap.get("param_atchFileId");
		String gubun = (String)commandMap.get("param_gubun");
		String flag = (String)commandMap.get("param_flag");
		
		model.addAttribute("gubun", gubun);
		model.addAttribute("flag", flag);
		
		fileVO.setAtchFileId(atchFileId);
		
		List<FileVO> result = fileMngService.selectFileInfs(fileVO);
		model.addAttribute("fileList", result);
		model.addAttribute("fileListCnt", result.size());
	
		return "/pjrb/cms/inc/bbsFileListImage";
    }
	
	@RequestMapping("/common/bbsSelectFile.do")
    public String bbsSelectFile(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		
		String atchFileId = (String)commandMap.get("param_atchFileId");

		String gubun = (String)commandMap.get("param_gubun");
		String flag = (String)commandMap.get("param_flag");
		
		model.addAttribute("gubun", gubun);
		model.addAttribute("flag", flag);
		
		fileVO.setAtchFileId(atchFileId);
		
		List<FileVO> result = fileMngService.selectFileInfs(fileVO);
		model.addAttribute("fileList", result);
		model.addAttribute("fileListCnt", result.size());
	
		return "pjrb/cms/inc/bbsFileList";
    }
	
	@RequestMapping(value = "/user/siteMap.do")
	public String siteMap(ModelMap model) throws Exception {

		return "pjrb/user/siteMap";
	} 
	
	@RequestMapping("/inc/importMenu.do")
	public String importMenu(DefaultVO searchVO ,Model model) throws Exception{		

		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		String seq[] = searchVO.getSearchCnd().split(",");
		String id[] = searchVO.getSearchWrd().split(",");
		searchVO.setSearchCnd(seq[0]);
		searchVO.setSearchWrd(id[0]);
		resultMap.put("sub_"+searchVO.getSearchCnd(), userMainService.selectSubMenuList(searchVO));
		searchVO.setSearchCnd(Integer.toString(Integer.parseInt(searchVO.getSearchCnd()) -1));
		model.addAttribute("subMenuList", resultMap);
		model.addAttribute("searchVO", searchVO);
		return "pjrb/user/inc/importMenu";
	}

	@RequestMapping("/user/content.do")
	public String userContent(@RequestParam(name="pageId" , required = false) String pageId , Model model) throws Exception{
		if(pageId == null || pageId.isEmpty()){
			return "redirect:/main.do";
		}
		
		pageId = xss(pageId);
		PageMngVO vo  = new PageMngVO();
		vo.setPageId(pageId);
		EgovMap result = userMainService.selectContentDetail(vo);
		if(result == null || result.get("pageContents") == null){
			return "redirect:/main.do";
		}
		
		model.addAttribute("result", result);
		return "pjrb/user/contents";
	}
	
	/*@RequestMapping(value = {"/pjrb/search.do", "/pjrbEng/search.do"})
	public String search(@ModelAttribute("searchAllVO") PjrbDefaultVO vo, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception{
		String isTrue = csrf(request, session);
		if(isTrue.equals("F")){
			return "redirect:/main.do";
		}
		
		if(vo.getSearchWrd() != null && !vo.getSearchWrd().equals("")){
			String searchWrd = xss(vo.getSearchWrd());
			vo.setSearchWrd(searchWrd);
		}
		if(vo.getSearchCnd() != null && !vo.getSearchCnd().equals("")){
			String searchCnd = xss(vo.getSearchCnd());
			vo.setSearchCnd(searchCnd);
		}
		
		String requestUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		
		if(requestUrl.equals("/pjrb/search.do")){
			vo.setSearchEngYn("N");
		}else {
			vo.setSearchEngYn("Y");
		}
		
		vo.setRecordCountPerPage(-1);
		
		model.addAttribute("boardList", noImageList((List<EgovMap>)mainService.selectSearchAllBoardList(vo),"detail"));
		model.addAttribute("boardListCnt", mainService.selectSearchAllBoardListCnt(vo));
		model.addAttribute("contentList", noImageList((List<EgovMap>)mainService.selectSearchAllContentList(vo),"detail"));
		model.addAttribute("contentListCnt", mainService.selectSearchAllContentListCnt(vo));
		
		if(requestUrl.equals("/pjrb/search.do")){
			return "/pjrb/user/search/search";
		}else {
			return "/pjrbEng/user/search/search";
		}
	}
	
	@RequestMapping(value = {"/pjrb/searchDetail.do", "/pjrbEng/searchDetail.do"})
	public String searchDetail(@ModelAttribute("searchAllVO") PjrbDefaultVO vo, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception{
		String isTrue = csrf(request, session);
		if(isTrue.equals("F")){
			return "redirect:/main.do";
		}
		
		if(vo.getSearchWrd() != null && !vo.getSearchWrd().equals("")){
			String searchWrd = xss(vo.getSearchWrd());
			vo.setSearchWrd(searchWrd);
		}
		if(vo.getSearchCnd() != null && !vo.getSearchCnd().equals("")){
			String searchCnd = xss(vo.getSearchCnd());
			vo.setSearchCnd(searchCnd);
		}
		
		String requestUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		paginationInfo.setPageSize(vo.getPageSize());

		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		if(requestUrl.equals("/pjrb/searchDetail.do")){
			vo.setSearchEngYn("N");
		}else {
			vo.setSearchEngYn("Y");
		}
		
		int totCnt = 0;
		if(vo.getSearchFlag().equals("board")){
			model.addAttribute("resultList", noImageList((List<EgovMap>)mainService.selectSearchAllBoardList(vo),"detail"));
			totCnt = mainService.selectSearchAllBoardListCnt(vo);
			model.addAttribute("resultListCnt", totCnt);
		}else if(vo.getSearchFlag().equals("content")){
			model.addAttribute("resultList", noImageList((List<EgovMap>)mainService.selectSearchAllContentList(vo),"detail"));
			totCnt = mainService.selectSearchAllContentListCnt(vo);
			model.addAttribute("resultListCnt", totCnt);
		}
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		if(requestUrl.equals("/pjrb/searchDetail.do")){
			return "/pjrb/user/search/searchDetail";
		}else {
			return "/pjrbEng/user/search/searchDetail";
		}
	}*/
	
	public String csrf(HttpServletRequest request, HttpSession session) throws Exception{
		
		String isTrue = "";
		
		if(request.getSession().getAttribute("CSRF_TOKEN") == null || 
			request.getSession().getAttribute("CSRF_TOKEN").equals("") || 
			request.getSession().getAttribute("CSRF_TOKEN").toString().isEmpty()){
			session.setAttribute("CSRF_TOKEN",UUID.randomUUID().toString());
		}
		
		// 파라미터로 전달된 csrf 토큰 값 String 
		String param = request.getParameter("_csrf"); 
		if(param==null || param.equals("") || param.isEmpty()){
			param = request.getSession().getAttribute("CSRF_TOKEN").toString();
		}
		// 세션에 저장된 토큰 값과 일치 여부 검증 
		if (!(request.getSession().getAttribute("CSRF_TOKEN").equals(param))) { 
			isTrue = "F";
		}else {
			isTrue = "T"; 
		}
		
		return isTrue;
	}
	
	public String xss(String param) throws Exception{
		
		param = URLDecoder.decode(param, "UTF-8"); 
		param = StringEscapeUtils.unescapeHtml3(param).replaceAll("(?i)<script.*?>.*?</script.*?>", "") // case 1
    																.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
    																.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "") // case 3;
    																.replaceAll("<", "") // case 3;
    																.replaceAll("\\/", "") // case 3;
    																.replaceAll("alert", "") // case 3;
    																.replaceAll("\\(", "") // case 3;
    																.replaceAll("\\)", "") // case 3;
    																.replaceAll(">", ""); // case 3;
		param = StringEscapeUtils.unescapeHtml4(param).replaceAll("(?i)<script.*?>.*?</script.*?>", "") // case 1
																	.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
																	.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "") // case 3;
														        	.replaceAll("<", "") // case 3;
														        	.replaceAll("\\/", "") // case 3;
														        	.replaceAll("alert", "") // case 3;
														        	.replaceAll("\\(", "") // case 3;
    																.replaceAll("\\)", "") // case 3;
																	.replaceAll(">", ""); // case 3;
		return param;
	}
}
