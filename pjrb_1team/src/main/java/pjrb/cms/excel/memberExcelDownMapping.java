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

public class memberExcelDownMapping  extends AbstractPOIExcelView {

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
		setText(cell, "아이디");
		sheet.setColumnWidth(0, 5000);
		
		cell = getCell(sheet, 0, 1);
		cell.setCellStyle(headCs);
		setText(cell, "이름");	
		sheet.setColumnWidth(1, 5000);
		
		cell = getCell(sheet, 0, 2);
		cell.setCellStyle(headCs);
		setText(cell, "사용유무");	
		sheet.setColumnWidth(2, 5000);
		
		cell = getCell(sheet, 0, 3);
		cell.setCellStyle(headCs);
		setText(cell, "부서");	
		sheet.setColumnWidth(3, 5000);
		
		cell = getCell(sheet, 0, 4);
		cell.setCellStyle(headCs);
		setText(cell, "직급");	
		sheet.setColumnWidth(4, 5000);
		
		cell = getCell(sheet, 0, 5);
		cell.setCellStyle(headCs);
		setText(cell, "이메일");
		sheet.setColumnWidth(5, 5000);
		
		cell = getCell(sheet, 0, 6);
		cell.setCellStyle(headCs);
		setText(cell, "연락처");
		sheet.setColumnWidth(6, 5000);
		
		cell = getCell(sheet, 0, 7);
		cell.setCellStyle(headCs);
		setText(cell, "권한명");
		sheet.setColumnWidth(7, 5000);
		
		cell = getCell(sheet, 0, 8);
		cell.setCellStyle(headCs);
		setText(cell, "등록일");
		sheet.setColumnWidth(8, 5000);
		
		for (int i = 0; i < resultList.size(); i++) {			
			cellIdx = 0;
			EgovMap tmp = resultList.get(i);
			
			if(tmp.get("emplyrId") != null && !tmp.get("emplyrId").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("emplyrId").toString());
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			if(tmp.get("userNm") != null && !tmp.get("userNm").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("userNm").toString());
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			if(tmp.get("status") != null && !tmp.get("status").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("status").toString());
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			if(tmp.get("department") != null && !tmp.get("department").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("department").toString());
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			if(tmp.get("position") != null && !tmp.get("position").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("position").toString());
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			if(tmp.get("emailAdres") != null && !tmp.get("emailAdres").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("emailAdres").toString());
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			if(tmp.get("mbtlnum") != null && !tmp.get("mbtlnum").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("mbtlnum").toString());
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			if(tmp.get("gradeNm") != null && !tmp.get("gradeNm").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("gradeNm").toString());
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			if(tmp.get("regDate") != null && !tmp.get("regDate").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("regDate").toString().substring(0,10));
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
