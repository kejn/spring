package pl.selenium.library.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pl.selenium.library.AbstractPageObject;

public abstract class NavigationBarOnPage extends AbstractPageObject {

	@FindBy(css=".nav > li:nth-child(1) > a:nth-child(1)")
	protected WebElement listBooks;

	@FindBy(css=".nav > li:nth-child(2) > a:nth-child(1)")
	protected WebElement listAuthors;
	
	public NavigationBarOnPage(WebDriver driver) {
		super(driver);
	}
	
	public ListBooksPage clickListBooks() {
		listBooks.click();
		return PageFactory.initElements(driver, ListBooksPage.class);
	}

//	public ListAuthorsPage clickListAuthors() {
//		listBooks.click();
//		return PageFactory.initElements(driver, ListAuthorsPage.class);
//	}

}
