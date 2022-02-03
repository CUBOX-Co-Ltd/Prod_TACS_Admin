package cubox;

public class FaceExtractor {
	static {
		try {
			// System.load("D:\\vsRepos\\CUFaceDetector\\x64Jni\\JNIFACEDETECTOR.dll");
			System.loadLibrary("JNIFACEDETECTOR");
			Init ();
		} catch (java.lang.UnsatisfiedLinkError e) {
			System.out.println(e.getMessage());
		}
	}

	// public boolean Initiated = false;
	public FaceExtractor() {}	
	private static native int Init();
	public native void helloWorld();// test	
	public native FaceInfo Extract(byte[] faceImg);		// byte[] 얼굴 이미지 전송
	public native FaceInfo Extract64(String faceImg);	// base64 얼굴 이미지 전송
	
	// test 중...
	public static void callback(int a) {
		System.out.println("callback : " + a);
	}
}
