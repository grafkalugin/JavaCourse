package ru.stqa.javacourse.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
	    goToGroup();
	    initGroupCreation();
	    fillGroupForm(new GroupData("group name", "Group header ", "Group footer "));
	    submitGroupCreation();
	    returnToGroupPage();
    }
}
