package pjrb.user.main.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.pagemng.service.PageMngVO;

public interface UserMainService {

	public Map<String,Object> selectMainMenuList() throws Exception;
	
	public EgovMap selectContentDetail(PageMngVO pageMngVO) throws Exception;
	
	public EgovMap selectMenuInfo(String oriUrl) throws Exception;
	
	public EgovMap selectTotalSearchCnt(DefaultVO defaultVO) throws Exception;
	
	public List<?> selectTotalSearchMenuList(DefaultVO defaultVO) throws Exception;
	
	public List<?> selectTotalSearchBbsList(DefaultVO defaultVO) throws Exception;
	
	public List<?> selectTotalSearchContentsList(DefaultVO defaultVO) throws Exception;
	
	public List<?> selectSubMenuList(DefaultVO defaultVO) throws Exception;
	
	public List<?> selectSubMenu2Depth(DefaultVO defaultVO) throws Exception;
	
	public int selectTotalSearchMenuListCnt(DefaultVO defaultVO) throws Exception;
	
	public int selectTotalSearchBbsListCnt(DefaultVO defaultVO) throws Exception;
	
	public int selectTotalSearchContentsListCnt(DefaultVO defaultVO) throws Exception;
	
	public List<?> selectMainPopupList() throws Exception;
}
