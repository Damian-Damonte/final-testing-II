package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import pages.BasePage;
import reports.ExtentFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Login {
    private BasePage basePage;
    private static ExtentReports extent;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/reporte_test_login.html");

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
        basePage = new BasePage();
        basePage.url("https://parabank.parasoft.com/parabank/index.htm");
    }

    @AfterEach
    public void closeRegistePge() throws InterruptedException {
        basePage.close();
    }

    @Test
    @Tag("login")
    public void login() {
        ExtentTest test = extent.createTest("Login");

        basePage.escribirUsername(UserConstants.USERNAME);
        basePage.escribirPassword(UserConstants.PASSWORD);
        test.log(Status.PASS, "Formulario login completado");

        basePage.login();
        test.log(Status.PASS, "Realizando login");

        String textBtnLogOut = basePage.getTextBtnLogOut();

        if(textBtnLogOut.equals("Log Out")) {
            test.log(Status.PASS, "Login exitoso");
        } else {
            test.log(Status.FAIL, "Login fallido");
        }

        assertEquals(
                "Log Out",
                textBtnLogOut
        );
    }
}
