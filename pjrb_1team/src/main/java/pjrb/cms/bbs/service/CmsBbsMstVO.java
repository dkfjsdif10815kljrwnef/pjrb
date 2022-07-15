package pjrb.cms.bbs.service;

import java.util.List;

import pjrb.user.service.PjrbDefaultVO;

public class CmsBbsMstVO extends PjrbDefaultVO{
	
	private String bbsId;
	
	private String bbsNm;
	
	private String bbsTp;
	
	private String cmdYn;
	
	private String noticeYn;
	
	private String atchFileNum;
	
	private String atchFileSize;
	
	private String regId;
	
	private String modId;
	
	private String seq;
	
	private String fieldNm;
	
	private String fieldDtYn;
	
	private String useYn;
	
	private String sort;
	
	private String fieldStatusYn;
	
	private String fieldTermYn;
	
	private String fieldStatusSeq;
	
	private String fieldSelectSearchYn;
	
	private List<CmsBbsMstVO> cmsBbsMstVOList;
	
	private List<CmsBbsMstVO> cmsBbsMstVOList2;

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	public String getBbsNm() {
		return bbsNm;
	}

	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}

	public String getBbsTp() {
		return bbsTp;
	}

	public void setBbsTp(String bbsTp) {
		this.bbsTp = bbsTp;
	}

	public String getCmdYn() {
		return cmdYn;
	}

	public void setCmdYn(String cmdYn) {
		this.cmdYn = cmdYn;
	}

	public String getNoticeYn() {
		return noticeYn;
	}

	public void setNoticeYn(String noticeYn) {
		this.noticeYn = noticeYn;
	}

	public String getAtchFileNum() {
		return atchFileNum;
	}

	public void setAtchFileNum(String atchFileNum) {
		this.atchFileNum = atchFileNum;
	}

	public String getAtchFileSize() {
		return atchFileSize;
	}

	public void setAtchFileSize(String atchFileSize) {
		this.atchFileSize = atchFileSize;
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

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getFieldNm() {
		return fieldNm;
	}

	public void setFieldNm(String fieldNm) {
		this.fieldNm = fieldNm;
	}

	public String getFieldDtYn() {
		return fieldDtYn;
	}

	public void setFieldDtYn(String fieldDtYn) {
		this.fieldDtYn = fieldDtYn;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public List<CmsBbsMstVO> getCmsBbsMstVOList() {
		return cmsBbsMstVOList;
	}

	public void setCmsBbsMstVOList(List<CmsBbsMstVO> cmsBbsMstVOList) {
		this.cmsBbsMstVOList = cmsBbsMstVOList;
	}

	public List<CmsBbsMstVO> getCmsBbsMstVOList2() {
		return cmsBbsMstVOList2;
	}

	public void setCmsBbsMstVOList2(List<CmsBbsMstVO> cmsBbsMstVOList2) {
		this.cmsBbsMstVOList2 = cmsBbsMstVOList2;
	}

	public String getFieldStatusYn() {
		return fieldStatusYn;
	}

	public void setFieldStatusYn(String fieldStatusYn) {
		this.fieldStatusYn = fieldStatusYn;
	}

	public String getFieldTermYn() {
		return fieldTermYn;
	}

	public void setFieldTermYn(String fieldTermYn) {
		this.fieldTermYn = fieldTermYn;
	}

	public String getFieldStatusSeq() {
		return fieldStatusSeq;
	}

	public void setFieldStatusSeq(String fieldStatusSeq) {
		this.fieldStatusSeq = fieldStatusSeq;
	}

	public String getFieldSelectSearchYn() {
		return fieldSelectSearchYn;
	}

	public void setFieldSelectSearchYn(String fieldSelectSearchYn) {
		this.fieldSelectSearchYn = fieldSelectSearchYn;
	}

	@Override
	public String toString() {
		return "CmsBbsMstVO [bbsId=" + bbsId + ", bbsNm=" + bbsNm + ", bbsTp=" + bbsTp + ", cmdYn=" + cmdYn
				+ ", noticeYn=" + noticeYn + ", atchFileNum=" + atchFileNum + ", atchFileSize=" + atchFileSize
				+ ", regId=" + regId + ", modId=" + modId + ", seq=" + seq + ", fieldNm=" + fieldNm + ", fieldDtYn="
				+ fieldDtYn + ", useYn=" + useYn + ", sort=" + sort + ", fieldStatusYn=" + fieldStatusYn
				+ ", fieldTermYn=" + fieldTermYn + ", fieldStatusSeq=" + fieldStatusSeq + ", fieldSelectSearchYn="
				+ fieldSelectSearchYn + ", cmsBbsMstVOList=" + cmsBbsMstVOList + ", cmsBbsMstVOList2="
				+ cmsBbsMstVOList2 + "]";
	}
	
	
}
