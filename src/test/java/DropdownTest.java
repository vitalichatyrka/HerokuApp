import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class DropdownTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkDropdown() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/dropdown");
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        List<WebElement> allOptions = dropdown.getOptions();
        for (WebElement option : allOptions) {
            softAssert.assertTrue(option.isDisplayed(), "Option is not shown");
        }
        dropdown.selectByIndex(1);
        softAssert.assertEquals(dropdown.getFirstSelectedOption().getText(),
                "Option 1", "Option 1 should be selected");
        dropdown.selectByIndex(2);
        softAssert.assertEquals(dropdown.getFirstSelectedOption().getText(),
                "Option 2", "Option 1 should be selected");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}

