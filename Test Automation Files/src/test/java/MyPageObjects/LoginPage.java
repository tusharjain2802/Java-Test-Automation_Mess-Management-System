package MyPageObjects;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
public class LoginPage {
	WebDriver ldriver;
	public LoginPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	@FindBy(linkText="Login")
	WebElement login;
	
	@FindBy(linkText="Review Us!")
	WebElement ReviewMenuButton;
	
	@FindBy(name="username")
	WebElement username;

	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="/html/body/div[1]/div/form/div[2]/div[1]/div[5]/button")
	WebElement btnLogin;
	
	@FindBy(xpath="/html/body/div/div[2]/form/label[4]")
	WebElement star2;
	
	@FindBy(xpath="/html/body/div/div[2]/form/label[2]")
	WebElement star4;
	
	@FindBy(name="meal")
	WebElement meal;
	
	@FindBy(name="postBody")
	WebElement feedb;
	
	@FindBy(xpath="/html/body/div/div[2]/form/div/div[3]/button")
	WebElement submitFeedback;
	
	@FindBy(linkText="Mess Menu")
	WebElement MessMenu;
	
	@FindBy(id="draggable")
	WebElement source;
	
	@FindBy(id="droppable")
	WebElement target;
	
	public void clickLogin() {
		ldriver.manage().window().maximize();
		ldriver.manage().deleteAllCookies();
		login.click();
	}
	public void setUsername(String uname) {
		username.sendKeys(uname);
	}
	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}
	public void clickSubmit() {
		btnLogin.click();
	}
	public void giveReview(String feedback) throws InterruptedException {
		Thread.sleep(2000);
		ReviewMenuButton.click();
		Actions action=new Actions(ldriver);
		action.moveToElement(star2).build().perform();
		Thread.sleep(1000);
		star4.click();
		WebElement selDropDown = meal;
		Select selAccount = new Select(selDropDown);
		selAccount.selectByVisibleText("Lunch");
		feedb.sendKeys(feedback);
		Thread.sleep(2000);
		submitFeedback.click();
		Thread.sleep(2000);
	}
	public void dragNdrop() throws InterruptedException {
		MessMenu.click();
		Thread.sleep(2000);
		String parentwindow=ldriver.getWindowHandle();
		JavascriptExecutor js = (JavascriptExecutor) ldriver;
		js.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(2000);
	
		Actions action=new Actions(ldriver);
		action.dragAndDrop(source, target).build().perform();
		Thread.sleep(3000);
		
		Alert alert=ldriver.switchTo().alert();
//		System.out.println(alert.getText());
		alert.accept();
		Thread.sleep(3000);
		
		String childwindow=ldriver.getWindowHandles().toArray()[1].toString();
//	    System.out.println("the child window handle"+childwindow);
	    ldriver.switchTo().window(childwindow);
	    ldriver.close();
	    ldriver.switchTo().window(parentwindow);
	    Thread.sleep(3000);
	    
	    ldriver.switchTo().frame("iframeResult");
	    js.executeScript("window.scrollBy(0,100)", "");
	    ldriver.findElement(By.id("clock_twelve_hour_format")).click();
	    ldriver.switchTo().parentFrame();
	    Thread.sleep(3000);
	}
}