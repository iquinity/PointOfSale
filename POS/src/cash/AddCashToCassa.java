package cash;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;

public class AddCashToCassa {
	LogInClass login = new LogInClass();

	@Test(description = "Adds cash to the point of sale, verify it was added and afterwards delete all the test data")
	public void main() throws InterruptedException {

		String depositAmount = "10";

		login.getDriver().findElement(By.linkText("Sularaha")).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sularaha lisamine kassasse")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addCashDepositModalBtn"))).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("depositAmount")))
				.sendKeys(depositAmount);
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("depositAmount"))).getText()
				.equals("10");
		login.wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='cashDepositModal']/div/div/form/div[2]/div/button")))
				.click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Kassa"))).click();

		login.getDriver().findElement(By.cssSelector("[onclick='endSalesDay()']")).click();
		final String cashDepositInCassa = login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-deposit"))).getText();

		AssertJUnit.assertEquals(cashDepositInCassa, depositAmount + ".00 €");
		login.getDriver().findElement(By.xpath("//*[@id='closeSalesDay']/div/div/div/button")).click();
		Thread.sleep(1000);

		login.getDriver().findElement(By.linkText("Sularaha")).click();
		login.getDriver().findElement(By.linkText("Sularaha lisamine kassasse")).click();

		login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='cashDeposit']/table/tbody/tr[1]/td[5]/span")))
				.click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='deleteCashWithdrawModal']/div/div/form/div[2]/span[2]/button"))).click();

		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
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
