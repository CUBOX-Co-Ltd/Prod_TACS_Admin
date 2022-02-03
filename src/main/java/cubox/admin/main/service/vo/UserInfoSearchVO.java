package cubox.admin.main.service.vo;

import java.util.List;

public class UserInfoSearchVO {

	private String srchCond	  		= "";	//조회조건
	private String srchCondWord	  	= "";
	private String srchFusdt = "";
	private String srchFuedt = "";
	private String srchCfsdt	= "";
	private String srchCfedt	= "";
	private String srchTemp	= "";
	private String srchVisit	= "";
	private String srchTVCardNum	= "";
	private String srchCardNum	= "";
	private String srchGuid = "";
	private String srchFuid = "";
	private String srchFunm = "";
	private String srchPartcd2 = "";
	private String srchFtel = "";
	private String srchPartcd3 = "";
	private String srchFcarno = "";
	private List<String> srchColChk = null;
	private List<String> srchColChk2 = null;
	private String colChkQuery = "";
	private String colChkQuery2 = "";
	private String srchPartnm1 = "";
	private String srchPartnm2 = "";
	private String srchPartnm3 = "";
	private String srchPartnmWord = "";
	private String srchEfgateuid = "";
	private String srchCfstatusY = "";
	private String srchCfstatusD = "";
	private String srchCfstatusE = "";
	private String srchCfstatusL = "";
	private String srchCfstatusW = "";
	private String srchCfstatusN = "";
	private String srchFutypenm = "";
	private List<String> srchCfstatus = null;
	private String fgname;
	private String srchFbioyn = "";
	private String srchFexpireyn = "";
	private String srchFutype = "";
	private String srchFexp;
	private String hidSortName = "";
	private String hidSortNum = "";
	private String srchUseYn = "";
	private String srchFustatus = "";
	private String srchFcardStatus = "";
	private String srchFromDt = "";
	private String srchToDt = "";
	private String srchAuthType = "";
	private String srchFpartnm1 = "";
	private String srchFpartnm2 = "";
	private String srchPartWord = "";
	
	
	private int srchPage			= 1;	//조회할 페이지 번호 기본 1페이지
	private int srchCnt				= 10;	//조회할 페이지 수
	private int offset				= 0;
	private int curPage				= 1;	//조회할 페이지 번호 기본 1페이지
	private int curPageUnit			= 10;	//한번에 표시할 페이지 번호 개수

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
	public String getSrchFutype() {
		return srchFutype;
	}
	public void setSrchFutype(String srchFutype) {
		this.srchFutype = srchFutype;
	}

