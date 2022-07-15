package pjrb.cms.accessip.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import pjrb.cms.accessip.service.CmsAccessIPVO;

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

@Repository("cmsAccessIPDAO")
public class CmsAccessIPDAO extends EgovComAbstractDAO {

	public List<?> selectAccessIPList()
	{
		return selectList("cmsAccess.selectAccessIPList");
	}
	
	public int selectAccessIPListCnt()
	{
		return (int) selectOne("cmsAccess.selectAccessIPListCnt");
	}
	
	public void insertAccessIP(CmsAccessIPVO ipVO)
	{
		insert("cmsAccess.insertAccessIP",ipVO);
	}
	
	public void updateAccessIP(CmsAccessIPVO ipVO)
	{
		update("cmsAccess.updateAccessIP",ipVO);
	}	
	
	public void deleteAccessIP(CmsAccessIPVO ipVO)
	{
		delete("cmsAccess.deleteAccessIP",ipVO);
	}


}
