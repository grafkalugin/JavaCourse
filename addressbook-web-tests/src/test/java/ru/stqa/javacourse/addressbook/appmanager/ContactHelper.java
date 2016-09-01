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
		type(By.name("mobile"), contactData.getMobilePhone());
		type(By.name("home"), contactData.getHomePhone());
		type(By.name("work"), contactData.getWorkPhone());
		type(By.name("email"), contactData.getEmail());
		type(By.name("email2"), contactData.getEmail2());
		type(By.name("email3"), contactData.getEmail3());
		if(creation && contactData.getGroup() != null){
			new Select(wd.findElement(By.name("group name"))).selectByVisibleText(contactData.getGroup());
		}
		else{
			Assert.assertFalse(isElementPresent(By.name("group name")));
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
		contactCache = null;
		returnToContactPage();
	}

	private Contacts contactCache = null;

	public Contacts allContacts() {
		if(contactCache != null){
			return new Contacts(contactCache);
		}
		contactCache = new Contacts();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for(WebElement element: elements){
			List<WebElement> cells = element.findElements(By.tagName("td"));
			String lastname =  cells.get(1).getText();   //(By.xpath("//td[2]")).getText();
			String firstname = cells.get(2).getText();  //(By.xpath("//td[3]")).getText();
			String allEmails = cells.get(4).getText();
			String address = cells.get(3).getText();
			// прямая проверка String[] phones = cells.get(5).getText().split("\n"); - для прямой проверки
			String allPhones = cells.get(5).getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			// прямая проверка contactCache.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname).withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
			contactCache.add(new ContactData()
					.withId(id)
					.withLastname(lastname)
					.withFirstname(firstname)
					.withAddress(address)
					.withAllEmails(allEmails)
					.withAllPhones(allPhones));
		}
		return new Contacts((contactCache));
	}
	public void delete(ContactData contact) {
		selectContactById(contact.getId());
		deleteOnHomeSubmit();
		submitOnAlert();
		contactCache = null;
	}

	private void selectContactById(int id) {
			wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
	}

	public void modifiContact(ContactData modifiContact, ContactData contact) {
		selectContactById(modifiContact.getId());
		initModifOrDelet(modifiContact);
		fillContactForm(contact, false);
		updateSubmit();
		contactCache = null;
	}
	public int count() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public ContactData infoFormEditForm(ContactData contact) {
		initContactModificationById(contact.getId());
		String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
		String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
		String home = wd.findElement(By.name("home")).getAttribute("value");
		String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
		String work = wd.findElement(By.name("work")).getAttribute("value");
		String email = wd.findElement(By.name("email")).getAttribute("value");
		String email2 = wd.findElement(By.name("email2")).getAttribute("value");
		String email3 = wd.findElement(By.name("email3")).getAttribute("value");
		String address = wd.findElement(By.name("address")).getText();
		wd.navigate().back();
		return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
	}

	private void initContactModificationById(int id) {
		WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id))); // находим чек
		WebElement row = checkbox.findElement(By.xpath("./../..")); // переход к родительскому элементу (2) //  первая точка - поиск начинается с текущего элемента
		List<WebElement> cells2 = row.findElements(By.tagName("td"));
		cells2.get(7).findElement(By.tagName("a")).click();

		//wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click(); // xpath - нумерация начинается с 1 // String.format - подставляет произвольное кол-во значений, указываются через запятую после id
		//wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click(); Найти tr у которой input-value (подзапрос начинается с точки) - в ней 8ую ячейку и ссылку
		//wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click(); // String.format("I like %s and %s", "apples", "oranges")
	}
/*
	public ContactData infoFromInfoPage(ContactData contact) {
		openContactInfoById(contact.getId());
		String firstAndLastName = wd.findElement(By.cssSelector("#content b")).getText();
		String home = wd.findElements(By.cssSelector("#content br")).get(2).getText();
		String mobile = wd.findElements(By.cssSelector("#content br")).get(3).getText();
		String work = wd.findElements(By.cssSelector("#content br")).get(4).getText();
		String allPhones = home + mobile + work;
		String address = wd.findElements(By.cssSelector("#content br")).get(0).getText();
		List<WebElement> allEmails = wd.findElements(By.xpath("//a[contains(@href,'mailto')]")); // [href='mailto:qwerty@wertyu.ru']
		return new ContactData().withId(contact.getId()).withFirstAndLastName(firstAndLastName).withAllPhones(allPhones).withAddress(address); //.withAllEmails(allEmails)
	}
*/

	public ContactData infoFromInfoPage(ContactData contact) {
		openContactInfoById(contact.getId());
		String allDataFromInfoPage = wd.findElement(By.id("content")).getText();
		return new ContactData().withAllDataFromInfoPage(allDataFromInfoPage);
	}


	private void openContactInfoById(int id) {
		WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id))); // находим чек
		WebElement row = checkbox.findElement(By.xpath("./../..")); // переход к родительскому элементу (2) //  первая точка - поиск начинается с текущего элемента
		List<WebElement> cells3 = row.findElements(By.tagName("td"));
		cells3.get(6).findElement(By.tagName("a")).click();
	}
}
