package pjrb.cms.login.service;

import egovframework.com.cmm.LoginVO;

/**
 * 일반로그인
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.16
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.16  권대성          최초 생성 
 *  
 */
public interface LoginService {
	
    LoginVO actionLogin(LoginVO vo) throws Exception;
    LoginVO actionSnsLogin(LoginVO vo) throws Exception;
    
}