	public String getSrchFutypenm() {
		return srchFutypenm;
	}
	public void setSrchFutypenm(String srchFutypenm) {
		this.srchFutypenm = srchFutypenm;
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
	public String getSrchFusdt() {
		return srchFusdt;
	}
	public void setSrchFusdt(String srchFusdt) {
		this.srchFusdt = srchFusdt;
	}
	public String getSrchFuedt() {
		return srchFuedt;
	}
	public void setSrchFuedt(String srchFuedt) {
		this.srchFuedt = srchFuedt;
	}
	public String getSrchCfsdt() {
		return srchCfsdt;
	}
	public void setSrchCfsdt(String srchCfsdt) {
		this.srchCfsdt = srchCfsdt;
	}
	public String getSrchCfedt() {
		return srchCfedt;
	}
	public void setSrchCfedt(String srchCfedt) {
		this.srchCfedt = srchCfedt;
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
	public String getSrchGuid() {
		return srchGuid;
	}
	public void setSrchGuid(String srchGuid) {
		this.srchGuid = srchGuid;
	}
	public String getSrchFuid() {
		return srchFuid;
	}
	public void setSrchFuid(String srchFuid) {
		this.srchFuid = srchFuid;
	}
	public String getSrchFunm() {
		return srchFunm;
	}
	public void setSrchFunm(String srchFunm) {
		this.srchFunm = srchFunm;
	}
	public String getSrchPartcd2() {
		return srchPartcd2;
	}
	public void setSrchPartcd2(String srchPartcd2) {
		this.srchPartcd2 = srchPartcd2;
	}
	public String getSrchFtel() {
		return srchFtel;
	}
	public void setSrchFtel(String srchFtel) {
		this.srchFtel = srchFtel;
	}
	public String getSrchPartcd3() {
		return srchPartcd3;
	}
	public void setSrchPartcd3(String srchPartcd3) {
		this.srchPartcd3 = srchPartcd3;
	}
	public String getSrchFcarno() {
		return srchFcarno;
	}
	public void setSrchFcarno(String srchFcarno) {
		this.srchFcarno = srchFcarno;
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
	public String getSrchPartnm1() {
		return srchPartnm1;
	}
	public void setSrchPartnm1(String srchPartnm1) {
		this.srchPartnm1 = srchPartnm1;
	}
	public String getSrchPartnm2() {
		return srchPartnm2;
	}
	public void setSrchPartnm2(String srchPartnm2) {
		this.srchPartnm2 = srchPartnm2;
	}
	public String getSrchPartnm3() {
		return srchPartnm3;
	}
	public void setSrchPartnm3(String srchPartnm3) {
		this.srchPartnm3 = srchPartnm3;
	}
	public String getSrchPartnmWord() {
		return srchPartnmWord;
	}
	public void setSrchPartnmWord(String srchPartnmWord) {
		this.srchPartnmWord = srchPartnmWord;
	}
	public String getSrchEfgateuid() {
		return srchEfgateuid;
	}
	public void setSrchEfgateuid(String srchEfgateuid) {
		this.srchEfgateuid = srchEfgateuid;
	}
	public String getSrchCfstatusY() {
		return srchCfstatusY;
	}
	public void setSrchCfstatusY(String srchCfstatusY) {
		this.srchCfstatusY = srchCfstatusY;
	}
	public String getSrchCfstatusE() {
		return srchCfstatusE;
	}
	public void setSrchCfstatusE(String srchCfstatusE) {
		this.srchCfstatusE = srchCfstatusE;
	}
	public String getSrchCfstatusD() {
		return srchCfstatusD;
	}
	public void setSrchCfstatusD(String srchCfstatusD) {
		this.srchCfstatusD = srchCfstatusD;
	}
	public String getSrchCfstatusL() {
		return srchCfstatusL;
	}
	public void setSrchCfstatusL(String srchCfstatusL) {
		this.srchCfstatusL = srchCfstatusL;
	}
	public String getSrchCfstatusW() {
		return srchCfstatusW;
	}
	public void setSrchCfstatusW(String srchCfstatusW) {
		this.srchCfstatusW = srchCfstatusW;
	}
	public String getSrchCfstatusN() {
		return srchCfstatusN;
	}
	public void setSrchCfstatusN(String srchCfstatusN) {
		this.srchCfstatusN = srchCfstatusN;
	}
	public List<String> getSrchCfstatus() {
		return srchCfstatus;
	}
	public void setSrchCfstatus(List<String> srchCfstatus) {
		this.srchCfstatus = srchCfstatus;
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

	public String getFgname() {
		return fgname;
	}
	public void setFgname(String fgname) {
		this.fgname = fgname;
	}

	public String getSrchFbioyn() {
		return srchFbioyn;
	}
	public void setSrchFbioyn(String srchFbioyn) {
		this.srchFbioyn = srchFbioyn;
	}

	public String getSrchFexpireyn() {
		return srchFexpireyn;
	}
	public void setSrchFexpireyn(String srchFexpireyn) {
		this.srchFexpireyn = srchFexpireyn;
	}

	public String getSrchFexp() {
		return srchFexp;
	}
	public void setSrchFexp(String srchFexp) {
		this.srchFexp = srchFexp;
	}
	
	public String getSrchUseYn() {
		return srchUseYn;
	}
	public void setSrchUseYn(String srchUseYn) {
		this.srchUseYn = srchUseYn;
	}
	public String getSrchFustatus() {
		return srchFustatus;
	}
	public void setSrchFustatus(String srchFustatus) {
		this.srchFustatus = srchFustatus;
	}		
	public String getSrchFcardStatus() {
		return srchFcardStatus;
	}
	public void setSrchFcardStatus(String srchFcardStatus) {
		this.srchFcardStatus = srchFcardStatus;
	}
	
	public String getSrchFromDt() {
		return srchFromDt;
	}
	public void setSrchFromDt(String srchFromDt) {
		this.srchFromDt = srchFromDt;
	}
	public String getSrchToDt() {
		return srchToDt;
	}
	public void setSrchToDt(String srchToDt) {
		this.srchToDt = srchToDt;
	}
	public String getSrchAuthType() {
		return srchAuthType;
	}
	public void setSrchAuthType(String srchAuthType) {
		this.srchAuthType = srchAuthType;
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
	
	
	public String getColChkQuery2() {
		return colChkQuery2;
	}
	public void setColChkQuery2(String colChkQuery2) {
		this.colChkQuery2 = colChkQuery2;
	}
	
	public List<String> getSrchColChk2() {
		return srchColChk2;
	}
	public void setSrchColChk2(List<String> srchColChk2) {
		this.srchColChk2 = srchColChk2;
	}
	@Override
	public String toString() {
		return "UserInfoSearchVO [srchCond=" + srchCond + ", srchCondWord=" + srchCondWord + ", srchFusdt=" + srchFusdt
				+ ", srchFuedt=" + srchFuedt + ", srchCfsdt=" + srchCfsdt + ", srchCfedt=" + srchCfedt + ", srchTemp="
				+ srchTemp + ", srchVisit=" + srchVisit + ", srchTVCardNum=" + srchTVCardNum + ", srchCardNum="
				+ srchCardNum + ", srchGuid=" + srchGuid + ", srchFuid=" + srchFuid + ", srchFunm=" + srchFunm
				+ ", srchPartcd2=" + srchPartcd2 + ", srchFtel=" + srchFtel + ", srchPartcd3=" + srchPartcd3
				+ ", srchFcarno=" + srchFcarno + ", srchColChk=" + srchColChk + ", colChkQuery=" + colChkQuery
				+ ", srchPartnm1=" + srchPartnm1 + ", srchPartnm2=" + srchPartnm2 + ", srchPartnm3=" + srchPartnm3
				+ ", srchPartnmWord=" + srchPartnmWord + ", srchEfgateuid=" + srchEfgateuid + ", srchCfstatusY="
				+ srchCfstatusY + ", srchCfstatusD=" + srchCfstatusD + ", srchCfstatusE=" + srchCfstatusE
				+ ", srchCfstatusL=" + srchCfstatusL + ", srchCfstatusW=" + srchCfstatusW + ", srchCfstatusN="
				+ srchCfstatusN + ", srchFutypenm=" + srchFutypenm + ", srchCfstatus=" + srchCfstatus + ", fgname="
				+ fgname + ", srchFbioyn=" + srchFbioyn + ", srchFexpireyn=" + srchFexpireyn + ", srchFutype="
				+ srchFutype + ", srchFexp=" + srchFexp + ", srchFustatus=" + srchFustatus + ", srchPage=" + srchPage + ", srchCnt=" + srchCnt
				+ ", offset=" + offset + ", curPage=" + curPage + ", curPageUnit=" + curPageUnit + "]";
	}
}
