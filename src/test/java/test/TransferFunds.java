package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import pages.BasePage;
import pages.TransferFundsPage;
import reports.ExtentFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferFunds {
    private TransferFundsPage transferFundsPage;
    private static ExtentReports extent;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/reporte_test_transferFunds.html");

    @BeforeAll
    public static void setupDriver() {
        BasePage.setupChromeDriver();
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);

    }
    @AfterAll
    public static void reporte() {
        extent.flush();
    }

    @BeforeEach
    public void setupRegisterPage() throws InterruptedException {
        transferFundsPage = new TransferFundsPage();
        transferFundsPage.url("https://parabank.parasoft.com/parabank/index.htm");
    }

    @AfterEach
    public void closeRegistePge() throws InterruptedException {
        transferFundsPage.close();
    }

    @Test
    public void transferFunds() {
        ExtentTest test = extent.createTest("Transfer funds");

        transferFundsPage.escribirUsername(UserConstants.USERNAME);
        transferFundsPage.escribirPassword(UserConstants.PASSWORD);
        transferFundsPage.login();
        test.log(Status.PASS, "Login realizado");

        transferFundsPage.toTransferFunds();
        test.log(Status.PASS, "Ingresar a pestaña transfer funds");

        String tituloTransferFunds = transferFundsPage.obtenerTitleTransferFunds();
        if(tituloTransferFunds.equals("Transfer Funds")) {
            test.log(Status.PASS, "Titulo transfer funds correcto");
        } else {
            test.log(Status.FAIL, "Titulo transfer funds incorrecto");
        }

        transferFundsPage.colocarMontoTransferir("200");
        test.log(Status.PASS, "Colocar monto a transferir");

        transferFundsPage.abrirListaFromAccount();
        transferFundsPage.selectFirstFromAccount();
        test.log(Status.PASS, "From account seleccionada");

        transferFundsPage.abrirListaToAccount();
        transferFundsPage.selectSecondToAccount();
        test.log(Status.PASS, "To account seleccionada");

        transferFundsPage.transfer();

        String textoTransferenciaExito = transferFundsPage.obtenerTituloExito();

        if(textoTransferenciaExito.equals("Transfer Complete!")) {
            test.log(Status.PASS, "Transferencia realizada con exito");
        } else {
            test.log(Status.FAIL, "Error al realizar la transferencia");
        }

        assertEquals(
                "Transfer Complete!",
                textoTransferenciaExito
        );
    }
}
