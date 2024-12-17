package org.APIProgramsPractice.ex05_PayloadManagement.Gson_demo;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class APITesting028_RestAssured_GSON {
    RequestSpecification r;
    ValidatableResponse vr;
    @Test
    public void test_Create_Booking_Postive()
    {

        Booking booking=new Booking();
        booking.setFirstname("Garima");
        booking.setLastname("Naik");
        booking.setTotalprice(1500);
        booking.setDepositpaid(true);

        Bookingdates bookingdates=new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        System.out.println(booking);

        Gson gson=new Gson();
        String jsonStringBooking=gson.toJson(booking);
        System.out.println(jsonStringBooking);

        r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com/");
        r.basePath("/booking");
        r.contentType(ContentType.JSON);
        r.body(jsonStringBooking).log().all();

        Response response=r.when().post();
        String jsonResponseString=response.asString();

        vr=response.then().log().all().statusCode(200);

//        Case1:extract()- Direct Extraction
        String firstname1 = response.then().extract().path("booking.firstname");
        String lastname1 = response.then().extract().path("booking.lastname");
        System.out.println(firstname1);
        System.out.println(lastname1);

//        Case2: jsonPath.getString(" ") JSON Path Extraction
        JsonPath jsonPath=new JsonPath(response.asString());
        String bookingId=jsonPath.getString("bookingid");
        String firstname=jsonPath.getString("booking.firstname");
        System.out.println(bookingId);
        System.out.println(firstname);

//        Case3: DeSer - Extraction
        BookingResponse bookingResponse=gson.fromJson(jsonResponseString,BookingResponse.class);
        System.out.println(bookingResponse.getBookingid());
        System.out.println(bookingResponse.getBooking().getFirstname());
        System.out.println(bookingResponse.getBooking().getLastname());

        assertThat(bookingResponse.getBookingid()).isNotNull().isNotZero().isPositive();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Garima").isNotEmpty().isNotNull().isNotBlank();

    }
}
