package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resorces.APIResources;
import resorces.TestDataBuild;
import resorces.Utils;

public class stepDefination extends Utils {

	ResponseSpecification resspec;
	RequestSpecification request;
	 Response response ;
	 static String place_id;
	
	 TestDataBuild data  = new TestDataBuild();
	 @Given("Add place Payload with {string} {string} {string}")
	 public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		
		 
		
		 request= given().log().all()
		.spec(requestSpecification())
		.body(data.addPlacePayload(name, language, address));
	}
	 @When("user calls {string} with {string} http request")
	 public void user_calls_with_http_request(String resorce, String method) {
	    // Write code here that turns the phrase above into concrete actions
		
		APIResources resorceAPI = APIResources.valueOf(resorce);
		System.out.println(resorceAPI.getReesource());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("Post"))
		  response = request.when().post(resorceAPI.getReesource());
		else if(method.equalsIgnoreCase("Get"))
			 response = request.when().get(resorceAPI.getReesource());
		
	}
	@Then("the API call is success with stutas code {int}")
	public void the_api_call_is_success_with_stutas_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals( response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void is_response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
	    
	    assertEquals(getJsonPath(response, keyValue),expectedValue);
	}
	
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	   
	    place_id = getJsonPath(response, "place_id");
		request= given().log().all()
				.spec(requestSpecification()).queryParam("place_id", getJsonPath(response, "place_id"));
		
		user_calls_with_http_request(resource, "GET");
		
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
		
	   
	}
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   
		request= given().log().all()
				.spec(requestSpecification())
				.body(data.deletePlacePayload(place_id));
	}


}
