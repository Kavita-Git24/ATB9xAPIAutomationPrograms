package org.APIProgramsPractice.ex05_PayloadManagement;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class APITesting025_RestAssured_Payload_Map {

    //PUT
    //token, bookingid
    RequestSpecification r;
    Response response;
    ValidatableResponse vr;
    String token;
    Integer bookingId;

    @Test
    public void test_Post()
    {
         /*token="14fb28727cbecf2";
        bookingId="1003";
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
                "}";*/

        Map<String,Object> JsonBodyUsingmap=new LinkedHashMap();
        JsonBodyUsingmap.put("firstname","Neha");
        JsonBodyUsingmap.put("lastname","Patel");
        JsonBodyUsingmap.put("totalprice",2000);
        JsonBodyUsingmap.put("depositpaid",true);

        Map<String,Object> BookingDatesmap=new LinkedHashMap();
        BookingDatesmap.put("checkin","2018-01-01");
        BookingDatesmap.put("checkout","2019-01-01");

        JsonBodyUsingmap.put("bookingdates",BookingDatesmap);
        JsonBodyUsingmap.put("additionalneeds","Breakfast");
        //System.out.println(JsonBodyUsingmap);

        // Map -> JSON ? ( GSON, Jackson API)
        // {firstname=Jim, lastname=brown, totalprice=123, depositpaid=true, bookingdates={checkin=2018-01-01, checkout=2019-01-01}, additionalneeds=Breakfast}
        //{"firstname" : "Jim", lastname=brown, totalprice=123, depositpaid=true, bookingdates={checkin=2018-01-01, checkout=2019-01-01}, additionalneeds=Breakfast}

        r= RestAssured.given();
           r.baseUri("https://restful-booker.herokuapp.com/");
           r.basePath("/booking");
           r.contentType(ContentType.JSON);
           r.body(JsonBodyUsingmap).log().all();

        response=r.when().post();

        vr=response.then().log().all().statusCode(200);

        // Rest Assured -> import org.hamcrest.Matchers;
        // Matchers.equalto()
        vr.body("booking.firstname",Matchers.equalTo("Neha"));
        vr.body("booking.lastname",Matchers.equalTo("Patel"));
        vr.body("booking.depositpaid",Matchers.equalTo(true));
        vr.body("bookingid",Matchers.notNullValue());

        // TestNG Assertions  -

        // SoftAssert vs
        // HardAssert -
        // This means that if any assertion fails,
        // the remaining statements in that test method will not be executed.

        bookingId = response.then().extract().path("bookingid");
        String firstname = response.then().extract().path("booking.firstname");
        String lastname = response.then().extract().path("booking.lastname");

        // TestNG Assertions
        Assert.assertNotNull(bookingId);
        Assert.assertEquals(firstname,"Neha");
        Assert.assertEquals(lastname,"Patel");

        // AssertJ( 3rd- Lib to Assertions)
        assertThat(bookingId).isNotNull().isNotZero().isPositive();
        assertThat(firstname).isEqualTo("Neha").isNotEmpty().isNotNull().isNotBlank();
    }
}
