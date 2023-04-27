package MyTestCases;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import MyUtilities.ReadConfig;

public class BaseClass {
	ReadConfig readconfig=new ReadConfig();
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUserName();
	public String password=readconfig.getPassword();
	public String feedback="The Lunch was good";
	public static WebDriver driver;
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\Tushar\\Documents\\eclipse-workspace\\Project2\\drivers\\chromedriver.exe");
		driver=new ChromeDriver();
	}
	@AfterClass
	public void teardown() {
		driver.quit();
	}
}