package uielements;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

public class ReusableAppiumTest {
	
	//Function for Print the steps in allure report
			@Step("{0}")
			 public static void logStep(String stepName ){
			
			 }
			
			//Function for take the screen shot in allure report
			@Attachment("Screenshot")
		    public static byte[] attachScreen(WebDriver driver ) {
		        logStep("Taking screenshot");
		        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		    }
			
			@Attachment("Error_Screenshot")
		    public static byte[] attachScreen_TestCaseError(WebDriver driver ) {
		        logStep("Taking screenshot");
		        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		    }
			
			//Method for App to load
			public void WaitAppToLoad()
		    {
		        try {
		            driver.wait(20);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
			
			//To set the Orientation & resolution of the device attached
			
			 public void GetCurrentRotation()
			    {
				 driver.rotate(ScreenOrientation.LANDSCAPE);
				 //assertEquals(ScreenOrientation.LANDSCAPE, driver.getOrientation());
				logStep("Current rotation of screen is ="+driver.getOrientation());

			    }
			 
			 public void SetRotationLandscape()
			    {
				 logStep("Current rotation set to PORTRAIT=");
			        driver.rotate(ScreenOrientation.LANDSCAPE);
			    }
			 
			 public void GetScreenResolution()
			 {
				 JavascriptExecutor js = (JavascriptExecutor) driver;
				 int width = ((Long) js.executeScript("return window.innerWidth || document.body.clientWidth")).intValue();
				 logStep("width value calculated is :" + width);
				 int height = ((Long) js.executeScript("return window.innerHeight || document.body.clientHeight")).intValue();
				 logStep("height value calculated is :" + height);
				 //Dimension dimension  = new Dimension(width, height);

			 }
			 
			 //Appium other API's reusable operations
			 
			 public void clickBackButton() {
			        driver.navigate().back(); //Closes keyboard
			        //driver.navigate().back(); //Comes out of edit mode
			    }
			 
			 public boolean clickButton(MobileElement element){
				 logStep("Click button or tap on screen");
			        new TouchAction(driver).tap(element);
			        return true;
			    }

			    public String getCurrentMethodName() {
			        return Thread.currentThread().getStackTrace()[2].getMethodName();
			    }

			    public void swipeRight() {
			        Dimension size = driver.manage().window().getSize();
			        int startx = (int) (size.width * 0.9);
			        int endx = (int) (size.width * 0.20);
			        int starty = size.height / 2;

			        new TouchAction(driver).press(startx, starty)
			                .waitAction(3000)
			                .moveTo(endx,starty).release().perform();
			    }

			    public void swipeLeft() {
			        Dimension size = driver.manage().window().getSize();
			        int startx = (int) (size.width * 0.8);
			        int endx = (int) (size.width * 0.20);
			        int starty = size.height / 2;
			        new TouchAction(driver).press(startx, starty)
			                .waitAction((3000))
			                .moveTo(endx,starty).release();
			    }

			    public boolean swipeDown() {
			        Dimension size = driver.manage().window().getSize();
			        int startx = (int) (size.width * 0.7);
			        int endx = (int) (size.width * 0.20);
			        int starty = size.width / 2;
			        new TouchAction(driver).press(startx, starty)
			                .waitAction(3000)
			                .moveTo(endx,starty).release().perform();
			        return true;

			    }
			    
			    public boolean swipeUp() {
			        Dimension size = driver.manage().window().getSize();
			        int startx = (int) (size.width * 0.7);
			        int endx = (int) (size.width * 0.20);
			        int starty = size.width / 2;
			        new TouchAction(driver).press(startx, starty)
			                .waitAction(3000)
			                .moveTo(endx,starty).release().perform();
			        return true;
			    }

	
	public static AndroidDriver<WebElement> driver=null;
	
	public static File file = null;
	
	public static Properties prop;
	
	public static Properties readProperties()
	{
		file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Input.properties");
		FileInputStream fileInput = null;

		try {
			fileInput = new FileInputStream(file);
			prop = new Properties();
			prop.load(fileInput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e1) {
			e1.printStackTrace();
		}
	
	return prop;
	}
	
	//Method for Click functionality
	public static void click(WebElement locator) throws Exception {
 		Thread.sleep(1000);

 		for (int i = 0; i <= 40; i++) {
 			try {
 				locator.click();
 				break;

 			} catch (Exception e) {
 				if (i == 40) {
 					throw e;

 				} else {
 					Thread.sleep(1000);
 				}
 			}
 		}
 	}
     
	//Method for element visbility functionality
     public static void waitForVisible(WebElement locator) throws Exception {
 		Thread.sleep(1000);
 		for (int i = 0; i <= 40; i++) {
 			try {
 				locator.isDisplayed();
 				break;

 			} catch (Exception e) {
 				if (i == 40) {
 					throw e;

 				} else {
 					Thread.sleep(1000);
 				}
 			}
 		}
 	}
     
 	
  	//Wait Method
  	public static WebElement waitTillElementLocated(WebElement element) {
  		WebDriverWait wait = new WebDriverWait(driver, 40);
  		WebElement elementloaded = wait.until(ExpectedConditions.visibilityOf(element));
  		return elementloaded;
  	}
  	
  	// Javascript Click
  	public static void clickByJS(WebElement el) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", el);

		} catch (Exception e) {
			e.getMessage();
		}
	}
  	
	public static void jsClick() throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,250)", "");

		} catch (Exception e) {
			e.getMessage();
			throw e;
		}

	}

	public static void jsClickup() throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-250)", "");

		} catch (Exception e) {
			e.getMessage();
			throw e;
		}

	}
  	
  	//Method for Input Data
  	public static void type(WebElement textbox, String inputdata) throws Exception {
		Thread.sleep(1000);
		for (int i = 0; i <= 15; i++) {
			try {
				textbox.clear();
				textbox.sendKeys(inputdata);
				break;

			} catch (Exception e) {
				if (i == 15) {
					throw e;

				} else {
					Thread.sleep(1000);
				}
			}
		}
	}
  	
  	public static String readingdata(int sheetno, int rownum, int colnum) throws Exception {
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Maxlife_Testdata.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		XSSFWorkbook hssfWorkbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = hssfWorkbook.getSheetAt(sheetno);
		XSSFCell cell = sheet.getRow(rownum).getCell(colnum);
		DataFormatter df = new DataFormatter();
		String data = df.formatCellValue(cell);
        return data;

	}
}
