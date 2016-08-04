package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;


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
			app.getContactHelper().createContact(new ContactData("firstname", "middlename", "lastname", "nickname", "address", "89990009988", "email@test.ru", "group name"), true);
		}
		app.getContactHelper().clickOnFirstContact();
		app.getContactHelper().deleteOnHomeSubmit();
		app.getContactHelper().submitOnAlert();
	}
}
