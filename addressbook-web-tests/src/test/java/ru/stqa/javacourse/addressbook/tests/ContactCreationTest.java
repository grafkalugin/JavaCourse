package ru.stqa.javacourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

	@Test
    public void testContactCreation() {
		app.getNavigationHelper().goHome();
		List<ContactData> before = app.getContactHelper().getContactList();
	    app.getNavigationHelper().goToContactCreation();
		ContactData contactdata = new ContactData(Integer.MAX_VALUE, "firstname", "lastname", "middlename", "nickname", "address", "89990009988", "email@test.ru", "group name");
		app.getContactHelper().createContact(contactdata);
		List<ContactData> after = app.getContactHelper().getContactList();
		before.add(contactdata);
		Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);

	}

}