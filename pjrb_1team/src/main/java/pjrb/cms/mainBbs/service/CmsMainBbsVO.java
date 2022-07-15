package pjrb.cms.mainBbs.service;

import pjrb.user.service.PjrbDefaultVO;

/**
 * 메인 게시판 관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성 
 *  
 */

public class CmsMainBbsVO extends PjrbDefaultVO {
	
	private String searchType;
	
	private String searchStatus;
	
	private String seq;
	
	/** 구분 */
	private String type;
	
	/** 게시판ID */
	private String bbsId;
	
	/** 게시판 유형 */
	private String bbsTp;
	
	/** 글번호 */
	private String nttNo;
	
	/** 게시판명 */
	private String bbsNm;
	
	/** 제목 */
	private String title;
	
	/** 사용여부 */
	private String useYn;
	
	/** 등록일 */
	private String regDate;
	
	/** 수정일 */
	private String modDate;
	
	/** 선택삭제 */
	private String seqList;

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	public String getBbsTp() {
		return bbsTp;
	}

	public void setBbsTp(String bbsTp) {
		this.bbsTp = bbsTp;
	}

	public String getNttNo() {
		return nttNo;
	}

	public void setNttNo(String nttNo) {
		this.nttNo = nttNo;
	}

	public String getBbsNm() {
		return bbsNm;
	}

	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
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

	public String getSeqList() {
		return seqList;
	}

	public void setSeqList(String seqList) {
		this.seqList = seqList;
	}

	
}
