package cubox.admin.main.service.vo;

import java.util.Arrays;

public class AuthGroupVO extends AuthGroupSearchVO{

	
	private String fuid; 
	private String funm;
	private String ftid;
	private String fptid;
	private String fcdno;   				
	private String fuseyn;   					
	private	String fsstatus;
	private	String ftype;
	private String fregdt;  					
	private String fqstatus;   				
	private String fitemnm;	
	private String authgroup;
	private String fpartcd1;
	private String fgid;
	private String centerCode;
	private String fpartnm1;
	private String fpartnm2;
	private String fpartnm3;
	
	public String getFuid() {
		return fuid;
	}
	public void setFuid(String fuid) {
		this.fuid = fuid;
	}
	public String getFunm() {
		return funm;
	}
	public void setFunm(String funm) {
		this.funm = funm;
	}
	public String getFtid() {
		return ftid;
	}
	public void setFtid(String ftid) {
		this.ftid = ftid;
	}
	public String getFptid() {
		return fptid;
	}
	public void setFptid(String fptid) {
		this.fptid = fptid;
	}
	public String getFcdno() {
		return fcdno;
	}
	public void setFcdno(String fcdno) {
		this.fcdno = fcdno;
	}
	public String getFuseyn() {
		return fuseyn;
	}
	public void setFuseyn(String fuseyn) {
		this.fuseyn = fuseyn;
	}
	public String getFsstatus() {
		return fsstatus;
	}
	public void setFsstatus(String fsstatus) {
		this.fsstatus = fsstatus;
	}
	public String getFtype() {
		return ftype;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	public String getFregdt() {
		return fregdt;
	}
	public void setFregdt(String fregdt) {
		this.fregdt = fregdt;
	}
	public String getFqstatus() {
		return fqstatus;
	}
	public void setFqstatus(String fqstatus) {
		this.fqstatus = fqstatus;
	}
	public String getFitemnm() {
		return fitemnm;
	}
	public void setFitemnm(String fitemnm) {
		this.fitemnm = fitemnm;
	}
	public String getAuthgroup() {
		return authgroup;
	}
	public void setAuthgroup(String authgroup) {
		this.authgroup = authgroup;
	}
	public String getFpartcd1() {
		return fpartcd1;
	}
	public void setFpartcd1(String fpartcd1) {
		this.fpartcd1 = fpartcd1;
	}
	public String getFgid() {
		return fgid;
	}
	public void setFgid(String fgid) {
		this.fgid = fgid;
	}
	public String getCenterCode() {
		return centerCode;
	}
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}
	public String getFpartnm1() {
		return fpartnm1;
	}
	public void setFpartnm1(String fpartnm1) {
		this.fpartnm1 = fpartnm1;
	}
	public String getFpartnm2() {
		return fpartnm2;
	}
	public void setFpartnm2(String fpartnm2) {
		this.fpartnm2 = fpartnm2;
	}
	public String getFpartnm3() {
		return fpartnm3;
	}
	public void setFpartnm3(String fpartnm3) {
		this.fpartnm3 = fpartnm3;
	}
	@Override
	public String toString() {
		return "AuthGroupVO [fuid=" + fuid + ", ftid=" + ftid + ", fptid=" + fptid + ", fcdno=" + fcdno + ", fuseyn="
				+ fuseyn + ", fsstatus=" + fsstatus + ", ftype=" + ftype + ", fregdt=" + fregdt + ", fqstatus="
				+ fqstatus + ", fitemnm=" + fitemnm + ", authgroup=" + authgroup + ", fpartcd1=" + fpartcd1 + ", fgid="
				+ fgid + ", centerCode=" + centerCode + "]";
	}	
}
