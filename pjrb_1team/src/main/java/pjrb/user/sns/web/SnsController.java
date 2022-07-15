package pjrb.user.sns.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pjrb.user.sns.service.SnsService;
import pjrb.user.sns.service.SnsVO;

@Controller
public class SnsController {
	static Logger logger = LogManager.getLogger();
	
	static String youtubeKey = "AIzaSyCxgXu8WFVyiSDudRRsbDQoFO_xu6eN8l4";
	static String youtubeChannelId = "UUSJaNmCrmrzqRQHURa9PtCA";
	static String youtubeUrl = "https://www.googleapis.com/youtube/v3/playlistItems?key="+youtubeKey+"&playlistId="+youtubeChannelId+"&part=snippet&maxResults=4";
	
	static String facebookKey = "EAAEB1DX8y5ABAJQN37xcdR1ITnJ2wtJhc3fAeQgHLNtlpaMdSLub4AOtCZCWFBiTpAhIgTPuTqQZBLQWXvdqwNxbpRVAq8U7TpFvShXjP9dNfBiVhZCii1DKErk4Ji7LRVaeNUQZAJwjAITCVGISu4KcCPUmB0jkhlwjeNHcx10Qzi2sy5zF";
	static String facebookUrl = "https://graph.facebook.com/v12.0/341300896014211/posts?fields=message,permalink_url,full_picture&limit=4&access_token="+facebookKey;
	
	static String naverUrl = "https://rss.blog.naver.com/hellohaccp.xml";
	
	@Resource(name = "snsService")
    protected SnsService snsService;
	
	public static String getConnection(String url) throws Exception {
			
	        URL getUrl = new URL(url);
	        HttpURLConnection con = (HttpURLConnection) getUrl.openConnection();
	        
	        con.setRequestMethod("GET");
	        int responseCode = con.getResponseCode();
	        
	        if(responseCode != 200) {
	        	return null;
	        }else {
	        	Charset charset = Charset.forName("UTF-8");
	            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();
	            
	    		return response.toString();
	        }
	        
		}
		
		public static String getYoutube() throws Exception {
			
			String info = getConnection(youtubeUrl);
	        if(info == null) {
	        	return null;
	        }
	        
	        JSONObject jsonObject = new JSONObject(info);
	        JSONArray jsonArray = jsonObject.getJSONArray("items");
	        
	        String youtubekHtml = "";
	        int cnt = 5;
	    	
	        for(int i=0; i<jsonArray.length(); i++){
	        	jsonObject = jsonArray.getJSONObject(i);
	        	
	        	JSONObject snippet = jsonObject.getJSONObject("snippet");
	        	
	        	String youLink = null;
	        	String youMessage = null;
	        	String youImg = null;
	        	
	        	try {
	        		JSONObject resourceId = snippet.getJSONObject("resourceId");
	            	youLink = resourceId.getString("videoId");
				} catch (JSONException e) {
					logger.error("JSONException");
				} catch (Exception e) {
					logger.error("Exception");
				}
	        	
	        	try {
	        		youMessage = snippet.getString("title");
				} catch (JSONException e) {
					logger.error("JSONException");
				} catch (Exception e) {
					logger.error("Exception");
				}
	        	
	        	JSONObject thumbnails = null;
	        	
	            try {
	            	thumbnails = snippet.getJSONObject("thumbnails");
	            	JSONObject maxres = thumbnails.getJSONObject("maxres");
	            	youImg = maxres.getString("url");
				} catch (JSONException e) {
					logger.error("JSONException");
				} catch (Exception e) {
					logger.error("Exception");
				}
	            
	            if(youImg == null) {
	            	try {
	            		JSONObject high = thumbnails.getJSONObject("high");
	                	youImg = high.getString("url");
	    			} catch (JSONException e) {
	    				logger.error("JSONException");
	    			} catch (Exception e) {
	    				logger.error("Exception");
	    			}
	            }
	            
	            if(youLink == null) {
	            	youLink = "";
	        	}
	        	if(youMessage == null) {
	        		youMessage = "";
	        	}
	        	if(youImg == null) {
	        		youImg = "/images/user/list_img01.png";
	        	}
	            
	        	youtubekHtml += 
	        			"<li class=\"fromBottomIn0"+cnt+"\" data-scroll=\"toggle(.fromBottomIn0"+cnt+", .fromBottomOut0"+cnt+")\">\r\n" + 
						"<a href=\"https://www.youtube.com/watch?v="+youLink+"\" target=\"_blank\" title=\"새창 열림\">\r\n" + 
						"<div class=\"hover_con\">\r\n" + 
						"<span class=\"yout_mark\"></span>\r\n" + 
						"<p class=\"sns_con\">"+youMessage+"</p>\r\n" + 
						"</div>\r\n" + 
						"<img src=\""+youImg+"\" alt=\"\">\r\n" + 
						"</a>\r\n" + 
						"</li>";
	            cnt++;
	        }
			
			return youtubekHtml;
		}
		
