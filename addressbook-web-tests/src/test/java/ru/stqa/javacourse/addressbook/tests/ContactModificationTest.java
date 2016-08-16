package ru.stqa.javacourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

	@Test (enabled = false)
	public void testContactModification() {
		app.getNavigationHelper().goHome();
		if(! app.getContactHelper().isThereAContact()){
			app.getNavigationHelper().goToContactCreation();
			app.getContactHelper().createContact(new ContactData(0, "firstname", "lastname", "middlename","nickname", "address", "89990009988", "email@test.ru", "group name"));
		}
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().initModifOrDelet(before.size());
		ContactData contact = new ContactData(0, "firstname", "lastname", "middlename", "nickname", "address", "89990009988", "email@test.ru", null);
		app.getContactHelper().fillContactForm(contact, false);
		app.getContactHelper().updateSubmit();
		app.getNavigationHelper().goHome();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size());

		before.remove(before.size() - 1);
		before.add(contact);
		Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		System.out.println(after.size()+" and "+before.size());
		Assert.assertEquals(before, after);
	}
}
