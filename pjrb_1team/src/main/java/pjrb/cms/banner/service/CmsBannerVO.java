package pjrb.cms.banner.service;

import pjrb.user.service.PjrbDefaultVO;

/**
 * 배너 및 관련사이트 관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성
 *  
 */

public class CmsBannerVO extends PjrbDefaultVO {
	
	private String bannerDetail;
	
	private String searchPurpose;
	
	private String type;
	
	private String purpose;
	
	private String searchStatus;
	
	private String searchEngYn;
	
	private String engYn;
	
	private String seq;
	
	/** 상태 */
	private String status;
	
	/** 사이트명 */
	private String title;
	
	/** 주소 */
	private String detail;

	/** 등록일 */
	private String regDate;
	
	/** 수정일 */
	private String modDate;
	
	/** 파일 */
	private String atchFileId;
	
	/** 로그인 체크 사용여부 */
	private String loginChk;
	
	private String seqList;

	public String getBannerDetail() {
		return bannerDetail;
	}

	public void setBannerDetail(String bannerDetail) {
		this.bannerDetail = bannerDetail;
	}

	public String getSearchPurpose() {
		return searchPurpose;
	}

	public void setSearchPurpose(String searchPurpose) {
		this.searchPurpose = searchPurpose;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public String getSearchEngYn() {
		return searchEngYn;
	}

	public void setSearchEngYn(String searchEngYn) {
		this.searchEngYn = searchEngYn;
	}

	public String getEngYn() {
		return engYn;
	}

	public void setEngYn(String engYn) {
		this.engYn = engYn;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getLoginChk() {
		return loginChk;
	}

	public void setLoginChk(String loginChk) {
		this.loginChk = loginChk;
	}

	public String getSeqList() {
		return seqList;
	}

	public void setSeqList(String seqList) {
		this.seqList = seqList;
	}

}
