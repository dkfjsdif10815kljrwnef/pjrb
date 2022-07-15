<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<script>

$( document ).ready( function () {
	$("#detail").load("<c:url value='/cms/grade/gradeView.do'/>", {grade:'<c:out value="${searchVO.grade}"/>'});
	if("<c:out value='${loginVO.sessionGrade }'/>" != "0" && '<c:out value="${searchVO.grade}"/>'=='1')	document.getElementById("btnSubmit").style.display ='none';
});

function fn_detail(grade) {
	$("#detail").load("<c:url value='/cms/grade/gradeView.do'/>", {grade:grade});
	
	if("<c:out value='${loginVO.sessionGrade }'/>" != "0" &&  grade=='1')	document.getElementById("btnSubmit").style.display ='none';
	else document.getElementById("btnSubmit").style.display ='block';
}

function fn_submit() {
	var url = '<c:url value="/cms/grade/gradeUpdate.do"/>';
	if(!$("input[name='gradeNm']").val()){
		alert("권한명을 입력하세요");
		return;
	}
	
	document.frm.action = url;
   	document.frm.submit();
}

</script>

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
            <li class="breadcrumb-item active"> 권한 관리</li>
          </ol>
        </div>
        <h4 class="page-title">권한 관리</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
          <div class="row">
            <div class="col-lg-4">
              <div data-simplebar class="table-responsive">
                <table class="table mb-0 table-hover">
                  <thead class="thead-dark">
                    <tr>
                      <th scope="col">번호</th>
                      <th scope="col">권한명</th>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:forEach var="result" items="${resultList}" varStatus="status">
	                    <tr>
	                      <th scope="row"><c:out value="${status.count }"/></th>
	                      <td><a href="javascript:void(0);" onclick="javascript:fn_detail('<c:out value="${result.grade }"/>')"><c:out value="${result.gradeNm }"/></a></td>
	                    </tr>
	                </c:forEach>
                  </tbody>
                </table>
              </div> <!-- end table-responsive -->
            </div>
            <div class="col-lg-8">
	  			<form class="needs-validation" novalidate id="frm" name="frm" method="post">
					<div id="detail"></div>  
				</form>
			</div>
		  </div>
          <div class="btn-group bottom-btn float-right" id="btnSubmit">
            <button type="button" class="btn btn-success" onclick="javascript:fn_submit();">저장</button>
          </div>
        </div> <!-- end card body-->
      </div> <!-- end card -->
    </div><!-- end col-->
  </div>
</div>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />