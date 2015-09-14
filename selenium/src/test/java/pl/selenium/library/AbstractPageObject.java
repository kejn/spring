package pl.selenium.library;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class AbstractPageObject {
	protected WebDriver driver;

	public AbstractPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@SuppressWarnings({ "deprecation" })
	public boolean hasError() {
		try {
			driver.findElement(By.cssSelector("input:required"));
			driver.findElement(By.cssSelector("input:invalid"));
			Log.debug("abstract hasError == true");
			return true;
		} catch (NoSuchElementException e) {
			Log.debug("abstract ELEMENT NOT FOUND error");
			return false;
		}
	}

}
