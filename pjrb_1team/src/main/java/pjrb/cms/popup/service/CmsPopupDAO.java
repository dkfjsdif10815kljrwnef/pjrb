package pjrb.cms.popup.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.popup.service.CmsPopupVO;

/**
 * 팝업관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성 
 *  
 */

@Repository("cmsPopupDAO")
public class CmsPopupDAO extends EgovComAbstractDAO {
	
	public List<?> popupList(CmsPopupVO vo) throws Exception{
		return selectList("cmsPopupDAO.popupList",vo);
	}
	
	public int popupListCnt(CmsPopupVO vo) throws Exception{
		return (int) selectOne("cmsPopupDAO.popupListCnt",vo);
	}

	public EgovMap popupView(CmsPopupVO vo) throws Exception{
		return (EgovMap) selectOne("cmsPopupDAO.popupView",vo);
	}

	public void popupInsert(CmsPopupVO vo) throws Exception{
		insert("cmsPopupDAO.popupInsert",vo);
	}

	public void popupUpdate(CmsPopupVO vo) throws Exception{
		update("cmsPopupDAO.popupUpdate",vo);
	}
	
	public void popupDelete(CmsPopupVO vo) throws Exception{
		delete("cmsPopupDAO.popupDelete",vo);
	}
	
	public void popupStatusUpdate(CmsPopupVO vo) throws Exception{
		update("cmsPopupDAO.popupStatusUpdate",vo);
	}
	
}
