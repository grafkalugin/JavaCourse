package ru.stqa.javacourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class GroupModificationTest extends TestBase {
	@BeforeMethod
	public void ensurePrecondition(){
		app.goTo().groupPage();
		//if(! app.group().isThereAGroup()){
		if(app.group().list().size() == 0){
			app.group().create(new GroupData().withName("group name"));
		}
	}

	@Test
	public void testGroupModification() {

		List<GroupData> before = app.group().list();
		int index = before.size() - 1;
		GroupData group = new GroupData()
				.withId(before.get(index).getId()).withName("test3").withHeader("test4").withFooter("test5");
		app.group().modify(index, group);
		List<GroupData> after = app.group().list();
		Assert.assertEquals(after.size(), before.size());

		before.remove(index);
		before.add(group);
		Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		//Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); // сравнение списков преобразованных в множества
		Assert.assertEquals(before, after);
	}

}
