package com.applicaton.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.applicaton.model.Cart;
import com.applicaton.model.Product;

public class RestSuccess {

	static Map<String, String> map = null;
	static Map<String, Integer> mapInt = null;
	static Map<String, Long> mapLong = null;

	public static Response successResponse() {
		map = new HashMap<String, String>();
		map.put("status", "success");
		ResponseBuilder rb = Response.ok(map, MediaType.APPLICATION_JSON);
		return rb.build();
	}

	public static Response successResponse(String key, String value) {
		map = new HashMap<String, String>();
		map.put(key, value);
		ResponseBuilder rb = Response.ok(map, MediaType.APPLICATION_JSON);
		return rb.build();
	}

	public static Response successResponse(String key, int value) {
		mapInt = new HashMap<String, Integer>();
		mapInt.put(key, value);
		ResponseBuilder rb = Response.ok(mapInt, MediaType.APPLICATION_JSON);
		return rb.build();
	}

	public static Response successResponse(Cart cart) {
		ResponseBuilder rb = Response.ok(cart, MediaType.APPLICATION_JSON);
		return rb.build();
	}
	
	public static Response successResponse(Product data) {
		ResponseBuilder rb = Response.ok(data, MediaType.APPLICATION_JSON);
		return rb.build();
	}

	public static Response successResponse(List<Product> students) {
		ResponseBuilder rb = Response.ok(students, MediaType.APPLICATION_JSON);
		return rb.build();
	}

//	public static Response successResponse(Set<String> data) {
//		ResponseBuilder rb = Response.ok(data, MediaType.APPLICATION_JSON);
//		return rb.build();
//	}

//	public static Response successResponse(Map<String, Integer> map) {
//		ResponseBuilder rb = Response.ok(map, MediaType.APPLICATION_JSON);
//		return rb.build();
//	}

//	public static Response successResponse(JSONArray data) {
//		ResponseBuilder rb = Response.ok(data.toString(), MediaType.APPLICATION_JSON);
//		return rb.build();
//	}

//	public static Response successResponse(JSONObject data) {
//		ResponseBuilder rb = Response.ok(data.toString(), MediaType.APPLICATION_JSON);
//		return rb.build();
//	}
}
