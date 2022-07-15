<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
    <title>CMS</title>
     <script src="${pageContext.request.contextPath}/js/cms/jquery-3.5.1.min.js"></script>
    <script language='javascript'>

        <c:if test="${!empty paramMap.message}">
            alert("<c:out value="${paramMap.message}" />");
        </c:if>
        <c:url value="${paramMap.url}" />
        <c:out value="${paramMap.function}" />

    </script>
</head>
<body>
<form name="frm" action="<c:url value="${paramMap.action}" />" method="post">
    ${paramMap.inputs}
</form>
</body>
</html>