package pl.selenium.library.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pl.selenium.library.AbstractPageWithFormObject;

public class AddBookModal extends AbstractPageWithFormObject {

	/**
	 * Unique CSS selector for alert displaying message
	 */
	private final String alertMessageCSS = ".modal-body > div:nth-child(3)";

	/**
	 * Form to be validated.
	 */
	@FindBy(css = ".modal-lg > div:nth-child(1) > div:nth-child(1) > form:nth-child(1)")
	private WebElement form;

	/**
	 * Input: book title
	 */
	@FindBy(css = ".form-control")
	private WebElement bookTitle;

	/**
	 * Button opening a new AddAuthorModal.
	 */
	@FindBy(css = "input.btn.btn-primary")
	private WebElement addAuthorButton;

	/**
	 * Button closing this modal and adding book to book list.
	 */
	@FindBy(css = "input.btn.btn-success")
	private WebElement saveBookButton;

	public AddBookModal(WebDriver driver) {
		super(driver);
	}

	/**
	 * Additionaly checks if authors were added. If not there should be shown an
	 * danger-alert in this modal.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean hasError() {
		try {
			WebElement alert = driver.findElement(By.cssSelector(alertMessageCSS));
			Log.debug("alert found");
			return super.hasError() || alert.isDisplayed();
		} catch (NoSuchElementException e) {
			Log.debug("alert NOT found");
			return false;
		}
	}

	@Override
	public WebElement getForm() {
		return form;
	}

	/**
	 * @return AddAuthorModal
	 */
	public AddAuthorModal clickAddAuthorButton() {
		addAuthorButton.click();
		return PageFactory.initElements(driver, AddAuthorModal.class);
	}

	/**
	 * Set text for {@link #bookTitle}.
	 * 
	 * @param bookTitle
	 *            to be set.
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle.sendKeys(bookTitle);
	}

	/**
	 * @return String text in {@link #bookTitle}
	 */
	public String getBookTitle() {
		return this.bookTitle.getText();
	}

	/**
	 * If form is valid: {@link #saveBookButton} action is called. Otherwise
	 * there is shown information which field is required or alert saying that
	 * no authors were added.
	 */
	public void clickSaveBookButton() {
		saveBookButton.click();
	}

	/**
	 * Set text for <b>index</b>-th book's author's <b>firstName</b>
	 * 
	 * @param index
	 *            of already added book authors (counting from 0)
	 * @param firstName
	 *            to be set.
	 */
	@SuppressWarnings("deprecation")
	public void setAuthorsFirstName(int index, String firstName) {
		try {
			getAuthor(index).get(0).clear();
			getAuthor(index).get(0).sendKeys(firstName);
		} catch (IndexOutOfBoundsException e) {
			Log.debug("in setAuthorsFirstName():\n" + e.getMessage());
		}
	}

	/**
	 * Set text for <b>index</b>-th book's author's <b>lastName</b>
	 * 
	 * @param index
	 *            of already added book authors (counting from 0)
	 * @param lastName
	 *            to be set.
	 */
	@SuppressWarnings("deprecation")
	public void setAuthorsLastName(int index, String lastName) {
		try {
			getAuthor(index).get(1).clear();
			getAuthor(index).get(1).sendKeys(lastName);
		} catch (IndexOutOfBoundsException e) {
			Log.debug("in setAuthorsLastName():\n" + e.getMessage());
		}
	}

	/**
	 * 
	 * @param index
	 *            of demanded book's author (counting from 0)
	 * @return 2-element list containing inputs with author's <b>firstName</b>
	 *         and <b>lastName</b>, right in this order.
	 * @throws IndexOutOfBoundsException
	 *             if <b>index</b> exceeded bounds of current authors list in
	 *             this modal.
	 */
	private List<WebElement> getAuthor(int index) throws IndexOutOfBoundsException {
		List<WebElement> authorsFirstLastNames = null;
		List<WebElement> result = new ArrayList<>();
		try {
			authorsFirstLastNames = driver.findElements(By.cssSelector(".modal-body > p.ng-scope"));
			WebElement firstName = authorsFirstLastNames.get(index)
					.findElement(By.cssSelector(String.format(":nth-child(%d)", 1)));
			WebElement lastName = authorsFirstLastNames.get(index)
					.findElement(By.cssSelector(String.format(":nth-child(%d)", 2)));
			result.add(firstName);
			result.add(lastName);
		} catch (NoSuchElementException e) {
			String listSize = authorsFirstLastNames == null ? "@null" : Integer.toString(authorsFirstLastNames.size());
			throw new IndexOutOfBoundsException(
					"Given index [" + index + "] exceeds size of resulting list [" + listSize + "]");
		}
		return result;
	}

}
