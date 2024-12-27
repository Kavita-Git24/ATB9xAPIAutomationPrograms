package org.APIProgramsPractice.ex02_RestAssuredBasics.GET;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.APIProgramsPractice.ex05_PayloadManagement.Gson_demo.BookingResponse;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class APITesting002_GET_PetStore {
    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    @Test
    public void test_GET_User(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://petstore.swagger.io/v2/user");
        requestSpecification.basePath("/admin13");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.log().all();

        Response respone=requestSpecification.when().get();

        validatableResponse=respone.then().log().all();
        validatableResponse.statusCode(200);

        /* bookingResponse=gson.fromJson(jsonResponseString,BookingResponse.class);
        System.out.println(bookingResponse.getBookingid());
        System.out.println(bookingResponse.getBooking().getFirstname());
        System.out.println(bookingResponse.getBooking().getLastname());
        BookingResponse
        assertThat(bookingResponse.getBookingid()).isNotNull().isNotZero().isPositive();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Garima").isNotEmpty().isNotNull().isNotBlank();*/
    }
}
