package pjrb.user.main.service;

public class DefaultVO {

	private String sessionId;
	private String sessionGrade;
	private String mngMember;
	private String mngSite;
	private String mngStat;
	private String mngMng;
	
	/** 검색일자 */
	private String searchDate;
	private String searchStartDate;
	private String searchEndDate;
	
	/** 검색조건 */
	private String searchCnd;
	
	/** 검색단어 */
	private String searchWrd;
	
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 10;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionGrade() {
		return sessionGrade;
	}

	public void setSessionGrade(String sessionGrade) {
		this.sessionGrade = sessionGrade;
	}

	public String getMngMember() {
		return mngMember;
	}

	public void setMngMember(String mngMember) {
		this.mngMember = mngMember;
	}

	public String getMngSite() {
		return mngSite;
	}

	public void setMngSite(String mngSite) {
		this.mngSite = mngSite;
	}

	public String getMngStat() {
		return mngStat;
	}

	public void setMngStat(String mngStat) {
		this.mngStat = mngStat;
	}

	public String getMngMng() {
		return mngMng;
	}

	public void setMngMng(String mngMng) {
		this.mngMng = mngMng;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public String getSearchStartDate() {
		return searchStartDate;
	}

	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}

	public String getSearchEndDate() {
		return searchEndDate;
	}

	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

	public String getSearchCnd() {
		return searchCnd;
	}

	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}

	public String getSearchWrd() {
		return searchWrd;
	}

	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

}
