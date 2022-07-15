<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="subMenu" items="${subMenuList[subMenuDepth[searchVO.searchCnd]] }" varStatus="status">
	<c:choose>
		<c:when test="${subMenu.menuDepth eq '2' }">
			 <h4>
			 	<a href="<c:url value='${subMenu.menuUrl }'/>" <c:if test="${subMenu.isBlank eq 'Y' }">target="_blank"</c:if>>
			 		<c:out value="${subMenu.menuTitle }"/>
			 		<c:if test="${subMenu.isBlank eq 'Y' }">
			 			<img src="${pageContext.request.contextPath}/images/user/external_icon.png" alt="" class="external_icon">
			 		</c:if>
			 	</a>
			 </h4>
		</c:when>
		<c:when test="${subMenu.menuDepth eq '3' }">
			 <h5>
			 	<a href="<c:url value='${subMenu.menuUrl }'/>" <c:if test="${subMenu.isBlank eq 'Y' }">target="_blank"</c:if>>
			 		<c:out value="${subMenu.menuTitle }"/>
			 		<c:if test="${subMenu.isBlank eq 'Y' }">
			 			<img src="${pageContext.request.contextPath}/images/user/external_icon.png" alt="" class="external_icon">
			 		</c:if>
			 	</a>
			 </h5>
		</c:when>
		<c:when test="${subMenu.menuDepth eq '4' }">
			<c:if test="${status.first }">
				<ul>
			</c:if>	
             <li>
             	<a href="<c:url value='${subMenu.menuUrl }'/>" <c:if test="${subMenu.isBlank eq 'Y' }">target="_blank"</c:if>>
             		<c:out value="${subMenu.menuTitle }"/>
             		<c:if test="${subMenu.isBlank eq 'Y' }">
			 			<img src="${pageContext.request.contextPath}/images/user/external_icon.png" alt="" class="external_icon">
			 		</c:if>
             	</a>
             </li>
             <c:if test="${status.last }">
				</ul>
			</c:if>	
		</c:when>
	</c:choose>
	<c:import url="/inc/importMenu.do">
		<c:param name="searchWrd" value="${subMenu.menuId }"/>
		<c:param name="searchCnd" value="${subMenu.menuDepth }"/>
	</c:import>
</c:forEach>
<c:if test="${searchVO.searchWrd eq 'MENU_0000000006' }">
	<h4><a href="<c:url value='/user/board.do?bbsId=BBSMSTR_000000000001'/>">공지사항</a></h4>
</c:if>
