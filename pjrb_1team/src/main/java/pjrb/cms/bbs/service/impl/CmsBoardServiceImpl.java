package pjrb.cms.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.cms.bbs.service.CmsBbsCommentVO;
import pjrb.cms.bbs.service.CmsBbsMstVO;
import pjrb.cms.bbs.service.CmsBbsVO;
import pjrb.cms.bbs.service.CmsBoardService;

@Service("cmsBoardService")
public class CmsBoardServiceImpl implements CmsBoardService{

	@Resource(name = "NttNoGnrService")
    private EgovIdGnrService nttNogenService;
	
	@Resource(name = "BbsMstrIdGnrService")
    private EgovIdGnrService bbsMstrIdGnrService;
	
	@Resource(name="cmsBoardDAO")
	private CmsBoardDAO cmsBoardDAO;
	
	@Override
	public List<?> mngList(CmsBbsMstVO vo) throws Exception{
		return cmsBoardDAO.mngList(vo);
	}

	@Override
	public int mngListCnt(CmsBbsMstVO vo) throws Exception{
		return cmsBoardDAO.mngListCnt(vo);
	}

	@Override
	public EgovMap mngView(CmsBbsMstVO vo) throws Exception{
		return cmsBoardDAO.mngView(vo);
	}

	@Override
	public void mngDelete(CmsBbsMstVO vo) throws Exception{
		cmsBoardDAO.mngDelete(vo);
	}

	@Override
	public List<?> bbsList(CmsBbsVO CmsBbsVO) throws Exception {
		return cmsBoardDAO.bbsList(CmsBbsVO);
	}

	@Override
	public int bbsListCnt(CmsBbsVO CmsBbsVO) throws Exception {
		return cmsBoardDAO.bbsListCnt(CmsBbsVO);
	}

	@Override
	public void bbsInsert(CmsBbsVO CmsBbsVO) throws Exception {
		CmsBbsVO.setNttNo(Integer.toString(nttNogenService.getNextIntegerId()));//2011.09.22
		insertBbsAddField(CmsBbsVO);
		cmsBoardDAO.bbsInsert(CmsBbsVO);
	}

	@Override
	public void bbsUpdate(CmsBbsVO CmsBbsVO) throws Exception {
		cmsBoardDAO.bbsUpdate(CmsBbsVO);
		insertBbsAddField(CmsBbsVO);
	}

	@Override
	public void bbsDelete(CmsBbsVO CmsBbsVO) throws Exception {
		cmsBoardDAO.bbsDelete(CmsBbsVO);
	}

	@Override
	public EgovMap bbsView(CmsBbsVO CmsBbsVO) throws Exception {
		cmsBoardDAO.updateBbsRdcnt(CmsBbsVO);
		return cmsBoardDAO.bbsView(CmsBbsVO);
	}

	@Override
	public void bbsUseAtUpdate(CmsBbsVO CmsBbsVO) throws Exception {
		cmsBoardDAO.bbsUseAtUpdate(CmsBbsVO);
	}

	@Override
	public void insertBbsAddFieldConf(CmsBbsMstVO CmsBbsMstVO) throws Exception {
		// TODO Auto-generated method stub
		if(CmsBbsMstVO != null && CmsBbsMstVO.getCmsBbsMstVOList() != null && !CmsBbsMstVO.getCmsBbsMstVOList().isEmpty()){
			cmsBoardDAO.deleteBbsAddFieldConf(CmsBbsMstVO);
			for(int i=0;i<CmsBbsMstVO.getCmsBbsMstVOList().size() ; i++){
				
				CmsBbsMstVO tmp = CmsBbsMstVO.getCmsBbsMstVOList().get(i);
				if(!"Y".equals(tmp.getFieldDtYn())){
					tmp.setFieldDtYn("N");
				}
				if(!"Y".equals(tmp.getUseYn())){
					tmp.setUseYn("N");
				}
				if(!"Y".equals(tmp.getFieldTermYn())){
					tmp.setFieldTermYn("N");
				}
				if(!"Y".equals(tmp.getFieldStatusYn())){
					tmp.setFieldStatusYn("N");
				}
				if(!"Y".equals(tmp.getFieldSelectSearchYn())){
					tmp.setFieldSelectSearchYn("N");
				}
				tmp.setBbsId(CmsBbsMstVO.getBbsId());
				cmsBoardDAO.insertBbsAddFieldConf(tmp);
			}
		}
		
	}

	@Override
	public void deleteBbsAddFieldConf(CmsBbsMstVO CmsBbsMstVO) throws Exception {
		// TODO Auto-generated method stub
		cmsBoardDAO.deleteBbsAddFieldConf(CmsBbsMstVO);
	}

	@Override
	public List<?> selectBbsAddFieldConfList(CmsBbsMstVO vo) throws Exception {
		// TODO Auto-generated method stub
		return cmsBoardDAO.selectBbsAddFieldConfList(vo);
	}

	@Override
	public void insertBbsAddField(CmsBbsVO CmsBbsVO) throws Exception {
		// TODO Auto-generated method stub
		if(CmsBbsVO != null && CmsBbsVO.getCmsBbsVOList()!= null && !CmsBbsVO.getCmsBbsVOList().isEmpty()){
			cmsBoardDAO.deleteBbsAddField(CmsBbsVO);
			for(int i=0;i<CmsBbsVO.getCmsBbsVOList().size() ; i++){
				
				CmsBbsVO tmp = CmsBbsVO.getCmsBbsVOList().get(i);
				if(tmp != null && tmp.getSeq() != null && !tmp.getSeq().isEmpty()){
					tmp.setBbsId(CmsBbsVO.getBbsId());
					tmp.setNttNo(CmsBbsVO.getNttNo());
					cmsBoardDAO.insertBbsAddField(tmp);
				}
				
			}
		}		
	}

