package pages;

import org.openqa.selenium.By;

public class AccountsOverviewPage extends BasePage{
    private By toAccountOverview = By.linkText("Accounts Overview");
    private By textoBalance = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");
    private By primeraCuenta = By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a");
    private By textoAccountDetails = By.xpath("//*[@id=\"rightPanel\"]/div/div[1]/h1");
    private By listaActivityPeriod = By.id("month");
    private By periodAllOption = By.xpath("//*[@id=\"month\"]/option[1]");
    private By listaActivityType = By.id("transactionType");
    private By typeAllOption = By.xpath("//*[@id=\"transactionType\"]/option[1]");
    private By btnGo = By.xpath("//*[@id=\"rightPanel\"]/div/div[2]/form/table/tbody/tr[3]/td[2]/input");
    private By primeraOpcionResultado = By.xpath("//*[@id=\"transactionTable\"]/tbody/tr[1]/td[2]/a");
    private By tituloTransactionDetails = By.xpath("//*[@id=\"rightPanel\"]/h1");

    public void toAccountOverview() {
        this.clickear(toAccountOverview);
    }
    public String getTextoBalance() {
        return this.getText(textoBalance);
    }
    public void seleccionarPrimeraCuenta() {
        this.clickear(primeraCuenta);
    }
    public String getTituloAccountDetails() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.getText(textoAccountDetails);
    }
    public void abrirPeriodList() {
        this.clickear(listaActivityPeriod);
    }
    public void seleccionarPeriodAll() {
        this.clickear(periodAllOption);
    }
    public void abrirTypeLista() {
        this.clickear(listaActivityType);
    }
    public void seleccionarTypeAll() {
        this.clickear(typeAllOption);
    }
    public void clickearGo() {
        this.clickear(btnGo);
    }

    public void seleccionarPrimeraOpcionResultado() {
        this.clickear(primeraOpcionResultado);
    }
    public String obtenerTituloTransactionDetails() {
        return this.getText(tituloTransactionDetails);
    }
}
