package cubox.admin.main.service.vo;

public class CenterInfoVO {

	private String fkind1;   		
	private String fkind2;	
	private String fkind3;   				
	private String fuseyn;   					
	private	String fvalue;					
	private String fdoc;  					
	private String fsidx;   				
	private String fregdt;
	private String fregdt2;
	
	public String getFkind1() {
		return fkind1;
	}
	public void setFkind1(String fkind1) {
		this.fkind1 = fkind1;
	}
	public String getFkind2() {
		return fkind2;
	}
	public void setFkind2(String fkind2) {
		this.fkind2 = fkind2;
	}
	public String getFkind3() {
		return fkind3;
	}
	public void setFkind3(String fkind3) {
		this.fkind3 = fkind3;
	}
	public String getFuseyn() {
		return fuseyn;
	}
	public void setFuseyn(String fuseyn) {
		this.fuseyn = fuseyn;
	}
	public String getFvalue() {
		return fvalue;
	}
	public void setFvalue(String fvalue) {
		this.fvalue = fvalue;
	}
	public String getFdoc() {
		return fdoc;
	}
	public void setFdoc(String fdoc) {
		this.fdoc = fdoc;
	}
	public String getFsidx() {
		return fsidx;
	}
	public void setFsidx(String fsidx) {
		this.fsidx = fsidx;
	}
	public String getFregdt() {
		return fregdt;
	}
	public String getFregdt2() {
		return fregdt2;
	}
	public void setFregdt(String fregdt) {
		this.fregdt = fregdt;
	}
	public void setFregdt2(String fregdt2) {
		this.fregdt2 = fregdt2;
	}
	
	@Override
	public String toString() {
		return "UserInfoVO [fkind1=" + fkind1 + ", fkind2=" + fkind2 + ", fkind3=" + fkind3 + ", fuseyn=" + fuseyn 
				+ ", fvalue=" + fvalue + ", fdoc=" + fdoc + ", fsidx=" + fsidx + ", fregdt=" + fregdt + ", fregdt2=" + fregdt2 + "]";
	}
}
