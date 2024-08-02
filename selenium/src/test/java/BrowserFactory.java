

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class BrowserFactory {

private static final String driverGecko_patch = "E:/Eclipse/eclipse/workspace/selenium/src/test/java/resources/geckodriver.exe";
private static final String driverCH_patch = "E:/Eclipse/eclipse/workspace/selenium/src/test/java/resources/chromedriver.exe"; 

//private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();


public static WebDriver getBrowser(String BrowserName){
    WebDriver driver = null;

    switch (BrowserName){
        case "GE" :
        {
                System.setProperty("webdriver.gecko.driver", driverGecko_patch);
              // ProfilesIni profile = new ProfilesIni();
             // FirefoxProfile myprofile = profile.getProfile("default");
                driver = new FirefoxDriver();
            break;
        }
        case "CH" :
        {
        	System.out.println(System.getProperty("user.dir"));
        	System.setProperty("webdriver.chrome.driver", driverCH_patch);
                ChromeOptions options = new ChromeOptions();
                
                /* cheat sheet :)
                options.addExtensions(new File("/path/to/extension.crx"));
                Proxy proxy = new Proxy();
                proxy.setHttpProxy("myhttpproxy:3337");
                options.setCapability("proxy", proxy);           
     			options.addArgument("incognito");
       			options.addArgument("headless");
         		options.addArgument("disable-extensions");
           		options.addArgument("disable-popup-blocking");
             	options.addArgument("make-default-browser");
               	options.addArgument("version");
                options.addArgument("disable-infobars");
                */
                
                options.addArguments("test-type","--start-maximized");
                options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                options.setCapability("chrome.binary", "driverCH_patch");
                driver = new ChromeDriver(options);
                
            break;
        }
    }
    return driver;
}

/*
    public static void closeBrowser(){
      try {
        for (String key : drivers.keySet()) {
            drivers.get(key).close();
            drivers.get(key).quit();
            drivers.remove(key);
        }
      }catch(Exception e){
        //...
     }
}
*/
}
