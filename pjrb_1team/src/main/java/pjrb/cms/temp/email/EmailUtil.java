package pjrb.cms.temp.email;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import pjrb.cms.temp.email.service.EmailVo;

@Repository("emailUtil")
public class EmailUtil {
	final String user = "kimjuryeon0116@gmail.com";
	final String password = "fvxmbmigbafawlrx"; //https://ivvve.github.io/2019/02/09/java/Spring/mail_AuthenticationFailedException/
	
	private Session getSetting() {
		///발신자의 계정 정보 설정
		/// Property에 SMTP 서버정보 설정
		Properties prop = new Properties(); //Windows의 INI파일과 같은 기능을하는 class. 
		prop.put("mail.smtp.host", "smtp.gmail.com"); // 이메일 발송을 처리해줄 STMP서버 
		prop.put("mail.smtp.port", 465); // SMTP와 통신하는 포트 gmail 465, name 587 >다를경우 확인
		prop.put("mail.smtp.auth", "true"); 
		prop.put("mail.smtp.ssl.enable", "true"); 
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		/// Session 생성
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
		return session;
	}
	
	public String getNullsafe(Object object) {
		return object == null ? "" : object.toString();
	}
	
	public Map sendMail(EmailVo emailVo) {
		  Map<String, String> errorCode = new HashMap<>();
		  String error = "notError"; /// 00 = 정상
		  
		  MimeMessage message = new MimeMessage(getSetting());
		  try {
		  /// 발신자 설정
          /// 이메일로 표기됨 > message.setFrom(new InternetAddress(user));
		  /// 발신자 이름을 변경할 때 new InternetAddress("이메일주소","출력명","UTF-8)를 사용한다 
          message.setFrom(new InternetAddress(user,"프로젝트레인보우_김주련","UTF-8"));
          
//          InternetAddress[] toAddr = new InternetAddress[emailVo.getEmailLength()];
//          toAddr[0] = new InternetAddress ("jhk149@hanmail.net");  // 현경
          message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailVo.getEmail()));///수신자 메일 설정 

          // Subject
          message.setSubject(emailVo.getSubject()); //메일 제목을 입력

          
          // Text
          message.setText(emailVo.getText());    //메일 내용을 입력
		  
          // send the message
          Transport.send(message); ////전송
		  } catch (AddressException e) {
			  error = "AddressException";
			  e.printStackTrace();
		  } catch (MessagingException e) {
			  error = "MessagingException";
			  e.printStackTrace();
	      } catch (Exception e) {			  
	    	  error = "Exception";
	    	  e.printStackTrace();
		}
		  
		  
	return errorCode ;
	}

}
