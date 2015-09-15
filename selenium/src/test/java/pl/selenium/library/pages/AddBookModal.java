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

	private final String alertMessageCSS = ".modal-body > div:nth-child(3)";

	@FindBy(css = ".modal-lg > div:nth-child(1) > div:nth-child(1) > form:nth-child(1)")
	private WebElement form;

	@FindBy(css = ".form-control")
	private WebElement bookTitle;

	@FindBy(css = "input.btn.btn-primary")
	private WebElement addAuthorButton;

	@FindBy(css = "input.btn.btn-success")
	private WebElement saveBookButton;

	public AddBookModal(WebDriver driver) {
		super(driver);
	}

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

	public AddAuthorModal clickAddAuthorButton() {
		addAuthorButton.click();
		return PageFactory.initElements(driver, AddAuthorModal.class);
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle.sendKeys(bookTitle);
	}

	public String getBookTitle() {
		return this.bookTitle.getText();
	}

	public void clickSaveBookButton() {
		saveBookButton.click();
	}

	@SuppressWarnings("deprecation")
	public void setAuthorsFirstName(int index, String firstName) {
		try {
			getAuthor(index).get(0).clear();
			getAuthor(index).get(0).sendKeys(firstName);
		} catch (IndexOutOfBoundsException e) {
			Log.debug("in setAuthorsFirstName():\n" + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public void setAuthorsLastName(int index, String lastName) {
		try {
			getAuthor(index).get(1).clear();
			getAuthor(index).get(1).sendKeys(lastName);
		} catch (IndexOutOfBoundsException e) {
			Log.debug("in setAuthorsLastName():\n" + e.getMessage());
		}		
	}

	private List<WebElement> getAuthor(int index) throws IndexOutOfBoundsException {
		List<WebElement> authorsFirstLastNames = null;
		List<WebElement> result = new ArrayList<>();
		try {
			authorsFirstLastNames = driver.findElements(By.cssSelector(".modal-body > p.ng-scope"));
			WebElement firstName = authorsFirstLastNames.get(index).findElement(
					By.cssSelector(String.format(":nth-child(%d)", 1)));
			WebElement lastName = authorsFirstLastNames.get(index).findElement(
					By.cssSelector(String.format(":nth-child(%d)", 2)));
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
