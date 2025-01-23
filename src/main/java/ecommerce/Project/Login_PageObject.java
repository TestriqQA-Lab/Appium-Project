package ecommerce.Project;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import AndroidUtils.AndroidActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Login_PageObject extends AndroidActions{

	public AppiumDriver driver;

	public Login_PageObject(AndroidDriver driver) {
		
		super(driver);
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility = "View menu")
	public WebElement mainMenu;
	
	@AndroidFindBy(accessibility = "Login Menu Item")
	public WebElement logIn;
	
	@AndroidFindBy(accessibility = "title")
	public WebElement verifyTitle;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/loginTV")
	public WebElement logOutTitle;

	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
	public WebElement emailID;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/passwordET")
	public WebElement password;
	
	@AndroidFindBy(accessibility = "Tap to login with given credentials")
	public WebElement loginButton;
	
	@AndroidFindBy(accessibility = "Logout Menu Item")
	public WebElement logoutButton;
	
	@AndroidFindBy(id = "android:id/button1")
	public WebElement logoutPoup;
	
	
	public void login(String email, String pass) {
		mainMenu.click();
		logIn.click();
		emailID.click();
		emailID.sendKeys(email);
		password.click();
		password.sendKeys(pass);
		super.hideKeyboard();
		loginButton.click();
	}
	
	public void logOut() {
	mainMenu.click();
	logoutButton.click();
	logoutPoup.click();
	}
	
	
	public boolean verifyLoginSuccess() {
		boolean flag = true;
		if(!verifyTitle.isDisplayed()) {
			flag = false;
		}
		else {
			flag = true;
		}
		return flag;
	}
	
	public boolean verifyLogOutSuccess() {
		boolean flag = true;
		if(!logOutTitle.isDisplayed()) {
			flag = false;
		}
		else {
			flag = true;
		}
		return flag;
	}

}
