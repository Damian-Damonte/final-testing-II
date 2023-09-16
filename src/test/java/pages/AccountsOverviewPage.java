package pages;

import org.openqa.selenium.By;

public class AccountsOverviewPage extends BasePage{
    private By btnAccountOverview = By.linkText("Accounts Overview");
    private By textoBalance = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");
    public void toAccountOverview() {
        this.clickear(btnAccountOverview);
    }
    public String getTextoBalance() {
        return this.getText(textoBalance);
    }
}
