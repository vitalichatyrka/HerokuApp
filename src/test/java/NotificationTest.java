import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class NotificationTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkNotification() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        for (int counter = 0; counter < 10; counter++) {
            driver.findElement(By.xpath("//*[@id=\"content\"]/div/p/a")).click();
            softAssert.assertEquals(driver.findElement
                            (By.id("flash")).getText().trim().replace("×", "").trim(),
                    "Action successful",
                    "The notification is not successful, iteration № " + counter);
        }
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
