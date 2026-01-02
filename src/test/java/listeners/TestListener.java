package listeners;

import io.qameta.allure.Allure;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        ScreenshotUtils.takeScreenshot();

        Allure.addAttachment(
                "Failure Reason",
                result.getThrowable().getMessage()
        );
    }
}
