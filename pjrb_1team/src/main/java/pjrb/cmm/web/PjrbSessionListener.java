package pjrb.cmm.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cmm.LoginVO;
import pjrb.cms.member.service.CmsMemberService;
import pjrb.cms.member.service.CmsMemberVO;

public class PjrbSessionListener implements HttpSessionListener{

	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(se.getSession().getServletContext());
		CmsMemberService member =  (CmsMemberService) ctx.getBean("cmsMemberService");

		LoginVO session = (LoginVO) se.getSession().getAttribute("loginVO");
		CmsMemberVO vo = new CmsMemberVO();
		if(session != null){
			vo.setEmplyrId(session.getEmplyrId());
			vo.setUuid(se.getSession().getId());
	        try {
	        	member.deleteSessionLogin(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
	        try {
	        	member.deleteSessionLoginAll(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	

}
