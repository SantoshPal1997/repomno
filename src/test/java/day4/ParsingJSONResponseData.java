package day4;

import static org.hamcrest.CoreMatchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class ParsingJSONResponseData {
	@Test
	public void testJsonResponseOne()
	{
		//Approach-1
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/student_data")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("[0].location",equalTo("india"));
	}
	
	@Test
	public void testJsonResponseTwo()
	{
		//Approach-2
		
		Response res = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/student_data");
		
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
		Assert.assertEquals(res.getStatusCode(),200);
		String location = res.jsonPath().get("[0].location").toString();
		Assert.assertEquals(location, "india");
	}
	@Test
	public void testJsonResponseThree()
	{
		Response res = given()
				.contentType(ContentType.JSON)
			.when()
				.get("http://localhost:3000/student_data");
		
		JSONObject jo=new JSONObject(res.toString());
		
		//res.toString()
		// Have to Type cast Response Type To JSONObject Type
		// I want to print all the name
		for(int i=0;i<jo.getJSONArray("student_data").length();i++)
		{
			String name = jo.getJSONArray("student_data").getJSONObject(i).get("name").toString();
			System.out.println(name);
		}
	}
}
