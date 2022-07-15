package pjrb.cms.accessip.service;

import pjrb.user.service.PjrbDefaultVO;

/**
 * 접근IP 관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성 
 *  
 */

public class CmsAccessIPVO  extends PjrbDefaultVO{
	private String idx;
	private String name;
	private String ip;
	private String useAt;
	private String RegistDe;
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUseAt() {
		return useAt;
	}
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	public String getRegistDe() {
		return RegistDe;
	}
	public void setRegistDe(String registDe) {
		RegistDe = registDe;
	}
	
	
	
	
	
}
