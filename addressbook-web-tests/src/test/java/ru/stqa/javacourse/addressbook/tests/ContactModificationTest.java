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
		if(! app.getContactHelper().isThereAContact()){
			app.goTo().goToContactCreation();
			app.getContactHelper().createContact(new ContactData().withId(0).withFirstname("firstname").withLastname("lastname").withMiddlename("middlename").withNickname("nickname").withAddress("address").withPhone("89990009988").withEmail("email@test.ru").withGroup("group name"));
		}
		Contacts before = app.getContactHelper().getContactList();
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
		app.getContactHelper().modifiContact(modifiedContact, contact);
		app.goTo().goHome();
		Contacts after = app.getContactHelper().getContactList();
		assertThat(after.size(), equalTo(before.size()));
		//before.remove(modifiedContact);
		//before.add(contact);
		//System.out.println(after.size()+ " and " + before.size());
		assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
	}
}
