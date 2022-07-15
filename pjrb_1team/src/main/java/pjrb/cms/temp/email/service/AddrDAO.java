package pjrb.cms.temp.email.service;



import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("AddrDAO")
public class AddrDAO extends EgovComAbstractDAO {

	public void insert(AddrVo vo) throws Exception {
		insert("AddrDAO.addrInsert",vo);
	}
	
	public int selectListCnt(AddrVo vo) throws Exception {
		return selectOne("AddrDAO.addrListCnt",vo);
	}
	
	public List<?> selectList(AddrVo vo) throws Exception {
		return selectList("AddrDAO.addrList",vo);
	}
	
	public void delete(AddrVo vo) throws Exception {
		update("AddrDAO.addrDelete",vo);
	}
}
