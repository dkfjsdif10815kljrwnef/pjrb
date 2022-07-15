package pjrb.user.service;

import java.util.Date;

public class CryptoToken {

	private String tokenVersion;
	
	private String tokenValue;
	
	private Date issDate;
	
	private long period;
	
	private static CryptoToken cpt;
	
	
	
	public String getTokenVersion() {
		return tokenVersion;
	}



	public void setTokenVersion(String tokenVersion) {
		this.tokenVersion = tokenVersion;
	}



	public String getTokenValue() {
		return tokenValue;
	}



	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}



	public Date getIssDate() {
		return issDate;
	}



	public void setIssDate(Date issDate) {
		this.issDate = issDate;
	}



	public long getPeriod() {
		return period;
	}



	public void setPeriod(long period) {
		this.period = period;
	}



	public static CryptoToken getCpt() {
		return cpt;
	}



	public static void setCpt(CryptoToken cpt) {
		CryptoToken.cpt = cpt;
	}



	public static CryptoToken getInstance(){
         if(cpt == null){
               System.out.println("token is null");
               synchronized(CryptoToken.class){
                     if(cpt == null) {
                    	 return cpt = new CryptoToken();
                     }
               }
         }
         else {
        	 return cpt;
         }
         return cpt;
   }
}
