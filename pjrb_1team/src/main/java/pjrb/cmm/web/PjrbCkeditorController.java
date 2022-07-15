package pjrb.cmm.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovProperties;
import pjrb.cmm.service.EgovFileUploadUtil;
import pjrb.cmm.service.EgovFormBasedFileVo;


/**
  * @FileName : FileUpload.java
  * @Project : gbict
  * @Date : 2016. 6. 29. 
  * @작성자 : 전한석
  * @변경이력 :
  * @프로그램 설명 : ckeditor 의 이미지 업로드를 하기 위한 기능구현
  * 		   
  */
@SuppressWarnings("serial")
@Controller
public class PjrbCkeditorController implements Serializable {


	/**
	  * @Method Name : ckeditorImageUpload
	  * @작성자 : 권대성
	  * @작성일 : 2018. 10. 18.
	  * @변경이력 : 
	  * @Method 설명 :ckeditor 의 이미지 업로드를 하기 위한 기능구현
	  * @param request
	  * @param response
	  * @param upload
	  * @throws Exception
	  */
	@RequestMapping(value = "/ckeditor/ckeditorImageUpload.do", method = RequestMethod.POST)
	public void ckeditorImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {

		OutputStream out = null;
		PrintWriter printWriter = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String url = null;

		try {
			//프로퍼티의 이미지 경로 지정
			final String uploadDir = EgovProperties.getProperty("Globals.fileStorePath");
			//CommonsMultipartResolver 에서 자동으로 잡아줌
			final long maxFileSize = 1024 * 1024 * 600;
			
			final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			//InputStream fis = null;

			//String sResult = "";

			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
			MultipartFile file;

			while (itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();

				file = entry.getValue();
				if (!"".equals(file.getOriginalFilename())) {
					// 업로드 파일에 대한 확장자를 체크
					String originFileNm = file.getOriginalFilename();
					originFileNm = originFileNm.toLowerCase();
					if (!originFileNm.endsWith(".gif") && 
						!originFileNm.endsWith(".png") && 
						!originFileNm.endsWith(".jpg") && 
						!originFileNm.endsWith(".jpeg") &&
						!originFileNm.endsWith(".pdf")
					) {
						String callback2 = request.getParameter("CKEditorFuncNum");
						callback2 = callback2.replaceAll("<", "&lt;");
						callback2 = callback2.replaceAll(">", "&gt;");
						printWriter = response.getWriter();
						printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(" + callback2 + ",'','허용되지 않은 파일 유형입니다.'" + ")</script>");
						printWriter.flush();
						
						return;
					}
				}
			}
			
			//전자정부 프레임워크에서 제공되는 메서드에서 maxFileSize 파라메터만 받고, 사용되지는 않음
			List<EgovFormBasedFileVo> list = EgovFileUploadUtil.uploadFiles(request, uploadDir, maxFileSize);

			if (list.size() > 0) {
				EgovFormBasedFileVo vo = list.get(0); // 첫번째 이미지

				url = request.getContextPath() + "/utl/web/imageSrc.do?" + "path=" + vo.getServerSubPath() + "&physical=" + vo.getPhysicalName() + "&contentType=" + vo.getContentType();

			}

			//저장된 이미지는 'CKEditorFuncNum'의 이름으로 콜백된다
			String callback = request.getParameter("CKEditorFuncNum");

			printWriter = response.getWriter();
			String fileUrl = url; // url경로

			callback = callback.replaceAll("<", "&lt;");
			callback = callback.replaceAll(">", "&gt;");
			
			fileUrl = fileUrl.replaceAll("<", "&lt;");
			fileUrl = fileUrl.replaceAll(">", "&gt;");
			
			printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + fileUrl + "','이미지를 업로드 하였습니다.'" + ")</script>");
			printWriter.flush();

		} catch (IOException e) {
			//e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}

		return;
	}
	
	@ResponseBody
    @RequestMapping(value = "/ckeditor/ckeditor5ImageUpload.do", method = {RequestMethod.POST, RequestMethod.GET})
  	public String ckeditor5ImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {

  		String url = null;
  		//저장된 이미지는 'CKEditorFuncNum'의 이름으로 콜백된다
  		try {
  			//프로퍼티의 이미지 경로 지정
  			final String uploadDir = EgovProperties.getProperty("Globals.ckeditorFileStorePath");
  			//CommonsMultipartResolver 에서 자동으로 잡아줌
  			final long maxFileSize = 1024 * 1024 * 600;
  			//전자정부 프레임워크에서 제공되는 메서드에서 maxFileSize 파라메터만 받고, 사용되지는 않음
  			List<EgovFormBasedFileVo> list = EgovFileUploadUtil.uploadFiles(request, uploadDir, maxFileSize);
  			
  			if(list == null){
  				return "{ \"uploaded\" : false, \"error\": { \"message\": \"업로드 중 에러가 발생했습니다. 다시 시도해 주세요.\" } }";
  			}else{
  				if (list.size() > 0) {
  					EgovFormBasedFileVo vo = list.get(0); // 첫번째 이미지

  					url = request.getContextPath() + "/utl/web/imageSrcCkediror.do?" + "path=" + vo.getServerSubPath() + "&physical=" + vo.getPhysicalName() + "&contentType=" + vo.getContentType();

  				}

  				

  				return "{ \"uploaded\" : true, \"url\" : \"" + url + "\" }";
  				
  			}
  			

  		} catch (IOException e) {
  			e.printStackTrace();
  		} finally {
  			
  		}
  		return "{ \"uploaded\" : false, \"error\": { \"message\": \"업로드 중 에러가 발생했습니다. 다시 시도해 주세요.\" } }";
  	}
}
