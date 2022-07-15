package pjrb.user.sns.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;


public interface SnsService {
	public List<?> snsSelectList(SnsVO vo) throws Exception;
	
	public void snsInsert(SnsVO vo) throws Exception;
	
	public void snsDelete(SnsVO vo) throws Exception;
	
	public void snsTokenInsert(SnsVO vo) throws Exception;
	
	public EgovMap snsTokenSelect(SnsVO vo) throws Exception;
}
