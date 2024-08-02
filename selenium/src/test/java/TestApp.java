

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import Pages.QurateMain;

public class TestApp {
	
	public static  WebDriver driver;
	public CommonActions actions;
	public QurateMain qurateMain;
	

	
	@BeforeSuite (alwaysRun = true)
	public void setUpClass(){
		BrowserFactory browserfactory = new BrowserFactory();
		this.driver = browserfactory.getBrowser("CH");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		actions = new CommonActions(); //the class i like to delegate most of my methods
		actions.setDriver(driver);
		
	}
	
	
	@AfterSuite (alwaysRun = true)
	public void tearDownClass() throws IOException{
		driver.close();
	    Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f"); // driver.close() failsafe
		//BrowserFactory.closeBrowser(); for tests using multiple browsers
	}
	

	
	@BeforeMethod (alwaysRun = true)
	public void setUpMethod() throws Exception { 
		 qurateMain = PageFactory.initElements(driver, QurateMain.class);
	}
	
	

	@Test (groups = { "group2.subgroup2" }) //(priority = 1)
	@Parameters({ "someParameter" })
	public void parameterTest(String param1){
		assert "TestParam".equals(param1);
		//kind of pointless test just to show that I know params :) 
		// the weirdly overcomplicated groups are also just to show the principles
	}
	
	@Test   (groups = { "group1" } , priority=1) 
	public void sumbMenu(){
		
		actions.hover(qurateMain.MENU_NEWS);
		actions.hover(qurateMain.getNewsSubmenu(driver));
		
		driver.findElement(By.xpath("//li[@id='menu-item-1841']/a")).click();
		
		
		
	}
	
	@Test   (groups = { "group1" } , priority=2) 
	public void mediaItems(){
		
		qurateMain.SUBMENU_MEDIA.click();
		
		WebElement parent = driver.findElement(By.xpath("//div[@id='media-results']"));
		List <WebElement> children1 = parent.findElements(By.xpath(".//*"));
		List <WebElement> children2 = driver.findElements(By.xpath("//div[@id='media-results']//*"));
		
		Assert.assertTrue(children1.size()==children2.size()); //another rather pointless step just to show asserts at work
		
		WebElement loadMore = driver.findElement(By.xpath("//a[@id='get-media']"));
		loadMore.click();
		
		actions.fluentWait().until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@id='media-results']//*"), children1.size()+1));
		
	}

	
	@Test   (groups = { "group1" } , priority=3) 
	public void mediaCalendar(){
		
		//unfinished test to show operations of the type sendkeys i searching for descendants in xpathie
		
	
		actions.scrollToTop(driver);
		actions.scrollAndClick(driver.findElement(By.xpath("//input[@name='filter-by-date-action']")));
		
		
		WebElement dateStart = driver.findElement(By.xpath("//input[@name='daterangepicker_start']"));
		actions.scrollAndClick(dateStart);
		
		dateStart.clear();
		dateStart.sendKeys("05/08/2021");
		
		/*
		//driver.findElement(By.xpath("//div[@class='calendar-table']/descendant::td[contains(text(),'8')]")).click();
		
		WebElement startDate = driver.findElement(By.xpath("//div[@class='calendar-table']/descendant::td[contains(text(),'8')]"));
		String startClass = startDate.getAttribute("class");
		
		actions.hover(startDate);
		startDate.click();
		
	//	actions.scrollAndClick(driver.findElement(By.xpath("//div[@class='calendar-table']/descendant::td[contains(text(),'8')]")));
		actions.fluentWait().until(ExpectedConditions.attributeToBe(By.xpath("//div[@class='calendar-table']/descendant::td[contains(text(),'8')]"), "class", startClass+"start-date active end-date available"));

		WebElement endDate = driver.findElement(By.xpath("//div[@class='calendar right']/descendant::td[contains(text(),'8')]"));
		String endClass = startDate.getAttribute("class");
		
		actions.hover(startDate);
		startDate.click();
		
		actions.fluentWait().until(ExpectedConditions.attributeToBe(By.xpath("//div[@class='calendar-table']/descendant::td[contains(text(),'8')]"), "class", endClass+"start-date active end-date available"));
*/
		
	}
	
	
	
	@Test   (groups = { "group1.subgroup1" }, priority=0) 
	public void loadPage(){
		driver.get("https://www.qurateretailgroup.com/");
		boolean result = actions.waitForPageToLoad();
		Assert.assertTrue(result);
		System.out.println("loadPage");
	}
	
	
	
	

}
