package people;

import org.testng.annotations.Test;

import credentials.LogInClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;

public class BusinessPerson {

	LogInClass login = new LogInClass();

	@Test
	public void main() throws InterruptedException {

		login.getDriver().findElement(By.linkText("Isikud")).click();
		Thread.sleep(500);
		login.getDriver().findElement(By.linkText("Juriidilised isikud")).click();
		Thread.sleep(500);
		login.getDriver().findElement(By.linkText("+ Lisa isik")).click();
		Thread.sleep(500);

		login.getDriver().findElement(By.name("orgname")).sendKeys("AutomationTest");
		login.getDriver().findElement(By.name("orgemail")).sendKeys("AutomationTest2");
		login.getDriver().findElement(By.name("orgphone")).sendKeys("AutomationTest3");
		login.getDriver().findElement(By.name("orgaddress")).sendKeys("AutomationTest4");
		Thread.sleep(500);

		login.getDriver().findElement(By.xpath("/html/body/div[1]/div/form/button")).click();
		Thread.sleep(5000);

		login.getDriver().findElement(By.className("fa-long-arrow-left")).click();
		Thread.sleep(500);

		login.getDriver().findElement(By.cssSelector("*[data-name='AutomationTest']")).click();
		Thread.sleep(200);

		login.getDriver().findElement(By.name("save")).click();

	}
	
	@Parameters("browserType")

	@BeforeMethod
	public void beforeMethod(String browserType) {
		if(browserType.equalsIgnoreCase("chrome")) {
		login.loginMethod("chrome");
		}

	}

	@AfterMethod
	public void afterMethod() {
		login.getDriver().quit();
	}

}
