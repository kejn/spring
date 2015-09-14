package pl.selenium.library.pages;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pl.selenium.library.AbstractPageObject;

public class AddAuthorModal extends AbstractPageObject{

	private final String formCssSelector = ".modal-sm > div:nth-child(1) > div:nth-child(1) > form:nth-child(1)";
	
	@FindBy(name="firstName")
	private WebElement firstName;
	
	@FindBy(name="lastName")
	private WebElement lastName;

	@FindBy(css=".modal-sm > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(3) > input:nth-child(1)")
	private WebElement addAuthorButton;

	public AddAuthorModal(WebDriver driver) {
		super(driver);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean hasError() {
		try {
			WebElement form = driver.findElement(By.cssSelector(formCssSelector));
			form.findElement(By.cssSelector("input:required"));
			form.findElement(By.cssSelector("input:invalid"));
			Log.debug("author hasError == true");
			return true;
		} catch (NoSuchElementException e) {
			Log.debug("author ELEMENT NOT FOUND error");
		} catch (Exception e) {
			Log.debug("form or one of its inputs dismissed - it indicates validation to have passed");
		}
		return false;
	}
	
	public void setFirstName(String firstName) {
		this.firstName.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		this.lastName.sendKeys(lastName);
	}
	
	public void clickAddAuthorButton() {
		addAuthorButton.click();
	}
}


