package pjrb.cms.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.menu.service.CmsMenuService;
import pjrb.cms.menu.service.CmsMenuVO;

/**
 * 메뉴관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.16
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.16  권대성          최초 생성 
 *  
 */

@Service("cmsMenuService")
public class CmsMenuServiceImpl extends EgovAbstractServiceImpl implements CmsMenuService {

    @Resource(name="cmsMenuDAO")
    private CmsMenuDAO cmsMenuDAO;

	@Override
	public List<?> menuList(CmsMenuVO vo) throws Exception {
		return cmsMenuDAO.menuList(vo);
	}

	@Override
	public EgovMap selectMenuInfo(CmsMenuVO menuVO) throws Exception {
		return cmsMenuDAO.selectMenuInfo(menuVO);
	}

	@Override
	public void menuInsert(CmsMenuVO menuVO) throws Exception {
		cmsMenuDAO.menuInsert(menuVO);
	}
	
	@Override
	public void menuUpdate(CmsMenuVO menuVO) throws Exception {
		cmsMenuDAO.menuUpdate(menuVO);
	}

	@Override
	public List<?> menuSort(CmsMenuVO menuVO) throws Exception {
		return cmsMenuDAO.menuSort(menuVO);
	}

	@Override
	public void menuSortUpdate(CmsMenuVO menuVO) throws Exception {
		cmsMenuDAO.menuSortUpdate(menuVO);
	}

	@Override
	public void menuDelete_1depth(CmsMenuVO menuVO) throws Exception {
		cmsMenuDAO.menuDelete_1depth(menuVO);
	}

	@Override
	public void menuDelete_2depth(CmsMenuVO menuVO) throws Exception {
		cmsMenuDAO.menuDelete_2depth(menuVO);
	}

	@Override
	public void menuDelete_3depth(CmsMenuVO menuVO) throws Exception {
		cmsMenuDAO.menuDelete_3depth(menuVO);
	}

	@Override
	public List<?> menuList_depth(CmsMenuVO menuVO) throws Exception {
		return cmsMenuDAO.menuList_depth(menuVO);
	}

	@Override
	public List<?> menuSearch(CmsMenuVO menuVO) throws Exception {
		return cmsMenuDAO.menuSearch(menuVO);
	}
	
	@Override
	public void updateMenuNav(CmsMenuVO menuVO) throws Exception {
		// TODO Auto-generated method stub
		cmsMenuDAO.updateMenuNav(menuVO);
	}
}
