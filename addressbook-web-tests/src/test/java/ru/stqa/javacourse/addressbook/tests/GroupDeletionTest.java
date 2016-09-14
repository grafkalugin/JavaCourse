package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;
import ru.stqa.javacourse.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTest extends TestBase {

    @BeforeMethod(enabled = false)
    public void ensurePrecondition(){ // web interface
        app.goTo().groupPage();
        if(app.group().all().size() == 0){
            app.group().create(new GroupData().withName("group name"));
        }
    }

    @BeforeMethod // фикстура для db тестов
    public void ensurePreconditionDb(){
        //if(! app.group().isThereAGroup()){
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("group name"));
        }
    }
    
    @Test
    public void testGroupDeletion() {
	    app.goTo().groupPage();
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
	    assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}
