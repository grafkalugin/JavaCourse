package ru.stqa.javacourse.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
	private String browser;
	WebDriver wd;
	private ContactHelper contactHelper;
	private  NavigationHelper navigationHelper;
	private GroupHelper groupHelper;
	private SessionHelper sessionHelper;

	public ApplicationManager(String browser) {
		this.browser = browser;
	}

	public void init() {
		if (browser.equals(BrowserType.FIREFOX)){
			wd = new FirefoxDriver();
		}
		else  if (browser.equals(BrowserType.CHROME)){
			wd = new ChromeDriver();
		}
		else if(browser.equals(BrowserType.SAFARI)){
			wd = new SafariDriver();
		}
		else if(browser.equals(BrowserType.IE)){
			wd = new InternetExplorerDriver();
		}
		else if(browser.equals(BrowserType.OPERA_BLINK)){ // -Dwebdriver.opera.driver=operadriver  Run/Debug Configurations - JDK Settings - VM Options
			wd = new OperaDriver();
		}
		if (browser == BrowserType.EDGE){
			wd = new EdgeDriver();
		}
		if (browser == BrowserType.HTMLUNIT){
			wd = new HtmlUnitDriver();
		}
/*	  	if (browser == BrowserType.IPHONE){
			wd = new AppiumDriver();
		}
*/
  		wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook");
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		contactHelper = new ContactHelper(wd);
		sessionHelper = new SessionHelper(wd);
		sessionHelper.login("admin", "secret");
	}

	public void stop() {
		wd.quit();
	}

	public GroupHelper group() {
		return groupHelper;
	}

	public NavigationHelper goTo() {
		return navigationHelper;
	}

	public ContactHelper contact() {
		return contactHelper;
	}
}
