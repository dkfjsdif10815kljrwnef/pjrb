package pjrb.cmm.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.scribejava.core.model.OAuth2AccessToken;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cmm.service.KakaoAPI;
import pjrb.cmm.service.NaverLoginBO;
import pjrb.cms.member.service.CmsMemberService;
import pjrb.cms.member.service.CmsMemberVO;
 

@Controller
public class SnsUserController {
 
	@Resource(name = "cmsMemberService")
	private CmsMemberService cmsMemberService;
	
	/*
	 * @Autowired private KakaoAPI kakao;
	 */
	
	/* NaverLoginBO */
	@Autowired
    private NaverLoginBO naverLoginBO;
	
    private String apiResult = null;
    
    
	@RequestMapping("/getNaverUrl.do")
	public ModelAndView getNaverUrl(HttpSession session, ModelMap model) throws Exception {
		
		naverLoginBO.setRedirectUrl(true);
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
	    model.addAttribute("naverUrl", naverAuthUrl);
	    return new ModelAndView("ajaxMainView");
	}
    
    /////////////////////////////////////////////////////////////////////////// 로그인  ///////////////////////////////////////////////////////////
    //
    //네이버 로그인 성공시 callback호출 메소드
    @RequestMapping(value = "/naverLoginCallback.do", method = { RequestMethod.GET, RequestMethod.POST })
    public String naverLoginCallback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
            throws IOException, InterruptedException, ExecutionException {
        System.out.println("---------- naver login callback");
        OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //로그인 사용자 정보를 읽어온다.
        apiResult = naverLoginBO.getUserProfile(oauthToken);
        model.addAttribute("naverResult", apiResult);
 
        /* 네이버 로그인 성공 페이지 View 호출 */
        return "pjrb/user/member/naverLogin";
    }
    
    @RequestMapping(value = "/naver_login_callback.do", method = { RequestMethod.GET, RequestMethod.POST })
    public String naver_callback2(@RequestParam String email, Model model, RedirectAttributes redirectAttributes) throws IOException {
        
    	LoginVO loginVO = new LoginVO();
    	loginVO.setEmplyrId(email);
    	loginVO.setEmail(email);
    	loginVO.setSnsYn("Y");
    	loginVO.setSnsType("N");
    	redirectAttributes.addFlashAttribute("loginVO",loginVO);
    	return "redirect:/user/snsActionLogin.do";
    }
    
    
    @RequestMapping(value = "/google_callback_login.do", method = { RequestMethod.GET, RequestMethod.POST })
    public String google_callback(@RequestParam String email, Model model, RedirectAttributes redirectAttributes) throws IOException {
        
    	LoginVO loginVO = new LoginVO();
    	loginVO.setEmplyrId(email);
    	loginVO.setEmail(email);
    	loginVO.setSnsYn("Y");
    	loginVO.setSnsType("G");
    	redirectAttributes.addFlashAttribute("loginVO",loginVO);
    	return "redirect:/user/snsActionLogin.do";
    }
    
    @RequestMapping(value = "/facebook_callback_login.do", method = { RequestMethod.GET, RequestMethod.POST })
    public String facebook_callback(@RequestParam String email, Model model, RedirectAttributes redirectAttributes) throws IOException {
        
    	LoginVO loginVO = new LoginVO();
    	loginVO.setEmplyrId(email);
    	loginVO.setEmail(email);
    	loginVO.setSnsYn("Y");
    	loginVO.setSnsType("F");
    	redirectAttributes.addFlashAttribute("loginVO",loginVO);
    	return "redirect:/user/snsActionLogin.do";
    }
    
    @RequestMapping(value = "/kakao_callback_login.do", method = { RequestMethod.GET, RequestMethod.POST })
    public String kakao_callback(@RequestParam String email, Model model, RedirectAttributes redirectAttributes) throws IOException {
        
    	LoginVO loginVO = new LoginVO();
    	loginVO.setEmplyrId(email);
    	loginVO.setEmail(email);
    	loginVO.setSnsYn("Y");
    	loginVO.setSnsType("K");
    	redirectAttributes.addFlashAttribute("loginVO",loginVO);
    	return "pjrb/user/member/joinForm";
    }
    
