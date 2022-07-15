package pjrb.cms.main.web;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.accessip.service.CmsAccessLogService;
import pjrb.cms.accessip.service.CmsAccessLogVO;
import pjrb.cms.banner.service.CmsBannerService;
import pjrb.cms.login.service.LoginService;
import pjrb.cms.member.service.CmsMemberService;
import pjrb.cms.member.service.CmsMemberVO;
import pjrb.cms.popup.service.CmsPopupService;
import pjrb.cms.stat.service.CmsStatService;
import pjrb.user.service.PjrbDefaultVO;

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
public class CmsMainController {
		
	@Resource(name = "ajaxMainView")
	private MappingJackson2JsonView ajaxMainView;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "cmsAccessLogService")
	protected CmsAccessLogService cmsAccessLogService;
	
	@Resource(name = "cmsMemberService")
	private CmsMemberService cmsMemberService;
	
	@Resource(name = "cmsPopupService")
    private CmsPopupService cmsPopupService;
	
	@Resource(name = "cmsBannerService")
    private CmsBannerService cmsBannerService;
	
	@Resource(name = "cmsStatService")
    private CmsStatService cmsStatService;
	
	@RequestMapping("/cms/pwChange.do")
	public ModelAndView pwChange(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {

		String pw = loginVO.getPassword();
		// 1. 일반 로그인 처리
		loginVO.setPassword(loginVO.getPrePw());
		LoginVO resultVO = loginService.actionLogin(loginVO);

		if (resultVO != null && resultVO.getEmplyrId() != null && !resultVO.getEmplyrId().equals("")) {
			CmsMemberVO vo = new CmsMemberVO();
	        vo.setEmplyrId(loginVO.getEmplyrId());
	        vo.setPassword(pw);
	        vo.setType(loginVO.getType());
	        cmsMemberService.memberPassUpdate(vo);
		        
	        model.addAttribute("result", 1);
		} else {
			
			model.addAttribute("returnValue", 4);
			model.addAttribute("result", 0);
		}
		
		return new ModelAndView("ajaxMainView");
	}
	
	@RequestMapping("/cms/pwChangePage.do")
	public String pwChangePage(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {

		return "/pjrb/cms/inc/pwChange";
	}
	
	@RequestMapping(value = "/cms/main.do")
	public String main(@ModelAttribute("searchVO") PjrbDefaultVO defaultVO, ModelMap model, @RequestParam(value="returnValue", required=false, defaultValue="0") int returnValue) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user == null){
			return "redirect:/cms/login.do?returnValue=2";
		}
		
		//return "redirect:/cms/stats/conStatList.do";
		return "/pjrb/cms/index";
		
	}
	
	public String FileName(String filename,HttpServletRequest request) throws Exception {
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
		
		return filename;

	}
	
}