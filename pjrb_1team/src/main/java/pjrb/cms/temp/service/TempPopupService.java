package pjrb.cms.temp.service;

import java.util.List;

import pjrb.cms.popup.service.CmsPopupVO;

public interface TempPopupService {
	public void insert(CmsPopupVO vo) throws Exception;
	public int popupListCnt(CmsPopupVO vo) throws Exception;
	public List<?> popupList(CmsPopupVO vo) throws Exception;
	public List<?> popupView(CmsPopupVO vo) throws Exception;
	public void popupDelete(CmsPopupVO vo) throws Exception;

}
