package pl.selenium.library;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPageObject {
	protected WebDriver driver;
	
	public AbstractPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
