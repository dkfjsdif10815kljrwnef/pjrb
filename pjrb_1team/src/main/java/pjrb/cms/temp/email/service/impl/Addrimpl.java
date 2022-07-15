package pjrb.cms.temp.email.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pjrb.cms.popup.service.CmsPopupVO;
import pjrb.cms.temp.email.service.AddrDAO;
import pjrb.cms.temp.email.service.AddrService;
import pjrb.cms.temp.email.service.AddrVo;
import pjrb.cms.temp.service.TempDAO;
import pjrb.cms.temp.service.TempPopupService;


@Service(value="AddrService")
public class Addrimpl implements AddrService {

	@Resource(name="AddrDAO")
	AddrDAO dao;
	
	@Override
	public void insert(AddrVo vo) throws Exception {
		dao.insert(vo);
	}

	@Override
	public int addrListCnt(AddrVo vo) throws Exception {
		return dao.selectListCnt(vo);
	}

	@Override
	public List<?> addrList(AddrVo vo) throws Exception {
		return dao.selectList(vo);
	}

	@Override
	public List<?> addrView(AddrVo vo) throws Exception {
		return dao.selectList(vo);
	}

	@Override
	public void addrDelete(AddrVo vo) throws Exception {
		dao.delete(vo);
		
	}

}
