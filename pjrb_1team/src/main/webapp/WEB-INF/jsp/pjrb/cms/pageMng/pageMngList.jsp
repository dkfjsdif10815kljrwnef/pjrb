<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/header" />
<script>
	function fn_pageLink(pageNo) {
		document.searchForm.pageIndex.value = pageNo;
		document.searchForm.pageUnit.value = $("#pageUnit").val();
		document.searchForm.action = '<c:url value="/cms/pageMng/pageMngList.do"/>';
		document.searchForm.submit();
	}
	
	function fn_search(){
		document.searchForm.pageIndex.value = 1;
		document.searchForm.pageUnit.value = $("#pageUnit").val();
		document.searchForm.action = '<c:url value="/cms/pageMng/pageMngList.do"/>';
		document.searchForm.submit();
	}
	
	function fn_excelDown(){
		document.dtForm.action="<c:url value='/cms/pageMng/pageMngExcelDown.do'/>";
		document.dtForm.submit();
	}
	
	function fn_updateUseYn(pageId , isCheck){
		var yn = "";
		if(isCheck){
			yn = "Y";
		}else{
			yn = "N";
		}
		$.ajax({
			url:"<c:url value='/cms/pageMng/pageMngUpdateUseYn.do'/>",
			type:"POST",
			data:{"pageId":pageId , "useYn":yn},
			success:function(){
				alert("변경되었습니다.");
			}
		})
	}
	
	function fn_detail(pageId){
		document.dtForm.pageId.value =pageId;
		document.dtForm.action = '<c:url value="/cms/pageMng/pageMngForm.do"/>';
		document.dtForm.submit();
	}
</script>
<form name="dtForm" method="post">
	<input type="hidden" name="pageId" />
</form>
<!-- Start Content -->
<div class="container-fluid">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <ol class="breadcrumb m-0">
            <li class="breadcrumb-item"><a href="/cms/main.do"><i class="fal fa-home-alt"></i>대시보드</a></li>
            <li class="breadcrumb-item active">페이지 관리</li>
          </ol>
        </div>
        <h4 class="page-title">페이지 관리</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row">
    <div class="col-xl-12">
      <div class="card">
        <div class="card-body">
          <div class="alert alert-light bg-light text-dark sch_wrap">
          	<form name="searchForm" method="post">
          		<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex }'/>"/>
        		<input type="hidden" name="pageUnit" value="<c:out value='${searchVO.pageUnit }'/>"/>
	            <div class="input-group col-sm-12">
	              <div class="input-group col-sm">
	                <select class="custom-select" name="searchCnd">
	                  <option value="0" <c:if test="${searcVO.searchCnd eq '0' or empty searcVO.searchCnd }">selected</c:if>>페이지명</option>
	                </select>
	              </div>
	              <%-- <select class="custom-select" name="useYn">
	                <option value="" <c:if test="${empty searchVO.useYn }">selected</c:if>>사용여부 전체</option>
	                <option value="Y" <c:if test="${searchVO.useYn eq 'Y' }">selected</c:if>>사용</option>
	                <option value="N" <c:if test="${searchVO.useYn eq 'N'}">selected</c:if>>미사용</option>
	              </select> --%>
	              <div class="input-group col-sm app-search">
	                <input type="text" class="form-control" placeholder="페이지명" name="searchWrd" onkeypress="javascript:if (event.keyCode==13) {fn_search()}" value="<c:out value='${searchVO.searchWrd }'/>">
	                <span class="search-icon"></span>
	                <div class="input-group-append">
	                  <button class="btn btn-primary" onclick="javascript:fn_search();">Search</button>
	                </div>
	              </div>
	            </div>
            </form>
          </div>
          <div class="dt-buttons col-sm-12 mb-3">
            <!-- <button class="btn btn-secondary flaot-left" type="button" onclick="javascript:fn_excelDown();"><span>엑셀 다운로드</span></button> -->
            <button class="btn btn-secondary flaot-left" type="button" onclick="javascript:fn_excel('pageMng','');"><span>엑셀 다운로드</span></button>
            <div class="float-right">
              <button class="btn btn-success" type="button" onclick="location.href='/cms/pageMng/pageMngForm.do'"><span>페이지 추가</span></button>
            </div>
          </div>
          <div class="table-responsive" data-simplebar>
            <table class="table mb-0 table-hover">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">번호</th>
                  <th scope="col">페이지명</th>
                  <th scope="col">생성일</th>
                  <!-- <th scope="col">상태</th> -->
                </tr>
              </thead>
              <tbody>
              	<c:forEach var="result" items="${resultList }" varStatus="status">
	                <tr>
	                  <td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
	                  <td><a href="javascript:void(0);" onclick="javascript:fn_detail('<c:out value='${result.pageId }'/>');"><c:out value="${result.pageNm }"/></a></td>
	                  <td><c:out value="${fn:substring(result.cdate,0,10) }"/></td>
	                  <%-- <td>
	                    <input type="checkbox" id="switch_<c:out value='${result.pageId }'/>" <c:if test="${result.useYn eq 'Y' }"> checked</c:if> data-switch="bool" onchange="javascript:fn_updateUseYn('<c:out value='${result.pageId }'/>',$(this).is(':checked'))">
	                    <label for="switch_<c:out value='${result.pageId }'/>" data-on-label="사용" data-off-label="미사용"></label>
	                  </td> --%>
	                </tr>
                </c:forEach>
                <c:if test="${empty resultList }">
	                <tr class="nodata">
	                  <td colspan="4">등록된 데이터가 없습니다.</td>
	                </tr>
                </c:if>
              </tbody>
            </table>
          </div> <!-- end table-responsive -->
          <div class="row mt-4">
            <div class="col-sm-12 col-md-5">
              <div class="dataTables_length"><label>목록개수
                  <select class="custom-select mb-3" id="pageUnit" onchange="javascript:fn_search();">
                    <option value="10" <c:if test="${searchVO.pageUnit eq '10' }">selected</c:if>>10</option>
                    <option value="15" <c:if test="${searchVO.pageUnit eq '15' }">selected</c:if>>15</option>
                    <option value="20" <c:if test="${searchVO.pageUnit eq '20' }">selected</c:if>>20</option>
                    <option value="30" <c:if test="${searchVO.pageUnit eq '30' }">selected</c:if>>30</option>
                    <option value="40" <c:if test="${searchVO.pageUnit eq '40' }">selected</c:if>>40</option>
                    <option value="50" <c:if test="${searchVO.pageUnit eq '50' }">selected</c:if>>50</option>
                    <option value="100" <c:if test="${searchVO.pageUnit eq '100' }">selected</c:if>>100</option>
                  </select></label></div>
            </div>
            <div class="col-sm-12 col-md-7">
              <div class="dataTables_paginate paging_simple_numbers">
                <ul class="pagination pagination-rounded">
                  <ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fn_pageLink" />
                </ul>
              </div>
            </div>
          </div> <!-- end list-all -->
        </div> <!-- end card body-->
      </div> <!-- end card -->
    </div><!-- end col-->
  </div>
</div>

<!-- /Start Content -->
<c:import url="/EgovPageLink.do?link=pjrb/cms/inc/footer" />