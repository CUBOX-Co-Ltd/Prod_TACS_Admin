package cubox.admin.main.service.vo;

public class GateOperationVO {
	private String sidx;
	
	private String gid;
	
	private String procid;
	
	private String tx;
	
	private String value;


	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getProcid() {
		return procid;
	}

	public void setProcid(String procid) {
		this.procid = procid;
	}

	public String getTx() {
		return tx;
	}

	public void setTx(String tx) {
		this.tx = tx;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "GateOperationVO [sidx=" + sidx + ", gid=" + gid + ", procid=" + procid + ", tx=" + tx + ", value="
				+ value + "]";
	}
}
