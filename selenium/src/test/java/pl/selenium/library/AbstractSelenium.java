package pl.selenium.library;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;

import pl.selenium.library.pages.WelcomePage;

/**
 * Abstract test class for selenium with Firefox webdriver.
 * @author KNIEMCZY
 */
@RunWith(SeleniumScreenshotJUnit4Runner.class)
public abstract class AbstractSelenium {

	private static final String SCREENSHOT_LOCATION = "C:\\tmp\\";
	
	private WebDriver driver;

	@Before
	public void setUp() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("init.accept_languages", "pl");
		driver = new FirefoxDriver(profile);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}

	/**
	 * @return WelcomePage initialized with {@link #driver}
	 */
	public WelcomePage openWelcomePage() {
		return PageFactory.initElements(driver, WelcomePage.class);
	}

	/**
	 * Takes screenshot.
	 * @throws IOException if <code>copyFile</code> operation fails
	 */
	public void takeScreenshot() throws IOException {
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(SCREENSHOT_LOCATION + screenshot.getName());
		FileUtils.copyFile(screenshot, destFile);
		System.out.printf("[[ATTACHMENT | %s]]\n", destFile.getAbsolutePath());
	}
}
