package pjrb.user.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pjrb.cmm.service.PjrbCmmUtil;
import pjrb.user.service.CryptoToken;

@Controller
public class AuthController {
	
	
	private static String Client_ID = "28b524f7-0ef2-4e15-b75c-daa4a68cb23b";
	private static String Client_Secret = "b255f1c4dd0def83d061ca626c035ad5573babe";
	/**
	 * 나이스 영구토큰 얻기 url
	 * @throws Exception
	 */
	@RequestMapping("/getNiceToken.do")
	public void getNiceToken() throws Exception{
		
		System.out.println("niceToken :: " + getNiceTokenMethod());
	}
	
	
	/**
	 * 나이스 영구토큰 폐기 
	 * @throws Exception
	 */
	@RequestMapping("/revokeNiceToken.do")
	public void niceToken() throws Exception{
		String url  = "https://svc.niceapi.co.kr:22001/digital/niceid/oauth/oauth/token/revokeById";
		String niceToken = getNiceTokenMethod();
		long current_timestamp = System.currentTimeMillis()/1000;
		
		String key = niceToken + ":" + current_timestamp + "+" + Client_ID;
		
		 String auth = "Basic " + Base64.encodeBase64String(key.getBytes());
		
		System.out.println("revokeNiceToken :: " +urlRequest(url,auth,"result" , "",""));
	}
	
	
	/**
	 * 암호화 토큰 얻기
	 * @throws Exception
	 */
//	@RequestMapping("/getEncryptionNiceToken.do")
	public static Map<String, Object> getEncryptionNiceToken(HttpSession session) throws Exception{
		
		String url  = "https://svc.niceapi.co.kr:22001/digital/niceid/api/v1.0/common/crypto/token";
		String niceToken = getNiceTokenMethod();
		Map<String, String> returnMapChk = new HashMap<>();
		Map<String, String> returnMapIpin = new HashMap<>();
		Map<String, Object> returnMap = new HashMap<>();
		
		session.setAttribute("niceResultMap", null);
		
		if(!IsNiceTokenExpire(session)) {
			
			//암호화토큰 만료 또는 암호화토큰이 없을때 암호화토큰 생성 유효기간 1시간
			
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			
			long current_timestamp = System.currentTimeMillis()/1000;
			
			String key = niceToken + ":" + current_timestamp + ":" + Client_ID;
			
			String auth = "bearer " + Base64.encodeBase64String(key.getBytes());
			
			String sdt = sdf.format(dt);
			
			String req_no = PjrbCmmUtil.pjrbRandomString(30);
			
			String paramData = "{ \"dataHeader\":{\"CNTY_CD\":\"ko\"},\"dataBody\":{\"req_dtim\":\""+sdt+"\",\"req_no\":\""+req_no+"\",\"enc_mode\":\"1\"}}";
			
			String returnDataChk = urlRequest(url,auth,"encryp" , paramData,"chkPlus");
			String returnDataIpin = urlRequest(url,auth,"encryp" , paramData,"Ipin");
			
			returnMapChk = getNiceMap(returnDataChk,sdt,req_no,"chkPlus");
			returnMapIpin = getNiceMap(returnDataIpin,sdt,req_no,"Ipin");
			
			session.setAttribute("niceDataChk", returnMapChk);
			session.setAttribute("niceDataIpin", returnMapIpin);
			
		}else {
			returnMap.put("niceDataChk", session.getAttribute("niceDataChk"));
			returnMap.put("niceDataIpin", session.getAttribute("niceDataIpin"));
		}
	    
	    return returnMap;
	}
	
