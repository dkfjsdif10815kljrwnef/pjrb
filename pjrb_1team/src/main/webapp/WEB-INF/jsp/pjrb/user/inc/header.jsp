<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="now" value="<%=new java.util.Date()%>" scope="application" />
<c:set var="reqUrl" value="<%= request.getRequestURL() %>"/>
<!doctype html>
<html lang="ko">
<script src="${pageContext.request.contextPath}/js/dev_cmm.js"></script>
<script>
	
	function fn_bbsDetail(bbsId,nttId){
		document.bbsForm.bbsId.value=bbsId;
		document.bbsForm.nttId.value=nttId;
		document.bbsForm.action="<c:url value='/user/boardDetail.do'/>";
		document.bbsForm.submit();
	}
	function fn_bbs(bbsId){
		document.bbsForm.bbsId.value=bbsId;
		document.bbsForm.action="<c:url value='/user/board.do'/>";
		document.bbsForm.submit();
	}

</script>
<form name="bbsForm" method="post">
	<input type="hidden" name="bbsId"/>
	<input type="hidden" name="nttId" value="0"/>
</form>
<form name="seqForm" method="post">
	<input type="hidden" name="seq"/>
</form>
<body <c:if test="${menuInfo.isRmouse eq 'N'}">onselectstart="return false;" oncontextmenu="return false;" </c:if>>
<c:set var="menuInfo" value="${menuInfo }" scope="application"/>
<c:set var="menuList" value="${menuList }" scope="application"/>
