package com.csm.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelExportDynamic {
	private XSSFWorkbook wb = new XSSFWorkbook();
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	private static int rowNum = 0;
	
	private void writeHeader(Object[] printHeader, int length ) throws IOException {
		length = length -1;
		try {
			sheet = wb.createSheet("sheet1");			
		} catch (Exception e) {
			System.out.println("Already Exists This Workbook");
		}
		sheet.getPrintSetup().setLandscape((boolean) printHeader[0]);
		Header header = sheet.getHeader();
		header.setCenter((String) printHeader[1]);	
		row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue((String) printHeader[2]);
        
//          Merging cells by providing cell index  
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,length));
        
        //design
        XSSFCellStyle style = wb.createCellStyle();
        style = wb.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		style.setFillPattern(FillPatternType.DIAMONDS);
		XSSFFont font = wb.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short)16);
		style.setFont(font);
        cell.setCellStyle(style);
        
        for (int i = 1; i < length + 1 ; i++) {
        	cell = row.createCell(i);
        	cell.setCellStyle(style);
		}
        
	}
	/** Portrait page then Total Columns Width Max = 22, LanScape Page Then Total Columns Width Max 28  */
	private void createColumns(String[] columnsName, int[] columnsWidth ) {
		row = sheet.createRow(rowNum++);
		
        for (int i = 0; i < columnsName.length; i++) {
        	sheet.setColumnWidth(i, columnsWidth[i] * 1000);  // set Columns width
			cell = row.createCell(i);
			cell.setCellValue(columnsName[i]);
			
			//design
			 XSSFCellStyle style = wb.createCellStyle();
		        style = wb.createCellStyle();
				style.setBorderBottom(BorderStyle.THIN);
				style.setBorderTop(BorderStyle.THIN);
				style.setBorderLeft(BorderStyle.THIN);
				style.setBorderRight(BorderStyle.THIN);
				style.setAlignment(HorizontalAlignment.CENTER);
				style.setFillBackgroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
				style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
				style.setFillPattern(FillPatternType.DIAMONDS);
				XSSFFont font = wb.createFont();
				font.setBold(true);
				style.setFont(font);
		        cell.setCellStyle(style);
		}
		
	}
	
	private void createData(List<Object[]> data) {
//		int rowCount = 2;
		for (Object[] aBook : data) {
        	row = sheet.createRow(rowNum++);
             
            int columnCount = 0;  
            for (Object field : aBook) {
            	cell = row.createCell(columnCount++);
                if (field instanceof String) {
                	cell.setCellValue((String) field);
                } else if (field instanceof Double) {
                    cell.setCellValue((Double) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
              //design
   			 XSSFCellStyle style = wb.createCellStyle();
   		        style = wb.createCellStyle();
   				style.setBorderBottom(BorderStyle.THIN);
   				style.setBorderTop(BorderStyle.THIN);
   				style.setBorderLeft(BorderStyle.THIN);
   				style.setBorderRight(BorderStyle.THIN);
   				style.setWrapText(true);
   				style.setVerticalAlignment(VerticalAlignment.CENTER);
   				style.setFillBackgroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
				style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				style.setAlignment(HorizontalAlignment.LEFT);
				
				
				if(rowNum == data.size()+2) {
					style.setBorderBottom(BorderStyle.THICK);
				}
   		        cell.setCellStyle(style);
   		        
            }
             
        }
		
	}
	public void exportDynamic(HttpServletResponse res, Object[] printHeader, String[] headers, int[] columnWidth, List<Object[]> data) throws IOException {
		 	
			writeHeader(printHeader, headers.length);
			createColumns(headers, columnWidth);
	        createData(data);
	        ServletOutputStream stream = res.getOutputStream();
	        
	        wb.write(stream);
	        wb.close();
	        stream.close();
		
	}
	

}
