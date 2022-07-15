package pjrb.cms.temp.excel.egov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TegovExcelController {
	///Egov엑셀 예제 
	@RequestMapping("/sale/listExcelCategory.do")
	public ModelAndView selectCategoryList() throws Exception {
	 
		List<Map> lists = new ArrayList<>();
	 
		Map<String, String> mapCategory = new HashMap<String, String>();
		mapCategory.put("id", "0000000001");
		mapCategory.put("name", "Sample Test");
		mapCategory.put("description", "This is initial test data.");
		mapCategory.put("useyn", "Y");
		mapCategory.put("reguser", "test");
	 
		lists.add(mapCategory);
	 
		mapCategory.put("id", "0000000002");
		mapCategory.put("name", "test Name");
		mapCategory.put("description", "test Deso1111");
		mapCategory.put("useyn", "Y");
		mapCategory.put("reguser", "test");
	 
		lists.add(mapCategory);
	 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", lists);
	 
//		return new ModelAndView("categoryExcelView", "categoryMap", map);	//.xls
		return new ModelAndView("CategoryPOIExcelView", "categoryMap", map);//.xlsx
	}
	
	@RequestMapping("/egov/excelDownload.do")
	public ModelAndView egovExcelDownload(HttpServletRequest request) throws Exception {
		Map<String, Object> excelData = new HashMap<String, Object>();
		String filename = "엑셀파일";
		/* EGOV파일명
		 * extends받은 클래스에서 model 또는 request안에 "filename"이라는 값으로 파일명을 할당한다.
		 * 기본값은 클래스명
		 * **/
//		excelData.put("filename", new String(filename.getBytes("KSC5601"),"8859_1") ); //model에 filename값을 넣으려 했으나 안됐음.. 
		request.setAttribute("filename", new String(filename.getBytes("KSC5601"),"8859_1"));
		return new ModelAndView("TegovExcelView", "excelData", "");
	}
	
}
