package cubox.admin.main.service.vo;

import java.util.Arrays;

public class AuthGroupSearchVO {
	private String srchCond	  		= "";	//조회조건
	private String srchCondWord	  	= "";
	private String srchStartDate	= "";
	private String srchExpireDate	= "";
	private String srchTemp	= "";
	private String srchVisit	= "";
	private String srchTVCardNum	= "";
	private String srchCardNum	= "";
	private int srchPage			= 1;	//조회할 페이지 번호 기본 1페이지
	private int srchCnt				= 20;	//조회할 페이지 수
	private int offset				= 0;
	private int curPage				= 1;	//조회할 페이지 번호 기본 1페이지
	private int curPageUnit			= 10;	//한번에 표시할 페이지 번호 개수
	private String unm;
	private String searchItemCenter;
	private String searchItemAuth;
	private String[] authList;
	private String chkValueArray;
	private String chkTextArray;
	
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
	public String getUnm() {
		return unm;
	}
	public void setUnm(String unm) {
		this.unm = unm;
	}
	public String getSearchItemAuth() {
		return searchItemAuth;
	}
	public void setSearchItemAuth(String searchItemAuth) {
		this.searchItemAuth = searchItemAuth;
	}
	public String getSearchItemCenter() {
		return searchItemCenter;
	}
	public void setSearchItemCenter(String searchItemCenter) {
		this.searchItemCenter = searchItemCenter;
	}
	public String[] getAuthList() {
		return authList;
	}
	public void setAuthList(String[] authList) {
		this.authList = authList;
	}
	public String getChkValueArray() {
		return chkValueArray;
	}
	public void setChkValueArray(String chkValueArray) {
		this.chkValueArray = chkValueArray;
	}
	public String getChkTextArray() {
		return chkTextArray;
	}
	public void setChkTextArray(String chkTextArray) {
		this.chkTextArray = chkTextArray;
	}
	@Override
	public String toString() {
		return "AuthGroupSearchVO [srchCond=" + srchCond + ", srchCondWord=" + srchCondWord + ", srchStartDate="
				+ srchStartDate + ", srchExpireDate=" + srchExpireDate + ", srchTemp=" + srchTemp + ", srchVisit="
				+ srchVisit + ", srchTVCardNum=" + srchTVCardNum + ", srchCardNum=" + srchCardNum + ", srchPage="
				+ srchPage + ", srchCnt=" + srchCnt + ", offset=" + offset + ", curPage=" + curPage + ", curPageUnit="
				+ curPageUnit + ", unm=" + unm + ", searchItemCenter=" + searchItemCenter + ", searchItemAuth="
				+ searchItemAuth + ", authList=" + Arrays.toString(authList) + ", chkValueArray=" + chkValueArray
				+ ", chkTextArray=" + chkTextArray + "]";
	}
	
	public void autoOffset(){
		int off = (this.srchPage - 1) * this.srchCnt;
		if(off<0) off = 0;
		this.offset = off;
	}
	
	
}
