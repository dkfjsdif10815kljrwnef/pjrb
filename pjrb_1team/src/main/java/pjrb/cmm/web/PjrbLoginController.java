package pjrb.cmm.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.UrlPathHelper;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.accessip.service.CmsAccessLogService;
import pjrb.cms.accessip.service.CmsAccessLogVO;
import pjrb.cms.login.service.LoginService;
import pjrb.cms.member.service.CmsMemberService;
import pjrb.cms.member.service.CmsMemberVO;

/**
 * 메인
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
public class PjrbLoginController {
		
	@Resource(name = "ajaxMainView")
	private MappingJackson2JsonView ajaxMainView;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Resource(name = "cmsAccessLogService")
	protected CmsAccessLogService cmsAccessLogService;
	
	@Resource(name = "cmsMemberService")
	private CmsMemberService cmsMemberService;
	
    @RequestMapping(value = {"/cms/actionLogout.do","/user/actionLogout.do"})
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		request.getSession().setAttribute("loginVO", null);
		String redirectUrl = "";
		UrlPathHelper urlPathHelper = new UrlPathHelper();
	    String url = urlPathHelper.getOriginatingRequestUri(request);

	    //url값 비교
	    if(url.indexOf("cms") != -1 ){
	      	 redirectUrl = "redirect:/cms/login.do";
	    }else{
	       	 redirectUrl = "redirect:/main.do";
	    }
	    
		if(user != null ){
			
			CmsMemberVO mVO = new CmsMemberVO();
			mVO.setEmplyrId(user.getEmplyrId());
			mVO.setUuid(request.getSession().getId());
			cmsMemberService.deleteSessionLogin(mVO);
		}

		return redirectUrl;
	}
	
    @RequestMapping("/dupleLoginCheck.do")
	public ModelAndView dupleLoginCheck(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {

		String userIP =  userIP(request);
		
		
		// check id
		CmsMemberVO mVO = new CmsMemberVO();
		if(loginVO.getEmplyrId() != null)
		{
			mVO.setEmplyrId(loginVO.getEmplyrId());
			EgovMap memberVO = cmsMemberService.memberInfoView(mVO);
			if(memberVO !=null && memberVO.get("emplyrId") != null && !memberVO.get("emplyrId").toString().equals(""))
			{
				if(Integer.parseInt(memberVO.get("wrongCnt").toString()) >= 5)
				{
					model.addAttribute("returnValue", 5);
					model.addAttribute("result", 0);
					return new ModelAndView("ajaxMainView");
				}
			}
			else
			{
				model.addAttribute("returnValue", 4);
				model.addAttribute("result", 0);
				return new ModelAndView("ajaxMainView");
			}
		}
		
		LoginVO resultVO = loginService.actionLogin(loginVO);
		
		if (resultVO == null || resultVO.getEmplyrId() == null || resultVO.getEmplyrId().equals(""))
		{
			CmsMemberVO memberVO = new CmsMemberVO();
			if(loginVO.getEmplyrId() != null)
			{
				memberVO.setEmplyrId(loginVO.getEmplyrId());
				memberVO.setType("admin");
				cmsMemberService.memberPwWrong(memberVO);
				
				CmsAccessLogVO aLogVO = new CmsAccessLogVO();
				aLogVO.setStatus("N");
				aLogVO.setAccessIp(userIP);
				aLogVO.setUserId(loginVO.getEmplyrId());
				cmsAccessLogService.insertAccessLog(aLogVO);
			}
			
			model.addAttribute("returnValue", 4);
			model.addAttribute("result", 0);
		}
		
		if(resultVO != null && resultVO.getType() != null && !resultVO.getType().isEmpty() && !"admin".equals(resultVO.getType())){
			model.addAttribute("accessDeny", "accessDeny");
		}
		
		mVO.setEmplyrId(loginVO.getEmplyrId());
		mVO.setLoginIp(userIP);
		EgovMap result = cmsMemberService.selectSessionLogin_id(mVO);
		if(result != null){
			model.addAttribute("dupleLoginInfo", "Y");
		}else{
			model.addAttribute("dupleLoginInfo", "N");  
		}
		 
		return new ModelAndView("ajaxMainView");
	}
    
	@RequestMapping(value = {"/cms/actionLogin.do","/user/actionLogin.do"})
	public ModelAndView actionLogin(@ModelAttribute("loginVO") LoginVO loginVO, @RequestParam(value="dupleLoginInfo" , required=false) String dupleLoginInfo,HttpServletRequest request, ModelMap model) throws Exception {

		// login처리
		LoginVO resultVO = loginService.actionLogin(loginVO);

		// 로그인 접속 로그
		String userIP = userIP(request);
		
		if (resultVO != null && resultVO.getEmplyrId() != null && !resultVO.getEmplyrId().equals(""))
		{

				if(Integer.parseInt(resultVO.getChangeDay().toString())>90)
				{
					model.addAttribute("returnValue", 6);
				}
				else
				{
					model.addAttribute("returnValue", 0);
				}
				
				// 2-1. 로그인 정보를 세션에 저장
				//request.getSession().setAttribute("loginVO", resultVO);
				HttpSession session = request.getSession();
		        session.setAttribute("loginVO", resultVO);
		        session.setMaxInactiveInterval(60*60);
		        
		        model.addAttribute("result", 1);
		        
		        CmsMemberVO vo = new CmsMemberVO();
		        vo.setEmplyrId(loginVO.getEmplyrId());
		        vo.setType(resultVO.getType());
		        
		        vo.setLoginIp(userIP);
		        
		        if(dupleLoginInfo != null && !dupleLoginInfo.isEmpty() && "Y".equals(dupleLoginInfo)){
		        	//기존로그인정보 상태값을 1로변경 - 중복로그인상태
			        vo.setStatus("1");
			        cmsMemberService.updateSessionLogin(vo);
		        }
		        	
	        	//새로운 로그인정보 등록 상태값 0 - 정상
		        vo.setStatus("0");
		        vo.setUuid(session.getId()); 
		        cmsMemberService.insertSessionLogin(vo);
		        
		        cmsMemberService.memberPwInit(vo);
		        
				CmsAccessLogVO aLogVO = new CmsAccessLogVO();
				aLogVO.setStatus("Y");
				aLogVO.setAccessIp(userIP);
				aLogVO.setUserId(resultVO.getEmplyrId());
				cmsAccessLogService.insertAccessLog(aLogVO);
		}
				
		return new ModelAndView("ajaxMainView");
	}
	
	@RequestMapping(value = {"/cms/login.do","/user/login.do"})
	public String loginPage(HttpServletRequest request, ModelMap model, @RequestParam(value="returnValue", required=false, defaultValue="0") int returnValue) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		UrlPathHelper urlPathHelper = new UrlPathHelper();
        String url = urlPathHelper.getOriginatingRequestUri(request);
        String redirectUrl = "";
        
        if(url.indexOf("cms") != -1){
        	if(user != null){
    			return "redirect:/cms/main.do";
    		}
        	redirectUrl = "/pjrb/cms/inc/login";
        	
        }else{
        	// 사용자 페이지일때 로그인페이지 로드 
        }
        
		model.addAttribute("returnMsg", returnValue);
		return redirectUrl;
	}

	@RequestMapping(value = {"/user/snsActionLogin.do"})
	public String snsActionLogin(@ModelAttribute("loginVO") LoginVO loginVO, @RequestParam(value="dupleLoginInfo" , required=false) String dupleLoginInfo,HttpServletRequest request, ModelMap model,RedirectAttributes redirectAttributes) throws Exception {
		
		// login처리
		LoginVO resultVO = loginService.actionSnsLogin(loginVO);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("function", "$(document).ready(function(){ document.frm.submit(); });");
		paramMap.put("action", "/user/login.do");
		
		if (resultVO != null && resultVO.getEmplyrId() != null && !resultVO.getEmplyrId().equals("")) {
			// 로그인 접속 로그
			String userIP = userIP(request);
			CmsAccessLogVO aLogVO = new CmsAccessLogVO();
			aLogVO.setAccessIp(userIP);
			aLogVO.setUserId(resultVO.getEmplyrId());

			if(!resultVO.getStatus().equals("Y"))
			{
				
				
				aLogVO.setStatus("N");
				cmsAccessLogService.insertAccessLog(aLogVO);
				
				paramMap.put("message", "승인되지 않은 사용자입니다.");
				model.addAttribute("paramMap", paramMap);
				return "pjrb/process";
				
			}
			
			
			aLogVO.setStatus("Y");
			cmsAccessLogService.insertAccessLog(aLogVO);
			
			request.getSession().setAttribute("loginVO", resultVO);
			request.getSession().setMaxInactiveInterval(120*60);
			
			return "redirect:/main.do";
		}
		else
		{
			paramMap.put("message", "가입되지 않은 사용자 입니다.");
			model.addAttribute("paramMap", paramMap);
			return "pjrb/process";
		}
	}
	
	public static String userIP(HttpServletRequest request){
    	String userIP = request.getHeader("X-Forwarded-For");
		 
	    if(userIP == null || userIP.length() == 0 || "unknown".equalsIgnoreCase(userIP)) {
	    	userIP = request.getHeader("Proxy-Client-userIP");
	    }
	    if(userIP == null || userIP.length() == 0 || "unknown".equalsIgnoreCase(userIP)) {
	    	userIP = request.getHeader("WL-Proxy-Client-userIP");
	    }
	    if(userIP == null || userIP.length() == 0 || "unknown".equalsIgnoreCase(userIP)) {
	    	userIP = request.getHeader("HTTP_CLIENT_userIP");
	    }
	    if(userIP == null || userIP.length() == 0 || "unknown".equalsIgnoreCase(userIP)) {
	    	userIP = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if(userIP == null || userIP.length() == 0 || "unknown".equalsIgnoreCase(userIP)) {
	    	userIP = request.getRemoteAddr();
	    }
	    
		return userIP;
    }
	
}