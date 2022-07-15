package pjrb.cms.errorlog.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.user.service.PjrbDefaultVO;

/**
 * 에러로그 관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.18
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.18  권대성          최초 생성 
 *  
 */

public interface ErrorLogService {

	public List<?> selectPjrbErrorLogList(PjrbDefaultVO  defaultVO) throws Exception;
	
	public int selectPjrbErrorLogListCnt(PjrbDefaultVO  defaultVO) throws Exception;
	
	public void deletePjrbErrorLog() throws Exception;
	
	public void insertPjrbErrorLog(ErrorLogVO errorLogVO) throws Exception;
	
	public EgovMap selectPjrbErrorLogDetail(ErrorLogVO errorLogVO) throws Exception;
}
