package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	//************ADD BOOKS*******************//
	@Test(dataProvider = "BooksDetails")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		String response = given().log().all()
		.header("Content-Type","application/json").body(payload.Addbook(isbn, aisle))
		.when().log().all()
		.post("/Library/Addbook.php")
		.then().log().all()
		.assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String ID = js.get("ID");
		System.out.println(ID);	
		
		
	}
	
	@DataProvider(name="BooksDetails")
	public Object[][] getData() { 
		
		//array = collection of elements
		//multidimensional array = collection of arrays
		return new Object[][] {{"qoiuweih", "123443165"}, {"asdrry", "4367452"}, {"poiuoiyu", "45676654"}};
	}
	
	
	//************DELETE BOOKS*******************//
	@Test(dataProvider="BooksDetails")
	public void deleteBook(String isbn, String aisle) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String deleteresponse = given()
		.body(payload.Deletebook(isbn, aisle))
		.when()
		.delete("/Library/DeleteBook.php")
		.then()
		.assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println(deleteresponse);
		
		
	}
	
}
