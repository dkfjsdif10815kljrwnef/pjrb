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

public class bannerExcelDownMapping  extends AbstractPOIExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, XSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = (Map<String, Object>) model.get("resultMap");
		
		List<EgovMap> resultList = (List<EgovMap>) map.get("resultList");
		String excelType = (String) map.get("excelType");
		
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
		if(excelType.equals("1")){
			cell = getCell(sheet, 0, 0);
			cell.setCellStyle(headCs);
			setText(cell, "기관명");
			sheet.setColumnWidth(0, 5000);
			
			cell = getCell(sheet, 0, 1);
			cell.setCellStyle(headCs);
			setText(cell, "설명");	
			sheet.setColumnWidth(1, 10000);
			
			cell = getCell(sheet, 0, 2);
			cell.setCellStyle(headCs);
			setText(cell, "주소");	
			sheet.setColumnWidth(2, 10000);
			
			cell = getCell(sheet, 0, 3);
			cell.setCellStyle(headCs);
			setText(cell, "사용유무");	
			sheet.setColumnWidth(3, 5000);
			
			cell = getCell(sheet, 0, 4);
			cell.setCellStyle(headCs);
			setText(cell, "등록일");	
			sheet.setColumnWidth(4, 5000);
			
		}else if(excelType.equals("2")){
			cell = getCell(sheet, 0, 0);
			cell.setCellStyle(headCs);
			setText(cell, "기관명");
			sheet.setColumnWidth(0, 5000);
			
			cell = getCell(sheet, 0, 1);
			cell.setCellStyle(headCs);
			setText(cell, "주소");	
			sheet.setColumnWidth(1, 10000);
			
			cell = getCell(sheet, 0, 2);
			cell.setCellStyle(headCs);
			setText(cell, "언어 구분");	
			sheet.setColumnWidth(2, 5000);
			
			cell = getCell(sheet, 0, 3);
			cell.setCellStyle(headCs);
			setText(cell, "사용유무");	
			sheet.setColumnWidth(3, 5000);
			
			cell = getCell(sheet, 0, 4);
			cell.setCellStyle(headCs);
			setText(cell, "등록일");	
			sheet.setColumnWidth(4, 5000);
		}
		
		for (int i = 0; i < resultList.size(); i++) {			
			cellIdx = 0;
			EgovMap tmp = resultList.get(i);
			
			if(excelType.equals("1")){
				if(tmp.get("title") != null && !tmp.get("title").toString().isEmpty()){
					cell = getCell(sheet, rowIdx, cellIdx++);
					cell.setCellStyle(mainCs);
					setText(cell,tmp.get("title").toString());
				}else{
					cell = getCell(sheet, rowIdx, cellIdx++);
					cell.setCellStyle(mainCs);
					setText(cell,"");
				}
				
				if(tmp.get("bannerDetail") != null && !tmp.get("bannerDetail").toString().isEmpty()){
					cell = getCell(sheet, rowIdx, cellIdx++);
					cell.setCellStyle(mainCs);
					setText(cell,tmp.get("bannerDetail").toString());
				}else{
					cell = getCell(sheet, rowIdx, cellIdx++);
					cell.setCellStyle(mainCs);
					setText(cell,"");
				}
				
				if(tmp.get("detail") != null && !tmp.get("detail").toString().isEmpty()){
					cell = getCell(sheet, rowIdx, cellIdx++);
					cell.setCellStyle(mainCs);
					setText(cell,tmp.get("detail").toString());
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
				
				if(tmp.get("regDate") != null && !tmp.get("regDate").toString().isEmpty()){
					cell = getCell(sheet, rowIdx, cellIdx++);
					cell.setCellStyle(mainCs);
					setText(cell,tmp.get("regDate").toString());
				}else{
					cell = getCell(sheet, rowIdx, cellIdx++);
					cell.setCellStyle(mainCs);
					setText(cell,"");
				}
			}else if(excelType.equals("2")){
				if(tmp.get("title") != null && !tmp.get("title").toString().isEmpty()){
					cell = getCell(sheet, rowIdx, cellIdx++);
					cell.setCellStyle(mainCs);
					setText(cell,tmp.get("title").toString());
				}else{
					cell = getCell(sheet, rowIdx, cellIdx++);
					cell.setCellStyle(mainCs);
					setText(cell,"");
				}
				
				if(tmp.get("detail") != null && !tmp.get("detail").toString().isEmpty()){
					cell = getCell(sheet, rowIdx, cellIdx++);
					cell.setCellStyle(mainCs);
					setText(cell,tmp.get("detail").toString());
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
				
				if(tmp.get("status") != null && !tmp.get("status").toString().isEmpty()){
					cell = getCell(sheet, rowIdx, cellIdx++);
					cell.setCellStyle(mainCs);
					setText(cell,tmp.get("status").toString());
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
			}
			rowIdx++;
		}
		
		Cookie cookie =  new Cookie("fileCookie","fileCookie");
	    cookie.setPath("/");
	    cookie.setSecure(true);
	    response.addCookie(cookie);
	}
}
