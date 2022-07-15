package pjrb.cms.filemng.web;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class CmsFileMngController {

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
    
    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
    
    @RequestMapping("/cms/fileMng/fileMngList.do")
	public String fileMngList(@ModelAttribute("searchVO") FileVO fileVO , Model model) throws Exception{
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(fileVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(fileVO.getPageUnit());
		paginationInfo.setPageSize(fileVO.getPageSize());

		fileVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		fileVO.setLastIndex(paginationInfo.getLastRecordIndex());
		fileVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt =fileService.selectFileMngListCnt(fileVO);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", fileService.selectFileMngList(fileVO));
		model.addAttribute("paginationInfo", paginationInfo);
    	return "pjrb/cms/filemng/fileMngList";
    }
    
    @RequestMapping("/cms/fileMng/fileMngForm.do")
   	public String fileMngForm(@ModelAttribute("searchVO") FileVO fileVO , Model model) throws Exception{
    	model.addAttribute("resultList", fileService.selectFileInfs(fileVO));
    	model.addAttribute("result", fileService.selectFileMng(fileVO));
       	return "pjrb/cms/filemng/fileMngForm";
   }
    
    @RequestMapping("/cms/fileMng/fileMngSave.do")
   	public String fileMngSave(@ModelAttribute("searchVO") FileVO fileVO ,final MultipartHttpServletRequest multiRequest, Model model) throws Exception{
    	
    	String _atchFileId = fileVO.getAtchFileId();// 해당 업무기능에 따라서 수정되는 기능의 파일 ID를 불러온다.
    	final Map<String, MultipartFile> files = multiRequest.getFileMap();
    	if(!files.isEmpty()){
    		if("".equals(_atchFileId)){
    			List<FileVO> _result = fileUtil.parseFileInf(files, "PJRBFILEMNG_", 0, _atchFileId, "Globals.pjrbFileStorePath");
    			if(_result != null && _result.size() > 0 && _result.get(0) != null){
    				_result.get(0).setFileCn(fileVO.getFileCn());
    			}
    			_atchFileId = fileService.insertFileInfs(_result); // 기존에 첨부파일 ID가 없다.
    		}else{
    			FileVO fvo = new FileVO();
    			fvo.setAtchFileId(_atchFileId); // 최종 파일 순번을 획득하기 위하여 VO에 현재 첨부파일 ID를 세팅한다.
    			int _cnt = fileService.getMaxFileSN(fvo); // 해당 첨부파일 ID에 속하는 최종 파일 순번을 획득한다.
    			List<FileVO> _result = fileUtil.parseFileInf(files, "PJRBFILEMNG_", _cnt, _atchFileId, "Globals.pjrbFileStorePath");
    			fileService.updateFileMng(fileVO);
    			fileService.updateFileInfs(_result);
    		}
    	}
       	return "redirect:/cms/fileMng/fileMngList.do";
   }
    
    @RequestMapping("/cms/fileMng/fileMngDelete.do")
   	public String fileMngDelete(@ModelAttribute("searchVO") FileVO fileVO , Model model) throws Exception{
    	if(fileVO != null && fileVO.getAtchFileId() != null && !fileVO.getAtchFileId().isEmpty()){
    		String ids [] = fileVO.getAtchFileId().split(",");
    		for(int i=0;i<ids.length; i++){
    			if(ids[i] != null && !ids[i].isEmpty()){
    				fileVO.setAtchFileId(ids[i]);
    				
    				//파일 마스터 사용안함으로 
    				List<FileVO> fileList = fileService.selectFileInfs(fileVO);
    				fileService.deleteAllFileInf(fileVO);
    				
    				
    				//파일상세 전체 삭제
    				fileService.deleteFileInfs(fileList);
    				for(FileVO tmp : fileList){
    					if(tmp != null && tmp.getFileStreCours() != null && !tmp.getFileStreCours().isEmpty() && tmp.getStreFileNm() != null && !tmp.getStreFileNm().isEmpty()){
    						fileService.deleteFileInf(fileVO);
    						File file = new File(tmp.getFileStreCours()+tmp.getStreFileNm());
    						if(file.exists() && file.isFile()){
    							file.delete();
    						}
    					}	
    				}
    				
    			}
    		}
    	}
    	return "redirect:/cms/fileMng/fileMngList.do";
   }
    
}
