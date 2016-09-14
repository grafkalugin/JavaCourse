package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;
import ru.stqa.javacourse.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTest extends TestBase{

	@BeforeMethod(enabled = false)
	public void ensurePreconditionContacts(){ // реализация через web interface
		File photo = new File("src/test/resources/stru.png");
		app.goTo().goHome();
		if(! app.contact().isThereAContact()){
			app.goTo().goToContactCreation();
			app.contact().createContact(new ContactData().withId(0).withFirstname("firstname").withLastname("lastname").withMiddlename("middlename").withNickname("nickname").withAddress("address").withPhone("89990009988").withEmail("email@test.ru").withGroup("group name").withPhoto(photo));
		}
	}

	@BeforeMethod
	public void ensurePreconditionContactsDb(){ // реализация через Db
		File photo = new File("src/test/resources/stru.png");
		app.goTo().goHome();
		if(app.db().contacts().size() == 0){
			app.goTo().goToContactCreation();
			app.contact().createContact(new ContactData().withId(0).withFirstname("firstname").withLastname("lastname").withMiddlename("middlename").withNickname("nickname").withAddress("address").withPhone("89990009988").withEmail("email@test.ru").withGroup("group name").withPhoto(photo));

		}
	}

	@Test (enabled = false)
	public void testContactDeletion1() {
		app.goTo().goHome();
		app.contact().initModifOrDelet(1);
		app.contact().deleteSubmit();
	}

	@Test
	public void testContactDeletion2() {
		app.goTo().goHome();
		Contacts before = app.db().contacts();
		ContactData deletedContact = before.iterator().next();
		app.contact().delete(deletedContact);
		app.goTo().goHome();
		assertThat(app.contact().count(), equalTo(before.size() - 1));
		Contacts after = app.db().contacts();
		assertThat(after, equalTo(before.without(deletedContact)));
	}
}
