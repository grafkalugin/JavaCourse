package ru.stqa.javacourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
	    app.getNavigationHelper().goToGroup();
	    List<GroupData> before = app.getGroupHelper().getGroupList();
	    //int before = app.getGroupHelper().getGroupCount();
	    app.getGroupHelper().createGroup(new GroupData("group name", "Group header ", "Group footer "));
	    List<GroupData> after = app.getGroupHelper().getGroupList();
	    //int after = app.getGroupHelper().getGroupCount();
	    Assert.assertEquals(after.size(), before.size() + 1);
    }
}
