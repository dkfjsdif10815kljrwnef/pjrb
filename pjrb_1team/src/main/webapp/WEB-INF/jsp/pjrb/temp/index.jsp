<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/temp/inc/header" />
<script>

</script>

<!-- Start Content-->
<div class="container-fluid dashboard">
    <!-- start page title -->
    <div class="row">
        <div class="col-12">
            <div class="page-title-box">
                <h4 class="page-title">Temp대시보드</h4>
                <h4 class="page-title"><a href="<c:url value='/temp/popupList.do'/>" target="_blank">게시판</a></h4>
                <h4 class="page-title"><a href="<c:url value='/temp/excel/list.do'/>" target="_blank">excel</a></h4>
                <h4 class="page-title"><a href="<c:url value='/temp/email/list.do'/>" target="_blank">email</a></h4>
            </div>
        </div>
    </div>
</div>
<!-- container -->

<c:import url="/EgovPageLink.do?link=pjrb/temp/inc/footer" />