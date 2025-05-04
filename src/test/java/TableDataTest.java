import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TableDataTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkTableData() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/tables");

        Assert.assertEquals(driver.findElement(
                        By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[1]")).getText(),
                "Smith", "The last name is incorrect");
        Assert.assertEquals(driver.findElement(
                        By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[2]")).getText(),
                "John", "The first name is incorrect");
        Assert.assertEquals(driver.findElement(
                        By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[3]")).getText(),
                "jsmith@gmail.com", "The email is incorrect");
        Assert.assertEquals(driver.findElement(
                        By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[4]")).getText(),
                "$50.00", "The due is incorrect");
        Assert.assertEquals(driver.findElement(
                        By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[5]")).getText(),
                "http://www.jsmith.com", "The web site is incorrect");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
