package org.APIProgramsPractice.ex05_PayloadManagement.Gson_demo;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

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
       // String jsonResponse
        validatableResponse=response.then().log().all().statusCode(200);
    }















































































}
