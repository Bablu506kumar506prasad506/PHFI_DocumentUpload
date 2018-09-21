package phfi.Scenarios;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.javascript.host.Set;

import jxl.Sheet;
import jxl.Workbook;
import phfi.GlobalMethods.GlobalMethods;
import phfi.GlobalMethods.GlobalWait;

public class UploadPdfDocument {

	public UploadPdfDocument() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	public String BrowserFile_Data = null;

	public void uploadDoc() throws Exception {

		GlobalMethods.PI_Login();
		Thread.sleep(2000);
		GlobalMethods.scrollToBottom();

		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/UploadDocument.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("LoginDetails");
		Sheet r = wb.getSheet("Project&StudyDetails");
		Sheet r2 = wb.getSheet("20092018");
		int rowacount = r1.getRows();
		for (int i = 1; i < rowacount - 1; i++) {

			String StudyName_Data = r.getCell(1, i).getContents();

//			List<WebElement> studyelemnt = GlobalMethods.driver.findElements(By.cssSelector(".wraptext"));
//			for (WebElement webElement : studyelemnt) {
//				System.out.println("Test01 "+webElement.getText());
//				WebElement ActiontoBeTaken = GWait.Wait_GetElementByXpath("//div[4]/div[1]/div/div[2]/div/div/div[2]/table/tbody/tr["+i+"]/td[2]/div");
//				if (webElement.getText().equalsIgnoreCase(StudyName_Data)) {
//					ActiontoBeTaken.click();
					Thread.sleep(3000);
					GWait.Wait_GetElementByXpath("(//a[contains(text(),'DMS')])[6]").click();

					int rowacount2 = r2.getRows();
					for (int j = 1; j < rowacount2; j++) {
						GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_anchAddDoc", 120).click();
						String DocumentName_Data = r2.getCell(1, j).getContents();
						String UploadVersion_Data = r2.getCell(2, j).getContents();
//						BrowserFile_Data = r2.getCell(3, j).getContents();
						String UploadFile_Data = r2.getCell(3, j).getContents();
						String Comments_Data = r2.getCell(4, j).getContents();

						Thread.sleep(1000);
						GWait.Wait_GetElementById("txtDocName", 120).sendKeys(DocumentName_Data);
						GWait.Wait_GetElementById("txtVersion", 120).sendKeys(UploadVersion_Data);
						GWait.Wait_GetElementByXpath("//table[@id='rbtncatalogue']/tbody/tr[3]/td/label").click();
						GWait.Wait_GetElementByXpath("//table[@id='rbtnVersion']/tbody/tr/td[2]/label").click();
						GWait.Wait_GetElementByXpath("//table[@id='rbtnAccess']/tbody/tr/td[2]/label").click();
						GWait.Wait_GetElementByXpath("//table[@id='rbtnLocation']/tbody/tr[2]/td/label").click();

						//---File Upload through FTP---//
						Thread.sleep(1000);
						Select fileset = new Select(GWait.Wait_GetElementById("lstFiles"));
						fileset.selectByVisibleText(DocumentName_Data);
						Thread.sleep(500);
						GWait.Wait_GetElementById("txtComments", 120).sendKeys(Comments_Data);
						GWait.Wait_GetElementById("btnUpload", 120).click();
						Thread.sleep(4000);
						GlobalMethods.isAlertPresent();
						
						
						//---File Upload through Local System---//
						/*GWait.Wait_GetElementById("fileUpload", 120).click();
						
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
						GWait.Wait_GetElementById("btnUpload", 120).click();
						
						Thread.sleep(4000);
						GlobalMethods.isAlertPresent();*/

					}

				}

			}
//			Thread.sleep(2000);
//			GWait.Wait_GetElementByXpath("//div[1]/div[1]/div[2]/ul/li[1]/a").click();
//		}

//	}

}
