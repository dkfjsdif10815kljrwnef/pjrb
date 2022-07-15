package pjrb.cms.login.service.impl;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

import org.springframework.stereotype.Repository;

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
@Repository("loginDAO")
public class LoginDAO extends EgovComAbstractDAO {

    public LoginVO actionLogin(LoginVO vo) throws Exception {
    	return (LoginVO)selectOne("loginDAO.actionLogin", vo);
    }
    
    public LoginVO actionSnsLogin(LoginVO vo) throws Exception {
    	return (LoginVO)selectOne("loginDAO.actionSnsLogin", vo);
    }
    

}
