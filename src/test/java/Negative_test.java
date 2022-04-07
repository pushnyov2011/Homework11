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
public class Negative_test {
    static WebDriver driver;
    private String password = "test88888888678";
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
    public void wrong_test()
    {
        driver.findElement(By.xpath("//*[contains(@id,'idcta-username')]")).click(); // перехожу на форму регистарции
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.titleIs("BBC – Sign in")); // жду пока не загрузится страницв=а, оринтировака на тайтел
       WebElement email_field =  driver.findElement(By.xpath("//*[contains(@id,'user-identifier-input')]"));// ввожу почту
        email_field.sendKeys(email);
        Assert.assertEquals(email_field.getAttribute("value"), email); // проверяю что данные введены в поле
        WebElement pass_fild = driver.findElement(By.id("password-input")); // попрубую найти по айдишнику
        pass_fild.sendKeys(password);
        Assert.assertEquals(pass_fild.getAttribute("value"), password);// проверяю что данные введены в поле
       // WebElement sing_in_aoutriz_button = driver.findElement(By.id("submit-button"));
   //   sing_in_aoutriz_button.click();
        driver.findElement(By.id("submit-button")).click();

        Assert.assertEquals( driver.findElement(By.xpath("//*[contains(@id,'form-message-password')]")).getText(), "Uh oh, that password doesn’t match that account. Please try again.");
        Assert.assertEquals(driver.getTitle(), "BBC – Sign in");
    }
    @AfterTest
    public void end_test()
    {
        driver.close();
    }

}
