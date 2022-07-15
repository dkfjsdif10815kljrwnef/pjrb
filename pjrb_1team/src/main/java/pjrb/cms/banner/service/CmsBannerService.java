package pjrb.cms.banner.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

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

public interface CmsBannerService {

	public List<?> bannerList(CmsBannerVO vo) throws Exception;
		
	public int bannerListCnt(CmsBannerVO vo) throws Exception;

	public EgovMap bannerView(CmsBannerVO vo) throws Exception;

	public void bannerInsert(CmsBannerVO vo) throws Exception;
	
	public void bannerUpdate(CmsBannerVO vo) throws Exception;
	
	public void bannerDelete(CmsBannerVO vo) throws Exception;

	public void bannerStatusUpdate(CmsBannerVO vo) throws Exception;
	
}
