package pl.selenium.library.pages;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page in which user can list all books from the repository. Available
 * operations are searching, adding, editing and deleting.
 * 
 * @author KNIEMCZY
 */
public class ListBooksPage extends NavigationBarOnPage {

	/**
	 * Alert showing information for the user if transaction between browser and
	 * server was successful (green background) or not (red background).
	 */
	@FindBy(css = "section.ng-scope > div:nth-child(1)")
	private WebElement alert;

	/**
	 * Input: title prefix for searching books
	 */
	@FindBy(css = ".ng-pristine")
	private WebElement searchInput;

	/**
	 * Button executing book searching action based on text in
	 * {@link #searchInput}
	 */
	@FindBy(css = "section.ng-scope > button:nth-child(4)")
	private WebElement searchButton;

	/**
	 * Button opening AddBookModal
	 */
	@FindBy(css = "section.ng-scope > button:nth-child(5)")
	private WebElement addBookButton;

	public ListBooksPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @return AddBookModal
	 */
	public AddBookModal clickAddBookButton() {
		addBookButton.click();
		return PageFactory.initElements(driver, AddBookModal.class);
	}

	/**
	 * Set text for {@link #titlePrefix}.
	 * 
	 * @param titlePrefix
	 *            to be set.
	 */
	public void setTitlePrefix(String titlePrefix) {
		searchInput.clear();
		searchInput.sendKeys(titlePrefix);
	}

	/**
	 * @return <b>true</b> if there was recently shown successful alert 
	 */
	@SuppressWarnings("deprecation")
	public boolean alertSuccess() {
		try {
			driver.findElement(By.className("alert-success"));
			return true;
		} catch (NoSuchElementException e) {
			Log.debug("Could not find any successful alert :(");
		}
		return false;
	}

	/**
	 * Clicks on {@link #searchButton}
	 */
	public void clickSearchButton() {
		searchButton.click();
	}

	/**
	 * @return number of rows in table containing book search results.
	 */
	@SuppressWarnings("deprecation")
	public int countBooks() {
		int result = 0;
		try {
			result = driver.findElements(By.cssSelector("tr.ng-scope")).size();
		} catch (NoSuchElementException e) {
			Log.debug("Could not find table rows containing books' details");
		}
		return result;
	}

	/**
	 * @param index of results table row
	 */
	@SuppressWarnings("deprecation")
	public void clickDeleteButton(int index) {
		try {
			driver.findElement(By.cssSelector(
					String.format("tr.ng-scope:nth-child(%d) > td:nth-child(3) > button:nth-child(1)", index + 2)))
					.click();
		} catch (NoSuchElementException e) {
			Log.debug("Could not find the delete button for this row");
		}
	}

	/**
	 * 
	 * @param index of results table row
	 * @return AddBookModal if the EDIT button was found. <b>null</b> otherwise.
	 */
	@SuppressWarnings("deprecation")
	public AddBookModal clickEditBookButton(int index) {
		try {
			driver.findElement(By.cssSelector(
					String.format("tr.ng-scope:nth-child(%d) > td:nth-child(4) > button:nth-child(1)", index + 2)))
					.click();
			return PageFactory.initElements(driver, AddBookModal.class);
		} catch (NoSuchElementException e) {
			Log.debug("Could not find the edit button for this row");
		}
		return null;
	}

}
