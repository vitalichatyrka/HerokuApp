import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class FileUploadTest extends BaseTest{
@Test
    public void checkUploadFile() {
    driver.get("https://the-internet.herokuapp.com/upload");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("file-upload")));
    String filePath = new File("src/test/java/AddRemoveElementsTest.java").getAbsolutePath();
    driver.findElement(By.xpath("//input[@class='dz-hidden-input']")).sendKeys(
            filePath);
    assertEquals(driver.findElement(By.xpath(
            "//div[@class='dz-filename']/span")).getText(), "AddRemoveElementsTest.java");
    softAssert.assertAll();
}
}
