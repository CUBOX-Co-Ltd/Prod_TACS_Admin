package cubox.admin.main.service.vo;

public class SyncDeviceVO {
	private String deviceNo;
	private String fuid;
	private String cudType;
	
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getFuid() {
		return fuid;
	}
	public void setFuid(String fuid) {
		this.fuid = fuid;
	}
	public String getCudType() {
		return cudType;
	}
	public void setCudType(String cudType) {
		this.cudType = cudType;
	}
	@Override
	public String toString() {
		return "SyncDeviceVO [deviceNo=" + deviceNo + ", fuid=" + fuid + ", cudType=" + cudType + "]";
	}	
}
