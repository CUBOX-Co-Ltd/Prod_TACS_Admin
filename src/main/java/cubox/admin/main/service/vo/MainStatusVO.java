package cubox.admin.main.service.vo;

public class MainStatusVO {
	private String exp_day;
	private int tot_log_cnt;
	private int fail_log_cnt;
	private int success_log_cnt;
	private int user_log_cnt;
	private int obs_cnt;

	public int getUser_log_cnt() {
		return user_log_cnt;
	}
	public void setUser_log_cnt(int user_log_cnt) {
		this.user_log_cnt = user_log_cnt;
	}
	public int getObs_cnt() {
		return obs_cnt;
	}
	public void setObs_cnt(int obs_cnt) {
		this.obs_cnt = obs_cnt;
	}
	public String getExp_day() {
		return exp_day;
	}
	public void setExp_day(String exp_day) {
		this.exp_day = exp_day;
	}
	public int getTot_log_cnt() {
		return tot_log_cnt;
	}
	public void setTot_log_cnt(int tot_log_cnt) {
		this.tot_log_cnt = tot_log_cnt;
	}
	public int getFail_log_cnt() {
		return fail_log_cnt;
	}
	public void setFail_log_cnt(int fail_log_cnt) {
		this.fail_log_cnt = fail_log_cnt;
	}
	public int getSuccess_log_cnt() {
		return success_log_cnt;
	}
	public void setSuccess_log_cnt(int success_log_cnt) {
		this.success_log_cnt = success_log_cnt;
	}
	@Override
	public String toString() {
		return "MainStatusVO [exp_day=" + exp_day + ", tot_log_cnt=" + tot_log_cnt + ", fail_log_cnt=" + fail_log_cnt
				+ ", success_log_cnt=" + success_log_cnt + ", user_log_cnt=" + user_log_cnt + ", obs_cnt=" + obs_cnt
				+ "]";
	}
}
