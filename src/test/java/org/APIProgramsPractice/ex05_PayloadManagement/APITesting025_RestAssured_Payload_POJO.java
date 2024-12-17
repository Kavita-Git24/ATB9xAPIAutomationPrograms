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

public class APITesting025_RestAssured_Payload_POJO {

    //PUT
    //token, bookingid
    RequestSpecification r;
    Response response;
    ValidatableResponse vr;
    Integer bookingId;
    @Test
    public void test_Post() {

     /*  String putpayload="{\n" +
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
        Booking booking=new Booking();
        booking.setFirstname("Neha");
        booking.setLastname("Patel");
        booking.setTotalprice(2000);
        booking.setDepositpaid(true);

        BookingDates bookingdates=new BookingDates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");

        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        r= RestAssured.given();
           r.baseUri("https://restful-booker.herokuapp.com/");
           r.basePath("/booking");
           r.contentType(ContentType.JSON);
           r.body(booking).log().all();

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
