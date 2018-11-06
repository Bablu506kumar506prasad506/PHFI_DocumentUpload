package phfi.Scenarios;

import java.io.FileInputStream;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import jxl.Sheet;
import jxl.Workbook;
import phfi.GlobalMethods.GlobalMethods;
import phfi.GlobalMethods.GlobalWait;

public class DMSDataUpload {

	public DMSDataUpload() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	String BeforePath;

	public void DMSUploadMethod() throws Exception {

		GlobalMethods.PI_LoginForDMSUpload();

		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/DMSDataUpload.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("Project&StudyDetails");

		String ProjectFolderCreation = st.getCell(1, 1).getContents();

		Thread.sleep(3000);
		GWait.Wait_GetElementByXpath("(//a[contains(text(),'DMS')])[1]").click();

		Thread.sleep(1500);
		GWait.Wait_GetElementById("btnShowCreateFolder", 120).click();

		WebElement CreateFolder = GWait.Wait_GetElementById("txtAddNewfoldername", 120);
		CreateFolder.sendKeys(ProjectFolderCreation);
		GWait.Wait_GetElementById("btnconfiggo", 120).click();

		/*
		 * List<WebElement> FolderNameData =
		 * GlobalMethods.driver.findElements(By.xpath("//td[2]/a")); for
		 * (WebElement FolderNameElement : FolderNameData) { System.out.println(
		 * "Folder Name: "+FolderNameElement.getText()); if
		 * (FolderNameElement.getText().trim().equalsIgnoreCase(
		 * ProjectFolderCreation)) { FolderNameElement.click(); break; } }
		 */

		// -----Folder Creation and File Upload---//
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
						
						List<WebElement> FolderNameData = GlobalMethods.driver.findElements(By.xpath("//td[2]/a"));
						for (WebElement FolderNameElement : FolderNameData) {
							System.out.println("Folder Name: " + FolderNameElement.getText());
							if (FolderNameElement.getText().trim().equalsIgnoreCase(BeforePath)) {
								Thread.sleep(1500);
								FolderNameElement.click();
								Thread.sleep(1500);
								List<WebElement> navigate = GlobalMethods.driver
										.findElements(By.xpath("//div[4]/div/div[2]/div/ul/li"));
								for (WebElement webElement : navigate) {
									if (webElement.getText().equalsIgnoreCase(BeforePath)) {
										Thread.sleep(1500);
										GWait.Wait_GetElementById("btnShowCreateFolder", 120).click();

										WebElement CreateFolder1 = GWait.Wait_GetElementById("txtAddNewfoldername",
												120);
										CreateFolder1.sendKeys(FileName);
										GWait.Wait_GetElementById("btnconfiggo", 120).click();
										break;
									}
								}
								break;
								
							} else {
								List<WebElement> nextBTN = GlobalMethods.driver
										.findElements(By.xpath("//div[7]/div/div/div/div[3]/div[2]/div/ul/li[4]/a"));
								for (WebElement NextElement : nextBTN) {
									System.out.println("Next word: " + NextElement.getText());
									if (NextElement.getText().equalsIgnoreCase("2")) {
										GlobalMethods.scrollToBottom();
										NextElement.click();
										List<WebElement> FolderNameData1 = GlobalMethods.driver
												.findElements(By.xpath("//td[2]/a"));
										for (WebElement FolderNameElement1 : FolderNameData1) {
											System.out.println("Folder Name: " + FolderNameElement1.getText());
											if (FolderNameElement1.getText().trim().equalsIgnoreCase(BeforePath)) {
												FolderNameElement1.click();
												List<WebElement> navigate = GlobalMethods.driver
														.findElements(By.xpath("//div[4]/div/div[2]/div/ul/li"));
												for (WebElement webElement : navigate) {
													if (webElement.getText().equalsIgnoreCase(BeforePath)) {
														Thread.sleep(1500);
														GWait.Wait_GetElementById("btnShowCreateFolder", 120).click();

														WebElement CreateFolder1 = GWait
																.Wait_GetElementById("txtAddNewfoldername", 120);
														CreateFolder1.sendKeys(FileName);
														GWait.Wait_GetElementById("btnconfiggo", 120).click();
														break;
													}
												}
												break;
											}
										}
										break;
									}else {
										List<WebElement> navigate = GlobalMethods.driver
												.findElements(By.xpath("//div[4]/div/div[2]/div/ul/li"));
										for (WebElement webElement : navigate) {
											if (webElement.getText().equalsIgnoreCase(BeforePath)) {
												Thread.sleep(1500);
												GWait.Wait_GetElementById("btnShowCreateFolder", 120).click();

												WebElement CreateFolder1 = GWait.Wait_GetElementById("txtAddNewfoldername",
														120);
												CreateFolder1.sendKeys(FileName);
												GWait.Wait_GetElementById("btnconfiggo", 120).click();
												break;
											}
										}
									}

								}
							}
							break;
						}

					}
				}

				break;
			}

		}
	}

}
