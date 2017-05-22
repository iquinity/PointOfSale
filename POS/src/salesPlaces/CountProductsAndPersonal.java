package salesPlaces;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;

public class CountProductsAndPersonal {
	LogInClass login = new LogInClass();

	@Test(description = "Check if correct amount of goods and employees is displayed in the 'Müügikohad' section.")
	public void main() throws InterruptedException {

		login.getDriver().findElement(By.linkText("Tooted")).click();

		WebElement masterDivGoods = login.getDriver().findElement(By.id("outlet30all"));
		// First page products number
		int firstPageGoods = masterDivGoods.findElements(By.className("clickable-row")).size();

		WebElement pageNumbersElement = login.getDriver().findElement(By.id("laravelPaginationUl"));
		// We don't take into consideration buttons turn one page back or
		// forward, and the last page in order to count total product number
		// correctly. First
		// page product quantity * (amount of total pages - last page) +
		// quantity
		// of products on the last page.
		int countPages = pageNumbersElement.findElements(By.tagName("li")).size() - 3;

		// Going to the last page
		login.wait.until(ExpectedConditions.elementToBeClickable(By.id("laravelPaginationUl")))
				.findElement(By.linkText(Integer.toString(countPages + 1))).click();

		WebElement masterDivGoods2 = login.getDriver().findElement(By.id("outlet30all"));
		// Last page products number
		int lastPageGoods = masterDivGoods2.findElements(By.className("clickable-row")).size();

		int goodsNumber = firstPageGoods * countPages + lastPageGoods;

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar-header"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Töötajad"))).click();

		WebElement masterDivEmployee = login.getDriver().findElement(By.id("main-employees"));
		int employeeNumber = masterDivEmployee.findElements(By.className("clickable-row")).size();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar-header"))).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Müügikohad"))).click();

		int employees = Integer.parseInt(
				login.getDriver().findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr[2]/td[4]")).getText());
		int goods = Integer.parseInt(
				login.getDriver().findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr[2]/td[5]")).getText());

		AssertJUnit.assertEquals(Integer.valueOf(employees), Integer.valueOf(employeeNumber));
		AssertJUnit.assertEquals(Integer.valueOf(goods), Integer.valueOf(goodsNumber));
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
