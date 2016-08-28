package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;
import ru.stqa.javacourse.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

	@Test//(enabled = false)
	public void testContactModification() {
		app.goTo().goHome();
		if(! app.contact().isThereAContact()){
			app.goTo().goToContactCreation();
			app.contact().createContact(new ContactData().withId(0).withFirstname("firstname").withLastname("lastname").withMiddlename("middlename").withNickname("nickname").withAddress("address").withPhone("89990009988").withEmail("email@test.ru").withGroup("group name"));
		}
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
				.withEmail("email@test.ru");
		app.contact().modifiContact(modifiedContact, contact);
		app.goTo().goHome();
		assertThat(app.contact().count(), equalTo(before.size()));
		Contacts after = app.contact().allContacts();
		//before.remove(modifiedContact);
		//before.add(contact);
		assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
	}
}
