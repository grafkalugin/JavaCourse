package ru.stqa.javacourse.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;
import ru.stqa.javacourse.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

	@DataProvider
	public Iterator<Object[]> validContactsFromJson() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json"))) // нужно менять расширение файла
		) {
			String json = "";
			String line = reader.readLine();
			while (line != null){
				json += line;
				line = reader.readLine();
			}
			Gson gson = new Gson();
			List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); // десериализация json // List<ContactData>.class
			return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator(); // map к каждому объекту применяет функцию, которая заворачивает его в массив состоящий из этого объекта // collect из потока собирает список // и у списка берем итератор
		}
	}

	@Test (dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contactdata) {
		app.goTo().goHome();
		Contacts before = app.contact().allContacts();
	    app.goTo().goToContactCreation();
		File photo = new File("src/test/resources/stru.png");
		/*
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
		*/
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