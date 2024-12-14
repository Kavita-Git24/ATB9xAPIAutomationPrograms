package org.APIProgramsPractice.ex02_RestAssuredBasics.POST;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting009_POST_NonBDDStyle {
    RequestSpecification r;
    Response response;
    ValidatableResponse vr;

    @Description("Verify  the Post Req Positive")
    @Test
    public void test_GET_NonBDD() {
        String payload="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        r= RestAssured.given();
                r.baseUri("https://restful-booker.herokuapp.com");
                r.basePath("/auth");
                r.contentType(ContentType.JSON);
                r.body(payload);

        response=r.when().log().all().post();

        vr=response.then()
                .log().all()
                .statusCode(200);
    }
//    @Description("Verify  the Get Req Negative Input: -1")
//    @Test
//    public void test_GET_NonBDD_negative1() {
//        String payload="{\n" +
//                "    \"username\" : \"admin\",\n" +
//                "    \"password\" : \"password123\"\n" +
//                "}";
//
//        r= RestAssured.given();
//        r.baseUri("https://restful-booker.herokuapp.com")
//        r.basePath("/auth");
//        r.contentType(ContentType.JSON);
//        r.body(payload);
//
//        response = r.when().log().all().post();
//
//        vr=response.then()
//                .log().all()
//                .statusCode(404);
//    }



}
