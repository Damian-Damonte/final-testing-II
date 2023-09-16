package pages;

import org.openqa.selenium.By;

public class TransferFundsPage extends BasePage{
    private By btnToTransferFunds = By.linkText("Transfer Funds");
    private By titleTransferFunds = By.xpath("//*[@id=\"rightPanel\"]/div/div/h1");
    private By inputAmount = By.id("amount");
    private By listaFromAccount = By.id("fromAccountId");
    private By fromAccountOption1 = By.xpath("//*[@id=\"fromAccountId\"]/option[1]");
    private By listaToAccount = By.id("toAccountId");
    private By toAccountOption2 = By.xpath("//*[@id=\"toAccountId\"]/option[2]");
    private By btnTransfer = By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div[2]/input");
    private By tituloExito = By.xpath("//*[@id=\"rightPanel\"]/div/div/h1");

    public void toTransferFunds() {
        this.clickear(btnToTransferFunds);
    }
    public String obtenerTitleTransferFunds() {
        return this.getText(titleTransferFunds);
    }
    public void colocarMontoTransferir(String monto) {
        this.sendText(monto, inputAmount);
    }
    public void abrirListaFromAccount() {
        this.clickear(listaFromAccount);
    }
    public void selectFirstFromAccount() {
        this.clickear(fromAccountOption1);
    }
    public void abrirListaToAccount() {
        this.clickear(listaToAccount);
    }
    public void selectSecondToAccount() {
        this.clickear(toAccountOption2);
    }
    public void transfer() {
        this.clickear(btnTransfer);
    }
    public String obtenerTituloExito() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.getText(tituloExito);
    }
}
