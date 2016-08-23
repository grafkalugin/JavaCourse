package ru.stqa.javacourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;

import java.util.Set;


public class GroupModificationTest extends TestBase {
	@BeforeMethod
	public void ensurePrecondition(){
		app.goTo().groupPage();
		//if(! app.group().isThereAGroup()){
		if(app.group().all().size() == 0){
			app.group().create(new GroupData().withName("group name"));
		}
	}

	@Test
	public void testGroupModification() {

		Set<GroupData> before = app.group().all();
		GroupData modifiedGroup = before.iterator().next();
		GroupData group = new GroupData()
				.withId(modifiedGroup.getId()).withName("test3").withHeader("test4").withFooter("test5");
		app.group().modify(group);
		Set<GroupData> after = app.group().all();
		Assert.assertEquals(after.size(), before.size());

		before.remove(modifiedGroup);
		before.add(group);
		//Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); // сравнение списков преобразованных в множества
		Assert.assertEquals(before, after);
	}

}
