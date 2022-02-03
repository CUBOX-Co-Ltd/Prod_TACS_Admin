package cubox.admin.main.service.vo;

import java.util.List;

public class LogInfoSearchVO {

	private String srchCond	  		= "";	//조회조건
	private String srchCondWord	  	= "";
	private String srchStartDate	= "";	//시작날짜
	private String srchExpireDate	= "";	//종료날짜
	private String srchTemp	= "";
	private String srchVisit	= "";
	private String srchTVCardNum	= "";
	private String srchCardNum	= "";
	private String srchColCond	= "";		//콤보박스
	private String srchSuccess	= "";		//결과체크
	private String srchFail	= "";			//결과체크
	private String fromTime = "";			//시작시간
	private String toTime =	"";				//종료시간
	private String stDateTime = "";			//시작날짜시간
	private String edDateTime = "";			//종료날짜시간
	private int srchPage			= 1;	//조회할 페이지 번호 기본 1페이지
	private int srchCnt				= 10;	//조회할 페이지 수
	private int offset				= 0;
	private int curPage				= 1;	//조회할 페이지 번호 기본 1페이지
	private int curPageUnit			= 10;	//한번에 표시할 페이지 번호 개수
	private String srchCardStatus;
	private String[] arrCardStatus;
	private List<String> fsList = null;
	private List<String> srchColChk = null;
	private String colChkQuery;
	private List<String> srchColChk2 = null;
	private String colChkQuery2;
	private String fsActive;
	private String fsDelete;
	private String fsExpired;
	private String fsLost;
	private String fsWait;
	private String fsNone;
	private String hidSortName;	//sort
	private String hidSortNum;	//sort
	
	private String srchFpartnm1;
	private String srchFpartnm2;
	private String srchPartWord;
	private String srchAuthType;
	
