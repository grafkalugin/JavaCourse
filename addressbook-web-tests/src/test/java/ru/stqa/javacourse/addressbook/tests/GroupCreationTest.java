package ru.stqa.javacourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
	    app.getNavigationHelper().goToGroup();
	    int before = app.getGroupHelper().getGroupCount();
	    app.getGroupHelper().createGroup(new GroupData("group name", "Group header ", "Group footer "));
	    int after = app.getGroupHelper().getGroupCount();
	    Assert.assertEquals(after, before + 1);
    }
}
