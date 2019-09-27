package base;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import driver.DriverFactory;
import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import property.PropertyLoader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Listeners({ListenerTest.class, ExtentITestListenerClassAdapter.class})
public abstract class BaseTest extends ListenerTest {

    protected static final String URL_BASE = PropertyLoader.getTestData().get("url");

    @BeforeMethod
    @Parameters("browser")
    public void preCondition(@Optional("chrome") String browser, Method method) {
        try {
            WebDriver driver = DriverFactory.createDriver(browser);
            DriverManager.setDriver(driver);

            DriverManager.getDriver().get(URL_BASE);
            driver.manage().window().maximize();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        DriverManager.quit();
    }

    @AfterSuite
    public void updateReport() {
        try {
            String reportPath = "target/reports/test-report.html";
            String htmlContent = FileUtils.readFileToString(new File(reportPath), "utf-8");
            String pattern = "href='([^'].*)' data-featherlight";
            Pattern r = Pattern.compile(pattern);
            Matcher matcher = r.matcher(htmlContent);
            Set<String> keyList = new HashSet();

            while (matcher.find()) {
                keyList.add(matcher.group(1));
            }

            for (String data : keyList) {
                String oldString = data + "' data-featherlight='image'>";
                htmlContent = htmlContent.replace(oldString, oldString + "<img src='" + data + "' style=\"width:150px\">");
            }

            htmlContent = htmlContent.replace("<span class='label grey badge white-text text-white'>base64-img</span>", "");

            FileUtils.writeStringToFile(new File(reportPath), htmlContent, "utf-8");
        } catch (Exception e) {
            LOGGER.error("An error occurred while updating the test report: " + e.getMessage());
        }
    }
}
