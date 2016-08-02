package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

	@Test
	public void testContactModification() {
		app.getNavigationHelper().goHome();
		app.getContactHelper().initModifOrDelet();
		app.getContactHelper().fillContactForm(new ContactData("firstname", "middlename", "lastname", "nickname", "address", "89990009988", "email@test.ru", null), false);
		app.getContactHelper().updateSubmit();
	}
}
