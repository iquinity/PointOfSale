package desktop;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;

public class ScreenLockCassa {
	LogInClass login = new LogInClass();

	@Test(description = "Check if lockScreen(Ekraanilukk) function is working")
	public void main() throws InterruptedException {
		login.getDriver().findElement(By.linkText("Kassa")).click();
		login.getDriver().findElement(By.id("lockScreen")).click();
		AssertJUnit.assertEquals(login.getDriver().getCurrentUrl(),
				"https://example.com");
	}
	
	@Parameters("browserType")

	@BeforeMethod
	public void beforeMethod(String browserType) {
		if(browserType.equalsIgnoreCase("chrome")) {
		login.loginMethod("chrome");
		}

	}

	@AfterMethod
	public void afterMethod() {
		login.getDriver().quit();
	}

}
