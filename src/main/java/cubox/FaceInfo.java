package cubox;

public class FaceInfo {

	private FaceInfo(float score_, byte[] features_) {
		score = score_;
		features = features_;
	}
	
	private float score;
	private byte[] features;
	
	public float getScore() { return this.score; }
	public byte[] getFeatures() { return this.features;}
	

}
