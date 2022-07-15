package pjrb.cms.bbs.service;

import pjrb.user.service.PjrbDefaultVO;

public class CmsBbsCommentVO extends PjrbDefaultVO {
	
	private String seq;
	
	private String bbsId;
	
	private String nttNo;
	
	private String parentKey;
	
	private String cn;
	
	private String regDate;
	
	private String modDate;
	
	private String regId;

	private String modId;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	public String getNttNo() {
		return nttNo;
	}

	public void setNttNo(String nttNo) {
		this.nttNo = nttNo;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	
}
