package ru.stqa.javacourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.javacourse.addressbook.model.ContactData;

/**
 * Created by Sunlab on 24.07.16.
 */
public class ContactHelper extends HelperBase{

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void returnToContactPage() {
		goWithChechUrl(By.linkText("home page"), "http://localhost/addressbook/index.php");
	}

	public void submitContactCreation() {
		click(By.xpath("//div[@id='content']/form/input[21]"));
	}

	public void fillContactForm(ContactData contactData, boolean creation) {
		type(By.name("firstname"), contactData.getFirstname());
		type(By.name("middlename"), contactData.getMiddlename());
		type(By.name("lastname"), contactData.getLastname());
		type(By.name("nickname"), contactData.getNickname());
		type(By.name("address"), contactData.getAddress());
		type(By.name("mobile"), contactData.getPhone());
		type(By.name("email"), contactData.getEmail());
		if(creation){
			new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
		}
		else{
			Assert.assertFalse(isElementPresent(By.name("new_group")));
		}
	}

	public void initModifOrDelet() {
		click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
	}

	public void updateSubmit() {
		click(By.xpath("//div[@id='content']/form[1]/input[22]"));
	}

	public void deleteSubmit() {
		click(By.xpath("//div[@id='content']/form[2]/input[2]"));
	}

	public void clickOnFirstContact() {
		click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
	}

	public void deleteOnHomeSubmit() {
		click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
	}

	public void submitOnAlert() {
		submitAlert();
	}

}
