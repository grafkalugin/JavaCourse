package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;


public class GroupModificationTest extends TestBase {
	@Test
	public void testGroupModification() {
		app.getNavigationHelper().goToGroup();
		if(! app.getGroupHelper().isThereAGroup()){
			app.getGroupHelper().createGroup(new GroupData("group name", "Group header ", "Group footer "));
		}
		app.getGroupHelper().initGroupModification();
		app.getGroupHelper().fillGroupForm(new GroupData("test3", "test4", "test5"));
		app.getGroupHelper().submitGroupModification();
		app.getGroupHelper().returnToGroupPage();
	}
}
