<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="now" value="<%=new java.util.Date()%>" scope="application" />
<html lang="ko">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>DEFAULT CMS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/all.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/webfonts.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/common.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/light.min.css" id="light-style" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/dark.min.css" id="dark-style" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/layout.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/board.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cms/ckeditor.css" />
    
    <%-- <script src="${pageContext.request.contextPath}/js/cms/jquery.min.js"></script> --%>
    <script src="${pageContext.request.contextPath}/js/cms/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/dev_cmm.js"></script>
    
    <!-- Right-bar -->
	<script src="${pageContext.request.contextPath}/js/cms/vendor.min.js"></script>
	<%-- <script src="${pageContext.request.contextPath}/js/cms/ckeditor.js"></script> --%>
    
    <script>
    	function fn_logout(){
    		location.href = '<c:url value="/cms/actionLogout.do"/>';
    	}
    	
    	function fn_mainBbsList(bbsId){
    		document.bbsFrm.pageIndex.value = 1;
    		document.bbsFrm.bbsId.value = bbsId;
    		
    		document.bbsFrm.action = '<c:url value="/cms/bbs/bbsList.do"/>';
    		document.bbsFrm.submit();
    	}
    	/*
    	function fn_excel(flag, type){
    		document.excelForm.excelFlag.value = flag;
    		document.excelForm.excelType.value = type;
    		
    		document.excelForm.action="<c:url value='/cms/"+flag+"/excelDown.do'/>";
    		document.excelForm.submit();
    	}
    	*/
    	function fn_excel(flag, type){
    		document.excelForm.excelFlag.value = flag;
    		document.excelForm.excelType.value = type;
    		
    		if(flag=='member' || flag=='banner' || flag=='popup' || flag=='bbsmng'){
    			excelForm.searchCnd.value = frm.searchCnd.value;
    			excelForm.searchWrd.value = frm.searchWrd.value;
    		}
    		
    		if(flag=='banner'){
    			excelForm.searchStatus.value = frm.searchStatus.value;
    			if(type=='2'){
	    			excelForm.searchEngYn.value = frm.searchEngYn.value;
    			}
    		}
    		
    		if(flag=='popup'){
    			excelForm.searchStatus.value = frm.searchStatus.value;
    		}
    		
    		if(flag=='stats'){
    			if(type=='1'){
    				excelForm.searchCnd.value = listForm.searchCnd.value;
    			}
    			excelForm.searchEngYn.value = listForm.engYn.value;
    			excelForm.searchDate.value = listForm.searchDate.value;
    			excelForm.searchStartDate.value = listForm.startDate.value;
				excelForm.searchEndDate.value = listForm.endDate.value;
    		}
    		
    		if(flag=='pageMng'){
    			excelForm.searchCnd.value = searchForm.searchCnd.value;
    			excelForm.searchWrd.value = searchForm.searchWrd.value;
    		}
    		
    		if(flag=='bbs' || flag=='bbsCon'){
    			document.frm.action="<c:url value='/cms/"+flag+"/excelDown.do'/>";
	    		document.frm.submit();
    		}else{
	    		document.excelForm.action="<c:url value='/cms/"+flag+"/excelDown.do'/>";
	    		document.excelForm.submit();
    		}
    	}
    	
    	function fn_adminMemberrModify(emplyrId) {
    		document.adminMemberFrm.emplyrId.value = emplyrId;

    		document.adminMemberFrm.action = '<c:url value="/cms/member/memberModify.do"/>';
    		document.adminMemberFrm.submit();
    	}
    	

    	function fn_egov_downFile(atchFileId, fileSn) {
    		window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId=" + atchFileId + "&fileSn=" + fileSn + "'/>");
    	}

    </script>
</head>
<form method="post" id="excelForm" name="excelForm">
	<input type="hidden" name="excelFlag" value=""/>
	<input type="hidden" name="excelType" value=""/>
	<input type="hidden" name="searchCnd" value=""/>
	<input type="hidden" name="searchWrd" value=""/>
	<input type="hidden" name="searchStatus" value=""/>
	<input type="hidden" name="searchEngYn" value=""/>
	<input type="hidden" name="searchDate" value=""/>
	<input type="hidden" name="searchStartDate" value=""/>
	<input type="hidden" name="searchEndDate" value=""/>
