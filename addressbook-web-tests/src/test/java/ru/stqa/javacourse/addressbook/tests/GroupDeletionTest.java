package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;
import ru.stqa.javacourse.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().groupPage();
        if(app.group().all().size() == 0){
            app.group().create(new GroupData().withName("group name"));
        }
    }
    
    @Test
    public void testGroupDeletion() {
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
	    assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}
