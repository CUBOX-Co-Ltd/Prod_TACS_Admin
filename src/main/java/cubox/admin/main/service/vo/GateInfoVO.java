package cubox.admin.main.service.vo;

public class GateInfoVO extends GateUserSearchVO{
	private String gid;
	
	private String tid;
	
	private String ptid;
	
	private String ip;
	
	private String name;
	
	private String acnt;
	
	private String ucnt;
	
	private String aliveDiff;
	
	private String terminalStatus;
	
	private String doorStatus;
	
	private String coverStatus;
	
	private String lockStatus;
	
	private String openStatus;
	
	private String vx;
	
	private String vy;
	
	private String vw;
	
	private String vh;
	
	private String version;
	
	private String fpartcd1;

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getPtid() {
		return ptid;
	}

	public void setPtid(String ptid) {
		this.ptid = ptid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcnt() {
		return acnt;
	}

	public void setAcnt(String acnt) {
		this.acnt = acnt;
	}

	public String getUcnt() {
		return ucnt;
	}

	public void setUcnt(String ucnt) {
		this.ucnt = ucnt;
	}

	public String getAliveDiff() {
		return aliveDiff;
	}

	public void setAliveDiff(String aliveDiff) {
		this.aliveDiff = aliveDiff;
	}

	public String getTerminalStatus() {
		return terminalStatus;
	}

	public void setTerminalStatus(String terminalStatus) {
		this.terminalStatus = terminalStatus;
	}

	public String getDoorStatus() {
		return doorStatus;
	}

	public void setDoorStatus(String doorStatus) {
		this.doorStatus = doorStatus;
	}

	public String getCoverStatus() {
		return coverStatus;
	}

	public void setCoverStatus(String coverStatus) {
		this.coverStatus = coverStatus;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(String openStatus) {
		this.openStatus = openStatus;
	}

	public String getVx() {
		return vx;
	}

	public void setVx(String vx) {
		this.vx = vx;
	}

	public String getVy() {
		return vy;
	}

	public void setVy(String vy) {
		this.vy = vy;
	}

	public String getVw() {
		return vw;
	}

	public void setVw(String vw) {
		this.vw = vw;
	}

	public String getVh() {
		return vh;
	}

	public void setVh(String vh) {
		this.vh = vh;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getFpartcd1() {
		return fpartcd1;
	}

	public void setFpartcd1(String fpartcd1) {
		this.fpartcd1 = fpartcd1;
	}
	
	@Override
	public String toString() {
		return "GateInfoVO [gid=" + gid + ", tid=" + tid + ", ptid=" + ptid + ", ip=" + ip + ", name=" + name
				+ ", acnt=" + acnt + ", ucnt=" + ucnt + ", aliveDiff=" + aliveDiff + ", terminalStatus="
				+ terminalStatus + ", doorStatus=" + doorStatus + ", coverStatus=" + coverStatus + ", lockStatus="
				+ lockStatus + ", openStatus=" + openStatus + ", vx=" + vx + ", vy=" + vy + ", vw=" + vw + ", vh=" + vh
				+ ", version=" + version + ", fpartcd1=" + fpartcd1 + "]";
	}
	
}
