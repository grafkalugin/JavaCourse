package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;
import ru.stqa.javacourse.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

	@Test//(enabled = false)
    public void testContactCreation() {
		app.goTo().goHome();
		Contacts before = app.contact().getContactList();
	    app.goTo().goToContactCreation();
		ContactData contactdata = new ContactData()
				.withFirstname("firstname")
				.withLastname("lastname")
				.withMiddlename("middlename")
				.withNickname("nickname")
				.withAddress("address")
				.withPhone("89990009988")
				.withEmail("email@test.ru")
				.withGroup("group name");
		app.contact().createContact(contactdata);
		assertThat(app.contact().count(), equalTo(before.size() +  1));
		Contacts after = app.contact().getContactList();
		assertThat(after, equalTo(before.withAdded(contactdata.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

	}

}