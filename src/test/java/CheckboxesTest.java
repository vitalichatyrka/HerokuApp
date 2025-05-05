import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class CheckboxesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkFirstCheckbox() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        List<WebElement> checkboxes = driver.findElements(
                By.cssSelector("input[type=checkbox]"));
        softAssert.assertFalse(checkboxes.get(0).isSelected(),
                "First checkbox should be unchecked");
        softAssert.assertTrue(checkboxes.get(1).isSelected(),
                "Second checkbox should be checked");
        checkboxes.get(0).click();
        softAssert.assertTrue(checkboxes.get(0).isSelected(),
                "First checkbox should be checked after clicking");
        checkboxes.get(1).click();
        softAssert.assertFalse(checkboxes.get(1).isSelected(),
                "Second checkbox should be unchecked after clicking");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}

