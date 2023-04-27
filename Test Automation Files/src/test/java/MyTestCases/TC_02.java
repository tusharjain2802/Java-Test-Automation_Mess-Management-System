package MyTestCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import MyPageObjects.LoginPage;

public class TC_02 extends TC_01{
	public String feedback="The Lunch was good";
	@Test
	public void reviewtest() throws InterruptedException {
	//WebDriver driver=new ChromeDriver();
		Logger logger;
		logger=LogManager.getLogger(TC_02.class);
		
	LoginPage lp=new LoginPage(driver);
	logger.info("opened page");
	lp.giveReview(feedback);
	logger.info("review given");
	lp.dragNdrop();
	logger.info("dragdrop");
	}
}
