package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) {
            return new byte[0];
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
