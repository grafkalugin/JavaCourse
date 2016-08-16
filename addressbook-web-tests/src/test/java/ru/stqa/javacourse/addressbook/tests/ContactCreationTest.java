package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTest extends TestBase {

	@Test
    public void testContactCreation() {
		app.getNavigationHelper().goHome();
		List<ContactData> before = app.getContactHelper().getContactList();
	    app.getNavigationHelper().goToContactCreation();
		ContactData contactdata = new ContactData(0, "firstname", "lastname", "middlename", "nickname", "address", "89990009988", "email@test.ru", "group name");
		app.getContactHelper().createContact(contactdata);
		List<ContactData> after = app.getContactHelper().getContactList();
		before.add(contactdata);

	}

}