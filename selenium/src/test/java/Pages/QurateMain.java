package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QurateMain {
	
	
	@FindBy(xpath = "//li[@id='menu-item-1840']") 
	public static WebElement MENU_NEWS; 
	
	@FindBy(xpath = "//a[contains(text(),'Corporate Responsibility')]") 
	public static WebElement MENU_CORP; 
	
	@FindBy(xpath = "//a[contains(text(),'Investors')]") 
	public static WebElement MENU_INVESTORS; 
	
	@FindBy(xpath = "//a[contains(text(),'Careers')]") 
	public static WebElement MENU_CAREERS; 
	
	@FindBy(xpath = "//a[contains(text(),'Contact Us')]") 
	public static WebElement MENU_CONTACT; 
	
	@FindBy(xpath = "//a[contains(text(),'Stories')]") 
	public static WebElement SUBMENU_STORIES; 
	
	@FindBy(xpath = "//li[contains(text(),'Brand Images')]/label") 
	public static WebElement SUBMENU_MEDIA; 
	
	
	//alternative method for finding elements

	public WebElement getNewsSubmenu(WebDriver driver){
		WebElement newsSubmenu = driver.findElement(By.xpath("//a[contains(text(),'Newsroom')]/following-sibling::ul")); 
		return newsSubmenu;
	}
	
	
}
