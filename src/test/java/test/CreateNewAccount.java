package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import pages.BasePage;
import pages.NewAccountPage;
import reports.ExtentFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateNewAccount {
    private NewAccountPage newAccountPage;
    private static ExtentReports extent;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/reporte_test_newAccount.html");

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
        newAccountPage = new NewAccountPage();
        newAccountPage.url("https://parabank.parasoft.com/parabank/index.htm");
    }

    @AfterEach
    public void closeRegistePge() throws InterruptedException {
        newAccountPage.close();
    }

    @Test
    @Tag("newAccount")
    public void newAccount() {
        ExtentTest test = extent.createTest("New account");

        newAccountPage.escribirUsername(UserConstants.USERNAME);
        newAccountPage.escribirPassword(UserConstants.PASSWORD);
        newAccountPage.login();
        test.log(Status.PASS, "Realizando login");


        newAccountPage.toOpenNewAccount();
        test.log(Status.PASS, "Ingresar a pestaña Open New Account");

        newAccountPage.openListAccountType();
        newAccountPage.selectTypeSavings();
        newAccountPage.openNewAccount();
        test.log(Status.PASS, "Nueva cuenta abierta");

        String textoExito = newAccountPage.obtenerTextoExito();

        if(textoExito.equals("Congratulations, your account is now open.")) {
            test.log(Status.PASS, "Nueva cuenta abierta con exito");
        } else {
            test.log(Status.FAIL, "Error al abrir nueva cuenta");
        }

        assertEquals(
                "Congratulations, your account is now open.",
                textoExito
        );
    }
}
