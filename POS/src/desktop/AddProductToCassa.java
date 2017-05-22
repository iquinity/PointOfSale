package desktop;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;

public class AddProductToCassa {
	LogInClass login = new LogInClass();

	@Test(description = "Create two goods, add them to kassa, verify the process and delete the two created goods")
	public void main() throws InterruptedException {

		// Good nr1.
		login.getDriver().findElement(By.linkText("Tooted")).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Kuumad joogid"))).click();

		WebElement buttonClass = login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("outlet30category14")));
		buttonClass.findElement(By.id("create")).click();
		login.getDriver().findElement(By.name("name")).sendKeys("AutomationNameSoojad");
		login.getDriver().findElement(By.name("display_name")).sendKeys("AutomationDisplayNameSoojad");

		Select select = new Select(login.getDriver().findElement(By.name("categories[]")));
		select.deselectAll();
		select.selectByVisibleText("Kuumad joogid");

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Salvesta')]")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit"))).click();

		Select select2 = new Select(login.getDriver().findElement(By.name("state")));
		select2.selectByVisibleText("Müügis");
		login.getDriver().findElement(By.cssSelector("[type='submit']")).click();

		// Good nr2.
		login.getDriver().findElement(By.linkText("Tooted")).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Lahja alkohol"))).click();

		WebElement buttonClass2 = login.wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("outlet30category16")));
		buttonClass2.findElement(By.id("create")).click();
		login.getDriver().findElement(By.name("name")).sendKeys("AutomationNameVeinid");
		login.getDriver().findElement(By.name("display_name")).sendKeys("AutomationDisplayNameVeinid");

		Select select3 = new Select(login.getDriver().findElement(By.name("categories[]")));
		select3.deselectAll();
		select3.selectByVisibleText("Lahja alkohol");

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Salvesta')]")))
				.click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit"))).click();

		Select select4 = new Select(login.getDriver().findElement(By.name("state")));
		select4.selectByVisibleText("Müügis");
		login.getDriver().findElement(By.cssSelector("[type='submit']")).click();

		// Look for goods in the point of sale
		login.getDriver().findElement(By.linkText("Kassa")).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='products-grid']/button[2]")))
				.click();

		login.wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-name='AutomationNameVeinid']")))
				.click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='products-grid']/button[1]")))
				.click();

		login.wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-name='AutomationNameSoojad']")))
				.click();

		// Delete added goods
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Tooted"))).click();
		login.getDriver().findElement(By.linkText("Lahja alkohol")).click();

		login.getDriver().findElement(By.cssSelector("*[data-product='AutomationNameVeinid']")).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("save"))).click();

		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Kuumad joogid"))).click();

		login.getDriver().findElement(By.cssSelector("*[data-product='AutomationNameSoojad']")).click();
		login.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("save"))).click();
		login.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("deleteProductModal")));
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
