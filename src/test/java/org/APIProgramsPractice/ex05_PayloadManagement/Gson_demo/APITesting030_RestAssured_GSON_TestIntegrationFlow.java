package org.APIProgramsPractice.ex05_PayloadManagement.Gson_demo;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.requestSpecification;
import static org.assertj.core.api.Assertions.assertThat;

public class APITesting030_RestAssured_GSON_TestIntegrationFlow {
    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    @Description("TC#INT1 - Step 1. Verify that the User is Created")
    @Test(groups = "qa", priority = 1)
    @Owner("Tester")
    public void test_Create_Booking(ITestContext iTestContext) {
        UserCreation userCreation = new UserCreation();
        userCreation.setId(160);
        userCreation.setUsername("admin160");
        userCreation.setFirstName("Maruti");
        userCreation.setLastName("Suzuki");
        userCreation.setPhone("98988 98988");
        userCreation.setEmail("maruti@gmail.com");
        userCreation.setPassword("Maruti@10");

        Gson gson = new Gson();
        String jsonString = gson.toJson(userCreation);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://petstore.swagger.io/v2/user");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonString).log().all();

        Response response = requestSpecification.when().post();
        String jsonResponseString = response.asString();

        validatableResponse = response.then().log().all().statusCode(200);
        UserCreationResponse userCreationResponse = gson.fromJson(jsonResponseString, UserCreationResponse.class);
        System.out.println(userCreationResponse.getCode());

        assertThat(userCreationResponse.getCode()).isNotNull().isNotZero().isPositive();
        iTestContext.setAttribute("UserName", userCreation.getUsername());
    }

    @Test(groups = "qa", priority = 2)
    @Owner("Tester")
    @Description("TC#INT1 - Step 2. Verify that the User Creation By ID")
    public void testVerifyBookingId(ITestContext iTestContext) {
        String userid = (String) iTestContext.getAttribute("UserName");
        String basepath = "/" + userid;
        System.out.println(basepath);
        Gson gson = new Gson();
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://petstore.swagger.io/v2/user");
        requestSpecification.basePath(basepath).log().all();

        Response response = requestSpecification.when().get();
        String jsonResponseString = response.asString();

        validatableResponse = response.then().log().all().statusCode(200);
        UserCreation userResponse = gson.fromJson(jsonResponseString, UserCreation.class);
        System.out.println(userResponse.getUsername());

        assertThat(userResponse.getId()).isNotNull().isNotZero().isPositive();
        assertThat(userResponse.getFirstName()).isNotNull().isNotBlank();
        assertThat(userResponse.getFirstName()).isEqualTo("Maruti");
    }

    @Description("TC#INT1 - Step 3. Verify that the User is Updated")
    @Test(groups = "qa", priority = 3)
    @Owner("Tester")
    public void test_Update_UserCreation(ITestContext iTestContext) {
        String userid = (String) iTestContext.getAttribute("UserName");
        String basepath = "/" + userid;
        System.out.println(basepath);

        UserCreation userCreation = new UserCreation();
        userCreation.setId(160);
        userCreation.setUsername("admin160");
        userCreation.setFirstName("Innova");
        userCreation.setLastName("Suzuki");
        userCreation.setPhone("88888 98988");
        userCreation.setEmail("innova@gmail.com");
        userCreation.setPassword("Innova@10");

        Gson gson = new Gson();
        String jsonString = gson.toJson(userCreation);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://petstore.swagger.io/v2/user");
        requestSpecification.basePath(basepath);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonString).log().all();

        Response response = requestSpecification.when().put();
        String jsonResponseString = response.asString();

        validatableResponse = response.then().log().all().statusCode(200);
        UserCreationResponse userCreationResponse = gson.fromJson(jsonResponseString, UserCreationResponse.class);
        System.out.println(userCreationResponse.getCode());

        assertThat(userCreationResponse.getCode()).isNotNull().isNotZero().isPositive();
        iTestContext.setAttribute("UserName", userCreation.getUsername());
    }

    @Description("TC#INT1 - Step 4. Verify that the User is Deleted")
    @Test(groups = "qa", priority = 4)
    @Owner("Tester")
    public void test_Delete_UserCreation(ITestContext iTestContext) {
        String userid = (String) iTestContext.getAttribute("UserName");
        String basepath = "/" + userid;
        System.out.println(basepath);

        Gson gson = new Gson();

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://petstore.swagger.io/v2/user");
        requestSpecification.basePath(basepath);
        requestSpecification.contentType(ContentType.JSON).log().all();

        Response response = requestSpecification.when().delete();
        String jsonResponseString = response.asString();

        validatableResponse = response.then().log().all().statusCode(200);
        UserCreationResponse userCreationResponse = gson.fromJson(jsonResponseString, UserCreationResponse.class);
        System.out.println(userCreationResponse.getCode());

        assertThat(userCreationResponse.getCode()).isNotNull().isNotZero().isPositive();
    }
}