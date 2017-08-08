package ru.stqa.revo.appmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.stqa.revo.model.Qvality;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class HelperBase {

    private Logger logger = LoggerFactory.getLogger(HelperBase.class);
    protected ApplicationManager app;
    protected WebDriver wd;


    public void get(){

        wd.get(app.getProperty("web.baseurlrevo"));
    }

    public void admin(){

        wd.get(app.getProperty("web.baseurladm"));
    }

    public void login(){
        wd.findElement(By.id("user_login")).sendKeys("admin");
        wd.findElement(By.id("user_password")).sendKeys("password");
        wd.findElement(By.className("submit")).click();
    }

    public void zayavka() throws InterruptedException {
        //авторизуемся
        wd.findElement(By.className("big")).click();
        Thread.sleep(1500);
        wd.findElement(By.id("login")).sendKeys("test");
        wd.findElement(By.id("pass")).sendKeys("1234");
        wd.findElement(By.className("btn")).click();
        wd.findElement(By.className("big")).click();





    }



    public void pokupka_1(String zayv[]) throws InterruptedException, IOException {
        //заполняем заявку

        wd.findElement(By.id("calc_by_purchase_amount")).sendKeys(zayv[0]); //сумма
        wd.findElement(By.id("calc_by_purchase_amount")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(2000);
        wd.findElement(By.id("calc_by_purchase_amount")).sendKeys("0");


        wd.findElement(By.id("expandInfoBtn")).click();

        //скрол вниз
        JavascriptExecutor jse = (JavascriptExecutor)wd;
        jse.executeScript("window.scrollBy(0,250)", "");

        //вводим пин код
        wd.findElement(By.id("modalPinShow")).click();
        Thread.sleep(5500);
        wd.findElement(By.id("pin")).sendKeys("333");
        wd.findElement(By.id("pin")).sendKeys(Keys.ENTER);
        wd.findElement(By.id("mobile_phone")).sendKeys(zayv[1]); //телефон
        wd.findElement(By.className("float-left")).click(); //продолжить
        File screenshot = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/test/resources/screen5.png"));


        Thread.sleep(2000);




        //заполняем заявку  с паспортными данными

        File screenshot2 = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot2, new File("src/test/resources/screen4.png"));

        wd.findElement(By.xpath("//td[text()='Женский']")).click();
        ///html/body/div/div[11]/div[3]/div[2]/div[2]/form/fieldset/ul[1]/li[8]/span[2]/table/tbody/tr/td[2]
       // wd.findElement(By.id("last_name")).sendKeys(last_name);
        wd.findElement(By.id("last_name")).sendKeys(zayv[2]);
        wd.findElement(By.id("first_name")).sendKeys("Ольга");
        wd.findElement(By.id("middle_name")).sendKeys("Николаевич");

        wd.findElement(By.id("birth_date")).sendKeys("10.10.1980");

        wd.findElement(By.id("passport_series")).sendKeys("3016");
        wd.findElement(By.id("passport_number")).sendKeys(zayv[3]);
        //wd.findElement(By.id("passport_number")).sendKeys(Keys.ENTER);

        Thread.sleep(5500);
        JavascriptExecutor jse2 = (JavascriptExecutor)wd;
        jse2.executeScript("window.scrollBy(0,250)", "");

        wd.findElement(By.cssSelector("#previewer > div.block.products > div.wrap > div:nth-child(2) > form > fieldset > ul.buttons > li > input")).click();
        wd.findElement(By.cssSelector("#previewer > div.block.products > div.wrap > div:nth-child(2) > form > fieldset > ul.buttons > li > input")).click();
//<input class="float-right" type="submit" value="Отправить заявку" autocomplete="off">
        //Thread.sleep(30500);
        //вводим код подтверждения
        Thread.sleep(2500);

        System.out.println("страница подтверждения");
        File screenshot23 = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot23, new File("src/test/resources/aprove.png"));


        Thread.sleep(5500); //заменить на вейты
        wd.findElement(By.id("asp_text")).sendKeys("1111");
        Thread.sleep(5500);
        wd.findElement(By.id("asp_button")).click();
        Thread.sleep(5500);



        File screenshot24= ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot24, new File("src/test/resources/aprove2.png"));
        Thread.sleep(20000);


         //грузим паспорт
        //wd.findElement(By.id("capture-btn-grey-loading_btn-first_two_pages-camera-reader")).click();

        wd.findElement(By.id("capture-btn-grey-loading_btn-first_two_pages-camera-reader")).sendKeys(new File("src//test//resources//1.jpg").getAbsolutePath());
        Thread.sleep(2000);
        wd.findElement(By.id("capture-btn-grey-loading_btn-living_addr-camera-reader")).sendKeys(new File("src//test//resources//1.jpg").getAbsolutePath());
        Thread.sleep(2000);
        wd.findElement(By.id("capture-btn-grey-loading_btn-client_with_passport-camera-reader")).sendKeys(new File("src//test//resources//1.jpg").getAbsolutePath());
        Thread.sleep(2000);

        wd.findElement(By.cssSelector("#photo_screen > ul > li > button")).click();
        Thread.sleep(10000); //переход на стараницу с подтверждением телефона

        System.out.println("подтверждение телф"); //сделать обработку исключения

