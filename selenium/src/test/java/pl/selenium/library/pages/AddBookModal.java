package pl.selenium.library.pages;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pl.selenium.library.AbstractPageObject;

public class AddBookModal extends AbstractPageObject {
	
	private final String alertMessageCSS = ".modal-body > div:nth-child(3)";

	@FindBy(css=".form-control")
	private WebElement bookTitle;

	@FindBy(css="input.btn.btn-primary")
	private WebElement addAuthorButton;

	@FindBy(css="input.btn.btn-success")
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
	
}
