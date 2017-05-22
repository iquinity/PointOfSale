package transactions;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;

public class Purchase {

	LogInClass login = new LogInClass();

	@Test(description = "Order goods to the storage and controll if the amount has actually changed")
	public void main() throws InterruptedException {

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar-header"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Ladu"))).click();

		int inventory = Integer.parseInt(login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='outlet30all']/table/tbody/tr[2]/td[6]")))
				.getText());

		login.wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tehingud"))).click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Ost"))).click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='buy-orders']/button"))).click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='datetimepicker1']/span"))).click();
		login.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='datetimepicker1']/span"))).click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.name("save"))).click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='searchResults2']/tr[1]"))).click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='order']/button"))).click();

		login.wait
				.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//*[@id='createOrderProductModal']/div/div/form/div[1]/div[1]/div/button/span[1]")))
				.click();

		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id='createOrderProductModal']/div/div/form/div[1]/div[1]/div/div/div/input")))
				.sendKeys("Inspiratsiooni erikohv");

		login.wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//*[@id='createOrderProductModal']/div/div/form/div[1]/div[1]/div/div/ul/li[1]/a/span[1]")))
				.click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("quantity"))).sendKeys("20");

		login.wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id='createOrderProductModal']/div/div/form/div[2]/span[2]/button"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar-header"))).click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Ladu"))).click();

		int actualInventory = Integer.parseInt(login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='outlet30all']/table/tbody/tr[2]/td[6]")))
				.getText());

		AssertJUnit.assertEquals(actualInventory, inventory + 20);

		login.wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tehingud"))).click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='searchResults2']/tr[1]/td[6]")))
				.click();

		login.wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='deleteOrderModal']/div/div/form/div[2]/span[2]/button")))
				.click();
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
