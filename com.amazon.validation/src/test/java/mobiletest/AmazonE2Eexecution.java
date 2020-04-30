package mobiletest;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobilePlatform;
import org.testng.annotations.Test;
import uielements.LoginPageTest;
import uielements.ReusableAppiumTest;
import io.appium.java_client.android.AndroidDriver;
/*import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobilePlatform;*/

public class AmazonE2Eexecution extends ReusableAppiumTest {
	
	
@Parameters({"device","os_version"})
	
//Setting desired capabilities to connect real android device from property file

	@BeforeClass
	public void beforeTest(String device, String osVersion) throws InterruptedException
	{
	logStep("Setting Android Driver");
	
		DesiredCapabilities caps=new DesiredCapabilities();
		caps.setCapability("deviceName", prop.getProperty("Device_name"));
		caps.setCapability("realMobile", prop.getProperty("Boolean"));
		caps.setCapability("platformVersion", prop.getProperty("platformVersion"));
		caps.setCapability("appPackage", prop.getProperty("appPackage"));
		caps.setCapability("appActivity", prop.getProperty("appActivity"));
		
		//Default Appium port set to run on 4723
		
		driver = new AndroidDriver(new URL("https://127.0.0.1:4723/wd/hub"),caps); 
		caps.wait();
		 driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		 WaitAppToLoad();
		 logStep("Checking Screen resolution & Screen rotation");
		 
		 //Implementation of rotation & resolution is in ReusableAppiumTest class
		 
		 GetCurrentRotation();
		 SetRotationLandscape();
		 GetScreenResolution();
	}
		 
		 @Test
		 
		   public void mainTest() throws Exception{
			LoginPageTest.logintest();
			logStep("Logged in succesfully to Amazon e-mobile application");
			LoginPageTest.SearchBox();
			logStep("Navigating to Serach bar");
			LoginPageTest.SearchItemFromSearchBox();
			logStep("Searching for 65 Inch TV product");
			LoginPageTest.SelectProduct();
			logStep("Selecting 65inch TV excluding 1st & last from thr list set through xpath");
			/*LoginPageTest.AddProductToCart();
			logStep("");*/
			LoginPageTest.logout();
			logStep("Logging out from Amazon");
			
		 }
		 
		 
		 //Xpaths for Search box: //*[@id="twotabsearchtextbox"]


		 @AfterClass
		    public void tearDown()
		    {
		        driver.quit();
		    }
		 
}

