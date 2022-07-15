package pjrb.user.member.web;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pjrb.cms.member.service.CmsMemberVO;
import pjrb.user.web.AuthController;

@Controller
public class UserMemberController
{
	
    @RequestMapping("/user/member/join.do")
	public String join(@ModelAttribute("searchVO") CmsMemberVO vo, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {

    	Map<String,Object> tokenMap = AuthController.getEncryptionNiceToken(session);
    	model.addAttribute("tokenMap" ,tokenMap);
    	
		return "pjrb/user/member/join";
	}
	
    @RequestMapping("/user/member/joinForm.do")
	public String joinForm(@ModelAttribute("searchVO") CmsMemberVO vo, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
    	
    	Map<String,String> niceData =  (Map) session.getAttribute("niceResultMap");
    	
    	if(niceData == null) {
    		return "redirect:user/member/joinForm.do";
    	}else{
        	model.addAllAttributes(niceData);
    	}
    	
		return "pjrb/user/member/joinForm";
	}
	
    // 나이스 아이핀 관련 콜백
    @RequestMapping(value="/nice/NiceIpinProcess.do")
    public String goMemberIpinProcess(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		// 아이핀 검증을 수행한다.  검증이 완료되면 나이스에서 받은 성명 정보등을 사용할 수 있다.
//		AuthController.checkNiceIpinData(request, session, model);
    	
//    	Map<String,String> niceData = (Map<String, String>) session.getAttribute("niceDataChk");
    	Map<String,String> niceData = new HashMap<>();
    	niceData = (Map<String, String>) session.getAttribute("niceDataIpin");

    	if(niceData != null) {
	    	
	    	String encData = request.getParameter("enc_data");
			// 복호화
			SecretKey secureKey = new SecretKeySpec(niceData.get("key").getBytes(), "AES");
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(niceData.get("iv").getBytes()));
			
			byte[] cipherEnc = Base64.decodeBase64(encData);
			String resData =   new String(c.doFinal(cipherEnc), "euc-kr");
			
			JSONParser jParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jParser.parse(resData);
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("resultcode", jsonObject.get("resultcode").toString());		//결과코드
			map.put("requestno", jsonObject.get("requestno").toString()); 		//요청 고유 번호 (회원사에서 전달보낸 값)
			map.put("enctime", jsonObject.get("enctime").toString());     		//암호화일시(YYYYMMDDHH24MISS)
			map.put("sitecode", jsonObject.get("sitecode").toString());    	 	//사이트코드
			map.put("name", jsonObject.get("name").toString()); 				//이름
			map.put("utf8_name", jsonObject.get("utf8_name").toString()); 		//UTF8로 URLEncoding된 이름 값			
			map.put("birthdate", jsonObject.get("birthdate").toString()); 		//생년월일 8자리
			map.put("gendercode", jsonObject.get("gendercode").toString()); 	//성별
			map.put("dupinfo", jsonObject.get("dupinfo").toString()); 			//개인 식별 코드(DI)						
			map.put("authmethod", jsonObject.get("authmethod").toString()); 	//아이핀가입시 본인인증 수단			
			map.put("ipaddr", jsonObject.get("ipaddr").toString()); 			//인증서버 아이피			
			map.put("receivedata", jsonObject.get("receivedata").toString()); 	//요청 시 전달 받은 RECEIVEDATA		
	
			session.setAttribute("niceResultMap", map);
			
			System.out.println("=======================NiceIpinProcess");
			System.out.println(resData);
		
    	}else {
    		return "redirect:/user/member/join.do";
    	}
    	
    	return "/pjrb/user/auth/authSuccess";
	}
    
    // 나이스 통합인증 관련 콜백
    @RequestMapping(value="/nice/NiceCheckPlusProcess.do")
    public String goMemberChkPlusProcess(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
    	// 아이핀 검증을 수행한다.  검증이 완료되면 나이스에서 받은 성명 정보등을 사용할 수 있다.
//		AuthController.checkNiceIpinData(request, session, model);
    	
//    	Map<String,String> niceData = (Map<String, String>) session.getAttribute("niceDataChk");
    	Map<String,String> niceData = new HashMap<>();
    	niceData = (Map<String, String>) session.getAttribute("niceDataChk");
    	
    	if(niceData != null) {
    		
    		String encData = request.getParameter("enc_data");
    		// 복호화
    		SecretKey secureKey = new SecretKeySpec(niceData.get("key").getBytes(), "AES");
    		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
    		c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(niceData.get("iv").getBytes()));
    		
    		byte[] cipherEnc = Base64.decodeBase64(encData);
    		String resData =   new String(c.doFinal(cipherEnc), "euc-kr");
    		
    		JSONParser jParser = new JSONParser();
    		JSONObject jsonObject = (JSONObject) jParser.parse(resData);
    		
			Map<String,String> map = new HashMap<String,String>();
			map.put("resultcode", jsonObject.get("resultcode").toString());		//결과코드
			map.put("requestno", jsonObject.get("requestno").toString()); 		//요청 고유 번호 (회원사에서 전달보낸 값)
			map.put("enctime", jsonObject.get("enctime").toString());     		//암호화일시(YYYYMMDDHH24MISS)
			map.put("sitecode", jsonObject.get("sitecode").toString());    	 	//사이트코드
			map.put("responseno", jsonObject.get("responseno").toString());	 	//응답고유번호
			map.put("authtype", jsonObject.get("authtype").toString()); 		//인증수단			
			map.put("name", jsonObject.get("name").toString()); 				//이름
			map.put("utf8_name", jsonObject.get("utf8_name").toString()); 		//UTF8로 URLEncoding된 이름 값			
			map.put("birthdate", jsonObject.get("birthdate").toString()); 		//생년월일 8자리
			map.put("gender", jsonObject.get("gender").toString()); 			//성별
//			map.put("mobile_co", jsonObject.get("mobile_co").toString()); 		//이통사 구분(휴대폰 인증 시)						
			map.put("mobileno", jsonObject.get("mobileno").toString()); 		//휴대폰 번호(휴대폰 인증 시)			
//			map.put("ci", jsonObject.get("ci").toString()); 					//개인 식별 코드(CI)			
			map.put("di", jsonObject.get("di").toString()); 					//개인 식별 코드(DI)						
//			map.put("businessno", jsonObject.get("businessno").toString()); 	//사업자번호(법인인증서 인증시)			
//			map.put("receivedata", jsonObject.get("receivedata").toString()); 	//요청 시 전달 받은 RECEIVEDATA		
    		
			session.setAttribute("niceResultMap", map);
    		
    		System.out.println("=======================NiceCheckPlusProcess");
    		System.out.println(resData);
    		
    	}else {
    		return "redirect:/user/member/join.do";
    	}
    	
    	return "/pjrb/user/auth/authSuccess";
    }
	
}
