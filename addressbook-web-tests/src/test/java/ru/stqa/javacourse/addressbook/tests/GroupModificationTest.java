package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;
import ru.stqa.javacourse.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
		Groups before = app.group().all();
		GroupData modifiedGroup = before.iterator().next();
		GroupData group = new GroupData()
				.withId(modifiedGroup.getId())
				.withName("test3")
				.withHeader("test4")
				.withFooter("test5");
		app.group().modify(group);
		Groups after = app.group().all();
		//Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); // сравнение списков преобразованных в множества
		assertThat(after.size(), equalTo(before.size()));
		assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
	}
}
