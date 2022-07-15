package pjrb.cms.menu.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.accessip.service.CmsAccessLogService;
import pjrb.cms.bbs.service.CmsBbsMstVO;
import pjrb.cms.bbs.service.CmsBoardService;
import pjrb.cms.login.service.LoginService;
import pjrb.cms.member.service.CmsMemberService;
import pjrb.cms.member.service.CmsMemberVO;
import pjrb.cms.menu.service.CmsMenuService;
import pjrb.cms.menu.service.CmsMenuVO;
import pjrb.cms.pagemng.service.PageMngService;
import pjrb.cms.pagemng.service.PageMngVO;

/**
 * 메뉴관리
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
public class CmsMenuController {
		
	@Resource(name = "ajaxMainView")
	private MappingJackson2JsonView ajaxMainView;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Resource(name = "cmsMenuService")
    private CmsMenuService cmsMenuService;
	
	@Resource(name = "egovMenuIdGnrService")
	private EgovIdGnrService idgenService;

	@Resource(name = "cmsMemberService")
    private CmsMemberService cmsMemberService;
	
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
	
    @Resource(name = "cmsAccessLogService")
    protected CmsAccessLogService cmsAccessLogService;
    
    @Resource(name = "cmsBoardService")
    private CmsBoardService cmsBoardService;
    
    @Resource(name="pageMngService")
	private PageMngService pageMngService;
	
	@RequestMapping("/cms/menu/menuinfo.do")
	public String menuinfo(@ModelAttribute("searchVO") CmsMenuVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		if(user == null){
			return "redirect:/cms/login.do?returnValue=2";
		}else{
			model.addAttribute("resultList", cmsMenuService.menuList(vo));
			
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/menu/menuinfo.do", "메뉴 관리 조회", "");
			return "/pjrb/cms/menu/menuinfo";
		}
	}
	
	@RequestMapping("/cms/menu/selectMenuInfo.do")
	public ModelAndView selectMenuInfo(CmsMenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
	
		/*CmsPageManagerVO pageMngVO = new cmsPageManagerVO();*/
		EgovMap menuMap = cmsMenuService.selectMenuInfo(menuVO);
		/*if(menuMap != null && menuMap.get("mngId") != null)
			pageMngVO.setMngId(menuMap.get("mngId").toString());*/
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("selectMenuInfo", menuMap);
		/*map.put("selectMngInfo", cmsPageManagerService.selectpageMngInfo(pageMngVO));*/
		map.put("success", "success");
		
		return new ModelAndView("ajaxMainView", map);
	}
	
	@RequestMapping("/cms/menu/menuWrite.do")
	public String menuWrite(@ModelAttribute CmsMenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
				
		model.addAttribute("menuVO", menuVO);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/menu/menuWrite.do", "메뉴 등록 화면", "");
		}
		
		return "/pjrb/cms/menu/menuWrite";
	}
	
	@RequestMapping("/cms/menu/menuModify.do")
	public String menuModify(CmsMenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {

		CmsMemberVO cmsMemberVO = new CmsMemberVO();
		EgovMap menuMap = cmsMenuService.selectMenuInfo(menuVO);
		if(menuMap != null && menuMap.get("emplyrId") != null)
			cmsMemberVO.setEmplyrId(menuMap.get("emplyrId").toString());
		
		model.addAttribute("selectMenuInfo", menuMap);
		model.addAttribute("selectMngInfo", cmsMemberService.memberView(cmsMemberVO));
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/menu/menuModify.do", "메뉴 상세 조회", "");
		}
		
		return "/pjrb/cms/menu/menuModify";
	}
	
	@RequestMapping("/cms/menu/menuSort.do")
	public String menuSort(CmsMenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
				
		model.addAttribute("resultList", cmsMenuService.menuSort(menuVO));
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/menu/menuSort.do", "메뉴 정렬 조회", "");
		}
		
		return "/pjrb/cms/menu/menuSort";
	}
	
	@RequestMapping("/cms/menu/menuInsert.do")
	public String menuInsert(CmsMenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		String menuId = idgenService.getNextStringId();
		menuVO.setMenuId(menuId);
		
		cmsMenuService.menuInsert(menuVO);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/menu/menuInsert.do", "메뉴 등록", "");
		}
		
		return "redirect:/cms/menu/menuinfo.do?menuType="+menuVO.getMenuType();
	}
	
	@RequestMapping("/cms/menu/menuUpdate.do")
	public String menuUpdate(CmsMenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
				
		cmsMenuService.menuUpdate(menuVO);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/menu/menuUpdate.do", "메뉴 수정", "");
		}
		
		return "redirect:/cms/menu/menuinfo.do?menuType="+menuVO.getMenuType();
	}
	
	@RequestMapping("/cms/menu/menuSortUpdate.do")
	public String menuSortUpdate(CmsMenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
				
		String menuCode[] = menuVO.getMenuId().split(",");
		for (int i = 0; i < menuCode.length; i++) {
			CmsMenuVO setMenuVO = new CmsMenuVO();  
			setMenuVO.setMenuId(menuCode[i]);
			setMenuVO.setMenuNum(String.valueOf(i+1));
			
			cmsMenuService.menuSortUpdate(setMenuVO);
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/menu/menuSortUpdate.do", "메뉴 정렬 수정", "");
		}
		
		return "redirect:/cms/menu/menuinfo.do?menuType="+menuVO.getMenuType();
	}
	
	@RequestMapping("/cms/menu/menuDelete.do")
	public String menuDelete(CmsMenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		if(menuVO != null && menuVO.getMenuId() != null && !menuVO.getMenuId().isEmpty()){
			String []menuArr = menuVO.getMenuId().split(",");
			for(int i=0 ; i<menuArr.length ; i++){
				if(menuArr[i] != null && !menuArr[i].isEmpty()){
					menuVO.setMenuId(menuArr[i]);
					cmsMenuService.menuDelete_1depth(menuVO);
				}
			}
		}
		
		/*EgovMap vo = cmsMenuService.selectMenuInfo(menuVO);
		int menuDepth = (int) vo.get("menuDepth");
		if(menuDepth==1){
			cmsMenuService.menuDelete_3depth(menuVO);
			cmsMenuService.menuDelete_2depth(menuVO);
			cmsMenuService.menuDelete_1depth(menuVO);
		}else if(menuDepth==2){
			cmsMenuService.menuDelete_2depth(menuVO);
			cmsMenuService.menuDelete_1depth(menuVO);
		}else if(menuDepth==3){
			cmsMenuService.menuDelete_1depth(menuVO);
		}*/
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/menu/menuDelete.do", "메뉴 삭제", "");
		}
		
		return "redirect:/cms/menu/menuinfo.do?menuType="+menuVO.getMenuType();
	}
	

	@RequestMapping("/cms/menu/pageManagerListPopup.do")
	public String pageManagerListPopup(@ModelAttribute("searchVO") CmsMemberVO cmsMemberVO, ModelMap model) throws Exception {
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null)
			return "redirect:/cms/login.do?returnValue=2";

		cmsMemberVO.setSearchType("admin");
		
		PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(cmsMemberVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(cmsMemberVO.getRecordCountPerPage());
        paginationInfo.setPageSize(cmsMemberVO.getPageSize());

        cmsMemberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        cmsMemberVO.setLastIndex(paginationInfo.getLastRecordIndex());
        cmsMemberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        paginationInfo.setTotalRecordCount(cmsMemberService.memberListCnt(cmsMemberVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("resultList", cmsMemberService.memberList(cmsMemberVO));
		return "/pjrb/cms/menu/pageMngModalView";
	}
	
	@RequestMapping("/cms/menu/contentListPopup.do")
	public String ContentListPopup(@RequestParam(value="eng", required=false, defaultValue="") String eng, @ModelAttribute("searchVO") PageMngVO vo, ModelMap model) throws Exception {
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null)
			return "redirect:/cms/login.do?returnValue=2";
		
		vo.setUniqId(user.getEmplyrId());
		
		vo.setPageUnit(propertyService.getInt("pageUnit"));
		vo.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		paginationInfo.setPageSize(vo.getPageSize());

		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = pageMngService.selectPageMngListCnt(vo);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", pageMngService.selectPageMngList(vo));
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("eng", eng);
		
		return "/pjrb/cms/menu/contentListModalView";
	}
	
	@RequestMapping("/cms/menu/bbsListPopup.do")
	public String bbsListPopup(@RequestParam(value="eng", required=false, defaultValue="") String eng, @ModelAttribute("searchVO") CmsBbsMstVO vo, ModelMap model) throws Exception {
				
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

		int totCnt = cmsBoardService.mngListCnt(vo);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", cmsBoardService.mngList(vo));
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("eng", eng);
		
		return "/pjrb/cms/menu/bbsListModalView";
	}
	
	@RequestMapping("/cms/menu/updateMenuNavAjax.do")
	public ModelAndView updateMenuNavAjax(@ModelAttribute("cmsMenuVO") CmsMenuVO cmsMenuVO) throws Exception {
			
		if(cmsMenuVO != null && cmsMenuVO.getCmsMenuVOList() != null){
			for(CmsMenuVO tmp : cmsMenuVO.getCmsMenuVOList()){
				if(tmp != null && tmp.getMenuId() != null){
					cmsMenuService.updateMenuNav(tmp);
				}
			}
		}
		return new ModelAndView("ajaxMainView");
	}
	
	@ResponseBody
	@RequestMapping("/cms/menu/urlInsert.do")
	public String urlInsert(CmsMenuVO menuVO, ModelMap model) throws Exception {
		
		return "";
	}
	
}