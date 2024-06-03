package day1;
/*
  gerkin-keywords
 	given()
  		(content type, set cookies, add auth, add param, set headers info etc..)
  	when()
 		(get, post, put, delete)
 	then()
 		(validate status code, extract response, extract headers cookies & response body..)
 */

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;


public class HTTPRequests {
	int id;
	
	@Test(priority = 1)
	public void getUsers()
	{
		System.out.println("changes done");
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
		
		
	}
	@Test(priority = 2)
	public void createUser()
	{
		HashMap data=new HashMap();
		data.put("name","santosh");
		data.put("job","Test Engineer");
		
		id=given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/Ausers")
			.jsonPath().getInt("id");
		/*.then()
			.statusCode(201)
			.log().all() */
		
	}
	@Test(priority = 3, dependsOnMethods = "createUser")
	public void updateUser()
	{
		HashMap data=new HashMap();
		data.put("name","santosh");
		data.put("job","Automation Test Engineer");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();
			
	}
	@Test(priority = 4, dependsOnMethods = "createUser")
	public void deleteUser()
	{
		given()
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
	}
	
	

}
