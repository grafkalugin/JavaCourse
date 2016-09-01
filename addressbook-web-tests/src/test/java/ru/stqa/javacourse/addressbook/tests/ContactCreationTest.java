package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;
import ru.stqa.javacourse.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

	@Test
    public void testContactCreation() {
		app.goTo().goHome();
		Contacts before = app.contact().allContacts();
	    app.goTo().goToContactCreation();
		File photo = new File("src/test/resources/stru.png");
		ContactData contactdata = new ContactData()
				.withFirstname("firstname")
				.withLastname("lastname")
				.withMiddlename("middlename")
				.withNickname("nickname")
				.withAddress("address")
				.withPhone("89990009988")
				.withEmail("email@test.ru")
				.withGroup("group name")
				.withPhoto(photo);
		app.contact().createContact(contactdata);
		assertThat(app.contact().count(), equalTo(before.size() +  1));
		Contacts after = app.contact().allContacts();
		assertThat(after, equalTo(before.withAdded(contactdata.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
	}

	@Test (enabled = false)
	public void testCurrentDir(){
		File currentDir = new File(".");
		System.out.println(currentDir.getAbsolutePath()); // /Users/Sunlab/JavaCourse/addressbook-web-tests/.
		File photo = new File("src/test/resources/stru.png");
		System.out.println(photo.getAbsolutePath()); // получить абсолютный путь
		System.out.println(photo.exists()); // Файл существует
	}

}