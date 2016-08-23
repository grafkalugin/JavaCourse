package ru.stqa.javacourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
	    GroupData group = new GroupData().withName("group name 2");
	    app.goTo().groupPage();
	    Set<GroupData> before = app.group().all();
	    app.group().create(group);
	    Set<GroupData> after = app.group().all();
	    Assert.assertEquals(after.size(), before.size() + 1);
		group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
	    before.add(group);
	    Assert.assertEquals(before, after);
    }
}
