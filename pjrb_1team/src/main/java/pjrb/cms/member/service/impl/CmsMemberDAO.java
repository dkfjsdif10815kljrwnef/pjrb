package pjrb.cms.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.etc.EgovFileScrty;
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

@Repository("cmsMemberDAO")
public class CmsMemberDAO extends EgovComAbstractDAO {
	
	public List<?> memberList(CmsMemberVO vo) throws Exception{
		return selectList("cmsMemberDAO.memberList",vo);
	}
	
	public int memberListCnt(CmsMemberVO vo) throws Exception{
		return (int) selectOne("cmsMemberDAO.memberListCnt",vo);
	}

	public EgovMap memberView(CmsMemberVO vo) throws Exception{
		if(vo.getType() != null && !vo.getType().equals("")){
			if(vo.getType().equals("admin")){
				return (EgovMap) selectOne("cmsMemberDAO.memberAdminView",vo);
			}else if(vo.getType().equals("general")){
				return (EgovMap) selectOne("cmsMemberDAO.memberGeneralView",vo);
			}else if(vo.getType().equals("orgnzt")){
				return (EgovMap) selectOne("cmsMemberDAO.memberOrgnztView",vo);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

	public void memberInsert(CmsMemberVO vo) throws Exception{
		String pass = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getEmplyrId());
		vo.setPassword(pass);
		if(vo.getType() != null && !vo.getType().equals("")){
			if(vo.getType().equals("admin")){
				insert("cmsMemberDAO.memberAdminInsert",vo);
			}else if(vo.getType().equals("general")){
				insert("cmsMemberDAO.memberGeneralInsert",vo);
			}else if(vo.getType().equals("orgnzt")){
				insert("cmsMemberDAO.memberOrgnztInsert",vo);
			}
		}
	}

	public void memberUpdate(CmsMemberVO vo) throws Exception{
		String pass = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getEmplyrId());
		vo.setPassword(pass);
		
		if(vo.getType() != null && !vo.getType().equals("")){
			if(vo.getType().equals("admin")){
				update("cmsMemberDAO.memberAdminUpdate",vo);
			}else if(vo.getType().equals("general")){
				update("cmsMemberDAO.memberGeneralUpdate",vo);
			}else if(vo.getType().equals("orgnzt")){
				update("cmsMemberDAO.memberOrgnztUpdate",vo);
			}
		}
	}
	
	public void memberDelete(CmsMemberVO vo) throws Exception{
		if(vo.getType() != null && !vo.getType().equals("")){
			if(vo.getType().equals("admin")){
				delete("cmsMemberDAO.memberAdminDelete",vo);
			}else if(vo.getType().equals("general")){
				delete("cmsMemberDAO.memberGeneralDelete",vo);
			}else if(vo.getType().equals("orgnzt")){
				delete("cmsMemberDAO.memberOrgnztDelete",vo);
			}
		}
	}
	
	public void memberStatusUpdate(CmsMemberVO vo) throws Exception{
		if(vo.getType() != null && !vo.getType().equals("")){
			if(vo.getType().equals("admin")){
				update("cmsMemberDAO.memberAdminStatusUpdate",vo);
			}else if(vo.getType().equals("general")){
				update("cmsMemberDAO.memberGeneralStatusUpdate",vo);
			}else if(vo.getType().equals("orgnzt")){
				update("cmsMemberDAO.memberOrgnztStatusUpdate",vo);
			}
		}
	}
	
	public void memberGradeUpdate(CmsMemberVO vo) throws Exception{
		update("cmsMemberDAO.memberGradeUpdate",vo);
	}
	
	public void memberPassUpdate(CmsMemberVO vo) throws Exception{
		String pass = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getEmplyrId());
		vo.setPassword(pass);
		if(vo.getType() != null && !vo.getType().equals("")){
			if(vo.getType().equals("admin")){
				update("cmsMemberDAO.memberAdminPassUpdate",vo);
			}else if(vo.getType().equals("general")){
				update("cmsMemberDAO.memberGeneralPassUpdate",vo);
			}else if(vo.getType().equals("orgnzt")){
				update("cmsMemberDAO.memberOrgnztPassUpdate",vo);
			}
		}
	}
	
