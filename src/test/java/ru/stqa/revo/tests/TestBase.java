package ru.stqa.revo.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.revo.appmanager.ApplicationManager;

import java.lang.reflect.Method;


public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (Boolean.getBoolean("browserStack")) {
            app.stopBrowserStack();
        } else {
            app.stop();

        }
    }

//    @BeforeMethod
//    public void logTestStart(Method method, Object[] parametrs) throws IOException, InterruptedException {
//        logger.info(String.format("===>>> START TEST %s, WITH PARAMETERS %s <<<===", method, Arrays.asList(parametrs)));
//    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {
        logger.info(String.format("===>>> STOP TEST %s <<<===", method));
    }
}
