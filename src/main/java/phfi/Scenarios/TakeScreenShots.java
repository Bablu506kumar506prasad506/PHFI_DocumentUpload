package phfi.Scenarios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.win32.StdCallLibrary;

import phfi.GlobalMethods.GlobalMethods;
import phfi.GlobalMethods.GlobalWait;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TakeScreenShots {

	public TakeScreenShots() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public void takescreenshot() throws Exception {
		GlobalMethods.screenShots();

		GlobalMethods.PI_Login();
		Thread.sleep(3000);

		/*
		 * SYSTEMTIME st = new SYSTEMTIME(); st.wYear = 2009; // must be short
		 * st.wMonth = 1; st.wDay = 1; st.wHour = 0; st.wMinute = 0; st.wSecond
		 * = 0; SetLocalTime(st);
		 */

		// System.out.println("START SYNC " + windowsSetSystemTime);
		/*
		 * WindowsSetSystemTime.SetLocalTime((short)2017, (short)10,(short)
		 * 29,(short) 11,(short) 35); GlobalMethods.screenShots();
		 * GlobalMethods.screenShots();
		 */
		
		 try {
	            String value = "2014-12-12 00:26:14";
	            Date date = dateFormat.parse(value);
	            value = dateFormat.format(date);
	            final Process dateProcess = Runtime.getRuntime().exec("cmd /c date "+value.substring(0, value.lastIndexOf(' ')));
	            dateProcess.waitFor();
	            dateProcess.exitValue();
	            final Process timeProcess = Runtime.getRuntime().exec("cmd /c time "+value.substring(value.lastIndexOf(' ')+1));
	            timeProcess.waitFor();
	            timeProcess.exitValue();
	        } catch (Exception exception) {
	            throw new RuntimeException(exception);
	        }

	}

	/*
	 * public interface Kernel32 extends StdCallLibrary {
	 * 
	 * boolean SetLocalTime(SYSTEMTIME st);
	 * 
	 * Kernel32 instance = (Kernel32) Native.loadLibrary("kernel32.dll",
	 * Kernel32.class);
	 * 
	 * } public boolean SetLocalTime(SYSTEMTIME st) { return
	 * Kernel32.instance.SetLocalTime(st); }
	 */

}
