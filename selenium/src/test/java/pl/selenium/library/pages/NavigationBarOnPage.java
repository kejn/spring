package pl.selenium.library.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pl.selenium.library.AbstractPageObject;

/**
 * Navigation bar on top of every webpage in this project.
 * 
 * @author KNIEMCZY
 */
public abstract class NavigationBarOnPage extends AbstractPageObject {

	/**
	 * Navigates to ListBooksPage 
	 */
	@FindBy(css = ".nav > li:nth-child(1) > a:nth-child(1)")
	protected WebElement listBooks;

	public NavigationBarOnPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @return ListBooksPage
	 */
	public ListBooksPage clickListBooks() {
		listBooks.click();
		return PageFactory.initElements(driver, ListBooksPage.class);
	}

}