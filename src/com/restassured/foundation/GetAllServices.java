package com.restassured.foundation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAllServices {

	String URL = "http://localhost:3030/services";

	@Test
	public void returnServices() {

		Response response = RestAssured.get(URL);
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(response.getStatusCode());
		System.out.println(response.asPrettyString());

		// *****Extracting count of all the services*****//
		int NoOfServices = response.jsonPath().getInt("data.id.size()");
		System.out.println(NoOfServices);

		
		// *****Extracting serviceIDs and Names*******//
		for (int i = 0; i < NoOfServices; i++) {
			int service = response.jsonPath().get("data[" + i + "].id");
			String serviceName = response.jsonPath().get("data[" + i + "].name");
			System.out.println(service + " , " + serviceName);
		}

	}

}
