package pl.selenium.library;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Represents webpage which contains a form. Requires subclass to implement
 * method returning form to be validated.
 * 
 * @author KNIEMCZY
 */
public abstract class AbstractPageWithFormObject extends AbstractPageObject {

	public AbstractPageWithFormObject(WebDriver driver) {
		super(driver);
	}

	/**
	 * @return form to be validated.
	 */
	public abstract WebElement getForm();

	/**
	 * @return <b>true</b> if form contains any <code>required</code> input
	 *         which is <code>invalid</code>
	 */
	@SuppressWarnings("deprecation")
	public boolean hasError() {
		try {
			getForm().findElement(By.cssSelector("input:required"));
			getForm().findElement(By.cssSelector("input:invalid"));
			Log.debug("form hasError == true");
			return true;
		} catch (NoSuchElementException e) {
			Log.debug("invalid element NOT FOUND - form has no error");
		} catch (Exception e) {
			Log.debug("form or one of its inputs dismissed - it indicates validation to have passed");
		}
		return false;
	}

}
