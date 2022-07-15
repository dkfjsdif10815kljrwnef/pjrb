package pjrb.cmm.web;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;

@Controller
public class PjrbFileCmmController {

	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	
	@RequestMapping("/common/ajaxFileDelete.do")
    public ModelAndView ajaxFileDelete(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String atchFileId = (String)commandMap.get("atchFileId");
		String fileSn = (String)commandMap.get("fileSn");
		
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		fileVO.setFileSn(fileSn);
		FileVO result = fileMngService.selectFileInf(fileVO);
					
		if(result != null && result.getFileStreCours() != null && !result.getFileStreCours().isEmpty() && result.getStreFileNm() != null && !result.getStreFileNm().isEmpty()){
			fileMngService.deleteFileInf(fileVO);
			File file = new File(result.getFileStreCours()+result.getStreFileNm());
			if(file.exists() && file.isFile()){
				file.delete();
			}
		}		
	
		return new ModelAndView("ajaxMainView");
    }
}
