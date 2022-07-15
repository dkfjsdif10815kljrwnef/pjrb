package pjrb.cms.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import egovframework.rte.fdl.excel.util.AbstractPOIExcelView;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public class pagestatExcelDownMapping  extends AbstractPOIExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, XSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = (Map<String, Object>) model.get("resultMap");
		
		List<EgovMap> resultList = (List<EgovMap>) map.get("resultList");
		
		XSSFSheet sheet = workbook.createSheet("sheet");
		XSSFCellStyle  headCs = workbook.createCellStyle();//제목 style
		XSSFCellStyle  mainCs = workbook.createCellStyle();

		headCs.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		headCs.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		headCs.setTopBorderColor(IndexedColors.BLACK.getIndex());
		headCs.setRightBorderColor(IndexedColors.BLACK.getIndex());
		headCs.setBorderBottom(CellStyle.BORDER_THIN);
		headCs.setBorderTop(CellStyle.BORDER_THIN);
		headCs.setBorderRight(CellStyle.BORDER_THIN);
		headCs.setBorderLeft(CellStyle.BORDER_THIN);

		headCs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headCs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		headCs.setAlignment(CellStyle.ALIGN_CENTER);

		mainCs.setAlignment(CellStyle.ALIGN_CENTER);
		
		mainCs.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		mainCs.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		mainCs.setTopBorderColor(IndexedColors.BLACK.getIndex());
		mainCs.setRightBorderColor(IndexedColors.BLACK.getIndex());
		mainCs.setBorderBottom(CellStyle.BORDER_THIN);
		mainCs.setBorderTop(CellStyle.BORDER_THIN);
		mainCs.setBorderRight(CellStyle.BORDER_THIN);
		mainCs.setBorderLeft(CellStyle.BORDER_THIN);
		
		//mainCs.setFillBackgroundColor((short) 10);
		//CellStyle mainCs = ExcelStyle.getMainCellStyle(workbook);//내용 style
		
		XSSFCell cell = null;
		int rowIdx  = 1;
		int cellIdx = 0;
		
		//head(항목)---------------------------------------------------------
		cell = getCell(sheet, 0, 0);
		cell.setCellStyle(headCs);
		setText(cell, "메뉴명");
		sheet.setColumnWidth(0, 15000);
		
		cell = getCell(sheet, 0, 1);
		cell.setCellStyle(headCs);
		setText(cell, "URL");	
		sheet.setColumnWidth(1, 15000);
		
		cell = getCell(sheet, 0, 2);
		cell.setCellStyle(headCs);
		setText(cell, "페이지뷰");	
		sheet.setColumnWidth(2, 5000);
		
		cell = getCell(sheet, 0, 3);
		cell.setCellStyle(headCs);
		setText(cell, "비율");	
		sheet.setColumnWidth(3, 5000);
		
		cell = getCell(sheet, 0, 4);
		cell.setCellStyle(headCs);
		setText(cell, "언어 구분");	
		sheet.setColumnWidth(4, 5000);
		
		float totCnt = 0;
		for (int i = 0; i < resultList.size(); i++) 
		{
			EgovMap temp = resultList.get(i);
			totCnt += Float.parseFloat(temp.get("viewCount").toString());
		}
		
		
		for (int i = 0; i < resultList.size(); i++) {			
			cellIdx = 0;
			EgovMap tmp = resultList.get(i);
			
			if(tmp.get("menuTitle") != null && !tmp.get("menuTitle").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("menuTitle").toString());
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			if(tmp.get("url") != null && !tmp.get("url").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("url").toString());
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			if(tmp.get("viewCount") != null && !tmp.get("viewCount").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("viewCount").toString());
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			if(tmp.get("viewCount") != null && !tmp.get("viewCount").toString().isEmpty()){
				float avg = Float.parseFloat(tmp.get("viewCount").toString());
				if(totCnt != 0){
					avg = avg / totCnt;
				}
				String avgVal = String.format("%.1f", avg*100);
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,avgVal);
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			if(tmp.get("engYn") != null && !tmp.get("engYn").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				String engYn = "국문";
				if(tmp.get("engYn").toString().equals("Y")){
					engYn = "영문";
				}
				setText(cell,engYn);
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			rowIdx++;
		}
		
		Cookie cookie =  new Cookie("fileCookie","fileCookie");
		cookie.setPath("/");
	    cookie.setSecure(true);
	    response.addCookie(cookie);
	}
}
