package pjrb.cms.stat.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

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

public interface CmsStatService {
	
	public List<?> constatList(CmsConstatVO constatVO) throws Exception;
	public int constatListCnt(CmsConstatVO constatVO) throws Exception;
	public void constatInsert(CmsConstatVO constatVO) throws Exception;

	public List<?> pagestatList(CmsPagestatVO pagestatVO) throws Exception;
	public int pagestatListCnt(CmsPagestatVO pagestatVO) throws Exception;
	public void pagestatInsert(CmsPagestatVO pagestatVO) throws Exception;
	
	public EgovMap selectMenuName(CmsPagestatVO pagestatVO) throws Exception;
	public EgovMap selectEngMenuName(CmsPagestatVO pagestatVO) throws Exception;
	
}
