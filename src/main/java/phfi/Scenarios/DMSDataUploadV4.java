package phfi.Scenarios;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import jxl.Sheet;
import jxl.Workbook;
import phfi.GlobalMethods.GlobalMethods;
import phfi.GlobalMethods.GlobalWait;

public class DMSDataUploadV4 {
	
	public DMSDataUploadV4() {
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
		GWait.Wait_GetElementByXpath("//div/div/div/div[2]/div/table/tbody/tr[1]/td[2]/a").click();
		Thread.sleep(2500);
		
		openNewTabWithURL();
			readfolders();
			readDocs();
			for (int i = 0; i <= 10 ; i++) {
				try {
					openOldTabL();
					GWait.Wait_GetElementByXpath("//div/div/div/div[2]/div/table/tbody/tr[1]/td[2]/a").click();
					Thread.sleep(2500);
					
//				openNewTabWithURL();
						readfolders();
						readDocs();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					openOldTabL();
				}
			}
		
	}
	
	// ---Open New Tab----//
		public static void openNewTab() {
			GlobalMethods.driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
			JavascriptExecutor jse = (JavascriptExecutor) GlobalMethods.driver;
			jse.executeScript("window.open()");
		}

		public static void CloseNewTab() {
			GlobalMethods.driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
			// Switch first tab
			GlobalMethods.driver.switchTo().defaultContent();
			GlobalMethods.driver.close();
		}
		
		public static String FolderURL = "file:///C:/Users/BabluKumarPrasad/Documents/test/";
		public static String ClinionURL = "http://edc-demo.eclinion.com/Default.aspx";
		
		public static void openNewTabWithURL() {
			GlobalMethods.driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
			JavascriptExecutor jse = (JavascriptExecutor) GlobalMethods.driver;
			jse.executeScript("window.open()");

			ArrayList<String> tabs = new ArrayList<String>(GlobalMethods.driver.getWindowHandles());
			GlobalMethods.driver.switchTo().window(tabs.get(1));
			GlobalMethods.driver.get(FolderURL);
			/*
			 * String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
			 * driver.findElement(By.linkText(GmailURL)).sendKeys(
			 * selectLinkOpeninNewTab);
			 */
		}
		public static void openOldTabL() {
			ArrayList<String> tabs = new ArrayList<String>(GlobalMethods.driver.getWindowHandles());
			GlobalMethods.driver.switchTo().window(tabs.get(0));

		}
		public static void openNewTabL() {
			ArrayList<String> tabs = new ArrayList<String>(GlobalMethods.driver.getWindowHandles());
			GlobalMethods.driver.switchTo().window(tabs.get(1));

		}
		String foldersName;
		String Fold;
		public void readfolders() throws Exception {
			
			try {
				List<WebElement> DatafolderList = GlobalMethods.driver.findElements(By.className("dir"));
				for (WebElement FoldersElement : DatafolderList) {
					System.out.println("Folder name: "+FoldersElement.getText());
					foldersName = FoldersElement.getText();
					Fold = foldersName.substring(0, foldersName.length() - 1);
					System.out.println("Trim data" +Fold);
					openOldTabL();
					FolderCreate();
					openNewTabL();
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
		}
		String fileName;
		public void readDocs() throws Exception {
			
			try {
				List<WebElement> DatafileList = GlobalMethods.driver.findElements(By.className("file"));
				for (WebElement DocssElement : DatafileList) {
					System.out.println("Folder name: "+DocssElement.getText());
					fileName = DocssElement.getText();
					openOldTabL();
					dmsdataupload();
					openNewTabL();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.getMessage();
				openOldTabL();
			}
		}
		
		

	public void dmsdataupload() throws Exception {
		GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_anchAddDoc", 120).click();
		
		Thread.sleep(1000);
		WebElement DocNM = GWait.Wait_GetElementById("txtDocName", 120);
		DocNM.clear();
		DocNM.sendKeys(fileName);
		WebElement versionData = GWait.Wait_GetElementById("txtVersion", 120);
		versionData.clear();
		versionData.sendKeys(fileName);
		GWait.Wait_GetElementByXpath(".//*[@id='rbtnVersion']/tbody/tr/td[2]/label").click();
		GWait.Wait_GetElementByXpath(".//*[@id='rbtnAccess']/tbody/tr/td[2]/label").click();
		GWait.Wait_GetElementByXpath(".//*[@id='rbtnLocation']/tbody/tr/td[1]/label").click();
		GWait.Wait_GetElementById("fileUpload", 120).click();
		String FinalDataPath = FolderURL + fileName;
		Thread.sleep(1500);
		StringSelection selection = new StringSelection(FinalDataPath);
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
		WebElement comnt = GWait.Wait_GetElementById("txtComments", 120);
		comnt.clear();
		comnt.sendKeys(fileName);
		Thread.sleep(1000);
		WebElement UploadBTN = GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_btnUpload", 120);
		GlobalMethods.scrollToElement(UploadBTN);
		UploadBTN.click();
		
		Thread.sleep(4000);
		GlobalMethods.isAlertPresent();
	}
	
	public void FolderCreate() throws Exception {
		Thread.sleep(1500);
		GWait.Wait_GetElementById("btnShowCreateFolder", 120).click();

		WebElement CreateFolder = GWait.Wait_GetElementById("txtAddNewfoldername", 120);
		CreateFolder.sendKeys(Fold);
		GWait.Wait_GetElementById("btnconfiggo", 120).click();
	}

}
