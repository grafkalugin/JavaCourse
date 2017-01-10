package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;
import ru.stqa.javacourse.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupModificationTest extends TestBase {

	@BeforeMethod(enabled = false) // фикстура для обычного теста
	public void ensurePrecondition(){
		app.goTo().groupPage();
		//if(! app.group().isThereAGroup()){
		if(app.group().all().size() == 0){
			app.group().create(new GroupData().withName("group name"));
		}
	}

	@BeforeMethod // фикстура для db тестов
	public void ensurePreconditionDb(){
		//if(! app.group().isThereAGroup()){
		if(app.db().groups().size() == 0){
			app.goTo().groupPage();
			app.group().create(new GroupData().withName("group name"));
		}
	}

	@Test(enabled = false) // тест работающий через web interface
	public void testGroupModification() {
		Groups before = app.group().all();
		GroupData modifiedGroup = before.iterator().next();
		GroupData group = new GroupData()
				.withId(modifiedGroup.getId())
				.withName("test6")
				.withHeader("test4")
				.withFooter("test5");
		app.group().modify(group);
		assertThat(app.group().count(), equalTo(before.size()));
		Groups after = app.group().all();
		//Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); // сравнение списков преобразованных в множества
		assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
	}

	@Test // тест работающий через Db
	public void testGroupModificationDb() {
		Groups before = app.db().groups(); // список получаем напрямую из db
		GroupData modifiedGroup = before.iterator().next();
		GroupData group = new GroupData()
				.withId(modifiedGroup.getId())
				.withName("test6")
				.withHeader("test4")
				.withFooter("test5");
		app.goTo().groupPage();
		app.group().modify(group);
		assertThat(app.group().count(), equalTo(before.size()));
		Groups after = app.db().groups(); // список получаем напрямую из db
		//Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); // сравнение списков преобразованных в множества
		assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
		verifyGroupListInUI();
	}

}
