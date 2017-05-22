package profile;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;

public class ScreenLock {

	LogInClass login = new LogInClass();

	@Test(description = "Check if lockScreen(Ekraanilukk) in the upper menu works")
	public void main() throws InterruptedException {

		login.getDriver().findElement(By.className("dropdown-toggle")).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Ekraanilukk"))).click();
		AssertJUnit.assertEquals(login.getDriver().getCurrentUrl(),
				"https://example.com");

	}

	@Parameters("browserType")

	@BeforeMethod
	public void beforeMethod(String browserType) {
		if (browserType.equalsIgnoreCase("chrome")) {
			login.loginMethod("chrome");
		}
	}

	@AfterMethod
	public void afterMethod() {
		login.getDriver().quit();
	}

}
