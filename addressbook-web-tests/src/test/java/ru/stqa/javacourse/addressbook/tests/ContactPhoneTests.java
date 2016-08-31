package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactPhoneTests extends TestBase{

	@Test
	public void testContactPhones(){
		app.goTo().goHome();

		if(! app.contact().isThereAContact()){
			app.goTo().goToContactCreation();
			app.contact()
					.createContact(new ContactData()
							.withId(0).withFirstname("firstname")
							.withLastname("lastname")
							.withMiddlename("middlename")
							.withNickname("nickname")
							.withAddress("address")
							.withWorkPhone("89990009911")
							.withMobilePhone("89990009922")
							.withHomePhone("899900099833")
							.withEmail("1email@test.ru")
							.withEmail2("2email@test.ru")
							.withEmail3("3email@test.ru")
							.withGroup("group name"));
		}

		ContactData contact = app.contact().allContacts().iterator().next();
		ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);
		/* прямые проверки
		assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFormEditForm.getHomePhone())));
		assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFormEditForm.getMobilePhone())));
		assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFormEditForm.getWorkPhone())));
		*/
		/* --- testing
		System.out.println(contact.getAllPhones());
		System.out.println(mergePhones(contactInfoFormEditForm));
		System.out.println(contact.getAllEmails());
		System.out.println(mergeEmail(contactInfoFormEditForm));
		System.out.println(contact.getAddress());
		System.out.println(contactInfoFormEditForm.getAddress());
		*/
		assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFormEditForm)));
		assertThat(contact.getAllEmails(), equalTo(mergeEmail(contactInfoFormEditForm)));
		assertThat(contact.getAddress(), equalTo(contactInfoFormEditForm.getAddress()));
	}

	private String mergeEmail(ContactData contact) {

		return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
				.stream()
				.filter((s) -> ! s.equals(""))
				.map(ContactPhoneTests::cleanedEmail)
				.collect(Collectors.joining("\n"));
	}

	private String mergePhones(ContactData contact) {

		/* Скучное склеивание
		String result = "";
		if(contact.getHomePhone() != null){
			result = result + contact.getHomePhone();
		}
		if(contact.getMobilePhone() != null && contact.getHomePhone() != null){
			result = result + "\n";
		}
		if(contact.getMobilePhone() != null){
			result = result + contact.getMobilePhone();
		}
		if((contact.getMobilePhone() != null && contact.getWorkPhone() != null) || (contact.getHomePhone() != null && contact.getWorkPhone() != null)){
			result = result + "\n";
		}
		if(contact.getWorkPhone() != null){
			result = result + contact.getWorkPhone();
		}
		return null; */

		return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
				.stream()
				.filter((s) -> ! s.equals(""))
				.map(ContactPhoneTests::cleaned)
				.collect(Collectors.joining("\n"));
	}

	public static String cleaned (String phone){
		return phone.replaceAll("\\s", "").replaceAll("[-()]", ""); // регулярные выражения \\s - пробельные символы  // [a-z] все буквы от и до // [-az] заменить эти символы
	}
	public static String cleanedEmail (String email){
		return email.replaceAll("\\s", "");
	}
}
