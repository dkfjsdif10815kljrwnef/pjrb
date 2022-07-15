package pjrb.cms.grade.service;

import pjrb.user.service.PjrbDefaultVO;

/**
 * 권한관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.16
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.16  권대성          최초 생성 
 *  
 */

public class CmsGradeVO extends PjrbDefaultVO {
	
private String grade;
	
	private String gradeNm;
	
	private String auth;

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGradeNm() {
		return gradeNm;
	}

	public void setGradeNm(String gradeNm) {
		this.gradeNm = gradeNm;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
	
}
