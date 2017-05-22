package login;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;

public class RememberUser {

	LogInClass login = new LogInClass();

	@Test
	public void main() throws InterruptedException {

		Set<Cookie> cookies = login.getDriver().manage().getCookies();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dropdown-toggle"))).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout"))).click();

		for (Cookie getCookie : cookies) {
			login.getDriver().manage().addCookie(getCookie);
		}

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary"))).click();
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
