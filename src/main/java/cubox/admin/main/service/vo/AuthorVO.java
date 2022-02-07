package cubox.admin.main.service.vo;

public class AuthorVO {

	private String authorCd;
	private String authorNm;
	private String sortOrdr;
	private String useYn;
	
	public String getAuthorCd() {
		return authorCd;
	}

	public void setAuthorCd(String authorCd) {
		this.authorCd = authorCd;
	}

	public String getAuthorNm() {
		return authorNm;
	}

	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}

	public String getSortOrdr() {
		return sortOrdr;
	}

	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	@Override
	public String toString() {
		return "AuthorVO [authorCd=" + authorCd + ", authorNm=" + authorNm 
				+ ", useYn=" + useYn + ", sortOrdr=" + sortOrdr + "]";
	}
}
