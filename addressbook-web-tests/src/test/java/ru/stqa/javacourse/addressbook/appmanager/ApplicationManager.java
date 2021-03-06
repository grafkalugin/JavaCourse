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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
	private final Properties properties;
	WebDriver wd;

	private String browser;
	private ContactHelper contactHelper;
	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;
	private SessionHelper sessionHelper;
	private DbHelper dbHelper;

	public ApplicationManager(String browser) {

		this.browser = browser;
		properties = new Properties();
	}

	public void init() throws IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

		dbHelper = new DbHelper();

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
		wd.get(properties.getProperty("web.baseURL"));
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		contactHelper = new ContactHelper(wd);
		sessionHelper = new SessionHelper(wd);
		sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
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

	public DbHelper db(){ return dbHelper;}
}
