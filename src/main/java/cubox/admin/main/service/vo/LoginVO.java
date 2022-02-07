package cubox.admin.main.service.vo;

public class LoginVO {

	private String esntlId;
	private String userId;
	private String userNm;	
	private String userPw;
	private String authorCd;
	private String pwUpdtYn;
	private int pwUpdtDays;
	private int pwFailrCnt;
	private String useYn;

	public String getEsntlId() {
		return esntlId;
	}
	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getAuthorCd() {
		return authorCd;
	}
	public void setAuthorCd(String authorCd) {
		this.authorCd = authorCd;
	}
	public String getPwUpdtYn() {
		return pwUpdtYn;
	}
	public void setPwUpdtYn(String pwUpdtYn) {
		this.pwUpdtYn = pwUpdtYn;
	}
	public int getPwUpdtDays() {
		return pwUpdtDays;
	}
	public void setPwUpdtDays(int pwUpdtDays) {
		this.pwUpdtDays = pwUpdtDays;
	}
	public int getPwFailrCnt() {
		return pwFailrCnt;
	}
	public void setPwFailrCnt(int pwFailrCnt) {
		this.pwFailrCnt = pwFailrCnt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
	@Override
	public String toString() {
		return "LoginVO [esntlId="+esntlId+" userId="+userId+" userNm="+userNm+" authorCd="+authorCd
					+" pwUpdtYn="+pwUpdtYn+" pwUpdtDays="+pwUpdtDays+" pwFailrCnt="+pwFailrCnt+" useYn="+useYn+ "]";
	}
	
}