	public static Map<String,String> getNiceMap(String returnData, String sdt, String req_no,String type) throws Exception{
		Map<String, String> returnMap = new HashMap<>();
	
		String returnUrl = "";
		
		if(type.equals("Ipin")){
			returnUrl= "http:localhost:8080/nice/NiceIpinProcess.do";
		}else{
			returnUrl= "http:localhost:8080/nice/NiceCheckPlusProcess.do";
		}
		
		
		JSONParser jParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jParser.parse(returnData);
		
		String token_val = jsonObject.get("token_val").toString();
		String site_code = jsonObject.get("site_code").toString();
		String token_version_id = jsonObject.get("token_version_id").toString();
		String period = jsonObject.get("period").toString();
		
		
		//System.out.println("encryptionToken token_val :: " + token_val);
		//System.out.println("encryptionToken site_code :: " + site_code);
		//System.out.println("encryptionToken token_version_id :: " + token_version_id);
		
		String v = sdt.trim() + req_no.trim() + token_val.trim();
		
		MessageDigest  md = MessageDigest.getInstance("SHA-256");
		md.update(v.getBytes());
		byte[] arrHashValue = md.digest();
		String resultVal = Base64.encodeBase64String(arrHashValue);
		
		String returnKey = resultVal.substring(0, 16);
		String returnIv = resultVal.substring(resultVal.length() - 16);
		String returnHmacKey = resultVal.substring(0, 32);
		
		String reqData = "{\"requestno\":\""+req_no+"\",\"returnurl\":\""+returnUrl+"\",\"sitecode\":\""+site_code+"\"}";
		
		SecretKey secureKey = new SecretKeySpec(returnKey.getBytes(), "AES");
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(returnIv.getBytes()));
		byte[] encrypted = c.doFinal(reqData.trim().getBytes());
		String enc_data = Base64.encodeBase64String(encrypted);
		
		byte[] hmacSha256 = hmac256(returnHmacKey.getBytes(), enc_data.getBytes());
		String integrity_value = Base64.encodeBase64String(hmacSha256);
		
		
		returnMap.put("integrity_value", integrity_value);
		returnMap.put("enc_data", enc_data);
		returnMap.put("token_version_id", token_version_id);
		returnMap.put("iv", returnIv);
		returnMap.put("key", returnKey);
		returnMap.put("token_val", token_val);
		returnMap.put("period", period);
	
