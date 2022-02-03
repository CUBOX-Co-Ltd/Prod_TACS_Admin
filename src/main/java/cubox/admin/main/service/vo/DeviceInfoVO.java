package cubox.admin.main.service.vo;

public class DeviceInfoVO extends GateUserSearchVO{
	private String deviceId;
	private String siteId;
	private String deviceNm;
	private String deviceDesc;
	private String deviceIp;
	private String deviceFloor;
	private String xCoordinate;
	private String yCoordinate;
	private String useYn;
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getDeviceNm() {
		return deviceNm;
	}
	public void setDeviceNm(String deviceNm) {
		this.deviceNm = deviceNm;
	}
	public String getDeviceDesc() {
		return deviceDesc;
	}
	public void setDeviceDesc(String deviceDesc) {
		this.deviceDesc = deviceDesc;
	}
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	public String getDeviceFloor() {
		return deviceFloor;
	}
	public void setDeviceFloor(String deviceFloor) {
		this.deviceFloor = deviceFloor;
	}
	public String getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(String xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public String getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(String yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	@Override
	public String toString() {
		return "DeviceInfoVO [deviceId=" + deviceId + ", siteId=" + siteId + ", deviceNm=" + deviceNm + ", deviceDesc="
				+ deviceDesc + ", deviceIp=" + deviceIp + ", deviceFloor=" + deviceFloor + ", xCoordinate="
				+ xCoordinate + ", yCoordinate=" + yCoordinate + ", useYn=" + useYn + "]";
	}
	
	
	
}
