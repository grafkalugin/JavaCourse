package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;


public class ContactDeletionTest extends TestBase{
	@Test
	public void testContactDeletion1() {
		app.getNavigationHelper().goHome();
		app.getContactHelper().initModifOrDelet();
		app.getContactHelper().deleteSubmit();
	}
	@Test
	public void testContactDeletion2() {
		app.getNavigationHelper().goHome();
		app.getContactHelper().clickOnFirstContact();
		app.getContactHelper().deleteOnHomeSubmit();
		app.getContactHelper().submitOnAlert();
	}
}
