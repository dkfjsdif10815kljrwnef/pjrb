package pjrb.cms.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.popup.service.CmsPopupDAO;
import pjrb.cms.popup.service.CmsPopupService;
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

@Service("cmsPopupService")
public class CmsPopupServiceImpl implements CmsPopupService{

	@Resource(name="cmsPopupDAO")
	private CmsPopupDAO cmsPopupDAO;
	
	@Override
	public List<?> popupList(CmsPopupVO vo) throws Exception{
		return cmsPopupDAO.popupList(vo);
	}

	@Override
	public int popupListCnt(CmsPopupVO vo) throws Exception{
		return cmsPopupDAO.popupListCnt(vo);
	}

	@Override
	public EgovMap popupView(CmsPopupVO vo) throws Exception{
		return cmsPopupDAO.popupView(vo);
	}

	@Override
	public void popupInsert(CmsPopupVO vo) throws Exception{
		cmsPopupDAO.popupInsert(vo);
	}

	@Override
	public void popupUpdate(CmsPopupVO vo) throws Exception{
		cmsPopupDAO.popupUpdate(vo);
	}
	
	@Override
	public void popupDelete(CmsPopupVO vo) throws Exception{
		cmsPopupDAO.popupDelete(vo);
	}
	
	@Override
	public void popupStatusUpdate(CmsPopupVO vo) throws Exception{
		cmsPopupDAO.popupStatusUpdate(vo);
	}
	
}
