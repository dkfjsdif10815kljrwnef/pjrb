package pjrb.user.main.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.bbs.service.CmsBbsVO;
import pjrb.cms.pagemng.service.PageMngVO;
import pjrb.user.main.service.DefaultVO;
import pjrb.user.service.PjrbDefaultVO;
import pjrb.user.sns.service.SnsVO;

@Repository("userMainDAO")
public class UserMainDAO extends EgovComAbstractDAO{

	public List<?> selectMain_1depthMenuList() throws Exception{
		return  selectList("userMainDAO.selectMain_1depthMenuList");
	}
	
	public List<?> selectMain_2depthMenuList() throws Exception{
		return  selectList("userMainDAO.selectMain_2depthMenuList");
	}	

	public List<?> selectMain_3depthMenuList() throws Exception{
		return  selectList("userMainDAO.selectMain_3depthMenuList");
	}
	
	public EgovMap selectContentDetail(PageMngVO pageMngVO) throws Exception{
		return  (EgovMap)selectOne("userMainDAO.selectContentDetail",pageMngVO);
	}
	
	public EgovMap selectMenuInfo(String oriUrl) throws Exception{
		return  (EgovMap)selectOne("userMainDAO.selectMenuInfo",oriUrl);
	}
	
	public EgovMap selectTotalSearchCnt(DefaultVO defaultVO) throws Exception{
		return  (EgovMap)selectOne("userMainDAO.selectTotalSearchCnt",defaultVO);
	}
	
	public List<?> selectTotalSearchMenuList(DefaultVO defaultVO) throws Exception{
		return  selectList("userMainDAO.selectTotalSearchMenuList",defaultVO);
	}
	
	public List<?> selectTotalSearchBbsList(DefaultVO defaultVO) throws Exception{
		return  selectList("userMainDAO.selectTotalSearchBbsList",defaultVO);
	}
	
	public List<?> selectTotalSearchContentsList(DefaultVO defaultVO) throws Exception{
		return  selectList("userMainDAO.selectTotalSearchContentsList",defaultVO);
	}
	
	public int selectTotalSearchMenuListCnt(DefaultVO defaultVO) throws Exception{
		return  (int)selectOne("userMainDAO.selectTotalSearchMenuListCnt",defaultVO);
	}
	
	public int selectTotalSearchBbsListCnt(DefaultVO defaultVO) throws Exception{
		return  (int)selectOne("userMainDAO.selectTotalSearchBbsListCnt",defaultVO);
	}
	
	public int selectTotalSearchContentsListCnt(DefaultVO defaultVO) throws Exception{
		return  (int)selectOne("userMainDAO.selectTotalSearchContentsListCnt",defaultVO);
	}
	
	public List<?> selectMainDepthMenuList(DefaultVO defaultVO) throws Exception{
		return  selectList("userMainDAO.selectMainDepthMenuList",defaultVO);
	}
	
	public List<?> selectSubMenuList(DefaultVO defaultVO) throws Exception{
		return  selectList("userMainDAO.selectSubMenuList",defaultVO);
	}
	
	public List<?> selectSubMenu2Depth(DefaultVO defaultVO) throws Exception{
		return  selectList("userMainDAO.selectSubMenu2Depth",defaultVO);
	}

	public List<?> selectMainPopupList() throws Exception{
		// TODO Auto-generated method stub
		return selectList("userMainDAO.selectMainPopupList");
	}
	
	public List<?> selectSnsList(SnsVO vo) throws Exception{
		return  selectList("userMainDAO.selectSnsList", vo);
	}
	
	public void snsInsert(SnsVO vo) throws Exception{
		insert("userMainDAO.snsInsert", vo);
	}
	
	public void snsDelete(SnsVO vo) throws Exception{
		delete("userMainDAO.snsDelete", vo);
	}
	
	public void snsTokenInsert(SnsVO vo) throws Exception{
		insert("userMainDAO.snsTokenInsert", vo);
	}
	
	public EgovMap snsTokenSelect(SnsVO vo) throws Exception{
		return selectOne("userMainDAO.snsTokenSelect",vo);
	}
}	
