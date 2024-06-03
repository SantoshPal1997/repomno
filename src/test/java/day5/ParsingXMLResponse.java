package day5;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
public class ParsingXMLResponse {
	@Test
	public void testXMLResponseOne()
	{
		//Approach-1
		given()
			
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"))
			;
	}
	@Test
	public void testXMLResponseTwo()
	{
		Response res = given()
				.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		String contentType = res.getHeader("Content-Type");
		Assert.assertEquals(contentType, "application/xml; charset=utf-8");
		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");
		String name = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(name, "Developer");
	}
	@Test
	public void testXMLResponseThree()
	{
		Response res = given()
				.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlObj=new XmlPath(res.asString());
		//TypeCastong Response type to String Type
		//I want to find all Travelers information
		
		List<Object> nameList = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		boolean status=false;
		for(Object name:nameList)
		{
			if(name.equals("Develper"))
			{
				status=true;
				break;
			}
		}
		if(!status)
		{
			System.out.println("Traveler name is not present.");
			Assert.assertTrue(status);
		  //Assert.assertEquals(status, true);
		}
	}

}
