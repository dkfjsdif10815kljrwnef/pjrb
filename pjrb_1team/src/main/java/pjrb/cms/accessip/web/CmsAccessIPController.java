package pjrb.cms.accessip.web;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import pjrb.cms.accessip.service.CmsAccessIPVO;
import pjrb.cms.accessip.service.CmsAccessIPService;

/**
 * 접근IP 관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.17
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.17  권대성          최초 생성
 *  
 */

@Controller
public class CmsAccessIPController {


    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
    @Resource(name = "cmsAccessIPService")
    protected CmsAccessIPService cmsAccessIPService;
  
	@RequestMapping(value = "/cms/accessip/accessIPList.do")
    public String accessIPList(ModelMap model) throws Exception {

		model.addAttribute("ipList",cmsAccessIPService.selectAccessIPList());
		
		CmsAccessIPVO ipVO = new CmsAccessIPVO();
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(ipVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(ipVO.getRecordCountPerPage());
        paginationInfo.setPageSize(ipVO.getPageSize());

        ipVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        ipVO.setLastIndex(paginationInfo.getLastRecordIndex());
        ipVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        int totCnt = cmsAccessIPService.selectAccessIPListCnt();
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "/pjrb/cms/accessip/accessIPlist";
    }
	
	@RequestMapping(value = "/cms/accessip/insertAccessIP.do")
    public String insertAccessIP(CmsAccessIPVO ipVO, ModelMap model, RedirectAttributes redirectAttrs) throws Exception {
		
		cmsAccessIPService.insertAccessIP(ipVO);
		redirectAttrs.addFlashAttribute("resultMsg","저장되었습니다");
		
    	return "redirect:/cms/accessip/accessIPList.do";
	}

	@RequestMapping(value = "/cms/accessip/updateAccessIP.do")
    public String updateAccessIP(CmsAccessIPVO ipVO, ModelMap model, RedirectAttributes redirectAttrs) throws Exception {
		
		cmsAccessIPService.updateAccessIP(ipVO);
		redirectAttrs.addFlashAttribute("resultMsg","수정되었습니다");
		
    	return "redirect:/cms/accessip/accessIPList.do";
	}
	
	
	@RequestMapping(value = "/cms/accessip/deleteAccessIP.do")
    public String deleteAccessIP(CmsAccessIPVO ipVO, ModelMap model, RedirectAttributes redirectAttrs) throws Exception {
		
		
		cmsAccessIPService.deleteAccessIP(ipVO);
		redirectAttrs.addFlashAttribute("resultMsg","삭제되었습니다");
    	return "redirect:/cms/accessip/accessIPList.do";
	}
}
