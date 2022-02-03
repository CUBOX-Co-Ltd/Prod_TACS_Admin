package cubox.admin.excel;
 
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

import cubox.admin.cmmn.util.StringUtil;

/**
 * 근태관리 > 월간근태현황 엑셀
 */
public class MonthlyWorkExcel extends AbstractView {
	private static final String CONTENT_TYPE = "application/vnd.ms-excel"; // Content Type 설정

	public MonthlyWorkExcel() {
		setContentType(CONTENT_TYPE);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<< monthly_work_excel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			
			String excelName = StringUtil.nvl(model.get("excelName"));
			
			// 파일 이름 설정
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar c1 = Calendar.getInstance();
			String yyyymmdd = sdf.format(c1.getTime());
			String fileName = excelName + "_" + yyyymmdd +".xlsx";
			fileName = URLEncoder.encode(fileName,"UTF-8"); // UTF-8로 인코딩
			
			// 다운로드 되는 파일명 설정
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			
			// SXSSFWorkbook 생성
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			workbook.setCompressTempFiles(true);

			// SXSSFSheet 생성
			SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet(StringUtil.nvl(model.get("excelName")));
			//sheet.setRandomAccessWindowSize(100); // 메모리 행 100개로 제한, 초과 시 Disk로 flush
		   
			// 엑셀에 출력할 List
			List<HashMap<String, Object>> resultList = (List<HashMap<String, Object>>) model.get("resultList");
			
			// Cell 스타일 값
			sheet.setDefaultColumnWidth(12);
			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setFontName("맑은 고딕");
			font.setFontHeightInPoints((short) 8);
			font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			style.setWrapText(true);
			
			///////////헤더줄////////////////////
			CellStyle headerStyle = workbook.createCellStyle();
			Font headerFont = workbook.createFont();
			headerFont.setFontName("맑은 고딕");
			headerFont.setFontHeightInPoints((short) 9);
			headerFont.setColor(HSSFColor.BLACK.index);
			headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headerStyle.setFont(headerFont);
			headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
			headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			headerStyle.setFillForegroundColor(HSSFColor.GOLD.index);
			headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
			CellStyle headerStyleRed = workbook.createCellStyle();
			Font headerFontRed = workbook.createFont();
			headerFontRed.setFontName("맑은 고딕");
			headerFontRed.setFontHeightInPoints((short) 9);
			headerFontRed.setColor(HSSFColor.RED.index);
			headerFontRed.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headerStyleRed.setFont(headerFontRed);
			headerStyleRed.setAlignment(CellStyle.ALIGN_CENTER);
			headerStyleRed.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			headerStyleRed.setFillForegroundColor(HSSFColor.GOLD.index);
			headerStyleRed.setFillPattern(CellStyle.SOLID_FOREGROUND);      
			
			CellStyle headerStyleBlue = workbook.createCellStyle();
			Font headerFontBlue = workbook.createFont();
			headerFontBlue.setFontName("맑은 고딕");
			headerFontBlue.setFontHeightInPoints((short) 9);
			headerFontBlue.setColor(HSSFColor.BLUE.index);
			headerFontBlue.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headerStyleBlue.setFont(headerFontBlue);
			headerStyleBlue.setAlignment(CellStyle.ALIGN_CENTER);
			headerStyleBlue.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			headerStyleBlue.setFillForegroundColor(HSSFColor.GOLD.index);
			headerStyleBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);
			////////////////////////////////////////
			
			// header 생성
			List<HashMap<String, Object>> headerList = (List<HashMap<String, Object>>) model.get("headerList");
			setHeaderCellValue(sheet, headerList, headerStyle, headerStyleRed, headerStyleBlue); // 헤더 칼럼명 설정
			
			// 행 데이터 생성
			int rowCount = 2;
			if(resultList != null) {
				for (HashMap<String, Object> map : resultList) {
					SXSSFRow aRow = (SXSSFRow) sheet.createRow(rowCount++);
					setEachRow(aRow, map, style);
				}
			}
			
