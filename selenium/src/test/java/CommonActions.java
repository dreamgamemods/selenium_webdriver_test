

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class CommonActions {
	
	private static final long TIMEOUT_FOR_PAGE_LOAD = 5;
	private WebDriver driver;
	private JavascriptExecutor js;
	
	
	public void setDriver( WebDriver driver){
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}
	
	public boolean waitForPageToLoad(){
		
		/*a PageLoadStrategy alternative, for pages suffering from infinite loading
		 status "interactive" allows for performing tests when most elements have finished loading 
		 PageLoadStrategy must be NONE. not optimal for fast-loading websites
		*/
		String pageLoadStatus = "";
		boolean pageIsLoaded = false;
		long startTime = System.currentTimeMillis();
		
		do{
			try {pageLoadStatus = this.returnDocumentStatus();
			} catch(Exception e){e.printStackTrace();	}
			if(pageLoadStatus.equals("complete")/* || pageLoadStatus.equals("interactive")*/){	pageIsLoaded = true;}
		}while(!pageIsLoaded && !this.isTimeout(startTime, TIMEOUT_FOR_PAGE_LOAD));
		
		return pageIsLoaded;
	}

	private boolean isTimeout(long startTime, long timeoutForPageLoad) {
		long waitTime = timeoutForPageLoad * 1000;
		long endTime = startTime + waitTime;
		return System.currentTimeMillis() > endTime;
	}

	private String returnDocumentStatus() {
		return (String) js.executeScript("return document.readyState");
	}
	
	
	public void uploadFile(WebElement downloadElement){
		
		//wget allows selenium to downlaod files 
		
			        String sourceLocation = downloadElement.getAttribute("href");
			        
			        String wget_command = "cmd /c E:\\Eclipse\\eclipse\\workspace\\selenium\\src\\test\\java\\resources\\wget.exe -P D: --no-check-certificate " + sourceLocation;

			        try {
			        Process exec = Runtime.getRuntime().exec(wget_command);
			        int exitVal = exec.waitFor();
			        System.out.println("Exit value: " + exitVal);
			        } catch (InterruptedException | IOException ex) {
			        System.out.println(ex.toString());
			        }
	}
	
	public void downloadFile(WebElement uploadElement, String filePath){
	        uploadElement.sendKeys(filePath);

	        //  "I accept the terms of service"
	        driver.findElement(By.id("terms")).click();

	        driver.findElement(By.name("upload")).click();
	 }
	
	public Wait fluentWait() { 
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(20)) 
				.pollingEvery(Duration.ofSeconds(1)) 
				.ignoring(NoSuchElementException.class); 
		
		return wait; }
	
	public void waitAndClick(WebElement elementToClick){
		fluentWait().until(ExpectedConditions.visibilityOf(elementToClick));
	    fluentWait().until(ExpectedConditions.elementToBeClickable(elementToClick));
	    elementToClick.click();
	}
	
	public void scrollTo(WebElement elementToScroll){
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToScroll);
	}
	
	void scrollToBottom(WebDriver driver)
    {
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
    }

void scrollToTop(WebDriver driver)
    {
	   // ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,0);"); robi to samo, ale niektóre strony źle na niego reagują
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.HOME).build().perform();
        
    }

public void scrollAndClick(WebElement scrollElement){

	Actions builder = new Actions(driver);
	builder.moveToElement(scrollElement).click().build().perform();

	}

void scrollToMiddle(WebDriver driver)
    {
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight/2,document.body.scrollHeight,document.documentElement.clientHeight/2));");
    }
	
	public void hover(WebElement hoverElement){

		Actions builder = new Actions(driver);
		builder.moveToElement(hoverElement).build().perform();

		}
	
	
	

}
