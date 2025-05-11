import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class DynamicControlsTest extends BaseTest {

    @Test
    public void CheckDynamicControls() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("//*[text()='Remove']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));
        assertEquals(driver.findElement(By.xpath("//p[@id='message']")), "It's gone!");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        assertFalse(driver.findElement(By.xpath(
                "//input[@type='text']")).isEnabled());
        driver.findElement(By.xpath("//button[@onclick='swapInput()']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));
        assertEquals(driver.findElement(By.xpath("//p[@id='message']")),
                "It's enabled!");
        assertTrue(driver.findElement(By.xpath(
                "//input[@type='text']")).isEnabled());
        softAssert.assertAll();
    }
}
