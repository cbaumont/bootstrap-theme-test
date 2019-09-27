package pageobjects;

import driver.DriverManager;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import property.PropertyLoader;

public abstract class AbstractPageObject {

    protected AbstractPageObject() {
        int timeout = Integer.parseInt(PropertyLoader.getConfigSelenium().get("timeout"));
        PageFactory.initElements(new AjaxElementLocatorFactory(DriverManager.getDriver(), timeout), this);
    }
}
