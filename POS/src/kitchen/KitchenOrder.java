package kitchen;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;

public class KitchenOrder {

	LogInClass login = new LogInClass();

	@Test(description = "Check for a correct display of order that was finished in the kitchen")
	public void test() throws InterruptedException {

		String userName = login.getUserName();
		String orderComment = "kitchenOrderCompleted";

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Kassa"))).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Pearoad')]")))
				.click();
		login.wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-name='Gluteenivaba pitsa ']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-target='#kitchenModal']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-note")))
				.sendKeys(orderComment);
		Thread.sleep(1000);
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Saada kööki')]")))
				.click();
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("kitchenModal")));
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Köök"))).click();

		login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'kitchenOrderCompleted')]")))
				.click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ready"))).click();
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-dialog")));

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar-header"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Köögitellimused"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='searchResults']/tr[1]")))
				.click();

		AssertJUnit.assertEquals(userName,
				login.wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='order']/div[1]/div[2]/p")))
						.getText());
		AssertJUnit.assertEquals(orderComment,
				login.wait
						.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//*[@id='order']/table/tbody/tr[2]/td[3]")))
						.getText());

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
