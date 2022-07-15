package pjrb.cms.etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pjrb.user.sns.service.SnsService;
import pjrb.user.sns.service.SnsVO;


@Component
public class SnsScheduler {
	
	@Resource(name = "snsService")
    protected SnsService snsService;
	
	static Logger logger = LogManager.getLogger();
	
	// * 을 입력할경우 모두(항상)으로 설정함.
	// 초 분 시 일 월 요일
	@Scheduled(cron = "0 0 15 * * *") // 매일 오후 3시 정각 인스타그램 토큰 갱신
	public void autoUpdate() throws Exception {
		String instagramKey = "";
		String instagramUrl = "https://graph.instagram.com/refresh_access_token?grant_type=ig_refresh_token&access_token="+instagramKey;
		
		URL getUrl = new URL(instagramUrl);
        HttpURLConnection con = (HttpURLConnection) getUrl.openConnection();
        
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        
        if(responseCode == 200) {
        	Charset charset = Charset.forName("UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            // 24시간 이상 지났지만 만료되지 않은 토큰
			// 24시간 86400
			// 초기화 5184000
			// 24시간 지난 토큰 남은 초 5097600
            //System.out.println("인스타그램 통신결과 : " + response);
        }else {
        	logger.error("인스타그램 통신 실패");
        }
        
	}
	
	// * 을 입력할경우 모두(항상)으로 설정함.
	// 초 분 시 일 월 요일
	@Scheduled(cron = "0 0 0/1 * * *") // 1시간마다 인스타업데이트
	public void autoUpdate2() throws Exception {
		
		String instagramKey = "";
		String url = "https://graph.instagram.com/me/media?fields=caption,media_url,permalink&limit=4&access_token="+instagramKey;
		
		URL getUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) getUrl.openConnection();
        
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        
        String info = null;
        
        if(responseCode == 200) {
        	Charset charset = Charset.forName("UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            info = response.toString();
            
        }
        
        if(info == null) {
        	logger.error("인스타그램 통신 실패");
        }else {
        	
        	JSONObject jsonObject = new JSONObject(info);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
        	
            SnsVO snsVO = new SnsVO();
            snsVO.setSnsType("I");
            
            snsService.snsDelete(snsVO);
            
            for(int i=0; i<jsonArray.length(); i++){
            	jsonObject = jsonArray.getJSONObject(i);
            	
            	String title = null;
            	String img = null;
            	String link = null;
            	
            	try {
            		title = jsonObject.getString("caption");
    			} catch (JSONException e) {
    				logger.error("JSONException");
    			} catch (Exception e) {
    				logger.error("Exception");
    			}
            	
            	try {
            		img = jsonObject.getString("media_url");
    			} catch (JSONException e) {
    				 logger.error("JSONException"); 
    			} catch (Exception e) {
    				logger.error("Exception");
    			}
            	
            	try {
            		link = jsonObject.getString("permalink");
    			} catch (JSONException e) {
    				 logger.error("JSONException"); 
    			} catch (Exception e) {
    				logger.error("Exception");
    			}
            	
            	if(img == null) {
            		img = "/images/user/list_img01.png";
            	}
            	
            	snsVO.setSnsTitle(title);
            	snsVO.setSnsImg(img);
            	snsVO.setSnsLink(link);
            	
            	snsService.snsInsert(snsVO);
        	
            }
        
        }
	
	}
	
}