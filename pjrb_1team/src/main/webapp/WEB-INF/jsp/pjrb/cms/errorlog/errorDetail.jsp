<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<%
     //치환 변수 선언합니다.
      pageContext.setAttribute("crcn", "\r\n"); //Space, Enter
      pageContext.setAttribute("br", "<br/>"); //br 태그
%> 

<!-- Start Content -->
<div class="container-fluid">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <ol class="breadcrumb m-0">
            <li class="breadcrumb-item"><a href="<c:url value='/cms/main.do'/>"><i class="fal fa-home-alt"></i></a></li>
            <li class="breadcrumb-item"><a href="javascript:void(0)">관리자</a></li>
            <li class="breadcrumb-item active">에러로그</li>
          </ol>
        </div>
        <h4 class="page-title">에러로그</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->

	  <div class="row">
	    <div class="col-xl-12">
	      <div class="card">
	        <div class="card-body">
                <div class="row">
	              <div class="col-lg-12">
	                <div class="form-group" style="font-size:15px;">
	                  ${fn:replace(result.exception , crcn, br)}
	                </div>
	              </div>
	            </div>

	            <div class="btn-group bottom-btn float-right">
	              <button type="button" class="btn btn-danger" onclick="location.href='/cms/errorlog/errorList.do'">취소</button>
	            </div>
	        </div>
	      </div> <!-- end card body-->
	    </div> <!-- end card -->
	  </div><!-- end col-->

</div>
</div>
<!-- /Start Content -->

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />