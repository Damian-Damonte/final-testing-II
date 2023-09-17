package testFrontend.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import testFrontend.pages.BasePage;
import testFrontend.pages.RegisterPage;
import testFrontend.reports.ExtentFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Register {
    private RegisterPage registerPage;
    private static ExtentReports extent;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/reporte_test_registro.html");

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
        registerPage = new RegisterPage();
        registerPage.url("https://parabank.parasoft.com/parabank/index.htm");
    }

    @AfterEach
    public void closeRegistePge() throws InterruptedException {
        registerPage.close();
    }

    @Test
    @Tag("register")
    public void crearCuenta() {
        ExtentTest test = extent.createTest("Registro");

        registerPage.toRegister();
        test.log(Status.PASS, "Ingresar a formulario de registro");

        registerPage.escribirFirstName("Juan");
        registerPage.escribirLastName("Fernandez");
        registerPage.escribirAddress("calle falsa 1234");
        registerPage.escribirCity("Tokio");
        registerPage.escribirState("Japon");
        registerPage.escribirZipCode("1234");
        registerPage.escribirPhone("123456789");
        registerPage.escribirSsn("ssn1234");
        registerPage.escribirUsername(UserConstants.USERNAME);
        registerPage.escribirPassword(UserConstants.PASSWORD);
        registerPage.escribirRepeatPassword(UserConstants.PASSWORD);
        test.log(Status.PASS, "Formulario completado");

        registerPage.register();
        test.log(Status.PASS, "Usuario registrado");

        String textoRegistroExitoso = registerPage.obtenerPRegistroExitoso();

        if(textoRegistroExitoso.equals("Your account was created successfully. You are now logged in.")) {
            test.log(Status.PASS, "Registro exitoso");
        } else {
            test.log(Status.FAIL, "Registro fallido");
        }

        assertEquals(
                "Your account was created successfully. You are now logged in.",
                textoRegistroExitoso
        );
    }
}
