package org.APIProgramsPractice.ex01_sampleCheck;

import io.restassured.RestAssured;

public class APITesting002 {
    public static void main(String[] args) {
        //Gherkins Syntax
        //Given() - pre requisite - URL,headers,Auth,Body...
        //When() - HTTP method-> Get, Post, Put , Patch , Delete
        //Then() - Validation ->200 OK ,firstname== "Kavita"

        //Full path: "https://api.zippopotam.us/IN/382715"
        //baseUri: https://api.zippopotam.us
        //basePath: /IN/382715
        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/382715")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200);

    }
}
