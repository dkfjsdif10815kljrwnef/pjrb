package pjrb.cms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.accessip.service.CmsAccessIPService;
import pjrb.cms.bbs.service.CmsBbsMstVO;
import pjrb.cms.bbs.service.CmsBoardService;
import pjrb.cms.grade.service.CmsGradeService;
import pjrb.cms.grade.service.CmsGradeVO;
import pjrb.cms.member.service.CmsMemberService;
import pjrb.cms.member.service.CmsMemberVO;

public class CmsInterceptor extends HandlerInterceptorAdapter{

	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(CmsInterceptor.class);
	
    @Resource(name = "cmsBoardService")
    private CmsBoardService cmsBoardService;
    
    @Resource(name = "cmsAccessIPService")
    protected CmsAccessIPService cmsAccessIPService;
    
    @Resource(name = "cmsGradeService")
    private CmsGradeService cmsGradeService;
    
    @Resource(name = "cmsMemberService")
	private CmsMemberService cmsMemberService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    			
    	//사용자 ip
		String userIP = request.getRemoteAddr();
		ModelAndView mav = new ModelAndView();
		LOGGER.debug("userIP:"+userIP);
		//접근가능한 ip목록
		int count = 0;
		List<EgovMap> ipList = (List<EgovMap>) cmsAccessIPService.selectAccessIPList();
		for(EgovMap map : ipList)
		{
			if(map.get("ip")!=null && map.get("ip").equals(userIP))
				count++;
		}
		//ip목록에 없는 경우 에러페이지 노출
		if(count == 0){
			mav.setViewName("redirect:/code404.jsp");
			throw new ModelAndViewDefiningException(mav);
		}
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user != null){
			CmsMemberVO vo2 = new CmsMemberVO();
	        vo2.setEmplyrId(user.getEmplyrId());
	        vo2.setUuid(request.getSession().getId());
			if(user.getType() != null && !user.getType().isEmpty() && !"admin".equals(user.getType())){
				//관리자 계정이 아닐경우
				
				request.getSession().removeAttribute("loginVO");
				cmsMemberService.deleteSessionLogin(vo2);
				mav.setViewName("pjrb/process");
	        	Map<String,Object> paramMap = new HashMap<String,Object>();
	        	paramMap.put("message", "접근할수 없습니다.");
	        	paramMap.put("function", "$(document).ready(function(){ document.frm.submit(); });");
	        	paramMap.put("action", "/cms/login.do");
	        	mav.addObject("paramMap", paramMap);
				throw new ModelAndViewDefiningException(mav);
			}
			
			
	        EgovMap sessionInfo = cmsMemberService.selectSessionLogin(vo2);
	        if(sessionInfo != null && sessionInfo.get("status") != null && !sessionInfo.get("status").toString().isEmpty() && "1".equals(sessionInfo.get("status").toString()) ){
	        	//다른곳에서 로그인시
	        	request.getSession().removeAttribute("loginVO");
	        	
	        	cmsMemberService.deleteSessionLogin(vo2);
	        	
	        	mav.setViewName("pjrb/process");
	        	Map<String,Object> paramMap = new HashMap<String,Object>();
	        	paramMap.put("message", "다른 브라우저에서 로그인을 시도하여 로그아웃 됩니다.");
	        	paramMap.put("function", "$(document).ready(function(){ document.frm.submit(); });");
	        	paramMap.put("action", "/cms/login.do");
	        	mav.addObject("paramMap", paramMap);
	        	
				throw new ModelAndViewDefiningException(mav);
	        }
	        if(sessionInfo == null){
	        	request.getSession().removeAttribute("loginVO");
	        	
	        	mav.setViewName("pjrb/process");
	        	Map<String,Object> paramMap = new HashMap<String,Object>();
	        	paramMap.put("function", "$(document).ready(function(){ document.frm.submit(); });");
	        	paramMap.put("action", "/cms/login.do");
	        	mav.addObject("paramMap", paramMap);
	        	
				throw new ModelAndViewDefiningException(mav);
	        }
	        
	        UrlPathHelper urlPathHelper = new UrlPathHelper();
			String oriUrl = urlPathHelper.getOriginatingRequestUri(request);
			String splitUrl [] = oriUrl.split("\\/");
			String Menukey =  "/"+splitUrl[1]+"/"+splitUrl[2]+"/";
			String auth = user.getAuth();
			if(!"0".equals(user.getSessionGrade())){
				//pjrb 일경우 grade 0
				
				if(oriUrl.indexOf("/cms/actionLogout.do") == -1 && oriUrl.indexOf("/cms/actionLogin.do") == -1  && oriUrl.indexOf("/cms/main.do") == -1 && auth.indexOf(Menukey) == -1 && oriUrl.indexOf("/cms/bbsCon/excelDown.do") == -1){
					//메뉴 권한이 없을경우
					mav.setViewName("pjrb/process");
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("message", "권한이 없습니다.");
					paramMap.put("function", "$(document).ready(function(){ document.frm.submit(); });");
					paramMap.put("action", "/cms/main.do");
					mav.addObject("paramMap", paramMap);
					throw new ModelAndViewDefiningException(mav);
				}
			}	
		}else{
			String emplyrId = request.getParameter("emplyrId");
			String pw = request.getParameter("password");
			
			if(emplyrId != null && pw != null ){
				return true;
			}
			
			mav.setViewName("pjrb/process");
        	Map<String,Object> paramMap = new HashMap<String,Object>();
        	paramMap.put("message", "로그인이 필요합니다.");
        	paramMap.put("function", "$(document).ready(function(){ document.frm.submit(); });");
        	paramMap.put("action", "/cms/login.do");
        	mav.addObject("paramMap", paramMap);
        	
			throw new ModelAndViewDefiningException(mav);
		}
		return true;
    }
    
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		

		CmsBbsMstVO vo = new CmsBbsMstVO();
		vo.setRecordCountPerPage(-1);
		modelAndView.addObject("boardList", cmsBoardService.mngList(vo));
		
		/*
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user != null){
			CmsGradeVO gradeVO = new CmsGradeVO();
			gradeVO.setGrade(user.getSessionGrade());
			modelAndView.addObject("gradeDetail", cmsGradeService.gradeView(gradeVO));
		}*/
	}

}
