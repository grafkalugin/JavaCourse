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
		if (browser == BrowserType.FIREFOX){
			wd = new FirefoxDriver();
		}
		else  if (browser == BrowserType.CHROME){
			wd = new ChromeDriver();
		}
		else if(browser == BrowserType.SAFARI){
			wd = new SafariDriver();
		}
		else if(browser == BrowserType.IE){
			wd = new InternetExplorerDriver();
		}
		else if(browser == BrowserType.OPERA_BLINK){ // -Dwebdriver.opera.driver=operadriver  Run/Debug Configurations - JDK Settings - VM Options
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
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/edit.php");
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		contactHelper = new ContactHelper(wd);
		sessionHelper = new SessionHelper(wd);
		sessionHelper.login("admin", "secret");
	}

	public void stop() {
		wd.quit();
	}

	public GroupHelper getGroupHelper() {
		return groupHelper;
	}

	public NavigationHelper getNavigationHelper() {
		return navigationHelper;
	}

	public ContactHelper getContactHelper() {
		return contactHelper;
	}
}
