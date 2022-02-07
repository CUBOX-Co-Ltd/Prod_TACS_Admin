package cubox.admin.cmmn.util;
 
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.AbstractView;

import cubox.admin.main.service.vo.ExcelVO;

@Service("excelDownloadView")
public class ExcelDownloadView extends AbstractView {
		
	//private String GLOBAL_AES256_KEY = CuboxProperties.getProperty("Globals.aes256.key");	
    private static final String CONTENT_TYPE = "application/vnd.ms-excel"; // Content Type 설정
    
    public ExcelDownloadView() {
        setContentType(CONTENT_TYPE);
    }
   
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, 
    			HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	ServletOutputStream out = null;
    	ModuleUtil moduleUtil = null;
        try {
        	moduleUtil = ModuleUtil.getInstance();
        	String excelName = (String)model.get("excelName");
        	String strImgYn = (String)model.get("strImgYn");
        	        	
            // 파일 이름 설정
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar c1 = Calendar.getInstance();
            String yyyymmdd = sdf.format(c1.getTime());
            String fileName = excelName + "_" + yyyymmdd +".xlsx";
            fileName = URLEncoder.encode(fileName,"UTF-8"); // UTF-8로 인코딩
            
            // 다운로드 되는 파일명 설정
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
           
            // SXSSFWorkbook 생성
            XSSFWorkbook workbook = new XSSFWorkbook();
            //workbook.setCompressTempFiles(true);
 
            // SXSSFSheet 생성
            XSSFSheet sheet = (XSSFSheet) workbook.createSheet();
            //sheet.setRandomAccessWindowSize(100); // 메모리 행 100개로 제한, 초과 시 Disk로 flush
           
            // 엑셀에 출력할 List
            List<ExcelVO> resultList = (List<ExcelVO>) model.get("resultList");
            
            // Cell 스타일 값
            sheet.setDefaultColumnWidth(15);
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setFontName("맑은 고딕");
            font.setFontHeightInPoints((short) 10);
            font.setColor(HSSFColor.BLACK.index);
            style.setFont(font);
            style.setAlignment(CellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);  
            style.setWrapText(true);            
            
            ///////////헤더줄////////////////////
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setFontName("맑은 고딕");
            headerFont.setFontHeightInPoints((short) 10);
            headerFont.setColor(HSSFColor.BLACK.index);
            headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
            headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            headerStyle.setFillForegroundColor(HSSFColor.GOLD.index);
            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            ////////////////////////////////////////
                  
            // header 생성
            String headerList[] = (String[])model.get("excelHeader");
            XSSFRow header = (XSSFRow) sheet.createRow(0);
            String imgHeaderList[] = new String[headerList.length+1];
            imgHeaderList[0] = "이미지";            
            System.arraycopy(headerList, 0, imgHeaderList, 1, headerList.length);
            //sheet.setColumnWidth(0, 2100);
            if(strImgYn!=null && strImgYn.equals("Y")) {
            	sheet.setColumnWidth(0, 2100);
            	setHeaderCellValue(header, imgHeaderList, headerStyle); // 헤더 칼럼명 설정 
            } else {
            	sheet.setColumnWidth(0, 4500);
            	setHeaderCellValue(header, headerList, headerStyle); // 헤더 칼럼명 설정 
            } 
            // 행 데이터 생성
            int rowCount = 1;
            moduleUtil.setCurrentStateCount(0);
            if(resultList != null) {
            	moduleUtil.setTotalRowCount(resultList.size());              	
		        for (ExcelVO cellVO : resultList) {	
		        	XSSFRow aRow = (XSSFRow) sheet.createRow(rowCount++);
		            setEachRow(aRow, cellVO, style, strImgYn);	
		            moduleUtil.setCurrentStateCount(moduleUtil.getCurrentStateCount()+1);
		        }
	        } else {
	        	moduleUtil.setTotalRowCount(0);
	        }                       
            out = response.getOutputStream();
            workbook.write(out);            
            moduleUtil.setCurrentState("C");
            System.out.println("Excel 완료! " +excelName+"_"+yyyymmdd);            
        } catch (Exception e) {
        	moduleUtil.setCurrentState("C");
            throw e;
        } finally {
        	if (out != null) out.close();        	
        	moduleUtil.resetModuleUtil();
		}
    }
   
    private void setHeaderCellValue(XSSFRow header, String[] dataInfo, CellStyle headerStyle) {
        for(int i=0; i < dataInfo.length; i++){
        	Cell cell = header.createCell(i);
        	cell.setCellValue(dataInfo[i]);
        	cell.setCellStyle(headerStyle);
        }
    }
   
   
    private void setEachRow(XSSFRow aRow, ExcelVO excelVO, CellStyle style, String strImgYn) {
    	int i = 0;
    	if(strImgYn!=null && strImgYn.equals("Y")) i = 1;
    	
    	Cell cell00 = aRow.createCell(i++);
    	cell00.setCellValue(excelVO.getCELL1());
    	cell00.setCellStyle(style);
    	
    	Cell cell01 = aRow.createCell(i++);
    	cell01.setCellValue(excelVO.getCELL2());
    	cell01.setCellStyle(style);
    	
    	Cell cell02 = aRow.createCell(i++);
    	cell02.setCellValue(excelVO.getCELL3());
    	cell02.setCellStyle(style);
    	
    	Cell cell03 = aRow.createCell(i++);
    	cell03.setCellValue(excelVO.getCELL4());
    	cell03.setCellStyle(style);
    	
    	Cell cell04 = aRow.createCell(i++);
    	cell04.setCellValue(excelVO.getCELL5());
    	cell04.setCellStyle(style);
    	
    	Cell cell05 = aRow.createCell(i++);
    	cell05.setCellValue(excelVO.getCELL6());
    	cell05.setCellStyle(style);
    	
    	Cell cell06 = aRow.createCell(i++);
    	cell06.setCellValue(excelVO.getCELL7());
    	cell06.setCellStyle(style);
    	
    	Cell cell07 = aRow.createCell(i++);
    	cell07.setCellValue(excelVO.getCELL8());
    	cell07.setCellStyle(style);
    	
    	Cell cell08 = aRow.createCell(i++);
    	cell08.setCellValue(excelVO.getCELL9());
    	cell08.setCellStyle(style);
    	
    	Cell cell09 = aRow.createCell(i++);
    	cell09.setCellValue(excelVO.getCELL10());
    	cell09.setCellStyle(style);
    	
    	Cell cell10 = aRow.createCell(i++);
    	cell10.setCellValue(excelVO.getCELL11());
    	cell10.setCellStyle(style);
    	
    	Cell cell11 = aRow.createCell(i++);
    	cell11.setCellValue(excelVO.getCELL12());
    	cell11.setCellStyle(style);
    	
    	Cell cell12 = aRow.createCell(i++);
    	cell12.setCellValue(excelVO.getCELL13());
    	cell12.setCellStyle(style);
    	
    	Cell cell13 = aRow.createCell(i++);
    	cell13.setCellValue(excelVO.getCELL14());
    	cell13.setCellStyle(style);
    	
    	Cell cell14 = aRow.createCell(i++);
    	cell14.setCellValue(excelVO.getCELL15());
    	cell14.setCellStyle(style);
    	
    	Cell cell15 = aRow.createCell(i++);
    	cell15.setCellValue(excelVO.getCELL16());
    	cell15.setCellStyle(style);
    	
    	Cell cell16 = aRow.createCell(i++);
    	cell16.setCellValue(excelVO.getCELL17());
    	cell16.setCellStyle(style);
    	
    	Cell cell17 = aRow.createCell(i++);
    	cell17.setCellValue(excelVO.getCELL18());
    	cell17.setCellStyle(style);
    	
    	Cell cell18 = aRow.createCell(i++);
    	cell18.setCellValue(excelVO.getCELL19());
    	cell18.setCellStyle(style);
    	
    	Cell cell19 = aRow.createCell(i++);
    	cell19.setCellValue(excelVO.getCELL20());
    	cell19.setCellStyle(style);
    	
    	Cell cell20 = aRow.createCell(i++);
    	cell20.setCellValue(excelVO.getCELL21());
    	cell20.setCellStyle(style);
    	
    	Cell cell21 = aRow.createCell(i++);
    	cell21.setCellValue(excelVO.getCELL22());
    	cell21.setCellStyle(style);
    	
    	Cell cell22 = aRow.createCell(i++);
    	cell22.setCellValue(excelVO.getCELL23());
    	cell22.setCellStyle(style);
    	
    	Cell cell23 = aRow.createCell(i++);
    	cell23.setCellValue(excelVO.getCELL24());
    	cell23.setCellStyle(style);
    	
    	Cell cell24 = aRow.createCell(i++);
    	cell24.setCellValue(excelVO.getCELL25());
    	cell24.setCellStyle(style);
    	
    	Cell cell25 = aRow.createCell(i++);
    	cell25.setCellValue(excelVO.getCELL26());
    	cell25.setCellStyle(style);
    	
    	Cell cell26 = aRow.createCell(i++);
    	cell26.setCellValue(excelVO.getCELL27());
    	cell26.setCellStyle(style);
    	
    	Cell cell27 = aRow.createCell(i++);
    	cell27.setCellValue(excelVO.getCELL28());
    	cell27.setCellStyle(style);
    	
    	Cell cell28 = aRow.createCell(i++);
    	cell28.setCellValue(excelVO.getCELL29());
    	cell28.setCellStyle(style);
    	
    	Cell cell29 = aRow.createCell(i++);
    	cell29.setCellValue(excelVO.getCELL30());
    	cell29.setCellStyle(style);   
    }
      
}
