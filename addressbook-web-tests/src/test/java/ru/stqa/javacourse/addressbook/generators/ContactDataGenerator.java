package ru.stqa.javacourse.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.javacourse.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

	@Parameter(names = "-c", description = "Group count")
	public int count;

	@Parameter(names = "-f", description = "Target file")
	public String file;

	@Parameter(names = "-d", description = "Data format")
	public String format;

	public static void main(String[] args) throws IOException {
		ContactDataGenerator generator = new ContactDataGenerator();
		JCommander jCommander = new JCommander(generator);

		try{
			jCommander.parse(args);
		} catch (ParameterException ex){
			jCommander.usage();
			return;
		}
		generator.run();
		//int count = Integer.parseInt(args[0]); //Удалено после добавления JCommander
		//File file = new File(args[1]); //Удалено после добавления JCommander
	}
	private void run() throws IOException {
		List<ContactData> contacts = generatorContacts(count);
		if (format.equals("json")){
			saveAsJson(contacts, new File(file));
		} else {
			System.out.println("Unrecognized format " + format);
		}

	}
	private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
		//Gson gson = new Gson(); // дефолтное форматирование
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create(); // pretty - красивое форматирование // excludeFieldsWithoutExposeAnnotation() - учёт всех необходимых полей, помеченных в классе объекте (GroupData)
		String json = gson.toJson(contacts);
		Writer writer = new FileWriter(file);
		writer.write(json);
		writer.close();
	}	private List<ContactData> generatorContacts(int count) {
		List<ContactData> contacts = new ArrayList<ContactData>();
		for(int i = 0; i < count; i++) {
			contacts.add(new ContactData()
					.withLastname(String.format("Lastname %s", i))
					.withFirstname(String.format("Firstname %s", i))
					.withAddress(String.format("Address %s", i))
					.withHomePhone(String.format("8919001010%s", i))
					.withMobilePhone(String.format("8919001020%s", i))
					.withWorkPhone(String.format("8919001030%s", i))
					.withEmail(String.format("test%s@mail.ru", i))
					.withEmail2(String.format("test%s@mail.ru", i))
					.withEmail3(String.format("test%s@mail.ru", i))
			);
		}
		return contacts;
	}


}
