package org.APIProgramsPractice.ex02_RestAssuredBasics.GET;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting008_GET_NonBDDStyle {
    RequestSpecification r;
    Response response;
    ValidatableResponse vr;

    @Description("Verify  the Get Req Positive")
    @Test
    public void test_GET_NonBDD() {
        String pincode = "560016";

        r= RestAssured
                .given();
                r.baseUri("https://api.zippopotam.us");
                r.basePath("/IN/" + pincode);

        response=r.when().log().all().get();

        vr=response.then()
                .log().all()
                .statusCode(200);
    }
    @Description("Verify  the Get Req Negative Input: -1")
    @Test
    public void test_GET_NonBDD_negative1() {
        String pincode = "-1";

        r= RestAssured
                .given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/" + pincode);

        response=r.when().log().all().get();

        vr=response.then()
                .log().all()
                .statusCode(404);
    }

    @Description("Verify  the Get Req Negative Input: null")
    @Test
    public void test_GET_NonBDD_negative2() {
        String pincode = "null";

        r= RestAssured
                .given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/" + pincode);

        response=r.when().log().all().get();

        vr=response.then()
                .log().all()
                .statusCode(404);
    }

    @Description("Verify  the Get Req Negative Input: null,blank")
    @Test
    public void test_GET_NonBDD_negative3() {
        String pincode = "null,blank";

        r= RestAssured
                .given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/" + pincode);

        response=r.when().log().all().get();

        vr=response.then()
                .log().all()
                .statusCode(404);
    }

}
