package ru.stqa.revo.tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class Test003admin  extends TestBase {

    //проверяем лимит

    @Test
    public void proba() throws InterruptedException, IOException {


        app.session().admin();
        app.session().login();
        Thread.sleep(100000);



    }
}
