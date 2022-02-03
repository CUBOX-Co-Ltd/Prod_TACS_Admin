package cubox.admin.main.service.vo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AuthorGroupVO {
	private String authorGroupId;
	private String authorGroupNm;
	private String authorGroupDesc;
	private String sortOrdr;
	private String useYn;
	private String empYn;
	private String visitYn;
	private String detailCnt;
	private String fuid;
	private String siteId;
	private String[] groupArray;
	private List<Map<String, Object>> fuids;
	private int logId;
	private String registId;
	private String modifyId;

	public List<Map<String, Object>> getFuids() {
		return fuids;
	}
	public void setFuids(List<Map<String, Object>> fuids) {
		this.fuids = fuids;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getFuid() {
		return fuid;
	}
	public void setFuid(String fuid) {
		this.fuid = fuid;
	}
	public String[] getGroupArray() {
		return groupArray;
	}
	public void setGroupArray(String[] groupArray) {
		this.groupArray = groupArray;
	}
	public String getDetailCnt() {
		return detailCnt;
	}
	public void setDetailCnt(String detailCnt) {
		this.detailCnt = detailCnt;
	}
	public String getAuthorGroupId() {
		return authorGroupId;
	}
	public void setAuthorGroupId(String authorGroupId) {
		this.authorGroupId = authorGroupId;
	}
	public String getAuthorGroupNm() {
		return authorGroupNm;
	}
	public void setAuthorGroupNm(String authorGroupNm) {
		this.authorGroupNm = authorGroupNm;
	}
	public String getAuthorGroupDesc() {
		return authorGroupDesc;
	}
	public void setAuthorGroupDesc(String authorGroupDesc) {
		this.authorGroupDesc = authorGroupDesc;
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
	public String getVisitYn() {
		return visitYn;
	}
	public void setVisitYn(String visitYn) {
		this.visitYn = visitYn;
	}
	public String getEmpYn() {
		return empYn;
	}
	public void setEmpYn(String empYn) {
		this.empYn = empYn;
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
	@Override
	public String toString() {
		return "AuthorGroupVO [authorGroupId=" + authorGroupId + ", authorGroupNm=" + authorGroupNm
				+ ", authorGroupDesc=" + authorGroupDesc + ", sortOrdr=" + sortOrdr + ", useYn=" + useYn + ", empYn=" + empYn + ", visitYn=" + visitYn
				+ ", detailCnt=" + detailCnt + ", fuid=" + fuid + ", siteId=" + siteId + ", groupArray="
				+ Arrays.toString(groupArray) + ", fuids=" + fuids + ", logId=" + logId +", registId=" + registId +", modifyId=" + modifyId + "]";
	}
}
