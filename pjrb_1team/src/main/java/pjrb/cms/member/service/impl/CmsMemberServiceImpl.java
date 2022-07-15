package pjrb.cms.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.member.service.CmsMemberService;
import pjrb.cms.member.service.CmsMemberVO;

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

@Service("cmsMemberService")
public class CmsMemberServiceImpl implements CmsMemberService{

	@Resource(name="cmsMemberDAO")
	private CmsMemberDAO cmsMemberDAO;
	
	@Override
	public List<?> memberList(CmsMemberVO vo) throws Exception{
		return cmsMemberDAO.memberList(vo);
	}

	@Override
	public int memberListCnt(CmsMemberVO vo) throws Exception{
		return cmsMemberDAO.memberListCnt(vo);
	}

	@Override
	public EgovMap memberView(CmsMemberVO vo) throws Exception{
		return cmsMemberDAO.memberView(vo);
	}

	@Override
	public void memberInsert(CmsMemberVO vo) throws Exception{
		cmsMemberDAO.memberInsert(vo);
	}

	@Override
	public void memberUpdate(CmsMemberVO vo) throws Exception{
		cmsMemberDAO.memberUpdate(vo);
	}
	
	@Override
	public void memberDelete(CmsMemberVO vo) throws Exception{
		cmsMemberDAO.memberDelete(vo);
	}
	
	@Override
	public void memberStatusUpdate(CmsMemberVO vo) throws Exception{
		cmsMemberDAO.memberStatusUpdate(vo);
	}
	
	@Override
	public void memberGradeUpdate(CmsMemberVO vo) throws Exception{
		cmsMemberDAO.memberGradeUpdate(vo);
	}

	@Override
	public void memberPassUpdate(CmsMemberVO vo) throws Exception {
		cmsMemberDAO.memberPassUpdate(vo);
	}

	@Override
	public void memberHPUpdate(CmsMemberVO vo) throws Exception {
		cmsMemberDAO.memberHPUpdate(vo);
	}
	
	@Override
	public int memberIdChk(CmsMemberVO vo) throws Exception{
		return cmsMemberDAO.memberIdChk(vo);
	}

	@Override
	public EgovMap mainMemberCnt(CmsMemberVO vo) throws Exception {
		return cmsMemberDAO.mainMemberCnt(vo);
	}

	@Override
	public EgovMap memberAuthChk(CmsMemberVO vo) throws Exception {
		return cmsMemberDAO.memberAuchChk(vo);
	}

	@Override
	public void memberPwWrong(CmsMemberVO vo) throws Exception {
		cmsMemberDAO.memberPwWrong(vo);
	}

	@Override
	public void memberPwInit(CmsMemberVO vo) throws Exception {
		cmsMemberDAO.memberPwInit(vo);
	}

	@Override
	public int bisNumChk(CmsMemberVO vo) throws Exception {
		return cmsMemberDAO.bisNumChk(vo);
	}

	@Override
	public void memberAllowYnUpdate(CmsMemberVO vo) throws Exception {
		cmsMemberDAO.memberAllowYnUpdate(vo);
	}

	@Override
	public EgovMap memberInfoView(CmsMemberVO vo) throws Exception {
		return cmsMemberDAO.memberInfoView(vo);
	}

	@Override
	public void insertSessionLogin(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		cmsMemberDAO.insertSessionLogin(vo);
	}

	@Override
	public void deleteSessionLogin(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		cmsMemberDAO.deleteSessionLogin(vo);
	}

	@Override
	public EgovMap selectSessionLogin(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return cmsMemberDAO.selectSessionLogin(vo);
	}
	
	@Override
	public void updateSessionLogin(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		cmsMemberDAO.updateSessionLogin(vo);
	}

	@Override
	public EgovMap selectSessionLogin_id(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return cmsMemberDAO.selectSessionLogin_id(vo);
	}

	@Override
	public void deleteSessionLoginAll(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		cmsMemberDAO.deleteSessionLoginAll(vo);
	}

	
}
