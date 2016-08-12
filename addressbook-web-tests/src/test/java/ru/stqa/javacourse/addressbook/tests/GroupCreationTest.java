package ru.stqa.javacourse.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
	    app.getNavigationHelper().goToGroup();
	    List<GroupData> before = app.getGroupHelper().getGroupList();
	    //int before = app.getGroupHelper().getGroupCount();
	    GroupData group = new GroupData("group name 2", "Group header ", "Group footer ");
	    app.getGroupHelper().createGroup(group);
	    List<GroupData> after = app.getGroupHelper().getGroupList();
	    //int after = app.getGroupHelper().getGroupCount();
	    Assert.assertEquals(after.size(), before.size() + 1);

	    /*
	    int max =0;
	    for(GroupData g: after){
		    if(g.getId()>max){
			    max = g.getId();
		    }
	    }
	    */

	    // Comparator<? super GroupData> byId  = (o1, o2) -> Integer.compare(o1.getId(), o2.getId()); // удалили, для передачи напрямую в int max1 = after.stream().max(  ).get().getId();

	    /*
	    // заменяется на лямбда выражение
	    Comparator<? super GroupData> byId  = new Comparator<GroupData>() {
	    @Override
	    public int compare(GroupData o1, GroupData o2) {
	    return Integer.compare(o1.getId(), o2.getId());
	    }
	    };
	    */


	    // int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
	    //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
	    before.add(group);
	    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
	    before.sort(byId);
	    after.sort(byId);
	    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); // сравнение списков преобразованных в множества
	    Assert.assertEquals(before, after);
    }
}
