import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

public class ContextMenuTest extends BaseTest {

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
        boolean closed = wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        assertTrue(closed, "Alert did not close after accept()");
    }

}
