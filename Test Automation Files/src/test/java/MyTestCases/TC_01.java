package MyTestCases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
	
import org.testng.annotations.Test;
import MyPageObjects.LoginPage;
public class TC_01 extends BaseClass {
	@Test
	public void logintest() throws InterruptedException{
		Logger logger;
		logger=LogManager.getLogger(TC_01.class);
		driver.get(baseURL);
		LoginPage lp=new LoginPage(driver);
		logger.info("Login page opened");
		lp.clickLogin();
		lp.setUsername(username);
		logger.info("Username set");
		lp.setPassword(password);
		logger.info("password set");
		lp.clickSubmit();
		
	}
}

