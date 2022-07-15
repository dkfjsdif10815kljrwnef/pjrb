package pjrb.cms.temp.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import pjrb.cms.popup.service.CmsPopupVO;
import pjrb.cms.temp.service.TempPopupService;

@Controller
public class TempFileUploadUtil {
	
	@Resource(name = "ajaxMainView")
	private MappingJackson2JsonView ajaxMainView;
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	
    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    @Resource(name = "TempPopupService")
    private TempPopupService popupService;
	
	@RequestMapping("/temp/fileupload.do")
	public ModelAndView insert(@ModelAttribute("searchVO") CmsPopupVO vo, final MultipartHttpServletRequest multiRequest, HttpServletRequest request, ModelMap model) throws Exception {
		
		List<FileVO> result = null;
		String atchFileId = "" ;
		
		///파라미터 이름을 key, 파라미터에 해당하는 파일정보를 값으로 가지는 Map을 구함
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			/// 파일 객체, 구분 값, 파일 순번, 파일 ID, 저장 경로
			result = fileUtil.parseFileInf(files, "POPUP_", 0, "", "POPUP");
			/// 파일이 생성되고 나면 첨부파일 ID를 리턴
			atchFileId = fileMngService.insertFileInfs(result);
		}
		vo.setAtchFileId(atchFileId);
		
		popupService.insert(vo);
		
		return new ModelAndView(ajaxMainView);
		
	}

}

