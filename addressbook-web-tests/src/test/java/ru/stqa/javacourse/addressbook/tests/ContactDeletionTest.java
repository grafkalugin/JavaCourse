package ru.stqa.javacourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionTest extends TestBase{
	//@Test
	public void testContactDeletion1() {
		app.getNavigationHelper().goHome();
		app.getContactHelper().initModifOrDelet();
		app.getContactHelper().deleteSubmit();
	}
	@Test
	public void testContactDeletion2() {
		app.getNavigationHelper().goHome();
		if(! app.getContactHelper().isThereAContact()){
			app.getNavigationHelper().goToContactCreation();
			app.getContactHelper().createContact(new ContactData(0, "firstname", "lastname", "middlename", "nickname", "address", "89990009988", "email@test.ru", "group name"));
		}
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().clickOnFirstContact();
		app.getContactHelper().deleteOnHomeSubmit();
		app.getContactHelper().submitOnAlert();
		app.getNavigationHelper().goHome();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() - 1);
		before.remove(before.size() - 1);
		Assert.assertEquals(after, before);
	}
}
