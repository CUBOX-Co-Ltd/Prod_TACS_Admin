package cubox.admin.main.service.vo;

public class DownloadLogVO {
	
	private String fip;   		
	private String fdwnidx;	
	private String fregdt;   	
	private String fdwnflg;
	private String fresn;  
	private String srchPage;
	public String getFip() {
		return fip;
	}
	public void setFip(String fip) {
		this.fip = fip;
	}
	public String getFdwnidx() {
		return fdwnidx;
	}
	public void setFdwnidx(String fdwnidx) {
		this.fdwnidx = fdwnidx;
	}
	public String getFregdt() {
		return fregdt;
	}
	public void setFregdt(String fregdt) {
		this.fregdt = fregdt;
	}
	public String getFdwnflg() {
		return fdwnflg;
	}
	public void setFdwnflg(String fdwnflg) {
		this.fdwnflg = fdwnflg;
	}
	public String getFresn() {
		return fresn;
	}
	public void setFresn(String fresn) {
		this.fresn = fresn;
	}
	public String getSrchPage() {
		return srchPage;
	}
	public void setSrchPage(String srchPage) {
		this.srchPage = srchPage;
	}
	@Override
	public String toString() {
		return "DownloadLogVO [fip=" + fip + ", fdwnidx=" + fdwnidx + ", fregdt=" + fregdt + ", fdwnflg=" + fdwnflg
				+ ", fresn=" + fresn + ", srchPage=" + srchPage + "]";
	}
}
