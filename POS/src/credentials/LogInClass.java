package credentials;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInClass {

	private WebDriver driver;
	public WebDriverWait wait;
	private String browserName;
	private String userName = "Roman Radionov";

	public void loginMethod(String browser) {
		if (browser.equals("chrome")) {

			browserName = "chrome";
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");

			driver = new ChromeDriver(options);
			wait = new WebDriverWait(driver, 10);

			driver.get("https://username:password@roman.radionov@example.ee");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")))
					.sendKeys("roman.radionov@example.ee");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("password");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary"))).click();
		}

		if (browser.equals("firefox")) {

			browserName = "firefox";
			System.setProperty("webdriver.gecko.driver", "C:\\Utility\\BrowserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			wait = new WebDriverWait(driver, 10);
			driver.manage().window().maximize();

			driver.get("https://username:password@b.example.ee");
			Alert alert = driver.switchTo().alert();
			alert.accept();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")))
					.sendKeys("roman.radionov@example.ee");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("password");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary"))).click();

		}

	}

	public String getUserName() {
		return userName;
	}

	public void onlyAccountLogin() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")))
				.sendKeys("roman.radionov@example.ee");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("password");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary"))).click();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public String getBrowserName() {
		return browserName;
	}

}
