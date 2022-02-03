package cubox.admin.main.service.vo;

import java.util.Arrays;

public class AuthorGroupDetailVO {
	private String authorGroupId;
	private String fgid;
	private String sortOrdr;
	private String useYn;
	private String flname;
	private String fvname;
	private String siteId;
	private String siteNm;
	private String[] deviceIds;
	private String registId;
	private String modifyId;
	private int logId;	
	
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
	public String[] getDeviceIds() {
		return deviceIds;
	}
	public void setDeviceIds(String[] deviceIds) {
		this.deviceIds = deviceIds;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getAuthorGroupId() {
		return authorGroupId;
	}
	public void setAuthorGroupId(String authorGroupId) {
		this.authorGroupId = authorGroupId;
	}
	public String getFgid() {
		return fgid;
	}
	public void setFgid(String fgid) {
		this.fgid = fgid;
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
	public String getFlname() {
		return flname;
	}
	public void setFlname(String flname) {
		this.flname = flname;
	}
	public String getFvname() {
		return fvname;
	}
	public void setFvname(String fvname) {
		this.fvname = fvname;
	}
	public String getRegistId() {
		return registId;
	}
	public void setRegistId(String registId) {
		this.registId = registId;
	}
	public String getModifyId() {
		return modifyId;
	}
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}	
	@Override
	public String toString() {
		return "AuthorGroupDetailVO [authorGroupId=" + authorGroupId + ", fgid=" + fgid
				+ ", sortOrdr=" + sortOrdr + ", useYn=" + useYn
				+ ", flname=" + flname + ", fvname=" + fvname + ", siteId=" + siteId + ", siteNm=" + siteNm
				+ ", deviceIds=" + Arrays.toString(deviceIds) + ", registId=" + registId +", modifyId=" + modifyId + ", logId=" + logId +  "]";
	}	
}
