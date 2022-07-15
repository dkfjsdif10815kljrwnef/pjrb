package pjrb.cms.grade.service;

import pjrb.user.service.PjrbDefaultVO;

public class CmsGradeInfoVO extends PjrbDefaultVO{
	String seq;
	String menuKey;
	String menuNm;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getMenuKey() {
		return menuKey;
	}
	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}  
	
}
