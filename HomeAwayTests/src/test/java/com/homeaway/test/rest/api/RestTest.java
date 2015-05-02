package com.homeaway.test.rest.api;

import static com.jayway.restassured.path.json.JsonPath.from;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.homeaway.test.config.TestConfig;
import com.homeaway.test.rest.RestServiceHelper;
import com.jayway.restassured.response.Response;

@Test(groups= {"api-gov", "all"})
public class RestTest {
	private Properties config = TestConfig.getConfigProperties();
	private int station_id = -1;
	private RestServiceHelper rsh;
	
	@BeforeClass
	public void init(){
		rsh = new RestServiceHelper (config);
	}
	
	@Test
	public void testOne(){
		Response r = rsh.getClosestChargePointNetworkStations("Austin", "TX");
		
		Assert.assertTrue(r.getStatusCode() == 200, "The response code should be 200.");
		String rb = r.body().asString();
		
		List<Map<String, ?>> stations = RestServiceHelper.getTargetedStations(rb, "HYATT AUSTIN");
		Assert.assertTrue(stations.size() == 1, "There should only be 1 item returned.");
		station_id = (Integer) stations.get(0).get("id");
		Assert.assertTrue(station_id != -1, "The station id should have been set.");
	}
	
	@Test(dependsOnMethods = "testOne")
	public void testTwo(){
		Response r = rsh.getClosestChargePointNetworkStationBasedOnID(station_id);
		Assert.assertTrue(r.getStatusCode() == 200, "The response code should be 200.");
		String address = from(r.body().asString()).get("alt_fuel_station.street_address");
		Assert.assertTrue(address.length() > 0, "The address should not be empty.");
	}
}