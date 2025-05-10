import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class FileDownloadTest extends BaseTest {
        @Test
        public void checkDownloadFile() throws InterruptedException {
            String userHome = System.getProperty("user.home");
            String downloadDir = userHome + "/Downloads";
            String fileName = "some-file.txt";
            driver.get("https://the-internet.herokuapp.com/download");
            File downloadedFile = new File(downloadDir + "/" + fileName);
            driver.findElement(By.linkText(
                    fileName)).click();
            int waitTime = 10;
            int waited = 0;
            while (!downloadedFile.exists() && waited < waitTime) {
                Thread.sleep(1000);
                waited++;
            }
            softAssert.assertTrue(downloadedFile.exists(),
                    "Downloaded file is not found in the file system");
            softAssert.assertAll();
            downloadedFile.delete();
        }
    }