	@Override
	public void deleteBbsAddField(CmsBbsVO CmsBbsVO) throws Exception {
		// TODO Auto-generated method stub
		cmsBoardDAO.deleteBbsAddField(CmsBbsVO);
	}

	@Override
	public void insertBbsTableHead(CmsBbsMstVO CmsBbsMstVO) throws Exception {
		// TODO Auto-generated method stub
		if(CmsBbsMstVO != null && CmsBbsMstVO.getCmsBbsMstVOList2() != null && !CmsBbsMstVO.getCmsBbsMstVOList2().isEmpty()){
			cmsBoardDAO.deleteBbsTableHead(CmsBbsMstVO);
			for(int i=0;i<CmsBbsMstVO.getCmsBbsMstVOList2().size() ; i++){
				
				CmsBbsMstVO tmp = CmsBbsMstVO.getCmsBbsMstVOList2().get(i);
				tmp.setBbsId(CmsBbsMstVO.getBbsId());
				tmp.setSort(Integer.toString(i));
				cmsBoardDAO.insertBbsTableHead(tmp);
			}
		}
	}

	@Override
	public void deleteBbsTableHead(CmsBbsMstVO CmsBbsMstVO) throws Exception {
		// TODO Auto-generated method stub
		cmsBoardDAO.deleteBbsTableHead(CmsBbsMstVO);
	}

	@Override
	public List<?> selectBbsTableHeadList(CmsBbsMstVO vo) throws Exception {
		// TODO Auto-generated method stub
		return cmsBoardDAO.selectBbsTableHeadList(vo);
	}

	@Override
	public List<?> selectBbsAddFieldList(CmsBbsVO CmsBbsVO) throws Exception {
		// TODO Auto-generated method stub
		return cmsBoardDAO.selectBbsAddFieldList(CmsBbsVO);
	}

	@Override
	public List<?> selectBbsAddFieldList_forList(CmsBbsVO CmsBbsVO) throws Exception {
		// TODO Auto-generated method stub
		return cmsBoardDAO.selectBbsAddFieldList_forList(CmsBbsVO);
	}

	@Override
	public void insertBbsMaster(CmsBbsMstVO cmsBbsMstVO) throws Exception {
		// TODO Auto-generated method stub
		String bbsId = bbsMstrIdGnrService.getNextStringId();
		cmsBbsMstVO.setBbsId(bbsId);
		cmsBoardDAO.insertBbsMaster(cmsBbsMstVO);
		
		if(cmsBbsMstVO.getBbsTp().equals("1")||cmsBbsMstVO.getBbsTp().equals("4")){
			insertBbsAddFieldConf(cmsBbsMstVO);
			insertBbsTableHead(cmsBbsMstVO);	
		}
		
		
	}

	@Override
	public void updateBbsMaster(CmsBbsMstVO cmsBbsMstVO) throws Exception {
		// TODO Auto-generated method stub
		cmsBoardDAO.updateBbsMaster(cmsBbsMstVO);
		insertBbsAddFieldConf(cmsBbsMstVO);
		insertBbsTableHead(cmsBbsMstVO);
	}

	@Override
	public List<?> selectBbsSelectBoxSearch(CmsBbsVO cmsBbsVO) throws Exception {
		// TODO Auto-generated method stub
		return cmsBoardDAO.selectBbsSelectBoxSearch(cmsBbsVO);
	}

	@Override
	public List<?> bbsContentsList(CmsBbsVO cmsBbsVO) throws Exception {
		return cmsBoardDAO.bbsContentsList(cmsBbsVO);
	}

	@Override
	public int bbsContentsListCnt(CmsBbsVO vo) throws Exception {
		return cmsBoardDAO.bbsContentsListCnt(vo);
	}

	@Override
	public void bbsContentsInsert(CmsBbsVO vo) throws Exception {
		cmsBoardDAO.bbsContentsInsert(vo);
	}

	@Override
	public void bbsContentsUpdate(CmsBbsVO vo) throws Exception {
		cmsBoardDAO.bbsContentsUpdate(vo);
	}

	@Override
	public void bbsContentsDelete(CmsBbsVO vo) throws Exception {
		cmsBoardDAO.bbsContentsDelete(vo);
	}

	@Override
	public EgovMap bbsContentsView(CmsBbsVO vo) throws Exception {
		cmsBoardDAO.updateBbsContentsRdcnt(vo);
		return cmsBoardDAO.bbsContentsView(vo);
	}

	@Override
	public List<?> bbsCommentList(CmsBbsCommentVO vo) throws Exception {
		return cmsBoardDAO.bbsCommentList(vo);
	}

	@Override
	public int bbsCommentListCnt(CmsBbsCommentVO vo) throws Exception {
		return cmsBoardDAO.bbsCommentListCnt(vo);
	}

	@Override
	public void bbsCommentInsert(CmsBbsCommentVO vo) throws Exception {
		cmsBoardDAO.bbsCommentInsert(vo);
	}

	@Override
	public void bbsCommentUpdate(CmsBbsCommentVO vo) throws Exception {
		cmsBoardDAO.bbsCommentUpdate(vo);
	}

	@Override
	public void bbsCommentDelete(CmsBbsCommentVO vo) throws Exception {
		cmsBoardDAO.bbsCommentDelete(vo);
	}
	
}
