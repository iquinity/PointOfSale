package desktop;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;

public class AddCommentary {
	LogInClass login = new LogInClass();

	@Test(description = "Add a comment to an order and check if is displayed correctly later in the 'Tehingud' section")
	public void test() {

		String commentary = "automationCommentary";

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Kassa"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-name='Espresso ']")))
				.click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addNoteButton"))).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("note"))).sendKeys(commentary);
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Salvesta')]")))
		.click();
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addNoteModal")));

		login.getDriver().findElement(By.cssSelector("[onclick='continueToCheckout()']")).click();

		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endCheckoutModal()']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='checkoutEndModal']/div/div/div[3]/div[2]/button[2]/span[2]"))).click();

		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutEndModal")));

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Tehingud"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Müük"))).click();
		login.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='searchResults']/tr[1]"))).click();

		AssertJUnit.assertEquals(commentary,
				login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note"))).getText());
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
