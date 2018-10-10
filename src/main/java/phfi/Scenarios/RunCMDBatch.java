package phfi.Scenarios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.win32.StdCallLibrary;

import phfi.GlobalMethods.GlobalMethods;
import phfi.GlobalMethods.GlobalWait;
import phfi.Scenarios.Studydatapreview;

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

public class RunCMDBatch extends UploadExcelFile {
	
		public RunCMDBatch() {
			PageFactory.initElements(GlobalMethods.driver, this);
		}

		GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
		Actions action = new Actions(GlobalMethods.driver);

		//---Data Migration Study Data Import---//
		public void RunBatch_DataMigrationSDI() throws Exception {
			GlobalMethods.screenShots();
//			UploadExcelData();
			Studydatapreview sdata = new Studydatapreview();
			sdata.StudyDataPreviewM();
			

		}	

}
