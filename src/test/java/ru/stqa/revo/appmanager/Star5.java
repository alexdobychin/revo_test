package ru.stqa.revo.appmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import ru.stqa.revo.model.Qvality;
import ru.stqa.revo.model.UsersCreateData;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Alexandro on 23.01.2017.
 */
public class Star5 extends HelperBase {


    public Star5(ApplicationManager app) {
        super(app);
    }




    public String getRandRangeLowlettersrus(int min, int max) {
        Random random = new Random();
        int randomNumberInRange = getRandomNumberInRange(min, max);
        String strAllowedCharacters = "абвгдеёжзийклмнопрстуфхцчшщъыь";
        StringBuilder randomString = new StringBuilder(randomNumberInRange);
        for (int i = 0; i < randomNumberInRange; i++) {
            int randomInt = random.nextInt(strAllowedCharacters.length());
            randomString.append(strAllowedCharacters.charAt(randomInt));
        }
        return randomString.toString();
    }




    public String getRandomphone() {
        String part ="8925"; //1234567
        String part2 ="";
        String partm;
        String phone;
        String strAllowedCharacters = "0123456789";
        Random rand = new Random();

        for (int i = 0; i < 7 ; i++) {
            int  ch = strAllowedCharacters.length();
            int  n = rand.nextInt(ch);
            partm = String.valueOf(n);
            part2 = part2+ partm;
        }


        phone = part+part2;
        System.out.println(phone);
        return phone;
    }

    public String getRandompasport() {


        String partm;
        String pasport = "";
        String strAllowedCharacters = "0123456789";
        Random rand = new Random();

        for (int i = 0; i < 6 ; i++) {
            int  ch = strAllowedCharacters.length();
            int  n = rand.nextInt(ch);
            partm = String.valueOf(n);
            pasport = pasport+ partm;
        }


        System.out.println(pasport);
        return pasport;
    }



    public String getRandRangeLowletters(int min, int max) {
        Random random = new Random();
        int randomNumberInRange = getRandomNumberInRange(min, max);
        String strAllowedCharacters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder(randomNumberInRange);
        for (int i = 0; i < randomNumberInRange; i++) {
            int randomInt = random.nextInt(strAllowedCharacters.length());
            randomString.append(strAllowedCharacters.charAt(randomInt));
        }
        return randomString.toString();
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return random.nextInt((max - min) + 1) + min;
    }






}











