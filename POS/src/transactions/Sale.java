package transactions;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;

public class Sale {
	LogInClass login = new LogInClass();

	@Test(description = "Check if a sale that was made previously is correctly displayed in the 'Tehingud' section.")
	public void main() throws InterruptedException {

		String expectedSalesAmount = "9.00 €";

		login.getDriver().findElement(By.linkText("Kassa")).click();

		login.getDriver().findElement(By.cssSelector("[data-name='Kuum sokolaad']")).click();
		login.getDriver().findElement(By.cssSelector("[data-name='Cappucino 240ml']")).click();
		login.getDriver().findElement(By.cssSelector("[data-name='Kohv piimaga 240ml']")).click();

		login.getDriver().findElement(By.xpath("//*[@id='checkout']/div[2]/div[3]/button[5]")).click();

		login.wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='checout-footer-buttons']/button[2]")))
				.click();

		login.wait
				.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//*[@id='checkoutEndModal']/div/div/div[3]/div[2]/button[2]")))
				.click();

		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutEndModal")));
		login.wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Tehingud"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Müük"))).click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='searchResults']/tr[1]"))).click();

		AssertJUnit.assertEquals("Sularaha: " + expectedSalesAmount,
				login.wait
						.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//*[@id='order']/div[2]/div[2]/span[1]")))
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
