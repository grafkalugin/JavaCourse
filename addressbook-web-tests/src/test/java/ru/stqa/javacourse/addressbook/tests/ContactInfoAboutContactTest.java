package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactInfoAboutContactTest extends TestBase{

	@BeforeMethod
	public void createContactBeforeTestInfoAboutContact(){
		app.goTo().goHome();
			app.goTo().goToContactCreation();
			app.contact()
					.createContact(new ContactData()
							.withId(0).withFirstname("firstname")
							.withLastname("lastname")
							.withAddress("address")
							.withWorkPhone("89990009911")
							.withMobilePhone("89990009922")
							.withHomePhone("899900099833")
							.withEmail("1email@test.ru")
							.withEmail2("2email@test.ru")
							.withEmail3("3email@test.ru"));
	}
	@Test
	 public void testContactInfoAboutContactTest(){
		 ContactData contact = app.contact().allContacts().iterator().next();
		 ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);
		 ContactData contactInfoFromInfoPage = app.contact().infoFromInfoPage(contact);
		 //System.out.println(mergeAllDataFromEditForm(contactInfoFormEditForm));
		 //System.out.println(cleanedDataFromInfoPage(contactInfoFromInfoPage.getAllDataFromInfoPage()));
		 assertThat(mergeAllDataFromEditForm(contactInfoFormEditForm), equalTo(cleanedDataFromInfoPage(contactInfoFromInfoPage.getAllDataFromInfoPage())));


	}
	private String mergeAllDataFromEditForm(ContactData contact) {

		return Arrays.asList(contact.getFirstname(),
				contact.getLastname(),
				contact.getAddress(),
				contact.getHomePhone(),
				contact.getMobilePhone(),
				contact.getWorkPhone(),
				contact.getEmail(),
				contact.getEmail2(),
				contact.getEmail3())
				.stream()
				.filter((s) -> ! s.equals(""))
				.map(ContactPhoneTest::cleanedEmail)
				.collect(Collectors.joining(""));
	}

	public static String cleanedDataFromInfoPage (String data){
		return data.replaceAll("\\s", "").replaceAll("\n", "").replaceAll("\\([a-z0-9_.-]*\\)", "").replaceAll("H:", "").replaceAll("M:", "").replaceAll("W:", ""); // регулярные выражения \\s - пробельные символы  // [a-z] все буквы от и до // [-az] заменить эти символы
	}



}
