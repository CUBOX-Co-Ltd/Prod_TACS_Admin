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
	
	//private static String GLOBAL_API_IP = CuboxProperties.getProperty("Globals.api.ip");
	//private static String GLOBAL_API_PORT = CuboxProperties.getProperty("Globals.api.port");
	//private static String GLOBAL_API_ID = CuboxProperties.getProperty("Globals.api.id");
	//private static String GLOBAL_API_PASSWORD = CuboxProperties.getProperty("Globals.api.password");
	
	static {
		instance = new ApiUtil();
	}
	
	public static ApiUtil getInstance() {
		return instance;
	}


    /*public static String getLogin(){
    	
    	String loginUrl= "http://" +GLOBAL_API_IP + ":" + GLOBAL_API_PORT+ "/v1/signin";
    	String inputLine = "";
    	String responseJSON = "";
    	
    	String token = JwtTokenRepo.getToken();

    	if(token.equals("")){
    		//login
    		try{
	    		URL url = new URL(loginUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("Accept-Charset", "UTF-8"); 
				conn.setConnectTimeout(5000);
				conn.setReadTimeout(5000);
				
				OutputStream os = conn.getOutputStream();
				String strLogin = "{\"id\": \""+GLOBAL_API_ID+"\",\"password\": \""+GLOBAL_API_PASSWORD+"\"}";
				os.write(strLogin.getBytes());
				os.flush();

				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		        while ((inputLine = in.readLine()) != null) {
		            responseJSON += inputLine;
		        }
		        JSONParser parser = new JSONParser();
				Object obj = parser.parse(responseJSON);
				Map<String, Object> result = CommonUtils.getMapFromJsonObject((JSONObject) obj);
				String code = String.valueOf( result.get("code"));
				if(code.equals("0")){
					token = String.valueOf( result.get("token"));
				}
				in.close();
				conn.disconnect();
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		JwtTokenRepo.setToken(token);
    	}
    	return token;
    }*/


    /*public static HashMap<String, Object> getApiReq(String reqBody, String url){

    	String token = getLogin();
    	
    	String InputLine = "";
    	String responseBody = "";
    	HashMap<String, Object> result = new HashMap<String, Object>();
    	HttpURLConnection matchConn = null;
    	try{
    		URL Url = new URL(url);
    		matchConn = (HttpURLConnection) Url.openConnection();
    		
    		matchConn.setDoOutput(true);
    		matchConn.setRequestMethod("POST");
			matchConn.setRequestProperty("Content-Type", "application/json");
			matchConn.setRequestProperty("X-AUTH-TOKEN", token); 
			matchConn.setRequestProperty("Accept-Charset", "UTF-8"); 
			matchConn.setConnectTimeout(5000);
			matchConn.setReadTimeout(5000);
			
			OutputStream matchOs = matchConn.getOutputStream();
			matchOs.write(reqBody.getBytes("UTF-8"));
			matchOs.flush();

		    int responseCode = matchConn.getResponseCode();

			// 리턴된 결과 읽기
			InputStreamReader isr = null;
		    if(responseCode == 200){
				isr = new InputStreamReader(matchConn.getInputStream(), "UTF-8");
		    }else{
				isr = new InputStreamReader(matchConn.getErrorStream(), "UTF-8");
		    }
			BufferedReader matchIn = new BufferedReader(isr);
	        while ((InputLine = matchIn.readLine()) != null) {
	        	responseBody += InputLine;
	        }
	        
	        JSONParser parser = new JSONParser();
			Object obj = parser.parse(responseBody);
			result = (HashMap<String, Object>) CommonUtils.getMapFromJsonObject((JSONObject) obj);
	        
			String code = String.valueOf(result.get("code"));
			
	        if(code.equals("-1002")){
	    		JwtTokenRepo.setToken("");
	    		getApiReq(reqBody, url);
	        }

    	}catch(Exception e){
    		e.printStackTrace();
    	} finally {
    		if(matchConn!=null) matchConn.disconnect();
		}
    	
    	//System.out.println(token);
    	return result;
    }*/
    
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

