package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;

import net.serenitybdd.core.pages.PageObject;

public class BrowserPage extends PageObject {
	
	//@Override
	/*public WebDriver driver() {
	    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		return new InternetExplorerDriver(capabilities);
	}*/
		public void launch(String url) {
			//driver().navigate().to(url);
     		openAt(url);   
     		getDriver().manage().window().maximize();
    	}

    	public String getBrowserTitle() {
     		return getTitle();
    	}
}
