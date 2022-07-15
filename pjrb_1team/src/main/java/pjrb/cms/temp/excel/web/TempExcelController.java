package pjrb.cms.temp.excel.web;



import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import egovframework.com.cmm.service.impl.CmmUseDAO;
import pjrb.cms.banner.service.CmsBannerVO;

import static org.hamcrest.CoreMatchers.endsWith;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class TempExcelController {
	
    @Resource(name = "excelUtil")
	TempExcelUtil excelUtil;
	
	@RequestMapping("/temp/excel/list.do")
	public String tempExcelList() {
		return "/pjrb/temp/excel/excelList";
	}
	
	@RequestMapping("/temp/excel/Uploadlist.do")
	public String tempExcelUploadList(@ModelAttribute("searchVO") CmsBannerVO vo, final MultipartHttpServletRequest multiRequest, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws InvalidFormatException, IOException {
		
		
		MultipartFile excelFile =  multiRequest.getFile("excelFile") ;
		
		boolean error = false ;
		String errorMsg = "엑셀 파일만 업로드 가능합니다.";
		
		if (  excelFile.getOriginalFilename().endsWith(".xls") || excelFile.getOriginalFilename().endsWith(".xlsx") ) {
			HashMap<String,ArrayList> excelMap = excelUtil.readExcel(excelFile);						
			model.addAttribute("cellHead",excelMap.get("cellHead") );
			model.addAttribute("excelList",excelMap.get("excelList") );
			
			error = (Boolean)excelMap.get("error").get(0);
			if( error ) errorMsg = "양식에 맞지않은 엑셀 파일입니다.";
		} else {
			error = true ;
		}
		
		if(error) {
			/// response의 Encoding을 먼저 한다. (PrintWriter를 후에 사용) 
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>alert('"+new String (errorMsg.getBytes("UTF-8"),"UTF-8")+"');</script>");
			out.flush();
		}
		
		
		return "/pjrb/temp/excel/excelList" ;
	}
	
	
	@RequestMapping("/temp/excel/Download.do")
	public void tempExcelDownload( HttpServletResponse response) throws InvalidFormatException, IOException {
				
//	        String filepath = "D:/data.xlsx";
//	        excelUtil.createExcelToFile(datas, filepath);
		 String fileName = String.format("%s-%s", "한글", LocalDate.now().toString());
		 
	     excelUtil.createExcelToResponse(fileName , response );
		
	}
	

}
