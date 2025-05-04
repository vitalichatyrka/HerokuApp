import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class HoverTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkHover() {
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);
        driver.get("https://the-internet.herokuapp.com/hovers");
        //List <WebElement> usersIcons = driver.findElements(By.xpath("//div[@class='figure']"));
        List<WebElement> usersIcons = driver.findElements(By.cssSelector("img[alt='User Avatar']"));
        actions.moveToElement(usersIcons.get(0)).perform();
        softAssert.assertEquals(driver.findElement(By.xpath(
                        "//*[@id=\"content\"]/div/div[1]/div/h5")).getText(),
                "name: user1", "The user name #1 is incorrect");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a")).click();
        softAssert.assertNotEquals(driver.findElement(By.xpath("//body/h1")).getText(),
                "Not Found", "The user #1 profile not found.");
        driver.get("https://the-internet.herokuapp.com/hovers");
        usersIcons.clear();
        usersIcons = driver.findElements(By.cssSelector("img[alt='User Avatar']"));
        actions.moveToElement(usersIcons.get(1)).perform();
        softAssert.assertEquals(driver.findElement(By.xpath(
                        "//*[@id=\"content\"]/div/div[2]/div/h5")).getText(),
                "name: user2", "The user name #2 is incorrect");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/a")).click();
        softAssert.assertNotEquals(driver.findElement(By.xpath("//body/h1")).getText(),
                "Not Found", "The user #2 profile not found.");
        driver.get("https://the-internet.herokuapp.com/hovers");
        usersIcons.clear();
        usersIcons = driver.findElements(By.cssSelector("img[alt='User Avatar']"));
        actions.moveToElement(usersIcons.get(2)).perform();
        softAssert.assertEquals(driver.findElement(By.xpath(
                        "//*[@id=\"content\"]/div/div[3]/div/h5")).getText(),
                "name: user3", "The user name #3 is incorrect");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/div/a")).click();
        softAssert.assertNotEquals(driver.findElement(By.xpath("//body/h1")).getText(),
                "Not Found", "The user #3 profile not found.");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
