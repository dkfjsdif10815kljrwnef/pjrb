package pjrb.cms.accessip.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import pjrb.cms.accessip.service.CmsAccessLogVO;

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

@Repository("cmsAccessLogDAO")
public class CmsAccessLogDAO extends EgovComAbstractDAO {

	public List<?> selectAccessLogList(CmsAccessLogVO aLogVO) throws Exception {
		return selectList("cmsAccess.selectAccessLogList",aLogVO);
	}
	
	public int selectAccessLogListCnt(CmsAccessLogVO aLogVO) throws Exception {
		return (int) selectOne("cmsAccess.selectAccessLogListCnt",aLogVO);
	}
	
	public List<?> selectAccessActLogList(CmsAccessLogVO aLogVO) throws Exception {
		return selectList("cmsAccess.selectAccessActLogList",aLogVO);
	}
	
	public int selectAccessActLogListCnt(CmsAccessLogVO aLogVO) throws Exception {
		return (int) selectOne("cmsAccess.selectAccessActLogListCnt",aLogVO);
	}
	
	public void insertAccessLog(CmsAccessLogVO aLogVO) throws Exception {
		insert("cmsAccess.insertAccessLog",aLogVO);
	}

	public void insertAccessActLog(CmsAccessLogVO vo) throws Exception {
		insert("cmsAccess.insertAccessActLog",vo);
	}

}
