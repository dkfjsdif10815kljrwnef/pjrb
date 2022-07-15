<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<title>NICE평가정보 가상주민번호 서비스</title>
	<script language='javascript'>
		function fnLoad()
		{
// 			var cnt = "${ipinCnt}";
			
// 			if(cnt > 0 && cnt != null){
// 				alert("가입된 회원 정보가 있습니다.");
// 				self.close();
// 				return;

			opener.location.href="<c:url value='/user/member/joinForm.do'/>";
			self.close();
		}
	</script>
</head>
<body onLoad="fnLoad()">
<form name="authForm" method="post">
	<input type="hidden" name=""/>
</form>
</body>
</html>