package ru.stqa.javacourse.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;
import ru.stqa.javacourse.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

	@DataProvider
	public Iterator<Object[]> validGroupsFromXml() throws IOException {
		//List<Object[]> list = new ArrayList<Object[]>(); // не нужен в xml
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml"))) // нужно менять расширение файла
		)   {
			String xml = "";
			String line = reader.readLine();
			while (line != null) {
				//String[] split = line.split(";"); // нужен для csv
				//list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])}); // нужен для csv
				xml += line;
				line = reader.readLine();
			}
			XStream xStream = new XStream();
			xStream.processAnnotations(GroupData.class); // обработка аннотаций
			List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml); // в скобках идёт приведение типа
			return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator(); // map к каждому объекту применяет функцию, которая заворачивает его в массив состоящий из этого объекта // collect из потока собирает список // и у списка берем итератор
			//return list.iterator(); - не нужно в xml
		}
	}

	@DataProvider
	public Iterator<Object[]> validGroupsFromJson() throws IOException {
		//List<Object[]> list = new ArrayList<Object[]>(); // не нужен в xml
		try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json"))); // нужно менять расширение файла
		)   {
			String json = "";
			String line = reader.readLine();
			while (line != null){
				//String[] split = line.split(";"); // нужен для csv
				//list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])}); // нужен для csv
				json += line;
				line = reader.readLine();
			}
			Gson gson = new Gson();
			List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType()); // десериализация json // List<GroupData>.class
			return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator(); // map к каждому объекту применяет функцию, которая заворачивает его в массив состоящий из этого объекта // collect из потока собирает список // и у списка берем итератор
		}
	}

    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData group) {
	    //GroupData group = new GroupData().withName("group name");
	    app.goTo().groupPage();
	    Groups before = app.group().all();
	    app.group().create(group);
	    Groups after = app.group().all();
	    assertThat(after.size(), equalTo(before.size() + 1));
	    assertThat(after, equalTo(
			    before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

	@Test
	public void testBadGroupCreation() {
		GroupData group = new GroupData().withName("group name 2'");
		app.goTo().groupPage();
		Groups before = app.db().groups();
		app.group().create(group);
		assertThat(app.group().count(), equalTo(before.size()));
		Groups after = app.db().groups();
		assertThat(after, equalTo(before));
	}
}
