package pjrb.cms.errorlog.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
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

@Repository("errorLogDAO")
public class ErrorLogDAO extends EgovComAbstractDAO{

	public List<?> selectPjrbErrorLogList(PjrbDefaultVO defaultVO) throws Exception{
		return selectList("errorLogDAO.selectPjrbErrorLogList",defaultVO);
	}
	
	public int selectPjrbErrorLogListCnt(PjrbDefaultVO defaultVO) throws Exception{
		return (int)selectOne("errorLogDAO.selectPjrbErrorLogListCnt",defaultVO);
	}

	public void deletePjrbErrorLog() throws Exception{
		delete("errorLogDAO.deletePjrbErrorLog");
	}
	
	public void insertPjrbErrorLog(ErrorLogVO errorLogVO) throws Exception{
		insert("errorLogDAO.insertPjrbErrorLog",errorLogVO);
	}

	public EgovMap selectPjrbErrorLogDetail(ErrorLogVO errorLogVO) throws Exception{
		return (EgovMap)selectOne("errorLogDAO.selectPjrbErrorLogDetail",errorLogVO);
	}
	
}
