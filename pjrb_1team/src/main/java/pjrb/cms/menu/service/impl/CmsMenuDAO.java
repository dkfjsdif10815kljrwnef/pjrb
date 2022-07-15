package pjrb.cms.menu.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
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

@Repository("cmsMenuDAO")
public class CmsMenuDAO extends EgovComAbstractDAO {

	public List<?> menuList(CmsMenuVO vo) throws Exception {
		return selectList("cmsMenuDAO.menuList", vo);
	}

	public EgovMap selectMenuInfo(CmsMenuVO menuVO) throws Exception {
		return (EgovMap) selectOne("cmsMenuDAO.selectMenuInfo", menuVO);
	}
	
	public List<?> menuSearch(CmsMenuVO menuVO) throws Exception {
		return selectList("cmsMenuDAO.menuSearch", menuVO);
	}
	
	public void menuInsert(CmsMenuVO menuVO) throws Exception {
		insert("cmsMenuDAO.menuInsert", menuVO);
	}
	
	public void menuUpdate(CmsMenuVO menuVO) throws Exception {
		update("cmsMenuDAO.menuUpdate", menuVO);
	}

	public List<?> menuSort(CmsMenuVO menuVO) throws Exception {
		return selectList("cmsMenuDAO.menuSort", menuVO);
	}

	public void menuSortUpdate(CmsMenuVO menuVO) throws Exception {
		update("cmsMenuDAO.menuSortUpdate", menuVO);
	}

	public void menuDelete_1depth(CmsMenuVO menuVO) throws Exception {
		delete("cmsMenuDAO.menuDelete_1depth", menuVO);
	}
	
	public void menuDelete_2depth(CmsMenuVO menuVO) throws Exception {
		delete("cmsMenuDAO.menuDelete_2depth", menuVO);
	}
	
	public void menuDelete_3depth(CmsMenuVO menuVO) throws Exception {
		delete("cmsMenuDAO.menuDelete_3depth", menuVO);
	}

	public void updateMenuNav(CmsMenuVO menuVO) throws Exception {
		update("cmsMenuDAO.updateMenuNav", menuVO);
	}
	
	public List<?> menuList_depth(CmsMenuVO menuVO) throws Exception {
		return selectList("cmsMenuDAO.menuList_depth", menuVO);
	}
}
