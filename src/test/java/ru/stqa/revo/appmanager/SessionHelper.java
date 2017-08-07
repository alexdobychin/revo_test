package ru.stqa.revo.appmanager;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.stqa.revo.model.Qvality;
import ru.stqa.revo.model.UsersData;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;
import static org.testng.Assert.assertEquals;

/**
 * Created by Alexandro on 18.01.2017.
 */
public class SessionHelper extends HelperBase {

    Logger logger = LoggerFactory.getLogger(SessionHelper.class);

    public SessionHelper(ApplicationManager app) {
        super(app);
    }


    public void close() {
        wd.close();
    }


}
