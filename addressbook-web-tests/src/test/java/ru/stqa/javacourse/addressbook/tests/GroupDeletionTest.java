package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {
    
    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroup();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }
}
