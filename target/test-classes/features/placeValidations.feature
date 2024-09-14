Feature: Validating place API's
@AddPlace @Regression
Scenario Outline: Verify if place is being Successfully added using AddPlace API 
	Given Add place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "post" http request
	Then the API call is success with stutas code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name  | language | address          |
	|abc   | english  | tester home      |
	|tester| hindi    | Automation tester|

@DeletePlace @Regression
Scenario: Verify if Delete place functinality is working
		
		Given DeletePlace Payload
		When user calls "deletePlaceAPI" with "POST" http request
		Then the API call is success with stutas code 200
		And "status" in response body is "OK"
		
	
	