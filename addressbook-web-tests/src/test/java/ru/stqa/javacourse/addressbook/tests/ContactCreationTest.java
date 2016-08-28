package ru.stqa.javacourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTest extends TestBase {

	@Test//(enabled = false)
    public void testContactCreation() {
		app.goTo().goHome();
		Set<ContactData> before = app.getContactHelper().getContactList();
	    app.goTo().goToContactCreation();
		//ContactData contactdata = new ContactData(Integer.MAX_VALUE, "firstname", "lastname", "middlename", "nickname", "address", "89990009988", "email@test.ru", "group name");
		ContactData contactdata = new ContactData().withFirstname("firstname").withLastname("lastname").withMiddlename("middlename").withNickname("nickname").withAddress("address").withPhone("89990009988").withEmail("email@test.ru").withGroup("group name");
		app.getContactHelper().createContact(contactdata);
		Set<ContactData> after = app.getContactHelper().getContactList();
		contactdata.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
		before.add(contactdata);
		Assert.assertEquals(before, after);

	}

}