	public String getSrchCardStatus() {
		return srchCardStatus;
	}
	public void setSrchCardStatus(String srchCardStatus) {
		this.srchCardStatus = srchCardStatus;
	}
	public String[] getArrCardStatus() {
		return arrCardStatus;
	}
	public void setArrCardStatus(String[] arrCardStatus) {
		this.arrCardStatus = arrCardStatus;
	}
	public String getSrchTemp() {
		return srchTemp;
	}
	public void setSrchTemp(String srchTemp) {
		this.srchTemp = srchTemp;
	}
	public String getSrchVisit() {
		return srchVisit;
	}
	public void setSrchVisit(String srchVisit) {
		this.srchVisit = srchVisit;
	}
	public String getSrchTVCardNum() {
		return srchTVCardNum;
	}
	public void setSrchTVCardNum(String srchTVCardNum) {
		this.srchTVCardNum = srchTVCardNum;
	}
	public String getSrchCardNum() {
		return srchCardNum;
	}
	public void setSrchCardNum(String srchCardNum) {
		this.srchCardNum = srchCardNum;
	}
	public String getSrchColCond() {
		return srchColCond;
	}
	public void setSrchColCond(String srchColCond) {
		this.srchColCond = srchColCond;
	}
	public String getSrchCond() {
		return srchCond;
	}
	public void setSrchCond(String srchCond) {
		this.srchCond = srchCond;
	}	
	public String getSrchCondWord() {
		return srchCondWord;
	}
	public void setSrchCondWord(String srchCondWord) {
		this.srchCondWord = srchCondWord;
	}
	public String getSrchStartDate() {
		return srchStartDate;
	}
	public void setSrchStartDate(String srchStartDate) {
		this.srchStartDate = srchStartDate;
	}
	public String getSrchExpireDate() {
		return srchExpireDate;
	}
	public void setSrchExpireDate(String srchExpireDate) {
		this.srchExpireDate = srchExpireDate;
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
	public String getSrchSuccess() {
		return srchSuccess;
	}
	public void setSrchSuccess(String srchSuccess) {
		this.srchSuccess = srchSuccess;
	}
	public String getSrchFail() {
		return srchFail;
	}
	public void setSrchFail(String srchFail) {
		this.srchFail = srchFail;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public String getStDateTime() {
		return stDateTime;
	}
	public void setStDateTime(String stDateTime) {
		this.stDateTime = stDateTime;
	}
	public String getEdDateTime() {
		return edDateTime;
	}
	public void setEdDateTime(String edDateTime) {
		this.edDateTime = edDateTime;
	}
	public List<String> getFsList() {
		return fsList;
	}
	public void setFsList(List<String> fsList) {
		this.fsList = fsList;
	}
	public String getFsActive() {
		return fsActive;
	}
	public void setFsActive(String fsActive) {
		this.fsActive = fsActive;
	}
	public String getFsDelete() {
		return fsDelete;
	}
	public void setFsDelete(String fsDelete) {
		this.fsDelete = fsDelete;
	}
	public String getFsExpired() {
		return fsExpired;
	}
	public void setFsExpired(String fsExpired) {
		this.fsExpired = fsExpired;
	}
	public String getFsLost() {
		return fsLost;
	}
	public void setFsLost(String fsLost) {
		this.fsLost = fsLost;
	}
	public String getFsWait() {
		return fsWait;
	}
	public void setFsWait(String fsWait) {
		this.fsWait = fsWait;
	}
	public String getFsNone() {
		return fsNone;
	}
	public void setFsNone(String fsNone) {
		this.fsNone = fsNone;
	}
	public List<String> getSrchColChk() {
		return srchColChk;
	}
	public void setSrchColChk(List<String> srchColChk) {
		this.srchColChk = srchColChk;
	}
	public String getColChkQuery() {
		return colChkQuery;
	}
	public void setColChkQuery(String colChkQuery) {
		this.colChkQuery = colChkQuery;
	}
	public String getHidSortName() {
		return hidSortName;
	}
	public void setHidSortName(String hidSortName) {
		this.hidSortName = hidSortName;
	}
	public String getHidSortNum() {
		return hidSortNum;
	}
	public void setHidSortNum(String hidSortNum) {
		this.hidSortNum = hidSortNum;
	}	
	
	
	public String getSrchFpartnm1() {
		return srchFpartnm1;
	}
	public void setSrchFpartnm1(String srchFpartnm1) {
		this.srchFpartnm1 = srchFpartnm1;
	}
	public String getSrchFpartnm2() {
		return srchFpartnm2;
	}
	public void setSrchFpartnm2(String srchFpartnm2) {
		this.srchFpartnm2 = srchFpartnm2;
	}
	public String getSrchPartWord() {
		return srchPartWord;
	}
	public void setSrchPartWord(String srchPartWord) {
		this.srchPartWord = srchPartWord;
	}
	
	public List<String> getSrchColChk2() {
		return srchColChk2;
	}
	public void setSrchColChk2(List<String> srchColChk2) {
		this.srchColChk2 = srchColChk2;
	}
	public String getColChkQuery2() {
		return colChkQuery2;
	}
	public void setColChkQuery2(String colChkQuery2) {
		this.colChkQuery2 = colChkQuery2;
	}
	
	public String getSrchAuthType() {
		return srchAuthType;
	}
	public void setSrchAuthType(String srchAuthType) {
		this.srchAuthType = srchAuthType;
	}
	
	@Override
	public String toString() {
		return "LogInfoSearchVO [srchCond=" + srchCond + ", srchCondWord=" + srchCondWord + ", srchStartDate="
				+ srchStartDate + ", srchExpireDate=" + srchExpireDate + ", srchTemp=" + srchTemp + ", srchVisit="
				+ srchVisit + ", srchTVCardNum=" + srchTVCardNum + ", srchCardNum=" + srchCardNum + ", srchColCond="
				+ srchColCond + ", srchSuccess=" + srchSuccess + ", srchFail=" + srchFail + ", fromTime=" + fromTime
				+ ", toTime=" + toTime + ", stDateTime=" + stDateTime + ", edDateTime=" + edDateTime + ", srchPage="
				+ srchPage + ", srchCnt=" + srchCnt + ", offset=" + offset + ", curPage=" + curPage + ", curPageUnit="
				+ curPageUnit + ", fsList=" + fsList + ", srchColChk=" + srchColChk + ", colChkQuery=" + colChkQuery
				+ ", fsActive=" + fsActive + ", fsDelete=" + fsDelete + ", fsExpired=" + fsExpired + ", fsLost=" + fsLost
				+ ", fsWait=" + fsWait + ", fsNone=" + fsNone + ", srchCardStatus=" + srchCardStatus 
				+ ", hidSortName=" + hidSortName + ", hidSortNum=" + hidSortNum + "]";
	}
	
	public void autoOffset(){
		int off = (this.srchPage - 1) * this.srchCnt;
		if(off<0) off = 0;
		this.offset = off;
	}
}
