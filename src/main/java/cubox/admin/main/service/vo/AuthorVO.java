package cubox.admin.main.service.vo;

public class AuthorVO extends AuthGroupSearchVO{

	private String authorId;
	private String authorNm;
	private String useYn;
	private String sortOrdr;
	private String registDt;
	private String modifyDt;
	private String authorDesc;

	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getAuthorNm() {
		return authorNm;
	}
	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSortOrdr() {
		return sortOrdr;
	}
	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}
	public String getRegistDt() {
		return registDt;
	}
	public void setRegistDt(String registDt) {
		this.registDt = registDt;
	}
	public String getModifyDt() {
		return modifyDt;
	}
	public void setModifyDt(String modifyDt) {
		this.modifyDt = modifyDt;
	}
	public String getAuthorDesc() {
		return authorDesc;
	}
	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}
	@Override
	public String toString() {
		return "AuthorVO [authorId=" + authorId + ", authorNm=" + authorNm + ", useYn=" + useYn + ", sortOrdr="
				+ sortOrdr + ", registDt=" + registDt + ", modifyDt=" + modifyDt + ", authorDesc=" + authorDesc + "]";
	}
}
