<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:import url="/EgovPageLink.do?link=pjrb/user/inc/header" />
<div class="sub_top sub_top07">
    <div class="page_title">
        <h2>사이트맵</h2>
    </div>    
</div>
<div class="sub_wrap">
    <div class="sub_cont page_sitemap"> 
		<c:set var="subMenuDepth" value="${fn:split(menuList.subMenuDepth,',') }" scope="application"/>
		<c:forEach var="dep_1" items="${menuList.dep_1 }" varStatus="status">
			<section class="menu_wrap menu_wrap0${status.count }">	
				<h3><c:out value="${dep_1.menuTitle }"/></h3>
				<c:import url="/inc/importMenu.do">
					<c:param name="searchWrd" value="${dep_1.menuId }"/>
					<c:param name="searchCnd" value="${dep_1.menuDepth}"/>
				</c:import>
			</section>
		</c:forEach>
        <section class="menu_wrap menu_wrap07">
            <h3>홈페이지 가이드</h3>
            <h4>
                <a href="http://www.yeosu.go.kr/www/support/mn02/mn0201/yeosu.go" target="_blank" title="여수시청 홈페이지 개인정보처리방침으로 새 창 이동">
                개인정보처리방침
                    <img src="${pageContext.request.contextPath}/images/user/external_icon.png" alt="새 창 아이콘" class="external">
                </a>
            </h4>
            <h4><a href="<c:url value='/user/content.do?pageId=PAGE_000000000000019'/>">이메일무단수집거부</a></h4>
            <h4>
                <a href="http://www.yeosu.go.kr/www/support/mn150000/yeosu.go" target="_blank" title="여수시청 홈페이지 저작권정책으로 새 창 이동">
                    저작권 정책
                    <img src="${pageContext.request.contextPath}/images/user/external_icon.png" alt="새 창 아이콘" class="external"></a>
            </h4>
        </section>
    </div>
</div>
<c:import url="/EgovPageLink.do?link=pjrb/user/inc/footer" />