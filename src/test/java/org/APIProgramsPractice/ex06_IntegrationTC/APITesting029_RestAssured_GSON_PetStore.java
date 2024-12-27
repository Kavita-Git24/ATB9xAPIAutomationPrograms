package org.APIProgramsPractice.ex06_IntegrationTC;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.APIProgramsPractice.ex05_PayloadManagement.Gson_demo.UserCreation;
import org.APIProgramsPractice.ex05_PayloadManagement.Gson_demo.UserCreationResponse;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class APITesting029_RestAssured_GSON_PetStore {
    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    @Test
    public void test_Create_Booking()
    {
       UserCreation userCreation=new UserCreation();
       userCreation.setId(5);
       userCreation.setUsername("admin5");
       userCreation.setFirstName("Maruti");
       userCreation.setLastName("Suzuki");
       userCreation.setPhone("98988 98988");
       userCreation.setEmail("maruti@gmail.com");
       userCreation.setPassword("Maruti@10");

        Gson gson=new Gson();
        String jsonString=gson.toJson(userCreation);

        requestSpecification=RestAssured.given();
        requestSpecification.baseUri("https://petstore.swagger.io/v2/user");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonString).log().all();

        Response response=requestSpecification.when().post();
        String jsonResponseString=response.asString();

        validatableResponse=response.then().log().all().statusCode(200);
        UserCreationResponse userCreationResponse=gson.fromJson(jsonResponseString, UserCreationResponse.class);
        System.out.println(userCreationResponse.getCode());


        assertThat(userCreationResponse.getCode()).isNotNull().isNotZero().isPositive();

    }















































































}
