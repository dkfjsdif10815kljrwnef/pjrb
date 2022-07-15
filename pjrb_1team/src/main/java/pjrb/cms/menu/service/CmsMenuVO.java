package pjrb.cms.menu.service;

import java.util.List;

import pjrb.user.service.PjrbDefaultVO;

/**
 * 메뉴관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.16
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.16  권대성          최초 생성 
 *  
 */

public class CmsMenuVO extends PjrbDefaultVO {
	
	/** 부모메뉴코드 */
	private String parentMenuId;
	
	/** 메뉴코드 */
	private String menuId;

	/** 메뉴구분 */
	private String menuType;
	
	/** 메뉴명 */
	private String menuTitle;
	
	private String menuTitleEng;
	
	/** 메뉴상세설명 */
	private String menuDesc;
	
	private String menuDescEng;
	
	/** 메뉴url */
	private String menuUrl;
	
	private String menuUrlEng;
	
	/** 메뉴뎁스 */
	private String menuDepth;
	
	/** 메뉴순번 */
	private String menuNum;
	
	/** 설문 */
	private String survey;

	/** 담당자 */
	private String emplyrId;
	
	/** 새창열림 여부 */
	private String isBlank;
	
	/** 마우스 우클릭 여부 */
	private String isRmouse;

	/** 사용여부 */
	private String menuUse;

	private String engUse;
	
	/** 메뉴 네비 */
	private String menuNav;
	
	private String menuIdPath;
	
	private List<CmsMenuVO> CmsMenuVOList;
	
	
	public String getIsBlank() {
		return isBlank;
	}

	public void setIsBlank(String isBlank) {
		this.isBlank = isBlank;
	}

	public String getIsRmouse() {
		return isRmouse;
	}

	public void setIsRmouse(String isRmouse) {
		this.isRmouse = isRmouse;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public String getMenuTitleEng() {
		return menuTitleEng;
	}

	public void setMenuTitleEng(String menuTitleEng) {
		this.menuTitleEng = menuTitleEng;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getMenuDescEng() {
		return menuDescEng;
	}

	public void setMenuDescEng(String menuDescEng) {
		this.menuDescEng = menuDescEng;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuUrlEng() {
		return menuUrlEng;
	}

	public void setMenuUrlEng(String menuUrlEng) {
		this.menuUrlEng = menuUrlEng;
	}

	public String getMenuDepth() {
		return menuDepth;
	}

	public void setMenuDepth(String menuDepth) {
		this.menuDepth = menuDepth;
	}

	public String getMenuNum() {
		return menuNum;
	}

	public void setMenuNum(String menuNum) {
		this.menuNum = menuNum;
	}

	public String getSurvey() {
		return survey;
	}

	public void setSurvey(String survey) {
		this.survey = survey;
	}

	public String getEmplyrId() {
		return emplyrId;
	}

	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	public String getMenuUse() {
		return menuUse;
	}

	public void setMenuUse(String menuUse) {
		this.menuUse = menuUse;
	}

	public String getEngUse() {
		return engUse;
	}

	public void setEngUse(String engUse) {
		this.engUse = engUse;
	}

	public String getMenuNav() {
		return menuNav;
	}

	public void setMenuNav(String menuNav) {
		this.menuNav = menuNav;
	}

	public String getMenuIdPath() {
		return menuIdPath;
	}

	public void setMenuIdPath(String menuIdPath) {
		this.menuIdPath = menuIdPath;
	}

	public List<CmsMenuVO> getCmsMenuVOList() {
		return CmsMenuVOList;
	}

	public void setCmsMenuVOList(List<CmsMenuVO> cmsMenuVOList) {
		CmsMenuVOList = cmsMenuVOList;
	}
	
	
	
}
