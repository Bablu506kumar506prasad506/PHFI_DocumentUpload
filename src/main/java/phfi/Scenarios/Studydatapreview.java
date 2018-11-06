package phfi.Scenarios;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import jxl.Sheet;
import jxl.Workbook;
import phfi.GlobalMethods.GlobalMethods;
import phfi.GlobalMethods.GlobalWait;

public class Studydatapreview {
	
	public Studydatapreview() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);
	
	public void StudyDataPreviewM() throws Exception {
		int b = 2;
//		for (int b = 1; b < 10; b++) {
			
			Runtime runtime = null;
	        try{
	        	//cmd.exe /C start \"start hub\" \"C:\\BatchFiles\\START HUB.bat\
	        	Runtime.getRuntime().exec("cmd.exe /C start \"start hub\" \"C:\\BatchFiles\\STARTHUB1 (1) - Shortcut\"");
	        	Thread.sleep(3000);
//	        	Runtime.getRuntime().exec("C:\\AutoITScripts\\CompileAutoIT\\ClickAdmin.exe");
//	        	Runtime.getRuntime().exec("runas /profile /user:Administrator \"cmd.exe /C start \"start hub\"\"C:\\BatchFiles\\STARTHUB.bat\"");
	        }
	        catch(RuntimeException e){ 
	            e.printStackTrace();
	        }
	        
			GlobalMethods.Superadmin_Login();
			
			GlobalMethods.screenShots();

			Thread.sleep(3000);
			WebElement element = GWait.Wait_GetElementByXpath("//div[2]/div/div[2]/div[1]/ul/li[6]/a");
			GlobalMethods.screenShots();
			action.moveToElement(element).build().perform();

			GlobalMethods.screenShots();

			GWait.Wait_GetElementByLinkText("Study Data Preview").click();

			GlobalMethods.screenShots();

			FileInputStream fi = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/UploadDocument.xls");
			Workbook wb = Workbook.getWorkbook(fi);
			Sheet st = wb.getSheet("StudyDataPreview");

			int Rowcount = st.getRows();
			System.out.println("No of rows: " + Rowcount);
			for (int i = 1; i <= Rowcount; i++) {
				
				 try{
			        	//cmd.exe /C start \"start hub\" \"C:\\BatchFiles\\START HUB.bat\
			        	Runtime.getRuntime().exec("cmd.exe /C start \"start hub\" \"C:\\BatchFiles\\STARTHUB1 ("+b+") - Shortcut\"");
			        	Thread.sleep(3000);
//			        	Runtime.getRuntime().exec("C:\\AutoITScripts\\CompileAutoIT\\ClickAdmin.exe");
//			        	Runtime.getRuntime().exec("runas /profile /user:Administrator \"cmd.exe /C start \"start hub\"\"C:\\BatchFiles\\STARTHUB.bat\"");
			        }
			        catch(RuntimeException e){ 
			            e.printStackTrace();
			        }

				String Project_Data = st.getCell(1, i).getContents();
				String Study_Data = st.getCell(2, i).getContents();
				String Table_Data = st.getCell(3, i).getContents();

				Select ProjctSelct = new Select(
						GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_ddlProjectList"));
				ProjctSelct.selectByVisibleText(Project_Data);

				Select StudySelct = new Select(
						GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_ddlStudiesList"));
				System.out.println("Study_Data: " + Study_Data);
				StudySelct.selectByVisibleText(Study_Data);
				
				Select TableSelct = new Select(
						GWait.Wait_GetElementById("ddlTable"));
				System.out.println("Study_Data: " + Table_Data);
				TableSelct.selectByVisibleText(Table_Data);
				GlobalMethods.screenShots();

				WebElement element1 = GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_btnPreViewData");
				element1.click();
				GlobalMethods.screenShots();
				GlobalMethods.screenShots();
				
				WebElement element11 = GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_btnExportToExcel");
				element11.click();
				GlobalMethods.screenShots();
				GlobalMethods.screenShots();
				ExcelCOMP();
				b++;
				
			}
			
//		}
		
	}
	
	public void ExcelCOMP() throws Exception {
		
		
	        	
	        	try {
					Runtime.getRuntime().exec("C:\\Program Files (x86)\\Microsoft Office\\Office16\\DCF\\SPREADSHEETCOMPARE.exe");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	Thread.sleep(3000);
	        }

}
