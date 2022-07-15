<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>
function fn_searchKeyPressed(event) {
	if (event.keyCode==13) {
		fnSearch();
	}
}


function fnSearch(){
	$.ajax({
		type:"POST",
		url:"<c:url value='/cms/menu/contentListPopup.do' />",
		data:{
			//"checkId": $("#mberId").val()	
			"pageIndex": "1",
			"searchWrd": $("#searchWrd").val(),
			"searchCnd": $("#searchCnd").val()
		},
		//dataType:'json',
		dataType:'html',
		async:false,
		success:function(returnData, status){
			
			if(status == "success") {
				//alert(returnData);
				$("#modal01").html(returnData);
			}else{ 
				alert("ERROR!");return;
			}
		}
	});
}

function fnLinkPage(pageNo){
	$.ajax({
		type:"POST",
		url:"<c:url value='/cms/menu/contentListPopup.do' />",
		data:{
			//"checkId": $("#mberId").val()	
			"pageIndex": pageNo,
			"searchWrd": $("#searchWrd").val(),
			"searchCnd": $("#searchCnd").val()
		},
		//dataType:'json',
		dataType:'html',
		async:false,
		success:function(returnData, status){
			
			if(status == "success") {
				//alert(returnData);
				$("#modal01").html(returnData);
			}else{ 
				alert("ERROR!");return;
			}
		}
	});
}

function selectBBS(pageId)
{
	var tp = "<c:out value='${menuType}'/>";
	if(tp == "1"){
		$("#menuUrl").val("/user/content.do?pageId="+pageId);		
	}else{
		
		$("#menuUrl").val("/userEng/content.do?pageId="+pageId);
	}
}

</script>



<form method="post" id="contentfrm" name="contentfrm" onsubmit="return false">
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="recordCountPerPage" value="<c:out value='${searchVO.recordCountPerPage}'/>"/>
<input type="hidden" name="bbsId" value=""/>


	<div class="modal-dialog modal-lg">
	  <div class="modal-content">
	    <div class="modal-header">
	      <h5 class="modal-title" id="exampleModalLabel">콘텐츠 선택</h5>
	      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	        <span aria-hidden="true">&times;</span>
	      </button>
	    </div>
	    <div class="modal-body">
	        <div class="alert alert-light bg-light text-dark sch_wrap">
	          <div class="input-group col-sm-12">
	            <div class="input-group col-sm">
	              <select class="custom-select" name="searchCnd" id="searchCnd">
	                <option value="0" selected="">페이지명</option>
	              </select>
	            </div>
	            <div class="input-group col-sm app-search">
	              <input type="text" class="form-control" placeholder="페이지명" id="searchWrd" name="searchWrd" value="<c:out value="${searchVO.searchWrd }"/>" onkeypress="javascript:fn_searchKeyPressed(event);">
	              <span class="search-icon"></span>
	              <div class="input-group-append">
	                <button type="button" class="btn btn-primary" onclick="javascript:fnSearch();">Search</button>
	              </div>
	            </div>
	          </div>
	        </div>
	        <div data-simplebar class="table-responsive">
	          <table class="table mb-0 table-hover">
	            <thead class="thead-dark">
	              <tr>
	                <th scope="col">번호</th>
	                <th scope="col">페이지명</th>
	                <th scope="col">사용여부</th>
	                <th scope="col">등록일</th>
	                <th scope="col">최종수정일</th>
	              </tr>
	            </thead>
	            <tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr>
				  <td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
				  <td><a href="javascript:void(0);" onclick="javascript:selectBBS('<c:out value="${result.pageId }"/>');" data-dismiss="modal"><c:out value="${result.pageNm }"/></a></td>
				  <td>
					<c:if test="${result.useYn eq 'Y' }"><div class="badge badge-success">사용</div></c:if>
					<c:if test="${result.useYn eq 'N' }"><div class="badge badge-danger">미사용</div></c:if>
				  </td>
				  <td><c:out value="${fn:substring(result.cdate,0,10) }"/></td>
				  <td><c:out value="${fn:substring(result.mdate,0,10) }"/></td>
				</tr>
			</c:forEach>
			<c:if test="${empty resultList}">
				<tr class="nodata">
				  <td colspan="5">등록된 데이터가 없습니다.</td>
				</tr>
			</c:if>
	            </tbody>
	          </table>
	        </div> <!-- end table-responsive -->
	        <div class="row mt-4">
		<div class="col-sm-12">
		  <div class="dataTables_paginate paging_simple_numbers">
			<ul class="pagination pagination-rounded">
				<ui:pagination paginationInfo="${paginationInfo}" type="pjrbcms" jsFunction="fnLinkPage" />
			</ul>
		  </div>
		</div>
	  </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	      </div>
	    </div>
	  </div>
</form>
