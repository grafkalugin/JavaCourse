package ru.stqa.javacourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

/**
 * Created by Sunlab on 24.07.16.
 */
public class HelperBase {
	protected WebDriver wd;

	public HelperBase(WebDriver wd) {
		this.wd = wd;
	}

	protected void click(By locator) {
		wd.findElement(locator).click();
	}

	protected void type(By locator, String text) {
		click(locator);
		wd.findElement(locator).clear();
		wd.findElement(locator).sendKeys(text);
	}
	protected boolean isSelected(By locator) {
		return wd.findElement(locator).isSelected();
	}
	public boolean isAlertPresent() {
		try {
			wd.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	protected void submitAlert() {
		wd.switchTo().alert().accept();
	}
}
