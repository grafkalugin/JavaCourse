package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;
import ru.stqa.javacourse.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTest extends TestBase{
	@Test//(enabled = false)
	public void testContactDeletion1() {
		app.goTo().goHome();
		app.getContactHelper().initModifOrDelet(1);
		app.getContactHelper().deleteSubmit();
	}
	@Test //(enabled = false)
	public void testContactDeletion2() {
		app.goTo().goHome();
		if(! app.getContactHelper().isThereAContact()){
			app.goTo().goToContactCreation();
			app.getContactHelper()
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

		Contacts before = app.getContactHelper().getContactList();
		ContactData deletedContact = before.iterator().next();
		app.getContactHelper().delete(deletedContact);
		app.goTo().goHome();
		Contacts after = app.getContactHelper().getContactList();
		assertThat(after.size(), equalTo(before.size() - 1));
		assertThat(after, equalTo(before.without(deletedContact)));
	}
}
