package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before("@DeletePlace")
	public void BeforeScenario() throws IOException
	{
		// write a codeto get the place id 
		// execute this code only when placeid is null
		stepDefination m = new stepDefination();
		if(stepDefination.place_id == null)
		{
		m.add_place_payload_with("abc", "hindi", "bilkhura");
		m.user_calls_with_http_request("AddPlaceAPI", "post");
		m.verify_place_id_created_maps_to_using("abc", "getPlaceAPI");
		}
	}

}
