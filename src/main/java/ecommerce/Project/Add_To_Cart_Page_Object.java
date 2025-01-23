package ecommerce.Project;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import AndroidUtils.AndroidActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Add_To_Cart_Page_Object extends AndroidActions{

	public AppiumDriver driver;

	public Add_To_Cart_Page_Object(AndroidDriver driver) {
		
		super(driver);
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility = "View menu")
	public WebElement viewMenu;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Catalog\")")
	public WebElement catalogMenu;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/productIV\").instance(0)")
	public WebElement product;
	
	@AndroidFindBy(accessibility = "Tap to add product to cart")
	public WebElement addTocartButton;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartIV")
	public WebElement addTocart;
	
	@AndroidFindBy(accessibility = "Increase item quantity")
	public WebElement increaseItemQuantity;
	
	@AndroidFindBy(accessibility = "Decrease item quantity")
	public WebElement decreaseItemQuantity;	
	
	@AndroidFindBy(accessibility = "Removes product from cart")
	public WebElement removesProductFromCart;	
	
	@AndroidFindBy(accessibility = "Confirms products for checkout")
	public WebElement confirmsProductsForCheckout;
	
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/fullNameET")
	public WebElement fullName;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
	public WebElement cardName;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cardNumberET")
	public WebElement cardNumber;

	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/expirationDateET")
	public WebElement expiryDate;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/securityCodeET")
	public WebElement securityCode;
	
	@AndroidFindBy(accessibility = "Saves payment info and launches screen to review checkout data")
	public WebElement reviewOrder;
	
	@AndroidFindBy(accessibility = "Completes the process of checkout")
	public WebElement placeOrder;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/completeTV")
	public WebElement orderPlaced;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/address1ET")
	public WebElement address1;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/address2ET")
	public WebElement address2;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cityET")
	public WebElement city;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/stateET")
	public WebElement state;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/zipET")
	public WebElement zip;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/countryET")
	public WebElement country;
	
	@AndroidFindBy(accessibility = "Saves user info for checkout")
	public WebElement checkout;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productTV")
	public WebElement myCart;
	
	@AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/noItemTitleTV")
	public WebElement verifyNoItemInCart;
	
	
	public void Withlogin() {
		product.click();
		increaseItemQuantity.click();
		increaseItemQuantity.click();
		addTocartButton.click();
		addTocart.click();
	}
	
	public void AddToCartWithoutlogin() {
		viewMenu.click();
		catalogMenu.click();
		product.click();
		increaseItemQuantity.click();
		increaseItemQuantity.click();
		addTocartButton.click();
		addTocart.click();
	}
	
	public void RemoveProductFromCart() {
		product.click();
		increaseItemQuantity.click();
		increaseItemQuantity.click();
		addTocartButton.click();
		addTocart.click();
		removesProductFromCart.click();
		
	}
	
	public void Checkout(String name, String add1, String add2, String cityName, String stateName, String zipCode, String countryName) {
		product.click();
		addTocartButton.click();
		addTocart.click();
		confirmsProductsForCheckout.click();
		fullName.click();
		fullName.sendKeys(name);
		address1.click();
		address1.sendKeys(add1);
		address2.click();
		address2.sendKeys(add2);
		super.hideKeyboard();
		city.click();
		city.sendKeys(cityName);
		super.hideKeyboard();
		state.click();
		state.sendKeys(stateName);
		super.hideKeyboard();
		zip.click();
		zip.sendKeys(zipCode);
		country.click();
		country.sendKeys(countryName);
		super.hideKeyboard();
		checkout.click();
	}
	
	public void AddCardDetails(String name, String cardNum, String expiry, String security) {
		cardName.click();
		cardName.sendKeys(name);
		cardNumber.click();
		cardNumber.sendKeys(cardNum);
		super.hideKeyboard();
		expiryDate.click();
		expiryDate.sendKeys(expiry);
		securityCode.click();
		securityCode.sendKeys(security);
		super.hideKeyboard();
		reviewOrder.click();
		placeOrder.click();
		
	}
	
	public boolean verifyMyCartPage() {
		boolean flag = true;
		if(!myCart.isDisplayed()) {
			flag = false;
		}
		else {
			flag = true;
		}
		return flag;
	}
	
	public boolean verifyNoItem() {
		boolean flag = true;
		if(!verifyNoItemInCart.isDisplayed()) {
			flag = false;
		}
		else {
			flag = true;
		}
		return flag;
	}
	
	public boolean orderPage() {
		boolean flag = true;
		if(!orderPlaced.isDisplayed()) {
			flag = false;
		}
		else {
			flag = true;
		}
		return flag;
	}
	
}
