package phfi.GlobalMethods;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.Sheet;
import jxl.Workbook;
import phfi.Scenarios.UploadPdfDocument;

public class GlobalMethods<var> {

	public static WebDriver driver;

	public GlobalMethods() {
		PageFactory.initElements(driver, this);
	}

	static GlobalWait GWait = new GlobalWait(driver);
	Actions action = new Actions(driver);

	public static void LaunchBrowser(String browserName, String Url) {
		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.firefox.driver",
					System.getProperty("user.dir") + "/src/main/resources/win/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/win/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "/src/main/resources/win/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.get(Url);
	}

	public static void PI_Login() throws Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/UploadDocument.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("LoginDetails");

		//------For F103 study user details----// 
//		String UserName_Data = r1.getCell(2, 4).getContents();
//		String Password_Data = r1.getCell(3, 4).getContents();
		
		//------For FN0075 study user details----// 
//		String UserName_Data = r1.getCell(2, 5).getContents();
//		String Password_Data = r1.getCell(3, 5).getContents();
		
		//------For FN0098 study user details----// 
//		String UserName_Data = r1.getCell(2, 6).getContents();
//		String Password_Data = r1.getCell(3, 6).getContents();
		
		//------For Wp7&8 study user details----// 
		String UserName_Data = r1.getCell(2, 7).getContents();
		String Password_Data = r1.getCell(3, 7).getContents();

		GWait.Wait_GetElementById("txtUserName").sendKeys(UserName_Data);
		WebElement sas = GWait.Wait_GetElementById("txtPassword");
//		takeScreenshotElement(sas);
		sas.sendKeys(Password_Data);
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_LoginButton")).click();

	}
	
	public static void alertaccept() throws Exception {

		Alert al = driver.switchTo().alert();
		String msgalert = al.getText();
		al.accept();

	}
	public static void isAlertPresent() throws Exception {

		try {
			driver.switchTo().alert();
			System.out.println(" Alert Present");
			alertaccept();
		} catch (NoAlertPresentException e) {
			System.out.println("No Alert Present");
		}
	}
	
	
	public static void Superadmin_Login() throws Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/UploadDocument.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("LoginDetails");

		String UserName_Data = r1.getCell(2, 3).getContents();
		String Password_Data = r1.getCell(3, 3).getContents();

		GWait.Wait_GetElementById("txtUserName").sendKeys(UserName_Data);
		WebElement sas = GWait.Wait_GetElementById("txtPassword");
		sas.sendKeys(Password_Data);
		screenShots();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_LoginButton")).click();

	}
	
	public static void scrollToBottom() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));");

	}
	
	

	public static void openNewTab() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");
	}

	public static void CloseNewTab() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
		// Switch first tab
		driver.switchTo().defaultContent();
		driver.close();
	}


	public static void UserCreationMailFunctionality() throws Exception {

		openNewTab();
		driver.switchTo().defaultContent();
		String URL = "https://accounts.google.com/signin/v2/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Ftab%3Dwm&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
		driver.get(URL);

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/CTMS.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("UserMNGMT");

		String Emaillink_data = r1.getCell(2, 1).getContents();
		String CTMS_url = "http://ctmsweb.com:8010/#/login";

		WebElement email_field1 = GWait.Wait_GetElementById("identifierId");
		email_field1.sendKeys(Emaillink_data);

		WebElement nextbutton = GWait.Wait_GetElementByXpath("//div[@id='identifierNext']/content/span");
		nextbutton.click();
		WebElement pwd_field1 = GWait.Wait_GetElementByName("password");
		pwd_field1.sendKeys("qa@123456");
		WebElement nextbutton1 = GWait.Wait_GetElementByXpath("//div[2]/div/div/content/span");
		nextbutton1.click();
		Thread.sleep(4500);
		WebElement link1 = GWait.Wait_GetElementByCSS(".asf.T-I-J3.J-J5-Ji");
		link1.click();
		List<WebElement> a = driver.findElements(By.xpath("//span/b[text()='Please login with below details']"));
		System.out.println(a.size());

		if (a.get(0).getText().equalsIgnoreCase("Forgot Password Details")) {
			a.get(0).click();
			WebElement pass1 = GWait.Wait_GetElementByXpath("//div[2]/div[3]/div[3]/div[1]/table/tbody/tr[7]/td");
			System.out.println(pass1.getText());
			String pass2 = pass1.getText();
			String[] passwordSplit = pass2.split(" ");
			System.out.println("1st: " + passwordSplit[0]);
			System.out.println("2nd: " + passwordSplit[1]);
			System.out.println("3rd: " + passwordSplit[2]);

			String finalPass = passwordSplit[2];
			Thread.sleep(2000);
			WebElement emaillogout = GWait.Wait_GetElementByXpath("//div[1]/div/div[5]/div[1]/a/span");
			emaillogout.click();
			WebElement emailsingoutBTN = GWait.Wait_GetElementByXpath("//div/div[5]/div[2]/div[4]/div[2]/a");
			emailsingoutBTN.click();

			Thread.sleep(1000);
			openNewTab();
			driver.switchTo().defaultContent();
			CloseNewTab();
			driver.get(CTMS_url);

			

			// -----Login-----//
			GWait.Wait_GetElementById("txtUserName", 120).sendKeys("");
			GWait.Wait_GetElementById("txtPassword", 120).sendKeys(finalPass);
			GWait.Wait_GetElementById("LoginButton", 120).click();

			// ----Reset Password----//
			Sheet r = wb.getSheet("LoginDetails");

			String SetNewPassword = r.getCell(4, 3).getContents();

			GWait.Wait_GetElementById("ChangePassword1_CurrentPassword", 120).sendKeys(finalPass);
			GWait.Wait_GetElementById("ChangePassword1_NewPassword", 120).sendKeys(SetNewPassword);
			GWait.Wait_GetElementById("ChangePassword1_ConfirmNewPassword", 120).sendKeys(SetNewPassword);
			GWait.Wait_GetElementById("ChangePassword1_ChangePasswordPushButton", 120).click();
			driver.switchTo().defaultContent();
		}

	}
	
	
	public static void screenShot() throws IOException, InterruptedException {
	    File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    String filename =  new SimpleDateFormat("yyyyMMddhhmmss'.jpg'").format(new Date());
	    File dest = new File("StudyScreenShots/" + filename);
	    FileUtils.copyFile(scr, dest);
	}
	
	public static void takeScreenshotElement(WebElement element) throws IOException {
	    WrapsDriver wrapsDriver = (WrapsDriver) element;
	    File screenshot = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
	    Rectangle rectangle = new Rectangle(element.getSize().width, element.getSize().height);
	    Point location = element.getLocation();
	    BufferedImage bufferedImage = ImageIO.read(screenshot);
	    BufferedImage destImage = bufferedImage.getSubimage(location.x, location.y, rectangle.width, rectangle.height);
	    ImageIO.write(destImage, "png", screenshot);
	    File file = new File("filePath/to");
	    FileUtils.copyFile(screenshot, file);
	}
	
	 public static void screenShots() throws IOException, InterruptedException, Exception {
		 Robot robot = new Robot();
		 String filename =  new SimpleDateFormat("yyyyMMddhhmmss'.jpg'").format(new Date());
		    BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		    ImageIO.write(screenShot, "JPG", new File("StudyScreenShots/" + filename));
		    
	       /* File scr=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        File dest= new File("filePath/screenshot_"+timestamp()+".png");
	        FileUtils.copyFile(scr, dest);*/
	        Thread.sleep(3000);
	    }
	 public static String timestamp() {
	        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	    }

}