    ///////////////////////////////////////////////////////////////////////////// 회원가입 ///////////////////////////////////////////////////////////
    //
    //네이버 로그인 성공시 callback호출 메소드
      @RequestMapping(value = "/naverJoinCallback.do", method = { RequestMethod.GET, RequestMethod.POST })
      public String naverJoinCallback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
              throws IOException, InterruptedException, ExecutionException {
          System.out.println("---------- naver join callback");
          OAuth2AccessToken oauthToken;
          oauthToken = naverLoginBO.getAccessToken(session, code, state);
          //로그인 사용자 정보를 읽어온다.
          apiResult = naverLoginBO.getUserProfile(oauthToken);
          model.addAttribute("naverResult", apiResult);
   
          /* 네이버 로그인 성공 페이지 View 호출 */
          return "pjrb/user/member/naverJoin";
      }
      
      @RequestMapping(value = "/naver_join_callback.do", method = { RequestMethod.GET, RequestMethod.POST })
      public String naver_join_callback(@RequestParam String email, CmsMemberVO vo , Model model) throws Exception {
          
			// id chcek
			vo.setEmplyrId(email);
			vo.setEmail(email);
			EgovMap memberAuth = cmsMemberService.memberInfoView(vo);
			if(memberAuth != null)
			{
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("function", "$(document).ready(function(){ document.frm.submit(); });");
				paramMap.put("action", "/user/login.do");
				String msg = "";
				if("orgnzt".equals(memberAuth.get("type"))) {
					msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 로그인을 진행해 주세요.";
				}else if("general".equals(memberAuth.get("type"))) {
					if("Y".equals(memberAuth.get("snsYn"))) {
						if("N".equals(memberAuth.get("snsType"))) {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 네이버 로그인을 진행해 주세요.";
						}else if("G".equals(memberAuth.get("snsType"))) {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 구글 로그인을 진행해 주세요.";
						}else if("K".equals(memberAuth.get("snsType"))) {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 카카오 로그인을 진행해 주세요.";
						}else {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 페이스북 로그인을 진행해 주세요.";
						}
					}
				}
				paramMap.put("message", msg);
				model.addAttribute("paramMap", paramMap);
				return "pjrb/process";
			}

	    	vo.setSnsYn("Y");
	    	vo.setSnsType("N");
	    	vo.setType("general");
	    	cmsMemberService.memberInsert(vo);
	    	model.addAttribute("id", vo.getEmail());
	    	return "pjrb/user/member/joinComplete";
      }
      
      @RequestMapping(value = "/google_join_callback.do", method = { RequestMethod.GET, RequestMethod.POST })
      public String google_join_callback(@RequestParam String email, CmsMemberVO vo , Model model) throws Exception {
          
	    	// id chcek
    	  	vo.setEmplyrId(email);
			vo.setEmail(email);
	      	EgovMap memberAuth = cmsMemberService.memberInfoView(vo);
			if(memberAuth != null)
			{
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("function", "$(document).ready(function(){ document.frm.submit(); });");
				paramMap.put("action", "/user/login.do");
				String msg = "";
				if("orgnzt".equals(memberAuth.get("type"))) {
					msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 로그인을 진행해 주세요.";
				}else if("general".equals(memberAuth.get("type"))) {
					if("Y".equals(memberAuth.get("snsYn"))) {
						if("N".equals(memberAuth.get("snsType"))) {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 네이버 로그인을 진행해 주세요.";
						}else if("G".equals(memberAuth.get("snsType"))) {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 구글 로그인을 진행해 주세요.";
						}else if("K".equals(memberAuth.get("snsType"))) {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 카카오 로그인을 진행해 주세요.";
						}else {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 페이스북 로그인을 진행해 주세요.";
						}
					}
				}
				paramMap.put("message", msg);
				model.addAttribute("paramMap", paramMap);
				return "pjrb/process";
			}

	    	vo.setSnsYn("Y");
	    	vo.setSnsType("G");
	    	vo.setType("general");
	    	cmsMemberService.memberInsert(vo);
	    	model.addAttribute("id", vo.getEmail());
	    	return "pjrb/user/member/joinComplete";
      }
      
