package pages;

import org.openqa.selenium.By;

public class RegisterPage extends BasePage{
    private By btnToRegister = By.linkText("Register");
    private By inputFirstName = By.id("customer.firstName");
    private By inputLastName = By.id("customer.lastName");
    private By inputAddress = By.id("customer.address.street");
    private By inputCity = By.id("customer.address.city");
    private By inputState = By.id("customer.address.state");
    private By inputZipCode = By.id("customer.address.zipCode");
    private By inputPhoneNumber = By.id("customer.phoneNumber");
    private By inputSsn = By.id("customer.ssn");
    private By inputUsername = By.id("customer.username");
    private By inputPassword = By.id("customer.password");
    private By inputRepeatedPassword = By.id("repeatedPassword");
    private By btnRegister = By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input");
    private By pRegistroExitoso = By.xpath("//*[@id=\"rightPanel\"]/p");

    public void toRegister() {
        this.clickear(btnToRegister);
    }
    public void escribirFirstName(String firstName) {
        this.sendText(firstName, inputFirstName);
    }
    public void escribirLastName(String lastName) {
        this.sendText(lastName, inputLastName);
    }
    public void escribirAddress(String address) {
        this.sendText(address, inputAddress);
    }
    public void escribirCity(String city) {
        this.sendText(city, inputCity);
    }
    public void escribirState(String state) {
        this.sendText(state, inputState);
    }
    public void escribirZipCode(String zipCode) {
        this.sendText(zipCode, inputZipCode);
    }
    public void escribirPhone(String phone) {
        this.sendText(phone, inputPhoneNumber);
    }
    public void escribirSsn(String ssn) {
        this.sendText(ssn, inputSsn);
    }
    public void escribirUsername(String username) {
        this.sendText(username, inputUsername);
    }
    public void escribirPassword(String password) {
        this.sendText(password, inputPassword);
    }
    public void escribirRepeatPassword(String repeatPassword) {
        this.sendText(repeatPassword, inputRepeatedPassword);
    }
    public void register() {
        this.clickear(btnRegister);
    }
    public String obtenerPRegistroExitoso() {
        return this.getText(pRegistroExitoso);
    }
}
