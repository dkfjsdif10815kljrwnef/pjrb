package pjrb.cms.grade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.grade.service.CmsGradeInfoVO;
import pjrb.cms.grade.service.CmsGradeService;
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

@Service("cmsGradeService")
public class CmsGradeServiceImpl implements CmsGradeService{

	@Resource(name="cmsGradeDAO")
	private CmsGradeDAO cmsGradeDAO;
	
	@Override
	public List<?> gradeList(CmsGradeVO vo) throws Exception{
		return cmsGradeDAO.gradeList(vo);
	}

	@Override
	public EgovMap gradeView(CmsGradeVO vo) throws Exception{
		return cmsGradeDAO.gradeView(vo);
	}

	@Override
	public void gradeUpdate(CmsGradeVO vo) throws Exception{
		cmsGradeDAO.gradeUpdate(vo);
	}

	@Override
	public List<?> gradeInfoList(CmsGradeInfoVO vo) throws Exception {
		return cmsGradeDAO.gradeInfoList(vo);
	}

	@Override
	public void gradeInfoInsert(CmsGradeInfoVO vo) throws Exception {
		cmsGradeDAO.gradeInfoInsert(vo);
	}

	@Override
	public void gradeInfoUpdate(CmsGradeInfoVO vo) throws Exception {
		cmsGradeDAO.gradeInfoUpdate(vo);
	}

	@Override
	public void gradeInfoDelete(CmsGradeInfoVO vo) throws Exception {
		cmsGradeDAO.gradeInfoDelete(vo);
	}

	@Override
	public int gradeInfoListCnt(CmsGradeInfoVO vo) throws Exception {
		return cmsGradeDAO.gradeInfoListCnt(vo);
	}
	
	
	
}
