package pjrb.cms.accessip.service;

import java.util.List;

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

public interface CmsAccessIPService {
	public List<?> selectAccessIPList() throws Exception;
	public int selectAccessIPListCnt() throws Exception;
	public void insertAccessIP(CmsAccessIPVO ipVO) throws Exception;
	public void updateAccessIP(CmsAccessIPVO ipVO) throws Exception;
	public void deleteAccessIP(CmsAccessIPVO ipVO) throws Exception;
}
