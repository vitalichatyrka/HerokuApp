import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class contextMenuTest extends BaseTest {

    @Test
    public void testAlertTextAfterRightClick() {
        driver.get("https://the-internet.herokuapp.com/context_menu");
        actions.contextClick(driver.findElement(By.id("hot-spot"))).perform();
        Alert alert = driver.switchTo().alert();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(alert.getText(), "You selected a context menu");

    }
    @Test
    public void testAlertIsClosedAfterAccept() {
        driver.get("https://the-internet.herokuapp.com/context_menu");
        actions.contextClick(driver.findElement(By.id("hot-spot"))).perform();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean closed = wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        softAssert.assertTrue(closed, "Alert did not close after accept()");
    }

}
