package ru.stqa.javacourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.javacourse.addressbook.model.GroupData;


public class GroupHelper extends HelperBase  {

	public GroupHelper(FirefoxDriver wd) {
	super(wd);
	}

	public void returnToGroupPage() {
		click(By.linkText("group page"));
	}

	public void submitGroupCreation() {
		click(By.name("submit"));
	}

	public void fillGroupForm(GroupData groupData) {
		type(By.name("group_name"),groupData.getName());
		click(By.xpath("//div[@id='content']//label[.='Group header (Logo):']"));
		type(By.name("group_header"), groupData.getHeder());
		click(By.xpath("//div[@id='content']//label[.='Group footer (Comment):']"));
		type(By.name("group_footer"), groupData.getFooter());
	}

	public void initGroupCreation() {
		click(By.name("new"));
	}

	public void deleteSelectedGroups() {
	    click(By.name("delete"));
	}

	public void selectGroup() {
	    if (!isSelected(By.name("selected[]"))) {
	        click(By.name("selected[]"));
	    }
	}

	public void initGroupModification() {
		click(By.name("edit"));
	}

	public void submitGroupModification() {
		click(By.name("update"));
	}
}
