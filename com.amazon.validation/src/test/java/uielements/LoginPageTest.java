package uielements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPageTest extends ReusableAppiumTest{
	AndroidDriver<WebElement> driver1;
	
	public static void logintest(){
		
			
			try{
				driver.get(prop.getProperty("URL"));
				System.out.println("Page Title is : " + driver.getTitle());
				Assert.assertEquals(prop, prop.getProperty("SampleURL"));
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.elementToBeClickable(By.id("loginid")));
				driver.findElement(By.id("loginid")).sendKeys(prop.getProperty("loginid"));
				driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
				driver.findElement(By.id("sign_in_btn")).click();
				ReusableAppiumTest.attachScreen(driver);
				ReusableAppiumTest.logStep("======Test Case Execution Started for Amazon shopping site======");
			}
			catch(Exception e){
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		
	}
	
	//Method for Dash board login options & adding product to cart
	
		 
		  @AndroidFindBy(id = "com.amazon.mobile:id/search_box")
		    public static WebElement SearchBox;

		    @AndroidFindBy(id="com.amazon.mobile:id/search_src_text")
		    public static WebElement SearchInText;

		    @AndroidFindBy(id="com.amazon.mobile:id/button_add_to_cart")
		    public static WebElement AddToCartButton;

		    @AndroidFindBy(id="com.amazon.mobile:id/fragmentContainer")
		    public static MobileElement ProductContainer;

		    @AndroidFindBy(id="com.amazon.mobile:id/top_scrollview_content")
		    public static MobileElement ScrollinProductPage;

		    String str="ADD TO CART";
		    String Item="65-Inch TV";
		    
		    public static void SearchBox()
		    {
		    	logStep("Click button or tap on screen");
		        try {
		            Thread.sleep(3000);
		            SearchBox.click();
		            Thread.sleep(4000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		        logStep("Search button clicked");
		    }

		    public static void SearchItemFromSearchBox()
		    {
		    	logStep("In search bar searching the Item for purchase");
		        SearchInText.sendKeys(prop.getProperty("SearchItemForPurchase"));
		        try {
		            Thread.sleep(5000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		        driver.pressKeyCode(AndroidKeyCode.KEYCODE_PAGE_DOWN);
		        driver.pressKeyCode(AndroidKeyCode.ENTER);
		    }

		    public static void SelectProduct()
		    {
		    	logStep("In select product");
		        try {
		            Thread.sleep(2000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		        WebElement ProductFromList = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox"));
		        //ScreenShotUtil.capscreen(driver);
		        ProductFromList.click();
		        logStep("In product buy page");
		        WebElement AddToCartID= driver.findElement(By.xpath("//*[@id=\"searchproduct"));
		        AddToCartID.click();
		        logStep("Product Added to cart succesfully");
		       

		    }
	
	//Method for Logout Button functionality of Amazon Application
		public static void logout() throws Exception {
			WebElement hamburgermenu=driver.findElement(By.xpath("//button[@id='nav-toggle']"));
			waitTillElementLocated(hamburgermenu);
			hamburgermenu.click();
			
			WebElement logout1=driver.findElement(By.xpath("//a[@id='mob-nav-logout' or text()='Logout']"));
			waitTillElementLocated(logout1);
			clickByJS(logout1);
			ReusableAppiumTest.attachScreen(driver);
			ReusableAppiumTest.logStep("==========MPower Application Logged Out Successfully=========");
			//System.out.println(logout1);
			
		}
	
	}

