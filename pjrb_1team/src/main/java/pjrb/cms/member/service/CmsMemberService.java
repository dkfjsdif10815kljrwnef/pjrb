package pjrb.cms.member.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 회원관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성 
 *  
 */

public interface CmsMemberService {

	public List<?> memberList(CmsMemberVO vo) throws Exception;
		
	public int memberListCnt(CmsMemberVO vo) throws Exception;

	public EgovMap memberView(CmsMemberVO vo) throws Exception;

	public void memberInsert(CmsMemberVO vo) throws Exception;
	
	public void memberUpdate(CmsMemberVO vo) throws Exception;
	
	public void memberDelete(CmsMemberVO vo) throws Exception;

	public void memberStatusUpdate(CmsMemberVO vo) throws Exception;
	
	public void memberGradeUpdate(CmsMemberVO vo) throws Exception;
	
	public void memberPassUpdate(CmsMemberVO vo) throws Exception;
	
	public void memberHPUpdate(CmsMemberVO vo) throws Exception;
	
	public int memberIdChk(CmsMemberVO vo) throws Exception;
	
	public EgovMap mainMemberCnt(CmsMemberVO vo) throws Exception;
	
	public EgovMap memberAuthChk(CmsMemberVO vo) throws Exception;

	public void memberPwWrong(CmsMemberVO vo) throws Exception;

	public void memberPwInit(CmsMemberVO vo) throws Exception;

	public int bisNumChk(CmsMemberVO vo) throws Exception;

	public void memberAllowYnUpdate(CmsMemberVO vo) throws Exception;

	public EgovMap memberInfoView(CmsMemberVO loginVO) throws Exception;
	
	public void insertSessionLogin(CmsMemberVO vo) throws Exception;
	
	public void deleteSessionLogin(CmsMemberVO vo) throws Exception;
	
	public EgovMap selectSessionLogin(CmsMemberVO vo) throws Exception;
	
	public void updateSessionLogin(CmsMemberVO vo) throws Exception;
	
	public EgovMap selectSessionLogin_id(CmsMemberVO vo) throws Exception;
	
	public void deleteSessionLoginAll(CmsMemberVO vo) throws Exception;
	
}

