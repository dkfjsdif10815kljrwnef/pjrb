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

<body class="loading" data-leftbar-theme="dark" data-editor="ClassicEditor" data-collaboration="false">
    <!-- Begin page -->
    <div class="wrapper">
        <!-- ============================================================== -->
        <!-- Start Page Content here -->
        <!-- ============================================================== -->

