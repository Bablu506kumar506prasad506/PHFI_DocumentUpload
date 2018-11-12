package phfi.Scenarios;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import phfi.GlobalMethods.GlobalMethods;
import phfi.GlobalMethods.GlobalWait;

public class DMSDataUploadV3 {

	public DMSDataUploadV3() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	String BeforePath;
	int i;
	int i1;

	public void DMSUploadMethod() throws Exception {

		GlobalMethods.PI_LoginForDMSUpload();

		Thread.sleep(3000);
		GWait.Wait_GetElementByXpath("(//a[contains(text(),'DMS')])[1]").click();

		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/DMSDataUpload.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("Project&StudyDetails");

		String ProjectFolderCreation = st.getCell(1, 1).getContents();

		Thread.sleep(1500);
		GWait.Wait_GetElementById("btnShowCreateFolder", 120).click();

		WebElement CreateFolder = GWait.Wait_GetElementById("txtAddNewfoldername", 120);
		CreateFolder.sendKeys(ProjectFolderCreation);
		GWait.Wait_GetElementById("btnconfiggo", 120).click();
		HeadFileVerify();
		CreateSubFolders();
		DMSFolderCheck2();
		DMSFolderCheck3Next();
		CreateSubFolders2();
		DMSFolderCheck2();
		DMSFolderCheck3Next();
		CreateSubFolders2();

	}

	public void HeadFileVerify() throws Exception {
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/DMSDataUpload.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st1 = wb.getSheet("CreateFolder");
		int RowCount = st1.getRows();

		for (i1 = 1; i1 < RowCount; ) {

//				WebElement NextElmt = GWait.Wait_GetElementByXpath("//div[7]/div/div/div/div[3]/div[2]/div/ul/li[4]/a");
				List<WebElement> FolderNameData = GlobalMethods.driver.findElements(By.xpath("//td[2]/a"));
				for (WebElement webElement : FolderNameData) {
					String HeadFolder_Data = st1.getCell(2, i1).getContents();
//					Thread.sleep(2500);
					System.out.println("Folder Name: " + webElement.getText());
					if (webElement.getText().equalsIgnoreCase(HeadFolder_Data)) {
						Thread.sleep(1500);
						webElement.click();
						i1++;
						break;
					}else if (GWait.Wait_GetElementByXpath("//div[7]/div/div/div/div[3]/div[2]/div/ul/li[4]/a").getText().equalsIgnoreCase("2")) {
						WebElement nextbtn = GWait.Wait_GetElementByXpath("//div[7]/div/div/div/div[3]/div[2]/div/ul/li[4]/a");
						GlobalMethods.scrollToElement(nextbtn);
						nextbtn.click();
						List<WebElement> FolderNameData1 = GlobalMethods.driver.findElements(By.xpath("//td[2]/a"));
						for (WebElement webElement1 : FolderNameData1) {
//							Thread.sleep(2500);
							System.out.println("Folder Name: " + webElement1.getText());
							if (webElement1.getText().equalsIgnoreCase(HeadFolder_Data)) {
								Thread.sleep(1500);
								webElement1.click();
								i1++;
								break;
							}
						}
					}
				}
				break;
		}
	}

	public void CreateSubFolders() throws Exception, IOException {
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/DMSDataUpload.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st1 = wb.getSheet("CreateFolder");

		int RowCount1 = st1.getRows();
		for (i = 1; i <= RowCount1; ) {
			int ColomnCount = st1.getColumns();
			for (int j = 3; j < ColomnCount; j++) {
				
				String SubFolders_Data = st1.getCell(j, i).getContents();
				
				Thread.sleep(1500);
				GWait.Wait_GetElementById("btnShowCreateFolder", 120).click();

				WebElement CreateFolder = GWait.Wait_GetElementById("txtAddNewfoldername", 120);
				CreateFolder.sendKeys(SubFolders_Data);
				GWait.Wait_GetElementById("btnconfiggo", 120).click();
			}
			i++;
			break;
		}

	}
	
	public void DMSFolderCheck2() throws Exception {
		
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/DMSDataUpload.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st1 = wb.getSheet("CreateFolder");
		int RowCount = st1.getRows();
		
		List<WebElement> FolderNameData = GlobalMethods.driver.findElements(By.xpath("//td[2]/a"));
		
//		for (int i = 0; i < FolderNameData.size(); i++) {
			for (WebElement webElement : FolderNameData) {
				String HeadFolder_Data = st1.getCell(2, i1).getContents();
//				Thread.sleep(2500);
				System.out.println("Folder Name: " + webElement.getText());
				if (webElement.getText().equalsIgnoreCase(HeadFolder_Data)) {
					Thread.sleep(1500);
					webElement.click();
					i1++;
					break;
					
				}
				
			}
			
//		}
		
		}
	
