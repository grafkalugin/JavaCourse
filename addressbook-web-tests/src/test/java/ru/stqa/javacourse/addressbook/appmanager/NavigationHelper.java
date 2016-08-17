package ru.stqa.javacourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

	public NavigationHelper(WebDriver wd) {
		super(wd);
	}

	public void goToContactCreation() {
		goWithChechUrl(By.linkText("add new"), "http://localhost/addressbook/edit.php");
	}

	public void groupPage() {
		goWithChechUrl(By.linkText("groups"), "http://localhost/addressbook/group.php");
	}

	public void goHome() {
		goWithChechUrl(By.linkText("home"), "http://localhost/addressbook/index.php");
	}

}
