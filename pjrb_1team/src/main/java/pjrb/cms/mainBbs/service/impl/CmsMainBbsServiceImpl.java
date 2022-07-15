package pjrb.cms.mainBbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.bbs.service.CmsBbsVO;
import pjrb.cms.mainBbs.service.CmsMainBbsService;
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

@Service("cmsMainBbsService")
public class CmsMainBbsServiceImpl implements CmsMainBbsService{

	@Resource(name="cmsMainBbsDAO")
	private CmsMainBbsDAO cmsMainBbsDAO;
	
	@Override
	public List<?> mainBbsList(CmsMainBbsVO vo) throws Exception{
		return cmsMainBbsDAO.mainBbsList(vo);
	}

	@Override
	public int mainBbsListCnt(CmsMainBbsVO vo) throws Exception{
		return cmsMainBbsDAO.mainBbsListCnt(vo);
	}

	@Override
	public void mainBbsInsert(CmsMainBbsVO vo) throws Exception{
		cmsMainBbsDAO.mainBbsInsert(vo);
	}

	@Override
	public void mainBbsUpdate(CmsMainBbsVO vo) throws Exception{
		cmsMainBbsDAO.mainBbsUpdate(vo);
	}
	
	@Override
	public void mainBbsDelete(CmsMainBbsVO vo) throws Exception{
		cmsMainBbsDAO.mainBbsDelete(vo);
	}

	@Override
	public List<?> mainBbsModalList(CmsBbsVO vo) throws Exception {
		return cmsMainBbsDAO.mainBbsModalList(vo);
	}

	@Override
	public int mainBbsModalListCnt(CmsBbsVO vo) throws Exception {
		return cmsMainBbsDAO.mainBbsModalListCnt(vo);
	}

	@Override
	public List<?> mainBbsContentsModalList(CmsBbsVO vo) throws Exception {
		return cmsMainBbsDAO.mainBbsContentsModalList(vo);
	}

	@Override
	public int mainBbsContentsModalListCnt(CmsBbsVO vo) throws Exception {
		return cmsMainBbsDAO.mainBbsContentsModalListCnt(vo);
	}
	
}
