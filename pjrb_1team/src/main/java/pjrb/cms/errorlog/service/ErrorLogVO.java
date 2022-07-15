package pjrb.cms.errorlog.service;

/**
 * 에러로그 관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.18
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.18  권대성          최초 생성 
 *  
 */

public class ErrorLogVO {

	private String regDt;
	
	private String level;
	
	private String message;
	
	private String exception;
	
	private String logger;
	
	private String seq;

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	
}
