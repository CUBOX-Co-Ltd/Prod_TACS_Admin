package cubox.admin.main.service.vo;

public class UserBioInfoVO {

	private String fdt;
	private String fuid;
	private String fimg;
	private String fextdata;
	private	String fquality;
	private String fregdt;
	private String fmoddt;
	private String ffailcnt;
	private String fsidx;
	private String fextfg;
	private String fsstatus;
	private String fqstatus;
	private String factdt;
	private String fqstdt;
	private String fwvname;
	private String fwedt;
	private String fwsdt;
	private String funm;
	private String frcvflg;
	private byte[] imgByte;
	private String resizeYn;
	private String bioCnt = "0";

	public String getResizeYn() {
		return resizeYn;
	}
	public void setResizeYn(String resizeYn) {
		this.resizeYn = resizeYn;
	}
	public byte[] getImgByte() {
		return imgByte;
	}
	public void setImgByte(byte[] imgByte) {
		this.imgByte = imgByte;
	}
	public String getFrcvflg() {
		return frcvflg;
	}
	public void setFrcvflg(String frcvflg) {
		this.frcvflg = frcvflg;
	}
	public String getFunm() {
		return funm;
	}
	public void setFunm(String funm) {
		this.funm = funm;
	}
	public String getFdt() {
		return fdt;
	}
	public void setFdt(String fdt) {
		this.fdt = fdt;
	}
	public String getFuid() {
		return fuid;
	}
	public void setFuid(String fuid) {
		this.fuid = fuid;
	}
	public String getFimg() {
		return fimg;
	}
	public void setFimg(String fimg) {
		this.fimg = fimg;
	}
	public String getFextdata() {
		return fextdata;
	}
	public void setFextdata(String fextdata) {
		this.fextdata = fextdata;
	}
	public String getFquality() {
		return fquality;
	}
	public void setFquality(String fquality) {
		this.fquality = fquality;
	}
	public String getFregdt() {
		return fregdt;
	}
	public void setFregdt(String fregdt) {
		this.fregdt = fregdt;
	}
	public String getFmoddt() {
		return fmoddt;
	}
	public void setFmoddt(String fmoddt) {
		this.fmoddt = fmoddt;
	}
	public String getFfailcnt() {
		return ffailcnt;
	}
	public void setFfailcnt(String ffailcnt) {
		this.ffailcnt = ffailcnt;
	}
	public String getFsidx() {
		return fsidx;
	}
	public void setFsidx(String fsidx) {
		this.fsidx = fsidx;
	}
	public String getFextfg() {
		return fextfg;
	}
	public void setFextfg(String fextfg) {
		this.fextfg = fextfg;
	}
	public String getFsstatus() {
		return fsstatus;
	}
	public void setFsstatus(String fsstatus) {
		this.fsstatus = fsstatus;
	}
	public String getFqstatus() {
		return fqstatus;
	}
	public void setFqstatus(String fqstatus) {
		this.fqstatus = fqstatus;
	}
	public String getFactdt() {
		return factdt;
	}
	public void setFactdt(String factdt) {
		this.factdt = factdt;
	}
	public String getFqstdt() {
		return fqstdt;
	}
	public void setFqstdt(String fqstdt) {
		this.fqstdt = fqstdt;
	}
	public String getFwvname() {
		return fwvname;
	}
	public void setFwvname(String fwvname) {
		this.fwvname = fwvname;
	}
	public String getFwedt() {
		return fwedt;
	}
	public void setFwedt(String fwedt) {
		this.fwedt = fwedt;
	}
	public String getFwsdt() {
		return fwsdt;
	}
	public void setFwsdt(String fwsdt) {
		this.fwsdt = fwsdt;
	}
	public String getBioCnt() {
		return bioCnt;
	}
	public void setBioCnt(String bioCnt) {
		this.bioCnt = bioCnt;
	}	
	@Override
	public String toString() {
		return "UserBioInfoVO [fdt=" + fdt + ", fuid=" + fuid + ", fimg=" + (fimg!=null?fimg.length():0) + ", fextdata=" + fextdata
				+ ", fquality=" + fquality + ", fregdt=" + fregdt + ", fmoddt=" + fmoddt + ", ffailcnt=" + ffailcnt
				+ ", fsidx=" + fsidx + ", fextfg=" + fextfg + ", fsstatus=" + fsstatus + ", fqstatus=" + fqstatus
				+ ", factdt=" + factdt + ", fqstdt=" + fqstdt + ", fwvname=" + fwvname + ", fwedt=" + fwedt + ", fwsdt="
				+ fwsdt + ", funm=" + funm + ", frcvflg=" + frcvflg
				+ ", resizeYn=" + resizeYn + ", bioCnt=" + bioCnt + "]";
	}
}
