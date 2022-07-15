package pjrb.cms.temp.email.service;

import pjrb.user.service.PjrbDefaultVO;

public class AddrVo extends PjrbDefaultVO {

	/*pk*/
	String seq;
	String seqList;
	/*사용자*/
	String emplyrId;
	/*이메일*/
	String email;
	/*이름*/
	String name;
	/*등록일자*/
	String inputDate;
	/*삭제일자*/
	String deleteDate;
	/*휴대폰번호*/
	String phoneNumber;
	/*사용여부*/
	String useYn;
	/*첨부파일*/
	String atchFileId;

	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getEmplyr_id() {
		return emplyrId;
	}
	public void setEmplyr_id(String emplyrId) {
		this.emplyrId = emplyrId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInputDate() {
		return inputDate;
	}
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}
	public String getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSeqList() {
		return seqList;
	}
	public void setSeqList(String seqList) {
		this.seqList = seqList;
	}
	public String getEmplyrId() {
		return emplyrId;
	}
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	
}
