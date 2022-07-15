package pjrb.cms.temp.excel.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sun.star.style.HorizontalAlignment;

import oracle.net.aso.a;
@Repository("excelUtil")
public class TempExcelUtil {
	
	public HashMap<String,ArrayList> readExcel(MultipartFile excelFile) throws  IOException {
	
		Boolean error = false ;
		Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());
	
        Sheet sheet = workbook.getSheetAt(0);
        ArrayList<Boolean> errerArr = new ArrayList<>() ;
        ArrayList<String> cellHead =  new ArrayList<String>(); 
        ArrayList<HashMap<String,String>> excelList = new ArrayList<>();
        cellHead.clear();
        excelList.clear();
        
        try {
        	 /* 행 갯수만큼 반복 */
            for ( int rowNum = 0 ; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
           	 Row row = sheet.getRow(rowNum);
           	 HashMap<String, String> cellData = new HashMap<>();
           	 for ( int cellNum = 0 ; cellNum < row.getPhysicalNumberOfCells(); cellNum++) {
           		 
           		 Cell cell = row.getCell(cellNum);
           		 String value = getValue(cell);
           		 
           		 if(cell == null && value == null) continue;
           		 
           		 /* 헤더 데이터  */
           		 if( rowNum == 0 ) cellHead.add(value);
           		
           		 /* cell 데이터  */
           		 if( rowNum != 0 && cellHead != null ) {
           			 cellData.put(cellHead.get(cellNum), value );
           		 }        		 	
           	 }
           	 if( !cellData.isEmpty() ) excelList.add(cellData);
            }
		} catch (Exception e) {
			error = true ; 
	        cellHead.clear();
	        excelList.clear();
		} finally {
			 errerArr.add(error);
	         HashMap<String,ArrayList> returnData = new HashMap<>() ;
	         returnData.put("error", errerArr );
	         returnData.put("cellHead", cellHead);
	         returnData.put("excelList", excelList);
	         
	         return returnData;
		}
       
         

	}
	
	public String getValue(Cell cell) {
		 String value = "";

			if(cell == null) {
			    value = "";
			}else {
				switch(cell.getCellType()) {
					case Cell.CELL_TYPE_FORMULA :
						value = cell.getCellFormula();
						break;
					case Cell.CELL_TYPE_NUMERIC :
						value = (int)cell.getNumericCellValue() + "";	//(int)형변환 미변환시 소수점 발생가능
						break;
					case Cell.CELL_TYPE_STRING :
						value = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_BOOLEAN :
						value = cell.getBooleanCellValue() + "";
						break;
					case Cell.CELL_TYPE_ERROR :
						value = cell.getErrorCellValue() + "";
						break;
					case Cell.CELL_TYPE_BLANK :
						value = "";
						break;
					default :
						value = cell.getStringCellValue();
				}

			}
			return value;
	}
	
	private void createExcel(Workbook workbook) {

		
        // 워크시트 생성
		Sheet sheet = workbook.createSheet("데이터");
		// cell column Width 설정 ( 약 200 = 10pt )	
		sheet.setColumnWidth(0, 5000);
		
	    
		// 셀 스타일 생성
		CellStyle headCs = workbook.createCellStyle();
		CellStyle mainCs = workbook.createCellStyle();
		
		// cell 기본 스타일 설정 
		headCs = cellDefaultStyle(headCs);
		mainCs = cellDefaultStyle(mainCs);
		
		// 헤더 셀 배경색
		headCs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index); // cell 배경색 
		headCs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  // 테두리 설정  

		int rowCnt = 0;
		
		ArrayList<ArrayList> dataArr = tempData();
		for (ArrayList<Map<String, String>> arr : dataArr) {
//			int idx = 0;
			for (int i=0; i<3;i++) {
				//row
				Row row = (SXSSFRow) sheet.createRow(rowCnt); //row.setHeight(350); //row높이설정 
				
				//cell
				SXSSFCell cell = (SXSSFCell) row.createCell(0);
				
				//cell style			
				if ( i == 0 ) cell.setCellStyle(headCs);
				if ( i != 0 ) cell.setCellStyle(mainCs);
				if ( rowCnt == 4 ) row.createCell(1).setCellStyle(headCs);
				String value = "";
				switch (i) {	
					case 0 : value = arr.get(0).get("type"); break;
					case 1 : value = arr.get(0).get("contry"); break;
					case 2 : value = arr.get(0).get("value");  break;
				}
				cell.setCellValue(value);
			
//				String value = i == 0 ? arr.get(0).get("type") : arr.get(0).get("contry") ;
//			
//				
//				//data
//				for (Map<String, String> maps : arr) {
//					cell.setCellValue(maps.get("value"));
//				}			
						
				rowCnt++;
//				idx++;
			}
			rowCnt++;
		}
		
		/// 셀병합 first row , last row, first col, last col
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
		

