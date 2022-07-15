package pjrb.cms.member.web;


import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.accessip.service.CmsAccessLogService;
import pjrb.cms.excel.excelModule;
import pjrb.cms.grade.service.CmsGradeService;
import pjrb.cms.grade.service.CmsGradeVO;
import pjrb.cms.main.web.CmsMainController;
import pjrb.cms.member.service.CmsMemberService;
import pjrb.cms.member.service.CmsMemberVO;
import pjrb.user.main.service.DefaultVO;
import pjrb.user.service.PjrbDefaultVO;

/**
 * 회원관리
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
public class CmsMemberController {
		
	@Resource(name = "ajaxMainView")
	private MappingJackson2JsonView ajaxMainView;
	
    @Resource(name = "cmsMemberService")
    private CmsMemberService cmsMemberService;

    @Resource(name = "cmsGradeService")
    private CmsGradeService cmsGradeService;
    
    @Resource(name = "cmsAccessLogService")
    protected CmsAccessLogService cmsAccessLogService;
    
	@RequestMapping("/cms/member/memberList.do")
	public String list(@ModelAttribute("searchVO") CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
				
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

			int totCnt = cmsMemberService.memberListCnt(vo);
			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", cmsMemberService.memberList(vo));
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			
			CmsGradeVO gradeVo = new CmsGradeVO();
			model.addAttribute("gradeList", cmsGradeService.gradeList(gradeVo));

			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/member/memberList.do", "회원 관리 조회","");
			
			return "/pjrb/cms/member/memberList";
		}
	}
	
	@RequestMapping("/cms/member/memberWrite.do")
	public String write(@ModelAttribute("searchVO") CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
			
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/member/memberWrite.do", "회원 등록 화면","");
		}
		
		return "/pjrb/cms/member/memberWrite";
	}
	
	@RequestMapping("/cms/member/memberInsert.do")
	public String insert(@ModelAttribute("searchVO") CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		
		cmsMemberService.memberInsert(vo);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/member/memberInsert.do", "회원 등록","");
		}
		
		return "redirect:/cms/member/memberList.do?searchType=";
	}
	
	@RequestMapping("/cms/member/memberModify.do")
	public String modify(@ModelAttribute("searchVO") CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
			
		model.addAttribute("result", cmsMemberService.memberView(vo));
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/member/memberModify.do", "회원 상세 조회","");
		}
		
		return "/pjrb/cms/member/memberWrite";
	}
	
	@RequestMapping("/cms/member/memberUpdate.do")
	public String update(@ModelAttribute("searchVO") CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		
		cmsMemberService.memberUpdate(vo);
			
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/member/memberUpdate.do", "회원 수정","");
		}
		
		return "redirect:/cms/member/memberList.do?searchType=";
	}
	
	@RequestMapping("/cms/member/memberDelete.do")
	public String delete(@ModelAttribute("searchVO") CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
			
		cmsMemberService.memberDelete(vo);

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/member/memberDelete.do", "회원 삭제","");
		}
		
		return "redirect:/cms/member/memberList.do";
	}
	
	@RequestMapping("/cms/member/memberPWInit.do")
    public ModelAndView memberPWInit(CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
	
		cmsMemberService.memberPwInit(vo);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/member/memberPWInit.do", "회원 비밀번호 오류 초기화","");
		}
		
		return new ModelAndView(ajaxMainView);
    }
	
	@RequestMapping("/cms/member/memberChkDel.do")
    public ModelAndView memberChkDel(CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		
		String[] emplyrIdList = (vo.getEmplyrIdList()).split(",");
		String[] typeList = (vo.getTypeList()).split(",");
		
		for(int i=0; i<emplyrIdList.length; i++){
			CmsMemberVO memberVO = new CmsMemberVO();
			memberVO.setEmplyrId(emplyrIdList[i]);
			memberVO.setType(typeList[i]);
			
			cmsMemberService.memberDelete(memberVO);
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/member/memberChkDel.do", "회원 선택 삭제","");
		}
		
		return new ModelAndView(ajaxMainView);
    }
	
	@RequestMapping("/cms/member/memberStatusUpdate.do")
    public ModelAndView memberStatusUpdate(CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
	
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/member/memberStatusUpdate.do", "회원 상태 수정","");
		}
		
		cmsMemberService.memberStatusUpdate(vo);
		
		return new ModelAndView(ajaxMainView);
    }
	
	@RequestMapping("/cms/member/memberAllowYnUpdate.do")
    public ModelAndView memberAllowYnUpdate(CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
	
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/member/memberAllowYnUpdate.do", "기관회원 승인여부 수정","");
		}
		
		cmsMemberService.memberAllowYnUpdate(vo);
		
		return new ModelAndView(ajaxMainView);
    }
	
	@RequestMapping("/cms/member/memberGradeUpdate.do")
    public ModelAndView memberGradeUpdate(CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
	
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String userIP = request.getRemoteAddr();
		
		if(user != null){
			cmsAccessLogService.insertAccessActLog("Y", user.getEmplyrId(), userIP, "/cms/member/memberGradeUpdate.do", "회원 권한 수정","");
		}
		
		cmsMemberService.memberGradeUpdate(vo);
		
		return new ModelAndView(ajaxMainView);
    }
	
	@RequestMapping("/cms/member/memberIdChk.do")
    public ModelAndView memberIdChk(CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
	
		int cnt = cmsMemberService.memberIdChk(vo);
		
		model.addAttribute("memberIdChk", cnt);
		return new ModelAndView(ajaxMainView);
    }
	
	@RequestMapping("/cms/member/bisNumChk.do")
    public ModelAndView bisNumChk(CmsMemberVO vo, HttpServletRequest request, ModelMap model) throws Exception {
	
		int cnt = cmsMemberService.bisNumChk(vo);
		
		model.addAttribute("bisNumChk", cnt);
		return new ModelAndView(ajaxMainView);
    }
	
	@RequestMapping("/cms/member/memberForm.do")
	public String memberForm(CmsMemberVO vo, ModelMap model) throws Exception {
		CmsGradeVO gradeVo = new CmsGradeVO();
		model.addAttribute("gradeList", cmsGradeService.gradeList(gradeVo));
		
		model.addAttribute("memberView", cmsMemberService.memberView(vo));
		
		if(vo.getType().equals("general"))
			return "/pjrb/cms/member/memberForm1";
		else if(vo.getType().equals("orgnzt"))
			return "/pjrb/cms/member/memberForm2";
		else if(vo.getType().equals("admin"))
			return "/pjrb/cms/member/memberForm0";
		else
			return "";
		
	}
	/*
	@RequestMapping("/cms/member/excelDown.do")
	public ModelAndView memberExcelDown(PjrbDefaultVO defaultVO, HttpServletRequest request, Model model)throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}
			
		Map<String,Object> map = new HashMap<String,Object>();
		
		String excelFlag = defaultVO.getExcelFlag();
		String filename = "";
		String listMapping = "";
		
		if(excelFlag != null && !excelFlag.isEmpty()){
			CmsMemberVO vo = new CmsMemberVO();
			vo.setRecordCountPerPage(-1);
			map.put("resultList", cmsMemberService.memberList(vo));
			filename = "회원 목록";
			listMapping = "memberListMapping";
			
			CmsMainController cmsMC = new CmsMainController();
			
		 	filename = cmsMC.FileName(filename, request);
		 	
		 	request.setAttribute("filename", filename);	
			return new ModelAndView(listMapping,"resultMap",map);
			
		}else{
			return new ModelAndView("","","");
		}
		
	}
	*/
	@RequestMapping("/cms/member/excelDown.do")
	public ModelAndView excelDown(CmsMemberVO vo, HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return new ModelAndView("redirect:/cms/login.do?returnValue=2");
		}
		
		String excelFlag = vo.getExcelFlag();
		String excelType = vo.getExcelType();
		String filename = "";
		
		if(excelFlag != null && !excelFlag.isEmpty()){
			vo.setRecordCountPerPage(-1);
			vo.setSearchType(vo.getExcelType());
			
			List<?> list = cmsMemberService.memberList(vo);
			String[] cellHeader = {"아이디", "이름", "사용유무", "부서", "직급", "이메일", "연락처", "권한명", "등록일"};
			String[] row = {"emplyrId", "userNm", "status", "department", "position", "email", "phone", "gradeNm" ,"regDate"};
			String[] columnWidth = {"5000", "5000", "5000", "5000", "5000", "5000", "5000", "5000", "5000"};
			
			filename = "회원 목록";
			
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