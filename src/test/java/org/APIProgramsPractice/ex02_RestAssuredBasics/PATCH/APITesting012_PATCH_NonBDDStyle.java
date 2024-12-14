package org.APIProgramsPractice.ex02_RestAssuredBasics.PATCH;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting012_PATCH_NonBDDStyle {

    //PUT
    //token, bookingid
    RequestSpecification r;
    Response response;
    ValidatableResponse vr;
    @Description("Verify the PUT request for the Restful Booker APIs with Positive Input")
    @Test
    public void test_PATCH_NonBDD()
    {
        String token="14fb28727cbecf2";
        String bookingid="1003";
        String patchpayload="{\n" +
                "    \"firstname\" : \"Mohan\",\n" +
                "    \"lastname\" : \"Seth\"\n" +

                "}";
        r= RestAssured.given();
           r.baseUri("https://restful-booker.herokuapp.com");
           r.basePath("/booking/" +bookingid);
           r.contentType(ContentType.JSON);
           r.cookie("token",token);
           r.body(patchpayload).log().all();

        response=r.when().log().all().patch();

        vr=response.then().log().all().statusCode(200);
    }
}
