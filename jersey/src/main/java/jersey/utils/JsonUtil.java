package jersey.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
		
	private static ObjectMapper mapper;
	
	static {
		mapper = new ObjectMapper();
	}
	
	public static void convertJavaToJson(Object object) {
		try {
			mapper.writeValueAsString(object);
		} catch (IOException  e) {
			e.printStackTrace();
		}
	}
}
