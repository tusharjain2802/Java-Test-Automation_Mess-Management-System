package ExtentReportBasicDemo;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class DemoExtentReport {
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("ExtentTest.html");
	WebDriver driver;
	
	@BeforeTest
	public void BrowserLaunch() {
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("My Report");
		extent.attachReporter(spark);
		System.setProperty("webdriver.chrome.driver", "D:\\Tushar\\Documents\\eclipse-workspace\\Project2\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:3000/");			
	}
	
	@AfterTest
	public void tearDown() {
		extent.flush();
		driver.quit();
	}
	@Test
	public void TestCase_001() {
		ExtentTest test = extent.createTest("Verify the page title").assignAuthor("Tushar Jain");
		test.info("I am verifying the page title");
		String title = driver.getTitle().toString();
		test.info("Captures page title= "+title);
		if(title.equals("Mess Management System")) {
			test.pass("The page title is verified successfully");
		}
		else {
			test.fail("Page title cannot be verified successfully");
		}
	}
	@Test
	public void TestCase_002() {
		ExtentTest test = extent.createTest("Verify logging In").assignAuthor("Tushar Jain");
		try {
			driver.findElement(By.linkText("Login")).click();
			WebElement userId;
			userId = driver.findElement(By.name("username"));
			userId.sendKeys("tjain2_be20@thapar.edu");
			test.info("I am entering the username");
			driver.findElement(By.name("password")).sendKeys("hello");
			test.info("I am entering the password");
			driver.findElement(By.xpath("/html/body/div[1]/div/form/div[2]/div[1]/div[5]/button")).click();
			test.pass("User has successfully logged in with the credentials.");
			test.addScreenCaptureFromPath(capturescreenshot(driver));	
		}
		catch(Exception e) {
			test.fail("Was not able to LogIn" +e.getMessage());
			test.addScreenCaptureFromPath(capturescreenshot(driver));			
		}
	}
	
	@Test
	public void TestCase_003() {
		ExtentTest test = extent.createTest("Verify submitting the review").assignAuthor("Tushar Jain");
		try {
			driver.findElement(By.linkText("Review Us!")).click();
			WebElement all=driver.findElement(By.xpath("/html/body/div/div[2]/form/label[4]"));
			Actions action=new Actions(driver);
			action.moveToElement(all).build().perform();
			test.info("Successfully hovered on the 2 star rating");
			driver.findElement(By.xpath("/html/body/div/div[2]/form/label[2]")).click();
			test.info("Selected 3 star rating");
			WebElement selDropDown = driver.findElement(By.name("meal"));
			Select selAccount = new Select(selDropDown);
			selAccount.selectByVisibleText("Dinner");
			test.info("I chose dinner from the drop down succesfully");
			driver.findElement(By.name("postBody")).sendKeys("The Dinner was good");
			driver.findElement(By.xpath("/html/body/div/div[2]/form/div/div[3]/button")).click();
			test.pass("User has successfully submitted the review.");
			test.addScreenCaptureFromPath(capturescreenshot(driver));	
		}
		catch(Exception e) {
			test.fail("Was not able to submit the review" +e.getMessage());
			test.addScreenCaptureFromPath(capturescreenshot(driver));			
		}
	}
	
	@Test
	public void TestCase_004() {
		ExtentTest test = extent.createTest("Verify downloading the Menu image").assignAuthor("Tushar Jain");
		try {
			driver.findElement(By.linkText("Mess Menu")).click();
			test.info("Selected Mess Menu from the nav bar.");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,300)", "");
			driver.switchTo().frame("iframeResult");
			test.info("Switched to iframe window");
		    js.executeScript("window.scrollBy(0,100)", "");
		    driver.findElement(By.id("clock_twelve_hour_format")).click();
		    test.info("Clicked a button within iframe");
		    driver.switchTo().parentFrame();
			test.pass("User has successfully downloaded the mess menu Image");
			test.addScreenCaptureFromPath(capturescreenshot(driver));	
		}
		catch(Exception e) {
			test.fail("Was not able to download the image" +e.getMessage());
			test.addScreenCaptureFromPath(capturescreenshot(driver));			
		}
	}
	

	public static String capturescreenshot(WebDriver driver) {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destinationFilePath = new File("screenshots/"+System.currentTimeMillis()+".png");
		String absolutePathLocation = destinationFilePath.getAbsolutePath();
		try {
			FileUtils.copyFile(srcFile, destinationFilePath);
		}catch(IOException e){
			e.printStackTrace();
		}
		return absolutePathLocation;
	}
}
