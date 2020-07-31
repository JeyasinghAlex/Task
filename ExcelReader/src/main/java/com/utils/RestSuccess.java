package com.utils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.simple.JSONObject;

public class RestSuccess {
	
	public static Response successResponse(JSONObject data) {
        ResponseBuilder rb = Response.ok(data.toString(), MediaType.APPLICATION_JSON);
        return rb.build();
    }
}
