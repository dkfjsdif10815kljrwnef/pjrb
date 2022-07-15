package pjrb.cms.pagemng.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface PageMngService {

	public List<?> selectPageMngList(PageMngVO pageMngVO) throws Exception;
	
	public int selectPageMngListCnt(PageMngVO pageMngVO) throws Exception;

	public EgovMap selectPageMngDetail(PageMngVO pageMngVO) throws Exception;
	
	public void insertPageMng(PageMngVO pageMngVO) throws Exception;
	
	public void updatePageMng(PageMngVO pageMngVO) throws Exception;
	
	public void updatePageMngUseYn(PageMngVO pageMngVO) throws Exception;
	
	public void deletePageMng(PageMngVO pageMngVO) throws Exception;
}
