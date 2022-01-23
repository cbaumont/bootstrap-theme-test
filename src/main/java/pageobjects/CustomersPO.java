package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomersPO extends AbstractPageObject {

    @FindBy(xpath = "//div[contains(text(), 'Customers')]")
    WebElement customersTableTitle;

    @FindBy(css = ".fa-plus")
    WebElement addCustomerButton;

    public CustomersPO() {
    }

    public String getTableTitle() {
        return customersTableTitle.getText();
    }

    public AddCustomerPO clickAddCustomer() {
        addCustomerButton.click();
        return new AddCustomerPO();
    }

}