			ServletOutputStream out = response.getOutputStream();
			workbook.write(out);
			if (out != null) out.close();
		
		} catch (Exception e) {
			throw e;
		}
	}

	private void setHeaderCellValue(SXSSFSheet sheet, List<HashMap<String, Object>> headerList, CellStyle headerStyle, CellStyle headerStyleRed, CellStyle headerStyleBlue) {
		// 1st header
		SXSSFRow header = (SXSSFRow) sheet.createRow(0);
		
		Cell cell = header.createCell(0);
		cell.setCellValue("고유번호(FID)");
		cell.setCellStyle(headerStyle);
		
		cell = header.createCell(1);
		cell.setCellValue("성명");
		cell.setCellStyle(headerStyle);
		
		cell = header.createCell(2);
		cell.setCellValue("부서");
		cell.setCellStyle(headerStyle);
		
		/* 2021-01-13 직급 삭제
		cell = header.createCell(3);
		cell.setCellValue("직급");
		cell.setCellStyle(headerStyle);*/
		
		cell = header.createCell(3);
		cell.setCellValue("출근");
		cell.setCellStyle(headerStyle);
		
		cell = header.createCell(4);
		cell.setCellValue("지각");
		cell.setCellStyle(headerStyle);
		
		cell = header.createCell(5);
		cell.setCellValue("조퇴");
		cell.setCellStyle(headerStyle);
		
		cell = header.createCell(6);
		cell.setCellValue("결근");
		cell.setCellStyle(headerStyle);
		
		for(int i = 0; i < headerList.size() ; i++){
			HashMap<String, Object> map = (HashMap<String, Object>) headerList.get(i);
			cell = header.createCell(i+7);
			cell.setCellValue(StringUtil.nvl(map.get("cf_day")));
			if(StringUtil.nvl(map.get("cf_color")).equals("#FF0000")) {
				cell.setCellStyle(headerStyleRed);
			} else if(StringUtil.nvl(map.get("cf_color")).equals("#0000FF")) {
				cell.setCellStyle(headerStyleBlue);
			} else {
				cell.setCellStyle(headerStyle);
			}
		}
		
		// 2nd header
		header = (SXSSFRow) sheet.createRow(1);
		
		for(int i = 0; i < headerList.size() ; i++){
			HashMap<String, Object> map = (HashMap<String, Object>) headerList.get(i);
			cell = header.createCell(i+7);
			cell.setCellValue(StringUtil.nvl(map.get("cf_day3")));
			if(StringUtil.nvl(map.get("cf_color")).equals("#FF0000")) {
				cell.setCellStyle(headerStyleRed);	
			} else if(StringUtil.nvl(map.get("cf_color")).equals("#0000FF")) {
				cell.setCellStyle(headerStyleBlue);	
			} else {
				cell.setCellStyle(headerStyle);
			}
		}
		
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 3, 3));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 4, 4));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 5, 5));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 6, 6));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 7, 7));
	}

	private void setEachRow(SXSSFRow aRow, HashMap<String, Object> map, CellStyle style) {
		int i = 0;
		
		Cell cell00 = aRow.createCell(i++);
		cell00.setCellValue(StringUtil.nvl(map.get("fuid")));
		cell00.setCellStyle(style);
		
		Cell cell01 = aRow.createCell(i++);
		cell01.setCellValue(StringUtil.nvl(map.get("funm")));
		cell01.setCellStyle(style);
		
		Cell cell03 = aRow.createCell(i++);
		cell03.setCellValue(StringUtil.nvl(map.get("fpartnm2")));
		cell03.setCellStyle(style);
		
		/* 2021-01-13 직급 삭제
		Cell cell04 = aRow.createCell(i++);
		cell04.setCellValue(StringUtil.nvl(map.get("fpartnm3")));
		cell04.setCellStyle(style);
		*/
		
		Cell cell04 = aRow.createCell(i++);
		cell04.setCellValue(StringUtil.nvl(map.get("fatncnt")));
		cell04.setCellStyle(style);
		
		Cell cell05 = aRow.createCell(i++);
		cell05.setCellValue(StringUtil.nvl(map.get("flatecnt")));
		cell05.setCellStyle(style);
		
		Cell cell06 = aRow.createCell(i++);
		cell06.setCellValue(StringUtil.nvl(map.get("flvelycnt")));
		cell06.setCellStyle(style);
		
		Cell cell07 = aRow.createCell(i++);
		cell07.setCellValue(StringUtil.nvl(map.get("fnoatncnt")));
		cell07.setCellStyle(style);
		
		Cell cell08 = aRow.createCell(i++);
		cell08.setCellValue(StringUtil.nvl(map.get("cf_01")));
		cell08.setCellStyle(style);
		
		Cell cell09 = aRow.createCell(i++);
		cell09.setCellValue(StringUtil.nvl(map.get("cf_02")));
		cell09.setCellStyle(style);
		
		Cell cell10 = aRow.createCell(i++);
		cell10.setCellValue(StringUtil.nvl(map.get("cf_03")));
		cell10.setCellStyle(style);
		
		Cell cell11 = aRow.createCell(i++);
		cell11.setCellValue(StringUtil.nvl(map.get("cf_04")));
		cell11.setCellStyle(style);
		
		Cell cell12 = aRow.createCell(i++);
		cell12.setCellValue(StringUtil.nvl(map.get("cf_05")));
		cell12.setCellStyle(style);
		
		Cell cell13 = aRow.createCell(i++);
		cell13.setCellValue(StringUtil.nvl(map.get("cf_06")));
		cell13.setCellStyle(style);
		
		Cell cell14 = aRow.createCell(i++);
		cell14.setCellValue(StringUtil.nvl(map.get("cf_07")));
		cell14.setCellStyle(style);
		
		Cell cell15 = aRow.createCell(i++);
		cell15.setCellValue(StringUtil.nvl(map.get("cf_08")));
		cell15.setCellStyle(style);
		
		Cell cell16 = aRow.createCell(i++);
		cell16.setCellValue(StringUtil.nvl(map.get("cf_09")));
		cell16.setCellStyle(style);
		
		Cell cell17 = aRow.createCell(i++);
		cell17.setCellValue(StringUtil.nvl(map.get("cf_10")));
		cell17.setCellStyle(style);
		
		Cell cell18 = aRow.createCell(i++);
		cell18.setCellValue(StringUtil.nvl(map.get("cf_11")));
		cell18.setCellStyle(style);
		
		Cell cell19 = aRow.createCell(i++);
		cell19.setCellValue(StringUtil.nvl(map.get("cf_12")));
		cell19.setCellStyle(style);
		
		Cell cell20 = aRow.createCell(i++);
		cell20.setCellValue(StringUtil.nvl(map.get("cf_13")));
		cell20.setCellStyle(style);
		
		Cell cell21 = aRow.createCell(i++);
		cell21.setCellValue(StringUtil.nvl(map.get("cf_14")));
		cell21.setCellStyle(style);
		
		Cell cell22 = aRow.createCell(i++);
		cell22.setCellValue(StringUtil.nvl(map.get("cf_15")));
		cell22.setCellStyle(style);
		
		Cell cell23 = aRow.createCell(i++);
		cell23.setCellValue(StringUtil.nvl(map.get("cf_16")));
		cell23.setCellStyle(style);
		
		Cell cell24 = aRow.createCell(i++);
		cell24.setCellValue(StringUtil.nvl(map.get("cf_17")));
		cell24.setCellStyle(style);
		
		Cell cell25 = aRow.createCell(i++);
		cell25.setCellValue(StringUtil.nvl(map.get("cf_18")));
		cell25.setCellStyle(style);
		
		Cell cell26 = aRow.createCell(i++);
		cell26.setCellValue(StringUtil.nvl(map.get("cf_19")));
		cell26.setCellStyle(style);
		
		Cell cell27 = aRow.createCell(i++);
		cell27.setCellValue(StringUtil.nvl(map.get("cf_20")));
		cell27.setCellStyle(style);
		
		Cell cell28 = aRow.createCell(i++);
		cell28.setCellValue(StringUtil.nvl(map.get("cf_21")));
		cell28.setCellStyle(style);        
		
		Cell cell29 = aRow.createCell(i++);
		cell29.setCellValue(StringUtil.nvl(map.get("cf_22")));
		cell29.setCellStyle(style);   
		
		Cell cell30 = aRow.createCell(i++);
		cell30.setCellValue(StringUtil.nvl(map.get("cf_23")));
		cell30.setCellStyle(style); 
		
		Cell cell31 = aRow.createCell(i++);
		cell31.setCellValue(StringUtil.nvl(map.get("cf_24")));
		cell31.setCellStyle(style);
		
		Cell cell32 = aRow.createCell(i++);
		cell32.setCellValue(StringUtil.nvl(map.get("cf_25")));
		cell32.setCellStyle(style); 
		
		Cell cell33 = aRow.createCell(i++);
		cell33.setCellValue(StringUtil.nvl(map.get("cf_26")));
		cell33.setCellStyle(style); 
		
		Cell cell34 = aRow.createCell(i++);
		cell34.setCellValue(StringUtil.nvl(map.get("cf_27")));
		cell34.setCellStyle(style);
		
		Cell cell35 = aRow.createCell(i++);
		cell35.setCellValue(StringUtil.nvl(map.get("cf_28")));
		cell35.setCellStyle(style); 
		
		Cell cell36 = aRow.createCell(i++);
		cell36.setCellValue(StringUtil.nvl(map.get("cf_29")));
		cell36.setCellStyle(style); 
		
		Cell cell37 = aRow.createCell(i++);
		cell37.setCellValue(StringUtil.nvl(map.get("cf_30")));
		cell37.setCellStyle(style); 
		
		Cell cell38 = aRow.createCell(i++);
		cell38.setCellValue(StringUtil.nvl(map.get("cf_31")));
		cell38.setCellStyle(style); 
	}
}
