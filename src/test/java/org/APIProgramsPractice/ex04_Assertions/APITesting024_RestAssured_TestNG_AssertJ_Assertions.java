package org.APIProgramsPractice.ex04_Assertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class APITesting024_RestAssured_TestNG_AssertJ_Assertions {
    RequestSpecification r;
    Response response;
    ValidatableResponse vr;
    String token;
    Integer bookingId;
    @Test
    public void test_POST()
    {
        //token= "ff1eb779e276d0f";
        String payload="{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
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
        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        //r.cookie("token",token);
        r.body(payload);

        response=r.when().log().all().post();

        vr=response.then()
                .log().all()
                .statusCode(200);

        //Rest Assured -> import org.hamcrest.Matchers;
        //Matchers.equalto()

        vr.body("bookingid", Matchers.notNullValue());
        vr.body("booking.firstname",Matchers.equalTo("Jim"));
        vr.body("booking.lastname",Matchers.equalTo("Brown"));
        vr.body("booking.depositpaid",Matchers.equalTo(true));
        vr.body("booking",Matchers.notNullValue());


        bookingId=response.then().extract().path("bookingid");
        String firstname=response.then().extract().path("booking.firstname");
        String lastname=response.then().extract().path("booking.lastname");

        //TestNG Assertion
        Assert.assertNotNull(bookingId);
        Assert.assertEquals(firstname,"Jim");
        Assert.assertEquals(lastname,"Brown");

        //AssertJ (3rd lib)
        assertThat(bookingId).isNotNull().isNotZero().isPositive();
        assertThat(firstname).isEqualTo("Jim").isNotEmpty().isNotNull().isAlphanumeric().isNotBlank();
    }

}