		public static String getFacebook() throws Exception {
			
			String info = getConnection(facebookUrl);
	        if(info == null) {
	        	return null;
	        }
	        
	        JSONObject jsonObject = new JSONObject(info);
	        JSONArray jsonArray = jsonObject.getJSONArray("data");

	        String facebookHtml = "";
	        int cnt = 5;
	    	
	        for(int i=0; i<jsonArray.length(); i++){
	        	jsonObject = jsonArray.getJSONObject(i);
	        	
	        	String faceLink = null;
	        	String faceMessage = null;
	        	String faceImg = null;
	        	
	        	try {
	        		faceLink = jsonObject.getString("permalink_url");
				} catch (JSONException e) {
					logger.error("JSONException");
				} catch (Exception e) {
					logger.error("Exception");
				}
	        	
	        	try {
	        		faceMessage = jsonObject.getString("message");
				} catch (JSONException e) {
					 logger.error("JSONException"); 
				} catch (Exception e) {
					logger.error("Exception");
				}
	        	
	        	try {
	        		faceImg = jsonObject.getString("full_picture");
				} catch (JSONException e) {
					 logger.error("JSONException"); 
				} catch (Exception e) {
					logger.error("Exception");
				}
	        	
	        	if(faceLink == null) {
	        		faceLink = "";
	        	}
	        	if(faceMessage == null) {
	        		faceMessage = "";
	        	}
	        	if(faceImg == null) {
	        		faceImg = "/images/user/list_img01.png";
	        	}
	            
	            facebookHtml += 
	            "<li class=\"fromBottomIn0"+cnt+"\" data-scroll=\"toggle(.fromBottomIn0"+cnt+", .fromBottomOut0"+cnt+")\">\r\n" + 
				"<a href=\""+faceLink+"\" target=\"_blank\" title=\"새창 열림\">\r\n" + 
				"<div class=\"hover_con\">\r\n" + 
				"<span class=\"face_mark\"></span>\r\n" + 
				"<p class=\"sns_con\">"+faceMessage+"</p>\r\n" + 
				"</div>\r\n" + 
				"<img src=\""+faceImg+"\" alt=\"\">\r\n" + 
				"</a>\r\n" + 
				"</li>";
	            cnt++;
	        }
			
			return facebookHtml;
		}
		
		public static String getNaver() throws Exception {
			
			String naverHtml = "";
			
			try{
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			    Document doc = builder.parse(naverUrl);
			    
			    NodeList items = doc.getElementsByTagName("item");
			    
			    for (int i = 0; i < 4; i++) {
			    	Element item = (Element)items.item(i);
			    	
			    	int cnt = 5;
			        
				    String naverLink = getValue(item, "link");
		        	String naverTitle = getValue(item, "title");
		        	
		        	if(naverLink == null) {
		        		naverLink = "";
		        	}
		        	if(naverTitle == null) {
		        		naverTitle = "";
		        	}
		        	
		        	naverHtml += 
		            "<li class=\"fromBottomIn0"+cnt+"\" data-scroll=\"toggle(.fromBottomIn0"+cnt+", .fromBottomOut0"+cnt+")\">\r\n" + 
					"<a href=\""+naverLink+"\" target=\"_blank\" title=\"새창 열림\">\r\n" + 
					"<div class=\"hover_con\">\r\n" + 
					"<span class=\"blog_mark\"></span>\r\n" + 
					"<p class=\"sns_con\">"+naverTitle+"</p>\r\n" + 
					"</div>\r\n" + 
//					"<img src=\"/images/user/sns_blog_img"+(i+1)+".png\" alt=\"\">"+
					"</a>\r\n" + 
					"</li>";
		            cnt++;
				}
		        
	            
		    }catch(Exception ex){
		        throw ex;
		    }
			
			return naverHtml;
		}
		
