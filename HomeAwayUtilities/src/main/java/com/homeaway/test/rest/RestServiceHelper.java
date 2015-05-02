package com.homeaway.test.rest;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.path.json.JsonPath.from;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import com.jayway.restassured.response.Response;


public class RestServiceHelper {
	private String apiEndPoint;
	private String apiKey;
	
	public RestServiceHelper (final Properties config){
		if (null != config){
			this.apiKey = config.getProperty("api.key");
			this.apiEndPoint = config.getProperty("rest.uri");
		}
		if (null != this.apiEndPoint){
			if (this.apiEndPoint.endsWith("/")){
				this.apiEndPoint = this.apiEndPoint.substring(0, this.apiEndPoint.lastIndexOf("/"));
			}
		}
	}
	
	public Response getClosestChargePointNetworkStations(final String city, 
			final String state){
		Response r = get(getGovRestEndPoint() + "/nrel/alt-fuel-stations/v1/nearest.json?api_key="
				+ getAPIKey() + "&location=" + city + "+" + state + "&ev_network=ChargePoint Network");
		return r;
	}
	
	public Response getClosestChargePointNetworkStationBasedOnID(final int stationID){
		Response r = get(getGovRestEndPoint() + "/nrel/alt-fuel-stations/v1/" + String.valueOf(stationID) +
				".json?api_key=" + getAPIKey());
		return r;
	}
	
	public static List<Map<String,?>> getTargetedStations(final String jsonBody, final String stationName){
		return from(jsonBody).param("station_name", stationName).
		get("fuel_stations.findAll {fuel_stations -> fuel_stations.station_name == station_name }");
	}
	
	
	public String getGovRestEndPoint(){
		return this.apiEndPoint;
	}
	
	public String getAPIKey(){
		return this.apiKey;
	}
}