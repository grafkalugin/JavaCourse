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
		app.getNavigationHelper().goToGroup();
		if(! app.getGroupHelper().isThereAGroup()){
			app.getGroupHelper().createGroup(new GroupData("group name", "Group header ", "Group footer "));
		}
	}

	@Test
	public void testGroupModification() {

		List<GroupData> before = app.getGroupHelper().getGroupList();
		int index = before.size() - 1;
		GroupData group = new GroupData(before.get(index).getId(), "test3", "test4", "test5");
		app.getGroupHelper().modifyGroup(index, group);
		List<GroupData> after = app.getGroupHelper().getGroupList();
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
