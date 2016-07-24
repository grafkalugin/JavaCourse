package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
	    app.goToGroup();
	    app.initGroupCreation();
	    app.fillGroupForm(new GroupData("group name", "Group header ", "Group footer "));
	    app.submitGroupCreation();
	    app.returnToGroupPage();
    }
}
