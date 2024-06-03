package day6;

import java.io.File;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import io.restassured.response.Response;



public class testFileUploadAndDownload {
	@Test
	public void testSingleFileUpload()
	{
		
		File myfile=new File("C:\\Users\\SANTOSH\\Desktop\\API Testing\\testFile\\demoFile.txt");
		
	        given()
				.multiPart("file",myfile)
				.contentType("multipart/form-data")
			
			.when()
				.post("http://localhost:3000/upload")
				
			.then()
				.log().body()
				.log().headers()
				.log().cookies()
				.log().status();
		
			
			
				
	}
	

}
