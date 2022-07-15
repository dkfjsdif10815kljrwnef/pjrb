package pjrb.cms.stat.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.stat.service.CmsConstatVO;
import pjrb.cms.stat.service.CmsPagestatVO;

/**
 * 접속,페이지통계
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성 
 *  
 */

@Repository("cmsStatsDAO")
public class CmsStatDAO extends EgovComAbstractDAO {

	public List<?> constatList(CmsConstatVO constatVO) throws Exception {
		return selectList("cmsStatsDAO.constatList", constatVO);
	}
	
	public int constatListCnt(CmsConstatVO constatVO) throws Exception {
		return (int) selectOne("cmsStatsDAO.constatListCnt", constatVO);
	}
	
	public void constatInsert(CmsConstatVO constatVO) throws Exception {
		insert("cmsStatsDAO.constatInsert", constatVO);
	}	
	
	public List<?> visitPageList(CmsPagestatVO pagestatVO) throws Exception {
		return selectList("cmsStatsDAO.selectVisitPageList", pagestatVO);
	}
	
	public int visitPageListCnt(CmsPagestatVO pagestatVO) throws Exception {
		return (int) selectOne("cmsStatsDAO.selectVisitPageListCnt", pagestatVO);
	}
	
	public void visitPageInsert(CmsPagestatVO pagestatVO) throws Exception {
		insert("cmsStatsDAO.selectVisitPageInsert", pagestatVO);
	}	
	
	public EgovMap selectMenuName(CmsPagestatVO pagestatVO) throws Exception {
		return (EgovMap) selectOne("cmsStatsDAO.selectMenuName", pagestatVO);
	}

	public EgovMap selectEngMenuName(CmsPagestatVO pagestatVO) throws Exception {
		return (EgovMap) selectOne("cmsStatsDAO.selectEngMenuName", pagestatVO);
	}	
}