		/*
		@RequestMapping("/user/snsApi.do")
		public ModelAndView snsApi(HttpServletRequest request, Model model) throws Exception{
			
			String instagramKey = "IGQVJXMHRMSVRNcS02MjdyNVdQSTN6Q0JxalpQS1lzbVJhelY3SENxbkhIcUVSbWhqT1VFV0dQUEFRLVBlQjFXZAmNldmVvX3owMHBhdm1EVjlyYWszNF9MNU1tN3RRZA2c1ZA25DTVBuemFDeFhuakt4TQZDZD";
			
			model.addAttribute("instagramKey", instagramKey);
			
			return new ModelAndView("ajaxMainView");
		}*/
		
		@RequestMapping(value = "/sns/getToken.do")
		public String getToken(HttpServletRequest request) throws Exception {
			//String instagramKey = "IGQVJXX3ZAFWjBlRDBkOXdRZA2VRSl9rZATdRRGx4cUhfZAWQ5S2pubmlfc0J3NGVhN3FQYXdIdEJsVDhQRlk5RkxpQndjdW0xdElMOFozRGQ1c3FOSTlrRHJtWW1PTmFaYmZA2MURSSUJuTTJDQ1ljR3JHbQZDZD";
			//String instagramUrl = "https://graph.instagram.com/refresh_access_token?grant_type=ig_refresh_token&access_token="+instagramKey;
			SnsVO vo = new SnsVO();
			vo.setSnsType("I");
			EgovMap tokenMap = snsService.snsTokenSelect(vo);//처음 발급한 토큰을 DB에 저장
			String instagramUrl = "https://graph.instagram.com/refresh_access_token?grant_type=ig_refresh_token&access_token="+tokenMap.get("token");
			
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
	            System.out.println("인스타그램 통신결과 : " + response);
	            String info = response.toString();
	        	JSONObject jsonObject = new JSONObject(info);
	        	vo = new SnsVO();
	        	vo.setToken(jsonObject.getString("access_token"));
	        	vo.setSnsType("I");
	        	snsService.snsTokenInsert(vo);
	        	
	        }else {
	        	logger.error("인스타그램 통신 실패");
	        }
			
			  return "pjrb/user/main";
		}
		
		
		@RequestMapping("/sns/getInstagram.do")
		public String getInstagram(HttpServletRequest request) throws Exception{
//			String instagramKey = "IGQVJXMHRMSVRNcS02MjdyNVdQSTN6Q0JxalpQS1lzbVJhelY3SENxbkhIcUVSbWhqT1VFV0dQUEFRLVBlQjFXZAmNldmVvX3owMHBhdm1EVjlyYWszNF9MNU1tN3RRZA2c1ZA25DTVBuemFDeFhuakt4TQZDZD";
			SnsVO snsVO = new SnsVO();
			snsVO.setSnsType("I");
			EgovMap tokenMap = snsService.snsTokenSelect(snsVO);//limit=4 갯수 제한
			String url = "https://graph.instagram.com/me/media?fields=caption,media_url,permalink&limit=4&access_token="+tokenMap.get("token");
			
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
	        	
	            snsVO = new SnsVO();
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
	        return "pjrb/user/main";
		}
		
		public static String getValue(Element parent, String nodeName) {
			return parent.getElementsByTagName(nodeName).item(0).getFirstChild().getNodeValue();
		}
}
