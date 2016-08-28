package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;
import ru.stqa.javacourse.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
	    GroupData group = new GroupData().withName("group name 2");
	    app.goTo().groupPage();
	    Groups before = app.group().all();
	    app.group().create(group);
	    Groups after = app.group().all();
	    assertThat(after.size(), equalTo(before.size() + 1));
	    assertThat(after, equalTo(
			    before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