//		//데이터를 한개씩 조회해서 한개의 행으로 만든다.
//		for (Map<String, Object> data : datas) {
//			//row 생성
//			Row row = sheet.createRow(rowNum++);
//			int cellNum = 0;
//			 
//            //map에 있는 데이터를 한개씩 조회해서 열을 생성한다.
//            for (String key : data.keySet()) {
//                //cell 생성
//                Cell cell = row.createCell(cellNum++);
//               	
//                //cell에 데이터 삽입
//                cell.setCellValue(data.get(key).toString());
//            }
//		}
		
	}
	
	public ArrayList tempData() {
		ArrayList<ArrayList> arr = new ArrayList<>();
		
			ArrayList<Map<String, String>> row1Arr = new ArrayList<>();
				HashMap area1 = new HashMap<String, String>();
				area1.put("type", "중국권역");
				area1.put("contry", "china(운남-YAAS)");
				area1.put("value", "350");
				row1Arr.add(area1);
				arr.add(row1Arr);
		
			ArrayList<Map<String, String>> row2Arr = new ArrayList<>();
				HashMap area2 = new HashMap<String, String>();
				area2.put("type", "중남미권역");
				area2.put("contry", "Chile");
				area2.put("value", "161");
				row2Arr.add(area2);
				
				HashMap area3 = new HashMap<String, String>();
				area3.put("type", "중남미권역");
				area3.put("contry", "Costa Rica");
				area3.put("value", "300");
				row2Arr.add(area3);
				arr.add(row2Arr);
				
			ArrayList<Map<String, String>> row3Arr = new ArrayList<>();
				HashMap area31 = new HashMap<String, String>();
				area31.put("type", "동남아권역");
				area31.put("contry", "Indonesia");
				area31.put("value", "301");
				row3Arr.add(area31);
				
				HashMap area32 = new HashMap<String, String>();
				area31.put("type", "동남아권역");
				area31.put("contry", "Micronesia");
				area31.put("value", "121");
				row3Arr.add(area31);
				arr.add(row3Arr);
				
			ArrayList<Map<String, String>> row4Arr = new ArrayList<>();
				HashMap areaMap = new HashMap<String, String>();
				areaMap.put("type", "인도차이나권역");
				areaMap.put("contry", "Vietnam(IEBR)");
				areaMap.put("value", "699");
				row4Arr.add(areaMap);
				
				areaMap.clear();
				areaMap.put("type", "인도차이나권역");
				areaMap.put("contry", "Cambodia(KIC)");
				areaMap.put("value", "102");
				row4Arr.add(areaMap);
				arr.add(row4Arr);
			
		return arr;
	
	}
	public CellStyle cellDefaultStyle( CellStyle cellstyle) {
		
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
		
		
		return cellstyle ;
	}
	
	///파일로 만들경우
	public void createExcelToFile(List<Map<String,Object>> datas, String filepath) throws IOException {
		Workbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet("데이터");
				
		//createExcel(sheet, datas);
		
		FileOutputStream fos = new FileOutputStream(new File(filepath));
		workbook.write(fos);
		workbook.close();
		
	}
	
    //HttpServletResponse 경우
    public void createExcelToResponse(String filename, HttpServletResponse response) throws IOException {
       
		// 워크북 생성
		Workbook workbook = new SXSSFWorkbook();
        
        createExcel(workbook);
        
        // 컨텐츠 타입과 파일명 지정
        String fileName = new String (filename.getBytes("KSC5601"),"8859_1");
        
        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", String.format("attachment;filename=%s.xlsx", fileName));
        
        workbook.write(response.getOutputStream());
        workbook.close();
    }
    
    public void createEgovExcelToResponse() {
    	
    }

	
	
	
	
	
	
	
	
	
	

}
