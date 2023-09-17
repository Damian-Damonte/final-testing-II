package testBackend;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAPI {
    private final String BASE_URL = "https://parabank.parasoft.com/parabank";
    private final static String USERNAME = "juanfer1";
    private final static String PASSWORD = "123456";
    private static String userId;
    private static String cuentaId;


    @Test
    @Order(1)
    public void register() {
        given()
                .baseUri(BASE_URL)
        .when()
                .get("/register.htm")
        .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    public void login() {
        Response response = when().get(BASE_URL + "/services/bank/login/" + USERNAME + "/" + PASSWORD);
        XmlPath xmlPath = new XmlPath(response.getBody().asString());
        String id = xmlPath.getString("customer.id");
        System.out.println("userId: " + id);
        userId = id;
        assertEquals(200, response.getStatusCode());
        assertNotNull(id);
        given().cookie("JSESSIONID", "6067A3F694EB070C87488A9750DD6249").
        when().get(BASE_URL + "/services/bank/login/" + USERNAME + "/" + PASSWORD).then().log().everything();
    }

    @Test
    public void login2() {
        given().formParam("username", "juanfer1").formParam("password", "123456").
                when().get(BASE_URL + "/login.htm").then().log().everything();
    }

    @Test
    @Order(3)
    public void getCuentas() {
        System.out.println("userid: " + userId);
        Response response = when().get(BASE_URL + "/services/bank/customers/" + userId + "/accounts");
        XmlPath xmlPath = new XmlPath(response.getBody().asString());
        cuentaId = xmlPath.getString("accounts.account[0].id");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    @Order(4)
    public void nuevaCuenta() {
        System.out.println("idCuenta: " + cuentaId);
        given()
                .baseUri(BASE_URL)
        .when()
                .post("/services/bank/createAccount?customerId=" + userId +"&newAccountType=1&fromAccountId=" + cuentaId)
        .then()
                .log().everything()
                .statusCode(200);
    }

    @Test
    @Order(5)
    public void resumenCuenta() {
        given()
                .baseUri(BASE_URL)
                .cookie("JSESSIONID", "6067A3F694EB070C87488A9750DD6249")
                .when()
                .get("/overview.htm")
                .then()
                .log().everything()
                .statusCode(200);
    }
//
//    @Test
//    public void descargarFondo() {
//        given()
//                .baseUri(BASE_URL)
//                .when()
//                .post("/services_proxy/bank/transfer?fromAccountId=13566&toAccountId=13677&amount=200")
//                .then()
//                .statusCode(200)
//                .log().everything();
//    }
//
//    @Test
//    public void actividadCuenta() {
//        given()
//                .baseUri(BASE_URL)
//                .when()
//                .get("/services_proxy/bank/accounts/13566/transactions/month/All/type/All")
//                .then()
////                .statusCode(200)
//                .log().everything();
//    }
}
