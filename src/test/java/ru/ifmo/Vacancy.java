package ru.ifmo;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * Created by anastasia on 29.05.17.
 */
public class Vacancy {
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
    public void testVacanciesFireFox() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/#");
        driver.findElement(By.xpath("//a[contains(.,'Вакансии')]")).click();
        Thread.sleep(1000);
                Assert.assertEquals("FireFox 'about page wasn't loaded",
                "Careers | Fandom powered by Wikia", driver.getTitle());
    }

    @Test
    public void testVacanciesChrome() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/#");
        driver.findElement(By.xpath("//a[contains(.,'Вакансии')]")).click();
        Thread.sleep(1000);
                Assert.assertEquals("Chrome 'about' page wasn't loaded",
                "Careers | Fandom powered by Wikia", driver.getTitle());
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
