package cubox.admin.main.service.vo;

import java.util.List;

public class TerminalLogSearchVO {

	private String startDate	= "";
	private String startTime	= "";
	private String startDateTime	= "";
	private String endDateTime	  	= "";
	private String endDate	= "";
	private String endTime	= "";
	private String srchCond = "";
	private String srchFtx = "";
	private String srchFlnam = "";
	private String srchFstat = "";
	private String rowData = "";
	private String rowText = "";
	private String searchItemArea = "";
	private String searchItemFloor = "";
	private String ftid = "";
	private String srchFtxnm = "";

	private int srchPage			= 1;	//조회할 페이지 번호 기본 1페이지
	private int srchCnt				= 50;	//조회할 페이지 수
	private int offset				= 0;
	private int curPage				= 1;	//조회할 페이지 번호 기본 1페이지
	private int curPageUnit			= 10;	//한번에 표시할 페이지 번호 개수

	public String getSrchFtxnm() {
		return srchFtxnm;
	}
	public void setSrchFtxnm(String srchFtxnm) {
		this.srchFtxnm = srchFtxnm;
	}
	public String getFtid() {
		return ftid;
	}
	public void setFtid(String ftid) {
		this.ftid = ftid;
	}
	public String getSearchItemArea() {
		return searchItemArea;
	}
	public void setSearchItemArea(String searchItemArea) {
		this.searchItemArea = searchItemArea;
	}
	public String getSearchItemFloor() {
		return searchItemFloor;
	}
	public void setSearchItemFloor(String searchItemFloor) {
		this.searchItemFloor = searchItemFloor;
	}
	public String getRowData() {
		return rowData;
	}
	public void setRowData(String rowData) {
		this.rowData = rowData;
	}
	public String getRowText() {
		return rowText;
	}
	public void setRowText(String rowText) {
		this.rowText = rowText;
	}
	public String getSrchFstat() {
		return srchFstat;
	}
	public void setSrchFstat(String srchFstat) {
		this.srchFstat = srchFstat;
	}
	public String getSrchFlnam() {
		return srchFlnam;
	}
	public void setSrchFlnam(String srchFlnam) {
		this.srchFlnam = srchFlnam;
	}
	public String getSrchFtx() {
		return srchFtx;
	}
	public void setSrchFtx(String srchFtx) {
		this.srchFtx = srchFtx;
	}
	public String getSrchCond() {
		return srchCond;
	}
	public void setSrchCond(String srchCond) {
		this.srchCond = srchCond;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public int getSrchPage() {
		return srchPage;
	}
	public void setSrchPage(int srchPage) {
		this.srchPage = srchPage;
	}
	public int getSrchCnt() {
		return srchCnt;
	}
	public void setSrchCnt(int srchCnt) {
		this.srchCnt = srchCnt;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getCurPageUnit() {
		return curPageUnit;
	}
	public void setCurPageUnit(int curPageUnit) {
		this.curPageUnit = curPageUnit;
	}
	public void autoOffset(){
		int off = (this.srchPage - 1) * this.srchCnt;
		if(off<0) off = 0;
		this.offset = off;
	}

	@Override
	public String toString() {
		return "SysLogSearchVO [startDate=" + startDate + ", startTime=" + startTime + ", startDateTime="
				+ startDateTime + ", endDateTime=" + endDateTime + ", endDate=" + endDate + ", endTime=" + endTime
				+ ", srchPage=" + srchPage + ", srchCnt=" + srchCnt + ", offset=" + offset + ", curPage=" + curPage
				+ ", curPageUnit=" + curPageUnit + "]";
	}

}
