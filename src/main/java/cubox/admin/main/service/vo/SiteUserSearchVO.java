package cubox.admin.main.service.vo;

import java.util.List;

import cubox.admin.cmmn.util.StringUtil;

public class SiteUserSearchVO {

	private String searchFuseyn;
	private String srchFsiteId;
	private String srchFname;
	private String srchFphone;
	private String srchCondWord;
	private String srchRadio;
	
	private List<String> srchColChk = null;
	private String colChkQuery = "";
	
	private int srchPage			= 1;	//조회할 페이지 번호 기본 1페이지
	private int srchCnt				= 10;	//조회할 페이지 수
	private int offset				= 0;
	private int curPage				= 1;	//조회할 페이지 번호 기본 1페이지
	private int curPageUnit			= 10;	//한번에 표시할 페이지 번호 개수
	
	public String getSearchFuseyn() {
		return searchFuseyn;
	}
	public void setSearchFuseyn(String searchFuseyn) {
		this.searchFuseyn = searchFuseyn;
	}
	public String getSrchFsiteId() {
		return srchFsiteId;
	}
	public void setSrchFsiteId(String srchFsiteId) {
		this.srchFsiteId = srchFsiteId;
	}
	public String getSrchFname() {
		return srchFname;
	}
	public void setSrchFname(String srchFname) {
		this.srchFname = srchFname;
	}
	public String getSrchFphone() {
		return srchFphone;
	}
	public void setSrchFphone(String srchFphone) {
		this.srchFphone = srchFphone;
	}
	public String getSrchCondWord() {
		return srchCondWord;
	}
	public void setSrchCondWord(String srchCondWord) {
		this.srchCondWord = srchCondWord;
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
	public String getSrchRadio() {
		return srchRadio;
	}
	public void setSrchRadio(String srchRadio) {
		this.srchRadio = srchRadio;
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

	
	
}
