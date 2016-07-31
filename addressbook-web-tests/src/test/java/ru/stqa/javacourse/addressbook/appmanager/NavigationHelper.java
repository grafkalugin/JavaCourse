package ru.stqa.javacourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

	public NavigationHelper(WebDriver wd) {
		super(wd);
	}

	public void goToContactCreation() {
		click(By.linkText("add new"));
	}

	public void goToGroup() {
		click(By.linkText("groups"));
	}

	public void goHome() {
		click(By.linkText("home"));
	}


}
