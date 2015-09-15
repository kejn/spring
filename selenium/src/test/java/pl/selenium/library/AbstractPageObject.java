package pl.selenium.library;

import org.openqa.selenium.WebDriver;

/**
 * Represents webpage with WebDriver 
 * @author KNIEMCZY
 */
public abstract class AbstractPageObject {
	/**
	 * Let programmer load HTML elements from given page.
	 */
	protected WebDriver driver;
	
	public AbstractPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
