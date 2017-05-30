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
public class Registration {
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
    public void testRegChrome() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/#");
        driver.findElement(By.xpath("//span[contains(.,'Мой аккаунт')]")).click();
        driver.findElement(By.xpath("//a[contains(.,'Зарегистрироваться')]")).click();
        Thread.sleep(10000);
        Assert.assertEquals("Chrome 'about' page wasn't loaded",
                "Присоединяйтесь к Фэндому сегодня | Fandom powered by Wikia", driver.getTitle());
        driver.findElement(By.xpath("//input[contains(@name,'email')]")).sendKeys("trofimo.nast@yandex.ru");
        driver.findElement(By.xpath("//input[contains(@name,'username')]")).sendKeys("nastya-tpo");
        driver.findElement(By.xpath("//input[contains(@name,'password')]")).sendKeys("qwerty");
        driver.findElement(By.xpath("//input[contains(@name,'birthdate')]")).click();
        driver.findElement(By.xpath("//input[contains(@name,'day')]")).sendKeys("29");
        driver.findElement(By.xpath("//input[contains(@name,'month')]")).sendKeys("10");
        driver.findElement(By.xpath("//input[contains(@name,'year')]")).sendKeys("1996");
        driver.findElement(By.xpath("//button[contains(.,'Зарегистрироваться')]")).click();
        Thread.sleep(10000);
        Assert.assertEquals("Chrome 'explore' page wasn't loaded",
                "Explore | Fandom powered by Wikia", driver.getTitle());
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/img")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/ul/li[2]/div/form/button")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[contains(.,'Мой аккаунт')]")).click();
        Thread.sleep(1000);
        Assert.assertEquals("Chrome 'nastasiatm' page wasn't loaded",
                "ВОЙТИ", driver.findElement(By.xpath("//a[contains(.,'Войти')]")).getText());
    }

    @Test
    public void testLoginFirefox() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/#");
        driver.findElement(By.xpath("//span[contains(.,'Мой аккаунт')]")).click();
        driver.findElement(By.xpath("//a[contains(.,'Зарегистрироваться')]")).click();
        Thread.sleep(10000);
        Assert.assertEquals("Chrome 'about' page wasn't loaded",
                "Присоединяйтесь к Фэндому сегодня | Fandom powered by Wikia", driver.getTitle());
        driver.findElement(By.xpath("//input[contains(@name,'email')]")).sendKeys("trofimo.nast@yandex.ru");
        driver.findElement(By.xpath("//input[contains(@name,'username')]")).sendKeys("nastya-tpo");
        driver.findElement(By.xpath("//input[contains(@name,'password')]")).sendKeys("qwerty");
        driver.findElement(By.xpath("//input[contains(@name,'birthdate')]")).click();
        driver.findElement(By.xpath("//input[contains(@name,'day')]")).sendKeys("29");
        driver.findElement(By.xpath("//input[contains(@name,'month')]")).sendKeys("10");
        driver.findElement(By.xpath("//input[contains(@name,'year')]")).sendKeys("1996");
        driver.findElement(By.xpath("//button[contains(.,'Зарегистрироваться')]")).click();
        Thread.sleep(10000);
        Assert.assertEquals("Chrome 'explore' page wasn't loaded",
                "Explore | Fandom powered by Wikia", driver.getTitle());
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/img")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/ul/li[2]/div/form/button")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[contains(.,'Мой аккаунт')]")).click();
        Thread.sleep(1000);
        Assert.assertEquals("Chrome 'nastasiatm' page wasn't loaded",
                "ВОЙТИ", driver.findElement(By.xpath("//a[contains(.,'Войти')]")).getText());
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
