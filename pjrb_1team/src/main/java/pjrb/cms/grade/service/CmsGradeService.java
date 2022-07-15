package pjrb.cms.grade.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

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

public interface CmsGradeService {

	public List<?> gradeList(CmsGradeVO vo) throws Exception;
		
	public EgovMap gradeView(CmsGradeVO vo) throws Exception;

	public void gradeUpdate(CmsGradeVO vo) throws Exception;
	
	public List<?> gradeInfoList(CmsGradeInfoVO vo) throws Exception;
	
	public int gradeInfoListCnt(CmsGradeInfoVO vo) throws Exception;
	
	public void gradeInfoInsert(CmsGradeInfoVO vo) throws Exception;
	
	public void gradeInfoUpdate(CmsGradeInfoVO vo) throws Exception;
	
	public void gradeInfoDelete(CmsGradeInfoVO vo) throws Exception;
	
}
