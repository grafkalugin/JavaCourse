package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

	@Test
    public void testContactCreation() {

	    app.getNavigationHelper().goToContactCreation();
	    app.getContactHelper().fillContactForm(new ContactData("firstname", "middlename", "lastname", "nickname", "address", "89990009988", "email@test.ru"));
	    app.getContactHelper().submitContactCreation();
	    app.getContactHelper().returnToContactPage();
    }

}