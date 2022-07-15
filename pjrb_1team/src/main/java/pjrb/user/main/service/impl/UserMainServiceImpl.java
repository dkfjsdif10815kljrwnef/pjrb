package pjrb.user.main.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.pagemng.service.PageMngVO;
import pjrb.user.main.service.DefaultVO;
import pjrb.user.main.service.UserMainService;

@Service("userMainService")
public class UserMainServiceImpl implements UserMainService{

	@Resource(name="userMainDAO")
	private UserMainDAO userMainDAO;

	@Override
	public Map<String,Object> selectMainMenuList() throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String subMenuDepth = "";
		int i = 1;
		DefaultVO defaultVO = new DefaultVO();
		while(true){
			defaultVO.setSearchWrd(Integer.toString(i));
			List<EgovMap> map = (List<EgovMap>) userMainDAO.selectMainDepthMenuList(defaultVO);
			if(map == null || map.isEmpty()){
				break;
			}
			resultMap.put("dep_"+Integer.toString(i), map);
			if(i == 1){
				subMenuDepth += "sub_"+Integer.toString(i);
			}else{
				subMenuDepth += ",sub_"+Integer.toString(i);
			}
			i++;
		}
		resultMap.put("subMenuDepth",subMenuDepth);
		return resultMap;
	}

	@Override
	public EgovMap selectContentDetail(PageMngVO pageMngVO) throws Exception {
		// TODO Auto-generated method stub
		return userMainDAO.selectContentDetail(pageMngVO);
	}

	@Override
	public EgovMap selectMenuInfo(String oriUrl) throws Exception {
		// TODO Auto-generated method stub
		return userMainDAO.selectMenuInfo(oriUrl);
	}

	@Override
	public EgovMap selectTotalSearchCnt(DefaultVO defaultVO) throws Exception {
		// TODO Auto-generated method stub
		return userMainDAO.selectTotalSearchCnt(defaultVO);
	}

	@Override
	public List<?> selectTotalSearchMenuList(DefaultVO defaultVO) throws Exception {
		// TODO Auto-generated method stub
		return userMainDAO.selectTotalSearchMenuList(defaultVO);
	}

	@Override
	public List<?> selectTotalSearchBbsList(DefaultVO defaultVO) throws Exception {
		// TODO Auto-generated method stub
		return userMainDAO.selectTotalSearchBbsList(defaultVO);
	}

	@Override
	public List<?> selectTotalSearchContentsList(DefaultVO defaultVO) throws Exception {
		// TODO Auto-generated method stub
		return userMainDAO.selectTotalSearchContentsList(defaultVO);
	}

	@Override
	public List<?> selectSubMenuList(DefaultVO defaultVO) throws Exception {
		// TODO Auto-generated method stub
		return userMainDAO.selectSubMenuList(defaultVO);
	}

	@Override
	public List<?> selectSubMenu2Depth(DefaultVO defaultVO) throws Exception {
		// TODO Auto-generated method stub
		return userMainDAO.selectSubMenu2Depth(defaultVO);
	}

	@Override
	public int selectTotalSearchMenuListCnt(DefaultVO defaultVO) throws Exception {
		// TODO Auto-generated method stub
		return userMainDAO.selectTotalSearchMenuListCnt(defaultVO);
	}

	@Override
	public int selectTotalSearchBbsListCnt(DefaultVO defaultVO) throws Exception {
		// TODO Auto-generated method stub
		return userMainDAO.selectTotalSearchBbsListCnt(defaultVO);
	}

	@Override
	public int selectTotalSearchContentsListCnt(DefaultVO defaultVO) throws Exception {
		// TODO Auto-generated method stub
		return userMainDAO.selectTotalSearchContentsListCnt(defaultVO);
	}

	@Override
	public List<?> selectMainPopupList() throws Exception {
		// TODO Auto-generated method stub
		return userMainDAO.selectMainPopupList();
	}
	
	
}
