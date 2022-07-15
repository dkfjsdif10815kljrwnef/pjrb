package pjrb.cms.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import pjrb.cms.member.service.CmsMemberVO;

@Repository("CmsTempMemberDAO")
public class CmsTempMemberDAO  extends EgovComAbstractDAO{
	
	public List<?> memberList(CmsMemberVO vo) throws Exception{
		return selectList("cmsTempMemberDAO.memberList",vo);
	}
	public int memberListCnt(CmsMemberVO vo) throws Exception{
		return selectOne("cmsTempMemberDAO.memberListCnt",vo);
	}


}
