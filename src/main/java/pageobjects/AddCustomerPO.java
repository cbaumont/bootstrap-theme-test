package pageobjects;

import driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCustomerPO extends AbstractPageObject {

    WebDriverWait wait;

    @FindBy(id = "field-customerName")
    WebElement customerFirstName;

    @FindBy(id = "field-contactLastName")
    WebElement customerLastName;

    @FindBy(id = "field-contactFirstName")
    WebElement contactFirstName;

    @FindBy(id = "field-phone")
    WebElement phone;

    @FindBy(id = "field-addressLine1")
    WebElement addressFirstLine;

    @FindBy(id = "field-addressLine2")
    WebElement addressSecondLine;

    @FindBy(id = "field-city")
    WebElement city;

    @FindBy(id = "field-state")
    WebElement state;

    @FindBy(id = "field-postalCode")
    WebElement postalCode;

    @FindBy(id = "field-country")
    WebElement country;

    @FindBy(id = "field-creditLimit")
    WebElement creditLimit;

    @FindBy(id = "field-deleted")
    WebElement deleted;

    @FindBy(id = "form-button-save")
    WebElement buttonSave;

    @FindBy(id = "report-success")
    WebElement successMessage;

    public AddCustomerPO() {
        wait = new WebDriverWait(DriverManager.getDriver(), 30);
    }

    public AddCustomerPO typeCustomerFirstName(String name) {
        customerFirstName.sendKeys(name);
        return this;
    }

    public AddCustomerPO typeCustomerLastName(String lastName) {
        customerLastName.sendKeys(lastName);
        return this;
    }

    public AddCustomerPO typeContactName(String contactName) {
        contactFirstName.sendKeys(contactName);
        return this;
    }

    public AddCustomerPO typePhone(String phone) {
        this.phone.sendKeys(phone);
        return this;
    }

    public AddCustomerPO typeAddressFirstLine(String address) {
        addressFirstLine.sendKeys(address);
        return this;
    }

    public AddCustomerPO typeAddressSecondLine(String address) {
        addressSecondLine.sendKeys(address);
        return this;
    }

    public AddCustomerPO typeCity(String city) {
        this.city.sendKeys(city);
        return this;
    }

    public AddCustomerPO typeState(String state) {
        this.state.sendKeys(state);
        return this;
    }

    public AddCustomerPO typePostalCode(String postalCode) {
        this.postalCode.sendKeys(postalCode);
        return this;
    }

    public AddCustomerPO typeCountry(String country) {
        this.country.sendKeys(country);
        return this;
    }

    public AddCustomerPO typeCreditLimit(String creditLimit) {
        this.creditLimit.sendKeys(creditLimit);
        return this;
    }

    public AddCustomerPO typeDeleted(String deleted) {
        this.deleted.sendKeys(deleted);
        return this;
    }

    public AddCustomerPO clickSaveButton() {
        buttonSave.click();
        return this;
    }

    public boolean validateSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOf(successMessage)).isDisplayed();
    }


}
