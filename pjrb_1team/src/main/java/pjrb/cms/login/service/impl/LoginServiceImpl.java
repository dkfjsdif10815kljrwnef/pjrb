package pjrb.cms.login.service.impl;

import egovframework.com.cmm.LoginVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import pjrb.cms.etc.EgovFileScrty;
import pjrb.cms.login.service.LoginService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
@Service("loginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {

    @Resource(name="loginDAO")
    private LoginDAO loginDAO;

    @Override
	public LoginVO actionLogin(LoginVO vo) throws Exception {

    	// 1. 입력한 비밀번호를 암호화한다.
		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getEmplyrId());
    	vo.setPassword(enpassword);

    	// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
    	LoginVO loginVO = loginDAO.actionLogin(vo);

    	// 3. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getEmplyrId().equals("") && !loginVO.getPassword().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}

    	return loginVO;
    }

	@Override
	public LoginVO actionSnsLogin(LoginVO vo) throws Exception {
		
		LoginVO loginVO = loginDAO.actionSnsLogin(vo);
		
		if (loginVO != null && !loginVO.getEmplyrId().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}
		
		return loginVO;
	}

}
