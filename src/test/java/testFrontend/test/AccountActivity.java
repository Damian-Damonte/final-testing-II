package testFrontend.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import testFrontend.pages.AccountsOverviewPage;
import testFrontend.pages.BasePage;
import testFrontend.reports.ExtentFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountActivity {
    private AccountsOverviewPage accountsOverviewPage;
    private static ExtentReports extent;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/reporte_test_AccountActivity.html");

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
        accountsOverviewPage = new AccountsOverviewPage();
        accountsOverviewPage.url("https://parabank.parasoft.com/parabank/index.htm");
    }

    @AfterEach
    public void closeRegistePge() throws InterruptedException {
        accountsOverviewPage.close();
    }

    @Test
    public void accountActivity() {
        ExtentTest test = extent.createTest("Account overview");

        accountsOverviewPage.escribirUsername(UserConstants.USERNAME);
        accountsOverviewPage.escribirPassword(UserConstants.PASSWORD);
        accountsOverviewPage.login();
        test.log(Status.PASS, "Login realizado");

        accountsOverviewPage.toAccountOverview();
        test.log(Status.PASS, "Ingresar a pestaña Accounts Overview");

        String textoBalance = accountsOverviewPage.getTextoBalance();

        if(textoBalance.equals("*Balance includes deposits that may be subject to holds")) {
            test.log(Status.PASS, "Texto balance se muestra correctamente");
        } else {
            test.log(Status.FAIL, "Texto balance no se muestra correctamente");
        }

        accountsOverviewPage.seleccionarPrimeraCuenta();
        test.log(Status.PASS, "Seleccionar primera cuenta");

        String tituloAccountDetails = accountsOverviewPage.getTituloAccountDetails();
        if(tituloAccountDetails.equals("Account Details")) {
            test.log(Status.PASS, "Texto Account Details se muestra correctamente");
        } else {
            test.log(Status.FAIL, "Texto Account Details no se muestra correctamente");
        }

        accountsOverviewPage.abrirPeriodList();
        accountsOverviewPage.seleccionarPeriodAll();
        test.log(Status.PASS, "Period all seleccionado");

        accountsOverviewPage.abrirTypeLista();
        accountsOverviewPage.seleccionarTypeAll();
        test.log(Status.PASS, "Type all seleccionado");

        accountsOverviewPage.clickearGo();
        test.log(Status.PASS, "Resultados obtenidos");

        accountsOverviewPage.seleccionarPrimeraOpcionResultado();

        String tituloTransactionDetails = accountsOverviewPage.obtenerTituloTransactionDetails();
        if(tituloTransactionDetails.equals("Transaction Details")) {
            test.log(Status.PASS, "Texto Transaction Details se muestra correctamente");
        } else {
            test.log(Status.FAIL, "Texto Transaction Details no se muestra correctamente");
        }

        assertEquals(
                "Transaction Details",
                tituloTransactionDetails
        );
    }
}
