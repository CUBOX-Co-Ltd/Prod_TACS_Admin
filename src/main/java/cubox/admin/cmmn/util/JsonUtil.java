package cubox.admin.cmmn.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.core.util.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static JsonUtil instance;
	
	static {
		instance = new JsonUtil();
	}
	
	public static JsonUtil getInstance() {
		return instance;
	}
	
    /**
     * Map을 json으로 변환한다.
     *
     * @param map Map<String, Object>.
     * @return JSONObject.
     */
    public static JSONObject getJsonStringFromMap( Map<String, Object> map )
    {
        JSONObject jsonObject = new JSONObject();
        for( Map.Entry<String, Object> entry : map.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            jsonObject.put(key, value);
        }

        return jsonObject;
    }

    /**
     * List<Map>을 jsonArray로 변환한다.
     *
     * @param list List<Map<String, Object>>.
     * @return JSONArray.
     */
    public static JSONArray getJsonArrayFromList( List<Map<String, Object>> list )
    {
        JSONArray jsonArray = new JSONArray();
        for( Map<String, Object> map : list ) {
            jsonArray.add( getJsonStringFromMap( map ) );
        }

        return jsonArray;
    }

    
    /**
     * List<Map>을 jsonArray로 변환한다.
     *
     * @param list List<Map<String, Object>>.
     * @return JSONArray.
     */
    public static JSONArray getJsonArrayFromList2( List<HashMap> list )
    {
        JSONArray jsonArray = new JSONArray();
        for( HashMap map : list ) {
            jsonArray.add( getJsonStringFromMap( map ) );
        }

        return jsonArray;
    }

    /**
     * List<Map>을 jsonString으로 변환한다.
     *
     * @param list List<Map<String, Object>>.
     * @return String.
     */
    public static String getJsonStringFromList( List<Map<String, Object>> list )
    {
        JSONArray jsonArray = getJsonArrayFromList( list );
        return jsonArray.toJSONString();
    }

    /**
     * JsonObject를 Map<String, String>으로 변환한다.
     *
     * @param jsonObj JSONObject.
     * @return Map<String, Object>.
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMapFromJsonObject( JSONObject jsonObj )
    {
        Map<String, Object> map = null;

        try {

            map = new ObjectMapper().readValue(jsonObj.toJSONString(), Map.class) ;

        } catch (JsonParseException e) {
        	map = null;
        } catch (JsonMappingException e) {
        	map = null;
        } catch (IOException e) {
        	map = null;
        }

        return map;
    }

    /**
     * JsonArray를 List<Map<String, String>>으로 변환한다.
     *
     * @param jsonArray JSONArray.
     * @return List<Map<String, Object>>.
     */
    public static List<Map<String, Object>> getListMapFromJsonArray( JSONArray jsonArray )
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        if( jsonArray != null )
        {
            int jsonSize = jsonArray.size();
            for( int i = 0; i < jsonSize; i++ )
            {
                Map<String, Object> map = getMapFromJsonObject( ( JSONObject ) jsonArray.get(i) );
                list.add( map );
            }
        }

        return list;
    }



    /**
     * request to responseBody
     * @param request HttpServletRequest.
     * @return String
     */
	public static String getResponseBody(HttpServletRequest request) {
	    String strJson;
		try {
			strJson = IOUtils.toString(request.getReader());
		} catch (IOException e1) {
			strJson = "";
		}
        return strJson;
    }



    /**
     * json to map
     * @param strJson String.
     * @return HashMap<String, Object>.
	public static HashMap<String, Object> getJsonToMap(String strJson) {
		HashMap<String, Object> value = new Gson().fromJson(strJson, HashMap.class);
		return value;
	}
     */


    /**
     * json to map
     * @param jsonObj JSONObject.
     * @return HashMap<String, Object>.
     */
    public static HashMap<String, Object> getJsonToMap(String aStrJson)
    {
    	JSONParser parser = new JSONParser();
    	HashMap<String, Object> map = null;
        try {
        	Object obj = parser.parse(aStrJson);
        	JSONObject jsonObj = (JSONObject) obj;
        	map = getJsonObjectToMap(jsonObj);
        } catch (Exception e) {
        	map = null;
        } 
        return map;
    }



    /**
     * json to map
     * @param jsonObj JSONObject.
     * @return HashMap<String, Object>.
     */
    public static HashMap<String, Object> getJsonObjectToMap( JSONObject jsonObj )
    {
    	HashMap<String, Object> map = null;        
        try {            
            map = new ObjectMapper().readValue(jsonObj.toJSONString(), HashMap.class) ;            
        } catch (JsonParseException e) {
        	map = null;
        } catch (JsonMappingException e) {
        	map = null;
        } catch (IOException e) {
        	map = null;
        }
 
        return map;
    }

}
