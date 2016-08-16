package ru.stqa.javacourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.javacourse.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;


public class GroupHelper extends HelperBase  {

	public GroupHelper(WebDriver wd) {
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

	public void selectGroup(int i) {
	    // устаревшая реализация
		// if (! isSelected(By.name("selected[]"))) {
	    //    click(By.name("selected[]"));
		    wd.findElements(By.name("selected[]")).get(i).click();
	}

	public void initGroupModification() {
		click(By.name("edit"));
	}

	public void submitGroupModification() {
		click(By.name("update"));
	}

	public void createGroup(GroupData group) {
		initGroupCreation();
		fillGroupForm(group);
		submitGroupCreation();
		returnToGroupPage();
	}

	public boolean  isThereAGroup() {
		return isElementPresent(By.name("selected[]"));
	}

	public int getGroupCount() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public List<GroupData> getGroupList() {
		List<GroupData> groups = new ArrayList<GroupData>();
		List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
		for(WebElement element: elements){
			String name = element.getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			GroupData group = new GroupData(id, name, null, null);
			groups.add(group);
		}
		return groups;
	}
	public void modifyGroup(int index, GroupData group) {
		selectGroup(index);
		initGroupModification();
		fillGroupForm(group);
		submitGroupModification();
		returnToGroupPage();
	}
}