	public void DMSFolderCheck3Next() throws Exception {
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/DMSDataUpload.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st1 = wb.getSheet("CreateFolder");
		int RowCount = st1.getRows();
		
		try {
			WebElement nextbtn = GlobalMethods.driver.findElement(By.xpath("//div[7]/div/div/div/div[3]/div[2]/div/ul/li[4]/a"));
			GlobalMethods.scrollToElement(nextbtn);
			nextbtn.click();
			List<WebElement> FolderNameData1 = GlobalMethods.driver.findElements(By.xpath("//td[2]/a"));
				for (WebElement webElement : FolderNameData1) {
					String HeadFolder_Data = st1.getCell(2, i1).getContents();
					System.out.println("Folder Name: " + webElement.getText());
					if (webElement.getText().equalsIgnoreCase(HeadFolder_Data)) {
						Thread.sleep(1500);
						webElement.click();
						i1++;
						break;
					}
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	public void CreateSubFolders2() throws Exception {
		
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/DMSDataUpload.xls");
		HSSFWorkbook wb = new HSSFWorkbook(fi);
		 
		HSSFSheet st1 = wb.getSheet("CreateFolder");
		
		Row row = st1.getRow(i);
//		int RowCount1 = st1.getRows();
//		for (i = i; i <= RowCount1; ) {
		 
	       int colNum = row.getLastCellNum();
//			int ColomnCount = st1.getColumns();
			System.out.println("Column ccount: "+colNum);
			for (int j = 3; j < colNum; j++) {
				
//				cell = st1.getRow(i).getCell(j);

				  String SubFolders_Data = row.getCell(j).getStringCellValue();
				
//				String SubFolders_Data = st1.getCell(j, i).getContents();
				
				Thread.sleep(1500);
				GWait.Wait_GetElementById("btnShowCreateFolder", 120).click();

				WebElement CreateFolder = GWait.Wait_GetElementById("txtAddNewfoldername", 120);
				CreateFolder.sendKeys(SubFolders_Data);
				GWait.Wait_GetElementById("btnconfiggo", 120).click();
			}
			i++;
//			break;
//		}
		
	}
	public String BrowserFile_Data = null;
	public void DMSDataUpload() throws Exception {

		GlobalMethods.scrollToBottom();
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/UploadDocument.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("UploadDocuments");
		
		int rowacount = r1.getRows();
		for (int i = 1; i < rowacount - 1; i++) {
			String FolderName_Data = r1.getCell(3, i).getContents();
			
			int colcount = r1.getColumns();
			for (int j = 0; j < colcount; j++) {
				String DocumentName_Data = r1.getCell(5, j).getContents();
				String UploadVersion_Data = r1.getCell(6, j).getContents();
//				BrowserFile_Data = r2.getCell(3, j).getContents();
				String UploadFile_Data = r1.getCell(4, j).getContents();
				String Comments_Data = r1.getCell(7, j).getContents();
				
				GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_anchAddDoc", 120).click();
				
				Thread.sleep(1000);
				GWait.Wait_GetElementById("txtDocName", 120).sendKeys(DocumentName_Data);
				GWait.Wait_GetElementById("txtVersion", 120).sendKeys(UploadVersion_Data);
				GWait.Wait_GetElementByXpath(".//*[@id='rbtnVersion']/tbody/tr/td[2]/label").click();
				GWait.Wait_GetElementByXpath(".//*[@id='rbtnAccess']/tbody/tr/td[2]/label").click();
				GWait.Wait_GetElementByXpath(".//*[@id='rbtnLocation']/tbody/tr/td[1]/label").click();
				GWait.Wait_GetElementById("fileUpload", 120).click();
				
				Thread.sleep(1500);
				StringSelection selection = new StringSelection(UploadFile_Data);
				java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				Thread.sleep(1000);
				clipboard.setContents(selection, null);
				Thread.sleep(1000);
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(1000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(500);
				GWait.Wait_GetElementById("txtComments", 120).sendKeys(Comments_Data);
				Thread.sleep(1000);
				GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_btnUpload", 120).click();
				
				Thread.sleep(4000);
				GlobalMethods.isAlertPresent();

			}
			
		}
		
		
	}
	

}
