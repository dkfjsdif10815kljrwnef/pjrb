package pjrb.cms.temp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pjrb.cms.popup.service.CmsPopupVO;
import pjrb.cms.temp.service.TempDAO;
import pjrb.cms.temp.service.TempPopupService;


@Service(value="TempPopupService")
public class TempPopupimpl implements TempPopupService {

	@Resource(name="TempDAO")
	TempDAO dao;
	
	@Override
	public void insert(CmsPopupVO vo) throws Exception {
		dao.insert(vo);
	}

	@Override
	public int popupListCnt(CmsPopupVO vo) throws Exception {
		return dao.selectListCnt(vo);
	}

	@Override
	public List<?> popupList(CmsPopupVO vo) throws Exception {
		return dao.selectList(vo);
	}

	@Override
	public List<?> popupView(CmsPopupVO vo) throws Exception {
		return dao.selectList(vo);
	}

	@Override
	public void popupDelete(CmsPopupVO vo) throws Exception {
		dao.delete(vo);
		
	}

}
