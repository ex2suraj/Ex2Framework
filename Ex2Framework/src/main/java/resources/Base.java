package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author skumar
 *  DO NOT EDIT THIS FILE **
 */
public class Base {

	public WebDriver driver;
	public Properties prop;
	int sleep;

	@BeforeTest
	public void setup() throws IOException {
		readConfig();
	}

	public WebDriver initDriver() throws IOException {

		String browserName = prop.getProperty("browser");
		System.out.println("Browser is :" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) // For Chrome browser
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) // For Firefox browser
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup(); // For Edge browser
			driver = new EdgeDriver();
		}
		System.out.println("browser launched, OK");
		driver.manage().timeouts().implicitlyWait(sleep, TimeUnit.SECONDS);
		return driver;
	}

	private void readConfig() throws IOException {
		String currentDir = System.getProperty("user.dir");
		String defaultConf = "./default.properties";
		prop = new Properties();
		FileInputStream fis = new FileInputStream(currentDir + defaultConf);
		prop.load(fis);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
