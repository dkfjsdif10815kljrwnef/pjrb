package pjrb.cms.stat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.stat.service.CmsConstatVO;
import pjrb.cms.stat.service.CmsPagestatVO;
import pjrb.cms.stat.service.CmsStatService;

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

@Service("cmsStatService")
public class CmsStatServiceImpl extends EgovAbstractServiceImpl implements CmsStatService{

    @Resource(name="cmsStatsDAO")
    private CmsStatDAO cmsStatDAO;

	@Override
	public List<?> constatList(CmsConstatVO constatVO) throws Exception {
		return cmsStatDAO.constatList(constatVO);
	}

	@Override
	public int constatListCnt(CmsConstatVO constatVO) throws Exception {
		return cmsStatDAO.constatListCnt(constatVO);
	}

	@Override
	public void constatInsert(CmsConstatVO constatVO) throws Exception {
		cmsStatDAO.constatInsert(constatVO);
	}

	@Override
	public List<?> pagestatList(CmsPagestatVO pagestatVO) throws Exception {
		return cmsStatDAO.visitPageList(pagestatVO);
	}

	@Override
	public int pagestatListCnt(CmsPagestatVO pagestatVO) throws Exception {
		return cmsStatDAO.visitPageListCnt(pagestatVO);
	}

	@Override
	public void pagestatInsert(CmsPagestatVO pagestatVO) throws Exception {
		cmsStatDAO.visitPageInsert(pagestatVO);
	}

	@Override
	public EgovMap selectMenuName(CmsPagestatVO pagestatVO) throws Exception {
		return cmsStatDAO.selectMenuName(pagestatVO);
	}

	@Override
	public EgovMap selectEngMenuName(CmsPagestatVO pagestatVO) throws Exception {
		return cmsStatDAO.selectEngMenuName(pagestatVO);
	}

    
    
}
