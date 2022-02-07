package cubox.admin.cmmn.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository("commonUtils")
public class CommonUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

	/**
	 * 오늘 날짜를 리턴하는 함
	 * @return yyyyMMdd형식의 날짜를 리턴
	 */
	public String getToday(){
		return getToday("yyyyMMdd");
	}
	/**
	 * 문자열 형태로 날짜를 리턴
	 * @param pattern	출력할 날짜 형태
	 * @return
	 */
	public String getToday(String pattern){
		Date date = new Date();
		pattern = getDatePattern(pattern);
		return getStringDate(date, pattern);
	}
	/**
	 * 오늘 날짜를 Timestamp형태로 리턴하는 함
	 * @return Timestamp
	 */
	public Timestamp getTodayTimeStamp(){
		return getTimeStamp(new Date());
	}
	/**
	 * Timestamp 형태로 날짜를 리턴
	 * @param dat	TimeStamp로 출력할 Date 객체
	 * @return Timestamp
	 */
	public Timestamp getTimeStamp(Date dat){
		return new Timestamp(dat.getTime());
	}
	/**
	 * Date 타입의 날짜 데이터를 문자열 형태로 변경
	 * @param date	Date 타입의 기준 데이터를 입력
	 * @return	문자열 형태의 데이터를 리턴(yyyyMMdd형태)
	 */
	public String getStringDate(Date date){
		if(date == null){
			date = new Date();
		}
		return getStringDate( date, "yyyyMMdd");
	}
	/**
	 * Timestamp 타입의 날짜 데이터를 문자열 형태로 변경
	 * @param date	Date 타입의 기준 데이터를 입력
	 * @return	문자열 형태의 데이터를 리턴(yyyyMMdd형태)
	 */
	public String getStringDate(Timestamp date){
		if(date == null){
			date = new Timestamp(new Date().getTime());
		}
		return getStringDate( date, "yyyyMMdd");
	}
	/**
	 * @param date		Date 타입의 기준 데이터를 입력
	 * @param pattern	출력할 날짜 형태
	 * @return			문자열 형태의 데이터를 리턴(yyyyMMdd형태)
	 */
	public String getStringDate(Date date, String pattern){
		if(date == null){
			date = new Date();
		}

		DateFormat sdf = null;
		pattern = getDatePattern(pattern);
		sdf = new SimpleDateFormat(pattern);

		return sdf.format(date);
	}

	/**
	 * 문자열 형태의 날짜의 차이를 구함(일단위 비교)
	 * @param strDate		기준 날짜(String)
	 * @param interval		차이
	 * @return
	 * @throws ParseException
	 */
	public String getStringDateDiff(String strDate, int interval) throws ParseException{
		return getStringDateDiff(strDate, interval, "yyyyMMdd","d");
	}

	/**
	 * 문자열 형태의 날짜의 차이를 구함(패턴 비교) - 마지막 단위가 비교단위임
	 * @param strDate		기준 날짜(String)
	 * @param interval		차이
	 * @param datePattern	날짜 패턴(기본은 yyyyMMdd)
	 * @param unit			차이 단위
	 * @return
	 * @throws ParseException
	 */
	public String getStringDateDiff(String strDate, int interval, String pattern) throws ParseException{
		String unit = pattern.replaceAll("^[y|M|d|H|m|s]","") ;
		return getStringDateDiff(strDate, interval, pattern,unit.substring(unit.length()-1,unit.length()));
	}

	/**
	 * 문자열 형태의 날짜의 차이를 구함
	 * @param strDate		기준 날짜(String)
	 * @param interval		차이
	 * @param datePattern	날짜 패턴(기본은 yyyyMMdd)
	 * @param unit			차이 단위
	 * @return
	 * @throws ParseException
	 */
	public String getStringDateDiff(String strDate, int interval, String datePattern, String unit) throws ParseException{
		datePattern = getDatePattern(datePattern);

		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		Date specimenDate = sdf.parse(strDate);
		Date resultDate = new Date();

		// 비교 단위 선택(기본은 일)
		switch(unit){
		case "y":
			resultDate = DateUtils.addYears(specimenDate, interval);
			break;
		case "M":
			resultDate = DateUtils.addMonths(specimenDate, interval);
			break;
		case "d":
			resultDate = DateUtils.addDays(specimenDate, interval);
			break;
		case "H":
			resultDate = DateUtils.addHours(specimenDate, interval);
			break;
		case "m":
			resultDate = DateUtils.addMinutes(specimenDate, interval);
			break;
		case "s":
			resultDate = DateUtils.addSeconds(specimenDate, interval);
			break;
		default:
			resultDate = DateUtils.addDays(specimenDate, interval);
		}

		return getStringDate(resultDate, datePattern);
	}

	public String getDatePattern(String pattern){
		// 날짜포맷 선택(기본은 yyyyMMdd)
		switch(pattern){
		case "yyyy":	break;
		case "yyyyMM":	break;
		case "yyyyMMdd":	break;
		case "yyyyMMddHHmmss":	break;
		case "MM/dd/yyyy":	break;
		case "yyyy-MM":	break;
		case "yyyy-MM-dd":	break;
		case "yyyy-MM-dd HH:mm":	break;
		case "yyyy-MM-dd HH:mm:ss":	break;
		case "yyyyMMddHHmmssSSSS":	break;
		case "yyyyMMddHHmmssSSS":	break;
		case "HH":	break;
		case "mm":	break;
		case "ss":	break;
		case "HHmmss":	break;
		case "SSSS":	break;
		default:
			pattern = "yyyyMMdd";
		}
		return pattern;
	}

	public String getLastStringDate(String strDate, String pattern) throws Exception{
		pattern = getDatePattern(pattern);
		String result = strDate;

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date theDay = sdf.parse(strDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(theDay);

		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);

		Date lastDayOfMonth = cal.getTime();

		result = sdf.format(lastDayOfMonth);
		return result;
	}

	public int getDayInteger(String date, String pattern) throws ParseException{
		pattern = getDatePattern(pattern);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date theDay = sdf.parse(date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(theDay);

		int dayNum = cal.get(Calendar.DAY_OF_WEEK);


		return dayNum;
	}

	public boolean isWeekDay(String strDate, String pattern) throws ParseException{
		boolean result = false;
		int dayNum = getDayInteger(strDate, pattern);
		switch(dayNum){
	        case 1:
	        case 7:
	        	result = false;
	            break ;
	        case 2:
	        case 3:
	        case 4:
	        case 5:
	        case 6:
	            result = true;
	            break ;
	    }
		return result;
	}

	public boolean isWeekend(String strDate, String pattern) throws ParseException{
		boolean result = false;
		int dayNum = getDayInteger(strDate, pattern);
		switch(dayNum){
		case 1:
		case 7:
			result = true;
			break ;
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			result = false;
			break ;
		}
		return result;
	}

	public boolean isTimeString(String strTime){
		return isDateString(strTime, "HHmmss");
	}

	public boolean isTimeString(String strTime, String pattern){
		boolean result = false;
		pattern = getDatePattern(pattern);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try{
			Date theTime = sdf.parse(strTime);
			result = true;
		}catch(ParseException e){
			LOGGER.error(strTime+" 는 시간 유형이 아닙니다.");
		}
		return result;
	}

	public boolean isDateString(String strDate){
		return isDateString(strDate, "yyyyMMdd");
	}

	public boolean isDateString(String strDate, String pattern){
		boolean result = false;
		pattern = getDatePattern(pattern);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			Date theDay = sdf.parse(strDate);
			result = true;
		} catch (ParseException e) {
			LOGGER.error(strDate+" 는 날짜 유형이 아닙니다.");
		}
		return result;
	}

	public Timestamp convertTimestampYmdHms(String strDate) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date specimenDate = sdf.parse(strDate);
		return getTimeStamp(specimenDate);
	}

	public Timestamp convertTimestampYmd(String strDate) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date specimenDate = sdf.parse(strDate);
		return getTimeStamp(specimenDate);
	}

	public Timestamp convertTimestamp(String strDate, String datePattern) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		Date specimenDate = sdf.parse(strDate);
		return getTimeStamp(specimenDate);
	}

	public Date convertDateYmdHms(String strDate) throws ParseException{
		return convertDate(strDate, "yyyyMMddHHmmss");
	}
	public Date convertDateYmd(String strDate) throws ParseException{
		return convertDate(strDate, "yyyyMMdd");
	}

	public Date convertDate(String strDate, String datePattern) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		Date specimenDate = sdf.parse(strDate);
		return specimenDate;
	}

	/**
	 * Request에서 IP정보를 가지고 온다.
	 *
	 * @param req		입력받은 Request
	 * @return			IP정보를 리턴
	 */
	public String getIPFromRequest(HttpServletRequest req){
		String ip = "";
		if(req != null){
			ip = req.getHeader("X-Forwarded-For");
	        if (ip == null || ip.length() ==  0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = req.getHeader("Proxy-Client-IP");
	        }
	        if (ip ==  null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = req.getHeader("WL-Proxy-Client-IP");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = req.getHeader("HTTP_CLIENT_IP");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = req.getHeader("HTTP_X_FORWARDED_FOR");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = req.getHeader("X-Real-IP");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = req.getHeader("X-RealIP");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = req.getHeader("REMOTE_ADDR");
	        }
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = req.getRemoteAddr();
	        }
		}
		return ip;
	}
	/**
	 * 게시판 및 Html로 이루어진 내용이 URI 인코딩이 되어 있는 내용을 디코딩할 때 사용
	 * @param html	URI인코딩된 내용
	 * @return	디코딩된 컨텐츠내용
	 * @throws UnsupportedEncodingException
	 */
	public String decodeURIComponent(String html) throws UnsupportedEncodingException{
		String result = "";
		result = decodeURIComponent(html, "UTF-8");
		return result;
	}
	/**
	 * 게시판 및 Html로 이루어진 내용이 URI 인코딩이 되어 있는 내용을 디코딩할 때 사용
	 * @param html	URI인코딩된 내용
	 * @param charset	문자셋 (기본값:UTF-8)
	 * @return	디코딩된 컨텐츠내용
	 * @throws UnsupportedEncodingException
	 */
	public String decodeURIComponent(String html, String charset) throws UnsupportedEncodingException{
		String result = "";
		if(StringUtils.isEmpty(charset)) charset = "UTF-8";
		result = URLDecoder.decode(html, charset);
		return result;
	}
	/**
	 * DB에서 조회한 게시판 내용을 인코딩할 때 사용
	 * @param html	URI인코딩할 내용
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String encodeURIComponent(String html) throws UnsupportedEncodingException{
		String result = "";
		result = encodeURIComponent(html, "UTF-8");
		return result;
	}
	/**
	 * DB에서 조회한 게시판 내용을 인코딩할 때 사용
	 * @param html		URI인코딩할 내용
	 * @param charset	문자셋 (기본값:UTF-8)
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String encodeURIComponent(String html, String charset) throws UnsupportedEncodingException{
		String result = "";
		if(StringUtils.isEmpty(charset)) charset = "UTF-8";
		result = URLEncoder.encode(html, charset);
		return result;
	}

	/**
	 * 에디터 전용태그를 Html태그로 변환한다.
	 * @param editorCTT
	 * @return
	 */
	public String convertEditorToHtml(String editorCTT){

		return convertEditorToHtml(editorCTT, "convert");
	}

	/**
	 * Html 전용태그를 에디터용 태그로 변환한다.
	 * @param editorCTT
	 * @return
	 */
	public String convertHtmlToEditor(String editorCTT){
		return convertEditorToHtml(editorCTT, "reverse");
	}

	/**
	 * 에디터 전용태그를 Html태그로 변환한다.
	 * @param editorCTT
	 * @return
	 */
	public String convertEditorToHtml(String editorCTT, String mode){
		String result = editorCTT;
		String[] targetArray = {"&#39;","&amp;","&lt;","&gt;","&apos;","&#34;","&quot;"};
		String[] convertArray = {"","&","<",">","'","'","\""};
		for(int i = 0 ; i < targetArray.length; i++){
			if("reverse".equals(mode)){
				result = result.replaceAll(convertArray[i], targetArray[i]);
			}else{
				result = result.replaceAll(targetArray[i], convertArray[i]);
			}
		}
		return result;
	}

	public void printHttpServletRequest(HttpServletRequest req)
	{
        @SuppressWarnings("rawtypes")
		Enumeration eParam = req.getParameterNames();

        LOGGER.debug("▶▷▶▷ [printHttpServletRequest] Parameter Info START =========");
        while (eParam != null && eParam.hasMoreElements())
        {
            String paramNm = (String) eParam.nextElement();
            String[] paramValues = req.getParameterValues(paramNm);

            if( (paramValues != null) && (paramValues.length > 0) )
            {

                Arrays.asList(paramValues).stream().forEach(value -> {LOGGER.debug("▶ " + paramNm + " : [" + value + "]");});
                /*
                int paramValueCnt = paramValues.length;
                // 파라메터값 세팅
                if (paramValueCnt > 1) {
                    // 복수 파라메터는 스트링 배열
                    for(int i=0; i<paramValueCnt; i++)
                    {
                    	LOGGER.debug("▶ " + paramNm + " : [" + paramValues[i] + "]");
                    }

                } else {
                    // 단일 파라메터는 스트링 값
                	LOGGER.debug("▶ " + paramNm + " : [" + paramValues[0] + "]");

                }
                */
            }
        }
        LOGGER.debug("▶▷▶▷ [printHttpServletRequest] Parameter Info END =========\n");


        LOGGER.debug("▶▷▶▷ [printHttpServletRequest] Attribute Info START =========");
        @SuppressWarnings("rawtypes")
		Enumeration eAttribute = req.getAttributeNames();

        while (eAttribute != null && eAttribute.hasMoreElements())
        {
        	String attrNm = (String) eAttribute.nextElement();
        	Object attrValue = req.getAttribute(attrNm);

        	if(attrValue == null){
        		LOGGER.debug("▶ " + attrNm + " is null ");
        	}else if(!attrValue.getClass().isArray() ){
        		LOGGER.debug("▶ " + attrNm + " : [" + attrValue + "]");
        	}else if(attrValue.getClass().isArray() ){
        		Arrays.asList(attrValue).stream().forEach(value -> {LOGGER.debug("▶ " + attrNm + " : [" + value + "]");});
        	}
        }
        LOGGER.debug("▶▷▶▷ [printHttpServletRequest] Attribute Info END =========\n");
	}

	public boolean saveMultpartFile(MultipartFile mf, String dirPath, String fileName){
		boolean result = false;

		if(mf == null){
			LOGGER.error("멀티파트파일이 정상적이지 않습니다.(null)");
			return result;
		}

		if(StringUtils.isEmpty(dirPath) || StringUtils.isEmpty(fileName)){
			LOGGER.error("디렉토리명("+dirPath+") 또는 파일명("+fileName+")이 정상적이지 않습니다.");
			return result;
		}

		File checkDir = new File(dirPath);
		if(!checkDir.exists()){
			checkDir.mkdirs();
		}
		File saveFile = new File(dirPath+"/"+fileName);
		if(saveFile.exists()){
			LOGGER.error("파일이 이미 존재합니다.(파일명:"+fileName+")");
			return result;
		}
		try {
			mf.transferTo(saveFile);
			result = true;
		} catch (IllegalStateException | IOException e) {
			LOGGER.error("파일 생성에 실패하였습니다. ("+mf.getOriginalFilename()+" - "+e.getMessage()+")");
		}
		return result;
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map convertObjectToMap(Object obj){
        Map map = new HashMap();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i=0; i <fields.length; i++){
            fields[i].setAccessible(true);
            try{
                map.put(fields[i].getName(), fields[i].get(obj));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }


    public static Object convertMapToObject(Map<String,Object> map,Object obj){
        String keyAttribute = null;
        String setMethodString = "set";
        String methodString = null;
        @SuppressWarnings("rawtypes")
		Iterator itr = map.keySet().iterator();

        while(itr.hasNext()){
            keyAttribute = (String) itr.next();
            methodString = setMethodString+keyAttribute.substring(0,1).toUpperCase()+keyAttribute.substring(1);
            Method[] methods = obj.getClass().getDeclaredMethods();
            for(int i=0;i<methods.length;i++){
                if(methodString.equals(methods[i].getName())){
                    try{

                    	if(methods[i].getParameterTypes()[0] != null && methods[i].getParameterTypes()[0].getName().equals("java.lang.Long")){
                    		Long value = map.get(keyAttribute) == null ? null : (StringUtils.isNumeric(String.valueOf(map.get(keyAttribute))) ? Long.valueOf(String.valueOf(map.get(keyAttribute))) : null);
                    		methods[i].invoke(obj, value);
                    	} else if(methods[i].getParameterTypes()[0] != null && methods[i].getParameterTypes()[0].getName().equals("java.lang.Integer")){
                    		Integer value = map.get(keyAttribute) == null ? null : (StringUtils.isNumeric(String.valueOf(map.get(keyAttribute))) ? Integer.valueOf(String.valueOf(map.get(keyAttribute))) : null);
                    		methods[i].invoke(obj, value);
//                    	} else if(methods[i].getParameterTypes()[0] != null && methods[i].getParameterTypes()[0].getName().equals("java.lang.String")){
//                    		String value = map.get(keyAttribute) == null ? null : (StringUtils.isNumeric(String.valueOf(map.get(keyAttribute))) ? String.valueOf(String.valueOf(map.get(keyAttribute))) : null);
//                    		methods[i].invoke(obj, value);
                    	}else{
                    		methods[i].invoke(obj, map.get(keyAttribute));
                    	}
                    }catch(Exception e){
                    	System.out.println(obj);
                    	System.out.println(map.get(keyAttribute));
                    	System.out.println(map.get(keyAttribute).getClass());
                    	System.out.println(methods[i]);
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }

    //파일명랜덤생성
    public static String getRandomString(){
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public String readFileText(String filePath){
    	String result = "";
    	File nFile = new File(filePath);
    	if(nFile.exists() && nFile.canRead()){
    		result = readFileText(nFile);
    	}
    	return result;
    }

    public String readFileText(File fil){
    	StringBuffer sb = new StringBuffer();
    	try{
            //파일 객체 생성
            if(fil.exists() && fil.canRead()){
	            //입력 스트림 생성
	            FileReader filereader = new FileReader(fil);
	            int singleCh = 0;
	            while((singleCh = filereader.read()) != -1){
	                System.out.print((char)singleCh);
	                sb.append((char)singleCh);
	            }
	            filereader.close();
            }
        }catch (FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e){
            System.err.println(e);
        }
    	return sb.toString();
    }

    public String getHtmlText(String sendUrl, Map<String, Object> params){
    	String result = "";
    	if(!StringUtils.isBlank(sendUrl)){
			try {
				URL url = new URL(sendUrl);
		        StringBuilder postData = new StringBuilder();
		        for(Map.Entry<String,Object> param : params.entrySet()) {
		            if(postData.length() != 0) postData.append('&');
		            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
		            postData.append('=');
		            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		        }
		        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

		        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		        conn.setRequestMethod("POST");
		        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		        conn.setDoOutput(true);
		        conn.getOutputStream().write(postDataBytes); // POST 호출

		        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

		        StringBuffer inputLine = new StringBuffer();
		        String read = "";
		        while((read = in.readLine()) != null) { // response 출력
		            inputLine.append(read);
		        }
		        result = inputLine.toString();
		        in.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}

		return result;
    }

    public Map<String, Object> getRequestMap(HttpServletRequest req){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	Enumeration<String> keys = req.getAttributeNames();
    	while(keys.hasMoreElements()){
			String key = keys.nextElement();
			System.out.println(key);
			resultMap.put(key, req.getAttribute(key));
		}

    	keys = req.getParameterNames();
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			System.out.println(key);
			resultMap.put(key, req.getParameter(key));
		}
    	return resultMap;
    }

    /**
     * Object type 변수가 비어있는지 체크
     *
     * @param obj
     * @return Boolean : true / false
     */
    public static Boolean empty(Object obj) {
        if (obj instanceof String) return obj == null || "".equals(obj.toString().trim());
        else if (obj instanceof List) return obj == null || ((List<?>) obj).isEmpty();
        else if (obj instanceof Map) return obj == null || ((Map<?, ?>) obj).isEmpty();
        else if (obj instanceof Object[]) return obj == null || Array.getLength(obj) == 0;
        else return obj == null;
    }

    /**
     * Object type 변수가 비어있지 않은지 체크
     *
     * @param obj
     * @return Boolean : true / false
     */
    public static Boolean notEmpty(Object obj) {
        return !empty(obj);
    }

    /**
     * JsonObject를 Map<String, String>으로 변환.
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
     * JsonArray를 List<Map<String, String>>으로 변환.
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
    
    public int getOffset(int srchPage, int srchCnt) {
		int offset = (srchPage - 1) * srchCnt;
		if(offset < 0) offset = 0;
    	return offset;
    }    
}
