package pl.selenium.library.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pl.selenium.library.AbstractPageWithFormObject;

/**
 * Modal in which author is added to book.
 * 
 * @author KNIEMCZY
 */
public class AddAuthorModal extends AbstractPageWithFormObject {

	/**
	 * Form to be validated.
	 */
	@FindBy(css = ".modal-sm > div:nth-child(1) > div:nth-child(1) > form:nth-child(1)")
	private WebElement form;

	/**
	 * Input: author first name
	 */
	@FindBy(name = "firstName")
	private WebElement firstName;

	/**
	 * Input: author last name
	 */
	@FindBy(name = "lastName")
	private WebElement lastName;

	/**
	 * Button closing this modal and adding author to book.
	 */
	@FindBy(css = ".modal-sm > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(3) > input:nth-child(1)")
	private WebElement addAuthorButton;

	public AddAuthorModal(WebDriver driver) {
		super(driver);
	}

	@Override
	public WebElement getForm() {
		return form;
	}

	/**
	 * Set text for {@link #firstName}.
	 * @param firstName to be set.
	 */
	public void setFirstName(String firstName) {
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
	}

	/**
	 * Set text for {@link #lastName}.
	 * @param lastName to be set.
	 */
	public void setLastName(String lastName) {
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
	}

	/**
	 * If form is valid: {@link #addAuthorButton} action is called.
	 * Otherwise there is shown information which field is required.
	 */
	public void clickAddAuthorButton() {
		addAuthorButton.click();
	}

}
