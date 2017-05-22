package desktop;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;

public class EmptyCheckOutError {
	LogInClass login = new LogInClass();

	@Test(description = "Checks that it is impossible to pay for goods if none were selected")
	public void main() throws InterruptedException {

		login.getDriver().findElement(By.linkText("Kassa")).click();
		login.getDriver().findElement(By.cssSelector("[data-name='Espresso ']")).click();
		login.getDriver().findElement(By.cssSelector("[onclick='continueToCheckout()']")).click();

		Thread.sleep(1000);
		login.getDriver().findElement(By.id("toggleAllBoxes")).click();
		login.getDriver().findElement(By.cssSelector("[onclick='endCheckoutModal()']")).click();

		String errorMessage = login.wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='errorModal']/div/div/div[2]/p")))
				.getText();

		AssertJUnit.assertEquals(errorMessage, "Palun vali makseviis ja summa!");
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
