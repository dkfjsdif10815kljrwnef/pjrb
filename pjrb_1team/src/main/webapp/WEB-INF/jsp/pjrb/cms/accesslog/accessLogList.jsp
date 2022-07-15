<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<script>
$(document).ready(function() 
{
	fnInit();
	// task alert
	setTimeout("fnAlert()", 500); // 500ms(0.5초) 후 alert창호출
	
});

function fnAlert(){
	<c:if test="${!empty resultMsg}">alert('${resultMsg}');</c:if>
}

/*********************************************************
 * 초기화
 ******************************************************** */

 function fnInit(){
	// 첫 입력란에 포커스..
	//document.articleForm.searchCnd.focus();
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */

 function fnLinkPage(pageNo){
	document.articleForm.pageIndex.value = pageNo;
	document.articleForm.action = "<c:url value='/cms/accesslog/accessLogList.do'/>";
   	document.articleForm.submit();	
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */

 function fnSearch(){
	document.articleForm.action = "<c:url value='/cms/accesslog/accessLogList.do'/>";
   	document.articleForm.submit();	
}

 function fnStatus(status){
		document.articleForm.status.value = status;
		document.articleForm.action = "<c:url value='/cms/accesslog/accessLogList.do'/>";
	    document.articleForm.submit();
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
						<li class="breadcrumb-item active">접속 로그</li>
					</ol>
				</div>
				<h4 class="page-title">접속 로그</h4>
			</div>
		</div>
	</div>
	<!-- end page title -->
	<form method="post" id="articleForm" name="articleForm">
		<input type="hidden" name="status" id="status" value="${searchVO.status }"/>
		<div class="row">
			<div class="col-xl-12">
				<div class="card">
					<div class="card-body">
						<ul class="nav nav-tabs nav-bordered mb-2">
				            <li class="nav-item">
				                <a class="nav-link <c:if test='${searchVO.status eq "Y" || empty searchVO.status }'>active</c:if>" href="#" onclick="fnStatus('Y'); return false;">성공</a>
				            </li>
				            <li class="nav-item">
				            	<a class="nav-link <c:if test='${searchVO.status eq "N" }'>active</c:if>" href="#" onclick="fnStatus('N'); return false;">실패</a>
				        	</li>
				        </ul>
						<div class="alert alert-light bg-light text-dark sch_wrap">
							<div class="input-group col-sm-12">
								<div class="input-group col-sm">
									<select class="custom-select" name="searchCnd">
										<option value="all" <c:if test="${searchVO.searchCnd == 'all' }">selected</c:if>>전체</option>
										<option value="id" <c:if test="${searchVO.searchCnd == 'id' }">selected</c:if>>접속ID</option>
										<option value="ip" <c:if test="${searchVO.searchCnd == 'ip' }">selected</c:if>>접속IP</option>
									</select>
								</div>
								<div class="input-group col-sm app-search">
									<input type="text" name="searchWrd" value="<c:out value='${searchVO.searchWrd }'/>" class="form-control" placeholder="검색어 입력">
									<span class="search-icon"></span>
									<div class="input-group-append">
										<button class="btn btn-primary" onclick="fnSearch();">Search</button>
									</div>
								</div>
							</div>
						</div>
						<div data-simplebar class="table-responsive">
							<table class="table mb-0 table-hover">
								<thead class="thead-dark">
									<tr>
										<th scope="col">번호</th>
										<th scope="col">접속ID</th>
										<th scope="col">접속IP</th>
										<th scope="col">로그인 일시</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="result" items="${logList}" varStatus="status">
										<tr>
											<td>
												<c:out value="${(paginationInfo.totalRecordCount - status.index) - ( (paginationInfo.currentPageNo - 1)  *  paginationInfo.recordCountPerPage ) }" />
											</td>
											<td>
												<c:out value="${result.userId }" />
											</td>
											<td>
												<c:out value="${result.accessIp}" />
											</td>
											<td>
												<fmt:parseDate value="${result.logDate}" var="time" pattern="yyyy-MM-dd HH:mm:ss.S" />
												<fmt:formatDate value="${time}" pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty logList}">
										<tr class="nodata">
											<td colspan="8">등록된 데이터가 없습니다.</td>
										</tr>
									</c:if>
								</tbody>
							</table>
						</div>
						<!-- end table-responsive -->
						<div class="row mt-4">
							<div class="col-sm-12 col-md-5">
								<div class="dataTables_length">
									<label>목록개수 <select class="custom-select mb-3" id="recordCountPerPage" name="recordCountPerPage" onchange="javascript:fnSearch();">
											<option value="10" <c:if test="${searchVO.recordCountPerPage == 10 }">selected</c:if>>10</option>
											<option value="15" <c:if test="${searchVO.recordCountPerPage == 15 }">selected</c:if>>15</option>
											<option value="20" <c:if test="${searchVO.recordCountPerPage == 20 }">selected</c:if>>20</option>
											<option value="30" <c:if test="${searchVO.recordCountPerPage == 30 }">selected</c:if>>30</option>
											<option value="40" <c:if test="${searchVO.recordCountPerPage == 40 }">selected</c:if>>40</option>
											<option value="50" <c:if test="${searchVO.recordCountPerPage == 50 }">selected</c:if>>50</option>
											<option value="100" <c:if test="${searchVO.recordCountPerPage == 100 }">selected</c:if>>100</option>
										</select></label>
								</div>
							</div>
							<div class="col-sm-12 col-md-7">
								<div class="dataTables_paginate paging_simple_numbers">
									<ul class="pagination pagination-rounded">
										<ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fnLinkPage" />
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" name="pageIndex" value="1" />
	</form>
</div>
<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />
