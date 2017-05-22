package goods;

import org.testng.annotations.Test;
import credentials.LogInClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;

public class AddCategory {
	LogInClass login = new LogInClass();

	@Test(description = "Check if adding product category works and then clear all the test data")
	public void main() throws InterruptedException {

		login.getDriver().findElement(By.linkText("Tooted")).click();
		login.getDriver().findElement(By.id("#categoryModal")).click();

		Thread.sleep(1000);
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-row"))).click();
		login.getDriver().findElement(By.className("new-category")).sendKeys("AutomationCategory");

		login.getDriver().findElement(By.id("save")).click();

		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("categoryModal")));
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create"))).click();

		login.getDriver().findElement(By.name("name")).sendKeys("AutomationName");
		login.getDriver().findElement(By.name("display_name")).sendKeys("AutomationDisplayName");
		login.getDriver().findElement(By.name("series")).sendKeys("AutomationSeries");
		login.getDriver().findElement(By.name("price_sale")).sendKeys("10");
		login.getDriver().findElement(By.name("discount_rate_max")).sendKeys("AutomationDiscount");
		login.getDriver().findElement(By.name("unit")).sendKeys("AutomationUnit");
		login.getDriver().findElement(By.name("buy_price")).sendKeys("5");

		Select select = new Select(login.getDriver().findElement(By.name("categories[]")));
		select.deselectAll();
		select.selectByVisibleText("AutomationCategory");

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Salvesta')]")))
				.click();
		login.wait.until(ExpectedConditions.elementToBeClickable(By.className("fa-long-arrow-left"))).click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.linkText("AutomationCategory"))).click();

		login.getDriver().findElement(By.id("#categoryModal")).click();
		Thread.sleep(1000);

		int locatorElementSize = login.getDriver().findElements(By.className("category-item")).size();
		login.getDriver().findElement(By.xpath("//*[@id='modalCategories']/li[" + locatorElementSize + "]/span/i"))
				.click();
		login.getDriver().findElement(By.id("save")).click();
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
