package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import pages.AccountsOverviewPage;
import pages.BasePage;
import reports.ExtentFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountsOverview {
    private AccountsOverviewPage accountsOverviewPage;
    private static ExtentReports extent;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/reporte_test_AccountBalance.html");

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
    @Tag("accountOverview")
    public void accoutOverviwe() {
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

        assertEquals(
                "*Balance includes deposits that may be subject to holds",
                textoBalance
        );
    }
}
