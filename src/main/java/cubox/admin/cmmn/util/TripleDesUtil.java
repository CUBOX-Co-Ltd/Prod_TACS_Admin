package cubox.admin.cmmn.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * 한국전력공사 istcontrol 방문객관리 시스템을 위한 암복호화
 * 키생성을 위해 지정된 패스워드, salt, iv등은 변경불가
 * String 사용을 위해 앞뒤로 base64 인코딩, 디코딩
 */
public class TripleDesUtil {

	private static String PASSWORD = "KEPCO_VERTX";
	private static byte[] SALT_BYTES = new byte[] { (byte)162, 27, 98, 1, 28, (byte)239, 64, 30, (byte)156, 102, (byte)223 };
	
	private static byte[] IV_8 = new byte[] { 37, 28, 19, 44, 25, (byte)170, 122, 25 };
	//private static byte[] IV_24 = new byte[] { 37, 28, 19, 44, 25, (byte)170, 122, 25, 25, 57, 127, 5, 22, 1, 66, 65, 14, (byte)155, (byte)224, 64, 9, 77, 18, (byte)251 };


	public TripleDesUtil() {
	}

	private static Key getKey() throws Exception {

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		PBEKeySpec pbeKeySpec = new PBEKeySpec(PASSWORD.toCharArray(), SALT_BYTES, 5, 192);
		Key secretKey = factory.generateSecret(pbeKeySpec);
		byte[] byteKey = secretKey.getEncoded();

		DESedeKeySpec desKeySpec = new DESedeKeySpec(byteKey);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		Key key = keyFactory.generateSecret(desKeySpec);
		
		return key;
	}

	public static String encrypt(String input) throws Exception {
		if(input == null || input.length() == 0) return "";

		// 모드 선택
		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(IV_8);
		cipher.init(Cipher.ENCRYPT_MODE, getKey(), iv);


		byte[] result = cipher.doFinal(input.getBytes("UTF-8"));

		return new BASE64Encoder().encode( result );
	}

	public static String decrypt(String input) throws Exception {
		if(input == null || input.length() == 0) return "";

		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(IV_8);
		cipher.init(Cipher.DECRYPT_MODE, getKey(), iv);

		byte [] inputBytes = new BASE64Decoder().decodeBuffer( input );
		byte [] outputBytes = cipher.doFinal( inputBytes );

		return new String( outputBytes, "UTF8" );
	}


}
