package AndroidUtils;

import org.openqa.selenium.JavascriptExecutor;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions {
	
	AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver) {
		this.driver = driver;
	}
	
	public void hideKeyboard() {
		driver.hideKeyboard();	
	}
	
	public void navigateBack() {
		driver.navigate().back();
	}
	
	public void scrollDown(String elementNƒame) {
		driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector()).scrollable(true).instance(0).scrollIntoView(new UiSelector()"+".textMatches(\""+elementNƒame+"\").instance(0)")));
		}

	
	public void scrollUp() {
//		driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ elementName +"\"))")));

		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "up", "percent", 3.0));
		} while (canScrollMore);

	}

	public void closedApp() {
		((AppiumDriver) driver).close();
	}

	public void scrollAndView(String visibleText) {
		driver.findElement(AppiumBy.androidUIAutomator(
				("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ visibleText + "\").instance(0))")));
	}
	
	
}
