package pjrb.cmm.service;

import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class PjrbCmmUtil {

	//쿠키 가져오기
	public static String pjrbGetCookies(HttpServletRequest request,String cookieNm){
		Cookie cookies[] = request.getCookies();
		String cookie = "";
		if(cookies != null) {
			for(Cookie coo : cookies) {
				if(cookieNm.equals(coo.getName())) {
					cookie = coo.getValue();
				}
			}
		}
		return cookie;
	}
	
	// 랜덤 string 생성
	public static String pjrbRandomString(int length){
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		Random random = new Random();

		String randString = random.ints(leftLimit,rightLimit + 1)
		  .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		  .limit(length)
		  .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		  .toString();
		return randString;
	}
}
