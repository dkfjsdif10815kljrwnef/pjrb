package pjrb.user.sns.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.user.main.service.impl.UserMainDAO;
import pjrb.user.sns.service.SnsService;
import pjrb.user.sns.service.SnsVO;

@Service("snsService")
public class SnsServiceImpl implements SnsService{
	
	@Resource(name="userMainDAO")
	private UserMainDAO userMainDAO;
	
	@Override
	public List<?> snsSelectList(SnsVO vo) throws Exception {
		return userMainDAO.selectSnsList(vo);
	}

	@Override
	public void snsInsert(SnsVO vo) throws Exception {
		userMainDAO.snsInsert(vo);
	}

	@Override
	public void snsDelete(SnsVO vo) throws Exception {
		userMainDAO.snsDelete(vo);
	}

	@Override
	public void snsTokenInsert(SnsVO vo) throws Exception {
		userMainDAO.snsTokenInsert(vo);
	}

	@Override
	public EgovMap snsTokenSelect(SnsVO vo) throws Exception {
		return userMainDAO.snsTokenSelect(vo);
	}
}
