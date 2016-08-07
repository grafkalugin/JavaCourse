package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

	@Test
	public void testContactModification() {
		app.getNavigationHelper().goHome();
		if(! app.getContactHelper().isThereAContact()){
			app.getNavigationHelper().goToContactCreation();
			app.getContactHelper().createContact(new ContactData("firstname", "middlename", "lastname", "nickname", "address", "89990009988", "email@test.ru", "group name"));
		}
		//int before = app.getGroupHelper().getGroupCount();
		app.getContactHelper().initModifOrDelet();
		app.getContactHelper().fillContactForm(new ContactData("firstname", "middlename", "lastname", "nickname", "address", "89990009988", "email@test.ru", null), false);
		app.getContactHelper().updateSubmit();
		//int after = app.getGroupHelper().getGroupCount();
		//Assert.assertEquals(after, before);
	}
}
