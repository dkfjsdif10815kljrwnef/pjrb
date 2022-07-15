package pjrb.cms.banner.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.banner.service.CmsBannerService;
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

@Service("cmsBannerService")
public class CmsBannerServiceImpl implements CmsBannerService{

	@Resource(name="cmsBannerDAO")
	private CmsBannerDAO cmsBannerDAO;
	
	@Override
	public List<?> bannerList(CmsBannerVO vo) throws Exception{
		return cmsBannerDAO.bannerList(vo);
	}

	@Override
	public int bannerListCnt(CmsBannerVO vo) throws Exception{
		return cmsBannerDAO.bannerListCnt(vo);
	}

	@Override
	public EgovMap bannerView(CmsBannerVO vo) throws Exception{
		return cmsBannerDAO.bannerView(vo);
	}

	@Override
	public void bannerInsert(CmsBannerVO vo) throws Exception{
		cmsBannerDAO.bannerInsert(vo);
	}

	@Override
	public void bannerUpdate(CmsBannerVO vo) throws Exception{
		cmsBannerDAO.bannerUpdate(vo);
	}
	
	@Override
	public void bannerDelete(CmsBannerVO vo) throws Exception{
		cmsBannerDAO.bannerDelete(vo);
	}
	
	@Override
	public void bannerStatusUpdate(CmsBannerVO vo) throws Exception{
		cmsBannerDAO.bannerStatusUpdate(vo);
	}
	
}
