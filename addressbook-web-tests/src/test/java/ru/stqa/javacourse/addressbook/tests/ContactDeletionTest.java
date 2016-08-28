package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;
import ru.stqa.javacourse.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTest extends TestBase{
	@Test (enabled = false)
	public void testContactDeletion1() {
		app.goTo().goHome();
		app.contact().initModifOrDelet(1);
		app.contact().deleteSubmit();
	}
	@Test //(enabled = false)
	public void testContactDeletion2() {
		app.goTo().goHome();
		if(! app.contact().isThereAContact()){
			app.goTo().goToContactCreation();
			app.contact()
					.createContact(new ContactData()
							.withId(0).withFirstname("firstname")
							.withLastname("lastname")
							.withMiddlename("middlename")
							.withNickname("nickname")
							.withAddress("address")
							.withPhone("89990009988")
							.withEmail("email@test.ru")
							.withGroup("group name"));
		}

		Contacts before = app.contact().getContactList();
		ContactData deletedContact = before.iterator().next();
		app.contact().delete(deletedContact);
		app.goTo().goHome();
		assertThat(app.contact().count(), equalTo(before.size() - 1));
		Contacts after = app.contact().getContactList();
		assertThat(after, equalTo(before.without(deletedContact)));
	}
}
