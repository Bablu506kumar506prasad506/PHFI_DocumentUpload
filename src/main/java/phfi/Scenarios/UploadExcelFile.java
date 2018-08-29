package phfi.Scenarios;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.server.browserlaunchers.DrivenSeleniumLauncher;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import jxl.Sheet;
import jxl.Workbook;
import phfi.GlobalMethods.GlobalMethods;
import phfi.GlobalMethods.GlobalWait;

public class UploadExcelFile {

	public UploadExcelFile() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	public void UploadExcelData() throws Exception {

		GlobalMethods.Superadmin_Login();

		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//div[2]/div/div[2]/div[1]/ul/li[6]/a");
		action.moveToElement(element).build().perform();

		GWait.Wait_GetElementByLinkText("Study Data Import").click();

		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/UploadDocument.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("ExcelDataUpload");

		int Rowcount = st.getRows();
		System.out.println("No of rows: "+Rowcount);
		for (int i = 1; i < Rowcount-1; i++) {

			String Project_Data = st.getCell(1, i).getContents();
			String Study_Data = st.getCell(2, i).getContents();
			String UploadExcelDoc_Data = st.getCell(3, i).getContents();

			/*
			 * Select ProjctSelct = new Select(GWait.Wait_GetElementById(
			 * "ctl00_ContentPlaceHolder1_ddlDataUplodProjectList"));
			 * ProjctSelct.selectByVisibleText("");
			 */

			Select StudySelct = new Select(
					GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_ddlDataUploadStudiesList"));
			StudySelct.selectByVisibleText(Study_Data);
			GlobalMethods.scrollToBottom();

			WebElement element1 = GWait.Wait_GetElementByXpath("//div/div[2]/div/div[2]/div[4]/div[2]/input[1]");
			element1.click();

			StringSelection selection = new StringSelection(UploadExcelDoc_Data);
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

			GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_btnUploadData", 120).click();
			try {
				if (GlobalMethods.driver.findElement(By.id("ctl00_ContentPlaceHolder1_lblError")).isDisplayed()) {
					GlobalMethods.driver.findElement(By.id("ctl00_ContentPlaceHolder1_lblError")).getText();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			Thread.sleep(15000);
		}

		Thread.sleep(2000);
		GWait.Wait_GetElementByXpath("//div[1]/div[1]/div[2]/ul/li[1]/a").click();

	}

}
