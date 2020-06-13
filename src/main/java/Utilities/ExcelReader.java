package Utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	XSSFWorkbook wb;
	XSSFSheet sheet;
	public ExcelReader(String file_path) throws Exception
	{
		try{
		File src = new File(file_path);
		FileInputStream fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
		}catch(Exception e){
		System.out.println(e.getMessage());
		}
	} 
	
	public int getrowCount(String sheetName)
	{
		int row = wb.getSheet(sheetName).getLastRowNum();
		return row;
	}
	
	public int getColumnCount(String sheetName)
	{
		sheet = wb.getSheet(sheetName);
	    XSSFRow row = sheet.getRow(0);
	    int colCount = row.getLastCellNum();
	    return colCount;
	}
	
	public String getData(String sheetName, int row, int column)
	{	
		sheet = wb.getSheet(sheetName);
		DataFormatter formatter = new DataFormatter();
		String data = formatter.formatCellValue(sheet.getRow(row).getCell(column));
		return data;
	}	
	
}
