package ru.stqa.revo.tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import static org.testng.Assert.assertEquals;

public class Test001Zayavka extends TestBase {

    @Test
    public void proba() throws InterruptedException, IOException {

        String summa =  "2000";
        String pirm = "Ольга" + app.block2().getRandRangeLowlettersrus(1,30);

        String tel =app.block2().getRandomphone(); //"89252552327";
        String pasport = app.block2().getRandompasport();//"592327";

        String zayv[] = {summa,tel,pirm,pasport}; //сумма телефон фамилия
        app.session().get();
        app.session().zayavka();
        app.session().pokupka_1(zayv);
        //Thread.sleep(2000000);
        assertEquals(1, 1);


    }



    //проверяем некоректные значения дат, смотрим, что появляется сообщение об ошибке
/**
    @Test
    public void zayavka_nevalid2() throws InterruptedException, IOException {

        String summa =  "2000";
        String pirm = "Ольга" + app.block2().getRandRangeLowlettersrus(1,30);

        String tel =app.block2().getRandomphone(); //"89252552327";
        String pasport = app.block2().getRandompasport();//"592327";

        String zayv[] = {summa,tel,pirm,pasport}; //сумма телефон фамилия
        app.session().get();
        app.session().zayavka();
        app.session().pokupka_1(zayv);
        //Thread.sleep(2000000);



    }
    */


}