/**
        wd.findElement(By.id("asp_confirm")).click();
        wd.findElement(By.id("confirm_asp_text")).sendKeys("1111");
        wd.findElement(By.cssSelector("#confirm_asp_button")).click();




        Thread.sleep(10000);
        File screenshot232 = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot232, new File("src/test/resources/aprovefinal.png"));

*/



    }



    public void zayavka2(){

        wd.get(String.format("%s/switch-locale/ru_RU?country_code=RU", app.getProperty("web_shop.baseurl")));


    }

    public void shop() {


        wd.get(app.getProperty("web_shop.baseurl"));
    }



    public void shop_shop(){


        wd.get(app.getProperty("web_shop.baseurl"));


    }
    public void close() {


        wd.close();
    }
    public void manager(String qvsave) throws IOException, InterruptedException {

        String index_date = new java.util.Date().toString();
        wd.get("http://138.201.247.106/manager/");

        //сейвим во временный json  квалу и все кружки
        Thread.sleep(3000);
        String qvality = wd.findElement(By.xpath("html/body/div/div[3]/div[2]/div/div[1]/p/span[2]")).getText();
        System.out.println(qvality);
        String qv[] = qvality.split(" ");
        System.out.println(qv[2]);
        assertEquals(qvsave, qv[2]);




/**
        //правый
        Thread.sleep(3000);
        String re0 = wd.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[3]/div[1]/div[3]/div/span")).getText();
        String re = wd.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[3]/div[1]/div[2]/div[2]/span")).getText();
       // /html/body/div/div[2]/div[3]/div[3]/div[1]/div[1]/div[1]/span
       // String re_bonus = wd.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[3]/div[1]/div[1]/div[1]/span[2]")).getText();
        String re2 = wd.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[3]/div[1]/div[1]/div[3]/span")).getText();
        String re23 = wd.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[3]/div[1]/div[2]/div[1]/span[1]")).getText();
        File screenshot = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/test/resources/screen.png"));
        //+index_date+

        System.out.println("го ноябрь_ "+re0);
        System.out.println("клиентский объём "+re);
     //   System.out.println("бонусы "+re_bonus);
        System.out.println("дельта"+re2);
        System.out.println("Личный объём "+re23);
*/
    }





    public HelperBase(ApplicationManager app) {
        this.app = app;
        if (Boolean.getBoolean("browserStack")) {
            wd = app.getWebdriverBrowserStack();
        } else {
            wd = app.getWebdriver();
        }
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.manage().window().maximize();
    }





    public String read_his_manager(String path) throws IOException, ParseException {


        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader(path));

        JSONArray jsonArray = (JSONArray) obj;
        // for (int i = 0; i < z; i++) {

        JSONObject jsonObjectRow = (JSONObject) jsonArray.get(0);

        String manager_id = (String) jsonObjectRow.get("his_m");
        System.out.println(manager_id);


        return manager_id;
    }




    public String readQvalityCheck(int z) throws IOException, ParseException {


        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader(app.getProperty("qvality.flow")));

        JSONArray jsonArray = (JSONArray) obj;
        // for (int i = 0; i < z; i++) {

        JSONObject jsonObjectRow = (JSONObject) jsonArray.get(z);
        //  int name = (int) jsonObjectRow.get("check");
        String qvality = (String) jsonObjectRow.get("qvality");
        System.out.println(qvality);


        return qvality;
    }





    public void writeJsonManager(Qvality qvality) {
        try {
            ArrayList<Qvality> qvalities = new ArrayList<>();
            qvalities.add(qvality);
            Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
            String json = gson.toJson(qvalities);
            try (Writer writer = new FileWriter(new File(app.getProperty("managers.flow")))) {
                writer.write(json);
            } catch (IOException e) {
                logger.error(String.format("Not write file: %s", app.getProperty("managers.flow")));
            }
        } catch (Exception exc) {

        }
    }

    public void write_his_manager(Qvality qvality, String path) {
        try {
            ArrayList<Qvality> qvalities = new ArrayList<>();
            qvalities.add(qvality);
            Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
            String json = gson.toJson(qvalities);
            try (Writer writer = new FileWriter(new File(path))) {
                writer.write(json);
            } catch (IOException e) {
                logger.error(String.format("Not write file: %s", path));
            }
        } catch (Exception exc) {

        }
    }



    public boolean whithIsRusLocal() {
        String textLocal = wd.findElement(By.cssSelector("div[class='footer-country']")).getText();
        if (textLocal.equals("Язык интерфейса:")) {
            return true;
        } else {
            return false;
        }
    }



    public void clickSearch(By locator) {
        wd.findElement(locator).click();
    }

    public void clickLocator(WebElement locator) {
        locator.click();
    }

    public void type(By locator, String text) {
        clickSearch(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }





    public WebDriverWait initWait(int seconds) {
        WebDriverWait wait = new WebDriverWait(wd, seconds);
        return wait;
    }

    public String getDateNow() {
        Date date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        String formatData = format1.format(date);
        return formatData;
    }

    public WebElement searchInDropDownList(List<WebElement> choicesElements, String textValue, String nameSearchingValue) {
        //int lenChoice = choicesElements.size();
        int count = 0;
        int sizeList = choicesElements.size();
        for (WebElement elementChoices:choicesElements ) {
            count++;
            String textChoice = elementChoices.getText();
            if (textChoice.equals(textValue)) {
                logger.info("Найден " + nameSearchingValue + ": " + textValue + " Полученный текст элемента: " + textChoice);
                return elementChoices;
            } else if (count < sizeList){
                logger.info(String.format("На %s шаге из %s не найден %s: %s, Полученный текст элемента: %s", count, sizeList, nameSearchingValue, textValue, textChoice));
            } else {
                logger.error("Не найден " + nameSearchingValue + ": " + textValue + " Полученный текст элемента: " + textChoice);
            }
        }
        return null;
    }

    public void searchInDropDownListAndClick(List<WebElement> choicesElements, String textValue, String nameSearchingValue) {
        int count = 0;
        int sizeList = choicesElements.size();
        for ( WebElement elementChoices:choicesElements ) {
            String textChoice = elementChoices.getText();
            if (textChoice.equals(textValue)) {
                logger.info("Найден " + nameSearchingValue + ": " + textValue + " Полученный текст элемента: " + textChoice);
                elementChoices.click();
            } else if (count < sizeList) {
                logger.info(String.format("На %s шаге из %s не найден %s: %s, Полученный текст элемента: %s", count, sizeList, nameSearchingValue, textValue, textChoice));
            } else {
                logger.error("Не найден " + nameSearchingValue + ": " + textValue + " Полученный текст элемента: " + textChoice);
            }
        }
    }

    public int getIndexInDropDownList(List<WebElement> choicesElements, String textValue, String nameSearchingValue) {
        int sizeList = choicesElements.size();
        for (int i = 1; i < choicesElements.size(); i++) {
            WebElement elementChoices = choicesElements.get(i);
            String textChoice = elementChoices.getText();
            if (textChoice.equals(textValue)) {
                logger.info("Найден " + nameSearchingValue + ": " + textValue + " Полученный текст элемента: " + textChoice);
                return i;
            } else if (i < sizeList) {
                logger.info(String.format("На %s шаге из %s не найден %s: %s, Полученный текст элемента: %s", i, sizeList, nameSearchingValue, textValue, textChoice));
            } else {
                logger.error("Не найден " + nameSearchingValue + ": " + textValue + " Полученный текст элемента: " + textChoice);
            }
        }
        return choicesElements.size() - 1;
    }

    public static int randRange(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }



    public List<Qvality> read_master_json(String path) {
        try  (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))){
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<Qvality> testcases = gson.fromJson(json, new TypeToken<List<Qvality>>() {
            }.getType());
            return testcases;
        } catch (IOException e) {
            logger.error(String.format("Not reading file: %s", path));
            return null;
        }
    }
}
