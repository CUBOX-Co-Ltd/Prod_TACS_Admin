package cubox.admin.main.service.vo;

public class SiteVO{
	private String siteId;
	private String siteNm; 
	private String sortOrdr;
	private String useyn;
	private String registDt;
	private String modifyDt;
	private String siteDesc;
	private String registId; 
	private String fkind3;
	private String fvalue;
	
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
	public String getSortOrdr() {
		return sortOrdr;
	}
	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}
	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
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
	public String getSiteDesc() {
		return siteDesc;
	}
	public void setSiteDesc(String siteDesc) {
		this.siteDesc = siteDesc;
	}
	public String getRegistId() {
		return registId;
	}
	public void setRegistId(String registId) {
		this.registId = registId;
	}
	
	public String getFkind3() {
		return fkind3;
	}
	public void setFkind3(String fkind3) {
		this.fkind3 = fkind3;
	}
	public String getFvalue() {
		return fvalue;
	}
	public void setFvalue(String fvalue) {
		this.fvalue = fvalue;
	}
	
	@Override
	public String toString() {
		return "SiteVO [siteId=" + siteId + ", siteNm=" + siteNm + ", sortOrdr=" + sortOrdr + ", useyn=" + useyn
				+ ", registDt=" + registDt + ", modifyDt=" + modifyDt + ", siteDesc=" + siteDesc + ", registId="
				+ registId + ", fkind3=" + fkind3 + ", fvalue=" + fvalue + "]";
	}
	
	
	
	
	
	
}
