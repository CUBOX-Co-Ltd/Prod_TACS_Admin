package cubox.admin.main.service.vo;

public class CodeVO {

	private String fkind1;
	private String fkind2;
	private String fkind3;
	private String fuseyn;
	private	String fvalue;
	private String forder;
	private String fdoc;
	private String fkind3_org;
	private String objName;
	
	public String getForder() {
		return forder;
	}
	public void setForder(String forder) {
		this.forder = forder;
	}
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
	public String getFkind3Org() {
		return fkind3_org;
	}
	public void setFkind3Org(String fkind3_org) {
		this.fkind3_org = fkind3_org;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}	
	
	@Override
	public String toString() {
		return "UserInfoVO [fkind1=" + fkind1 + ", fkind2=" + fkind2 + ", fkind3=" + fkind3 + ", fuseyn=" + fuseyn 
				+ ", fvalue=" + fvalue+ ", fdoc=" + fdoc+ ", fkind3_org=" + fkind3_org  + "]";
	}
}
