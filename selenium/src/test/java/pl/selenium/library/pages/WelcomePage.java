package pl.selenium.library.pages;

import org.openqa.selenium.WebDriver;

/**
 * Welcome page. 
 * @author KNIEMCZY
 */
public class WelcomePage extends NavigationBarOnPage {
	
	public WelcomePage(WebDriver driver) {
		super(driver);
		this.driver.get("http://localhost:9721/workshop/#/main/welcome");
	}
}
