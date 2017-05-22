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

public class TwoEmployeesAtOnce {
	LogInClass login = new LogInClass();

	@Test(description = "Controlls the behaviour of the system if two employees use the system at once")
	public void test() throws InterruptedException, AWTException {

		// Robot - to deal with Print pop up window
		Robot r = new Robot();
		r.delay(1000);

		String dealsNumber = "6";
		String totalSum = "31.70 €";
		String cashSum = "6.90 €";
		String bankCardSum = "5.80 €";
		String giftCardSum = "19.00 €";

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Ava päev"))).click();
		login.wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='sessionModal']/div/div/form/div[3]/div[2]/button")))
				.click();

		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Kuumad joogid')]")))
				.click();

		login.wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-name='Inspiratsiooni erikohv']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-name='Espresso ']")))
				.click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Pearoad')]")))
				.click();
		login.wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-name='Gluteenivaba pitsa ']")))
				.click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addNoteButton"))).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("note"))).sendKeys("Test comment");
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Salvesta')]")))
				.click();
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addNoteModal")));

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-target='#kitchenModal']")))
				.click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("course"))).sendKeys("5");
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-note")))
				.sendKeys("NoteAutomation");
		Thread.sleep(1000);
		login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("[placeholder='Lisa kommentaar tellimuse kohta']")))
				.sendKeys("testComment2");
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Saada kööki')]")))
				.click();
		Thread.sleep(1000);

		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("kitchenModal")));
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Köök"))).click();

		login.wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'NoteAutomation')]")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ready"))).click();
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-dialog")));

		// Log out from the first account
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dropdown-toggle"))).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout"))).click();

		// Log in with second account
		login.getDriver().get("https://username:password@example.com");
		login.getDriver().findElement(By.name("email")).sendKeys("secondAccount");
		login.getDriver().findElement(By.name("password")).sendKeys("secondPassword");
		login.getDriver().findElement(By.className("btn-primary")).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Ava vahetus"))).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("openSession"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("order-ready-image")));

		login.wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@class,'order-ready-image')]//ancestor::button")))
				.click();
		login.wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='markOrderCompleted()']")))
				.click();
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("orderReadyModal")));

		login.wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='continueToCheckout()']")))
				.click();

		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("lockScreen")));

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toggleAllBoxes"))).click();

		// Pay in cash -> first div in paymentMethodHolder.
		login.getDriver().findElement(By.id("checkbox_0")).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='paymentMethodHolder']/div[1]")))
				.click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endCheckoutModal()']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='stayInCheckout()']")))
				.click();

		// Pay by card -> 2nd div in paymentMethodHolder.
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutContModal")));
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox_1"))).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='paymentMethodHolder']/div[2]")))
				.click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endCheckoutModal()']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='stayInCheckout()']")))
				.click();

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
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='backToPosPage()']")))
				.click();
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutEndModal")));

		// Second table payments
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'4')]"))).click();

		login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("[data-name='Tee (taime, must, roheline) tass']")))
				.click();
		login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("[data-name='Tee (taime, must, roheline) kann']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Pearoad')]")))
				.click();
		login.wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-name='Gluteenivaba pitsa ']")))
				.click();

		login.wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='continueToCheckout()']")))
				.click();

		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("lockScreen")));

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toggleAllBoxes"))).click();

		// Pay in cash -> first div in paymentMethodHolder.
		login.getDriver().findElement(By.id("checkbox_0")).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='paymentMethodHolder']/div[1]")))
				.click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endCheckoutModal()']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='stayInCheckout()']")))
				.click();

		// Pay by card -> 2nd div in paymentMethodHolder.
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutContModal")));
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox_1"))).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='paymentMethodHolder']/div[2]")))
				.click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endCheckoutModal()']")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='stayInCheckout()']")))
				.click();

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
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='backToPosPage()']")))
				.click();
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutEndModal")));

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick='endSalesDay()']")))
				.click();

		String totalCassaAmount = login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='endShift']/div[1]/div[8]/div/div[5]/span[2]")))
				.getText();

		// End sales day total sum
		String res = "";
		for (int i = 0; i < totalCassaAmount.length(); i++) {
			char c = totalCassaAmount.charAt(i);
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

		// Using robot to deal with the pop up by pressing escape
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);

		// Log out and control all the details with the second account that has
		// all the rights to see all the necessary information
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dropdown-toggle"))).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout"))).click();

		login.onlyAccountLogin();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Müügipäevad"))).click();
		login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sessions']/table/tbody/tr[2]")))
				.click();

		AssertJUnit.assertEquals(dealsNumber, login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='session-all']/div/div[1]/div")))
				.getText());
		AssertJUnit.assertEquals(totalSum, login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='session-all']/div/div[2]/div")))
				.getText());
		AssertJUnit.assertEquals(cashSum, login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='session-all']/div/div[3]/div")))
				.getText());
		AssertJUnit.assertEquals(bankCardSum, login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='session-all']/div/div[4]/div")))
				.getText());
		AssertJUnit.assertEquals(giftCardSum, login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='session-all']/div/div[5]/div")))
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
