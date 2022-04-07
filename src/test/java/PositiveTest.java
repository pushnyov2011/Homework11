import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.StringWriter;
import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class PositiveTest {
    static WebDriver driver;
    private String password = "test12345678";
    private  String email = "ttestq4@yandex.by";


    @BeforeTest
    public void setup()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.bbc.com/");



    }

    @Test
  public void login_and_pass_true()
    {
      WebElement sing_in_button = driver.findElement(By.xpath("//*[contains(@id,'idcta-username')]")); // находим кнопку для перехода на страницу аторизации
       // sing_in_button = driver.findElement(By.id("idcta-username"));
        sing_in_button.click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.titleIs("BBC – Sign in"));
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getTitle(), "BBC – Sign in" ); // наверная лишняя проверка, так как она ляжет и в первом случае , если за 5 секунд не найдет нужный тайтел
        WebElement email_fild = driver.findElement(By.xpath("//*[contains(@id,'user-identifier-input')]"));
        email_fild.sendKeys(email); // ввод данных
        Assert.assertEquals(email_fild.getAttribute("value"), email); // проверяем , что наши данные есть в поле
        WebElement pass_fild = driver.findElement(By.id("password-input")); // попрубую найти по айдишку
        pass_fild.sendKeys(password);
        Assert.assertEquals(pass_fild.getAttribute("value"), password); // проверяем , что наши данные есть в поле
        WebElement sing_in_aoutriz_button = driver.findElement(By.id("submit-button"));
        sing_in_aoutriz_button.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("https://www.bbc.com/"));
        
      //  Assert.assertEquals(driver.getTitle(), "BBC - Homepage");
            Assert.assertEquals(driver.getTitle(), "BBC - Homepage");
           Assert.assertEquals( driver.findElement(By.id("idcta-username")).getText(), "Your account" );

//idcta-username

    }

    @AfterTest
    public void end_test()
    {
        driver.get("https://account.bbc.com/signout"); // перехожу на страницу выхода
      //  driver.findElement(By.id("idcta-username")).click();
        //new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions);

        driver.findElement(By.linkText("Continue")).click();// выхожу из акка
        driver.close(); // закрыл браузер
        //link=Continue

    }

}
