package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
	    app.getNavigationHelper().goToGroup();
	    app.getGroupHelper().initGroupCreation();
	    app.getGroupHelper().fillGroupForm(new GroupData("group name", "Group header ", "Group footer "));
	    app.getGroupHelper().submitGroupCreation();
	    app.getGroupHelper().returnToGroupPage();
    }
}
