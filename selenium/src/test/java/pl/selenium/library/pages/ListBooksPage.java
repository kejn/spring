package pl.selenium.library.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ListBooksPage extends NavigationBarOnPage {

	@FindBy(css="section.ng-scope > button:nth-child(5)")
	private WebElement addBookButton;
	
	public ListBooksPage(WebDriver driver) {
		super(driver);
	}
	
	public AddBookModal clickAddBookButton() {
		addBookButton.click();
		return PageFactory.initElements(driver, AddBookModal.class);
	}

}
