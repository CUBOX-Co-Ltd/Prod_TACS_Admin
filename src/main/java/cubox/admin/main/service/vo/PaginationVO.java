package cubox.admin.main.service.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cubox.admin.cmmn.util.CuboxProperties;

public class PaginationVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7013945198447392996L;
	
	public int totRecord = 0;		// 총 데이터 개수
	public int recPerPage = Integer.parseInt(CuboxProperties.getProperty("Globals.paging.recPerPage"));;		// 한 페이지당 레코드 개수
	public int curPage = 1;			// 현재 페이지번호
	public int totPage = 1;			// 총 페이지수
	public static int unitPage = Integer.parseInt(CuboxProperties.getProperty("Globals.paging.curPageUnit"));	// 한번에 보여줄 페이지개수
	public int startPage = 1;		// 현재 페이지를 기준으로 처음 시작하는 페이지번호
	public int endPage = 1;			// 현재 페이지를 기준으로 마지막에 표기되는 페이지번호

	private int firstRecordIndex = 0;
	private int lastRecordIndex = 0;

	public List<Integer> pageList = new ArrayList<Integer>();
	public void calcPageList(){
		setTotPage( ( (totRecord - 1 ) / recPerPage ) + 1 );
		setStartPage( (( (curPage - 1) / unitPage ) * unitPage) + 1 );
		setEndPage( Math.min(startPage + unitPage - 1, totPage) );

		// 현재페이지가 잘못입력되는 경우 1페이지부터 나오도록 세팅
		if(startPage > endPage){
			setStartPage( (( endPage / unitPage ) * unitPage) + 1 );
		}
		for(int i = startPage; i<=endPage; i++){
			pageList.add(i);
		}
	}

	public void calcRecordIndex(){
		setFirstRecordIndex((curPage - 1) * recPerPage);
		setLastRecordIndex(lastRecordIndex = curPage * recPerPage);
	}

	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}
	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}

	public int getLastRecordIndex() {
		return lastRecordIndex;
	}
	public void setLastRecordIndex(int lastRecordIndex) {
		this.lastRecordIndex = lastRecordIndex;
	}

	public int getTotRecord() {
		return totRecord;
	}
	public void setTotRecord(int totRecord) {
		this.totRecord = totRecord;
	}
	public int getRecPerPage() {
		return recPerPage;
	}
	public void setRecPerPage(int recPerPage) {
		this.recPerPage = recPerPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotPage() {
		return totPage;
	}
	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}
	public int getUnitPage() {
		return unitPage;
	}
	public void setUnitPage(int unitPage) {
		this.unitPage = unitPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public List<Integer> getPageList() {
		return pageList;
	}
	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}
	@Override
	public String toString() {
		return "PaginationVO [totRecord=" + totRecord + ", recPerPage=" + recPerPage + ", curPage=" + curPage
				+ ", totPage=" + totPage + ", unitPage=" + unitPage + ", startPage=" + startPage + ", endPage="
				+ endPage + "]";
	}
}
