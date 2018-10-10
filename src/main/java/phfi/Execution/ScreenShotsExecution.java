package phfi.Execution;

import java.io.FileInputStream;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import phfi.GlobalMethods.GlobalMethods;
import phfi.Scenarios.RunCMDBatch;

public class ScreenShotsExecution {
	
	@BeforeMethod
	public void beforeMethod() throws Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/UploadDocument.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("LoginDetails");

		String URL = r1.getCell(0, 3).getContents();
		String firefoxBrowser = r1.getCell(1, 2).getContents();
		GlobalMethods.LaunchBrowser(firefoxBrowser, URL);
		Thread.sleep(500);
		GlobalMethods.screenShots();
	}
	
	@Test
	public void RunCMDBatch_Method() throws Exception{
		RunCMDBatch rb = new RunCMDBatch();
		rb.RunBatch_DataMigrationSDI();
	}

}
