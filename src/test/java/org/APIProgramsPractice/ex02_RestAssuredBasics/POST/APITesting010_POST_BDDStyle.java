package org.APIProgramsPractice.ex02_RestAssuredBasics.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
//https://restful-booker.herokuapp.com/auth
// Content-Type: application/json
//{
//        "username" : "admin",
//        "password" : "password123"
//        }
public class APITesting010_POST_BDDStyle {
    @Test
    public void test_POST_Req_POSITIVE() {
        String payload="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .log().all().body(payload)
                .when()
                .log().all().post()
                .then().log().all()
                .statusCode(200);
    }
    @Test
    public void test_POST_Req_NEGATIVE()
    {
        String payload="{\n" +
                "    \"username\" : \"admin1\",\n" +
                "    \"password\" : \"password\"\n" +
                "}";
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .log().all().body(payload)
                .when()
                .log().all().post()
                .then().log().all()
                .statusCode(404);
    }
}
