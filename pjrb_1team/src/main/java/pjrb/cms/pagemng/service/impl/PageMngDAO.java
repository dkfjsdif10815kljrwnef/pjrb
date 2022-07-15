package pjrb.cms.pagemng.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.pagemng.service.PageMngVO;

@Repository("pageMngDAO")
public class PageMngDAO extends EgovComAbstractDAO{

	public List<?> selectPageMngList(PageMngVO pageMngVO) throws Exception{
		return selectList("pageMngDAO.selectPageMngList",pageMngVO);
	}
	
	public int selectPageMngListCnt(PageMngVO pageMngVO) throws Exception{
		return (int) selectOne("pageMngDAO.selectPageMngListCnt",pageMngVO);
	}

	public EgovMap selectPageMngDetail(PageMngVO pageMngVO) throws Exception{
		return (EgovMap) selectOne("pageMngDAO.selectPageMngDetail",pageMngVO);
	}
	
	public void insertPageMng(PageMngVO pageMngVO) throws Exception{
		 insert("pageMngDAO.insertPageMng",pageMngVO);
	}
	
	public void updatePageMng(PageMngVO pageMngVO) throws Exception{
		 update("pageMngDAO.updatePageMng",pageMngVO);
	}
	
	public void updatePageMngUseYn(PageMngVO pageMngVO) throws Exception{
		 update("pageMngDAO.updatePageMngUseYn",pageMngVO);
	}

	public void deletePageMng(PageMngVO pageMngVO) throws Exception{
		 delete("pageMngDAO.deletePageMng",pageMngVO);
	}
}
