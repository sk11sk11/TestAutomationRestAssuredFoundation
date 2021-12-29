package com.restassured.foundation;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndtoEnd {
	
	
	String URL = "http://localhost:3030/services";
	String newservice = "asiandeli";
	String postRequestBody = "{\"name\": \"SNAME\"}";
	String updatedSN = "american_bistro";

	@Test
	public void addservice() {

		//Send a POST request
		RequestSpecification request = given();
		request.header("content-type", "application/json");
		request.body(postRequestBody.replace("SNAME", newservice));

		//Do a POST and and store in response variable
		Response response = request.post(URL);

		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 201);
		System.out.println(response.asPrettyString());

		String serviceID = response.jsonPath().getString("id");
		System.out.println(serviceID);
		
		//GET the newly added service and validate the response content 
		Response getresponse = get(URL + "/"+serviceID);
		//validations
		System.out.println(getresponse.getStatusCode());
		Assert.assertEquals(getresponse.getStatusCode(), 200);
		System.out.println(getresponse.asPrettyString());
		Assert.assertEquals(getresponse.jsonPath().getString("name"), newservice);
		
		
		//PUT request -- update the newservice name
		RequestSpecification PUTrequest = given();
		PUTrequest.header("content-type", "application/json");
		PUTrequest.body(postRequestBody.replace("SNAME", updatedSN));
		
		Response putResponse = PUTrequest.put(URL + "/"+ serviceID);
		System.out.println(putResponse.getStatusCode());
		Assert.assertEquals(putResponse.getStatusCode(), 200);
		System.out.println(putResponse.asPrettyString());
		Assert.assertEquals(putResponse.jsonPath().getString("name"), updatedSN);
		
		
		//GET the newly updated servicename and validate the response content 
		Response getUpdatedResponse = get(URL + "/"+ serviceID);
		Assert.assertEquals(getUpdatedResponse.jsonPath().getString("name"), updatedSN);
		Assert.assertEquals(getUpdatedResponse.getStatusCode(), 200);
		System.out.println(getUpdatedResponse.getStatusCode());
		
		//DELETE the newly updated servicename
		Response deleteResponse = delete(URL + "/"+ serviceID);
		Assert.assertEquals(deleteResponse.getStatusCode(), 200);
		
		Response getDeleteResponse = get(URL + "/"+ serviceID);
		System.out.println(getDeleteResponse.getStatusCode());
		Assert.assertEquals(getDeleteResponse.jsonPath().getString("message"), "No record found for id '"+serviceID+"'");
		Assert.assertEquals(getDeleteResponse.jsonPath().getString("name"), "NotFound");
		System.out.println(getDeleteResponse.asPrettyString());
	}

}
