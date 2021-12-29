package com.restassured.foundation;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class createNewService {

	String URL = "http://localhost:3030/services";

	@Test
	public void addservice() {

		RequestSpecification request = given();
		request.header("content-type", "application/json");
		request.body("{\"name\": \"asian food court\"}");

		Response response = request.post(URL);

		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 201);
		System.out.println(response.asPrettyString());

		String serviceID = response.jsonPath().getString("id");
		System.out.println(serviceID);

	}

}
