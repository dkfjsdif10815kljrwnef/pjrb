package pjrb.cms.pagemng.service;

import pjrb.user.service.PjrbDefaultVO;

public class PageMngVO extends PjrbDefaultVO{

	private String pageId;
	
	private String pageNm;
	
	private String pageContents;
	
	private String useYn;
	
	private String cdate;
	
	private String mdate;
	
	private String cname;
	
	private String mname;

	private String uniqId;
	
	

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getPageNm() {
		return pageNm;
	}

	public void setPageNm(String pageNm) {
		this.pageNm = pageNm;
	}

	public String getPageContents() {
		return pageContents;
	}

	public void setPageContents(String pageContents) {
		this.pageContents = pageContents;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getUniqId() {
		return uniqId;
	}

	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}
	
	
}
