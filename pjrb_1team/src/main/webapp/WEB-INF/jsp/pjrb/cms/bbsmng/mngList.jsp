<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />

<script>
	function fn_mngList(pageNo) {
		document.frm.pageIndex.value = pageNo;

		document.frm.action = '<c:url value="/cms/bbsmng/mngList.do"/>';
		document.frm.submit();
	}

	function fn_mngWrite() {
		document.frm.action = '<c:url value="/cms/bbsmng/mngWrite.do"/>';
		document.frm.submit();
	}

	function fn_mngView(bbsId) {
		document.frm.bbsId.value = bbsId;

		document.frm.action = '<c:url value="/cms/bbsmng/mngModify.do"/>';
		document.frm.submit();
	}

	function fn_search() {
		document.frm.pageIndex.value = 1;
		document.frm.pageUnit.value = $("#pageUnit").val();
		document.frm.action = '<c:url value="/cms/bbsmng/mngList.do"/>';
		document.frm.submit();
	}

	function fn_searchKeyPressed(event) {
		if (event.keyCode == 13) {
			fn_search('1');
		}
	}
	
	function fn_excelDown(){
		document.frm.action = '<c:url value="/cms/bbsmng/mngListExcelDown.do"/>';
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
						<li class="breadcrumb-item"><a href="<c:url value='/cms/main.do'/>"><i class="fal fa-home-alt"></i>대시보드</a></li>
						<li class="breadcrumb-item active">게시판 관리</li>
					</ol>
				</div>
				<h4 class="page-title">게시판 관리</h4>
			</div>
		</div>
	</div>
	<!-- end page title -->
	<div class="row">
		<div class="col-xl-12">
			<div class="card">
				<div class="card-body">
					<form method="post" id="frm" name="frm" onsubmit="return false">
						<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
						<input type="hidden" name="pageUnit" value="<c:out value='${searchVO.pageUnit}'/>" />
						<input type="hidden" name="bbsId" value="" />
						<div class="alert alert-light bg-light text-dark sch_wrap">
							<div class="input-group col-sm-12">
								<div class="input-group col-sm">
									<select class="custom-select" name=searchCnd>
										<option value="">게시판유형 전체</option>
										<option value="1" <c:if test="${searchVO.searchCnd eq '1' }">selected</c:if>>리스트</option>
										<option value="2" <c:if test="${searchVO.searchCnd eq '2' }">selected</c:if>>이미지</option>
										<option value="4" <c:if test="${searchVO.searchCnd eq '4' }">selected</c:if>>묻고답하기</option>
										<option value="3" <c:if test="${searchVO.searchCnd eq '3' }">selected</c:if>>동영상</option>
										<%-- <option value="5" <c:if test="${searchVO.bbsTp eq '5' }">selected</c:if>>비밀글</option>
										<option value="6" <c:if test="${searchVO.bbsTp eq '6' }">selected</c:if>>구인/구직</option>
										<option value="7" <c:if test="${searchVO.bbsTp eq '7' }">selected</c:if>>포스터형</option> --%>
									</select>
								</div>
								<%-- <div class="input-group col-sm">
									<select class="custom-select" name="searchUseAt">
										<option value="">사용여부 전체</option>
										<option value="Y" <c:if test="${searchVO.searchUseAt eq 'Y' }">selected</c:if>>사용</option>
										<option value="N" <c:if test="${searchVO.searchUseAt eq 'N' }">selected</c:if>>미사용</option>
									</select>
								</div> --%>
								<div class="input-group col-sm app-search">
									<input type="text" class="form-control" placeholder="게시판명" name="searchWrd" value="<c:out value="${searchVO.searchWrd }"/>" onkeypress="javascript:fn_searchKeyPressed(event);">
									<span class="search-icon"></span>
									<div class="input-group-append">
										<button class="btn btn-primary" onclick="javascript:fn_search();">Search</button>
									</div>
								</div>
							</div>
						</div>
					</form>

					<div class="dt-buttons col-sm-12 mb-3">
						<!-- <button class="btn btn-secondary flaot-left" type="button" onclick="javascript:fn_excelDown();"><span>엑셀 다운로드</span></button> -->
						<button class="btn btn-secondary flaot-left" type="button" onclick="javascript:fn_excel('bbsmng','');">
							<span>엑셀 다운로드</span>
						</button>
						<div class="float-right" style="vertical-align: bottom;">
							<button class="btn btn-success" type="button" onclick="javascript:fn_mngWrite();">
								<span>게시판 추가</span>
							</button>
						</div>
					</div>
					<div data-simplebar class="table-responsive">
						<table class="table mb-0 table-hover">
							<thead class="thead-dark">
								<tr>
									<th scope="col">번호</th>
									<th scope="col">게시판명</th>
									<th scope="col">게시판유형</th>
									<th scope="col">글목록</th>
									<th scope="col">생성일</th>
									<!-- <th scope="col">상태</th> -->
								</tr>
							</thead>
							<tbody>
								<c:forEach var="result" items="${resultList}" varStatus="status">
									<tr>
										<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
										<td>
											<a href="javascript:void(0);" onclick="javascript:fn_mngView('<c:out value="${result.bbsId }"/>');"><c:out value="${result.bbsNm }" /></a>
										</td>
										<td>
											<c:if test="${result.bbsTp eq '1' }">리스트</c:if>
											<c:if test="${result.bbsTp eq '2' }">이미지</c:if>
											<c:if test="${result.bbsTp eq '3' }">동영상</c:if>
											<c:if test="${result.bbsTp eq '4' }">묻고답하기</c:if>
											<%-- <c:if test="${result.bbsFlag eq '5' }">비밀글</c:if>
											<c:if test="${result.bbsFlag eq '6' }">구인/구직</c:if>
											<c:if test="${result.bbsFlag eq '7' }">포스터형</c:if> --%>
										</td>
										<td>
											<a href="javascript:void(0);" onclick="javascript:fn_mainBbsList('<c:out value="${result.bbsId }"/>');">바로가기</a>
										</td>
										<td>
											<c:out value="${fn:substring(result.regDate,0,10) }" />
										</td>
										<%-- <td>
						                    <input type="checkbox" id="switch<c:out value='${status.index }'/>" onchange="javascript:fn_" <c:if test="${result.useYn eq 'Y' }">checked</c:if> data-switch="bool">
						                    <label for="switch<c:out value='${status.index }'/>" data-on-label="사용" data-off-label="미사용"></label>
						                  </td> --%>
									</tr>
								</c:forEach>
								<c:if test="${resultList == null || empty resultList }">
									<tr class="nodata">
										<td colspan="6">등록된 데이터가 없습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					<!-- end table-responsive -->
					<div class="row mt-4">
						<div class="col-sm-12 col-md-5">
							<div class="dataTables_length">
								<label>목록개수 <select class="custom-select mb-3" id="pageUnit" onchange="javascript:fn_search();">
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
									<ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fn_mngList" />
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />