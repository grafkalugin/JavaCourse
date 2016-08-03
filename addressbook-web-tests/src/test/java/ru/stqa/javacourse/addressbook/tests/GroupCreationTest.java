package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
	    app.getNavigationHelper().goToGroup();
	    app.getGroupHelper().createGroup(new GroupData("group name", "Group header ", "Group footer "));
    }
}
