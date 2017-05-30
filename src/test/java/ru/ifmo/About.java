package ru.ifmo;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class About {
    WebDriver driver;

    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void init() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
    }

    @Before
    public void setUp() throws Exception {
        baseUrl = "http://wikia.com/";
    }

    @Test
    public void testAboutFireFox() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/#");
        driver.findElement(By.xpath("//a[contains(.,'О нас')]")).click();
        Thread.sleep(1000);
        if (!driver.getCurrentUrl().contains("http://wikia.com")) {
            //String[] handlers = new String[2];
            //handlers = driver.getWindowHandles().toArray(handlers);
            //driver.switchTo().window(handlers[1]);
            driver.findElement(By.xpath("//a[contains(.,'О нас')]")).click();
        }
        Assert.assertEquals("FireFox 'about page wasn't loaded",
                "About | Fandom powered by Wikia", driver.getTitle());
    }

    @Test
    public void testAboutChrome() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/#");
        driver.findElement(By.xpath("//a[contains(.,'О нас')]")).click();
        Thread.sleep(1000);
        if (!driver.getCurrentUrl().contains("http://wikia")) {
            //String[] handlers = new String[2];
            //handlers = driver.getWindowHandles().toArray(handlers);
            //driver.switchTo().window(handlers[1]);
            driver.findElement(By.xpath("//a[contains(.,'О нас')]")).click();
        }
        Assert.assertEquals("Chrome 'about' page wasn't loaded",
                "About | Fandom powered by Wikia", driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by, WebDriver driver) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
