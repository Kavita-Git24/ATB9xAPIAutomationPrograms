package org.APIProgramsPractice.ex02_RestAssuredBasics.PUT;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting011_PUT_NonBDDStyle {

    //PUT
    //token, bookingid
    RequestSpecification r;
    Response response;
    ValidatableResponse vr;
    @Description("Verify the PUT request for the Restful Booker APIs with Positive Input")
    @Test
    public void test_PUT_NonBDD()
    {
        String token="14fb28727cbecf2";
        String bookingid="1003";
        String putpayload="{\n" +
                "    \"firstname\" : \"Komal\",\n" +
                "    \"lastname\" : \"Advani\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        r= RestAssured.given();
           r.baseUri("https://restful-booker.herokuapp.com");
           r.basePath("/booking/" +bookingid);
           r.contentType(ContentType.JSON);
           r.cookie("token",token);
           r.body(putpayload).log().all();

        response=r.when().log().all().put();

        vr=response.then().log().all().statusCode(200);
    }

//    @Description("Verify the PUT request for the Restful Booker APIs with Negative Input")
//    @Test
    public void test_PUT_NonBDD_Negative()
    {
        String token="570cda5ed58280c2";
        String bookingid="abc";
        String payload="{\n" +
                "    \"firstname\" : \"Kiara\",\n" +
                "    \"lastname\" : \"Advant\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/" +bookingid);
        r.contentType(ContentType.JSON);
        r.cookie("token",token);
        r.body(payload).log().all();

        response=r.when().log().all().put();

        vr=response.then().log().all().statusCode(200);
    }
}
