package pjrb.cms.stat.service;

import pjrb.user.service.PjrbDefaultVO;

/**
 * 접속통계
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성 
 *  
 */

public class CmsConstatVO extends PjrbDefaultVO {
	
	private String engYn;
	private String idx;
	private String ip;
	private String osPc;
	private String osMobile;
	private String browser;
	private String date;
	
	private String startDate;
	private String endDate;
	
	private String searchOrder;
	
	public String getEngYn() {
		return engYn;
	}
	public void setEngYn(String engYn) {
		this.engYn = engYn;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOsPc() {
		return osPc;
	}
	public void setOsPc(String osPc) {
		this.osPc = osPc;
	}
	public String getOsMobile() {
		return osMobile;
	}
	public void setOsMobile(String osMobile) {
		this.osMobile = osMobile;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSearchOrder() {
		return searchOrder;
	}
	public void setSearchOrder(String searchOrder) {
		this.searchOrder = searchOrder;
	}
	
	
}

