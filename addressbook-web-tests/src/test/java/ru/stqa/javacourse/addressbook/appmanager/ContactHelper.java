package ru.stqa.javacourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.javacourse.addressbook.model.ContactData;
import ru.stqa.javacourse.addressbook.model.Contacts;

import java.util.List;

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

	public void initModifOrDelet(Integer i) {
		i++;
		click(By.xpath("//table[@id='maintable']/tbody/tr["+i+"]/td[8]/a/img"));
	}
	public void initModifOrDelet(ContactData modContact) {
		click(By.cssSelector("a[href='edit.php?id=" + modContact.getId() + "']"));
	}


	public void updateSubmit() {
		click(By.xpath("//div[@id='content']/form[1]/input[22]"));
	}

	public void deleteSubmit() {
		click(By.xpath("//div[@id='content']/form[2]/input[2]"));
	}

	public void deleteOnHomeSubmit() {
		click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
	}

	public void submitOnAlert() {
		submitAlert();
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("entry"));
	}

	public void createContact(ContactData contactData) {
		fillContactForm(contactData, true);
		submitContactCreation();
		returnToContactPage();
	}

	public Contacts getContactList() {
		Contacts contacts = new Contacts();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for(WebElement element: elements){
			String lastname = element.findElement(By.xpath("//td[2]")).getText();
			String firstname = element.findElement(By.xpath("//td[3]")).getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			//ContactData contact = new ContactData(id, lastname, firstname, null, null, null, null, null, null);
			ContactData contact = new ContactData().withId(id).withLastname(lastname).withFirstname(firstname);
			contacts.add(contact);
		}
		return contacts;
	}
	public void delete(ContactData contact) {
		selectContactById(contact.getId());
		deleteOnHomeSubmit();
		submitOnAlert();
	}

	private void selectContactById(int id) {
			wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
	}
	public void modifiContact(ContactData modifiContact, ContactData contact) {
		selectContactById(modifiContact.getId());
		initModifOrDelet(modifiContact);
		fillContactForm(contact, false);
		updateSubmit();
	}
}
