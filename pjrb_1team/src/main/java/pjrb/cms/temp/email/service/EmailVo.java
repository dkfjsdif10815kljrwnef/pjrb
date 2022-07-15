package pjrb.cms.temp.email.service;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import antlr.collections.List;


public class EmailVo {
	String email;
	String[] emailArr;
	int emailLength;
	String subject;
	String text;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getEmailArr() {
		return emailArr;
	}
	public void setEmailArr(String[] emailArr) {
		this.emailArr = emailArr;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSubject() {
		return subject;
	}
	public String getText() {
		return text;
	}
	public int getEmailLength() {
		return emailArr.length;
	}

}
