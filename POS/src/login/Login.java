package login;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;

public class Login {

	LogInClass login = new LogInClass();

	@Test(description = "Check if login is working(chrome) + test with firefox browser")
	public void main() throws InterruptedException {

		if (login.getBrowserName().equals("chrome")) {
			login.wait.until(ExpectedConditions.elementToBeClickable(By.className("dropdown-toggle"))).click();
			login.wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout"))).click();
			login.wait.until(ExpectedConditions.urlToBe("https://example.com"));
		}

		if (login.getBrowserName().equals("firefox")) {
			Thread.sleep(5000);
		}
	}

	@Parameters("browserType")

	@BeforeMethod
	public void beforeMethod(String browserType) {
		if (browserType.equalsIgnoreCase("chrome")) {
			login.loginMethod("chrome");
		}
		if (browserType.equalsIgnoreCase("firefox")) {
			login.loginMethod("firefox");
		}
	}

	@AfterMethod
	public void afterMethod() {
		login.getDriver().quit();
	}

}
