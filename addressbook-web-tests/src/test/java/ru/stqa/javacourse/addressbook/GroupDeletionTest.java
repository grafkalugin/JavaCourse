package ru.stqa.javacourse.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {
    
    @Test
    public void testGroupDeletion() {
        goToGroup();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }
}
