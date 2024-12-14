package org.APIProgramsPractice.ex02_RestAssuredBasics.DELETE;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting013_DELETE_NonBDDStyle {

    //PUT
    //token, bookingid
    RequestSpecification r;
    Response response;
    ValidatableResponse vr;
    @Description("Verify the DELETE request for the Restful Booker APIs with Positive Input")
    @Test
    public void test_DELETE_NonBDD()
    {
        String token="14fb28727cbecf2";
        String bookingid="1003";

        r= RestAssured.given();
           r.baseUri("https://restful-booker.herokuapp.com");
           r.basePath("/booking/" +bookingid);
           r.contentType(ContentType.JSON);
           r.cookie("token",token).log().all();

        response=r.when().log().all().delete();

        vr=response.then().log().all().statusCode(201);
    }

}
