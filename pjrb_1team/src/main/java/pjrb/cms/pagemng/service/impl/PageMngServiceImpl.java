package pjrb.cms.pagemng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.pagemng.service.PageMngService;
import pjrb.cms.pagemng.service.PageMngVO;

@Service("pageMngService")
public class PageMngServiceImpl implements PageMngService{
	
	@Resource(name="pageMngDAO")
	private PageMngDAO pageMngDAO;
	
	@Resource(name = "PageMngIdGnrService")
    private EgovIdGnrService idgenService;

	@Override
	public List<?> selectPageMngList(PageMngVO pageMngVO) throws Exception {
		// TODO Auto-generated method stub
		return pageMngDAO.selectPageMngList(pageMngVO);
	}

	@Override
	public int selectPageMngListCnt(PageMngVO pageMngVO) throws Exception {
		// TODO Auto-generated method stub
		return pageMngDAO.selectPageMngListCnt(pageMngVO);
	}

	@Override
	public EgovMap selectPageMngDetail(PageMngVO pageMngVO) throws Exception {
		// TODO Auto-generated method stub
		return pageMngDAO.selectPageMngDetail(pageMngVO);
	}

	@Override
	public void insertPageMng(PageMngVO pageMngVO) throws Exception {
		// TODO Auto-generated method stub
		String id = idgenService.getNextStringId();
		pageMngVO.setPageId(id);
		pageMngDAO.insertPageMng(pageMngVO);
	}

	@Override
	public void updatePageMng(PageMngVO pageMngVO) throws Exception {
		// TODO Auto-generated method stub
		pageMngDAO.updatePageMng(pageMngVO);
	}

	@Override
	public void updatePageMngUseYn(PageMngVO pageMngVO) throws Exception {
		// TODO Auto-generated method stub
		pageMngDAO.updatePageMngUseYn(pageMngVO);
	}

	@Override
	public void deletePageMng(PageMngVO pageMngVO) throws Exception {
		// TODO Auto-generated method stub
		pageMngDAO.deletePageMng(pageMngVO);
	}

}
