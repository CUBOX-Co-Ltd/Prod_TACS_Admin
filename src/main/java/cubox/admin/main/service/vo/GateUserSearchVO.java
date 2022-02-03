package cubox.admin.main.service.vo;

import java.util.ArrayList;
import java.util.Arrays;

public class GateUserSearchVO {
	private int srchPage			= 1;	//조회할 페이지 번호 기본 1페이지
	private int srchCnt				= 10;	//조회할 페이지 수
	private int offset				= 0;
	private int curPage				= 1;	//조회할 페이지 번호 기본 1페이지
	private int curPageUnit			= 10;	//한번에 표시할 페이지 번호 개수
	private String unm;
	private String chkValueArray;
	private String chkTextArray;
	private String[] gateList;
	private String searchItemCenter;
	private String searchItemArea;
	private String searchItemFloor;
	
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
	
	public String[] getGateList() {
		return gateList;
	}
	public void setGateList(String[] gateList) {
		this.gateList = gateList;
	}
	public void autoOffset(){
		int off = (this.srchPage - 1) * this.srchCnt;
		if(off<0) off = 0;
		this.offset = off;
	}
	public String getSearchItemCenter() {
		return searchItemCenter;
	}
	public void setSearchItemCenter(String searchItemCenter) {
		this.searchItemCenter = searchItemCenter;
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
	
	@Override
	public String toString() {
		return "GateUserSearchVO [srchPage=" + srchPage + ", srchCnt=" + srchCnt + ", offset=" + offset + ", curPage="
				+ curPage + ", curPageUnit=" + curPageUnit + ", unm=" + unm + ", chkValueArray=" + chkValueArray
				+ ", chkTextArray=" + chkTextArray + ", gateList=" + Arrays.toString(gateList) + ", searchItemCenter="
				+ searchItemCenter + ", searchItemArea=" + searchItemArea + ", searchItemFloor=" + searchItemFloor
				+ "]";
	}
	
}
