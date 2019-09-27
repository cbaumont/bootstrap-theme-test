package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import pageobjects.CustomersPO;

import static org.testng.Assert.assertEquals;

public class CRUDTest extends BaseTest {

    @Test
    public void validateCustomersPageLoad() {
        CustomersPO customersPO = new CustomersPO();

        String actual = customersPO.getTableTitle();
        String expected = "Customers";

        assertEquals(expected, actual);

        reportPrintPass("Page loaded successfully!");
    }
}
