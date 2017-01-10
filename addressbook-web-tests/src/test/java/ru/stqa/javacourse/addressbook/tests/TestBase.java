package ru.stqa.javacourse.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.javacourse.addressbook.appmanager.ApplicationManager;
import ru.stqa.javacourse.addressbook.model.GroupData;
import ru.stqa.javacourse.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class  TestBase {
	Logger logger = LoggerFactory.getLogger(TestBase.class);


	protected static final ApplicationManager app
			= new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
	//= new ApplicationManager(BrowserType.FIREFOX);

	@BeforeSuite
	public void setUp() throws Exception {
		 app.init();
	}

	@AfterSuite(alwaysRun = true)
    public void tearDown() {
		app.stop();
	}

	 @BeforeMethod
	public void logTestStart(Method m, Object[] p){ // добавить переменную метода , Object[] p
		 //logger.info("Start test " + m.getName()); // вывод без информации о тестовых данных
		 logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));

	}

	@AfterMethod(alwaysRun = true)
	public void logTestStop(Method m, Object[] p){
		logger.info("Stop test " + m.getName() + " with parameters " + Arrays.asList(p));
		//logger.info("Stop test " + m.getName()); // вывод без информации о тестовых данных

	}

	public void verifyGroupListInUI() {
		if (Boolean.getBoolean("verifyUI")){ // берётся системная переменная VM options -ea -DverifyUI=true
			Groups dbGroups = app.db().groups();
			Groups uiGroups = app.group().all();
			assertThat(uiGroups, equalTo(dbGroups.stream()
					.map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
					.collect(Collectors.toSet())));
		}
	}
}
