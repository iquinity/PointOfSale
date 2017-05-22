package profile;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;

public class PersonalDataChange {

	LogInClass login = new LogInClass();

	@Test(description = "Check if it saves all the data when account data is changed. Then delete all the test data")
	public void main() throws InterruptedException {

		login.wait.until(ExpectedConditions.elementToBeClickable(By.className("dropdown-toggle"))).click();
		login.wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id='site-navbar-collapse']/ul/li[8]/ul/li[1]/a")))
				.click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.id("edit"))).click();

		String firstname = login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")))
				.getAttribute("value");
		String surname = login.getDriver().findElement(By.name("surname")).getAttribute("value");
		String personalId = login.getDriver().findElement(By.name("personalId")).getAttribute("value");

		int length1v2 = firstname.length() + 3;
		int length2v2 = surname.length() + 3;
		int length3v2 = personalId.length() + 3;

		login.getDriver().findElement(By.name("firstname")).sendKeys("123");
		login.getDriver().findElement(By.name("surname")).sendKeys("123");
		login.getDriver().findElement(By.name("personalId")).sendKeys("123");

		login.getDriver().findElement(By.xpath("//*[@id='settings']/button")).click();

		login.wait.until(ExpectedConditions.elementToBeClickable(By.id("edit"))).click();

		if ((length1v2 == login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")))
				.getAttribute("value").length())
				&& (length2v2 == login.getDriver().findElement(By.name("surname")).getAttribute("value").length())
				&& (length3v2 == login.getDriver().findElement(By.name("personalId")).getAttribute("value").length())) {

			login.getDriver().findElement(By.name("firstname")).clear();
			login.getDriver().findElement(By.name("firstname")).sendKeys(firstname);
			login.getDriver().findElement(By.name("surname")).clear();
			login.getDriver().findElement(By.name("surname")).sendKeys(surname);
			login.getDriver().findElement(By.name("personalId")).clear();
			login.getDriver().findElement(By.name("personalId")).sendKeys(personalId);

			login.getDriver().findElement(By.xpath("//*[@id='settings']/button")).click();

			login.wait.until(ExpectedConditions.elementToBeClickable(By.id("edit"))).click();

		}

		else
			Assert.fail();
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
