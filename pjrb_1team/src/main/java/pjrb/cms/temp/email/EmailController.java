package pjrb.cms.temp.email;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.popup.service.CmsPopupVO;
import pjrb.cms.temp.email.service.AddrService;
import pjrb.cms.temp.email.service.AddrVo;
import pjrb.cms.temp.email.service.EmailVo;
import pjrb.cms.temp.excel.web.TempExcelUtil;

@Controller
public class EmailController {
	
	@Resource(name = "ajaxMainView")
	private MappingJackson2JsonView ajaxMainView;

    @Resource(name = "AddrService")
    AddrService service;
    
    @Resource(name = "emailUtil")
    EmailUtil emailUtil;
    
	@RequestMapping("/temp/email/list.do")
	public String tempList() {
		return "/pjrb/temp/email/emailList";
	}

	@RequestMapping("/temp/email/addressList.do")
	public String tempaddrList(@ModelAttribute("searchVO") AddrVo vo, ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		paginationInfo.setPageSize(vo.getPageSize());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		/// 게시판 total cnt
		paginationInfo.setTotalRecordCount( service.addrListCnt(vo) );
		
		/// addAttribute
		model.addAttribute("resultList", service.addrList(vo) );
		model.addAttribute("resultCnt", paginationInfo.getTotalRecordCount() );
		model.addAttribute("paginationInfo", paginationInfo);
		
		
		return "/pjrb/temp/email/addressList";
	}

	@RequestMapping("/temp/email/addrWrite.do")
	public String addrWrite(@ModelAttribute("searchVO") AddrVo vo, ModelMap model) {
		
		/// addAttribute
		model.addAttribute("result", vo );
		
		return "/pjrb/temp/email/addressWrite";
	}
	
	
	@RequestMapping("/temp/email/send.do")
	public String tempAddrSend(@ModelAttribute("emailData")EmailVo emailData, ModelMap model) {
		String[] emailArr = emailData.getEmailArr();
		for (String email : emailArr) {
			emailData.setEmail(email);
			Map<String, String> result = emailUtil.sendMail(emailData);
			if (result.get("errorCode") != "notError") {
				///error
			}
		}
		
		
		return "/pjrb/temp/email/emailList";
	}

	
	@RequestMapping("/temp/email/delete.do")
	public ModelAndView tempAddrChkDel(AddrVo vo, MultipartHttpServletRequest multiRequest, HttpServletRequest request, ModelMap model) throws Exception {
		// 본인체크 
		// LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		String[] seqList = (vo.getSeqList()).split(",");
		
		for(int i=0; i<seqList.length; i++){
			AddrVo delVo = new AddrVo();
			delVo.setSeq(seqList[i]);
			
			service.addrDelete(delVo);
		}
		
		
		return new ModelAndView(ajaxMainView);
	}
}
