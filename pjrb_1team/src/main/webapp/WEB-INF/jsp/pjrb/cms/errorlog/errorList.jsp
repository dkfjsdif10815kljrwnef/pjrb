<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<script>

$(document).ready(function (){
	
	if("${searchVO.searchDate}" == null || "${searchVO.searchDate}" == "") {
		$("#searchDateD").val("");
	}

	$('input[id="searchDateD"]').on('cancel.daterangepicker', function(ev, picker) {
	    $(this).val('');
	});
});

function fnLinkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/errorlog/errorList.do'/>";
  	document.listForm.submit();	
}

function fnSearch(){
	
	var dateArr = $("#searchDateD").val();
	 
	if(dateArr!=null && dateArr!="")
	{
		document.listForm.searchDate.value = dateArr;
		dateArr = dateArr.split("~");
		$("#searchStartDate").val(dateArr[0]);
		$("#searchEndDate").val(dateArr[1]+" 23:59");
	}
	
	if($("#searchStartDate").val().length > 0 && $("#searchEndDate").val().length > 0){
		if($("#searchStartDate").val() > $("#searchEndDate").val()){
			alert('기간을 확인 하세요');
			return false;
		}
	}
	
	document.listForm.action = "<c:url value='/cms/errorlog/errorList.do'/>";
    document.listForm.submit();
}


	function fn_detail(seq){
		document.logDtlForm.action="<c:url value='/cms/errorlog/errorDetail.do'/>"
		document.logDtlForm.seq.value=seq;
		document.logDtlForm.submit();
	}
</script>
<form name="logDtlForm" method="post">
	<input type="hidden" name="seq" value=""/>
</form>
<form id="searchVO" name="listForm" method="post">
<input type="hidden" name="searchStartDate" id="searchStartDate"/>
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex }'/>"/>
<input type="hidden" name="searchEndDate" id="searchEndDate"/>
<input type="hidden" name="searchDate" id="searchDate" value="${searchVO.searchDate }"/>
<div class="container-fluid">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <ol class="breadcrumb m-0">
            <li class="breadcrumb-item"><a href="<c:url value='/cms/main.do'/>"><i class="fal fa-home-alt"></i></a></li>
            <li class="breadcrumb-item"><a href="javascript:void(0);">관리자</a></li>
            <li class="breadcrumb-item active">에러로그</li>
          </ol>
        </div>
        <h4 class="page-title">에러 로그</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
         
          <div class="alert alert-light bg-light text-dark sch_wrap">
            <div class="input-group col-sm-12">
              <div class="input-group col-sm">
              	
                	<input type="text" autocomplete="off" class="form-control" data-toggle="date-picker" data-cancel-class="btn-warning" id="searchDateD" value="${searchVO.searchDate }">
                
                <div class="input-group-append">
                  <button class="btn btn-primary" type="button" onclick="fnSearch();">Search</button>
                </div>
              </div>
            </div>
          </div>
          <div data-simplebar class="table-responsive">
            <table class="table mb-0 table-hover">
              <thead class="thead-dark">
                <tr>
                	<th scope="col">번호</th>
                  <th scope="col">메세지</th>
                  <th scope="col">날짜</th>
                </tr>
              </thead>
              <tbody>
				<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
					  <td><a href="javascript:void(0)" onclick="javascript:fn_detail('<c:out value='${result.seq }'/>')" ><c:out value="${result.message}"/></a></td>
					  <td><c:out value="${result.regDt}"/></td>
					</tr>
				</c:forEach>
              </tbody>
              
            </table>
          </div> <!-- end table-responsive -->
          <div class="row mt-4">
			<div class="col-sm-12 col-md-5">
			  <div class="dataTables_length"><label>목록개수
				  <select class="custom-select mb-3" id="recordCountPerPage" name="recordCountPerPage" onchange="javascript:fnSearch();">
					<option value="10" <c:if test="${searchVO.recordCountPerPage == 10 }">selected</c:if>>10</option>
					<option value="15" <c:if test="${searchVO.recordCountPerPage == 15 }">selected</c:if>>15</option>
					<option value="20" <c:if test="${searchVO.recordCountPerPage == 20 }">selected</c:if>>20</option>
					<option value="30" <c:if test="${searchVO.recordCountPerPage == 30 }">selected</c:if>>30</option>
					<option value="40" <c:if test="${searchVO.recordCountPerPage == 40 }">selected</c:if>>40</option>
					<option value="50" <c:if test="${searchVO.recordCountPerPage == 50 }">selected</c:if>>50</option>
					<option value="100" <c:if test="${searchVO.recordCountPerPage == 100 }">selected</c:if>>100</option>
				  </select></label></div>
			</div>
			<div class="col-sm-12 col-md-7">
			  <div class="dataTables_paginate paging_simple_numbers">
				<ul class="pagination pagination-rounded">
					<ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fnLinkPage" />
				</ul>
			  </div>
			</div>
		  </div>
        </div> <!-- end card body-->
      </div> <!-- end card -->
    </div><!-- end col-->
  </div>
</div>
</form>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />
 