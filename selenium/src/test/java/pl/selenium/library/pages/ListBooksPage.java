package pl.selenium.library.pages;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ListBooksPage extends NavigationBarOnPage {

	@FindBy(css = "section.ng-scope > div:nth-child(1)")
	private WebElement alert;

	@FindBy(css = ".ng-pristine")
	private WebElement searchInput;

	@FindBy(css = "section.ng-scope > button:nth-child(4)")
	private WebElement searchButton;

	@FindBy(css = "section.ng-scope > button:nth-child(5)")
	private WebElement addBookButton;

	public ListBooksPage(WebDriver driver) {
		super(driver);
	}

	public AddBookModal clickAddBookButton() {
		addBookButton.click();
		return PageFactory.initElements(driver, AddBookModal.class);
	}

	public void setTitlePrefix(String titlePrefix) {
		searchInput.clear();
		searchInput.sendKeys(titlePrefix);
	}

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

	public void clickSearchButton() {
		searchButton.click();
	}

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
