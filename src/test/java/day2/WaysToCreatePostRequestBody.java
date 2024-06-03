package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;


public class WaysToCreatePostRequestBody {

	
	@Test
	public void testPostUsingHashMap()
	{
		
		HashMap data=new HashMap();
		data.put("name", "ankhi");
		data.put("location", "mursidabad");
		data.put("phone", "7679003733");
		String [] coursesArr= {"Anotomy","Nursing"};
		data.put("courses", coursesArr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("ankhi"))
			.body("phone", equalTo("7679003733"))
			.body("courses[0]", equalTo("Anotomy"))
			.body("courses[1]", equalTo("Nursing"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
		
	}
	
	@Test(priority = 2, dependsOnMethods = "testPostUsingHashMap")
	public void testDelete()
	{
		given()
		.when()
			.delete("http://localhost:3000/students/4")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	public void testPostUsingOrgJsonLibrary()
	{
		JSONObject data=new JSONObject();
		data.put("name", "ankhi");
		data.put("location", "mursidabad");
		data.put("phone", "7679003733");
		String [] coursesArr= {"Anotomy","Nursing"};
		data.put("courses", coursesArr);
		
	given()
		.contentType("application/json")
		.body(data.toString())
	.when()
		.post("http://localhost:3000/students")
	.then()
		.statusCode(201)
		.body("name", equalTo("ankhi"))
		.body("phone", equalTo("7679003733"))
		.body("courses[0]", equalTo("Anotomy"))
		.body("courses[1]", equalTo("Nursing"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();
	}
	@Test(priority = 4, dependsOnMethods = "testPostUsingOrgJsonLibrary" )
	public void deleteJsonData()
	{
		given()
		.when()
			.delete("http://localhost:3000/students/4")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	@Test(priority = 5)
	public void testPostUsingPOJOClass()
	{
		POJO_PostRequest data=new POJO_PostRequest();
		data.setName("ankhi");
		data.setLocation("mursidabad");
		data.setPhone("7679003733");
		
		String [] coursesArr= {"Anotomy","Nursing"};
		
		data.setCourses(coursesArr);
		
	given()
		.contentType("application/json")
		.body(data)
	.when()
		.post("http://localhost:3000/students")
	.then()
		.statusCode(201)
		.body("name", equalTo("ankhi"))
		.body("phone", equalTo("7679003733"))
		.body("courses[0]", equalTo("Anotomy"))
		.body("courses[1]", equalTo("Nursing"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();	
		
	}
	
	
	@Test(priority = 6, dependsOnMethods = "testPostUsingPOJOClass")
	public void deletePojoData()
	{
		given()
		.when()
			.delete("http://localhost:3000/students/4")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 7)
	public void testPostUsingExternalJSONFile() throws FileNotFoundException
	{
		File file=new File("C:\\Users\\SANTOSH\\RestAssuredTraining\\RestAssuredTraining\\src\\test\\resources\\body.json");
		java.io.FileReader fr=new java.io.FileReader(file);
		JSONTokener jt=new JSONTokener(fr);
		JSONObject data=new JSONObject(jt);
		
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("ankhi"))
			.body("phone", equalTo("7679003733"))
			.body("courses[0]", equalTo("Anotomy"))
			.body("courses[1]", equalTo("Nursing"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
	}
	
	
	@Test(priority = 8,dependsOnMethods = "testPostUsingExternalJSONFile"  )
	public void deleteDataJSONFile()
	{
		given()
		.when()
			.delete("http://localhost:3000/students/4")
		.then()
			.statusCode(200)
			.log().all();
	}
}
