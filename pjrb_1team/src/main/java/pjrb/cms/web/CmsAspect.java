package pjrb.cms.web;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import pjrb.user.service.PjrbDefaultVO;

@Aspect
public class CmsAspect {
    @Pointcut("execution(public * pjrb.cms.*.web.*Controller.*(..))")
    private void checkAccess(){}

    Map<String, String> mappingMap = new HashMap<String, String>();
    Map<String, String> mappingMapSub = new HashMap<String, String>();
    
    @Around(value = "checkAccess()")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("#### pjrbcmsAspect 시작 ####");
		
		Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
		String requestMapping = method.getAnnotation(RequestMapping.class).value()[0];
		
		List<String> loginCheckExcept = new ArrayList<String>();
		
		loginCheckExcept.add("/cms/actionLogin.do");
		loginCheckExcept.add("/cms/actionLogout.do");
		loginCheckExcept.add("/cms/login.do");
		loginCheckExcept.add("/cms/pwChange.do");
		loginCheckExcept.add("/cms/pwChangePage.do");
		//loginCheckExcept.add("/cms/main.do");
		loginCheckExcept.add("/cms/excelDown.do");
		loginCheckExcept.add("/cms/memberListPopup.do");
		loginCheckExcept.add("/cms/stats/conStatList.do");
		loginCheckExcept.add("/cms/stats/pageStatList.do");
		loginCheckExcept.add("/dupleLoginCheck.do");
		
		LoginVO loginUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();	
		if(loginUser==null){
			if(!loginCheckExcept.contains(requestMapping)){
				return "redirect:/cms/login.do?returnValue=2";
			}
		}else{
			if(loginUser.getSessionGrade() == null || loginUser.getSessionGrade().equals("") || loginUser.getSessionGrade().isEmpty())
			{
				return "redirect:/cms/main.do?returnValue=3";
			}
		}
		
		mappingMap.put("/cms/member/", "MngMember");				//회원관리
		mappingMap.put("/cms/popup/", "MngPopup");					//팝업관리
		mappingMap.put("/cms/bbsmng/", "MngBbs");					//게시판,페이지 관리
		mappingMap.put("/cms/bbs/", "MngBbs");						//게시물관리
		mappingMap.put("/cms/banner/", "MngBanner");				//배너,관련사이트관리
		//mappingMap.put("/cms/stats/", "MngStat");					//통계관리
		mappingMap.put("/cms/grade/", "MngGrade");					//권한관리
		mappingMap.put("/cms/accessip/", "MngIp");					//접근 ip관리
		mappingMap.put("/cms/accesslog/", "MngLog");				//접근 로그 관리
		mappingMap.put("/cms/menu/", "MngMenu");					//메뉴관리
		//mappingMap.put("/cms/pagemanager/", "MngPagemanager");	//담당자관리
		
		int inx = requestMapping.lastIndexOf("/");
		requestMapping = requestMapping.substring(0,inx+1);
		if(mappingMap.containsKey(requestMapping)){ 
			Object[] arr = joinPoint.getArgs();
			int i = 0;
			
			/*if(requestMapping.equals("/cms/bbs/")){
				for(Object object : arr) {
					if(object instanceof BoardVO) {
						LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();	
						if(user!=null){
							((BoardVO) arr[i]).setSessionId(user.getEmplyrId());
							
							Class<?> cls = LoginVO.class;
							String methodName = "get" + mappingMap.get(requestMapping);
							Method method2 = cls.getDeclaredMethod(methodName);
							String grade = (String) method2.invoke(user, new Object[]{});
											
							if(!user.getSessionGrade().equals("0") && !user.getSessionGrade().equals("1")){
								if(grade == null || grade.isEmpty() || !grade.equals("Y")) return "redirect:/cms/main.do?returnValue=3";
							}
							((BoardVO) arr[i]).setSessionGrade(grade);
						}
					}
					i++;
				}
			}else if(requestMapping.equals("/cms/bbsmng/")){
				for(Object object : arr) {
					if(object instanceof BoardMasterVO) {
						LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();	
						if(user!=null){
							((BoardMasterVO) arr[i]).setSessionId(user.getEmplyrId());
							
							Class<?> cls = LoginVO.class;
							String methodName = "get" + mappingMap.get(requestMapping);
							Method method2 = cls.getDeclaredMethod(methodName);
							String grade = (String) method2.invoke(user, new Object[]{});
											
							if(!user.getSessionGrade().equals("0") && !user.getSessionGrade().equals("1")){
								if(grade == null || grade.isEmpty() || !grade.equals("Y")) return "redirect:/cms/main.do?returnValue=3";
							}
							((BoardMasterVO) arr[i]).setSessionGrade(grade);
						}
					}
					i++;
				}
			}else{*/
				for(Object object : arr) {
					if(object instanceof PjrbDefaultVO) {
						LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();	
						if(user!=null){
							((PjrbDefaultVO) arr[i]).setSessionId(user.getEmplyrId());
							
							Class<?> cls = LoginVO.class;
							String methodName = "get" + mappingMap.get(requestMapping);
							Method method2 = cls.getDeclaredMethod(methodName);
							String grade = (String) method2.invoke(user, new Object[]{});
											
							if(!user.getSessionGrade().equals("0") && !user.getSessionGrade().equals("1")){
								if(grade == null || grade.isEmpty() || !grade.equals("Y")) return "redirect:/cms/main.do?returnValue=3";
							}
							((PjrbDefaultVO) arr[i]).setSessionGrade(grade);
						}
					}
					
					i++;
				}
			/*}*/
			
		}else {
			Object[] arr = joinPoint.getArgs();
			int i = 0;
			for(Object object : arr) {
				if(object instanceof PjrbDefaultVO) {
					LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();	
					if(user!=null){
						((PjrbDefaultVO) arr[i]).setSessionId(user.getEmplyrId());
					}
				}
				
				i++;
			}
		}
		
		Object result = joinPoint.proceed();
		System.out.println("#### CmsAspect 끝 ####");     
		return result;
	}
}
