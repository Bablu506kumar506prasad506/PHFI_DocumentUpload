package phfi.Scenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
		HeadFileVerify();
		CreateSubFolders();

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

}