		return returnMap;
	}
	
	public static byte[] hmac256(byte[] secretKey,byte[] message){
	      byte[] hmac256 = null;
	      try{
	            Mac mac = Mac.getInstance("HmacSHA256");
	            SecretKeySpec sks = new SecretKeySpec(secretKey, "HmacSHA256");
	            mac.init(sks);
	            hmac256 = mac.doFinal(message);

	            return hmac256;
	      } catch(Exception e){
	            throw new RuntimeException("Failed to generate HMACSHA256 encrypt");
	      }

	}
	
	/**
	 * 나이스 영구토큰 얻기
	 * @return
	 * @throws Exception
	 */
	public static String getNiceTokenMethod() throws Exception{
		String url  = "https://svc.niceapi.co.kr:22001/digital/niceid/oauth/oauth/token?grant_type=client_credentials&scope=default";
		
		String key = Client_ID+":"+Client_Secret;
		
	    String auth = "Basic " + Base64.encodeBase64String(key.getBytes());
	    
		return urlRequest(url,auth,"access_token","","");
	}
	
	
	public static boolean IsNiceTokenExpire(HttpSession session) throws Exception{
		
		boolean validToken = false;
		
		Map<String,String> niceData = (Map<String, String>) session.getAttribute("niceDataChk");
		
		if(niceData != null) {

			/* 1. 암호화토큰요청_API 요청 진행 */
			/* 2. API 결과 세팅 - 세팅된 결과 */
			CryptoToken.getInstance().setTokenVersion(niceData.get("token_version_id").toString()); //토큰 버전 아이디
			CryptoToken.getInstance().setTokenValue(niceData.get("token_val").toString()); //토큰값
			CryptoToken.getInstance().setPeriod(Long.valueOf(niceData.get("period").toString().replace(".0", "")).longValue() ); //토큰 period
			Date issdate = new Date();
			CryptoToken.getInstance().setIssDate(issdate); //토큰 발급일시

			/* 3. 토큰유효기간 비교 - 만료되었을 경우 재발급 */
			if(CryptoToken.getInstance().getTokenVersion().isEmpty()){ //토큰이 없을 경우
			      //암호화토큰요청_API 요청 및 토큰 저장
			}
			else{
			      Date today = new Date();
			      today.getTime(); 
			      //토큰 유효기간이 지났는지 확인 - 초를 밀리세컨드로 표시
			      validToken = CryptoToken.getInstance().getIssDate().after(new Date(today.getTime() - (CryptoToken.getInstance().getPeriod() * 1000)));
			      //validToken의 값이 false면 토큰 재발급 처리, true면 기존 암호화값 사용
			}
		}
		
		return validToken;
	}
	
	/**
	 * url 요청
	 * @param url 호출주소
	 * @param auth base64인코딩된 헤더
	 * @param key 리턴된 요청값 - json 값 키
	 * @return
	 * @throws Exception
	 */
	public static String urlRequest(String url , String auth , String key , String paramData, String type) throws Exception{
		
		String returnData = "";
		HttpURLConnection conn = null;
	     
	    URL reqUrl = new URL(url);
	     
	    conn = (HttpURLConnection) reqUrl.openConnection();
	     
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    conn.setRequestProperty("Authorization", auth);
	    
	    if(paramData != null && !paramData.isEmpty()) {
	    	conn.setRequestProperty("Content-Type", "application/json");
	    	if(type.equals("chkPlus")){
	    		//통합인증
	    		conn.setRequestProperty("ProductID","2101979031");
	    	}else{
	    		//아이핀
	    		conn.setRequestProperty("ProductID","2101434007");
	    	}
	    	
	    	conn.setDoOutput(true);
	    	
	    	try (OutputStream os = conn.getOutputStream()){
	    		byte request_data[] = paramData.getBytes("utf-8");
	    		os.write(request_data);
	    		os.close();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}	
	    }
	    
	    
	    

	    
	    int responseCode = conn.getResponseCode();
	     
	    if (responseCode == 200) { // 200 요청성공코드
	    	BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            
            //System.out.println(sb.toString()+"=======================");
            returnData  = jsonParse(sb.toString(),key);
              
	     }
	    
	    return returnData;

	}
	
	
	/**
	 * json 파싱
	 * @param data 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String jsonParse(String data ,String key) throws Exception{
		JSONParser jParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jParser.parse(data);
        JSONObject jsonObject2 = (JSONObject) jParser.parse(jsonObject.get("dataBody").toString());
        String returnData = "";
        
        if(key != null && !key.isEmpty()) {
        	
        	if("encryp".equals(key)) {
        		returnData = "{\"token_val\":\""+jsonObject2.get("token_val").toString()+"\",\"site_code\":\""+jsonObject2.get("site_code").toString()+"\",\"token_version_id\":\""+jsonObject2.get("token_version_id").toString()+"\",\"period\":\""+jsonObject2.get("period").toString()+"\"}";
        	}else {
        		returnData = jsonObject2.get(key).toString();
        	}
        }
        
        return returnData;
        
        
        
	}
	
	@RequestMapping("/user/auth/setParamSession.do")
    public void setParamSesstion(@RequestParam(value="niceParam1",required=false ) String niceParam1, 
    									 @RequestParam(value="niceParam2",required=false ) String niceParam2,
    									 @RequestParam(value="niceParam3",required=false ) String niceParam3,
    									 @RequestParam(value="niceParam4",required=false ) String niceParam4,
    									 HttpSession session){
    	
    	session.setAttribute("niceParam1",null);
    	session.setAttribute("niceParam2",null);
    	session.setAttribute("niceParam3",null);
    	session.setAttribute("niceParam4",null);
    	
    	session.setAttribute("niceParam1",niceParam1);
    	session.setAttribute("niceParam2",niceParam2);
    	session.setAttribute("niceParam3",niceParam3);
    	session.setAttribute("niceParam4",niceParam4);
    	
    }
	

}
