import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkInput() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement input = driver.findElement(By.tagName("input"));
        input.sendKeys("test");
        softAssert.assertEquals(input.getAttribute("value"), "");
        input.clear();
        input.sendKeys("1234567890");
        softAssert.assertEquals(input.getAttribute("value"), "1234567890");
        input.clear();
        input.sendKeys(Keys.ARROW_UP);
        input.sendKeys(Keys.ARROW_UP);
        softAssert.assertEquals(input.getAttribute("value"), "2");
        input.sendKeys(Keys.ARROW_DOWN);
        softAssert.assertEquals(input.getAttribute("value"), "1");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
