package testFrontend.pages;

import org.openqa.selenium.By;

public class NewAccountPage extends BasePage {
    private By btnNewAccount = By.linkText("Open New Account");
    private By listAccountType = By.xpath("//*[@id=\"type\"]");
    private By optionSavings = By.xpath("//*[@id=\"type\"]/option[2]");
    private By btnOpenNewAccount = By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div/input");
    private By textoExito = By.xpath("//*[@id=\"rightPanel\"]/div/div/p[1]");

    public void toOpenNewAccount() {
        this.clickear(btnNewAccount);
    }
    public void openListAccountType() {
        this.clickear(listAccountType);
    }
    public void selectTypeSavings() {
        this.clickear(optionSavings);
    }
    public void openNewAccount() {
        this.clickear(btnOpenNewAccount);
    }
    public String obtenerTextoExito() {
        return this.getText(textoExito);
    }
}
