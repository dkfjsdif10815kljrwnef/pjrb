package pjrb.cms.stat.service;

import pjrb.user.service.PjrbDefaultVO;

/**
 * 페이지통계
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성 
 *  
 */

public class CmsPagestatVO extends PjrbDefaultVO {
	
	private String idx;
	private String engYn;
	private String menuTitle;
	private String url;
	private String conDate;
	
	private String startDate;
	private String endDate;
	private String searchOrder;
	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getEngYn() {
		return engYn;
	}
	public void setEngYn(String engYn) {
		this.engYn = engYn;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getConDate() {
		return conDate;
	}
	public void setConDate(String conDate) {
		this.conDate = conDate;
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

