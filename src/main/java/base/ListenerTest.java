package base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.service.ExtentService;
import com.aventstack.extentreports.service.ExtentTestManager;
import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Base64;


public class ListenerTest implements ITestListener {

    protected static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        reportPrintFail();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        report(Status.SKIP, "SKIP Test");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentService.getInstance().flush();
    }


    public void reportPrintPass(String log) {
        reportPrint(Status.PASS, log);
    }

    public void reportPrintFail() {
        reportPrintFail("");
    }

    public void reportPrintFail(String log) {
        reportPrint(Status.FAIL, log);
    }

    public void reportPrintInfo() {
        reportPrintInfo("");
    }

    public void reportPrintInfo(String log) {
        reportPrint(Status.INFO, log);
    }

    public void reportInfo(String log) {
        report(Status.INFO, log);
    }

    public void reportPass(String log) {
        report(Status.PASS, log);
    }

    public void reportFail(String log) {
        report(Status.FAIL, log);
    }

    private void reportPrint(Status status, String log) {
        try {
            LOGGER.info(log);
            ExtentTestManager.getTest().log(status, log,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot()).build());
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    private void report(Status status, String log) {
        LOGGER.info(log);
        ExtentTestManager.getTest().log(status, log);
    }

    private String takeScreenshot() throws IOException {
        String screenshotsPath = System.getProperty("user.dir") + "/target/reports/img/" + System.currentTimeMillis() + ".png";
        File screenshot = new File(screenshotsPath);
        if (!screenshot.getParentFile().exists()) {
            screenshot.getParentFile().mkdirs();
        }
        screenshot.createNewFile();

        File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, screenshot);

        byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        return "data:image/png;base64," + encodedString;
    }
}
