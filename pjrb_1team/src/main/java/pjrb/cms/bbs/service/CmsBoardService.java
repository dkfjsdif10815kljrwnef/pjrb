package pjrb.cms.bbs.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface CmsBoardService {

	public List<?> mngList(CmsBbsMstVO vo) throws Exception;
		
	public int mngListCnt(CmsBbsMstVO vo) throws Exception;

	public EgovMap mngView(CmsBbsMstVO vo) throws Exception;

	public void mngDelete(CmsBbsMstVO vo) throws Exception;

	public List<?> bbsList(CmsBbsVO vo) throws Exception;

	public int bbsListCnt(CmsBbsVO vo) throws Exception;

	public void bbsInsert(CmsBbsVO vo) throws Exception;

	public void bbsUpdate(CmsBbsVO vo) throws Exception;
	
	public void bbsDelete(CmsBbsVO vo) throws Exception;
	
	public EgovMap bbsView(CmsBbsVO vo) throws Exception;

	public void bbsUseAtUpdate(CmsBbsVO vo) throws Exception;
	
	public void insertBbsAddFieldConf(CmsBbsMstVO vo) throws Exception;
	
	public void deleteBbsAddFieldConf(CmsBbsMstVO vo) throws Exception;

	public List<?> selectBbsAddFieldConfList(CmsBbsMstVO vo) throws Exception;
	
	public void insertBbsAddField(CmsBbsVO vo) throws Exception;

	public void deleteBbsAddField(CmsBbsVO vo) throws Exception;
	
	public List<?> selectBbsAddFieldList(CmsBbsVO vo) throws Exception;
	
	public void insertBbsTableHead(CmsBbsMstVO vo) throws Exception;

	public void deleteBbsTableHead(CmsBbsMstVO vo) throws Exception;

	public List<?> selectBbsTableHeadList(CmsBbsMstVO vo) throws Exception;
	
	public List<?> selectBbsAddFieldList_forList(CmsBbsVO vo) throws Exception;
	
	public void insertBbsMaster(CmsBbsMstVO cmsBbsMstVO) throws Exception;
	
	public void updateBbsMaster(CmsBbsMstVO cmsBbsMstVO) throws Exception;
	
	public List<?> selectBbsSelectBoxSearch(CmsBbsVO cmsBbsVO) throws Exception;
	
	public List<?> bbsContentsList(CmsBbsVO cmsBbsVO) throws Exception;
	
	public int bbsContentsListCnt(CmsBbsVO vo) throws Exception;
	
	public void bbsContentsInsert(CmsBbsVO vo) throws Exception;

	public void bbsContentsUpdate(CmsBbsVO vo) throws Exception;
	
	public void bbsContentsDelete(CmsBbsVO vo) throws Exception;
	
	public EgovMap bbsContentsView(CmsBbsVO vo) throws Exception;
	
	public List<?> bbsCommentList(CmsBbsCommentVO vo) throws Exception;
	
	public int bbsCommentListCnt(CmsBbsCommentVO vo) throws Exception;
	
	public void bbsCommentInsert(CmsBbsCommentVO vo) throws Exception;

	public void bbsCommentUpdate(CmsBbsCommentVO vo) throws Exception;
	
	public void bbsCommentDelete(CmsBbsCommentVO vo) throws Exception;
}
