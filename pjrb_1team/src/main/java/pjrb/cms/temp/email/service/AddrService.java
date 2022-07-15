package pjrb.cms.temp.email.service;

import java.util.List;

import pjrb.cms.popup.service.CmsPopupVO;

public interface AddrService {
	public void insert(AddrVo vo) throws Exception;
	public int addrListCnt(AddrVo vo) throws Exception;
	public List<?> addrList(AddrVo vo) throws Exception;
	public List<?> addrView(AddrVo vo) throws Exception;
	public void addrDelete(AddrVo vo) throws Exception;

}
