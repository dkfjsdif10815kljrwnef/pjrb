package pjrb.cms.popup.service;

import pjrb.user.service.PjrbDefaultVO;

/**
 * 팝업관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성 
 *  
 */

public class CmsPopupVO extends PjrbDefaultVO {
	
	private String searchStatus;
	
	private String atchFileId;
	
	private String seq;
	
	/** 상태 */
	private String status;
	
	/** 팝업명 */
	private String title;
	
	/** 내용 */
	private String detail;
	
	private String address;
	
	/** 새창구분 */
	private String flag;
	
	/** 시작일 */
	private String startDate;
	
	/** 시작시간 */
	private String startTime;
	
	/** 종료일 */
	private String endDate;
	
	/** 종료시간 */
	private String endTime;
	
	/** 가로크기 */
	private String widthSize;
	
	/** 가로위치 */
	private String widthLocation;
	
	/** 세로크기 */
	private String heightSize;
	
	/** 세로위치 */
	private String heightLocation;

	/** 등록일 */
	private String regDate;
	
	/** 수정일 */
	private String modDate;
	
	/** 로그인 체크 사용여부 */
	private String loginChk;
	
	private String seqList;

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getWidthSize() {
		return widthSize;
	}

	public void setWidthSize(String widthSize) {
		this.widthSize = widthSize;
	}

	public String getWidthLocation() {
		return widthLocation;
	}

	public void setWidthLocation(String widthLocation) {
		this.widthLocation = widthLocation;
	}

	public String getHeightSize() {
		return heightSize;
	}

	public void setHeightSize(String heightSize) {
		this.heightSize = heightSize;
	}

	public String getHeightLocation() {
		return heightLocation;
	}

	public void setHeightLocation(String heightLocation) {
		this.heightLocation = heightLocation;
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
