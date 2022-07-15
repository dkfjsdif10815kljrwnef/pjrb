package pjrb.cms.bbs.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class BbsExcelDownMapping extends AbstractPOIExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, XSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String,Object> map = (Map<String, Object>) model.get("resultMap");
		
		List<EgovMap> resultList = (List<EgovMap>) map.get("resultList");
		List<EgovMap> tableHead = (List<EgovMap>) map.get("tableHead");
		PaginationInfo paginationInfo = (PaginationInfo) map.get("paginationInfo");
		List<EgovMap> addFieldList = (List<EgovMap>) map.get("addFieldList");
		EgovMap  mResult= (EgovMap) map.get("mResult");
		
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
		setText(cell, "번호");
		sheet.setColumnWidth(0, 4000);
		
		/*cell = getCell(sheet, 0, 1);
		cell.setCellStyle(headCs);
		setText(cell, "제목");	
		sheet.setColumnWidth(1, 10000);*/
		
		int thCnt = 1;
		int colSize = 0;
		for(EgovMap tmp : tableHead ){
			
			if(tmp.get("fieldNm") != null && !tmp.get("fieldNm").toString().isEmpty() && !tmp.get("fieldNm").toString().replace(" ", "").isEmpty()){
				cell = getCell(sheet, 0, thCnt);
				cell.setCellStyle(headCs);
				setText(cell, tmp.get("fieldNm").toString());	
				if(thCnt == 1){
					colSize = 15000;
				}else{
					colSize = 10000;
				}
				sheet.setColumnWidth(thCnt++, colSize);
			}
			
		}
		cell = getCell(sheet, 0, thCnt);
		cell.setCellStyle(headCs);
		setText(cell, "조회수");
		sheet.setColumnWidth(thCnt++, 4000);
		
		cell = getCell(sheet, 0, thCnt);
		cell.setCellStyle(headCs);
		setText(cell, "작성일");
		sheet.setColumnWidth(thCnt++, 4000);
		
		
		for (int i = 0; i < resultList.size(); i++) {			
			cellIdx = 0;
			EgovMap tmp = resultList.get(i);
			
			if(mResult != null && mResult.get("noticeYn") != null && "Y".equals(mResult.get("noticeYn"))){
				if(tmp.get("ntcYn") != null &&  "1".equals(tmp.get("ntcYn"))){
					cell = getCell(sheet, rowIdx, cellIdx++);
					cell.setCellStyle(mainCs);
					setText(cell,"공지");
				}else{
					cell = getCell(sheet, rowIdx, cellIdx++);
					cell.setCellStyle(mainCs);
					setText(cell,Integer.toString(paginationInfo.getTotalRecordCount() - ((paginationInfo.getCurrentPageNo()-1) * paginationInfo.getRecordCountPerPage() + i)));
				}
			}else{
				
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,Integer.toString(paginationInfo.getTotalRecordCount() - ((paginationInfo.getCurrentPageNo()-1) * paginationInfo.getRecordCountPerPage() + i)));
			}
			
			
			/*if(tmp.get("nttSj") != null && !tmp.get("nttSj").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("nttSj").toString());
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}*/
			
			for(int j = 0; j< addFieldList.size() ; j++){
				EgovMap tmp2 = addFieldList.get(j);
				if(tmp != null && tmp.get("nttNo") != null && tmp2 != null && tmp2.get("nttNo") != null){
					String nttId_1 = tmp.get("nttNo").toString();
					String nttId_2 = tmp2.get("nttNo").toString();
					if(nttId_1.equals(nttId_2)){
						/*if(tmp2 != null && tmp2.get("addFieldCn") != null && tmp2.get("addFieldCn").toString() != null){
							String [] addFieldValue = tmp2.get("addFieldCn").toString().split("\\|\\|");
							for(int k = 0 ; k<addFieldValue.length; k++ ){
								if(addFieldValue[k] != null && !addFieldValue[k].isEmpty()){
									cell = getCell(sheet, rowIdx, cellIdx++);
									cell.setCellStyle(mainCs);
									setText(cell,addFieldValue[k]);
								}else{
									cell = getCell(sheet, rowIdx, cellIdx++);
									cell.setCellStyle(mainCs);
									setText(cell,"");
								}
							}
							
						}*/
						if(tmp2.get("fieldStatusSeq") != null && !tmp2.get("fieldStatusSeq").toString().isEmpty()){
							if(!"0".equals(tmp2.get("fieldStatusSeq").toString())){
								if(tmp2.get("addFieldCn") != null && !tmp2.get("addFieldCn").toString().isEmpty()){
									String statusDt[] = tmp2.get("addFieldCn").toString().split("~");
									SimpleDateFormat sDt = new SimpleDateFormat("yyyy-MM-dd");
									Date dt = new Date();
									String txt = "";
									if(statusDt.length > 1){
										if((sDt.parse(sDt.format(dt)).compareTo(sDt.parse(statusDt[0])) > 0 || sDt.parse(sDt.format(dt)).compareTo(sDt.parse(statusDt[0])) == 0) && (sDt.parse(sDt.format(dt)).compareTo(sDt.parse(statusDt[1])) < 0 || sDt.parse(sDt.format(dt)).compareTo(sDt.parse(statusDt[1])) == 0)){
											txt = "진행중";
										}else if(sDt.parse(statusDt[1]).compareTo(sDt.parse(sDt.format(dt))) < 0){
											txt = "종료";
										}else{
											txt = "예정";
										}
										cell = getCell(sheet, rowIdx, cellIdx++);
										cell.setCellStyle(mainCs);
										setText(cell,txt);
									}else{
										if(sDt.parse(sDt.format(dt)).compareTo(sDt.parse(statusDt[0])) < 0 || sDt.parse(sDt.format(dt)).compareTo(sDt.parse(statusDt[0])) == 0){
											txt = "진행중";
										}else{
											txt = "종료";
										}
										cell = getCell(sheet, rowIdx, cellIdx++);
										cell.setCellStyle(mainCs);
										setText(cell,txt);
									}
									
								}
								
							}else{
								if(tmp2.get("addFieldCn") != null && !tmp2.get("addFieldCn").toString().isEmpty() && "Y".equals(tmp2.get("addFieldCn").toString())){
									cell = getCell(sheet, rowIdx, cellIdx++);
									cell.setCellStyle(mainCs);
									setText(cell,"O");
								}else{
									cell = getCell(sheet, rowIdx, cellIdx++);
									cell.setCellStyle(mainCs);
									setText(cell,"X");
								}
							}
						}else{
							if(tmp2.get("addFieldCn") != null && !tmp2.get("addFieldCn").toString().isEmpty()){
								cell = getCell(sheet, rowIdx, cellIdx++);
								cell.setCellStyle(mainCs);
								setText(cell,tmp2.get("addFieldCn").toString());
							}else{
								cell = getCell(sheet, rowIdx, cellIdx++);
								cell.setCellStyle(mainCs);
								setText(cell,"");
							}
						}
						
					}
				}
				
				
			}
			
			if(tmp.get("rdcnt") != null && !tmp.get("rdcnt").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("rdcnt").toString());
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
			if(tmp.get("regDate") != null && !tmp.get("regDate").toString().isEmpty()){
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,tmp.get("regDate").toString().substring(0, 10));
			}else{
				cell = getCell(sheet, rowIdx, cellIdx++);
				cell.setCellStyle(mainCs);
				setText(cell,"");
			}
			
		
			rowIdx++;
		}
		
	}

}
