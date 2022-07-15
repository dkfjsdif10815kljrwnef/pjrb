package pjrb.cms.mainBbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.bbs.service.CmsBbsVO;
import pjrb.cms.mainBbs.service.CmsMainBbsVO;

/**
 * 메인 게시판 관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성 
 *  
 */

@Repository("cmsMainBbsDAO")
public class CmsMainBbsDAO extends EgovComAbstractDAO {
	
	public List<?> mainBbsList(CmsMainBbsVO vo) throws Exception{
		return selectList("cmsMainBbsDAO.mainBbsList",vo);
	}
	
	public int mainBbsListCnt(CmsMainBbsVO vo) throws Exception{
		return (int) selectOne("cmsMainBbsDAO.mainBbsListCnt",vo);
	}

	public void mainBbsInsert(CmsMainBbsVO vo) throws Exception{
		insert("cmsMainBbsDAO.mainBbsInsert",vo);
	}

	public void mainBbsUpdate(CmsMainBbsVO vo) throws Exception{
		update("cmsMainBbsDAO.mainBbsUpdate",vo);
	}
	
	public void mainBbsDelete(CmsMainBbsVO vo) throws Exception{
		delete("cmsMainBbsDAO.mainBbsDelete",vo);
	}
	
	public List<?> mainBbsModalList(CmsBbsVO vo) throws Exception{
		return selectList("cmsMainBbsDAO.mainBbsModalList",vo);
	}
	
	public int mainBbsModalListCnt(CmsBbsVO vo) throws Exception{
		return (int) selectOne("cmsMainBbsDAO.mainBbsModalListCnt",vo);
	}
	
	public List<?> mainBbsContentsModalList(CmsBbsVO vo) throws Exception{
		return selectList("cmsMainBbsDAO.mainBbsContentsModalList",vo);
	}
	
	public int mainBbsContentsModalListCnt(CmsBbsVO vo) throws Exception{
		return (int) selectOne("cmsMainBbsDAO.mainBbsContentsModalListCnt",vo);
	}
	
}
