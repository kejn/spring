package pl.selenium.library.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pl.selenium.library.AbstractPageWithFormObject;

public class AddAuthorModal extends AbstractPageWithFormObject {

	@FindBy(css=".modal-sm > div:nth-child(1) > div:nth-child(1) > form:nth-child(1)")
	private WebElement form;
	
	@FindBy(name="firstName")
	private WebElement firstName;
	
	@FindBy(name="lastName")
	private WebElement lastName;

	@FindBy(css=".modal-sm > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(3) > input:nth-child(1)")
	private WebElement addAuthorButton;

	public AddAuthorModal(WebDriver driver) {
		super(driver);
	}

	@Override
	public WebElement getForm() {
		return form;
	}

	public void setFirstName(String firstName) {
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		this.lastName.sendKeys(lastName);
	}
	
	public void clickAddAuthorButton() {
		addAuthorButton.click();
	}

}


