package pjrb.cms.popup.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

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

public interface CmsPopupService {

	public List<?> popupList(CmsPopupVO vo) throws Exception;
		
	public int popupListCnt(CmsPopupVO vo) throws Exception;

	public EgovMap popupView(CmsPopupVO vo) throws Exception;

	public void popupInsert(CmsPopupVO vo) throws Exception;
	
	public void popupUpdate(CmsPopupVO vo) throws Exception;
	
	public void popupDelete(CmsPopupVO vo) throws Exception;

	public void popupStatusUpdate(CmsPopupVO vo) throws Exception;
	
}