	public void memberHPUpdate(CmsMemberVO vo) throws Exception{
		update("cmsMemberDAO.memberHPUpdate",vo);
	}
	
	public int memberIdChk(CmsMemberVO vo) throws Exception{
		return (int) selectOne("cmsMemberDAO.memberIdChk",vo);
	}
	
	public EgovMap mainMemberCnt(CmsMemberVO vo) throws Exception{
		return (EgovMap) selectOne("cmsMemberDAO.mainMemberCnt",vo);
	}
	
	public EgovMap memberAuchChk(CmsMemberVO vo) throws Exception{
		return (EgovMap) selectOne("cmsMemberDAO.memberAuthChk",vo);
	}

	public void memberPwWrong(CmsMemberVO vo) throws Exception{
		if(vo.getType() != null && !vo.getType().equals("")){
			if(vo.getType().equals("admin")){
				update("cmsMemberDAO.memberAdminPwWrong",vo);
			}else if(vo.getType().equals("general")){
				update("cmsMemberDAO.memberGeneralPwWrong",vo);
			}else if(vo.getType().equals("orgnzt")){
				update("cmsMemberDAO.memberOrgnztPwWrong",vo);
			}
		}
	}

	public void memberPwInit(CmsMemberVO vo) throws Exception{
		if(vo.getType() != null && !vo.getType().equals("")){
			if(vo.getType().equals("admin")){
				update("cmsMemberDAO.memberAdminPwInit",vo);
			}else if(vo.getType().equals("general")){
				update("cmsMemberDAO.memberGeneralPwInit",vo);
			}else if(vo.getType().equals("orgnzt")){
				update("cmsMemberDAO.memberOrgnztPwInit",vo);
			}
		}
	}

	public int bisNumChk(CmsMemberVO vo) throws Exception{
		return (int) selectOne("cmsMemberDAO.bisNumChk",vo);
	}

	public void memberAllowYnUpdate(CmsMemberVO vo) throws Exception{
		if(vo.getType() != null && !vo.getType().equals("")){
			/*if(vo.getType().equals("admin")){
				update("cmsMemberDAO.memberAdminPwInit",vo);
			}else if(vo.getType().equals("general")){
				update("cmsMemberDAO.memberGeneralPwInit",vo);
			}*/
			
			if(vo.getType().equals("orgnzt")){
				update("cmsMemberDAO.memberOrgnztAllowYnUpdate",vo);
			}
		}
	}

	public EgovMap memberInfoView(CmsMemberVO vo) throws Exception{
		return (EgovMap) selectOne("cmsMemberDAO.memberInfoView",vo);
	}
	

	public void insertSessionLogin(CmsMemberVO vo) throws Exception{
		insert("cmsMemberDAO.insertSessionLogin",vo);
	}
	
	public void deleteSessionLogin(CmsMemberVO vo) throws Exception{
		delete("cmsMemberDAO.deleteSessionLogin",vo);
	}
	
	public void deleteSessionLoginAll(CmsMemberVO vo) throws Exception{
		delete("cmsMemberDAO.deleteSessionLoginAll",vo);
	}
	
	public void updateSessionLogin(CmsMemberVO vo) throws Exception{
		update("cmsMemberDAO.updateSessionLogin",vo);
	}

	public EgovMap selectSessionLogin(CmsMemberVO vo) throws Exception{
		return (EgovMap)selectOne("cmsMemberDAO.selectSessionLogin",vo);
	}
	
	public EgovMap selectSessionLogin_id(CmsMemberVO vo) throws Exception{
		return (EgovMap)selectOne("cmsMemberDAO.selectSessionLogin_id",vo);
	}
}
