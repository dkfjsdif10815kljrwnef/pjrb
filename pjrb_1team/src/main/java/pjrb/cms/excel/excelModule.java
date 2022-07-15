package pjrb.cms.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class excelModule {
	
	public static void excel(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String excelFlag = (String) map.get("excelFlag");
		String excelType = (String) map.get("excelType");
		List<EgovMap> excelList = (List<EgovMap>) map.get("excelList");
		String[] excelHead = (String[]) map.get("excelHead");
		String[] excelRow = (String[]) map.get("excelRow");
		String[] excelWidth = (String[]) map.get("excelWidth");
		String filename = (String) map.get("filename");
		
		EgovMap  mResult= (EgovMap) map.get("mResult");
		List<EgovMap> addFieldList = (List<EgovMap>) map.get("addFieldList");
		
		FileOutputStream fos = null;

		// 워크북
		SXSSFWorkbook workbook = null;
		// 행
		SXSSFRow row = null;
		// 셀
		SXSSFCell cell = null;
		// 샐 스타일
		CellStyle headCs = null;
		CellStyle mainCs = null;
		// 셀 헤더 카운트
		int index = 0;
		// 행 카운트
		int rowIndex = 1;

		// 엑셀 헤더 정보 구성
		String[] cellHeader = excelHead;

		try {

		    // 워크북 생성
		    workbook = new SXSSFWorkbook();
		    workbook.setCompressTempFiles(true);

			// 워크시트 생성
		    SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet(filename);
		    sheet.setRandomAccessWindowSize(100); // 메모리 행 100개로 제한, 초과 시 Disk로 flush
		    
			//셀 칼럼 크기 설정
		    for (int i = 0; i < excelWidth.length; i++) {
		    	sheet.setColumnWidth(i, Integer.parseInt(excelWidth[i]));
			}
		    
			// 행 생성
			row = (SXSSFRow) sheet.createRow(0);
			// 셀 스타일 생성
			headCs = workbook.createCellStyle();
			mainCs = workbook.createCellStyle();
			CreationHelper ch = workbook.getCreationHelper();
			
			headCs.setDataFormat(ch.createDataFormat().getFormat("#,##0"));
			headCs.setAlignment(CellStyle.ALIGN_CENTER);
			headCs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			headCs.setFillPattern(CellStyle.SOLID_FOREGROUND);
			headCs.setBorderBottom(CellStyle.BORDER_THIN);
			headCs.setBorderTop(CellStyle.BORDER_THIN);
			headCs.setBorderRight(CellStyle.BORDER_THIN);
			headCs.setBorderLeft(CellStyle.BORDER_THIN);

			mainCs.setDataFormat(ch.createDataFormat().getFormat("#,##0"));
			mainCs.setAlignment(CellStyle.ALIGN_CENTER);
			mainCs.setBorderBottom(CellStyle.BORDER_THIN);
			mainCs.setBorderTop(CellStyle.BORDER_THIN);
			mainCs.setBorderRight(CellStyle.BORDER_THIN);
			mainCs.setBorderLeft(CellStyle.BORDER_THIN);
			
			// 헤더 적용
			for(String head : cellHeader ) {
				cell = (SXSSFCell) row.createCell(index++);
				cell.setCellValue(head);
				cell.setCellStyle(headCs);
			}
			
			// 페이지 통계
			float totCnt = 0;
			if(excelFlag.equals("stats")) {
				if(excelType.equals("2")) {
					for (int i = 0; i < excelList.size(); i++) {
						EgovMap temp = excelList.get(i);
						totCnt += Float.parseFloat(temp.get("viewCount").toString());
					}
				}
			}
			
			int num = excelList.size(); // 번호 셀
			for(int i=0; i<excelList.size(); i++) {
				EgovMap tmp = excelList.get(i);
				
				row = (SXSSFRow) sheet.createRow(rowIndex);
				rowIndex++;
				int cnt = 0; // bbs용 카운트
				for (int j = 0; j < excelRow.length; j++) {
					
					if(!excelFlag.equals("bbs")) { // bbs 아닐때
						cell = (SXSSFCell) row.createCell(j);
						cell.setCellStyle(mainCs);
					}
						
					if(excelFlag.equals("popup")) { // 팝업 관리
						if(excelRow[j].equals("startDate")) {
							String startDate = excelRow[j];
							if(tmp.get(startDate) != null && !tmp.get(startDate).toString().isEmpty()){
								String popupDate = tmp.get("startDate").toString();
								if(tmp.get("startTime") != null && !tmp.get("startTime").toString().isEmpty()) {
									popupDate+=" "+tmp.get("startTime").toString();
								}
								if(tmp.get("endDate") != null && !tmp.get("endDate").toString().isEmpty()) {
									popupDate+=" ~ "+tmp.get("endDate").toString();
								}
								if(tmp.get("endTime") != null && !tmp.get("endTime").toString().isEmpty()) {
									popupDate+=" "+tmp.get("endTime").toString();
								}
								
				            	cell.setCellValue(popupDate);
							}else{
								cell.setCellValue("");
							}
						}else {
							if(tmp.get(excelRow[j]) != null && !tmp.get(excelRow[j]).toString().isEmpty()){
				            	cell.setCellValue(tmp.get(excelRow[j]).toString());
							}else{
								cell.setCellValue("");
							}
						}
					}else if(excelFlag.equals("stats")) {
						if(excelType.equals("1")) {// 접속 통계
							if(tmp.get(excelRow[j]) != null && !tmp.get(excelRow[j]).toString().isEmpty()){
				            	cell.setCellValue(tmp.get(excelRow[j]).toString());
							}else{
								cell.setCellValue("");
							}
						}else if(excelType.equals("2")) { // 페이지 통계
							if(excelRow[j].equals("viewCount")) {
								String viewCount = excelRow[j];
								if(tmp.get(viewCount) != null && !tmp.get(viewCount).toString().isEmpty()){
									float avg = Float.parseFloat(tmp.get(viewCount).toString());
									if(totCnt != 0){
										avg = avg / totCnt;
									}
									String avgVal = String.format("%.1f", avg*100);
					            	cell.setCellValue(avgVal);
								}else{
									cell.setCellValue("");
								}
							}else if(excelRow[j].equals("engYn")) {
								String engYn = excelRow[j];
								if(tmp.get(engYn) != null && !tmp.get(engYn).toString().isEmpty()){
									String koEng = "국문";
									if(tmp.get(engYn).toString().equals("Y")){
										koEng = "영문";
									}
					            	cell.setCellValue(koEng);
								}else{
									cell.setCellValue("");
								}
							}else {
								if(tmp.get(excelRow[j]) != null && !tmp.get(excelRow[j]).toString().isEmpty()){
					            	cell.setCellValue(tmp.get(excelRow[j]).toString());
								}else{
									cell.setCellValue("");
								}
							}
						}
					}else if(excelFlag.equals("banner")) {
						if(excelType.equals("1")) { // 배너 관리
							if(tmp.get(excelRow[j]) != null && !tmp.get(excelRow[j]).toString().isEmpty()){
				            	cell.setCellValue(tmp.get(excelRow[j]).toString());
							}else{
								cell.setCellValue("");
							}
						}else if(excelType.equals("2")) { // 관련사이트 관리
							if(excelRow[j].equals("engYn")) {
								String engYn = excelRow[j];
								if(tmp.get(engYn) != null && !tmp.get(engYn).toString().isEmpty()){
									String koEng = "국문";
									if(tmp.get(engYn).toString().equals("Y")){
										koEng = "영문";
									}
					            	cell.setCellValue(koEng);
								}else{
									cell.setCellValue("");
								}
							}else {
								if(tmp.get(excelRow[j]) != null && !tmp.get(excelRow[j]).toString().isEmpty()){
					            	cell.setCellValue(tmp.get(excelRow[j]).toString());
								}else{
									cell.setCellValue("");
								}
							}
						}
					}else if(excelFlag.equals("pageMng")) { // 페이지 관리
						if(excelRow[j].equals("@")) { // 번호
							cell.setCellValue(num);
							num--;
						}else {
							if(tmp.get(excelRow[j]) != null && !tmp.get(excelRow[j]).toString().isEmpty()){
				            	cell.setCellValue(tmp.get(excelRow[j]).toString());
							}else{
								cell.setCellValue("");
							}
						}
					}else if(excelFlag.equals("bbsmng")) { // 게시판 관리
						if(excelRow[j].equals("@")) { // 번호
							cell.setCellValue(num);
							num--;
						}else if(excelRow[j].equals("bbsTp")) { // 게시판 유형
							String bbsTp = excelRow[j];
							if(tmp.get(bbsTp) != null && !tmp.get(bbsTp).toString().isEmpty()){
								String bbsF = "";
								if("1".equals(tmp.get(bbsTp).toString())){
									bbsF = "리스트";
								}else if("2".equals(tmp.get(bbsTp).toString())){
									bbsF = "이미지";
								}else if("3".equals(tmp.get(bbsTp).toString())){
									bbsF = "동영상";
								}else if("4".equals(tmp.get(bbsTp).toString())){
									bbsF = "묻고답하기";
								}else{
									bbsF = "";
								}
				            	cell.setCellValue(bbsF);
							}else{
								cell.setCellValue("");
							}
						}else {
							if(tmp.get(excelRow[j]) != null && !tmp.get(excelRow[j]).toString().isEmpty()){
				            	cell.setCellValue(tmp.get(excelRow[j]).toString());
							}else{
								cell.setCellValue("");
							}
						}
					}else if(excelFlag.equals("bbsCon")) { // 게시판 관리
						if(excelRow[j].equals("ntcYn")) { // 번호 & 공지
							if(mResult != null && mResult.get("noticeYn") != null && "Y".equals(mResult.get("noticeYn"))) {
								if(tmp.get("ntcYn") != null &&  "1".equals(tmp.get("ntcYn"))){
									cell.setCellValue("공지");
									num--;
								}else{
									cell.setCellValue(num);
									num--;
								}
							}else {
								cell.setCellValue(num);
								num--;
							}
						}else {
							if(tmp.get(excelRow[j]) != null && !tmp.get(excelRow[j]).toString().isEmpty()){
								cell.setCellValue(tmp.get(excelRow[j]).toString());
							}else{
								cell.setCellValue("");
							}
						}
					}else if(excelFlag.equals("bbs")) { // 게시판 관리
						if(cnt==excelRow.length) {
							break;
						}
						if(excelRow[cnt].equals("ntcYn")) { // 번호 & 공지
							if(mResult != null && mResult.get("noticeYn") != null && "Y".equals(mResult.get("noticeYn"))) {
								if(tmp.get("ntcYn") != null &&  "1".equals(tmp.get("ntcYn"))){
									cell = (SXSSFCell) row.createCell(cnt);
									cell.setCellValue("공지");
									num--;
									cnt++;
								}else{
									cell = (SXSSFCell) row.createCell(cnt);
									cell.setCellValue(num);
									num--;
									cnt++;
								}
							}else {
								cell = (SXSSFCell) row.createCell(cnt);
								cell.setCellValue(num);
								num--;
								cnt++;
							}
						}else if(excelRow[cnt].equals("field")){
							for(int k = 0; k< addFieldList.size() ; k++){
								EgovMap tmp2 = addFieldList.get(k);
								if(tmp != null && tmp.get("nttNo") != null && tmp2 != null && tmp2.get("nttNo") != null){
									String nttId_1 = tmp.get("nttNo").toString();
									String nttId_2 = tmp2.get("nttNo").toString();
									if(nttId_1.equals(nttId_2)){
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
														cell = (SXSSFCell) row.createCell(cnt);
														cell.setCellValue(txt);
														cnt++;
													}else{
														if(sDt.parse(sDt.format(dt)).compareTo(sDt.parse(statusDt[0])) < 0 || sDt.parse(sDt.format(dt)).compareTo(sDt.parse(statusDt[0])) == 0){
															txt = "진행중";
														}else{
															txt = "종료";
														}
														cell = (SXSSFCell) row.createCell(cnt);
														cell.setCellValue(txt);
														cnt++;
													}
												}
											}else{
												if(tmp2.get("addFieldCn") != null && !tmp2.get("addFieldCn").toString().isEmpty() && "Y".equals(tmp2.get("addFieldCn").toString())){
													cell = (SXSSFCell) row.createCell(cnt);
													cell.setCellValue("O");
													cnt++;
												}else{
													cell = (SXSSFCell) row.createCell(cnt);
													cell.setCellValue("X");
													cnt++;
												}
											}
										}else{
											if(tmp2.get("addFieldCn") != null && !tmp2.get("addFieldCn").toString().isEmpty()){
												cell = (SXSSFCell) row.createCell(cnt);
												cell.setCellValue(tmp2.get("addFieldCn").toString());
												cnt++;
											}else{
												cell = (SXSSFCell) row.createCell(cnt);
												cell.setCellValue("");
												cnt++;
											}
										}
										cell.setCellStyle(mainCs);
									}
								}
							}
						}else {
							if(tmp.get(excelRow[cnt]) != null && !tmp.get(excelRow[cnt]).toString().isEmpty()){
								cell = (SXSSFCell) row.createCell(cnt);
								cell.setCellValue(tmp.get(excelRow[cnt]).toString());
				            	cnt++;
							}else{
								cell = (SXSSFCell) row.createCell(cnt);
								cell.setCellValue("");
								cnt++;
							}
						}
						cell.setCellStyle(mainCs);
					}else {
						if(excelFlag.equals("bbs")) {
							if(tmp.get(excelRow[cnt]) != null && !tmp.get(excelRow[cnt]).toString().isEmpty()){
								cell = (SXSSFCell) row.createCell(cnt);
								cell.setCellValue(tmp.get(excelRow[cnt]).toString());
								cnt++;
							}else{
								cell = (SXSSFCell) row.createCell(cnt);
								cell.setCellValue("");
								cnt++;
							}
						}else {
							if(tmp.get(excelRow[j]) != null && !tmp.get(excelRow[j]).toString().isEmpty()){
								cell.setCellValue(tmp.get(excelRow[j]).toString());
							}else{
								cell.setCellValue("");
							}
						}
					}
		            
				}
	            
			}
			
	        Date date = new Date();
			SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
			String now = dt.format(date);
			String header = request.getHeader( "User-Agent" );
			filename += "_"+now;
			
			if ( header.indexOf( "MSIE" ) > -1 ) {
				filename = URLEncoder.encode( filename, "UTF-8" ).replaceAll( "\\+", "%20" );
			}else if ( header.indexOf( "Trident" ) > -1 ) { 
				filename = URLEncoder.encode( filename, "UTF-8" ).replaceAll( "\\+", "%20" );
			}else if ( header.indexOf( "Chrome" ) > -1 ) {
				StringBuffer sb = new StringBuffer();
				for ( int i = 0; i < filename.length(); i++ ) {
					char c = filename.charAt( i );
					if ( c > '~' ) {
						sb.append( URLEncoder.encode( "" + c, "UTF-8" ) );
					}else {
						sb.append( c );
					}
				}
				filename = sb.toString();
			}
			
			Cookie cookie = new Cookie("fileDownloadToken", "true");
			cookie.setPath("/");
			response.addCookie(cookie);
			
			response.setContentType("application/vnd.ms-excel"); 
			response.setHeader("Content-Disposition", "attachment;filename=" + filename + ".xlsx"); 
			ServletOutputStream output = response.getOutputStream(); 
			workbook.write(output); 
			output.flush(); 
			output.close();

	    } catch (Exception e) {
	        if(workbook != null) try { workbook.close(); } catch(Exception ignore) {}
	    } finally {
	        try {
	            workbook.close();
	            workbook.dispose();
	            if(workbook != null) try { workbook.close(); } catch(Exception ignore) {}
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
		
		
	}
	
}
