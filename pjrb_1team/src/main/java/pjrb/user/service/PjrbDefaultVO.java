package pjrb.user.service;

/**
 * 기본VO
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.16
 * version 1.0
 *  
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2021.02.16  권대성          최초 생성 
 *  
 */

public class PjrbDefaultVO {

	private String excelFlag;
	
	private String excelType;
	
	private String sessionId;
	
	private String sessionGrade;
	
	private String mngProposal;
	
	private String mngMember;
	
	private String mngPopup;
	
	private String mngBbs;
	
	private String mngBanner;
	
	private String mngStat;
	
	private String mngGrade;
	
	private String mngIp;
	
	private String mngLog;
	
	private String mngMenu;
	
	private String mngPagemanager;
	
	private String searchFlag;
	
	private String searchEngYn;
	
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
    
    /** 페이지사이즈 */
    private int pageSize = 10;
    
    /** 페이지사이즈 */
    private int pageUnit = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

	public String getExcelFlag() {
		return excelFlag;
	}

	public void setExcelFlag(String excelFlag) {
		this.excelFlag = excelFlag;
	}

	public String getExcelType() {
		return excelType;
	}

	public void setExcelType(String excelType) {
		this.excelType = excelType;
	}

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

	public String getMngProposal() {
		return mngProposal;
	}

	public void setMngProposal(String mngProposal) {
		this.mngProposal = mngProposal;
	}

	public String getMngMember() {
		return mngMember;
	}

	public void setMngMember(String mngMember) {
		this.mngMember = mngMember;
	}

	public String getMngPopup() {
		return mngPopup;
	}

	public void setMngPopup(String mngPopup) {
		this.mngPopup = mngPopup;
	}

	public String getMngBbs() {
		return mngBbs;
	}

	public void setMngBbs(String mngBbs) {
		this.mngBbs = mngBbs;
	}

	public String getMngBanner() {
		return mngBanner;
	}

	public void setMngBanner(String mngBanner) {
		this.mngBanner = mngBanner;
	}

	public String getMngStat() {
		return mngStat;
	}

	public void setMngStat(String mngStat) {
		this.mngStat = mngStat;
	}

	public String getMngGrade() {
		return mngGrade;
	}

	public void setMngGrade(String mngGrade) {
		this.mngGrade = mngGrade;
	}

	public String getMngIp() {
		return mngIp;
	}

	public void setMngIp(String mngIp) {
		this.mngIp = mngIp;
	}

	public String getMngLog() {
		return mngLog;
	}

	public void setMngLog(String mngLog) {
		this.mngLog = mngLog;
	}

	public String getMngMenu() {
		return mngMenu;
	}

	public void setMngMenu(String mngMenu) {
		this.mngMenu = mngMenu;
	}

	public String getMngPagemanager() {
		return mngPagemanager;
	}

	public void setMngPagemanager(String mngPagemanager) {
		this.mngPagemanager = mngPagemanager;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getSearchEngYn() {
		return searchEngYn;
	}

	public void setSearchEngYn(String searchEngYn) {
		this.searchEngYn = searchEngYn;
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
