package goods;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;

public class AddProduct {
	LogInClass login = new LogInClass();

	@Test(description = "Check if adding a product works and then clear all the test data")
	public void main() throws InterruptedException {

		login.getDriver().findElement(By.linkText("Tooted")).click();
		login.getDriver().findElement(By.id("create")).click();

		login.getDriver().findElement(By.name("name")).sendKeys("AutomationName");
		login.getDriver().findElement(By.name("display_name")).sendKeys("AutomationDisplayName");
		login.getDriver().findElement(By.name("series")).sendKeys("AutomationSeries");
		login.getDriver().findElement(By.name("price_sale")).sendKeys("10");
		login.getDriver().findElement(By.name("discount_rate_max")).sendKeys("AutomationDiscount");
		login.getDriver().findElement(By.name("unit")).sendKeys("AutomationUnit");
		login.getDriver().findElement(By.name("buy_price")).sendKeys("5");

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Salvesta')]")))
				.click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.className("fa-long-arrow-left"))).click();

		// Location of added product ( last page ).
		WebElement pageNumbersElement = login.getDriver().findElement(By.id("laravelPaginationUl"));
		int countPages = pageNumbersElement.findElements(By.tagName("li")).size() - 2;

		login.wait.until(ExpectedConditions.elementToBeClickable(By.id("laravelPaginationUl")))
				.findElement(By.linkText(Integer.toString(countPages))).click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-product='AutomationName']")))
				.click();

		Thread.sleep(1000);
		login.wait.until(ExpectedConditions.elementToBeClickable(By.name("save"))).click();
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("categoryModal")));
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
