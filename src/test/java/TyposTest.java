import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class TyposTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test
    public void checkTypos() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/typos");
        int refreshCounter = 10;
        for (int count = 0; count < refreshCounter; count++) {
            List<WebElement> paragraphs = driver.findElements(By.cssSelector(".example p"));
            softAssert.assertEquals(paragraphs.get(0).getText(),
                    "This example demonstrates a typo being introduced. It does it randomly on each page load.",
                    "Fist paragraph is incorrect" + " after " + count + " refreshes");
            softAssert.assertEquals(paragraphs.get(1).getText(),
                    "Sometimes you'll see a typo, other times you won't.",
                    "Second paragraph is incorrect" + " after " + count + " refreshes");
            driver.navigate().refresh();
        }
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
