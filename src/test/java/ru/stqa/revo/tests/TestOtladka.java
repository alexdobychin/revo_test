package ru.stqa.revo.tests;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.revo.model.UsersCreateData;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Alexandro on 19.01.2017.
 */
public class TestOtladka extends TestBase {


    @Test
    public void read_normal_json_sort_real() throws InterruptedException, IOException, ParseException {
        //   String tele = Integer.toString(usersData.getPhone()) +"1"+ piram;


        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("src/test/resources/users_params_all.json"));


        JSONArray jsonArray = (JSONArray) obj;
        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject jsonObjectRow = (JSONObject) jsonArray.get(i);
            //  int name = (int) jsonObjectRow.get("check");
            String qvality = (String) jsonObjectRow.get("name");
            String qvality2 = (String) jsonObjectRow.get("phone");
            System.out.println(qvality);
            System.out.println(qvality2);

        }


    }


    @Test
    public void randomurl2() throws InterruptedException, IOException {

        // app.session().get2("https://vk.com/");
        Thread.sleep(20000);


    }


    @Test
    public void phone() throws InterruptedException, IOException, ParseException {
       app.session().admin();




        }
    }



