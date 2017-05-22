package people;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;

public class PrivatePerson {

	LogInClass login = new LogInClass();

	@Test(description = "Check if adding an employee works and then delete all the test data")
	public void main() throws InterruptedException {

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar-header"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Isikud"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Eraisikud"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")))
				.sendKeys("AutomationTest");
		login.getDriver().findElement(By.name("surname")).sendKeys("AutomationTest2");
		login.getDriver().findElement(By.name("personalId")).sendKeys("AutomationTest3");
		login.getDriver().findElement(By.name("email")).sendKeys("AutomationTest4");
		login.getDriver().findElement(By.name("phone")).sendKeys("AutomationTest5");
		login.getDriver().findElement(By.name("address")).sendKeys("AutomationTest6");

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Salvesta')]")))
				.click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-long-arrow-left"))).click();

		login.wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("*[data-personname='AutomationTest AutomationTest2']")))
				.click();

		Thread.sleep(1000);
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("save"))).click();

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
