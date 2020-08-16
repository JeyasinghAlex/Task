package com.applicaton.util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RestError {

	static Map<String, String> map = null;
	static Map<String, Integer> mapInt = null;
	static Map<String, Long> mapLong = null;

	public static Response errorResponse() {
		map = new HashMap<String, String>();
		map.put("status", "failed");
		Response.ResponseBuilder rb = Response.ok(map, MediaType.APPLICATION_JSON);
		return rb.build();
	}

	public static Response errorResponse(String key, String value) {
		map = new HashMap<String, String>();
		map.put(key, value);
		Response.ResponseBuilder rb = Response.ok(map, MediaType.APPLICATION_JSON);
		return rb.build();
	}

	public static Response errorResponse(String key, String value, int statusCode) {
		map = new HashMap<String, String>();
		map.put(key, value);
		Response.ResponseBuilder rb = Response.ok(map, MediaType.APPLICATION_JSON);
		rb.status(statusCode);
		return rb.build();
	}

	public static Response errorResponse(JSONObject obj, int statusCode) {
		Response.ResponseBuilder rb = Response.ok(obj.toString(), MediaType.APPLICATION_JSON);
		rb.status(statusCode);
		return rb.build();
	}

	public static Response errorResponse(String key, int value) {
		mapInt = new HashMap<String, Integer>();
		mapInt.put(key, value);
		Response.ResponseBuilder rb = Response.ok(mapInt, MediaType.APPLICATION_JSON);
		return rb.build();
	}

}
