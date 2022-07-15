package pjrb.cms.accessip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import pjrb.cms.accessip.service.CmsAccessIPVO;
import pjrb.cms.accessip.service.CmsAccessIPService;

/**
 * 접근IP 관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성 
 *  
 */

@Service("cmsAccessIPService")
public class CmsAccessIPServiceImpl extends EgovAbstractServiceImpl implements CmsAccessIPService{

	@Resource(name="cmsAccessIPDAO")
	private CmsAccessIPDAO cmsAccessIPDAO;

	@Override
	public List<?> selectAccessIPList() throws Exception {
		return cmsAccessIPDAO.selectAccessIPList();
	}

	@Override
	public int selectAccessIPListCnt() throws Exception {
		return cmsAccessIPDAO.selectAccessIPListCnt();
	}

	@Override
	public void insertAccessIP(CmsAccessIPVO ipVO) throws Exception {
		cmsAccessIPDAO.insertAccessIP(ipVO);
	}

	@Override
	public void updateAccessIP(CmsAccessIPVO ipVO) throws Exception {
		cmsAccessIPDAO.updateAccessIP(ipVO);
	}

	@Override
	public void deleteAccessIP(CmsAccessIPVO ipVO) throws Exception {
		cmsAccessIPDAO.deleteAccessIP(ipVO);
	}

	
	

}
