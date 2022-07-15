package pjrb.cms.temp.service;



import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import pjrb.cms.popup.service.CmsPopupVO;

@Repository("TempDAO")
public class TempDAO extends EgovComAbstractDAO {

	public void insert(CmsPopupVO vo) throws Exception {
		insert("TempDAO.popupInsert",vo);
	}
	
	public int selectListCnt(CmsPopupVO vo) throws Exception {
		return selectOne("TempDAO.popupListCnt",vo);
	}
	
	public List<?> selectList(CmsPopupVO vo) throws Exception {
		return selectList("TempDAO.popupList",vo);
	}
	
	public void delete(CmsPopupVO vo) throws Exception {
		delete("TempDAO.popupDelete",vo);
	}
}
