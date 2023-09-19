package testBackend;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import testFrontend.reports.ExtentFactory;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAPI {
    private final String BASE_URL = "https://parabank.parasoft.com/parabank";
    private final static String USERNAME = "juanfer1";
    private final static String PASSWORD = "123456";
    private static String userId;
    private static String cuentaId1;
    private static String cuentaId2;
    private static ExtentReports extent;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/reporte_test_back.html");

    @BeforeAll
    public static void setupDriver() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);

    }
    @AfterAll
    public static void reporte() {
        extent.flush();
    }


    @Test
    @Order(1)
    @Tag("testBack")
    public void register() {
        ExtentTest test = extent.createTest("Register");
        Response response = given().baseUri(BASE_URL).when().get("/register.htm");

        if(response.getStatusCode() == 200) {
            test.log(Status.PASS, "Página register devuelve status 200 corectamente");
        } else {
            test.log(Status.FAIL, "Página register no devuelve status 200");
        }
        assertEquals(200, response.getStatusCode());
    }

    @Test
    @Order(2)
    @Tag("testBack")
    public void login() {
        ExtentTest test = extent.createTest("Login");
        Response response = when().get(BASE_URL + "/services/bank/login/" + USERNAME + "/" + PASSWORD);
        XmlPath xmlPath = new XmlPath(response.getBody().asString());
        userId = xmlPath.getString("customer.id");

        if(response.getStatusCode() == 200) {
            test.log(Status.PASS, "Endpoint login devuelve status 200 correctamente");
        } else {
            test.log(Status.FAIL, "Endpoint login no devuelve status 200");
        }

        if(userId != null) {
            test.log(Status.PASS, "Id de usuario obtenido correctamente");
        } else {
            test.log(Status.FAIL, "No se pudo obtener el id del usuario");
        }

        assertEquals(200, response.getStatusCode());
        assertNotNull(userId);
    }

    @Test
    @Order(3)
    @Tag("testBack")
    public void getCuentas() {
        ExtentTest test = extent.createTest("Obtener cuentas");
        Response response = when().get(BASE_URL + "/services/bank/customers/" + userId + "/accounts");
        XmlPath xmlPath = new XmlPath(response.getBody().asString());
        cuentaId1 = xmlPath.getString("accounts.account[0].id");

        if(response.getStatusCode() == 200) {
            test.log(Status.PASS, "Endpoint obtener cuentas devuelve status 200 correctamente");
        } else {
            test.log(Status.FAIL, "Endpoint obtener cuentas no devuelve status 200");
        }

        if(cuentaId1 != null) {
            test.log(Status.PASS, "Id de la cuenta del usuario obtenido correctamente");
        } else {
            test.log(Status.FAIL, "No se pudo obtener el id de la cuenta del usuario");
        }

        assertEquals(200, response.getStatusCode());
        assertNotNull(cuentaId1);
    }

    @Test
    @Order(4)
    @Tag("testBack")
    public void nuevaCuenta() {
        ExtentTest test = extent.createTest("Abir nueva cuenta");
        Response response = given().baseUri(BASE_URL).when()
                .post("/services/bank/createAccount?customerId=" + userId +"&newAccountType=1&fromAccountId=" + cuentaId1);

        XmlPath xmlPath = new XmlPath(response.getBody().asString());
        cuentaId2 = xmlPath.getString("account.id");

        if(response.getStatusCode() == 200) {
            test.log(Status.PASS, "Endpoint abrir cuenta devuelve status 200 correctamente");
        } else {
            test.log(Status.FAIL, "Endpoint abrir cuenta no devuelve status 200");
        }

        if(cuentaId2 != null) {
            test.log(Status.PASS, "Cuenta creada correctamente");
        } else {
            test.log(Status.FAIL, "Error al obtener id de cuenta creada");
        }

        assertEquals(200, response.getStatusCode());
        assertNotNull(cuentaId2);
    }

    @Test
    @Order(5)
    @Tag("testBack")
    public void trasferir() {
        ExtentTest test = extent.createTest("Transferir");
        Response response = given().baseUri(BASE_URL).when()
                .post("/services/bank/transfer?fromAccountId=" + cuentaId1 +"&toAccountId=" + cuentaId2 +"&amount=250");

        String mensajeResponse = response.getBody().asString();
        String mensajeEsperado = "Successfully transferred $250 from account #" + cuentaId1 + " to account #" + cuentaId2;

        if(response.getStatusCode() == 200) {
            test.log(Status.PASS, "Endpoint transferir devuelve status 200 correctamente");
        } else {
            test.log(Status.FAIL, "Endpoint transferir no devuelve status 200");
        }

        if(mensajeEsperado.equals(mensajeResponse)) {
            test.log(Status.PASS, "Transferencia realizada correctamente");
        } else {
            test.log(Status.FAIL, "Error al realizar transferencia");
        }

        assertEquals(200, response.getStatusCode());
        assertEquals(mensajeEsperado, mensajeResponse);
    }

    @Test
    @Order(6)
    @Tag("testBack")
    public void actividadCuenta() {
        ExtentTest test = extent.createTest("Actividad cuenta");

        Response response = given().baseUri(BASE_URL).when()
                .get("/services/bank/accounts/" + cuentaId1 +"/transactions/month/All/type/All");

        String responseBody = response.getBody().asString();

        if(response.getStatusCode() == 200) {
            test.log(Status.PASS, "Endpoint actividad cuenta devuelve status 200 correctamente");
        } else {
            test.log(Status.FAIL, "Endpoint actividad cuenta no devuelve status 200");
        }

        if(responseBody != null && !responseBody.isEmpty()) {
            test.log(Status.PASS, "Endpoint actividad cuenta devuelve informacion correctamente");
        } else {
            test.log(Status.FAIL, "Endpoint actividad cuenta no devuelve informacion");
        }

        assertEquals(200, response.getStatusCode());
        assertNotNull(responseBody);
        assertNotEquals("", responseBody);
    }
}
