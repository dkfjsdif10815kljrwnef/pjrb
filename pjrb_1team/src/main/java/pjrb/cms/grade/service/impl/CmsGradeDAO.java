package pjrb.cms.grade.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.grade.service.CmsGradeInfoVO;
import pjrb.cms.grade.service.CmsGradeVO;

/**
 * 권한관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.16
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.16  권대성          최초 생성 
 *  
 */

@Repository("cmsGradeDAO")
public class CmsGradeDAO extends EgovComAbstractDAO {
	
	public List<?> gradeList(CmsGradeVO vo) throws Exception{
		return selectList("cmsGradeDAO.gradeList",vo);
	}
	
	public EgovMap gradeView(CmsGradeVO vo) throws Exception{
		return (EgovMap) selectOne("cmsGradeDAO.gradeView",vo);
	}

	public void gradeUpdate(CmsGradeVO vo) throws Exception{
		update("cmsGradeDAO.gradeUpdate",vo);
	}
	
	public List<?> gradeInfoList(CmsGradeInfoVO vo) throws Exception{
		return selectList("cmsGradeDAO.gradeInfoList",vo);
	}
	
	public void gradeInfoInsert(CmsGradeInfoVO vo) throws Exception{
		insert("cmsGradeDAO.gradeInfoInsert",vo);
	}
	
	public void gradeInfoUpdate(CmsGradeInfoVO vo) throws Exception{
		update("cmsGradeDAO.gradeInfoUpdate",vo);
	}
	
	public void gradeInfoDelete(CmsGradeInfoVO vo) throws Exception{
		delete("cmsGradeDAO.gradeInfoDelete",vo);
	}
	
	public int gradeInfoListCnt(CmsGradeInfoVO vo) throws Exception{
		return selectOne("cmsGradeDAO.gradeInfoListCnt",vo);
	}
}
