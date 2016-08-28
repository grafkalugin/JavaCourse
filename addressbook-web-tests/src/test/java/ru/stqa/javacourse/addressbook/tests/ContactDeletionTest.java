package ru.stqa.javacourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;

import java.util.Set;


public class ContactDeletionTest extends TestBase{
	@Test//(enabled = false)
	public void testContactDeletion1() {
		app.goTo().goHome();
		app.getContactHelper().initModifOrDelet(1);
		app.getContactHelper().deleteSubmit();
	}
	@Test //(enabled = false)
	public void testContactDeletion2() {
		app.goTo().goHome();
		if(! app.getContactHelper().isThereAContact()){
			app.goTo().goToContactCreation();
			app.getContactHelper()
					.createContact(new ContactData()
							.withId(0).withFirstname("firstname")
							.withLastname("lastname")
							.withMiddlename("middlename")
							.withNickname("nickname")
							.withAddress("address")
							.withPhone("89990009988")
							.withEmail("email@test.ru")
							.withGroup("group name"));
			//ContactData contactdata = new ContactData().withId(Integer.MAX_VALUE).withFirstname("firstname").withLastname("lastname").withMiddlename("middlename").withNickname("nickname").withAddress("address").withPhone("89990009988").withEmail("email@test.ru").withGroup("group name");
		}
		Set<ContactData> before = app.getContactHelper().getContactList();
		ContactData deletedContact = before.iterator().next();
		app.getContactHelper().delete(deletedContact);
		app.goTo().goHome();
		Set<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() - 1);
		before.remove(deletedContact);
		Assert.assertEquals(after, before);
	}
}
