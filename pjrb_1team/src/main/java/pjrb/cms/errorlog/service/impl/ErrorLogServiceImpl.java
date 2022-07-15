package pjrb.cms.errorlog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.errorlog.service.ErrorLogService;
import pjrb.cms.errorlog.service.ErrorLogVO;
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

@Service("errorLogService")
public class ErrorLogServiceImpl implements ErrorLogService{

	@Resource(name="errorLogDAO")
	private ErrorLogDAO errorLogDAO;

	@Override
	public List<?> selectPjrbErrorLogList(PjrbDefaultVO defaultVO) throws Exception {
		return errorLogDAO.selectPjrbErrorLogList(defaultVO);
	}

	@Override
	public void deletePjrbErrorLog() throws Exception {
		errorLogDAO.deletePjrbErrorLog();
	}

	@Override
	public int selectPjrbErrorLogListCnt(PjrbDefaultVO  defaultVO) throws Exception {
		return errorLogDAO.selectPjrbErrorLogListCnt(defaultVO);
	}

	@Override
	public void insertPjrbErrorLog(ErrorLogVO errorLogVO) throws Exception {
		errorLogDAO.insertPjrbErrorLog(errorLogVO);
	}

	@Override
	public EgovMap selectPjrbErrorLogDetail(ErrorLogVO errorLogVO) throws Exception {
		return errorLogDAO.selectPjrbErrorLogDetail(errorLogVO);
	}
}
