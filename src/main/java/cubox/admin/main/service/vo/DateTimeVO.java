package cubox.admin.main.service.vo;

public class DateTimeVO{

	private String yesterday;   		
	private String today;	
	private String currenttime;
	
	public String getYesterday() {
		return yesterday;
	}
	public void setYesterday(String yesterday) {
		this.yesterday = yesterday;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getCurrenttime() {
		return currenttime;
	}
	public void setCurrenttime(String currenttime) {
		this.currenttime = currenttime;
	}   				
	
	@Override
	public String toString() {
		return "DateTimeVO [yesterday=" + yesterday + ", today=" + today + ", currenttime=" + currenttime + "]";
	}
}