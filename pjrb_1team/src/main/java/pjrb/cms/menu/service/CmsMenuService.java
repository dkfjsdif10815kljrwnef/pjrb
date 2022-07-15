package pjrb.cms.menu.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

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

public interface CmsMenuService {
	
	public List<?> menuList(CmsMenuVO vo) throws Exception;

	public EgovMap selectMenuInfo(CmsMenuVO menuVO) throws Exception;
	
	public List<?> menuSearch(CmsMenuVO menuVO) throws Exception;

	public void menuInsert(CmsMenuVO menuVO) throws Exception;

	public void menuUpdate(CmsMenuVO menuVO) throws Exception;

	public List<?> menuSort(CmsMenuVO menuVO) throws Exception;

	public void menuSortUpdate(CmsMenuVO menuVO) throws Exception;

	public void menuDelete_1depth(CmsMenuVO menuVO) throws Exception;
	
	public void menuDelete_2depth(CmsMenuVO menuVO) throws Exception;
	
	public void menuDelete_3depth(CmsMenuVO menuVO) throws Exception;

	public List<?> menuList_depth(CmsMenuVO menuVO) throws Exception;
	
	public void updateMenuNav(CmsMenuVO menuVO) throws Exception ;
	
}
