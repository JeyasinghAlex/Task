package com.utils;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestError {
	
	public static final int STATUS_CODE = 415;
	public static final String ERROR_STATUS = "Unsupported Media Type";
	public static final String STATUS = "Status";
	
	public static Response errorResponse(String key, String value,int statusCode) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, value);
        Response.ResponseBuilder rb = Response.ok(map, MediaType.APPLICATION_JSON);
        rb.status(statusCode);
        return rb.build();
    }
}
