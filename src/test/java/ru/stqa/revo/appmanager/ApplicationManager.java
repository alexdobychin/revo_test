package ru.stqa.revo.appmanager;

import com.browserstack.local.Local;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    private final Properties properties;
    private WebDriver wd;
    private Local localBStack;

    private String browser;
    private HelperBase helperBase;
    private SessionHelper sessionHelper;
    private Star5 star5;
    private String parentWindowname;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
            this.helperBase = null;
            this.star5 = null;
            this.sessionHelper = null;

        }
    }
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public HelperBase base() {
        if (helperBase == null) {
            helperBase = new HelperBase(this);
        }
        return helperBase;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper =  new SessionHelper(this);
        }
        return sessionHelper;
    }

    public Star5 block2() {
        if (star5 == null) {
            star5 = new Star5(this);
        }
        return star5;
    }


    public WebDriver getWebdriver() {
        if (!getWebDriverSessionId()) {
            String DriverPath = "src";
            if (Objects.equals(browser, BrowserType.CHROME)) {
                System.setProperty("webdriver.chrome.driver",
                        DriverPath + "/chromedriver_win/chromedriver.exe");
               wd = new ChromeDriver();
                parentWindowname = wd.getWindowHandle();
           } else if (Objects.equals(browser, BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
                parentWindowname = wd.getWindowHandle();
            } else
            if (Objects.equals(browser, BrowserType.EDGE)) {
                System.setProperty("webdriver.edge.driver",
                        DriverPath + "/edgedriver/MicrosoftWebDriver.exe");
                wd = new EdgeDriver();
                parentWindowname = wd.getWindowHandle();
            } else if (Objects.equals(browser, BrowserType.IE)) {
                System.setProperty("webdriver.ie.driver",
                        DriverPath + "/iedriver/IEDriverServer.exe");
                wd = new InternetExplorerDriver();
                parentWindowname = wd.getWindowHandle();
            } else if(Objects.equals(browser, "chrome_options")){
                System.out.println("chrome_options");
                System.setProperty("webdriver.chrome.driver",
                        DriverPath + "/chromedriver_win/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("user-agent=Mozilla/5.0 (Linux; Android 5.0; Nexus 5 Build/LPX13D) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.102 Mobile Safari/537.36");
                wd = new ChromeDriver(options);

            }
               // wd = new ChromeDriver();
                parentWindowname = wd.getWindowHandle();

        }
        return wd;
    }




    public WebDriver getWebdriverBrowserStack() {
        if (!getWebDriverSessionId() | wd == null) {
            String browserStackConfig = System.getProperty("browserStackConfig", "ordersBrowserStack");
            JSONParser parser = new JSONParser();
            JSONObject config = null;
            try {
                config = (JSONObject) parser
                        .parse(new FileReader(String.format("src/test/resources/config/%s.json", browserStackConfig)));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JSONObject envs = (JSONObject) config.get("environments");

            DesiredCapabilities capabilities = new DesiredCapabilities();

            Map<String, String> envCapabilities = (Map<String, String>) envs.get(browser);
            Iterator it = envCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }

            Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
            it = commonCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                if(capabilities.getCapability(pair.getKey().toString()) == null){
                    capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
                }
            }

            String username = System.getenv("BROWSERSTACK_USERNAME");
            if(username == null) {
                username = (String) config.get("user");
            }

            String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
            if(accessKey == null) {
                accessKey = (String) config.get("key");
            }

            if(capabilities.getCapability("browserstack.local") != null
                    && capabilities.getCapability("browserstack.local") == "true"){
                localBStack = new Local();
                Map<String, String> options = new HashMap<String, String>();
                options.put("key", accessKey);
                try {
                    localBStack.start(options);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                wd = new RemoteWebDriver(new URL(String.format
                        ("http://%s:%s@%s/wd/hub", username, accessKey, config.get("server"))), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return wd;
    }

    public boolean getWebDriverSessionId() {
        try {
            SessionId sessionId = ((RemoteWebDriver) wd).getSessionId();
            if (sessionId != null) {
                logger.info(String.format("Получен Session ID: %s вебдрайвера", sessionId));
                return true;
            } else {
                return false;
            }
        } catch (Exception exc) {
            return false;
        }
    }

    public void stopBrowserStack() {
        wd.quit();
        if (localBStack != null) {
            try {
                localBStack.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.helperBase = null;
    }





}
