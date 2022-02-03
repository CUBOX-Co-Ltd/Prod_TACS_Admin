package cubox.admin.main.service.vo;

public class SiteUserVO extends SiteUserSearchVO {

	private String fsiteid;
	private String fpasswd;
	private String fpasswdyn;
	private String fkind3;
	private String fvalue;
	private	String fname;
	private	String fphone;
	private String fuseyn;
	private String fregdt;
	private String fregdt2;
	private String flastaccdt;
	private String flastaccip;
	private String femergency;
	private String femergencynm;
	private String fuid;
	private String fauthcd;
	private String funm;
	private String fauthnm;
	private String site_nm;
	private String site_id;
	private String author_nm;
	private String author_id;

	public String getAuthor_nm() {
		return author_nm;
	}
	public void setAuthor_nm(String author_nm) {
		this.author_nm = author_nm;
	}
	public String getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}
	public String getSite_nm() {
		return site_nm;
	}
	public void setSite_nm(String site_nm) {
		this.site_nm = site_nm;
	}
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	public String getFauthnm() {
		return fauthnm;
	}
	public void setFauthnm(String fauthnm) {
		this.fauthnm = fauthnm;
	}
	public String getFunm() {
		return funm;
	}
	public void setFunm(String funm) {
		this.funm = funm;
	}
	public String getFauthcd() {
		return fauthcd;
	}
	public void setFauthcd(String fauthcd) {
		this.fauthcd = fauthcd;
	}
	public String getFuid() {
		return fuid;
	}
	public void setFuid(String fuid) {
		this.fuid = fuid;
	}
	public String getFsiteid() {
		return fsiteid;
	}
	public void setFsiteid(String fsiteid) {
		this.fsiteid = fsiteid;
	}
	public String getFpasswd() {
		return fpasswd;
	}
	public void setFpasswd(String fpasswd) {
		this.fpasswd = fpasswd;
	}
	public String getFpasswdyn() {
		return fpasswdyn;
	}
	public void setFpasswdyn(String fpasswdyn) {
		this.fpasswdyn = fpasswdyn;
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
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFphone() {
		return fphone;
	}
	public void setFphone(String fphone) {
		this.fphone = fphone;
	}
	public String getFuseyn() {
		return fuseyn;
	}
	public void setFuseyn(String fuseyn) {
		this.fuseyn = fuseyn;
	}
	public String getFregdt() {
		return fregdt;
	}
	public void setFregdt(String fregdt) {
		this.fregdt = fregdt;
	}
	public String getFregdt2() {
		return fregdt2;
	}
	public void setFregdt2(String fregdt2) {
		this.fregdt2 = fregdt2;
	}
	public String getFlastaccdt() {
		return flastaccdt;
	}
	public void setFlastaccdt(String flastaccdt) {
		this.flastaccdt = flastaccdt;
	}
	public String getFlastaccip() {
		return flastaccip;
	}
	public void setFlastaccip(String flastaccip) {
		this.flastaccip = flastaccip;
	}
	public String getFemergency() {
		return femergency;
	}
	public void setFemergency(String femergency) {
		this.femergency = femergency;
	}
	public String getFemergencynm() {
		return femergencynm;
	}
	public void setFemergencynm(String femergencynm) {
		this.femergencynm = femergencynm;
	}
	@Override
	public String toString() {
		return "SiteUserVO [fsiteid=" + fsiteid + ", fpasswd=" + fpasswd + ", fpasswdyn=" + fpasswdyn + ", fkind3="
				+ fkind3 + ", fvalue=" + fvalue + ", fname=" + fname + ", fphone=" + fphone + ", fuseyn=" + fuseyn
				+ ", fregdt=" + fregdt + ", fregdt2=" + fregdt2 + ", flastaccdt=" + flastaccdt + ", flastaccip="
				+ flastaccip + ", femergency=" + femergency + ", femergencynm=" + femergencynm + ", fuid=" + fuid
				+ ", fauthcd=" + fauthcd + ", funm=" + funm + ", fauthnm=" + fauthnm + ", site_nm=" + site_nm
				+ ", site_id=" + site_id + ", author_nm=" + author_nm + ", author_id=" + author_id + "]";
	}
}
