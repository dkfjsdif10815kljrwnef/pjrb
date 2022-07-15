package pjrb.cms.member.service;

import pjrb.user.service.PjrbDefaultVO;

/**
 * 회원관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성 
 *  
 */

public class CmsMemberVO extends PjrbDefaultVO {
	
	/** 회원구분 */
	private String type;
	
	/** 권한 */
	private String grade;
	
	/** 상태 */
	private String status;
	
	/** 사용자ID  */
	private String emplyrId;
	
	/** 비밀번호 */
	private String password;
	
	/** 사용자명 */
	private String userNm;
	
	/** 이에밀주소 */
	private String email;
	
	/** 휴대폰번호 */
	private String phone;
	
	/** 비밀번호오류횟수 */
	private String wrongCnt;

	/** 비밀번호최종수정일 */
	private String changeDate;
	
	/** 마지막로그인날짜 */
	private String lastLoginDate;
	
	/** 등록일 */
	private String regDate;
	
	/** 수정일 */
	private String modDate;
	
	/** 주소 */
	private String adres;
	
	/** 상세주소 */
	private String detailAdres;
	
	/** 우편번호 */
	private String zip;
	
	/** 소속 */
	private String department;
	
	/** 직위 */
	private String position;
	
	/** 탈퇴여부 */
	private String deleteAt;
	
	/** 탈퇴사유 */
	private String deleteReason;
	
	/** 기관구분 */
	private String orgnztFlag;
	
	/** 기관구분(기타) */
	private String orgnztFlagDetail;
	
	/** 기관명 */
	private String orgnztNm;
	
	/** 대표자명 */
	private String ceoNm;
	
	/** 사업자번호 */
	private String businessNum;
	
	/** 대표번호 */
	private String orgnztNum;
	
	/** 탈퇴여부 */
	private String allowYn;
	
	/** 입력폼 */
	private String memberForm;
	
	/** 검색등급 */
	private String searchType;
	
	/** 기타검색 */
	private String searchFlag;
	
	/** 비밀번호변경여부 */
	private String pwChange;

	private String emplyrIdList;
	
	private String typeList;
	
	private String uuid;
	
	private String loginIp;
	
	/**sns 가입유형*/
	private String snsType;
	
	/**sns 가입여부*/
	private String snsYn;
	
	/**휴대폰 인증코드*/
	private String selfCrtfcCode;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmplyrId() {
		return emplyrId;
	}

	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWrongCnt() {
		return wrongCnt;
	}

	public void setWrongCnt(String wrongCnt) {
		this.wrongCnt = wrongCnt;
	}

	public String getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
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

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getDetailAdres() {
		return detailAdres;
	}

	public void setDetailAdres(String detailAdres) {
		this.detailAdres = detailAdres;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDeleteAt() {
		return deleteAt;
	}

	public void setDeleteAt(String deleteAt) {
		this.deleteAt = deleteAt;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	public String getOrgnztFlag() {
		return orgnztFlag;
	}

	public void setOrgnztFlag(String orgnztFlag) {
		this.orgnztFlag = orgnztFlag;
	}

	public String getOrgnztFlagDetail() {
		return orgnztFlagDetail;
	}

	public void setOrgnztFlagDetail(String orgnztFlagDetail) {
		this.orgnztFlagDetail = orgnztFlagDetail;
	}

	public String getOrgnztNm() {
		return orgnztNm;
	}

	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	public String getCeoNm() {
		return ceoNm;
	}

	public void setCeoNm(String ceoNm) {
		this.ceoNm = ceoNm;
	}

	public String getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

	public String getOrgnztNum() {
		return orgnztNum;
	}

	public void setOrgnztNum(String orgnztNum) {
		this.orgnztNum = orgnztNum;
	}

	public String getAllowYn() {
		return allowYn;
	}

	public void setAllowYn(String allowYn) {
		this.allowYn = allowYn;
	}

	public String getMemberForm() {
		return memberForm;
	}

	public void setMemberForm(String memberForm) {
		this.memberForm = memberForm;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getPwChange() {
		return pwChange;
	}

	public void setPwChange(String pwChange) {
		this.pwChange = pwChange;
	}

	public String getEmplyrIdList() {
		return emplyrIdList;
	}

	public void setEmplyrIdList(String emplyrIdList) {
		this.emplyrIdList = emplyrIdList;
	}

	public String getTypeList() {
		return typeList;
	}

	public void setTypeList(String typeList) {
		this.typeList = typeList;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getSnsType() {
		return snsType;
	}

	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}

	public String getSnsYn() {
		return snsYn;
	}

	public void setSnsYn(String snsYn) {
		this.snsYn = snsYn;
	}

	public String getSelfCrtfcCode() {
		return selfCrtfcCode;
	}

	public void setSelfCrtfcCode(String selfCrtfcCode) {
		this.selfCrtfcCode = selfCrtfcCode;
	}

}
