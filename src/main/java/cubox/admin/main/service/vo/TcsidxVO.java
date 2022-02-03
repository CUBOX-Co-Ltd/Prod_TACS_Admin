package cubox.admin.main.service.vo;

public class TcsidxVO {

	private String fidx;   		
	private String fcode;	
	private String fwdt;   				
	private String fregdt;
	
	public String getFidx() {
		return fidx;
	}
	public void setFidx(String fidx) {
		this.fidx = fidx;
	}
	public String getFcode() {
		return fcode;
	}
	public void setFcode(String fcode) {
		this.fcode = fcode;
	}
	public String getFwdt() {
		return fwdt;
	}
	public void setFwdt(String fwdt) {
		this.fwdt = fwdt;
	}
	public String getFregdt() {
		return fregdt;
	}
	public void setFregdt(String fregdt) {
		this.fregdt = fregdt;
	} 
	
	@Override
	public String toString() {
		return "TcsidxVO [fidx=" + fidx + ", fcode=" + fcode + ", fwdt=" + fwdt + ", fregdt=" + fregdt + "]";
	}
}
