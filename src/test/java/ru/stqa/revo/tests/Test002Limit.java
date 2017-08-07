package ru.stqa.revo.tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class Test002Limit extends TestBase {

    //проверяем лимит

    @Test
    public void proba() throws InterruptedException, IOException {


        app.session().get();
        app.session().get();



    }
}
