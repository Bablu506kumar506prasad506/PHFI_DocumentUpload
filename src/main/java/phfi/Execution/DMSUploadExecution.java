package phfi.Execution;

import java.io.FileInputStream;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import phfi.GlobalMethods.GlobalMethods;
import phfi.Scenarios.DMSDataUpload;
import phfi.Scenarios.DMSDataUploadV3;
import phfi.Scenarios.DMSDataUploadsV2;

public class DMSUploadExecution {
	
	@BeforeMethod
	public void beforeMethod() throws Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DMSDataUpload.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("LoginDetails");

		String URL = r1.getCell(0, 1).getContents();
		String firefoxBrowser = r1.getCell(1, 1).getContents();
		GlobalMethods.LaunchBrowser(firefoxBrowser, URL);
		Thread.sleep(500);
		GlobalMethods.screenShots();
	}
	
	/*@Test
	public void DMSUploadData_M() throws Exception {
		DMSDataUpload DMSD = new DMSDataUpload();
		DMSD.DMSUploadMethod();
	}*/
	/*@Test
	public void DMSUploadDatav2_M() throws Exception {
		DMSDataUploadsV2 DMSDv2 = new DMSDataUploadsV2();
		DMSDv2.DMSUploadMethod();
	}*/
	
	@Test
	public void DMSUploadDatav3_M() throws Exception {
		DMSDataUploadV3 DMSDv3 = new DMSDataUploadV3();
		DMSDv3.DMSUploadMethod();
	}

}
