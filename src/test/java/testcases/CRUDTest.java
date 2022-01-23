package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import pageobjects.AddCustomerPO;
import pageobjects.CustomersPO;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CRUDTest extends BaseTest {

    @Test
    public void validateCustomersPageLoad() {
        CustomersPO customersPO = new CustomersPO();

        String actual = customersPO.getTableTitle();
        String expected = "Customers";

        assertEquals(expected, actual);

        reportPrintPass("Page loaded successfully!");
    }

    @Test
    public void validateCreationOfNewCustomer() {
        CustomersPO customersPO = new CustomersPO();
        AddCustomerPO addCustomerPO = customersPO.clickAddCustomer();

        boolean successMessageIsDisplayed =
                addCustomerPO.typeCustomerFirstName("Customer Test")
                        .typeCustomerLastName("WebDriver")
                        .typeContactName("Friend Test")
                        .typePhone("9828882920")
                        .typeAddressFirstLine("24 Lemon St")
                        .typeAddressSecondLine("Apt 101")
                        .typeCity("Fresno")
                        .typeState("CA")
                        .typePostalCode("12345")
                        .typeCountry("USA")
                        .typeCreditLimit("2000")
                        .typeDeleted("No")
                        .clickSaveButton()
                        .validateSuccessMessage();
        
        assertTrue(successMessageIsDisplayed);

        reportPrintPass("Customer creation validated successfully!");
    }
}
