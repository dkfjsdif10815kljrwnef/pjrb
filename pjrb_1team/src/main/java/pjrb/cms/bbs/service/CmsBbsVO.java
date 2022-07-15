package pjrb.cms.bbs.service;

import java.util.List;

import pjrb.user.service.PjrbDefaultVO;

public class CmsBbsVO extends PjrbDefaultVO{

	private String bbsId;
	
	private String nttNo;
	
	private String seq;
	
	private String addFieldCn;
	
	private String nttSj;
	
	private String nttCn;
	
	private String atchFileId;
	
	private String regId;

	private String modId;
	
	private String rdcnt;
	
	private String replyCn;
	
	private String useYn;
	
	private String ntcYn;
	
	private String regDate;
	
	private String modDate;
	
	private String fieldNm;
	
	private String thumbAtchFileId;

	private String videoFileId;
	
	private String videoUrl;
	
	private String videoFileType;
	
	private List<CmsBbsVO> cmsBbsVOList;
	
	private List<CmsBbsVO> groupByList;
    
    private List<CmsBbsVO> searchAddDt1FieldList;
    private List<CmsBbsVO> searchAddDt2FieldList;
    
    private List<CmsBbsVO> searchSelectBoxList;

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

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getAddFieldCn() {
		return addFieldCn;
	}

	public void setAddFieldCn(String addFieldCn) {
		this.addFieldCn = addFieldCn;
	}

	public String getNttSj() {
		return nttSj;
	}

	public void setNttSj(String nttSj) {
		this.nttSj = nttSj;
	}

	public String getNttCn() {
		return nttCn;
	}

	public void setNttCn(String nttCn) {
		this.nttCn = nttCn;
	}

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
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

	public List<CmsBbsVO> getCmsBbsVOList() {
		return cmsBbsVOList;
	}

	public void setCmsBbsVOList(List<CmsBbsVO> cmsBbsVOList) {
		this.cmsBbsVOList = cmsBbsVOList;
	}

	public String getRdcnt() {
		return rdcnt;
	}

	public void setRdcnt(String rdcnt) {
		this.rdcnt = rdcnt;
	}

	public String getReplyCn() {
		return replyCn;
	}

	public void setReplyCn(String replyCn) {
		this.replyCn = replyCn;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getNtcYn() {
		return ntcYn;
	}

	public void setNtcYn(String ntcYn) {
		this.ntcYn = ntcYn;
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

	public String getFieldNm() {
		return fieldNm;
	}

	public void setFieldNm(String fieldNm) {
		this.fieldNm = fieldNm;
	}

	public List<CmsBbsVO> getGroupByList() {
		return groupByList;
	}

	public void setGroupByList(List<CmsBbsVO> groupByList) {
		this.groupByList = groupByList;
	}

	public List<CmsBbsVO> getSearchAddDt1FieldList() {
		return searchAddDt1FieldList;
	}

	public void setSearchAddDt1FieldList(List<CmsBbsVO> searchAddDt1FieldList) {
		this.searchAddDt1FieldList = searchAddDt1FieldList;
	}

	public List<CmsBbsVO> getSearchAddDt2FieldList() {
		return searchAddDt2FieldList;
	}

	public void setSearchAddDt2FieldList(List<CmsBbsVO> searchAddDt2FieldList) {
		this.searchAddDt2FieldList = searchAddDt2FieldList;
	}

	public List<CmsBbsVO> getSearchSelectBoxList() {
		return searchSelectBoxList;
	}

	public void setSearchSelectBoxList(List<CmsBbsVO> searchSelectBoxList) {
		this.searchSelectBoxList = searchSelectBoxList;
	}

	public String getThumbAtchFileId() {
		return thumbAtchFileId;
	}

	public void setThumbAtchFileId(String thumbAtchFileId) {
		this.thumbAtchFileId = thumbAtchFileId;
	}

	public String getVideoFileId() {
		return videoFileId;
	}

	public void setVideoFileId(String videoFileId) {
		this.videoFileId = videoFileId;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getVideoFileType() {
		return videoFileType;
	}

	public void setVideoFileType(String videoFileType) {
		this.videoFileType = videoFileType;
	}
	
}
