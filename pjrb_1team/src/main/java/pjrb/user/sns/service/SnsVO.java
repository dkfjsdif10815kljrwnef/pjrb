package pjrb.user.sns.service;

public class SnsVO {
	
	String snsType;
	String snsTitle;
	String snsImg;
	String snsLink;
	String token;
	String issueDate;
	
	public String getSnsType() {
		return snsType;
	}
	
	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}
	
	public String getSnsTitle() {
		return snsTitle;
	}
	
	public void setSnsTitle(String snsTitle) {
		this.snsTitle = snsTitle;
	}
	
	public String getSnsImg() {
		return snsImg;
	}
	
	public void setSnsImg(String snsImg) {
		this.snsImg = snsImg;
	}
	
	public String getSnsLink() {
		return snsLink;
	}
	
	public void setSnsLink(String snsLink) {
		this.snsLink = snsLink;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	
}