      @RequestMapping(value = "/facebook_join_callback.do", method = { RequestMethod.GET, RequestMethod.POST })
      public String facebook_join_callback(@RequestParam String email, CmsMemberVO vo , Model model) throws Exception {
          
	      	// id chcek
    	  	vo.setEmplyrId(email);
			vo.setEmail(email);
			EgovMap memberAuth = cmsMemberService.memberInfoView(vo);
			if(memberAuth != null)
			{
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("function", "$(document).ready(function(){ document.frm.submit(); });");
				paramMap.put("action", "/user/login.do");
				String msg = "";
				if("orgnzt".equals(memberAuth.get("type"))) {
					msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 로그인을 진행해 주세요.";
				}else if("general".equals(memberAuth.get("type"))) {
					if("Y".equals(memberAuth.get("snsYn"))) {
						if("N".equals(memberAuth.get("snsType"))) {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 네이버 로그인을 진행해 주세요.";
						}else if("G".equals(memberAuth.get("snsType"))) {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 구글 로그인을 진행해 주세요.";
						}else if("K".equals(memberAuth.get("snsType"))) {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 카카오 로그인을 진행해 주세요.";
						}else {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 페이스북 로그인을 진행해 주세요.";
						}
					}
				}
				paramMap.put("message", msg);
				model.addAttribute("paramMap", paramMap);
				return "pjrb/process";
			}
	    	
	    	vo.setSnsYn("Y");
	    	vo.setSnsType("F");
	    	vo.setType("general");
	    	cmsMemberService.memberInsert(vo);
	    	model.addAttribute("id", vo.getEmail());
	    	return "pjrb/user/member/joinComplete";
      }
      
      @RequestMapping(value = "/kakao_join_callback.do", method = { RequestMethod.GET, RequestMethod.POST })
      public String kakao_join_callback(@RequestParam String email, CmsMemberVO vo , Model model) throws Exception {
          
	    	// id chcek
	      	vo.setEmplyrId(email);
	      	vo.setEmail(email);
	      	EgovMap memberAuth = cmsMemberService.memberInfoView(vo);
			if(memberAuth != null)
			{
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("function", "$(document).ready(function(){ document.frm.submit(); });");
				paramMap.put("action", "/user/login.do");
				String msg = "";
				if("orgnzt".equals(memberAuth.get("type"))) {
					msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 로그인을 진행해 주세요.";
				}else if("general".equals(memberAuth.get("type"))) {
					if("Y".equals(memberAuth.get("snsYn"))) {
						if("N".equals(memberAuth.get("snsType"))) {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 네이버 로그인을 진행해 주세요.";
						}else if("G".equals(memberAuth.get("snsType"))) {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 구글 로그인을 진행해 주세요.";
						}else if("K".equals(memberAuth.get("snsType"))) {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 카카오 로그인을 진행해 주세요.";
						}else {
							msg = "이미 홈페이지에 회원으로 등록되어 있습니다. 페이스북 로그인을 진행해 주세요.";
						}
					}
				}
				paramMap.put("message", msg);
				model.addAttribute("paramMap", paramMap);
				return "pjrb/process";
			}

	    	vo.setSnsYn("Y");
	    	vo.setSnsType("K");
	    	model.addAttribute("memberVO",vo);
//	    	return "pjrb/user/member/joinComplete";
	    	return "pjrb/user/member/joinForm";
      }
}
