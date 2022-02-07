package cubox.admin.main.service.vo;

public class MenuVO {
	private String menuCd;
	private String menuClCd;
	private String menuNm;
	private String menuUrl;
	private String sortOrdr;
	private String useYn;
	private String authorCd;
	
	public String getMenuCd() {
		return menuCd;
	}
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	public String getMenuClCd() {
		return menuClCd;
	}
	public void setMenuClCd(String menuClCd) {
		this.menuClCd = menuClCd;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
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
	public String getAuthorCd() {
		return authorCd;
	}
	public void setAuthorCd(String authorCd) {
		this.authorCd = authorCd;
	}	

	@Override
	public String toString() {
		return "MenuDetailVO [menuCd=" + menuCd + ", menuClCd=" + menuClCd + ", menuNm=" + menuNm
				+ ", menuUrl=" + menuUrl + ", sortOrdr=" + sortOrdr + ", useYn=" + useYn + "]";
	}
}
