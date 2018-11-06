package phfi.Scenarios;

import java.io.FileInputStream;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import jxl.Sheet;
import jxl.Workbook;
import phfi.GlobalMethods.GlobalMethods;
import phfi.GlobalMethods.GlobalWait;

public class DMSDataUploadsV2 {
	
	public DMSDataUploadsV2() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	String BeforePath;
	
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
		
		Sheet st1 = wb.getSheet("CreateFolder");
		int RowCount = st1.getRows();
		for (int i = 1; i <= RowCount; i++) {
			int ColomnCount = st1.getColumns();
			for (int j = 1; j <= ColomnCount;) {
				String PathofFile = st1.getCell(3, i).getContents();
				String FileName = st1.getCell(4, i).getContents();

				String[] path1 = PathofFile.split("/");
				for (int k = 0; k < path1.length; k++) {
					System.out.println(path1[k]);
					if (path1[k].equalsIgnoreCase(FileName)) {
						BeforePath = path1[k - 1];
						WebElement NextElmt = GWait.Wait_GetElementByXpath("//div[7]/div/div/div/div[3]/div[2]/div/ul/li[4]/a"); 
						List<WebElement> FolderNameData = GlobalMethods.driver.findElements(By.xpath("//td[2]/a"));
						for (WebElement FolderNameElement : FolderNameData) {
							
							Thread.sleep(2500);
							System.out.println("Folder Name: " + FolderNameElement.getText());
							GlobalMethods.scrollToElement(NextElmt);
							System.out.println(NextElmt.getText());
							if (FolderNameElement.getText().trim().equalsIgnoreCase(BeforePath)) {
								Thread.sleep(1500);
								FolderNameElement.click();
								Thread.sleep(1500);
								Thread.sleep(1500);
								GWait.Wait_GetElementById("btnShowCreateFolder", 120).click();

								WebElement CreateFolder1 = GWait.Wait_GetElementById("txtAddNewfoldername",
										120);
								CreateFolder1.sendKeys(FileName);
								GWait.Wait_GetElementById("btnconfiggo", 120).click();
//								System.out.println(NextElmt.getText());
								
							}else {
								Thread.sleep(1500);
								NextElmt.click();
								Thread.sleep(1500);
								GWait.Wait_GetElementById("btnShowCreateFolder", 120).click();

								WebElement CreateFolder1 = GWait.Wait_GetElementById("txtAddNewfoldername",
										120);
								CreateFolder1.sendKeys(FileName);
								GWait.Wait_GetElementById("btnconfiggo", 120).click();
								
							}
						}
					}
					
				}
				break;
			}
		}
		
		
	}
	
	public void DMS1() {
		
	}

}
