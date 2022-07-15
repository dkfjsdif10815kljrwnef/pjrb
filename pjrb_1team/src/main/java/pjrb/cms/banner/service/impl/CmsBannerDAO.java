package pjrb.cms.banner.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.banner.service.CmsBannerVO;

/**
 * 배너 및 관련사이트 관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성
 *  
 */

@Repository("cmsBannerDAO")
public class CmsBannerDAO extends EgovComAbstractDAO {
	
	public List<?> bannerList(CmsBannerVO vo) throws Exception{
		return selectList("cmsBannerDAO.bannerList",vo);
	}
	
	public int bannerListCnt(CmsBannerVO vo) throws Exception{
		return (int) selectOne("cmsBannerDAO.bannerListCnt",vo);
	}

	public EgovMap bannerView(CmsBannerVO vo) throws Exception{
		return (EgovMap) selectOne("cmsBannerDAO.bannerView",vo);
	}

	public void bannerInsert(CmsBannerVO vo) throws Exception{
		insert("cmsBannerDAO.bannerInsert",vo);
	}

	public void bannerUpdate(CmsBannerVO vo) throws Exception{
		update("cmsBannerDAO.bannerUpdate",vo);
	}
	
	public void bannerDelete(CmsBannerVO vo) throws Exception{
		delete("cmsBannerDAO.bannerDelete",vo);
	}
	
	public void bannerStatusUpdate(CmsBannerVO vo) throws Exception{
		update("cmsBannerDAO.bannerStatusUpdate",vo);
	}
	
}
