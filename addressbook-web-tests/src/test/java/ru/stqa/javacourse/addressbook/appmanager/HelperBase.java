package ru.stqa.javacourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
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
	protected void typeDefault(By locator, String text) {
		click(locator);
		if(text == null)
		{
			wd.findElement(locator).clear();
			wd.findElement(locator).sendKeys(text);
		}
	}
	protected void typeNoChangeEqualsText(By locator, String text) {
		click(locator);
		if(text != null)
		{
			String existingText = wd.findElement(locator).getAttribute("value"); // отдает значение поля ввода
			if(! text.equals(existingText))
			{
				wd.findElement(locator).clear();
				wd.findElement(locator).sendKeys(text);
			}
		}
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
	public boolean isElementPresent(By by) {
		try {
			wd.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	public boolean urlIsNotEquals(String url){
		if(url.equals(wd.getCurrentUrl())){
			return false;
		}
		else {
			return true;
		}
	}

	protected void submitAlert() {
		wd.switchTo().alert().accept();
	}

	public void goWithChechUrl(By locator, String url) {
		if(urlIsNotEquals(url)){
			click(locator);}
	}
	public void goWithChechElement(By locatorClick, By locatorSearch) {
		if(! isElementPresent(locatorSearch)){
			click(locatorClick);}
	}
}
