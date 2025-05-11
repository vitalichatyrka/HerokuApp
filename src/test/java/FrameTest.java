import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class FrameTest extends BaseTest {

    @Test
    public void testFrame() {
        driver.get("https://the-internet.herokuapp.com/iframe");
        driver.findElement(By.xpath("//div[@class='tox-icon']")).click();
        driver.switchTo().frame("mce_0_ifr");
        assertEquals(driver.findElement(By.id("tinymce")).getText(), "Your content goes here.");
    }
}
