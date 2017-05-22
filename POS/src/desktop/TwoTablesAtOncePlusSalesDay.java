package desktop;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;

public class TwoTablesAtOncePlusSalesDay {
	LogInClass login = new LogInClass();

	@Test(description = "Control the behaviour of the system and all the calculations if payments in two different tables in one session were made")
	public void main() throws InterruptedException, AWTException {

		// Robot - to deal with Print pop up window
		Robot r = new Robot();
		r.delay(1000);

		// Closing cassa session
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Kassa"))).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endSalesDay()']")))
				.click();

		String cassaSession = login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='endShift']/div[1]/div[8]/div/div[5]/span[2]")))
				.getText();

		// End sales day total sum
		String resSession = "";
		for (int i = 0; i < cassaSession.length(); i++) {
			char c = cassaSession.charAt(i);
			if (Character.toString(c).equals(".")) {
				resSession = resSession + ".";
			}
			if (c < '0' || c > '9')
				continue;
			resSession = resSession + c;
		}

		double finalSumSession = Double.parseDouble(resSession) * 100;
		int finalSumIntSession = (int) (finalSumSession + 0.5d);

		login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='endShift']/div[1]/div[6]/div[3]/div/input")))
				.sendKeys(String.valueOf(finalSumIntSession));
		Thread.sleep(1000);

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='printSalesDayButton']")))
				.click();
		Thread.sleep(5000);

		// Using robot to deal with the pop up by pressing enter
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);

		String dealsNumber = "8";
		String cashAmount = "4.40";
		String bankCardAmount = "4.40";
		String giftCardAmount = "4.40";
		String myChefCardAmount = "4.40";
		String totalSum = "17.60";

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Ava päev"))).click();
		login.wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='sessionModal']/div/div/form/div[3]/div[2]/button")))
				.click();

		// Table one add products
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-name='Espresso ']")))
				.click();
		login.getDriver().findElement(By.cssSelector("[data-name='Espresso ']")).click();
		login.getDriver().findElement(By.cssSelector("[data-name='Espresso ']")).click();
		login.getDriver().findElement(By.cssSelector("[data-name='Espresso ']")).click();

		// Table two add products
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tables-grid']/button[7]")))
				.click();
		login.getDriver().findElement(By.cssSelector("[data-name='Espresso ']")).click();
		login.getDriver().findElement(By.cssSelector("[data-name='Espresso ']")).click();
		login.getDriver().findElement(By.cssSelector("[data-name='Espresso ']")).click();
		login.getDriver().findElement(By.cssSelector("[data-name='Espresso ']")).click();

		// Table two
		login.getDriver().findElement(By.cssSelector("[onclick='continueToCheckout()']")).click();
		Thread.sleep(1500);

		login.getDriver().findElement(By.id("toggleAllBoxes")).click();

		// Pay in cash -> first div in paymentMethodHolder.
		login.getDriver().findElement(By.id("checkbox_0")).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='paymentMethodHolder']/div[1]")))
				.click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endCheckoutModal()']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='checkoutContModal']/div/div/div[3]/div[2]/button[2]/span[2]"))).click();

		// Pay by card -> 2nd div in paymentMethodHolder.
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutContModal")));
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox_1"))).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='paymentMethodHolder']/div[2]")))
				.click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endCheckoutModal()']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='checkoutContModal']/div/div/div[3]/div[2]/button[2]/span[2]"))).click();

		// Pay by kinkekaart -> 3th in paymentMethodHolder.

		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutContModal")));
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-backdrop")));
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("fade")));
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox_2"))).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='paymentMethodHolder']/div[3]")))
				.click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endCheckoutModal()']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='checkoutContModal']/div/div/div[3]/div[2]/button[2]/span[2]"))).click();

		// Pay by myChef -> 4th in paymentMethodHolder.
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutContModal")));
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-backdrop")));
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("fade")));
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox_3"))).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='paymentMethodHolder']/div[4]")))
				.click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endCheckoutModal()']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='checkoutEndModal']/div/div/div[3]/div[2]/button[2]/span[2]"))).click();

		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutEndModal")));
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Kassa"))).click();

		// Table one
		login.getDriver().findElement(By.cssSelector("[onclick='continueToCheckout()']")).click();
		Thread.sleep(1500);

		login.getDriver().findElement(By.id("toggleAllBoxes")).click();

		// Pay in cash -> first div in paymentMethodHolder.
		login.getDriver().findElement(By.id("checkbox_0")).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='paymentMethodHolder']/div[1]")))
				.click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endCheckoutModal()']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='checkoutContModal']/div/div/div[3]/div[2]/button[2]/span[2]"))).click();

		// Pay by card -> 2nd div in paymentMethodHolder.
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutContModal")));
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-backdrop")));
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("fade")));
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox_1"))).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='paymentMethodHolder']/div[2]")))
				.click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endCheckoutModal()']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='checkoutContModal']/div/div/div[3]/div[2]/button[2]/span[2]"))).click();

		// Pay by kinkekaart -> 3th in paymentMethodHolder.
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutContModal")));
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-backdrop")));
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("fade")));
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox_2"))).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='paymentMethodHolder']/div[3]")))
				.click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endCheckoutModal()']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='checkoutContModal']/div/div/div[3]/div[2]/button[2]/span[2]"))).click();

		// Pay by myChef -> 4th in paymentMethodHolder.
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutContModal")));
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-backdrop")));
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("fade")));
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox_3"))).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='paymentMethodHolder']/div[4]")))
				.click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endCheckoutModal()']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='checkoutEndModal']/div/div/div[3]/div[2]/button[2]/span[2]"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endSalesDay()']")))
				.click();

		String test = login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='endShift']/div[1]/div[8]/div/div[5]/span[2]")))
				.getText();

		// End sales day total sum
		String res = "";
		for (int i = 0; i < test.length(); i++) {
			char c = test.charAt(i);
			if (Character.toString(c).equals(".")) {
				res = res + ".";
			}
			if (c < '0' || c > '9')
				continue;
			res = res + c;
		}

		double finalSum = Double.parseDouble(res) * 100;
		int finalSumInt = (int) (finalSum + 0.5d);

		login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='endShift']/div[1]/div[6]/div[3]/div/input")))
				.sendKeys(String.valueOf(finalSumInt));
		Thread.sleep(1000);
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='printSalesDayButton']")))
				.click();

		Thread.sleep(5000);

		// Using robot to deal with the pop up by pressing enter
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Müügipäevad"))).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sessions']/table/tbody/tr[2]")))
				.click();

		// Time to compare data from cassa with salesday
		AssertJUnit.assertEquals(dealsNumber, login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sessions']/div[1]/div[4]/p")))
				.getText());
		AssertJUnit.assertEquals(totalSum + " €",
				login.getDriver().findElement(By.xpath("//*[@id='sessions']/div[1]/div[3]/p")).getText());
		AssertJUnit.assertEquals(cashAmount + " €",
				login.getDriver().findElement(By.xpath("//*[@id='session-all']/div/div[3]/div")).getText());
		AssertJUnit.assertEquals(bankCardAmount + " €",
				login.getDriver().findElement(By.xpath("//*[@id='session-all']/div/div[4]/div")).getText());
		AssertJUnit.assertEquals(giftCardAmount + " €",
				login.getDriver().findElement(By.xpath("//*[@id='session-all']/div/div[5]/div")).getText());
		AssertJUnit.assertEquals(myChefCardAmount + " €",
				login.getDriver().findElement(By.xpath("//*[@id='session-all']/div/div[6]/div")).getText());
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
