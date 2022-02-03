package cubox.admin.main.service.vo;

public class SysLogVO extends SysLogSearchVO {

	private String fevttm;   		
	private String fsiteid;
	private String fname;
	private String fsyscode;   				
	private String fdetail;  
	private String fdetail2;
	private	String fcnntip;		
	private	String fcnntid;		
	private	String fkind3;		
	private String syslogdept1;
	private String syslogdept2;
	private String syslogdept3;
	private String syslogdeptnm1;
	private String syslogdeptnm2;
	private String syslogdeptnm3;
	private String fsyscodenm;
	private String chkValueArray;
	private String chkTextArray;
	private String authorId;	
	
	public String getFevttm() {
		return fevttm;
	}
	public void setFevttm(String fevttm) {
		this.fevttm = fevttm;
	}
	public String getFsiteid() {
		return fsiteid;
	}
	public void setFsiteid(String fsiteid) {
		this.fsiteid = fsiteid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFsyscode() {
		return fsyscode;
	}
	public void setFsyscode(String fsyscode) {
		this.fsyscode = fsyscode;
	}
	public String getFdetail() {
		return fdetail;
	}
	public void setFdetail(String fdetail) {
		this.fdetail = fdetail;
	}
	public String getFdetail2() {
		return fdetail2;
	}
	public void setFdetail2(String fdetail2) {
		this.fdetail2 = fdetail2;
	}
	public String getFcnntip() {
		return fcnntip;
	}
	public void setFcnntip(String fcnntip) {
		this.fcnntip = fcnntip;
	}
	public String getFcnntid() {
		return fcnntid;
	}
	public void setFcnntid(String fcnntid) {
		this.fcnntid = fcnntid;
	}
	public String getFkind3() {
		return fkind3;
	}
	public void setFkind3(String fkind3) {
		this.fkind3 = fkind3;
	}
	public String getSyslogdept1() {
		return syslogdept1;
	}
	public void setSyslogdept1(String syslogdept1) {
		this.syslogdept1 = syslogdept1;
	}
	public String getSyslogdept2() {
		return syslogdept2;
	}
	public void setSyslogdept2(String syslogdept2) {
		this.syslogdept2 = syslogdept2;
	}
	public String getSyslogdept3() {
		return syslogdept3;
	}
	public void setSyslogdept3(String syslogdept3) {
		this.syslogdept3 = syslogdept3;
	}
	public String getSyslogdeptnm1() {
		return syslogdeptnm1;
	}
	public void setSyslogdeptnm1(String syslogdeptnm1) {
		this.syslogdeptnm1 = syslogdeptnm1;
	}
	public String getSyslogdeptnm2() {
		return syslogdeptnm2;
	}
	public void setSyslogdeptnm2(String syslogdeptnm2) {
		this.syslogdeptnm2 = syslogdeptnm2;
	}
	public String getSyslogdeptnm3() {
		return syslogdeptnm3;
	}
	public void setSyslogdeptnm3(String syslogdeptnm3) {
		this.syslogdeptnm3 = syslogdeptnm3;
	}
	public String getFsyscodenm() {
		return fsyscodenm;
	}
	public void setFsyscodenm(String fsyscodenm) {
		this.fsyscodenm = fsyscodenm;
	}
	public String getChkValueArray() {
		return chkValueArray;
	}
	public void setChkValueArray(String chkValueArray) {
		this.chkValueArray = chkValueArray;
	}
	public String getChkTextArray() {
		return chkTextArray;
	}
	public void setChkTextArray(String chkTextArray) {
		this.chkTextArray = chkTextArray;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	
	@Override
	public String toString() {
		return "SysLogVO [fevttm=" + fevttm + ", fsiteid=" + fsiteid + ", fname=" + fname + ", fsyscode=" + fsyscode + ", fdetail=" + fdetail
				+ ", fdetail2=" + fdetail2 + ", fcnntip=" + fcnntip + ", fcnntid=" + fcnntid + ", fkind3=" + fkind3 + ", syslogdept1="
				+ syslogdept1 + ", syslogdept2=" + syslogdept2 + ", syslogdept3=" + syslogdept3 + ", syslogdeptnm1="
				+ syslogdeptnm1 + ", syslogdeptnm2=" + syslogdeptnm2 + ", syslogdeptnm3=" + syslogdeptnm3
				+ ", fsyscodenm=" + fsyscodenm + ", chkValueArray=" + chkValueArray + ", chkTextArray=" + chkTextArray
				+ ", authorId=" + authorId + "]";
	}	
}