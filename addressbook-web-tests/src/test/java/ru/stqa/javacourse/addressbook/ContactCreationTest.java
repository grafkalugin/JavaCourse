package ru.stqa.javacourse.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

	@Test
    public void testContactCreation() {

	    initContactCreation();
	    fillContactForm(new ContactData("firstname", "middlename", "lastname", "nickname", "address", "89990009988", "email@test.ru"));
	    submitContactCreation();
	    returnToContactPage();
    }

}