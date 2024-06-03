package day3;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.Map;

public class CookiesAndHeaders {
	// We get Cookies and Header in the response

	@Test(priority = 1)
	public void testCookies() 
	{
		given()
			
		.when()
			.get("https://www.google.com/")
		.then()
			.log().all()
		;
	}
	@Test(priority = 2)
	public void getCookieInfo()
	{
		Response res=given()
		
		.when()
			.get("https://www.google.com/");
		
		//get single cookie info
	/*	String cookieValue= res.getCookie("AEC");
		System.out.println("The value of Cookie is:"+cookieValue);
	*/	
		//get all cookies info
		
		Map<String, String> cookies_values = res.cookies();
		
		System.out.println("List of cookies"+cookies_values.keySet());
		
		for(String key:cookies_values.keySet())
		{
			String cookie_value=res.getCookie(key);
			System.out.println(key+" :"+cookie_value);
		}
	}
	
	@Test(priority = 3)
	public void testHeader()
	{
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws")
		;
	}
	@Test(priority = 4)
	public void getHeaderInfo()
	{
		Response res = given()
		.when()
			.get("https://www.google.com/");
		
		//get single header info
		
		String header_name = res.getHeader("Content-Type");
		System.out.println(header_name);
		
		//get all header info
		/*
		Headers header_names = res.headers();
		for(Header name:header_names)
		{
			System.out.println(name.getName()+": "+name.getValue());
		}
		*/
	}

}
