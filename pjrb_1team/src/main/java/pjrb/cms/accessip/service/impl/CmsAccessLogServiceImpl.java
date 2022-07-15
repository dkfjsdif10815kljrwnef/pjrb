package pjrb.cms.accessip.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import pjrb.cms.accessip.service.CmsAccessLogVO;
import pjrb.cms.accessip.service.CmsAccessLogService;

/**
 * 접근로그 관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성
 *  
 */

@Service("cmsAccessLogService")
public class CmsAccessLogServiceImpl extends EgovAbstractServiceImpl implements CmsAccessLogService{

	@Resource(name="cmsAccessLogDAO")
	private CmsAccessLogDAO cmsAccessLogDAO;

	@Override
	public List<?> selectAccessLogList(CmsAccessLogVO aLogVO) throws Exception {
		return cmsAccessLogDAO.selectAccessLogList(aLogVO);
	}

	@Override
	public int selectAccessLogListCnt(CmsAccessLogVO aLogVO) throws Exception {
		return cmsAccessLogDAO.selectAccessLogListCnt(aLogVO);
	}

	@Override
	public List<?> selectAccessActLogList(CmsAccessLogVO aLogVO) throws Exception {
		return cmsAccessLogDAO.selectAccessActLogList(aLogVO);
	}

	@Override
	public int selectAccessActLogListCnt(CmsAccessLogVO aLogVO) throws Exception {
		return cmsAccessLogDAO.selectAccessActLogListCnt(aLogVO);
	}
	
	@Override
	public void insertAccessLog(CmsAccessLogVO aLogVO) throws Exception {
		cmsAccessLogDAO.insertAccessLog(aLogVO);
	}

	@Override
	public void insertAccessActLog(String status, String id, String ip, String url, String act, String atchFileId) throws Exception {
		CmsAccessLogVO vo = new CmsAccessLogVO();
		
		vo.setStatus(status);
		vo.setUserId(id);
		vo.setAccessIp(ip);
		vo.setUrl(url);
		vo.setAct(act);
		vo.setAtchFileId(atchFileId);
		
		cmsAccessLogDAO.insertAccessActLog(vo);
	}

}
