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
public class CreateWiki {
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

    /*@Test
    public void testCommunityFireFox() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/#");
        driver.findElement(By.xpath("//a[contains(.,'Вики Сообщества')]")).click();
        Thread.sleep(1000);
        Assert.assertEquals("FireFox 'about page wasn't loaded",
                "Вики Сообщества | Fandom powered by Wikia", driver.getTitle());
    }*/

    @Test
    public void testCommunityChrome() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/#");

        //login
        driver.findElement(By.xpath("//span[contains(.,'Мой аккаунт')]")).click();
        driver.findElement(By.xpath("//a[contains(.,'Войти')]")).click();
        Thread.sleep(10000);
        Assert.assertEquals("Chrome 'about' page wasn't loaded",
                "Войти | Fandom powered by Wikia", driver.getTitle());
        driver.findElement(By.xpath("//input[contains(@name,'username')]")).sendKeys("1cloud-n");
        driver.findElement(By.xpath("//input[contains(@name,'password')]")).sendKeys("qwerty");
        driver.findElement(By.xpath("//button[contains(.,'Войти')]")).click();
        Thread.sleep(10000);
        Assert.assertEquals("Chrome 'explore' page wasn't loaded",
                "Explore | Fandom powered by Wikia", driver.getTitle());
        //create
        driver.findElement(By.xpath("//span[contains(.,'Создать вики')]")).click();
        Thread.sleep(1000);
        Assert.assertEquals("Chrome 'about' page wasn't loaded",
                "Создать новую вики | Fandom powered by Wikia", driver.getTitle());
        driver.findElement(By.xpath("//input[contains(@name,'wiki-name')]")).sendKeys("trololotpogames");
        //driver.findElement(By.xpath("//input[contains(@name,'wiki-domain')]")).sendKeys("qwerty-tpo");
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id='NameWiki']/form/nav/input")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[contains(.,'Список порталов')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(@id,'7')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='DescWiki']/form/nav/input[2]")).click();
        Thread.sleep(50000);
        driver.findElement(By.xpath("//input[contains(@value,'Перейти на мою вики')]")).click();
        Thread.sleep(1000); //Qwertyu вики | Fandom powered by Wikia
        Assert.assertEquals("Chrome 'about' page wasn't loaded",
                "qwerty-tpo вики | Fandom powered by Wikia", driver.getTitle());


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
