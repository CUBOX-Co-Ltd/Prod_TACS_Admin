package cubox.admin.cmmn.util;

import org.springframework.stereotype.Component;

@Component 
public class JwtTokenRepo {

	private static String JWT_TOKEN = "";

	public static void setToken(String token){
		JWT_TOKEN = token;
	}

	public static String getToken(){
		return JWT_TOKEN;
	}
}
