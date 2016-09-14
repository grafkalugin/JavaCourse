package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;
import ru.stqa.javacourse.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {


	@BeforeMethod(enabled = false)
	public void ensurePreconditionContacts(){
		File photo = new File("src/test/resources/stru.png");
		app.goTo().goHome();
		if(! app.contact().isThereAContact()){
			app.goTo().goToContactCreation();
			app.contact().createContact(new ContactData().withId(0).withFirstname("firstname").withLastname("lastname").withMiddlename("middlename").withNickname("nickname").withAddress("address").withPhone("89990009988").withEmail("email@test.ru").withGroup("group name").withPhoto(photo));
		}
	}

	@BeforeMethod
	public void ensurePreconditionContactsDb(){
		File photo = new File("src/test/resources/stru.png");
		app.goTo().goHome();
		if(app.db().contacts().size() == 0){
			app.goTo().goToContactCreation();
			app.contact().createContact(new ContactData().withId(0).withFirstname("firstname").withLastname("lastname").withMiddlename("middlename").withNickname("nickname").withAddress("address").withPhone("89990009988").withEmail("email@test.ru").withGroup("group name").withPhoto(photo));

		}
	}

	@Test(enabled = false)
	public void testContactModification() { // Тест модификации контактов без прямого использования DB
		File photo = new File("src/test/resources/stru.png");
		app.goTo().goHome();
		Contacts before = app.contact().allContacts();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData()
				.withId(modifiedContact.getId())
				.withFirstname("firstname")
				.withLastname("lastname")
				.withMiddlename("middlename")
				.withNickname("nickname")
				.withAddress("address")
				.withPhone("89990009988")
				.withEmail("email@test.ru")
				.withPhoto(photo);
		app.contact().modifiContact(modifiedContact, contact);
		app.goTo().goHome();
		assertThat(app.contact().count(), equalTo(before.size()));
		Contacts after = app.contact().allContacts();
		//before.remove(modifiedContact);
		//before.add(contact);
		assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
	}
	@Test
	public void testContactModificationDb() { // Тест модификации контактов с прямым использованием Db
		File photo = new File("src/test/resources/stru.png");
		app.goTo().goHome();
		Contacts before = app.db().contacts();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData()
				.withId(modifiedContact.getId())
				.withFirstname("firstname")
				.withLastname("lastname")
				.withMiddlename("middlename")
				.withNickname("nickname")
				.withAddress("address")
				.withPhone("89990009988")
				.withEmail("email@test.ru")
				.withPhoto(photo);;
		app.contact().modifiContact(modifiedContact, contact);
		app.goTo().goHome();
		assertThat(app.contact().count(), equalTo(before.size()));
		Contacts after = app.db().contacts();
		//before.remove(modifiedContact);
		//before.add(contact);
		assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
	}
}
