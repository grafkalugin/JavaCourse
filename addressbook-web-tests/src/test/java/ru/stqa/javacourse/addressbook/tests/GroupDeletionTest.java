package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;

public class GroupDeletionTest extends TestBase {
    
    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroup();
        if(! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("group name", "Group header ", "Group footer "));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }
}
