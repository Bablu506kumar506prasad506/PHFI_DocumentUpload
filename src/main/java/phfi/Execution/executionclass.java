package phfi.Execution;

import java.io.FileInputStream;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import phfi.GlobalMethods.GlobalMethods;
import phfi.Scenarios.UploadExcelFile;
import phfi.Scenarios.UploadPdfDocument;

public class executionclass {
	
	@BeforeMethod
	public void beforeMethod() throws Exception, Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/UploadDocument.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("LoginDetails");

		String URL = r1.getCell(0, 3).getContents();
		String firefoxBrowser = r1.getCell(1, 2).getContents();
		GlobalMethods.LaunchBrowser(firefoxBrowser, URL);
	}
	
	@Test
	public void UploadDocument() throws Exception{
		UploadPdfDocument uplod = new UploadPdfDocument();
		uplod.uploadDoc();
	}
	
	/*@Test
	public void UploadExeclDocument() throws Exception{
		UploadExcelFile UEF = new UploadExcelFile();
		UEF.UploadExcelData();
	}*/
	

}
