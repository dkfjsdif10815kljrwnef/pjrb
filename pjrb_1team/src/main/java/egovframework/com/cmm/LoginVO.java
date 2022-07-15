package egovframework.com.cmm;

import java.io.Serializable;

/**
 * @Class Name : LoginVO.java
 * @Description : Login VO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.03.03    박지욱          최초 생성
 *
 *  @author 공통서비스 개발팀 박지욱
 *  @since 2009.03.03
 *  @version 1.0
 *  @see
 *  
 */
public class LoginVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8274004534207618049L;
	
	private String prePw;
	
	private String wrongCnt;
	
	private String changeDay;
	
	private String lastLoginDay;
	
	private String mngMember;
	
	private String mngPopup;
	
	private String mngBbs;
	
	private String mngBanner;
	
	private String mngStat;
	
	private String mngGrade;
	
	private String mngIp;
	
	private String mngLog;
	
	private String mngMenu;
	
	private String mngPagemanager;
	
	private String sessionGrade;
	
	private String sessionGradeNm;
	
	private String sessionId;
	
	private String auth;
	
	/** 아이디 */
	private String emplyrId;
	/** 이름 */
	private String userNm;
	/** 전화번호 */
	private String phone;
	/** 이메일주소 */
	private String email;
	/** 비밀번호 */
	private String password;
	/** 사용자구분 */
	private String type;
	/** 로그인 후 이동할 페이지 */
	private String url;
	/** 사용자 IP정보 */
	private String ip;
	
	private String status;

	/**sns 가입유형*/
	private String snsType;
	/**sns 가입여부*/
	private String snsYn;
	/**휴대폰 인증코드*/
	private String selfCrtfcCode;
	
	public String getPrePw() {
		return prePw;
	}

	public void setPrePw(String prePw) {
		this.prePw = prePw;
	}

	public void setChangeDay(String changeDay) {
		this.changeDay = changeDay;
	}

	public String getWrongCnt() {
		return wrongCnt;
	}

	public void setWrongCnt(String wrongCnt) {
		this.wrongCnt = wrongCnt;
	}

	public String getChangeDay() {
		return changeDay;
	}

	public void setChangeDate(String changeDay) {
		this.changeDay = changeDay;
	}

	public String getLastLoginDay() {
		return lastLoginDay;
	}

	public void setLastLoginDay(String lastLoginDay) {
		this.lastLoginDay = lastLoginDay;
	}

	public String getMngMember() {
		return mngMember;
	}

	public void setMngMember(String mngMember) {
		this.mngMember = mngMember;
	}

	public String getMngPopup() {
		return mngPopup;
	}

	public void setMngPopup(String mngPopup) {
		this.mngPopup = mngPopup;
	}

	public String getMngBbs() {
		return mngBbs;
	}

	public void setMngBbs(String mngBbs) {
		this.mngBbs = mngBbs;
	}

	public String getMngBanner() {
		return mngBanner;
	}

	public void setMngBanner(String mngBanner) {
		this.mngBanner = mngBanner;
	}

	public String getMngStat() {
		return mngStat;
	}

	public void setMngStat(String mngStat) {
		this.mngStat = mngStat;
	}

	public String getMngGrade() {
		return mngGrade;
	}

	public void setMngGrade(String mngGrade) {
		this.mngGrade = mngGrade;
	}

	public String getMngIp() {
		return mngIp;
	}

	public void setMngIp(String mngIp) {
		this.mngIp = mngIp;
	}

	public String getMngLog() {
		return mngLog;
	}

	public void setMngLog(String mngLog) {
		this.mngLog = mngLog;
	}

	public String getMngMenu() {
		return mngMenu;
	}

	public void setMngMenu(String mngMenu) {
		this.mngMenu = mngMenu;
	}

	public String getMngPagemanager() {
		return mngPagemanager;
	}

	public void setMngPagemanager(String mngPagemanager) {
		this.mngPagemanager = mngPagemanager;
	}

	public String getSessionGrade() {
		return sessionGrade;
	}

	public void setSessionGrade(String sessionGrade) {
		this.sessionGrade = sessionGrade;
	}

	public String getSessionGradeNm() {
		return sessionGradeNm;
	}

	public void setSessionGradeNm(String sessionGradeNm) {
		this.sessionGradeNm = sessionGradeNm;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getEmplyrId() {
		return emplyrId;
	}

	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
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