</form>
<form method="post" id="bbsFrm" name="bbsFrm">
	<input type="hidden" name="pageIndex" value="1"/>
	<input type="hidden" name="bbsId" value=""/>
</form>
<form method="post" id="adminMemberFrm" name="adminMemberFrm">
	<input type="hidden" name="emplyrId" value=""/>
	<input type="hidden" name="type" value="admin"/>
</form>

<c:set var="menuUrl" value="${requestScope['javax.servlet.forward.request_uri']}" />

<body class="loading" data-layout-config='{"leftSideBarTheme":"default","layoutBoxed":false, "leftSidebarCondensed":false, "leftSidebarScrollable":false,"darkMode":false, "showRightSidebarOnStart": false}' data-leftbar-theme="dark" data-editor="ClassicEditor" data-collaboration="false">
    <!-- Begin page -->
    <div class="wrapper">
        <!-- ========== Left Sidebar Start ========== -->
        <div class="left-side-menu">
            <!-- LOGO -->
            <a href="<c:url value='/cms/main.do'/>" class="logo text-center logo-light">
                <span class="logo-lg">
               		DEFAULT CMS
                </span>
                <span class="logo-sm">
                    DEFAULT CMS
                </span>
            </a>
            <!-- LOGO -->
            <a href="<c:url value='/cms/main.do'/>" class="logo text-center logo-dark">
                <span class="logo-lg">
                 	DEFAULT CMS
                </span>
                <span class="logo-sm">
                    DEFAULT CMS
                </span>
            </a>
            <div class="h-100" id="left-side-menu-container" data-simplebar>
                <!--- Sidemenu -->
                <ul class="metismenu side-nav">
                    <%-- <li class="side-nav-item">
                        <a href="<c:url value='/cms/main.do'/>" class="side-nav-link">
                            <i class="far fa-home-alt"></i>
                            <span> 대시보드 </span>
                        </a>
                    </li> --%>
                    <c:if test="${loginVO.auth.indexOf('/cms/stats/') > -1 }">
	                    <li class="side-nav-item">
	                        <a href="javascript: void(0);" class="side-nav-link">
	                            <i class="far fa-chart-line"></i>
	                            <span> 통계 </span>
	                            <span class="menu-arrow"></span>
	                        </a>
	                        <ul class="side-nav-second-level" aria-expanded="false">
	                            <li <c:if test="${fn:indexOf(menuUrl,'/cms/stats/conStatList.do') != -1 }">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/stats/conStatList.do'/>?engYn=N">접속 통계</a>
	                            </li>
	                            <li <c:if test="${fn:indexOf(menuUrl,'/cms/stats/pageStatList.do') != -1 }">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/stats/pageStatList.do'/>?engYn=N">페이지 통계</a>
	                            </li>
	                        </ul>
	                    </li>
	                </c:if>
                    <hr>
	                <c:if test="${loginVO.auth.indexOf('/cms/member/') > -1 }">
	                    <li class="side-nav-item <c:if test="${fn:indexOf(menuUrl,'/cms/member/memberList.do') != -1 || fn:indexOf(menuUrl,'/cms/member/memberWrite.do') != -1 || fn:indexOf(menuUrl,'/cms/member/memberModify.do') != -1}">mm-active</c:if>">
	                        <a href="<c:url value='/cms/member/memberList.do'/>" class="side-nav-link">
	                            <i class="fas fa-user-circle"></i>
	                            <span> 회원 관리 </span>
	                        </a>
	                    </li>
	                </c:if>
	                <c:if test="${loginVO.auth.indexOf('/cms/popup/,/cms/banner/,/cms/pageMng/,/cms/bbsmng/') > -1 }">
	                    <li class="side-nav-item">
	                        <a href="javascript: void(0);" class="side-nav-link">
	                            <i class="far fa-browser"></i>
	                            <span> 사이트 </span>
	                            <span class="menu-arrow"></span>
	                        </a>
	                        <ul class="side-nav-second-level" aria-expanded="false">
	                            <li <c:if test="${fn:indexOf(menuUrl,'/cms/popup/popupList.do') != -1 || fn:indexOf(menuUrl,'/cms/popup/popupModify.do') != -1}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/popup/popupList.do'/>">팝업 관리</a>
	                            </li>
	                            <li <c:if test="${(fn:indexOf(menuUrl,'/cms/banner/bannerList.do') != -1 || fn:indexOf(menuUrl,'/cms/banner/bannerWrite.do') != -1 || fn:indexOf(menuUrl,'/cms/banner/bannerModify.do') != -1) && param.purpose == '1'}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/banner/bannerList.do'/>?purpose=1">배너 관리</a>
	                            </li>
	                            <li <c:if test="${(fn:indexOf(menuUrl,'/cms/banner/bannerList.do') != -1 || fn:indexOf(menuUrl,'/cms/banner/bannerWrite.do') != -1 || fn:indexOf(menuUrl,'/cms/banner/bannerModify.do') != -1) && param.purpose == '2'}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/banner/bannerList.do'/>?purpose=2">관련사이트 관리</a>
	                            </li>
	                            <li <c:if test="${fn:indexOf(menuUrl,'/cms/pageMng/pageMngList.do') > -1 || fn:indexOf(menuUrl,'/cms/pageMng/pageMngForm.do') != -1}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/pageMng/pageMngList.do'/>">페이지 관리</a>
	                            </li>
	                            <li <c:if test="${fn:indexOf(menuUrl,'/cms/bbsmng/mngList.do') != -1 || fn:indexOf(menuUrl,'/cms/bbsmng/mngModify.do') != -1 || fn:indexOf(menuUrl,'/cms/bbsmng/mngWrite.do') != -1}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/bbsmng/mngList.do'/>">게시판 관리</a>
	                            </li>
	                        </ul>
	                    </li>
	                </c:if>
	                <c:if test="${loginVO.auth.indexOf('/cms/bbs/') > -1 }">
		                    <li class="side-nav-item">
		                        <a href="javascript: void(0);" class="side-nav-link">
		                            <i class="far fa-browser"></i>
		                            <span>게시물 관리</span>
		                            <span class="menu-arrow"></span>
		                        </a>
		                        <ul class="side-nav-second-level" aria-expanded="false">
		                        	<c:forEach var="board" items="${boardList}" varStatus="status">
			                            <li <c:if test="${(fn:indexOf(menuUrl,'/cms/bbs/bbsList.do') != -1 || fn:indexOf(menuUrl,'/cms/bbs/bbsModify.do') != -1 || fn:indexOf(menuUrl,'/cms/bbs/bbsWrite.do') != -1) && param.bbsId == board.bbsId}">class="mm-active"</c:if>>
			                                <a href="javascript:void(0);" onclick="javascript:fn_mainBbsList('<c:out value="${board.bbsId }"/>')"><c:out value="${board.bbsNm }"/></a>
			                            </li>
			                    	</c:forEach>
		                        </ul>
		                    </li>
		            </c:if>
	                
	                <c:if test="${loginVO.auth.indexOf('/cms/grade/,/cms/accessip/,/cms/accesslog/,/cms/menu/') > -1 }">
	                    <li class="side-nav-item">
	                        <a href="javascript: void(0);" class="side-nav-link">
	                            <i class="far fa-user-cog"></i>
	                            <span> 관리자 </span>
	                            <span class="menu-arrow"></span>
	                        </a>
	                        <ul class="side-nav-second-level" aria-expanded="false">
	                            <li <c:if test="${fn:indexOf(menuUrl,'/cms/grade/gradeList.do') != -1}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/grade/gradeList.do'/>?grade=1">권한 관리</a>
	                            </li>
	                            <li <c:if test="${fn:indexOf(menuUrl,'/cms/accessip/accessIPList.do') != -1}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/accessip/accessIPList.do'/>">접근 IP관리</a>
	                            </li>
	                            <li <c:if test="${fn:indexOf(menuUrl,'/cms/accesslog/accessLogList.do') != -1}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/accesslog/accessLogList.do'/>?status=Y">접속 로그</a>
	                            </li>
	                            <li <c:if test="${fn:indexOf(menuUrl,'/cms/accesslog/accessActLogList.do') != -1}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/accesslog/accessActLogList.do'/>">접근 로그</a>
	                            </li>
	                            <li <c:if test="${fn:indexOf(menuUrl,'/cms/menu/menuinfo.do') != -1}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/menu/menuinfo.do'/>?menuType=1">메뉴 관리</a>
	                            </li>
	                        </ul>
	                    </li>
	                </c:if>
	                <c:if test="${loginVO.sessionGrade eq '0' }">
		                <li class="side-nav-item">
	                        <a href="javascript: void(0);" class="side-nav-link">
	                            <i class="far fa-user-cog"></i>
	                            <span> pjrb </span>
	                            <span class="menu-arrow"></span>
	                        </a>
	                        <ul class="side-nav-second-level" aria-expanded="false">
	                            <li <c:if test="${fn:indexOf(menuUrl,'/cms/errorlog/errorList.do') != -1 || fn:indexOf(menuUrl,'/cms/errorlog/errorDetail.do') != -1}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/errorlog/errorList.do'/>">에러로그</a>
	                            </li>
	                            <li <c:if test="${fn:indexOf(menuUrl,'/cms/grade/gradeInfo.do') != -1}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/grade/gradeInfo.do'/>">권한설정</a>
	                            </li>
	                            <li <c:if test="${fn:indexOf(menuUrl,'/cms/fileMng/fileMngList.do') != -1}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/fileMng/fileMngList.do'/>">파일관리</a>
	                            </li>
                             	<li <c:if test="${fn:indexOf(menuUrl,'/cms/mainBbs/mainBbsList.do') != -1}">class="mm-active"</c:if>>
	                                <a href="<c:url value='/cms/mainBbs/mainBbsList.do'/>">메인 게시판 관리</a>
	                            </li>
	                        </ul>
	                    </li>
                    </c:if>
                </ul>
                <!-- End Sidebar -->
                <div class="clearfix"></div>
            </div>
            <!-- Sidebar -left -->
        </div>
        <!-- Left Sidebar End -->
        <!-- ============================================================== -->
        <!-- Start Page Content here -->
        <!-- ============================================================== -->
        <div class="content-page">
            <div class="content">
                <!-- Topbar Start -->
                <div class="navbar-custom">
                    <ul class="list-unstyled topbar-right-menu float-right mb-0">
                   	    <li class="notification-list">
                            <a href="<c:url value='/main.do'/>" target="_blank" title="사용자 사이트">
                                <i class="fal fa-home-lg-alt"></i>
                            </a>
                        </li>
                        <!-- <li class="notification-list">
                            <a class="nav-link right-bar-toggle" href="javascript: void(0);">
                                <i class="fad fa-fill-drip"></i>
                            </a>
                        </li> -->
                        <li class="dropdown notification-list">
                            <a class="nav-link dropdown-toggle nav-user arrow-none mr-0" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                                <span class="account-user-avatar">
                                    <i class="fad fa-user-circle"></i>
                                </span>
                                <span class="admin-info">
                                    <span class="account-position">${loginVO.sessionGradeNm }</span>
                                    <span class="account-user-name">${loginVO.userNm }</span>
                                </span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right dropdown-menu-animated topbar-dropdown-menu profile-dropdown">
                                <a href="javascript:void(0);" onclick="fn_adminMemberrModify('${loginVO.emplyrId}');" class="dropdown-item notify-item">
                                    <i class="fad fa-user-circle"></i>
                                    <span>정보수정</span>
                                </a>
                                <!-- <a href="javascript: void(0);" class="dropdown-item notify-item right-bar-toggle">
                                    <i class="far fa-eye-dropper"></i>
                                    <span>테마설정</span>
                                </a> -->
                                <a href="javascript:void(0);" class="dropdown-item notify-item" onclick="javascript:fn_logout();">
                                    <i class="far fa-sign-out"></i>
                                    <span>로그아웃</span>
                                </a>
                            </div>
                        </li>
                    </ul>
                    <button class="button-menu-mobile open-left disable-btn">
                        <i class="fal fa-bars"></i>
                    </button>
                </div>
                <!-- end Topbar -->