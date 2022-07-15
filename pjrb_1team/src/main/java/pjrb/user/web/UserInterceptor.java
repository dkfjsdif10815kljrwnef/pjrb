package pjrb.user.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.stat.service.CmsConstatVO;
import pjrb.cms.stat.service.CmsPagestatVO;
import pjrb.cms.stat.service.CmsStatService;
import pjrb.user.main.service.UserMainService;

public class UserInterceptor extends HandlerInterceptorAdapter{

	@Resource(name = "cmsStatService")
    private CmsStatService cmsStatService;
	
	@Resource(name="userMainService")
	private UserMainService userMainService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		String requestUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
    	
    	/*response.addHeader("Access-Control-Allow-Origin", "*");
		if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
			// CORS "pre-flight" request
			response.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE");
			response.addHeader("Access-Control-Allow-Headers", "Authorization");
			response.addHeader("Access-Control-Max-Age", "1728000");
		}*/
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Authorization");
		response.addHeader("Access-Control-Max-Age", "1728000");
		
		response.setHeader("Content-Security-Policy", "default-src 'self' localhost:8080 www.google-analytics.com *.youtube.com youtu.be *.iamport.kr; style-src 'self' 'unsafe-inline'; script-src 'self' 'unsafe-inline' 'unsafe-eval' developers.kakao.com localhost:8080 www.googletagmanager.com dapi.kakao.com t1.daumcdn.net *.iamport.kr; img-src 'self' localhost:8080 *.daumcdn.net *.youtube.com youtu.be *.iamport.kr *.jobkorea.co.kr; frame-src 'self' *.iamport.kr *.daum.net *.kakao.com;");
		response.setHeader("Cache-Control", "no-cache");//, no-store, must-revalidate, pre-check=0, post-check=0, max-age=0, s-maxage=0"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		//교차 프레임 스크립팅 방어 누락
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		
		//HTTP Strict-Transport-Security 헤더 누락
		response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");
		//Missing "X-Content-Type-Options" header
		response.setHeader("X-Content-Type-Options", "nosniff");
		//Missing "X-XSS-Protection" header
		response.setHeader("X-XSS-Protection", "1; mode=block");
		response.setDateHeader("Expires", -1); // Proxies.
		
    	return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		
	////////////////////////////////////////////////////////////////////접속 통계
		
		String token = ""; 
		if(request.getSession().getAttribute("CSRF_TOKEN") == null)
		{
			token = UUID.randomUUID().toString();
			request.getSession().setAttribute("CSRF_TOKEN", token); //로그인시 처리되도록 수정필요
			
			CmsConstatVO conStatsVO = new CmsConstatVO(); 
			
			//접속 아이피
			String mberConectIp = request.getRemoteAddr();
			//접속 user-agent
			String mberConectUa = request.getHeader("user-agent");
			//접속 OS
			//String mberConectOs = EgovClntInfo.getClntOsInfo(request);
			String mberConectOs = "";
			//접속 referer 
			String mberConectRfu = request.getHeader("referer");
			//접속 브라우저
			String mberConectBr = "";
			if (mberConectUa != null) {
				if (mberConectUa.indexOf("Trident") > -1) {
					mberConectBr = "MSIE";
					mberConectOs = "PC";
				} else if (mberConectUa.indexOf("Chrome") > -1) {
					mberConectBr = "Chrome";
					mberConectOs = "PC";
				} else if (mberConectUa.indexOf("Opera") > -1) {
					mberConectBr = "Opera";
					mberConectOs = "PC";
				} else if (mberConectUa.indexOf("iPhone") > -1
						&& mberConectUa.indexOf("Mobile") > -1) {
					mberConectBr = "iPhone";
					mberConectOs = "Mobile";
				} else if (mberConectUa.indexOf("Android") > -1
						&& mberConectUa.indexOf("Mobile") > -1) {
					mberConectBr = "Android";
					mberConectOs = "Mobile";
				} else
				{
					mberConectBr = "";
					mberConectOs = "PC";
				}
			}
			
			conStatsVO.setIp(mberConectIp);
			conStatsVO.setBrowser(mberConectBr);
			if(mberConectOs.equals("PC"))
				conStatsVO.setOsPc("PC");
			else
				conStatsVO.setOsMobile("Mobile");
			conStatsVO.setEngYn("N");
			cmsStatService.constatInsert(conStatsVO);
		}
		
		//////////////////////////////////////////////////////////////////////페이지 통계
		
		//사용자 ip
		String userIP = request.getRemoteAddr();

		// 페이지 통계
		CmsPagestatVO pageStatVO = new CmsPagestatVO();
		//String url = request.getRequestURI();
		
		UrlPathHelper urlPathHelper = new UrlPathHelper();
        String searchUrl = "";
        String url = urlPathHelper.getOriginatingRequestUri(request);
        String query = urlPathHelper.getOriginatingQueryString(request);
        
        String splitUrl [] = url.split("\\/");
		//관리자 페이지 제외
		if(url.contains("/cms/"))
			return;
		
		
		String bbsId  = request.getParameter("bbsId");
		String pageId  = request.getParameter("pageId");
		String nttNo = request.getParameter("nttNo");
		String seq = request.getParameter("seq");

		if(url.indexOf("/user/board") > -1){
			searchUrl = bbsId;
			
		}else if(url.indexOf("/content.do") > -1){
			searchUrl = pageId;
		}else if("/".equals(url) || url.indexOf("/main.do") > -1){
			searchUrl = "/main.do";
		}else{
			if(splitUrl.length > 3){
				searchUrl = "/" + splitUrl[1] + "/" + splitUrl[2] +"/" ;
			}else{
				searchUrl = url;
			}
		}
		
        String copyUrl = "";
        if(nttNo != null && !nttNo.isEmpty()){
        	copyUrl = url+"?bbsId="+bbsId + "&nttNo="+nttNo;
        }else if(seq != null && !seq.isEmpty()){
        	copyUrl = url + "?seq="+seq;
        }else if(pageId != null && !pageId.isEmpty()){
        	copyUrl = url + "?pageId="+pageId;
        }else{
        	copyUrl = url;
        }
        
        EgovMap menuInfo = userMainService.selectMenuInfo(searchUrl);
        
        Map<String,String> map = new HashMap<String,String>();
        map.put("copyUrl", copyUrl);
    	modelAndView.addObject("menuInfo", menuInfo);
    	modelAndView.addObject("copyUrl", map);
    	modelAndView.addObject("menuList", userMainService.selectMainMenuList());
		
		
		if(!copyUrl.isEmpty() && menuInfo != null && menuInfo.get("menuNav") != null && menuInfo.get("menuUrl") != null){
			pageStatVO.setMenuTitle(menuInfo.get("menuNav").toString());
			pageStatVO.setUrl(menuInfo.get("menuUrl").toString());
			cmsStatService.pagestatInsert(pageStatVO);
		}
				
		
	}
}
