package pjrb.cms.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.member.service.CmsMemberService;
import pjrb.cms.member.service.CmsMemberVO;

@Service("cmsTempMemberService")
public class CmsTempMemberServiceImpl implements CmsMemberService {
	
	
	@Resource(name="CmsTempMemberDAO")
	private CmsTempMemberDAO CmsTempMemberDAO;

	@Override
	public List<?> memberList(CmsMemberVO vo) throws Exception {
		return CmsTempMemberDAO.memberList(vo);
	}

	@Override
	public int memberListCnt(CmsMemberVO vo) throws Exception {
		return CmsTempMemberDAO.memberListCnt(vo);
	}

	@Override
	public EgovMap memberView(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void memberInsert(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberUpdate(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberDelete(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberStatusUpdate(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberGradeUpdate(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberPassUpdate(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberHPUpdate(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int memberIdChk(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EgovMap mainMemberCnt(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EgovMap memberAuthChk(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void memberPwWrong(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberPwInit(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int bisNumChk(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void memberAllowYnUpdate(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EgovMap memberInfoView(CmsMemberVO loginVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertSessionLogin(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSessionLogin(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EgovMap selectSessionLogin(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSessionLogin(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EgovMap selectSessionLogin_id(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSessionLoginAll(CmsMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
