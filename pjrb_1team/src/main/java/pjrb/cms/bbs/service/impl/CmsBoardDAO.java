package pjrb.cms.bbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.bbs.service.CmsBbsCommentVO;
import pjrb.cms.bbs.service.CmsBbsMstVO;
import pjrb.cms.bbs.service.CmsBbsVO;

@Repository("cmsBoardDAO")
public class CmsBoardDAO extends EgovComAbstractDAO {

	public List<?> mngList(CmsBbsMstVO vo) throws Exception{
		return selectList("cmsBoardDAO.mngList",vo);
	}
	
	public int mngListCnt(CmsBbsMstVO vo) throws Exception{
		return (int) selectOne("cmsBoardDAO.mngListCnt",vo);
	}

	public EgovMap mngView(CmsBbsMstVO vo) throws Exception{
		return (EgovMap) selectOne("cmsBoardDAO.mngView",vo);
	}

	public void mngDelete(CmsBbsMstVO vo) throws Exception{
		delete("cmsBoardDAO.mngDelete",vo);
	}

	public List<?> bbsList(CmsBbsVO CmsBbsVO) throws Exception{
		return selectList("cmsBoardDAO.bbsList",CmsBbsVO);
	}

	public int bbsListCnt(CmsBbsVO CmsBbsVO) throws Exception{
		return (int) selectOne("cmsBoardDAO.bbsListCnt",CmsBbsVO);
	}

	public void bbsInsert(CmsBbsVO CmsBbsVO) throws Exception{
		insert("cmsBoardDAO.bbsInsert",CmsBbsVO);
	}
	
	public void bbsUpdate(CmsBbsVO CmsBbsVO) throws Exception{
		update("cmsBoardDAO.bbsUpdate",CmsBbsVO);
	}
	
	public void bbsDelete(CmsBbsVO CmsBbsVO) throws Exception{
		delete("cmsBoardDAO.bbsDelete",CmsBbsVO);
	}
	
	public EgovMap bbsView(CmsBbsVO CmsBbsVO) throws Exception{
		return (EgovMap) selectOne("cmsBoardDAO.bbsView",CmsBbsVO);
	}

	public void bbsUseAtUpdate(CmsBbsVO CmsBbsVO) throws Exception{
		update("cmsBoardDAO.bbsUseAtUpdate",CmsBbsVO);
	}
	
	public void insertBbsAddFieldConf(CmsBbsMstVO CmsBbsMstVO) throws Exception{
		insert("cmsBoardDAO.insertBbsAddFieldConf",CmsBbsMstVO);
	}
	
	public void deleteBbsAddFieldConf(CmsBbsMstVO CmsBbsMstVO) throws Exception{
		delete("cmsBoardDAO.deleteBbsAddFieldConf",CmsBbsMstVO);
	}

	public List<?> selectBbsAddFieldConfList(CmsBbsMstVO vo) throws Exception{
		return selectList("cmsBoardDAO.selectBbsAddFieldConfList",vo);
	}

	public List<?> selectBbsAddFieldList(CmsBbsVO CmsBbsVO) throws Exception{
		return selectList("cmsBoardDAO.selectBbsAddFieldList",CmsBbsVO);
	}
	
	public List<?> selectBbsAddFieldList_forList(CmsBbsVO CmsBbsVO) throws Exception{
		return selectList("cmsBoardDAO.selectBbsAddFieldList_forList",CmsBbsVO);
	}
	
	public void insertBbsAddField(CmsBbsVO CmsBbsVO) throws Exception{
		insert("cmsBoardDAO.insertBbsAddField",CmsBbsVO);
	}

	public void deleteBbsAddField(CmsBbsVO CmsBbsVO) throws Exception{
		delete("cmsBoardDAO.deleteBbsAddField",CmsBbsVO);
	}
	
	public void insertBbsTableHead(CmsBbsMstVO CmsBbsMstVO) throws Exception{
		insert("cmsBoardDAO.insertBbsTableHead",CmsBbsMstVO);
	}

	public void deleteBbsTableHead(CmsBbsMstVO CmsBbsMstVO) throws Exception{
		delete("cmsBoardDAO.deleteBbsTableHead",CmsBbsMstVO);
	}

	public List<?> selectBbsTableHeadList(CmsBbsMstVO vo) throws Exception{
		return selectList("cmsBoardDAO.selectBbsTableHeadList",vo);
	}

	public void updateBbsRdcnt(CmsBbsVO CmsBbsVO) throws Exception{
		update("cmsBoardDAO.updateBbsRdcnt",CmsBbsVO);
	}
	
	public void insertBbsMaster(CmsBbsMstVO cmsBbsMstVO) throws Exception{
		insert("cmsBoardDAO.insertBbsMaster",cmsBbsMstVO);
	}
	
	public void updateBbsMaster(CmsBbsMstVO cmsBbsMstVO) throws Exception{
		update("cmsBoardDAO.updateBbsMaster",cmsBbsMstVO);
	}
	
	public List<?> selectBbsSelectBoxSearch(CmsBbsVO cmsBbsVO) {
		// TODO Auto-generated method stub
		return selectList("cmsBoardDAO.selectBbsSelectBoxSearch",cmsBbsVO);
	}
	
	public List<?> bbsContentsList(CmsBbsVO CmsBbsVO) throws Exception{
		return selectList("cmsBoardDAO.bbsContentsList",CmsBbsVO);
	}

	public int bbsContentsListCnt(CmsBbsVO CmsBbsVO) throws Exception{
		return (int) selectOne("cmsBoardDAO.bbsContentsListCnt",CmsBbsVO);
	}

	public void bbsContentsInsert(CmsBbsVO CmsBbsVO) throws Exception{
		insert("cmsBoardDAO.bbsContentsInsert",CmsBbsVO);
	}
	
	public void bbsContentsUpdate(CmsBbsVO CmsBbsVO) throws Exception{
		update("cmsBoardDAO.bbsContentsUpdate",CmsBbsVO);
	}
	
	public void bbsContentsDelete(CmsBbsVO CmsBbsVO) throws Exception{
		delete("cmsBoardDAO.bbsContentsDelete",CmsBbsVO);
	}
	
	public EgovMap bbsContentsView(CmsBbsVO CmsBbsVO) throws Exception{
		return (EgovMap) selectOne("cmsBoardDAO.bbsContentsView",CmsBbsVO);
	}
	
	public void updateBbsContentsRdcnt(CmsBbsVO CmsBbsVO) throws Exception{
		update("cmsBoardDAO.updateBbsContentsRdcnt",CmsBbsVO);
	}
	
	public List<?> bbsCommentList(CmsBbsCommentVO vo) throws Exception{
		return selectList("cmsBoardDAO.bbsCommentList",vo);
	}
	
	public int bbsCommentListCnt(CmsBbsCommentVO vo) throws Exception{
		return (int) selectOne("cmsBoardDAO.bbsCommentListCnt",vo);
	}
	
	public void bbsCommentInsert(CmsBbsCommentVO vo) throws Exception{
		insert("cmsBoardDAO.bbsCommentInsert",vo);
	}
	
	public void bbsCommentUpdate(CmsBbsCommentVO vo) throws Exception{
		update("cmsBoardDAO.bbsCommentUpdate",vo);
	}
	
	public void bbsCommentDelete(CmsBbsCommentVO vo) throws Exception{
		delete("cmsBoardDAO.bbsCommentDelete",vo);
	}
}
