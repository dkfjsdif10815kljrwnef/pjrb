package pjrb.cms.temp.excel.egov;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;

import egovframework.rte.fdl.excel.util.AbstractPOIExcelView;

public class TegovExcelView extends AbstractPOIExcelView  {

	private static final Logger LOGGER  = LoggerFactory.getLogger(TegovExcelView.class);
	 
	@Override
	protected void buildExcelDocument(Map model, XSSFWorkbook wb, HttpServletRequest req, HttpServletResponse response) throws Exception {
        XSSFCell cell = null;
 
        XSSFSheet sheet = wb.createSheet("SheetName시트명");
        ///Sheet Column Width
        sheet.setDefaultColumnWidth(30);
        
        CellStyle dc = wb.createCellStyle();
        CellStyle hc = wb.createCellStyle();
        setDefaultCellstyle(dc);
        setHeaderCellstyle(hc);
        
        
        // excelData
        // getCell(sheet, row, col)
        setText (getCell(sheet, 0, 0), "중국권역");
        setText (getCell(sheet, 1, 0), "China(운남-YAAS)");
        setText (getCell(sheet, 2, 0), "350");
        setText (getCell(sheet, 4, 0), "중남미권역");
        setText (getCell(sheet, 5, 0), "Chile");
        setText (getCell(sheet, 6, 0), "161");
        setText (getCell(sheet, 5, 1), "Costa Rica");
        setText (getCell(sheet, 6, 1), "300");
        
        for(int i = 0 ; i<7;i++) {
        	if (i==3) continue;
        	
        	getCell(sheet, i, 0).setCellStyle(dc);
        	
        	if (i>=5) getCell(sheet, i, 1).setCellStyle(dc);
        }
        getCell(sheet, 0, 0).setCellStyle(hc);
        getCell(sheet, 4, 0).setCellStyle(hc);
        getCell(sheet, 4, 1).setCellStyle(hc);
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 1)); /// 셀병합 first row , last row, first col, last col
    }
	
	public CellStyle setDefaultCellstyle(CellStyle cellstyle) {
		
		/// 글자 정렬
		cellstyle.setAlignment(CellStyle.ALIGN_CENTER); // 가로 기준 가운데정렬
		cellstyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 세로 기준 가운데 정렬
		
		///테두리 경계선
		cellstyle.setBorderBottom(CellStyle.BORDER_THIN);		
		cellstyle.setBorderTop(CellStyle.BORDER_THIN);		
		cellstyle.setBorderRight(CellStyle.BORDER_THIN);		
		cellstyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		
		return cellstyle;
	}
	
public CellStyle setHeaderCellstyle(CellStyle cellstyle) {
		cellstyle = setDefaultCellstyle(cellstyle);
		// 헤더 셀 배경색
		cellstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index); // cell 배경색 
		cellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  // 테두리 설정  
		
		return cellstyle;
	}
}
