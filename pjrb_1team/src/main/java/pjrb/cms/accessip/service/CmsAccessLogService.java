package pjrb.cms.accessip.service;

import java.util.List;

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

public interface CmsAccessLogService {
	
	public List<?> selectAccessLogList(CmsAccessLogVO aLogVO) throws Exception;
	public int selectAccessLogListCnt(CmsAccessLogVO aLogVO) throws Exception;
	public List<?> selectAccessActLogList(CmsAccessLogVO aLogVO) throws Exception;
	public int selectAccessActLogListCnt(CmsAccessLogVO aLogVO) throws Exception;
	public void insertAccessLog(CmsAccessLogVO aLogVO) throws Exception;
	public void insertAccessActLog(String status, String id, String ip, String url, String act, String atchFileId) throws Exception;
	
}
