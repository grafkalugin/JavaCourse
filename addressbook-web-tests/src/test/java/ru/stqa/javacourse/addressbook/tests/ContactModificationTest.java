package ru.stqa.javacourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;
import ru.stqa.javacourse.addressbook.model.GroupData;

import java.util.List;

public class ContactModificationTest extends TestBase {

	@Test
	public void testContactModification() {
		app.getNavigationHelper().goHome();
		if(! app.getContactHelper().isThereAContact()){
			app.getNavigationHelper().goToContactCreation();
			app.getContactHelper().createContact(new ContactData(0, "firstname", "lastname", "middlename","nickname", "address", "89990009988", "email@test.ru", "group name"));
		}
		List<GroupData> before = app.getGroupHelper().getGroupList();
		//int before = app.getGroupHelper().getGroupCount();
		app.getContactHelper().initModifOrDelet();
		app.getContactHelper().fillContactForm(new ContactData(0, "firstname", "lastname", "middlename", "nickname", "address", "89990009988", "email@test.ru", null), false);
		app.getContactHelper().updateSubmit();
		List<GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size());
	}
}
