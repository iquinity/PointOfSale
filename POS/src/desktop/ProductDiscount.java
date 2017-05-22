package desktop;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;

public class ProductDiscount {
	LogInClass login = new LogInClass();

	@Test(description = "Checks if it possible to set the maximum discount when selling a good")
	public void main() throws InterruptedException {

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Tooted"))).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='outlet30']/div[1]/ul/li[2]/a")))
				.click();
		login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='outlet30category14']/table/tbody/tr[3]/td[3]")))
				.click();

		String webElement1 = login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("discout_rate_max")))
				.getText();

		int goodOneDiscount = Integer.parseInt(webElement1);
		login.getDriver().get("https://b.itprojektid.ee/pos/public/products");
		login.getDriver().findElement(By.xpath("//*[@id='outlet30']/div[1]/ul/li[2]/a")).click();

		login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='outlet30category14']/table/tbody/tr[4]/td[3]")))
				.click();
		String webElement2 = login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("discout_rate_max")))
				.getText();
		int goodOneDiscount2 = Integer.parseInt(webElement2);
		login.getDriver().get("https://b.itprojektid.ee/pos/public/products");
		login.getDriver().findElement(By.xpath("//*[@id='outlet30']/div[1]/ul/li[2]/a")).click();

		login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='outlet30category14']/table/tbody/tr[6]/td[3]")))
				.click();
		String webElement3 = login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("discout_rate_max")))
				.getText();
		int goodOneDiscount3 = Integer.parseInt(webElement3);
		login.getDriver().get("https://example.com");
		login.getDriver().findElement(By.xpath("//*[@id='outlet30']/div[1]/ul/li[2]/a")).click();

		login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='outlet30category14']/table/tbody/tr[9]/td[3]")))
				.click();
		String webElement4 = login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("discout_rate_max")))
				.getText();
		int goodOneDiscount4 = Integer.parseInt(webElement4);

		login.getDriver().findElement(By.linkText("Kassa")).click();

		login.getDriver().findElement(By.cssSelector("[data-name='Espresso ']")).click();
		login.getDriver().findElement(By.cssSelector("[data-name='Topelt Espresso ']")).click();
		login.getDriver().findElement(By.cssSelector("[data-name='Suur Americano ']")).click();
		login.getDriver().findElement(By.cssSelector("[data-name='Kuum sokolaad']")).click();
		Thread.sleep(2000);

		login.getDriver().findElement(By.cssSelector("[data-discount_rate_max='" + goodOneDiscount + "']")).click();
		login.getDriver().findElement(By.id("button_10")).click();

		for (int i = 3; i > 0; i--) {
			login.getDriver().findElement(By.xpath("//*[@id='numpad']/button[10]")).click();
		}

		login.getDriver().findElement(By.cssSelector("[data-discount_rate_max='" + goodOneDiscount2 + "']")).click();
		login.getDriver().findElement(By.id("button_10")).click();

		for (int i = 3; i > 0; i--) {
			login.getDriver().findElement(By.xpath("//*[@id='numpad']/button[10]")).click();
		}

		login.getDriver().findElement(By.cssSelector("[data-discount_rate_max='" + goodOneDiscount3 + "']")).click();
		login.getDriver().findElement(By.id("button_10")).click();

		for (int i = 3; i > 0; i--) {
			login.getDriver().findElement(By.xpath("//*[@id='numpad']/button[10]")).click();
		}

		login.getDriver().findElement(By.cssSelector("[data-discount_rate_max='" + goodOneDiscount4 + "']")).click();
		login.getDriver().findElement(By.id("button_10")).click();

		for (int i = 3; i > 0; i--) {
			login.getDriver().findElement(By.xpath("//*[@id='numpad']/button[10]")).click();
		}

		int actualGoodOneDiscount = Integer.parseInt(
				login.getDriver().findElement(By.cssSelector("[data-discount_rate_max='" + goodOneDiscount + "']"))
						.findElement(By.className("row-discount")).getText());
		AssertJUnit.assertEquals(actualGoodOneDiscount, goodOneDiscount);

		int actualGoodOneDiscount2 = Integer.parseInt(
				login.getDriver().findElement(By.cssSelector("[data-discount_rate_max='" + goodOneDiscount2 + "']"))
						.findElement(By.className("row-discount")).getText());
		AssertJUnit.assertEquals(actualGoodOneDiscount2, goodOneDiscount2);

		int actualGoodOneDiscount3 = Integer.parseInt(
				login.getDriver().findElement(By.cssSelector("[data-discount_rate_max='" + goodOneDiscount3 + "']"))
						.findElement(By.className("row-discount")).getText());
		AssertJUnit.assertEquals(actualGoodOneDiscount3, goodOneDiscount3);

		int actualGoodOneDiscount4 = Integer.parseInt(
				login.getDriver().findElement(By.cssSelector("[data-discount_rate_max='" + goodOneDiscount4 + "']"))
						.findElement(By.className("row-discount")).getText());
		AssertJUnit.assertEquals(actualGoodOneDiscount4, goodOneDiscount4);

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
