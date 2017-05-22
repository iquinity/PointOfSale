package salesPlaces;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;

public class AddTable {
	LogInClass login = new LogInClass();

	@Test(description = "Add an additional table to the system and check if it is working. Then delete all the test data")
	public void main() throws InterruptedException {

		login.getDriver().findElement(By.linkText("Kassa")).click();
		int countTables = login.getDriver().findElements(By.cssSelector("[data-orderid='new']")).size() - 1;
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar-header"))).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Müügikohad"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-pencil"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addSubOutlet"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys("AutomationLaud");

		login.wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='tableAddModal']/div/div/form/div[2]/span/button")))
				.click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Kassa"))).click();

		AssertJUnit.assertEquals(countTables + 1,
				login.getDriver().findElements(By.cssSelector("[data-orderid='new']")).size() - 1);
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar-header"))).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Müügikohad"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-pencil"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-name='AutomationLaud']")))
				.click();

		Thread.sleep(1000);
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("delete"))).click();
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
