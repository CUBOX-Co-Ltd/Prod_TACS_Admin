package cubox.admin.cmmn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiUtil {

	private static ApiUtil instance;
	
	private static String GLOBAL_API_IP = CuboxProperties.getProperty("Globals.api.ip");
	private static String GLOBAL_API_PORT = CuboxProperties.getProperty("Globals.api.port");
	private static String GLOBAL_API_ID = CuboxProperties.getProperty("Globals.api.id");
	private static String GLOBAL_API_PASSWORD = CuboxProperties.getProperty("Globals.api.password");
	
	static {
		instance = new ApiUtil();
	}
	
	public static ApiUtil getInstance() {
		return instance;
	}

    public static HashMap<String, Object> getApiReq (String url){
    	String InputLine = "";
    	String responseBody = "";
    	HashMap<String, Object> result = new HashMap<String, Object>();
    	HttpURLConnection conn = null;
    	try{
    		URL Url = new URL(url);
    		conn = (HttpURLConnection) Url.openConnection();
    		
    		conn.setDoOutput(true);
    		conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept-Charset", "UTF-8"); 
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(20000);
			
		    int responseCode = conn.getResponseCode();

			// 리턴된 결과 읽기
			InputStreamReader isr = null;
		    if(responseCode == 200){
				isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
		    }else{
				isr = new InputStreamReader(conn.getErrorStream(), "UTF-8");
		    }
			BufferedReader in = new BufferedReader(isr);
	        while ((InputLine = in.readLine()) != null) {
	        	responseBody += InputLine;
	        }
	        
	        JSONParser parser = new JSONParser();
			Object obj = parser.parse(responseBody);
			result = (HashMap<String, Object>) CommonUtils.getMapFromJsonObject((JSONObject) obj);
	        
    	}catch(Exception e){
    		e.printStackTrace();
    	} finally {
    		if(conn!=null) conn.disconnect();
		}
    	
    	//System.out.println(token);
    	return result;
    }

}

