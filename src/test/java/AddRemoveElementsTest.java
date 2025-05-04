import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class AddRemoveElementsTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkAddRemoveElements() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/button")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/button")).click();
        softAssert.assertTrue(driver.findElement(By.xpath(
                "//*[@id=\"elements\"]/button")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.xpath(
                "//*[@id=\"elements\"]/button[2]")).isDisplayed());
        driver.findElement(By.xpath("//*[@id=\"elements\"]/button[2]")).click();
        softAssert.assertTrue(driver.findElement(By.xpath(
                "//*[@id=\"elements\"]/button")).isDisplayed());
        softAssert.assertTrue(driver.findElements(By.xpath(
                "//*[@id=\"elements\"]/button[2]")).isEmpty());
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
