package cubox.admin.main.service.vo;

public class GatePositionVO {
	private String tid;
	
	private String vx;
	
	private String vy;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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

	@Override
	public String toString() {
		return "GatePositionVO [tid=" + tid + ", vx=" + vx + ", vy=" + vy + "]";
	}
}
