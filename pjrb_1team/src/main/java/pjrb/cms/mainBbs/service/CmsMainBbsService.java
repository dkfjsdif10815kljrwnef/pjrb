package pjrb.cms.mainBbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.bbs.service.CmsBbsVO;

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

public interface CmsMainBbsService {

	public List<?> mainBbsList(CmsMainBbsVO vo) throws Exception;
		
	public int mainBbsListCnt(CmsMainBbsVO vo) throws Exception;

	public void mainBbsInsert(CmsMainBbsVO vo) throws Exception;
	
	public void mainBbsUpdate(CmsMainBbsVO vo) throws Exception;
	
	public void mainBbsDelete(CmsMainBbsVO vo) throws Exception;

	public List<?> mainBbsModalList(CmsBbsVO vo) throws Exception;
	
	public int mainBbsModalListCnt(CmsBbsVO vo) throws Exception;
	
	public List<?> mainBbsContentsModalList(CmsBbsVO vo) throws Exception;
	
	public int mainBbsContentsModalListCnt(CmsBbsVO vo) throws Exception;
	
}
