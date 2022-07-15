<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<c:forEach var="fileVO" items="${fileList}" varStatus="status">
		<c:if test="${status.count eq '1'}">
			<img src="<c:url value='/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${fileVO.atchFileId}"/>&fileSn=${fileVO.fileSn}" alt="<c:out value="${nttSj }"/>">
		</c:if>
	</c:forEach>
	<c:if test="${empty fileList}">
		<img src="/images/fcdt/user/noimg01.jpg" class="noimg noimg01" alt="이미지 없음">
	</c:if>