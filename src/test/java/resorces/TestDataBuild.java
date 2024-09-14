package resorces;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address)
	{
		AddPlace p = new AddPlace();
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setWebsite("http://google.com");
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		Location obj = new Location();
		obj.setLat(-38.383494);
		obj.setLng(33.427362);
		p.setLocation(obj);
		
		return p;
	}
	public String deletePlacePayload(String place_Id)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+place_Id+"\"\r\n"
				+ "}\r\n"
				+ "";
	}

}
